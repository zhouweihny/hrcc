package com.modules.service.impl;

import java.util.List;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.commons.util.netease.NeteaseUtil;
import com.commons.util.netease.bean.NeteaseResponse;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.template.TemplateMsg;
import com.commons.util.wx.bean.template.Vdata;
import com.modules.service.BillService;
import com.modules.service.SysDictdataService;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxService;
import com.modules.service.SysWxUserService;
import com.modules.service.UserService;
import com.modules.service.UserSupplierService;

import freemarker.template.TemplateScalarModel;

import com.modules.dao.AgentDao;
import com.modules.dao.BillDao;
import com.modules.dao.BillDetailDao;
import com.modules.pojo.Agent;
import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;
import com.modules.pojo.SysDictdata;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.SysWxUser;
import com.modules.pojo.User;
import com.modules.pojo.UserSupplier;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;

	@Autowired
	private UserService userService;
	@Autowired
	private UserSupplierService userSupplierService;
	@Autowired
	private SysWxUserService sysWxUserService;
	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;
	@Autowired
	private BillDetailDao billDetailDao;

	@Autowired
	private SysDictdataService sysDictdataService;

	@Autowired
	private AgentDao agentDao;

	@Value("#{configProperties['ws.template.billmsg']}")
	private String ws_template_billmsg;

	@Value("#{configProperties['wsp.template.billstatus']}")
	private String wsp_template_billstatus;

	@Autowired
	private SysWxService sysWxService;

	public void save(Bill entity) throws Exception {
		billDao.save(entity);
	}

	public void save(List<Bill> entities) throws Exception {
		billDao.save(entities);
	}

	public void update(Bill entity) throws Exception {
		billDao.update(entity);
	}

	public void update(Bill newEntity, Bill oldEntity) throws Exception {
		billDao.update(newEntity, oldEntity);
	}

	public void delete(Bill entity) throws Exception {
		billDao.delete(entity);
	}

	public Bill findObject(Bill entity) throws Exception {
		return billDao.findObject(entity);
	}

	public List<Bill> findList(Bill entity) throws Exception {
		return billDao.findList(entity);
	}

	public PageResult<Bill> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select b.* from t_bill b left join t_agent a on b.agentid=a.id left join t_user p on p.id=b.purchaserid left join t_user s on s.id =b.supplierid    where 1=1  ");

		StringBuffer sqls = new StringBuffer();

		int i = 0;
		if (params.containsKey("agentid")) {
			i++;
			if (sqls.length() != 0) {
				sqls.append("  or ");
			}
			sqls.append("     b.agentid in (select id from t_agent where userid=? ) ");
			ps.add(params.get("agentid"));
		}

		if (params.containsKey("purchaserid")) {
			i++;
			if (sqls.length() != 0) {
				sqls.append("  or ");
			}
			sqls.append("   b.purchaserid=? ");
			ps.add(params.get("purchaserid"));
		}

		if (params.containsKey("supplierid")) {
			i++;
			if (sqls.length() != 0) {
				sqls.append("  or ");
			}
			sqls.append("     b.supplierid=? ");
			ps.add(params.get("supplierid"));
			i++;
		}

		if (params.containsKey("contactid")) {
			i++;
			if (sqls.length() != 0) {
				sqls.append("  or ");
			}
			sqls.append("   b.purchaserid in(select userid from t_user_supplier us  where us.contactid= ?) ");
			ps.add(params.get("contactid"));
		}

		if (params.containsKey("erpusername")) {
			i++;
			if (sqls.length() != 0) {
				sqls.append("  or ");
			}
			sqls.append("    ( (b.erpusername = ? or b.erpusername is null) and   b.purchaserid=?)");
			ps.add(params.get("erpusername"));
			ps.add(params.get("companyid"));
		}

		if (i > 0) {
			sql.append("and ");
			if (i > 1) {
				sql.append("(").append(sqls).append(")");
			} else {
				sql.append(sqls);
			}
		}

		if (params.containsKey("status")) {
			sql.append(" and status=? ");
			ps.add(params.get("status"));
		}

		if (params.containsKey("planid")) {
			sql.append(" and b.planid=? ");
			ps.add(params.get("planid"));
		}

		if (params.containsKey("agent")) {
			sql.append(" and a.name like ? ");
			ps.add("%" + params.get("agent") + "%");
		}

		if (params.containsKey("supplier")) {
			sql.append(" and s.company like ? ");
			ps.add("%" + params.get("supplier") + "%");
		}

		if (params.containsKey("purchaser")) {
			sql.append(" and   p.company like ? ");
			ps.add("%" + params.get("purchaser") + "%");
		}

		if (params.containsKey("notpurchaser")) {
			sql.append(" and b.status!=-1 ");
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return billDao.findList(sqlContext, page, Bill.class);
	}

	@Override
	public void sendNotice(String billid) throws Exception {
		// TODO Auto-generated method stub
		Bill bill = new Bill();
		bill.setId(billid);
		bill = billDao.findObject(bill);

		UserSupplier userSupplier = new UserSupplier();
		if (!StringUtils.isEmpty(bill.getSupplierid())) {
			userSupplier.setUserid(bill.getPurchaserid());
			userSupplier.setSupplierid(bill.getSupplierid());
			userSupplier = userSupplierService.findObject(userSupplier);
		}
		Agent agent = new Agent();
		agent.setId(bill.getAgentid());
		agent = agentDao.findObject(agent);

		List<String> userids = new ArrayList<String>();
		if (!StringUtils.isEmpty(userSupplier.getContactid())) {
			userids.add(userSupplier.getContactid());
		}
		if (!StringUtils.isEmpty(agent.getUserid()) && userids.indexOf(agent.getUserid()) < 0) {
			userids.add(agent.getUserid());
		}
		for (String userid : userids) {
			User user = new User();
			user.setId(userid);
			user = userService.findObject(user);

			SysWxUser sysWxuser = new SysWxUser();
			sysWxuser.setUserid(user.getId());
			sysWxuser = sysWxUserService.findObject(sysWxuser);

			User tuser = new User();
			tuser.setId(bill.getPurchaserid());
			tuser = userService.findObject(tuser);

			if (sysWxuser != null) {
				TemplateMsg msg = new TemplateMsg();
				msg.setTemplate_id(ws_template_billmsg);
				msg.setTouser(sysWxuser.getOpenid());
				SysWx sysWx = new SysWx();
				sysWx.setId(sysWxuser.getWxid());
				sysWx = sysWxService.findObject(sysWx);
				msg.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3ca8549e433c9534&redirect_uri=" + sysWx.getDomain() + "/ws/billpagesub.do?billid=" + billid
						+ "&clearsession=true&wxcode=sanxing&response_type=code&scope=snsapi_base&state=000#wechat_redirect");
				Map<String, Vdata> data = new HashMap<String, Vdata>();
				msg.setData(data);

				Map<String, Vdata> map = new HashMap<String, Vdata>();
				Vdata firt = new Vdata();
				firt.setValue("您好,有一个来自  " + tuser.getCompany() + "的订单!");
				map.put("firt", firt);

				Vdata keyword1 = new Vdata();
				keyword1.setValue(bill.getNo());
				map.put("keyword1", keyword1);

				Vdata keyword2 = new Vdata();
				keyword2.setValue(tuser.getCompany());
				map.put("keyword2", keyword2);

				Vdata keyword3 = new Vdata();
				keyword3.setValue(bill.getNum() + "个品种");
				map.put("keyword3", keyword3);

				msg.setData(map);

				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(sysWxuser.getWxid());
				sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				WxUtil.sendTemplateMsg(msg, sysWxAccesstoken.getAccesstoken());
			} else {
				if (!StringUtils.isEmpty(user.getMobile())) {
					NeteaseResponse neteaseResponse = NeteaseUtil.sengNotice("4032124", "[\"" + user.getMobile() + "\"]", "[\"" + URLEncoder.encode(tuser.getCompany(), "utf-8") + "\"]");
				}

			}
		}

	}

	@Override
	public void upload(Bill bill, List<BillDetail> billdetails) throws Exception {
		// TODO Auto-generated method stub
		billDao.save(bill);
		for (BillDetail billdetail : billdetails) {
			billdetail.setBillid(bill.getId());
		}
		billDetailDao.save(billdetails);

	}

	@Override
	public void updateNum(String billid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("update  t_bill  set num=(select count(1) from t_bill_detail where billid=t_bill.id ) where id=?");
		ps.add(billid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		billDao.update(sqlContext);

	}

	@Override
	public void sendStatus(String billid) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");

		Bill bill = new Bill();
		bill.setId(billid);
		bill = billDao.findObject(bill);
		String userid = bill.getPurchaserid();
		if (!StringUtils.isEmpty(bill.getErpusername())) {
			User user = new User();
			user.setParentid(userid);
			user.setErpusername(bill.getErpusername());
			user = userService.findObject(user);
			if (user != null) {
				userid = user.getId();
			}
		}

		Agent agent = new Agent();
		agent.setId(bill.getAgentid());
		agent = agentDao.findObject(agent);

		User tuser = new User();
		tuser.setId(bill.getPurchaserid());
		tuser = userService.findObject(tuser);

		List<String> userids = new ArrayList<String>();
		if (!StringUtils.isEmpty(tuser.getId())) {
			userids.add(tuser.getId());
		}
		if (!StringUtils.isEmpty(agent.getUserid()) && userids.indexOf(agent.getUserid()) < 0) {
			userids.add(agent.getUserid());
		}

		for (String id : userids) {
			SysWxUser sysWxuser = new SysWxUser();
			sysWxuser.setUserid(id);
			sysWxuser = sysWxUserService.findObject(sysWxuser);
			HashMap<String, Object> ps = new HashMap<String, Object>();
			ps.put("code", "bill.status");
			PageResult<SysDictdata> sysDictdatas = sysDictdataService.findList(ps, new Page(false));

			if (sysWxuser != null) {
				TemplateMsg msg = new TemplateMsg();
				msg.setTemplate_id(wsp_template_billstatus);
				msg.setTouser(sysWxuser.getOpenid());
				SysWx sysWx = new SysWx();
				sysWx.setId(sysWxuser.getWxid());
				sysWx = sysWxService.findObject(sysWx);

				msg.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcc2ddf7a5831ec72&redirect_uri=" + sysWx.getDomain() + "/wsp/billpagesub.do?billid=" + billid
						+ "&clearsession=true&wxcode=sanxing&response_type=code&scope=snsapi_base&state=000#wechat_redirect");

				msg.setUrl(sysWx.getDomain() + "/wsp/billpagesub.do?billid=" + billid);
				Map<String, Vdata> data = new HashMap<String, Vdata>();
				msg.setData(data);

				Map<String, Vdata> map = new HashMap<String, Vdata>();
				Vdata firt = new Vdata();
				firt.setValue("您好,订单" + bill.getNo() + "有更新!");
				map.put("firt", firt);

				Vdata keyword1 = new Vdata();
				keyword1.setValue(bill.getNo());
				map.put("keyword1", keyword1);

				Vdata keyword2 = new Vdata();
				keyword2.setValue(tuser.getCompany());
				map.put("keyword2", keyword2);

				Vdata keyword3 = new Vdata();
				for (SysDictdata dict : sysDictdatas.getData()) {
					if ((bill.getStatus() + "").equals(dict.getDkey())) {
						keyword3.setValue(dict.getValue());
					}
				}
				map.put("keyword3", keyword3);
				Vdata keyword4 = new Vdata();
				keyword4.setValue(sdf.format(new Date()));
				map.put("keyword3", keyword3);

				msg.setData(map);

				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(sysWxuser.getWxid());
				sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				WxUtil.sendTemplateMsg(msg, sysWxAccesstoken.getAccesstoken());
			}
		}
	}

}
