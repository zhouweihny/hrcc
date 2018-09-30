package com.modules.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.UserRoleService;
import com.modules.service.UserService;
import com.modules.dao.UserDao;
import com.modules.dao.UserRoleDao;
import com.modules.pojo.SysRole;
import com.modules.pojo.User;
import com.modules.pojo.UserRole;

/**
 * 
 * @author Du.Jun
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	public void save(User entity) throws Exception {
		if (StringUtils.isNotBlank(entity.getMobile())) {
			User tuser = new User();
			tuser.setMobile(entity.getMobile());
			tuser = userDao.findObject(tuser);
			if (tuser != null) {
				throw new Exception("手机号已被使用!");
			}
			userDao.save(entity);
			if (entity.getRoleids() != null) {
				for (String roleid : entity.getRoleids()) {
					UserRole userRole = new UserRole();
					userRole.setRoleid(roleid);
					userRole.setUserid(entity.getId());
					userRoleDao.save(userRole);
				}
			}
		} else {
			userDao.save(entity);
			if (entity.getRoleids() != null) {
				for (String roleid : entity.getRoleids()) {
					UserRole userRole = new UserRole();
					userRole.setRoleid(roleid);
					userRole.setUserid(entity.getId());
					userRoleDao.save(userRole);
				}
			}

		}

	}

	public void save(List<User> entities) throws Exception {
		userDao.save(entities);
	}

	public void update(User entity) throws Exception {
		if (StringUtils.isNotBlank(entity.getMobile())) {
			User tuser = new User();
			tuser.setMobile(entity.getMobile());
			tuser = userDao.findObject(tuser);
			if (tuser != null && !tuser.getId().equals(entity.getId())) {
				throw new Exception("手机号已被使用!");
			}
		}
		userDao.update(entity);
	}

	public void update(User newEntity, User oldEntity) throws Exception {
		userDao.update(newEntity, oldEntity);
	}

	public void delete(User entity) throws Exception {
		User tuser = userDao.findObject(entity);
		userDao.delete(entity);
		UserRole userRole = new UserRole();
		userRole.setUserid(tuser.getId());
		userRoleDao.delete(userRole);
	}

	public User findObject(User entity) throws Exception {
		User user = userDao.findObject(entity);
		if (user != null) {
			UserRole t = new UserRole();
			t.setUserid(user.getId());
			List<UserRole> userRoles = userRoleDao.findList(t);
			List<String> roleids = new ArrayList<String>();
			for (UserRole userRole : userRoles) {
				roleids.add(userRole.getRoleid());
			}
			user.setRoleids(roleids);
		}
		return user;
	}

	public List<User> findList(User entity) throws Exception {
		return userDao.findList(entity);
	}

	public PageResult<User> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		List<Object> plist = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_user where 1=1 ");
		if (params.containsKey("name")) {
			plist.add("%" + params.get("name") + "%");
			sql.append(" and name like ? ");
		}
		if (params.containsKey("mobile")) {
			plist.add("%" + params.get("mobile") + "%");
			sql.append(" and mobile like ? ");
		}

		if (params.containsKey("username")) {
			plist.add("%" + params.get("username") + "%");
			sql.append(" and username like ? ");
		}
		if (params.containsKey("roleid")) {
			plist.add(params.get("roleid"));
			sql.append(" and id in (select userid from t_user_role where roleid = ? )");
		}

		if (params.containsKey("parentid")) {
			plist.add(params.get("parentid"));
			sql.append(" and parentid = ? ");
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(plist.toArray());
		return userDao.findList(sqlContext, page, User.class);
	}

}
