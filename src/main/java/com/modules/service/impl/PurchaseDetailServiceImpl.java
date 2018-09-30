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
import com.modules.service.PurchaseDetailService;
import com.modules.vo.PurchaseDetailVo;
import com.modules.dao.PurchaseDetailDao;
import com.modules.pojo.PurchaseDetail;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

	@Autowired
	private PurchaseDetailDao purchaseDetailDao;

	public void save(PurchaseDetail entity) throws Exception {
		purchaseDetailDao.save(entity);
	}

	public void save(List<PurchaseDetail> entities) throws Exception {
		purchaseDetailDao.save(entities);
	}

	public void update(PurchaseDetail entity) throws Exception {
		purchaseDetailDao.update(entity);
	}

	public void update(PurchaseDetail newEntity, PurchaseDetail oldEntity) throws Exception {
		purchaseDetailDao.update(newEntity, oldEntity);
	}

	public void delete(PurchaseDetail entity) throws Exception {
		purchaseDetailDao.delete(entity);
	}

	public PurchaseDetail findObject(PurchaseDetail entity) throws Exception {
		return purchaseDetailDao.findObject(entity);
	}

	public List<PurchaseDetail> findList(PurchaseDetail entity) throws Exception {
		return purchaseDetailDao.findList(entity);
	}

	public PageResult<PurchaseDetail> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_purchase_detail where 1=1 ");
		if (params.containsKey("purchaseid")) {
			sql.append(" and purchaseid = ?");
			ps.add(params.get("purchaseid"));
		}
		if (params.containsKey("name")) {
			sql.append(" and name like ?");
			ps.add("%" + params.get("name") + "%");
		}
		if (params.containsKey("code")) {
			sql.append(" and code like ?");
			ps.add("%" + params.get("code") + "%");
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return purchaseDetailDao.findList(sqlContext, page, PurchaseDetail.class);
	}

	public PageResult<PurchaseDetailVo> findPurchaseDetailVoList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select pd.*,(select count(1) from  t_supplier_purchase_detail where purchasedetailid=pd.id) as bjnum from t_purchase_detail pd where 1=1 ");
		if (params.containsKey("purchaseid")) {
			sql.append(" and purchaseid = ?");
			ps.add(params.get("purchaseid"));
		}
		if (params.containsKey("name")) {
			sql.append(" and name like ?");
			ps.add("%" + params.get("name") + "%");
		}
		if (params.containsKey("code")) {
			sql.append(" and code like ?");
			ps.add("%" + params.get("code") + "%");
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return purchaseDetailDao.findList(sqlContext, page, PurchaseDetailVo.class);
	}

}
