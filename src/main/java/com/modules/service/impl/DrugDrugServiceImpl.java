package com.modules.service.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.DrugDrugService;
import com.modules.dao.DrugDrugDao;
import com.modules.pojo.DrugDrug;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugDrugServiceImpl implements DrugDrugService {

	@Autowired
	private DrugDrugDao drugDrugDao;

	public void save(DrugDrug entity) throws Exception {
		drugDrugDao.save(entity);
	}

	public void save(List<DrugDrug> entities) throws Exception {
		drugDrugDao.save(entities);
	}

	public void update(DrugDrug entity) throws Exception {
		drugDrugDao.update(entity);
	}

	public void update(DrugDrug newEntity, DrugDrug oldEntity) throws Exception {
		drugDrugDao.update(newEntity, oldEntity);
	}

	public void delete(DrugDrug entity) throws Exception {
		drugDrugDao.delete(entity);
	}

	public DrugDrug findObject(DrugDrug entity) throws Exception {
		return drugDrugDao.findObject(entity);
	}

	public List<DrugDrug> findList(DrugDrug entity) throws Exception {
		return drugDrugDao.findList(entity);
	}

	public PageResult<DrugDrug> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug_drug where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugDrugDao.findList(sqlContext, page, DrugDrug.class);
	}

	@Override
	public List<DrugDrug> findcheckedbycatalogid(String spaceid, String catalogid, Date lastExportTime) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug_drug where  spaceid=? and drugid  in(select id from t_drug where catalogid=?) and checked=true  ");
		ps.add(spaceid);
		ps.add(catalogid);

		if (lastExportTime != null) {
			sql.append(" and updatetime > ?");
			ps.add(lastExportTime);
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugDrugDao.findList(sqlContext, DrugDrug.class);
	}
}
