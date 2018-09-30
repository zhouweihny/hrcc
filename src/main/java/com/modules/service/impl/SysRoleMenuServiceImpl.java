package com.modules.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.SysRoleMenuService;
import com.modules.dao.SysRoleMenuDao;
import com.modules.pojo.SysRoleMenu;

/**
 * 
 * @author Du.Jun
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	public void save(SysRoleMenu entity) throws Exception {
		sysRoleMenuDao.save(entity);
	}

	public void save(List<SysRoleMenu> entities) throws Exception {
		sysRoleMenuDao.save(entities);
	}

	public void update(SysRoleMenu entity) throws Exception {
		sysRoleMenuDao.update(entity);
	}

	public void update(SysRoleMenu newEntity, SysRoleMenu oldEntity) throws Exception {
		sysRoleMenuDao.update(newEntity, oldEntity);
	}

	public void delete(SysRoleMenu entity) throws Exception {
		sysRoleMenuDao.delete(entity);
	}

	public SysRoleMenu findObject(SysRoleMenu entity) throws Exception {
		return sysRoleMenuDao.findObject(entity);
	}

	public List<SysRoleMenu> findList(SysRoleMenu entity) throws Exception {
		return sysRoleMenuDao.findList(entity);
	}

	public PageResult<SysRoleMenu> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		List<Object> plist = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from sys_role_menu where 1=1 ");
		if (params.containsKey("roleid")) {
			plist.add(params.get("roleid"));
			sql.append(" and roleid = ? ");
		}

		if (params.containsKey("userid")) {
			plist.add(params.get("userid"));
			sql.append(" and roleid in (select roleid from t_user_role where userid= ?) ");
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(plist.toArray());

		return sysRoleMenuDao.findList(sqlContext, page, SysRoleMenu.class);
	}

	@Override
	public void bindmenus(String roleid, String menuids) throws Exception {
		SysRoleMenu entity = new SysRoleMenu();
		entity.setRoleid(roleid);
		sysRoleMenuDao.delete(entity);
		List<SysRoleMenu> rms = new ArrayList<SysRoleMenu>();
		for (String menuid : menuids.split(",")) {
			if (!StringUtils.isEmpty(menuid)) {
				SysRoleMenu rm = new SysRoleMenu();
				rm.setMenuid(menuid);
				rm.setRoleid(roleid);
				rms.add(rm);
			}
		}
		sysRoleMenuDao.save(rms);
	}

}
