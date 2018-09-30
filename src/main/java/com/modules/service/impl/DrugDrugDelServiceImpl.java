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
import com.modules.service.DrugDrugDelService;
import com.modules.dao.DrugDrugDelDao;
import com.modules.pojo.DrugDrug;
import com.modules.pojo.DrugDrugDel;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugDrugDelServiceImpl implements DrugDrugDelService {

	@Autowired
	private DrugDrugDelDao drugDrugDelDao;

	public void save(DrugDrugDel entity) throws Exception {
		drugDrugDelDao.save(entity);
	}

	public void save(List<DrugDrugDel> entities) throws Exception {
		drugDrugDelDao.save(entities);
	}

	public void update(DrugDrugDel entity) throws Exception {
		drugDrugDelDao.update(entity);
	}

	public void update(DrugDrugDel newEntity, DrugDrugDel oldEntity) throws Exception {
		drugDrugDelDao.update(newEntity, oldEntity);
	}

	public void delete(DrugDrugDel entity) throws Exception {
		drugDrugDelDao.delete(entity);
	}

	public DrugDrugDel findObject(DrugDrugDel entity) throws Exception {
		return drugDrugDelDao.findObject(entity);
	}

	public List<DrugDrugDel> findList(DrugDrugDel entity) throws Exception {
		return drugDrugDelDao.findList(entity);
	}

	public PageResult<DrugDrugDel> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug_drug_del where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugDrugDelDao.findList(sqlContext, page, DrugDrugDel.class);
	}

	@Override
	public List<DrugDrug> findbycatalogid(String spaceid, String catalogid, Date lastExportTime) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug_drug_del where  spaceid=? and drugid  in(select id from t_drug where catalogid=?) ");
		ps.add(spaceid);
		ps.add(catalogid);
		if (lastExportTime != null) {
			sql.append(" and updatetime > ?");
			ps.add(lastExportTime);
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugDrugDelDao.findList(sqlContext, DrugDrug.class);
	}
}
