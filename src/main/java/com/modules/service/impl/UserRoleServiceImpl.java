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
import com.modules.service.UserRoleService;
import com.modules.dao.UserRoleDao;
import com.modules.pojo.UserRole;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	public void save(UserRole entity) throws Exception {
		userRoleDao.save(entity);
	}

	public void save(List<UserRole> entities) throws Exception {
		userRoleDao.save(entities);
	}

	public void update(UserRole entity) throws Exception {
		userRoleDao.update(entity);
	}

	public void update(UserRole newEntity, UserRole oldEntity) throws Exception {
		userRoleDao.update(newEntity, oldEntity);
	}

	public void delete(UserRole entity) throws Exception {
		userRoleDao.delete(entity);
	}

	public UserRole findObject(UserRole entity) throws Exception {
		return userRoleDao.findObject(entity);
	}

	public List<UserRole> findList(UserRole entity) throws Exception {
		return userRoleDao.findList(entity);
	}

	public PageResult<UserRole> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_user_role where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userRoleDao.findList(sqlContext, page, UserRole.class);
	}

}
