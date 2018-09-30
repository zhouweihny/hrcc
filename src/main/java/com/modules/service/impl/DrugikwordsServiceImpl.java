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
import com.modules.service.DrugikwordsService;
import com.modules.dao.DrugikwordsDao;
import com.modules.pojo.Drugikwords;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugikwordsServiceImpl implements DrugikwordsService {

	@Autowired
	private DrugikwordsDao drugikwordsDao;

	public void save(Drugikwords entity) throws Exception {
		drugikwordsDao.save(entity);
	}

	public void save(List<Drugikwords> entities) throws Exception {
		drugikwordsDao.save(entities);
	}

	public void update(Drugikwords entity) throws Exception {
		drugikwordsDao.update(entity);
	}

	public void update(Drugikwords newEntity, Drugikwords oldEntity) throws Exception {
		drugikwordsDao.update(newEntity, oldEntity);
	}

	public void delete(Drugikwords entity) throws Exception {
		drugikwordsDao.delete(entity);
	}

	public Drugikwords findObject(Drugikwords entity) throws Exception {
		return drugikwordsDao.findObject(entity);
	}

	public List<Drugikwords> findList(Drugikwords entity) throws Exception {
		return drugikwordsDao.findList(entity);
	}

	public PageResult<Drugikwords> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drugikwords where 1=1 and disabled=0 ");
		if (params.containsKey("name")) {
			sql.append(" and  name like ?");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("iskeywords")) {
			if (params.get("iskeywords").equals("0"))
				sql.append(" and num =0");
			else
				sql.append(" and num >0");
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugikwordsDao.findList(sqlContext, page, Drugikwords.class);
	}

	@Override
	public void batchInsertFromDrug() throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("insert into t_drugikwords (id,name ,createtime) ");
		sql.append(" select t2.* from (select min(id) as id,  name  ,min(createtime) as createtime from t_drug GROUP BY name) as t2 ");
		sql.append(" where   not  exists   ( select * from  t_drugikwords t1     where t1.name=t2.name )");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugikwordsDao.update(sqlContext);
	}

	@Override
	public void updateDelwords() throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("update t_drugikwords set words=null, num=null ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugikwordsDao.update(sqlContext);
	}

}
