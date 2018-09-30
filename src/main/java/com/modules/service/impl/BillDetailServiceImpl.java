package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.dao.BillDetailDao;
import com.modules.dao.CatalogDao;
import com.modules.dao.UserDao;
import com.modules.pojo.BillDetail;
import com.modules.pojo.Catalog;
import com.modules.pojo.User;
import com.modules.service.BillDetailService;
import com.modules.vo.apipur.BillDetailStatusVo;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BillDetailServiceImpl implements BillDetailService {

	@Autowired
	private BillDetailDao billDetailDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CatalogDao catalogDao;

	public void save(BillDetail entity) throws Exception {
		billDetailDao.save(entity);
	}

	public void save(List<BillDetail> entities) throws Exception {
		billDetailDao.save(entities);
	}

	public void update(BillDetail entity) throws Exception {
		billDetailDao.update(entity);
	}

	public void update(BillDetail newEntity, BillDetail oldEntity) throws Exception {
		billDetailDao.update(newEntity, oldEntity);
	}

	public void delete(BillDetail entity) throws Exception {
		billDetailDao.delete(entity);
	}

	public BillDetail findObject(BillDetail entity) throws Exception {
		return billDetailDao.findObject(entity);
	}

	public List<BillDetail> findList(BillDetail entity) throws Exception {
		return billDetailDao.findList(entity);
	}

	public PageResult<BillDetail> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_bill_detail where 1=1  ");

		if (params.containsKey("billid")) {
			sql.append(" and billid=? ");
			ps.add(params.get("billid"));
		}

		if (params.containsKey("name")) {
			sql.append(" and name like ? ");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return billDetailDao.findList(sqlContext, page, BillDetail.class);
	}

	public void updateDrugId(String userid, String billid) throws Exception {
		Catalog catalog = new Catalog();
		catalog.setUserid(userid);
		catalog.setMyself(true);
		List<Catalog> catalogs = catalogDao.findList(catalog);
		catalog = catalogs.get(0);
		SqlContext sqlContext = new SqlContext();
		List<Object> ps = new ArrayList<Object>();
		sqlContext = new SqlContext();
		ps = new ArrayList<Object>();
		sqlContext.setSql("update t_bill_detail,t_drug set t_bill_detail.drugid=t_drug.id where t_bill_detail.code=t_drug.code and t_bill_detail.billid=? and t_drug.catalogid=?");
		ps.add(billid);
		ps.add(catalog.getId());
		sqlContext.setParams(ps.toArray());
		billDetailDao.update(sqlContext);

	}

	@Override
	public User lastDrugCompany(String purchaserid, String drugid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select u.* from t_bill_detail  bd, t_user u ,t_bill b   where  bd.billid=b.id and  b.status=3  and  bd.purchaserid=? and bd.supplierid=u.id and bd.drugid=?   order by bd.createtime desc limit 0,1");
		ps.add(purchaserid);
		ps.add(drugid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userDao.findObject(sqlContext, User.class);
	}

	@Override
	public List<BillDetailStatusVo> querybilldetailstatus(String purchaserid, Date updatetime) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select bd.code,bd.remark,bd.status,b.status billstatus ,b.arrivaltime,b.updatetime from t_bill_detail bd left join t_bill b on bd.billid=b.id  where b.purchaserid=? and b.updatetime>=? and b.status>0");
		ps.add(purchaserid);
		ps.add(updatetime);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userDao.findList(sqlContext, BillDetailStatusVo.class);
	}

	@Override
	public void updateContractStatus(String erpid, String code, String contractno, String contractnum, Date contracttime) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		List<Object> ps = new ArrayList<Object>();
		sqlContext = new SqlContext();
		ps = new ArrayList<Object>();
		sqlContext.setSql("update t_bill_detail  bd ,t_bill b set bd.contractno=?,bd.contractnum=?,bd.contracttime=? where  bd.billid=b.id and b.erpid=? and bd.code=? ");
		ps.add(contractno);
		ps.add(contractnum);
		ps.add(contracttime);
		ps.add(erpid);
		ps.add(code);
		sqlContext.setParams(ps.toArray());
		billDetailDao.update(sqlContext);
	}

}
