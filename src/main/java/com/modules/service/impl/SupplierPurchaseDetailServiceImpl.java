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
import com.modules.service.SupplierPurchaseDetailService;
import com.modules.vo.SupplierPurchaseDetailPirceVo;
import com.modules.dao.SupplierPurchaseDetailDao;
import com.modules.pojo.SupplierPurchaseDetail;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierPurchaseDetailServiceImpl implements SupplierPurchaseDetailService {

	@Autowired
	private SupplierPurchaseDetailDao supplierPurchaseDetailDao;

	public void save(SupplierPurchaseDetail entity) throws Exception {
		supplierPurchaseDetailDao.save(entity);
	}

	public void save(List<SupplierPurchaseDetail> entities) throws Exception {
		supplierPurchaseDetailDao.save(entities);
	}

	public void update(SupplierPurchaseDetail entity) throws Exception {
		supplierPurchaseDetailDao.update(entity);
	}

	public void update(SupplierPurchaseDetail newEntity, SupplierPurchaseDetail oldEntity) throws Exception {
		supplierPurchaseDetailDao.update(newEntity, oldEntity);
	}

	public void delete(SupplierPurchaseDetail entity) throws Exception {
		supplierPurchaseDetailDao.delete(entity);
	}

	public SupplierPurchaseDetail findObject(SupplierPurchaseDetail entity) throws Exception {
		return supplierPurchaseDetailDao.findObject(entity);
	}

	public List<SupplierPurchaseDetail> findList(SupplierPurchaseDetail entity) throws Exception {
		return supplierPurchaseDetailDao.findList(entity);
	}

	public PageResult<SupplierPurchaseDetail> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_supplier_purchase_detail where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return supplierPurchaseDetailDao.findList(sqlContext, page, SupplierPurchaseDetail.class);
	}

	@Override
	public List<SupplierPurchaseDetailPirceVo> findprice(String puchasedetailid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select spd.supplierid as supplierid,u.company as supplier,spd.price as price, spd.purchasedetailid as  purchasedetailid from t_supplier_purchase_detail spd left join t_user u on spd.supplierid=u.id  where 1=1 and purchasedetailid=? order by price asc");
		ps.add(puchasedetailid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return supplierPurchaseDetailDao.findList(sqlContext, SupplierPurchaseDetailPirceVo.class);
	}

}
