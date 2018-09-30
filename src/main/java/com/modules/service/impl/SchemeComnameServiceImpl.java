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
import com.modules.service.SchemeComnameService;
import com.modules.dao.SchemeComnameDao;
import com.modules.pojo.SchemeComname;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SchemeComnameServiceImpl implements SchemeComnameService {

	@Autowired
	private SchemeComnameDao schemeComnameDao;

	public void save(SchemeComname entity) throws Exception {
		schemeComnameDao.save(entity);
	}

	public void save(List<SchemeComname> entities) throws Exception {
		schemeComnameDao.save(entities);
	}

	public void update(SchemeComname entity) throws Exception {
		schemeComnameDao.update(entity);
	}

	public void update(SchemeComname newEntity, SchemeComname oldEntity) throws Exception {
		schemeComnameDao.update(newEntity, oldEntity);
	}

	public void delete(SchemeComname entity) throws Exception {
		schemeComnameDao.delete(entity);
	}

	public SchemeComname findObject(SchemeComname entity) throws Exception {
		return schemeComnameDao.findObject(entity);
	}

	public List<SchemeComname> findList(SchemeComname entity) throws Exception {
		return schemeComnameDao.findList(entity);
	}

	public PageResult<SchemeComname> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_scheme_comname where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return schemeComnameDao.findList(sqlContext, page, SchemeComname.class);
	}

}
