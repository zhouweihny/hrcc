package com.modules.service.impl;

import java.util.List;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.template.TemplateMsg;
import com.commons.util.wx.bean.template.Vdata;
import com.modules.service.PurchaseService;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxService;
import com.modules.service.SysWxUserService;
import com.modules.service.UserSupplierService;
import com.modules.vo.UserVo;
import com.modules.dao.CatalogDao;
import com.modules.dao.PurchaseDao;
import com.modules.dao.PurchaseDetailDao;
import com.modules.dao.SupplierPurchaseDao;
import com.modules.dao.UserDao;
import com.modules.dao.UserSupplierDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.Purchase;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.SupplierPurchase;
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
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDao purchaseDao;

	@Autowired
	private PurchaseDetailDao purchaseDetailDao;

	@Autowired
	private SupplierPurchaseDao supplierPurchaseDao;

	@Autowired
	private UserSupplierDao userSupplierDao;

	@Autowired
	private CatalogDao catalogDao;

	@Autowired
	private UserSupplierService userSupplierService;

	@Autowired
	private SysWxService sysWxService;

	@Autowired
	private SysWxUserService sysWxUserService;

	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@Autowired
	private UserDao userDao;

	@Value("#{configProperties['wx.template.purchasemsg']}")
	private String wx_template_purchasemsg;

	public void save(Purchase entity) throws Exception {
		purchaseDao.save(entity);
	}

	public void save(List<Purchase> entities) throws Exception {
		purchaseDao.save(entities);
	}

	public void update(Purchase entity) throws Exception {
		purchaseDao.update(entity);
	}

	public void update(Purchase newEntity, Purchase oldEntity) throws Exception {
		purchaseDao.update(newEntity, oldEntity);
	}

	public void delete(Purchase entity) throws Exception {
		purchaseDao.delete(entity);
	}

	public Purchase findObject(Purchase entity) throws Exception {
		return purchaseDao.findObject(entity);
	}

	public List<Purchase> findList(Purchase entity) throws Exception {
		return purchaseDao.findList(entity);
	}

	public PageResult<Purchase> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_purchase   where 1=1 ");

		if (params.containsKey("userid")) {
			sql.append(" and userid=? ");
			ps.add(params.get("userid"));
		}

		if (params.containsKey("planid")) {
			sql.append(" and  planid=? ");
			ps.add(params.get("planid"));
		}

		if (params.containsKey("name")) {
			sql.append(" and name  like  ? ");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("erpusername")) {
			sql.append(" and(  erpusername = ? or erpusername is null)");
			ps.add(params.get("erpusername"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return purchaseDao.findList(sqlContext, page, Purchase.class);
	}

	@Override
	public void upload(String name, String storecode, String userid, List<PurchaseDetail> list) throws Exception {
		// TODO Auto-generated method stub
		Purchase entity = new Purchase();
		entity.setName(name);
		entity.setUserid(userid);
		entity.setStorecode(storecode);
		entity.setStatus(0);
		entity.setNum(list.size());
		purchaseDao.save(entity);
		for (PurchaseDetail d : list) {
			d.setPurchaseid(entity.getId());
		}
		Catalog catalog = new Catalog();
		catalog.setUserid(userid);
		catalog.setMyself(true);
		List<Catalog> catalogs = catalogDao.findList(catalog);
		if (catalogs.size() == 0) {
			catalogDao.save(catalog);
		}
		catalog = catalogs.get(0);
		purchaseDetailDao.save(list);

		SqlContext sqlContext = new SqlContext();
		List<Object> ps = new ArrayList<Object>();
		sqlContext.setSql(
				" insert into t_drug (id,code,name,specifications,unit,dosageform,factory,zunzi,catalogid) select id,code,name,specifications,unit,dosageform,factory,zunzi,? from t_purchase_detail where code not in (select code from t_drug where catalogid =?  ) and purchaseid=?");
		ps.add(catalog.getId());
		ps.add(catalog.getId());
		ps.add(entity.getId());
		sqlContext.setParams(ps.toArray());
		purchaseDetailDao.update(sqlContext);

		sqlContext = new SqlContext();
		ps = new ArrayList<Object>();
		sqlContext.setSql("update t_purchase_detail,t_drug set t_purchase_detail.drugid=t_drug.id where t_purchase_detail.code=t_drug.code and t_purchase_detail.purchaseid=? and t_drug.catalogid=?");
		ps.add(entity.getId());
		ps.add(catalog.getId());
		sqlContext.setParams(ps.toArray());
		purchaseDetailDao.update(sqlContext);

	}

	@Override
	public void send(String purchaseid, String userid, String[] supplerids) throws Exception {
		// TODO Auto-generated method stub
		UserSupplier userSupplier = new UserSupplier();
		userSupplier.setUserid(userid);
		List<UserSupplier> list = userSupplierDao.findList(userSupplier);
		for (UserSupplier us : list) {
			for (String id : supplerids) {
				if (id.equals(us.getSupplierid())) {
					SupplierPurchase supplierPurchase = new SupplierPurchase();
					supplierPurchase.setPurchaseid(purchaseid);
					supplierPurchase.setSupplierid(us.getSupplierid());
					if (supplierPurchaseDao.findObject(supplierPurchase) == null) {
						supplierPurchase.setStatus(0);
						supplierPurchase.setSend(0);
						supplierPurchaseDao.save(supplierPurchase);
					}
				}
			}
		}

		Purchase purchase = new Purchase();
		purchase.setId(purchaseid);
		purchase.setSend(1);
		purchaseDao.update(purchase);
	}

	@Override
	public void sendNoitce(String purchaseid, String userid, String[] supplierids) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(userid);
		user = userDao.findObject(user);
		for (String id : supplierids) {
			UserSupplier userSupplier = new UserSupplier();
			userSupplier.setUserid(userid);
			userSupplier.setSupplierid(id);
			userSupplier = userSupplierService.findObject(userSupplier);
			User supplier = new User();
			supplier.setId(userSupplier.getContactid());
			supplier = userDao.findObject(supplier);

			SysWxUser sysWxuser = new SysWxUser();
			sysWxuser.setUserid(supplier.getId());
			sysWxuser = sysWxUserService.findObject(sysWxuser);

			Purchase purchase = new Purchase();
			purchase.setId(purchaseid);
			purchase = purchaseDao.findObject(purchase);

			if (sysWxuser != null) {
				TemplateMsg msg = new TemplateMsg();
				msg.setTemplate_id(wx_template_purchasemsg);
				msg.setTouser(sysWxuser.getOpenid());
				SysWx sysWx = new SysWx();
				sysWx.setId(sysWxuser.getWxid());
				sysWx = sysWxService.findObject(sysWx);
				msg.setUrl(sysWx.getDomain() + "/ws/purchaselistpage.do?purchaseid=" + purchaseid);
				Map<String, Vdata> map = new HashMap<String, Vdata>();
				Vdata firt = new Vdata();
				firt.setValue("你有一个新的询价单!");
				Vdata keyword1 = new Vdata();
				keyword1.setValue(purchase.getNo() + "");
				map.put("keyword1", keyword1);

				Vdata keyword2 = new Vdata();
				keyword2.setValue(user.getCompany());
				map.put("keyword2", keyword2);

				Vdata keyword3 = new Vdata();
				keyword3.setValue(purchase.getNum() + "个品种");
				map.put("keyword3", keyword3);

				msg.setData(map);
				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(sysWxuser.getWxid());
				sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				WxUtil.sendTemplateMsg(msg, sysWxAccesstoken.getAccesstoken());
			} else {
				if (!StringUtils.isEmpty(supplier.getMobile())) {
					NeteaseUtil.sengNotice("3962087", "[\"" + supplier.getMobile() + "\"]", "[\"" + URLEncoder.encode(user.getCompany(), "utf-8") + "\"]");

				}
			}
		}

	}
}
