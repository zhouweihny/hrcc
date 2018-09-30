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
import com.modules.service.ErpuserService;
import com.modules.dao.ErpuserDao;
import com.modules.pojo.Erpuser;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ErpuserServiceImpl implements ErpuserService {

	@Autowired
	private ErpuserDao erpuserDao;

	public void save(Erpuser entity) throws Exception {
		erpuserDao.save(entity);
	}

	public void save(List<Erpuser> entities) throws Exception {
		erpuserDao.save(entities);
	}

	public void update(Erpuser entity) throws Exception {
		erpuserDao.update(entity);
	}

	public void update(Erpuser newEntity, Erpuser oldEntity) throws Exception {
		erpuserDao.update(newEntity, oldEntity);
	}

	public void delete(Erpuser entity) throws Exception {
		erpuserDao.delete(entity);
	}

	public Erpuser findObject(Erpuser entity) throws Exception {
		return erpuserDao.findObject(entity);
	}

	public List<Erpuser> findList(Erpuser entity) throws Exception {
		return erpuserDao.findList(entity);
	}

	public PageResult<Erpuser> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_erpuser where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return erpuserDao.findList(sqlContext, page, Erpuser.class);
	}

}
