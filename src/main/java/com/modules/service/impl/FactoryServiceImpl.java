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
import com.modules.service.FactoryService;
import com.modules.dao.FactoryDao;
import com.modules.pojo.Factory;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FactoryServiceImpl implements FactoryService {

	@Autowired
	private FactoryDao factoryDao;

	public void save(Factory entity) throws Exception {
		factoryDao.save(entity);
	}

	public void save(List<Factory> entities) throws Exception {
		factoryDao.save(entities);
	}

	public void update(Factory entity) throws Exception {
		factoryDao.update(entity);
	}

	public void update(Factory newEntity, Factory oldEntity) throws Exception {
		factoryDao.update(newEntity, oldEntity);
	}

	public void delete(Factory entity) throws Exception {
		factoryDao.delete(entity);
	}

	public Factory findObject(Factory entity) throws Exception {
		return factoryDao.findObject(entity);
	}

	public List<Factory> findList(Factory entity) throws Exception {
		return factoryDao.findList(entity);
	}

	public PageResult<Factory> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_factory where 1=1 ");
		if (params.containsKey("name")) {
			sql.append(" and (");
			int i = 0;
			for (String str : params.get("name").toString().split(",")) {
				if (i == 0) {
					sql.append("   name like ? ");
				} else {
					sql.append(" or  name like ? ");
				}
				ps.add("%" + str + "%");
				i++;
			}

			sql.append(" )");

		}

		if (params.containsKey("code")) {
			sql.append(" and code like ?");
			ps.add("%" + params.get("code") + "%");

		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return factoryDao.findList(sqlContext, page, Factory.class);
	}

	public void upload(String userid, List<String> list) throws Exception {
		List<Factory> factorys = new ArrayList<Factory>();
		StringBuilder strs = new StringBuilder();
		strs.append("''");
		int i = 0;
		for (String str : list) {
			if (!StringUtils.isEmpty(str)) {
				i++;
				Factory factory = new Factory();
				factory.setName(str);
				factorys.add(factory);
				strs.append(",'").append(str).append("'");
			}
		}
		if (i > 0) {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append("select distinct(code) as code from t_factory where name  in(").append(strs).append(") ");
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			Factory factory = factoryDao.findObject(sqlContext, Factory.class);
			String code;
			if (factory == null) {
				code = UUIDGenerator.getUUID();
			} else {
				code = factory.getCode();
			}

			for (Factory f : factorys) {
				f.setCode(code);
				f.setOperatorid(userid);
			}

			factoryDao.save(factorys);
		}
	}

	public void removeRepeat() throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("delete from t_factory where id not in (select minid from (select min(id) as minid from t_factory group by name) b)");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		factoryDao.update(sqlContext);
	}

	@Override
	public PageResult<Factory> findFactoryCode(Page page) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select code,group_concat(name) as name from t_factory group by code");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return factoryDao.findList(sqlContext, page, Factory.class);
	}

	@Override
	public void merge(String userid, List<String> ids) throws Exception {
		// TODO Auto-generated method stub
		String uuid = UUIDGenerator.getUUID();

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("update  t_factory set code=? , operatorid=?  where code in(select code from (select code from t_factory where id in (");
		for (int i = 0; i < ids.size(); i++) {
			if (i == 0)
				sql.append("?");
			else
				sql.append(",?");
		}
		sql.append("))  a)   ");
		ps.add(uuid);
		ps.add(userid);
		ps.addAll(ids);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		factoryDao.update(sqlContext);
	}

	@Override
	public PageResult<Factory> findNIFactory(Page page) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select DISTINCT(factory) as name from t_drug   where not EXISTS (select * from t_factory where name=factory)");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return factoryDao.findList(sqlContext, page, Factory.class);
	}

}
