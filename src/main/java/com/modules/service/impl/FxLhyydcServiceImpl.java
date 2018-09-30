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
import com.modules.service.FxLhyydcService;
import com.modules.dao.FxLhyydcDao;
import com.modules.pojo.FxLhyydc;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxLhyydcServiceImpl implements FxLhyydcService {

	@Autowired
	private FxLhyydcDao fxLhyydcDao;

	public void save(FxLhyydc entity) throws Exception {
		fxLhyydcDao.save(entity);
	}

	public void save(List<FxLhyydc> entities) throws Exception {
		fxLhyydcDao.save(entities);
	}

	public void update(FxLhyydc entity) throws Exception {
		fxLhyydcDao.update(entity);
	}

	public void update(FxLhyydc newEntity, FxLhyydc oldEntity) throws Exception {
		fxLhyydcDao.update(newEntity, oldEntity);
	}

	public void delete(FxLhyydc entity) throws Exception {
		fxLhyydcDao.delete(entity);
	}

	public FxLhyydc findObject(FxLhyydc entity) throws Exception {
		return fxLhyydcDao.findObject(entity);
	}

	public List<FxLhyydc> findList(FxLhyydc entity) throws Exception {
		return fxLhyydcDao.findList(entity);
	}

	public PageResult<FxLhyydc> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_lhyydc where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxLhyydcDao.findList(sqlContext, page, FxLhyydc.class);
	}

}
