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
import com.modules.service.SchemeService;
import com.modules.dao.SchemeDao;
import com.modules.pojo.Scheme;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	private SchemeDao schemeDao;

	public void save(Scheme entity) throws Exception {
		schemeDao.save(entity);
	}

	public void save(List<Scheme> entities) throws Exception {
		schemeDao.save(entities);
	}

	public void update(Scheme entity) throws Exception {
		schemeDao.update(entity);
	}

	public void update(Scheme newEntity, Scheme oldEntity) throws Exception {
		schemeDao.update(newEntity, oldEntity);
	}

	public void delete(Scheme entity) throws Exception {
		schemeDao.delete(entity);
	}

	public Scheme findObject(Scheme entity) throws Exception {
		return schemeDao.findObject(entity);
	}

	public List<Scheme> findList(Scheme entity) throws Exception {
		return schemeDao.findList(entity);
	}

	public PageResult<Scheme> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_scheme where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return schemeDao.findList(sqlContext, page, Scheme.class);
	}

}
