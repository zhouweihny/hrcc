package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.UserSupplierService;
import com.modules.dao.UserDao;
import com.modules.dao.UserSupplierDao;
import com.modules.pojo.User;
import com.modules.pojo.UserSupplier;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSupplierServiceImpl implements UserSupplierService {

	@Autowired
	private UserSupplierDao userSupplierDao;

	@Autowired
	private UserDao userDao;

	public void save(UserSupplier entity) throws Exception {

		UserSupplier us = new UserSupplier();
		us.setCode(entity.getCode());
		us.setUserid(entity.getUserid());
		us = userSupplierDao.findObject(us);
		if (us == null) {
			// 当手机号和供应商都存在时 系统查询 供应商的手机号是否存在 如果不存在则新增
			if (!StringUtils.isEmpty(entity.getMobile()) && !StringUtils.isEmpty(entity.getSupplierid())) {
				User user = new User();
				user.setMobile(entity.getMobile());
				user = userDao.findObject(user);
				if (user != null) {
					if (user.getParentid().equals(entity.getSupplierid()) || user.getId().equals(entity.getSupplierid())) {
						entity.setContactid(user.getId());
					} else {
						throw new Exception("手机号已被其他供应商占用!");
					}
				} else {
					user = new User();
					user.setMobile(entity.getMobile());
					user.setParentid(entity.getSupplierid());
					user.setUsername(entity.getMobile());
					List<String> roleids = new ArrayList<String>();
					roleids.add("F717B4EEF241460891F0E1D3AB84F74E");
					user.setRoleids(roleids);
					// user.setRoleid("F717B4EEF241460891F0E1D3AB84F74E");
				}
			}
			userSupplierDao.save(entity);
		} else {
			throw new Exception("供应商编码已存在!");
		}
	}

	public void save(List<UserSupplier> entities) throws Exception {
		userSupplierDao.save(entities);
	}

	public void update(UserSupplier entity) throws Exception {

		UserSupplier t = new UserSupplier();
		t.setId(entity.getId());
		t = userSupplierDao.findObject(t);
		if (t != null) {
			// 手机号存在 与平台供应商id都不为空时
			if (!StringUtils.isEmpty(entity.getMobile()) && !StringUtils.isEmpty(entity.getSupplierid())) {
				User user = new User();
				user.setMobile(entity.getMobile());
				user = userDao.findObject(user);
				if (user != null) {
					if (user.getParentid().equals(entity.getSupplierid()) || user.getId().equals(entity.getSupplierid())) {
						entity.setContactid(user.getId());
					} else {
						throw new Exception("手机号已被其他供应商占用!");
					}
				} else {
					user = new User();
					user.setMobile(entity.getMobile());
					user.setParentid(entity.getSupplierid());
					user.setUsername(entity.getMobile());
					// user.setRoleid("F717B4EEF241460891F0E1D3AB84F74E");

					List<String> roleids = new ArrayList<String>();
					roleids.add("F717B4EEF241460891F0E1D3AB84F74E");
					user.setRoleids(roleids);
					userDao.save(user);
				}
			}
		}

		userSupplierDao.update(entity);
	}

	public void update(UserSupplier newEntity, UserSupplier oldEntity) throws Exception {
		userSupplierDao.update(newEntity, oldEntity);
	}

	public void delete(UserSupplier entity) throws Exception {
		userSupplierDao.delete(entity);
	}

	public UserSupplier findObject(UserSupplier entity) throws Exception {
		return userSupplierDao.findObject(entity);
	}

	public List<UserSupplier> findList(UserSupplier entity) throws Exception {
		return userSupplierDao.findList(entity);
	}

	public PageResult<UserSupplier> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_user_supplier where 1=1 ");

		if (params.containsKey("userid")) {
			ps.add(params.get("userid"));
			sql.append(" and userid=?");
		}

		if (params.containsKey("keyword")) {
			ps.add("%" + params.get("keyword") + "%");
			ps.add("%" + params.get("keyword") + "%");
			ps.add("%" + params.get("keyword") + "%");
			sql.append(" and (name  like ? or code like ? or mobile like ?)");
		}

		if (params.containsKey("used")) {
			if (params.get("used").equals("1")) {
				sql.append(" and used =true ");
			} else if (params.get("used").equals("0")) {
				sql.append(" and used=false ");
			}
		}

		if (params.containsKey("status")) {
			if (params.get("status").equals("1")) {
				sql.append(" and supplierid is not null ");
			} else if (params.get("status").equals("0")) {
				sql.append(" and (supplierid is null or supplierid  ) ");
			}
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userSupplierDao.findList(sqlContext, page, UserSupplier.class);
	}

	@Override
	public List<User> findPurchaserList(String supplierid, Boolean send) throws Exception {
		SqlContext sqlContext = new SqlContext();
		List<Object> plist = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select  u.id  ,  u.company  from  t_user_supplier us left join t_user u    on us.userid=u.id   where 1=1  and us.supplierid = ? and send =?");
		plist.add(supplierid);
		plist.add(send);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(plist.toArray());
		return userSupplierDao.findList(sqlContext, User.class);
	}

	public void addMobileno(String id, String mobileno, String supplierid, String userid) throws Exception {
		UserSupplier userSupplier = new UserSupplier();
		if (StringUtils.isEmpty(id)) {
			userSupplier = new UserSupplier();
			userSupplier.setUsed(false);
			userSupplier.setUserid(userid);
			userSupplier.setSupplierid(supplierid);
			userSupplierDao.save(userSupplier);
		} else {
			userSupplier = new UserSupplier();
			userSupplier.setId(id);
			userSupplier = userSupplierDao.findObject(userSupplier);
		}
		if (userSupplier != null) {

			if (!StringUtils.isEmpty(mobileno)) {
				User user = new User();
				user.setMobile(mobileno);
				user = userDao.findObject(user);

				User tuser = new User();
				tuser.setUsername(mobileno);
				tuser = userDao.findObject(tuser);
				if (user == null) {
					user = new User();
					user.setMobile(mobileno);
					user.setParentid(userSupplier.getSupplierid());
					user.setUsername(mobileno);
					// user.setRoleid("F717B4EEF241460891F0E1D3AB84F74E");
					List<String> roleids = new ArrayList<String>();
					roleids.add("F717B4EEF241460891F0E1D3AB84F74E");
					user.setRoleids(roleids);

					userDao.save(user);
				} else if (!userSupplier.getSupplierid().equals(user.getParentid()) && !user.getId().equals(userSupplier.getSupplierid())) {
					throw new Exception("该手机号已被其他供应商使用!");
				}
				userSupplier.setContactid(user.getId());
			} else {
				userSupplier.setContactid("");
			}
			userSupplierDao.update(userSupplier);
		}
	}

	@Override
	public void upload(String userid, List<UserSupplier> userSuppliers) throws Exception {
		// TODO Auto-generated method stub
		for (UserSupplier userSupplier : userSuppliers) {
			userSupplier.setUserid(userid);
			userSupplier.setUsed(false);
			userSupplier.setSend(false);
			UserSupplier us = new UserSupplier();
			us.setCode(userSupplier.getCode());
			us.setUserid(userid);
			us = userSupplierDao.findObject(us);

			if (us == null) {
				userSupplierDao.save(userSupplier);
			} else {
				userSupplier.setId(us.getId());
				this.update(userSupplier);
			}

		}
	}

	@Override
	public PageResult<User> findSupplierList(String keyword) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_user   where 1=1 and id in(select userid from t_user_role where  roleid ='B1CC34941707470D9BF361D1CEF2B08E')");

		ps.add("%" + keyword + "%");
		sql.append(" and  company  like  ?");

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userSupplierDao.findList(sqlContext, new Page(false), User.class);
	}

}
