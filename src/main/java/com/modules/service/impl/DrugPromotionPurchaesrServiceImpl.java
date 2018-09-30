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
import com.modules.service.DrugPromotionPurchaesrService;
import com.modules.dao.DrugPromotionPurchaesrDao;
import com.modules.pojo.DrugPromotionPurchaesr;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugPromotionPurchaesrServiceImpl implements DrugPromotionPurchaesrService {

	@Autowired
	private DrugPromotionPurchaesrDao drugPromotionPurchaesrDao;

	public void save(DrugPromotionPurchaesr entity) throws Exception {
		drugPromotionPurchaesrDao.save(entity);
	}

	public void save(List<DrugPromotionPurchaesr> entities) throws Exception {
		drugPromotionPurchaesrDao.save(entities);
	}

	public void update(DrugPromotionPurchaesr entity) throws Exception {
		drugPromotionPurchaesrDao.update(entity);
	}

	public void update(DrugPromotionPurchaesr newEntity, DrugPromotionPurchaesr oldEntity) throws Exception {
		drugPromotionPurchaesrDao.update(newEntity, oldEntity);
	}

	public void delete(DrugPromotionPurchaesr entity) throws Exception {
		drugPromotionPurchaesrDao.delete(entity);
	}

	public DrugPromotionPurchaesr findObject(DrugPromotionPurchaesr entity) throws Exception {
		return drugPromotionPurchaesrDao.findObject(entity);
	}

	public List<DrugPromotionPurchaesr> findList(DrugPromotionPurchaesr entity) throws Exception {
		return drugPromotionPurchaesrDao.findList(entity);
	}

	public PageResult<DrugPromotionPurchaesr> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug_promotion_purchaesr where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugPromotionPurchaesrDao.findList(sqlContext, page, DrugPromotionPurchaesr.class);
	}

}
