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
import com.modules.service.StoreTypeService;
import com.modules.dao.StoreTypeDao;
import com.modules.pojo.StoreType;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreTypeServiceImpl implements StoreTypeService {

	@Autowired
	private StoreTypeDao storeTypeDao;

	public void save(StoreType entity) throws Exception {
		storeTypeDao.save(entity);
	}

	public void save(List<StoreType> entities) throws Exception {
		storeTypeDao.save(entities);
	}

	public void update(StoreType entity) throws Exception {
		storeTypeDao.update(entity);
	}

	public void update(StoreType newEntity, StoreType oldEntity) throws Exception {
		storeTypeDao.update(newEntity, oldEntity);
	}

	public void delete(StoreType entity) throws Exception {
		storeTypeDao.delete(entity);
	}

	public StoreType findObject(StoreType entity) throws Exception {
		return storeTypeDao.findObject(entity);
	}

	public List<StoreType> findList(StoreType entity) throws Exception {
		return storeTypeDao.findList(entity);
	}

	public PageResult<StoreType> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_store_type where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return storeTypeDao.findList(sqlContext, page, StoreType.class);
	}

}
