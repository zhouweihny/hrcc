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
import com.modules.service.UserStoreService;
import com.modules.dao.UserStoreDao;
import com.modules.pojo.UserStore;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserStoreServiceImpl implements UserStoreService {

	@Autowired
	private UserStoreDao userStoreDao;

	public void save(UserStore entity) throws Exception {
		userStoreDao.save(entity);
	}

	public void save(List<UserStore> entities) throws Exception {
		userStoreDao.save(entities);
	}

	public void update(UserStore entity) throws Exception {
		userStoreDao.update(entity);
	}

	public void update(UserStore newEntity, UserStore oldEntity) throws Exception {
		userStoreDao.update(newEntity, oldEntity);
	}

	public void delete(UserStore entity) throws Exception {
		userStoreDao.delete(entity);
	}

	public UserStore findObject(UserStore entity) throws Exception {
		return userStoreDao.findObject(entity);
	}

	public List<UserStore> findList(UserStore entity) throws Exception {
		return userStoreDao.findList(entity);
	}

	public PageResult<UserStore> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_user_store where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userStoreDao.findList(sqlContext, page, UserStore.class);
	}

}
