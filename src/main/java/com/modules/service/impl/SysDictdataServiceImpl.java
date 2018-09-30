package com.modules.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.SysDictdataService;
import com.modules.dao.SysDictdataDao;
import com.modules.pojo.SysDictdata;

/**
 * 
 * @author Du.Jun
 */
@Service
public class SysDictdataServiceImpl implements SysDictdataService {

	@Autowired
	private SysDictdataDao sysDictdataDao;

	public void save(SysDictdata entity) throws Exception {
		sysDictdataDao.save(entity);
	}

	public void save(List<SysDictdata> entities) throws Exception {
		sysDictdataDao.save(entities);
	}

	public void update(SysDictdata entity) throws Exception {
		sysDictdataDao.update(entity);
	}

	public void update(SysDictdata newEntity, SysDictdata oldEntity) throws Exception {
		sysDictdataDao.update(newEntity, oldEntity);
	}

	public void delete(SysDictdata entity) throws Exception {
		sysDictdataDao.delete(entity);
	}

	public SysDictdata findObject(SysDictdata entity) throws Exception {
		return sysDictdataDao.findObject(entity);
	}

	public List<SysDictdata> findList(SysDictdata entity) throws Exception {
		return sysDictdataDao.findList(entity);
	}

	public PageResult<SysDictdata> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		List<Object> plist = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.* from sys_dictdata t1,sys_dict t2  where 1=1 and t1.dictid=t2.id ");
		if (params.containsKey("code")) {
			plist.add(params.get("code"));
			sql.append(" and t2.code=? ");
		}

		if (params.containsKey("dictid")) {
			plist.add(params.get("dictid"));
			sql.append(" and t1.dictid=? ");
		}

		sql.append(" order by dkey asc ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(plist.toArray());
		return sysDictdataDao.findList(sqlContext, page, SysDictdata.class);
	}

}
