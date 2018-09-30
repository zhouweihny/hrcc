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
import com.modules.service.StoreService;
import com.modules.dao.StoreDao;
import com.modules.pojo.Store;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDao;

	public void save(Store entity) throws Exception {
		storeDao.save(entity);
	}

	public void save(List<Store> entities) throws Exception {
		storeDao.save(entities);
	}

	public void update(Store entity) throws Exception {
		storeDao.update(entity);
	}

	public void update(Store newEntity, Store oldEntity) throws Exception {
		storeDao.update(newEntity, oldEntity);
	}

	public void delete(Store entity) throws Exception {
		storeDao.delete(entity);
	}

	public Store findObject(Store entity) throws Exception {
		return storeDao.findObject(entity);
	}

	public List<Store> findList(Store entity) throws Exception {
		return storeDao.findList(entity);
	}

	public PageResult<Store> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_store where 1=1 ");
		if (params.containsKey("companyid")) {
			sql.append(" and  userid= ?");
			ps.add(params.get("companyid"));
		}

		if (params.containsKey("userid")) {
			if (!(params.containsKey("companyid") && params.get("companyid").equals(params.get("userid")))) {
				sql.append(" and  id in (select storeid from t_user_store where userid= ?) ");
				ps.add(params.get("userid"));
			}
		}

		if (params.containsKey("keywords"))

		{
			sql.append(" and ( name  like  ?  or  storecode  like  ? )");
			ps.add("%" + params.get("keywords") + "%");
			ps.add("%" + params.get("keywords") + "%");
		}

		if (params.containsKey("name")) {
			sql.append(" and  name  like  ?");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("storecode")) {
			sql.append(" and  storecode  like  ?");
			ps.add("%" + params.get("storecode") + "%");
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return storeDao.findList(sqlContext, page, Store.class);
	}

}
