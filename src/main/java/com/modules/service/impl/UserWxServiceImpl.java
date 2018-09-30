package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.UserWxService;
import com.modules.vo.Wx;
import com.modules.dao.UserWxDao;
import com.modules.pojo.UserWx;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserWxServiceImpl implements UserWxService {

	@Autowired
	private UserWxDao userWxDao;

	public void save(UserWx entity) throws Exception {
		userWxDao.save(entity);
	}

	public void save(List<UserWx> entities) throws Exception {
		userWxDao.save(entities);
	}

	public void update(UserWx entity) throws Exception {
		userWxDao.update(entity);
	}

	public void update(UserWx newEntity, UserWx oldEntity) throws Exception {
		userWxDao.update(newEntity, oldEntity);
	}

	public void delete(UserWx entity) throws Exception {
		userWxDao.delete(entity);
	}

	public UserWx findObject(UserWx entity) throws Exception {
		return userWxDao.findObject(entity);
	}

	public List<UserWx> findList(UserWx entity) throws Exception {
		return userWxDao.findList(entity);
	}

	public PageResult<UserWx> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_user_wx where 1=1  ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userWxDao.findList(sqlContext, page, UserWx.class);
	}

	@Override
	public PageResult<Wx> findWxList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from (select w.* ,");
		if (params.containsKey("userid")) {
			sql.append("case when exists(select 1 from t_user_wx  where wxid=w.id and userid=? ) then 1 else 0 end   selected");
			ps.add(params.get("userid"));
		} else
			sql.append("1 selected");
		sql.append(" from  sys_wx w  ) as t where 1=1 ");
		if (params.containsKey("name")) {
			ps.add("%" + params.get("name") + "%");
			sql.append(" and name like ? ");
		}
		if (params.containsKey("selected")) {
			ps.add(params.get("selected"));
			sql.append(" and t.selected = ? ");
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return userWxDao.findList(sqlContext, page, Wx.class);
	}
}
