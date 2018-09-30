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
import com.modules.service.SysWxUserService;
import com.modules.vo.Wx;
import com.modules.dao.SysWxUserDao;
import com.modules.pojo.SysWxUser;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxUserServiceImpl implements SysWxUserService {

	@Autowired
	private SysWxUserDao sysWxUserDao;

	public void save(SysWxUser entity) throws Exception {
		sysWxUserDao.save(entity);
	}

	public void save(List<SysWxUser> entities) throws Exception {
		sysWxUserDao.save(entities);
	}

	public void update(SysWxUser entity) throws Exception {
		sysWxUserDao.update(entity);
	}

	public void update(SysWxUser newEntity, SysWxUser oldEntity) throws Exception {
		sysWxUserDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxUser entity) throws Exception {
		sysWxUserDao.delete(entity);
	}

	public SysWxUser findObject(SysWxUser entity) throws Exception {
		return sysWxUserDao.findObject(entity);
	}

	public List<SysWxUser> findList(SysWxUser entity) throws Exception {
		return sysWxUserDao.findList(entity);
	}

	public PageResult<SysWxUser> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_user where 1=1");
		if (params.containsKey("wxid")) {
			sql.append(" and wxid = ?");
			ps.add(params.get("wxid"));
		}
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		sql.append(" order by createtime desc");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxUserDao.findList(sqlContext, page, SysWxUser.class);
	}

	@Override
	public PageResult<Wx> selectWxList(Map<String, Object> params, Page page) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_user wu,sys_wx w where 1=1");
		if (params.containsKey("name")) {
			sql.append(" and w.name like ?");
			ps.add("%" + params.get("name") + "%");
		}
		if (params.containsKey("userid")) {
			sql.append(" and wu.userid = ?");
			ps.add(params.get("userid"));
		}
		sql.append(" order by wu.createtime desc");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxUserDao.findList(sqlContext, page, Wx.class);
	}

}
