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
import com.modules.service.FxMetaDatasService;
import com.modules.dao.FxMetaDatasDao;
import com.modules.pojo.FxMetaDatas;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxMetaDatasServiceImpl implements FxMetaDatasService {

	@Autowired
	private FxMetaDatasDao fxMetaDatasDao;

	public void save(FxMetaDatas entity) throws Exception {
		fxMetaDatasDao.save(entity);
	}

	public void save(List<FxMetaDatas> entities) throws Exception {
		fxMetaDatasDao.save(entities);
	}

	public void update(FxMetaDatas entity) throws Exception {
		fxMetaDatasDao.update(entity);
	}

	public void update(FxMetaDatas newEntity, FxMetaDatas oldEntity) throws Exception {
		fxMetaDatasDao.update(newEntity, oldEntity);
	}

	public void delete(FxMetaDatas entity) throws Exception {
		fxMetaDatasDao.delete(entity);
	}

	public FxMetaDatas findObject(FxMetaDatas entity) throws Exception {
		return fxMetaDatasDao.findObject(entity);
	}

	public List<FxMetaDatas> findList(FxMetaDatas entity) throws Exception {
		return fxMetaDatasDao.findList(entity);
	}

	public PageResult<FxMetaDatas> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_meta_datas where 1=1 ");
		if (params.containsKey("metaid")) {
			sql.append(" and metaid=? ");
			ps.add(params.get("metaid"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxMetaDatasDao.findList(sqlContext, page, FxMetaDatas.class);
	}

}
