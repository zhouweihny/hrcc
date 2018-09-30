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
import com.modules.service.PxsService;
import com.modules.dao.PxsDao;
import com.modules.pojo.Pxs;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PxsServiceImpl implements PxsService {

	@Autowired
	private PxsDao pxsDao;

	public void save(Pxs entity) throws Exception {
		pxsDao.save(entity);
	}

	public void save(List<Pxs> entities) throws Exception {
		pxsDao.save(entities);
	}

	public void update(Pxs entity) throws Exception {
		pxsDao.update(entity);
	}

	public void update(Pxs newEntity, Pxs oldEntity) throws Exception {
		pxsDao.update(newEntity, oldEntity);
	}

	public void delete(Pxs entity) throws Exception {
		pxsDao.delete(entity);
	}

	public Pxs findObject(Pxs entity) throws Exception {
		return pxsDao.findObject(entity);
	}

	public List<Pxs> findList(Pxs entity) throws Exception {
		return pxsDao.findList(entity);
	}

	public PageResult<Pxs> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_pxs where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return pxsDao.findList(sqlContext, page, Pxs.class);
	}

}
