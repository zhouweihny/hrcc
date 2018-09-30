package com.modules.service.impl;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.DrugSupplierService;
import com.modules.service.PlanService;
import com.modules.service.StoreService;
import com.modules.vo.DrugSupplierVo;

import net.sf.jsqlparser.expression.StringValue;

import com.modules.dao.BillDao;
import com.modules.dao.BillDetailDao;
import com.modules.dao.CatalogDao;
import com.modules.dao.PlanDao;
import com.modules.dao.PlanDetailDao;
import com.modules.dao.PurchaseDao;
import com.modules.dao.PurchaseDetailDao;
import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;
import com.modules.pojo.Catalog;
import com.modules.pojo.Plan;
import com.modules.pojo.PlanDetail;
import com.modules.pojo.Purchase;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.Store;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanDao planDao;

	@Autowired
	private PlanDetailDao planDetailDao;

	@Autowired
	private CatalogDao catalogDao;

	@Autowired
	private BillDao billDao;

	@Autowired
	private BillDetailDao billDetailDao;

	@Autowired
	private DrugSupplierService drugSupplierService;

	@Autowired
	private PurchaseDao purchaseDao;

	@Autowired
	private PurchaseDetailDao purchaseDetailDao;

	@Autowired
	private StoreService storeService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public void save(Plan entity) throws Exception {
		planDao.save(entity);
	}

	public void save(List<Plan> entities) throws Exception {
		planDao.save(entities);
	}

	public void update(Plan entity) throws Exception {
		planDao.update(entity);
	}

	public void update(Plan newEntity, Plan oldEntity) throws Exception {
		planDao.update(newEntity, oldEntity);
	}

	public void delete(Plan entity) throws Exception {
		planDao.delete(entity);
	}

	public Plan findObject(Plan entity) throws Exception {
		return planDao.findObject(entity);
	}

	public List<Plan> findList(Plan entity) throws Exception {
		return planDao.findList(entity);
	}

	public PageResult<Plan> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_plan where 1=1 ");

		if (params.containsKey("userid")) {
			sql.append(" and userid=?");
			ps.add(params.get("userid"));
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
		return planDao.findList(sqlContext, page, Plan.class);
	}

	@Override
	public void upload(String name, String storecode, String userid, List<PlanDetail> list) throws Exception {

		Store store = new Store();
		store.setStorecode(storecode);
		store.setUserid(userid);
		store = storeService.findObject(store);
		// TODO Auto-generated method stub
		DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String date = format1.format(new Date());
		name = store.getName() + date + "_" + name;
		Plan plan = new Plan();
		plan.setName(name);
		plan.setUserid(userid);
		plan.setStorecode(storecode);
		plan.setStatus(0);
		plan.setNum(list.size());
		planDao.save(plan);
		for (PlanDetail d : list) {
			d.setPlanid(plan.getId());
		}
		Catalog catalog = new Catalog();
		catalog.setUserid(userid);
		catalog.setMyself(true);
		List<Catalog> catalogs = catalogDao.findList(catalog);
		if (catalogs.size() == 0) {
			catalogDao.save(catalog);
		}
		catalog = catalogs.get(0);
		planDetailDao.save(list);

		SqlContext sqlContext = new SqlContext();
		List<Object> ps = new ArrayList<Object>();
		sqlContext.setSql(
				" insert into t_drug (id,code,name,specifications,unit,dosageform,factory,zunzi,catalogid) select id,code,name,specifications,unit,dosageform,factory,zunzi,? from t_plan_detail where code not in (select code from t_drug where catalogid =?  ) and planid=?");
		ps.add(catalog.getId());
		ps.add(catalog.getId());
		ps.add(plan.getId());
		sqlContext.setParams(ps.toArray());
		planDao.update(sqlContext);

		sqlContext = new SqlContext();
		ps = new ArrayList<Object>();
		sqlContext.setSql("update t_plan_detail,t_drug set t_plan_detail.drugid=t_drug.id where t_plan_detail.code=t_drug.code and t_plan_detail.planid=? and t_drug.catalogid=?");
		ps.add(plan.getId());
		ps.add(catalog.getId());
		sqlContext.setParams(ps.toArray());
		planDao.update(sqlContext);

	}

	public void create(String planid) throws Exception {

		Plan plan = new Plan();
		plan.setId(planid);
		plan = planDao.findObject(plan);
		String userid = plan.getUserid();
		String name = plan.getName();
		String storecode = plan.getStorecode();
		PlanDetail planDetail = new PlanDetail();
		planDetail.setPlanid(planid);
		List<PlanDetail> list = planDetailDao.findList(planDetail);

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("purchaseid", userid);
		PageResult<DrugSupplierVo> data = drugSupplierService.findDrugSupplierVoList(params, new Page(false));
		List<PurchaseDetail> purchaseDetails = new ArrayList<PurchaseDetail>();
		Map<String, List<BillDetail>> billmap = new HashMap<String, List<BillDetail>>();
		loop: for (PlanDetail pd : list) {
			for (DrugSupplierVo ds : data.getData()) {
				if (pd.getCode().equals(ds.getCode())) {
					if (StringUtils.isNotBlank(ds.getAgentid()) || StringUtils.isNotBlank(ds.getSupplierid())) {
						String agentid = " ";
						String supplierid = " ";
						String taxrate = " ";
						if (StringUtils.isNotBlank(ds.getAgentid()))
							agentid = ds.getAgentid();

						if (StringUtils.isNotBlank(ds.getSupplierid()))
							supplierid = ds.getSupplierid();
						if (ds.getTaxrate() != null)
							taxrate = ds.getTaxrate() + "";

						if (!billmap.containsKey(agentid + "_" + supplierid + "_" + taxrate)) {
							billmap.put(agentid + "_" + supplierid + "_" + taxrate, new ArrayList<BillDetail>());
						}
						billmap.get(agentid + "_" + supplierid + "_" + taxrate).add(dozerBeanMapper.map(pd, BillDetail.class));
						continue loop;
					}
				}
			}
			PurchaseDetail purchaseDetail = dozerBeanMapper.map(pd, PurchaseDetail.class);
			purchaseDetails.add(purchaseDetail);
		}
		Iterator<Entry<String, List<BillDetail>>> iter = billmap.entrySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			i++;
			Entry<String, List<BillDetail>> entry = iter.next();
			String key = entry.getKey();
			String ids[] = key.split("_");
			List<BillDetail> billDetails = entry.getValue();
			Bill bill = new Bill();
			bill.setPlanid(plan.getId());
			bill.setPurchaserid(userid);
			bill.setName(name + "_" + i);
			if (StringUtils.isNotBlank(ids[0]) )
				bill.setAgentid(ids[0]);
			if (StringUtils.isNotBlank(ids[1]))
				bill.setSupplierid(ids[1]);
			bill.setStatus(-1);
			bill.setStorecode(storecode);
			bill.setNum(billDetails.size());
			billDao.save(bill);
			for (BillDetail bd : billDetails) {
				bd.setBillid(bill.getId());
				bd.setStatus(0); 
			}
			billDetailDao.save(billDetails);
		}
		if (purchaseDetails.size() != 0) {
			Purchase purchase = new Purchase();
			purchase.setPlanid(plan.getId());
			purchase.setName(name + "_询价单");
			purchase.setStatus(0);
			purchase.setSend(0);
			purchase.setUserid(userid);
			purchase.setStorecode(storecode);
			purchase.setNum(purchaseDetails.size());
			purchaseDao.save(purchase);
			for (PurchaseDetail pd : purchaseDetails) {
				pd.setPurchaseid(purchase.getId());
			}
			purchaseDetailDao.save(purchaseDetails);
			plan.setPurchasenum(1);
		}
		plan.setBillnum(i);
		planDao.update(plan);

	}

	@Override
	public void upload(Plan plan, List<PlanDetail> plandetails) throws Exception {
		// TODO Auto-generated method stub
		planDao.save(plan);
		for (PlanDetail d : plandetails) {
			d.setPlanid(plan.getId());
		}
		planDetailDao.save(plandetails);
	}

}
