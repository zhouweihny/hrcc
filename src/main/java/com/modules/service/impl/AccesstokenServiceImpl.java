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
import com.modules.service.AccesstokenService;
import com.modules.dao.AccesstokenDao;
import com.modules.pojo.Accesstoken;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccesstokenServiceImpl implements AccesstokenService {

	@Autowired
	private AccesstokenDao accesstokenDao;

	public void save(Accesstoken entity) throws Exception {
		accesstokenDao.save(entity);
	}

	public void save(List<Accesstoken> entities) throws Exception {
		accesstokenDao.save(entities);
	}

	public void update(Accesstoken entity) throws Exception {
		accesstokenDao.update(entity);
	}

	public void update(Accesstoken newEntity, Accesstoken oldEntity) throws Exception {
		accesstokenDao.update(newEntity, oldEntity);
	}

	public void delete(Accesstoken entity) throws Exception {
		accesstokenDao.delete(entity);
	}

	public Accesstoken findObject(Accesstoken entity) throws Exception {
		return accesstokenDao.findObject(entity);
	}

	public List<Accesstoken> findList(Accesstoken entity) throws Exception {
		return accesstokenDao.findList(entity);
	}

	public PageResult<Accesstoken> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_accesstoken where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return accesstokenDao.findList(sqlContext, page, Accesstoken.class);
	}

	@Override
	public void clear() throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("delete from t_accesstoken where expirytime<now()");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		accesstokenDao.update(sqlContext);
	}

}
