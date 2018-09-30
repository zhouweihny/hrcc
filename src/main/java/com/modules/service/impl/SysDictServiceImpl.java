package com.modules.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.SysDictService;
import com.modules.dao.SysDictDao;
import com.modules.pojo.SysDict;

/**
 * 
 * @author Du.Jun
 */
@Service
public class SysDictServiceImpl implements SysDictService {

	@Autowired
	private SysDictDao sysDictDao;

	public void save(SysDict entity) throws Exception {
		sysDictDao.save(entity);
	}

	public void save(List<SysDict> entities) throws Exception {
		sysDictDao.save(entities);
	}

	public void update(SysDict entity) throws Exception {
		sysDictDao.update(entity);
	}

	public void update(SysDict newEntity, SysDict oldEntity) throws Exception {
		sysDictDao.update(newEntity, oldEntity);
	}

	public void delete(SysDict entity) throws Exception {
		sysDictDao.delete(entity);
	}

	public SysDict findObject(SysDict entity) throws Exception {
		return sysDictDao.findObject(entity);
	}

	public List<SysDict> findList(SysDict entity) throws Exception {
		return sysDictDao.findList(entity);
	}

	public PageResult<SysDict> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		List<Object> plist = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from sys_dict where 1=1 ");

		if (params.containsKey("code")) {
			plist.add("%" + params.get("code") + "%");
			sql.append(" and code like ? ");
		}

		if (params.containsKey("name")) {
			plist.add("%" + params.get("name") + "%");
			sql.append(" and name like ? ");
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(plist.toArray());
		return sysDictDao.findList(sqlContext, page, SysDict.class);
	}

}
