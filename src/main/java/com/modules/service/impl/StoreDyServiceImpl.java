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
import com.modules.service.StoreDyService;
import com.modules.dao.StoreDyDao;
import com.modules.pojo.StoreDy;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreDyServiceImpl implements StoreDyService {

	@Autowired
	private StoreDyDao storeDyDao;

	public void save(StoreDy entity) throws Exception {
		storeDyDao.save(entity);
	}

	public void save(List<StoreDy> entities) throws Exception {
		storeDyDao.save(entities);
	}

	public void update(StoreDy entity) throws Exception {
		storeDyDao.update(entity);
	}

	public void update(StoreDy newEntity, StoreDy oldEntity) throws Exception {
		storeDyDao.update(newEntity, oldEntity);
	}

	public void delete(StoreDy entity) throws Exception {
		storeDyDao.delete(entity);
	}

	public StoreDy findObject(StoreDy entity) throws Exception {
		return storeDyDao.findObject(entity);
	}

	public List<StoreDy> findList(StoreDy entity) throws Exception {
		return storeDyDao.findList(entity);
	}

	public PageResult<StoreDy> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_store_dy where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return storeDyDao.findList(sqlContext, page, StoreDy.class);
	}

}
