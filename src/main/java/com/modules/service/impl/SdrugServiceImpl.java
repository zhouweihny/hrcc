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
import com.modules.service.SdrugService;
import com.modules.dao.SdrugDao;
import com.modules.pojo.Sdrug;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SdrugServiceImpl implements SdrugService {

	@Autowired
	private SdrugDao sdrugDao;

	public void save(Sdrug entity) throws Exception {
		sdrugDao.save(entity);
	}

	public void save(List<Sdrug> entities) throws Exception {
		sdrugDao.save(entities);
	}

	public void update(Sdrug entity) throws Exception {
		sdrugDao.update(entity);
	}

	public void update(Sdrug newEntity, Sdrug oldEntity) throws Exception {
		sdrugDao.update(newEntity, oldEntity);
	}

	public void delete(Sdrug entity) throws Exception {
		sdrugDao.delete(entity);
	}

	public Sdrug findObject(Sdrug entity) throws Exception {
		return sdrugDao.findObject(entity);
	}

	public List<Sdrug> findList(Sdrug entity) throws Exception {
		return sdrugDao.findList(entity);
	}

	public PageResult<Sdrug> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_sdrug where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sdrugDao.findList(sqlContext, page, Sdrug.class);
	}

}
