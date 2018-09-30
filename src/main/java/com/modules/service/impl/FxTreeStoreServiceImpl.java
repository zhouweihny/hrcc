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
import com.modules.service.FxTreeStoreService;
import com.modules.dao.FxTreeStoreDao;
import com.modules.pojo.FxTreeStore;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxTreeStoreServiceImpl implements FxTreeStoreService {

	@Autowired
	private FxTreeStoreDao fxTreeStoreDao;

	public void save(FxTreeStore entity) throws Exception {
		fxTreeStoreDao.save(entity);
	}

	public void save(List<FxTreeStore> entities) throws Exception {
		fxTreeStoreDao.save(entities);
	}

	public void update(FxTreeStore entity) throws Exception {
		fxTreeStoreDao.update(entity);
	}

	public void update(FxTreeStore newEntity, FxTreeStore oldEntity) throws Exception {
		fxTreeStoreDao.update(newEntity, oldEntity);
	}

	public void delete(FxTreeStore entity) throws Exception {
		fxTreeStoreDao.delete(entity);
	}

	public FxTreeStore findObject(FxTreeStore entity) throws Exception {
		return fxTreeStoreDao.findObject(entity);
	}

	public List<FxTreeStore> findList(FxTreeStore entity) throws Exception {
		return fxTreeStoreDao.findList(entity);
	}

	public PageResult<FxTreeStore> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree_store where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeStoreDao.findList(sqlContext, page, FxTreeStore.class);
	}

}
