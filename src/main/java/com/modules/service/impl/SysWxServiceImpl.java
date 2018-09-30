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
import com.modules.service.SysWxService;
import com.modules.dao.SysWxDao;
import com.modules.pojo.SysWx;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxServiceImpl implements SysWxService {

	@Autowired
	private SysWxDao sysWxDao;

	public void save(SysWx entity) throws Exception {
		sysWxDao.save(entity);
	}

	public void save(List<SysWx> entities) throws Exception {
		sysWxDao.save(entities);
	}

	public void update(SysWx entity) throws Exception {
		sysWxDao.update(entity);
	}

	public void update(SysWx newEntity, SysWx oldEntity) throws Exception {
		sysWxDao.update(newEntity, oldEntity);
	}

	public void delete(SysWx entity) throws Exception {
		sysWxDao.delete(entity);
	}

	public SysWx findObject(SysWx entity) throws Exception {
		return sysWxDao.findObject(entity);
	}

	public List<SysWx> findList(SysWx entity) throws Exception {
		return sysWxDao.findList(entity);
	}

	public PageResult<SysWx> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx where 1=1 ");
		if (params.containsKey("name")) {
			sql.append(" and name like ?");
			ps.add("%" + params.get("name") + "%");
		}
		if (params.containsKey("userid")) {
			sql.append(" and id in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		
		if (params.containsKey("ispromotion")) {
			sql.append(" and ispromotion=?");
			ps.add(params.get("ispromotion"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxDao.findList(sqlContext, page, SysWx.class);
	}

}
