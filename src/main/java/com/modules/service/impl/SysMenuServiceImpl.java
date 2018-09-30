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
import com.commons.util.UUIDGenerator;
import com.modules.service.SysMenuService;
import com.modules.dao.SysMenuDao;
import com.modules.pojo.SysMenu;

/**
 * 
 * @author Du.Jun
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	public void save(SysMenu entity) throws Exception {
		String id = UUIDGenerator.getUUID();
		if (StringUtils.isEmpty(entity.getParentid())) {
			entity.setId(id);
			entity.setPath(id);
		} else {
			SysMenu m = new SysMenu();
			m.setId(entity.getParentid());
			SysMenu menu = sysMenuDao.findObject(m);
			entity.setId(id);
			entity.setPath(menu.getPath() + "/" + id);
		}
		sysMenuDao.save(entity);
	}

	public void save(List<SysMenu> entities) throws Exception {
		sysMenuDao.save(entities);
	}

	public void update(SysMenu entity) throws Exception {
		SysMenu m = new SysMenu();
		if (!StringUtils.isEmpty(entity.getParentid())) {
			m.setId(entity.getParentid());
			SysMenu menu = sysMenuDao.findObject(m);
			entity.setPath(menu.getPath() + "/" + entity.getId());
		}
		sysMenuDao.update(entity);
	}

	public void update(SysMenu newEntity, SysMenu oldEntity) throws Exception {
		sysMenuDao.update(newEntity, oldEntity);
	}

	public void delete(SysMenu entity) throws Exception {
		sysMenuDao.delete(entity);
	}

	public SysMenu findObject(SysMenu entity) throws Exception {
		return sysMenuDao.findObject(entity);
	}

	public List<SysMenu> findList(SysMenu entity) throws Exception {
		return sysMenuDao.findList(entity);
	}

	public PageResult<SysMenu> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();

		List<Object> plist = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from sys_menu where 1=1 ");
		if (params.containsKey("parentid")) {
			plist.add(params.get("parentid"));
			sql.append(" and parentid = ? ");
		}
		sql.append(" order by sort desc,path asc ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(plist.toArray());

		return sysMenuDao.findList(sqlContext, page, SysMenu.class);
	}
}
