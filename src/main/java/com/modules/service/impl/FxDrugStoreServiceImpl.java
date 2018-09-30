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
import com.modules.service.FxDrugStoreService;
import com.modules.dao.FxDrugStoreDao;
import com.modules.pojo.FxDrugStore;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxDrugStoreServiceImpl implements FxDrugStoreService {

	@Autowired
	private FxDrugStoreDao fxDrugStoreDao;

	public void save(FxDrugStore entity) throws Exception {
		fxDrugStoreDao.save(entity);
	}

	public void save(List<FxDrugStore> entities) throws Exception {
		fxDrugStoreDao.save(entities);
	}

	public void update(FxDrugStore entity) throws Exception {
		fxDrugStoreDao.update(entity);
	}

	public void update(FxDrugStore newEntity, FxDrugStore oldEntity) throws Exception {
		fxDrugStoreDao.update(newEntity, oldEntity);
	}

	public void delete(FxDrugStore entity) throws Exception {
		fxDrugStoreDao.delete(entity);
	}

	public FxDrugStore findObject(FxDrugStore entity) throws Exception {
		return fxDrugStoreDao.findObject(entity);
	}

	public List<FxDrugStore> findList(FxDrugStore entity) throws Exception {
		return fxDrugStoreDao.findList(entity);
	}

	public PageResult<FxDrugStore> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_drug_store where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxDrugStoreDao.findList(sqlContext, page, FxDrugStore.class);
	}

}
