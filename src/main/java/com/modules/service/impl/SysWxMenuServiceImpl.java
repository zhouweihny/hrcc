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
import com.commons.util.UUIDGenerator;
import com.modules.service.SysWxMenuService;
import com.modules.dao.SysWxMenuDao;
import com.modules.pojo.SysWxMenu;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxMenuServiceImpl implements SysWxMenuService {

	@Autowired
	private SysWxMenuDao sysWxMenuDao;

	public void save(SysWxMenu entity) throws Exception {
		String id = UUIDGenerator.getUUID();
		if (StringUtils.isEmpty(entity.getParentid())) {
			entity.setId(id);
			entity.setPath(id);
		} else {
			SysWxMenu m = new SysWxMenu();
			m.setId(entity.getParentid());
			SysWxMenu menu = sysWxMenuDao.findObject(m);
			entity.setId(id);
			entity.setPath(menu.getPath() + "/" + id);
		}
		sysWxMenuDao.save(entity);
	}

	public void save(List<SysWxMenu> entities) throws Exception {
		sysWxMenuDao.save(entities);
	}

	public void update(SysWxMenu entity) throws Exception {
		SysWxMenu m = new SysWxMenu();
		if (!StringUtils.isEmpty(entity.getParentid())) {
			m.setId(entity.getParentid());
			SysWxMenu menu = sysWxMenuDao.findObject(m);
			entity.setPath(menu.getPath() + "/" + entity.getId());
		}
		sysWxMenuDao.update(entity);
	}

	public void update(SysWxMenu newEntity, SysWxMenu oldEntity) throws Exception {
		sysWxMenuDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxMenu entity) throws Exception {
		sysWxMenuDao.delete(entity);
	}

	public SysWxMenu findObject(SysWxMenu entity) throws Exception {
		return sysWxMenuDao.findObject(entity);
	}

	public List<SysWxMenu> findList(SysWxMenu entity) throws Exception {
		return sysWxMenuDao.findList(entity);
	}

	public PageResult<SysWxMenu> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_menu where 1=1 ");
		if (params.containsKey("wxid")) {
			sql.append("  and  wxid=? ");
			ps.add(params.get("wxid"));
		}
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		sql.append(" order by sort desc,path asc ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxMenuDao.findList(sqlContext, page, SysWxMenu.class);
	}

}
