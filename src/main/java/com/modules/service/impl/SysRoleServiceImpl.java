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
import com.modules.service.SysRoleService;
import com.modules.dao.SysRoleDao;
import com.modules.pojo.SysRole;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	public void save(SysRole entity) throws Exception {
		sysRoleDao.save(entity);
	}

	public void save(List<SysRole> entities) throws Exception {
		sysRoleDao.save(entities);
	}

	public void update(SysRole entity) throws Exception {
		sysRoleDao.update(entity);
	}

	public void update(SysRole newEntity, SysRole oldEntity) throws Exception {
		sysRoleDao.update(newEntity, oldEntity);
	}

	public void delete(SysRole entity) throws Exception {
		sysRoleDao.delete(entity);
	}

	public SysRole findObject(SysRole entity) throws Exception {
		return sysRoleDao.findObject(entity);
	}

	public List<SysRole> findList(SysRole entity) throws Exception {
		return sysRoleDao.findList(entity);
	}

	public PageResult<SysRole> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_role where 1=1 ");
		if (params.containsKey("name")) {
			ps.add("%" + params.get("name") + "%");
			sql.append(" and name like ? ");
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysRoleDao.findList(sqlContext, page, SysRole.class);
	}

}
