package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.commons.util.MyStringUtil;
import com.modules.service.DrugTempService;
import com.modules.dao.DrugTempDao;
import com.modules.pojo.DrugTemp;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugTempServiceImpl implements DrugTempService {

	@Autowired
	private DrugTempDao drugTempDao;

	public void save(DrugTemp entity) throws Exception {
		entity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getName())));
		entity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getFactory())));
		drugTempDao.save(entity);
	}

	public void save(List<DrugTemp> entities) throws Exception {
		for (DrugTemp entity : entities) {
			entity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getName())));
			entity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getFactory())));
		}
		drugTempDao.save(entities);
	}

	public void update(DrugTemp entity) throws Exception {
		if (StringUtils.isNotBlank(entity.getFactory()))
			entity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getFactory())));
		if (StringUtils.isNotBlank(entity.getName()))
			entity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getName())));
		drugTempDao.update(entity);
	}

	public void update(DrugTemp newEntity, DrugTemp oldEntity) throws Exception {
		if (StringUtils.isNotBlank(newEntity.getFactory()))
			newEntity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(newEntity.getFactory())));
		if (StringUtils.isNotBlank(newEntity.getName()))
			newEntity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(newEntity.getName())));
		drugTempDao.update(newEntity, oldEntity);
	}

	public void delete(DrugTemp entity) throws Exception {
		drugTempDao.delete(entity);
	}

	public DrugTemp findObject(DrugTemp entity) throws Exception {
		return drugTempDao.findObject(entity);
	}

	public List<DrugTemp> findList(DrugTemp entity) throws Exception {
		return drugTempDao.findList(entity);
	}

	public PageResult<DrugTemp> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug_temp where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugTempDao.findList(sqlContext, page, DrugTemp.class);
	}

}
