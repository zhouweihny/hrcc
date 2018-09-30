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
import com.modules.service.SpaceCatalogService;
import com.modules.dao.SpaceCatalogDao;
import com.modules.pojo.SpaceCatalog;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpaceCatalogServiceImpl implements SpaceCatalogService {

	@Autowired
	private SpaceCatalogDao spaceCatalogDao;

	public void save(SpaceCatalog entity) throws Exception {
		spaceCatalogDao.save(entity);
	}

	public void save(List<SpaceCatalog> entities) throws Exception {
		spaceCatalogDao.save(entities);
	}

	public void update(SpaceCatalog entity) throws Exception {
		spaceCatalogDao.update(entity);
	}

	public void update(SpaceCatalog newEntity, SpaceCatalog oldEntity) throws Exception {
		spaceCatalogDao.update(newEntity, oldEntity);
	}

	public void delete(SpaceCatalog entity) throws Exception {
		spaceCatalogDao.delete(entity);
	}

	public SpaceCatalog findObject(SpaceCatalog entity) throws Exception {
		return spaceCatalogDao.findObject(entity);
	}

	public List<SpaceCatalog> findList(SpaceCatalog entity) throws Exception {
		return spaceCatalogDao.findList(entity);
	}

	public PageResult<SpaceCatalog> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_space_catalog where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return spaceCatalogDao.findList(sqlContext, page, SpaceCatalog.class);
	}

}
