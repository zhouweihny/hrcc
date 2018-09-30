package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.SupplierPurchaseService;
import com.modules.vo.PurchaseDetailExcelVo;
import com.modules.vo.PurchaseDetailVo;
import com.modules.dao.CatalogDao;
import com.modules.dao.PurchaseDao;
import com.modules.dao.PurchaseDetailDao;
import com.modules.dao.SupplierPurchaseDao;
import com.modules.dao.SupplierPurchaseDetailDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.PlanDetail;
import com.modules.pojo.Purchase;
import com.modules.pojo.PurchaseDetail;
import com.modules.pojo.SupplierPurchase;
import com.modules.pojo.SupplierPurchaseDetail;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierPurchaseServiceImpl implements SupplierPurchaseService {

	@Autowired
	private SupplierPurchaseDao supplierPurchaseDao;

	@Autowired
	private SupplierPurchaseDetailDao supplierPurchaseDetailDao;

	@Autowired
	private PurchaseDao purchaseDao;

	@Autowired
	private PurchaseDetailDao purchaseDetailDao;

	@Autowired
	private CatalogDao catalogDao;

	public void save(SupplierPurchase entity) throws Exception {
		supplierPurchaseDao.save(entity);
	}

	public void save(List<SupplierPurchase> entities) throws Exception {
		supplierPurchaseDao.save(entities);
	}

	public void update(SupplierPurchase entity) throws Exception {
		supplierPurchaseDao.update(entity);
	}

	public void update(SupplierPurchase newEntity, SupplierPurchase oldEntity) throws Exception {
		supplierPurchaseDao.update(newEntity, oldEntity);
	}

	public void delete(SupplierPurchase entity) throws Exception {
		supplierPurchaseDao.delete(entity);
	}

	public SupplierPurchase findObject(SupplierPurchase entity) throws Exception {
		return supplierPurchaseDao.findObject(entity);
	}

	public List<SupplierPurchase> findList(SupplierPurchase entity) throws Exception {
		return supplierPurchaseDao.findList(entity);
	}

	public PageResult<SupplierPurchase> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select sp.* from t_supplier_purchase sp left join t_user u  on  sp.supplierid =u.id  where 1=1 ");
		if (params.containsKey("supplierid")) {
			sql.append(" and supplierid=? ");
			ps.add(params.get("supplierid"));
		}
		if (params.containsKey("contactid")) {
			sql.append(" and purchaseid  in (select id from     t_purchase  where userid in(select userid from t_user_supplier us  where us.contactid= ?))");
			ps.add(params.get("contactid"));
		}
		if (params.containsKey("supplier")) {
			sql.append(" and u.company like ? ");
			ps.add("%" + params.get("supplier") + "%");
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return supplierPurchaseDao.findList(sqlContext, page, SupplierPurchase.class);
	}

	@Override
	public List<SupplierPurchase> findList(String purchaserid, String supplierid, Boolean send) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_supplier_purchase where supplierid=?  and purchaseid in (select id from t_purchase where userid=?) and send=?");
		ps.add(supplierid);
		ps.add(purchaserid);
		ps.add(send);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return supplierPurchaseDao.findList(sqlContext, SupplierPurchase.class);
	}

	public Integer findCount(String purchaseid, String supplierid) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select count(1) from t_supplier_purchase_detail where purchasedetailid in (select id from t_purchase_detail where purchaseid =? ) and supplierid=?");
		ps.add(purchaseid);
		ps.add(supplierid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return supplierPurchaseDao.findE(sqlContext, Integer.class);
	}

	@Override
	public void upload(String supplierid, String purchaseid, List<PurchaseDetailExcelVo> planDetails) throws Exception {
		// TODO Auto-generated method stub
		SupplierPurchase supplierPurchase = new SupplierPurchase();
		supplierPurchase.setSupplierid(supplierid);
		supplierPurchase.setPurchaseid(purchaseid);
		supplierPurchase = supplierPurchaseDao.findObject(supplierPurchase);
		supplierPurchase.setStatus(1);
		supplierPurchaseDao.update(supplierPurchase);
		for (PurchaseDetailExcelVo plan : planDetails) {
			PurchaseDetail pd = new PurchaseDetail();
			pd.setCode(plan.getCode());
			pd.setPurchaseid(purchaseid);
			pd = purchaseDetailDao.findObject(pd);
			if (pd != null && plan.getPrice() != null) {
				if (plan.getPrice().intValue() != 0) {
					SupplierPurchaseDetail supplierPurchaseDetail = new SupplierPurchaseDetail();
					supplierPurchaseDetail.setPurchasedetailid(pd.getId());
					supplierPurchaseDetail.setRemark(plan.getRemark());
					supplierPurchaseDetail.setPrice(plan.getPrice());
					supplierPurchaseDetail.setSupplierid(supplierid);
					supplierPurchaseDetailDao.save(supplierPurchaseDetail);
				}
			}
		}

	}

}
