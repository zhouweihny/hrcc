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
import com.modules.service.FxTreeMetaDatasService;
import com.modules.dao.FxTreeMetaDatasDao;
import com.modules.pojo.FxTreeMetaDatas;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxTreeMetaDatasServiceImpl implements FxTreeMetaDatasService {

	@Autowired
	private FxTreeMetaDatasDao fxTreeMetaDatasDao;

	public void save(FxTreeMetaDatas entity) throws Exception {
		fxTreeMetaDatasDao.save(entity);
	}

	public void save(List<FxTreeMetaDatas> entities) throws Exception {
		fxTreeMetaDatasDao.save(entities);
	}

	public void update(FxTreeMetaDatas entity) throws Exception {
		fxTreeMetaDatasDao.update(entity);
	}

	public void update(FxTreeMetaDatas newEntity, FxTreeMetaDatas oldEntity) throws Exception {
		fxTreeMetaDatasDao.update(newEntity, oldEntity);
	}

	public void delete(FxTreeMetaDatas entity) throws Exception {
		fxTreeMetaDatasDao.delete(entity);
	}

	public FxTreeMetaDatas findObject(FxTreeMetaDatas entity) throws Exception {
		return fxTreeMetaDatasDao.findObject(entity);
	}

	public List<FxTreeMetaDatas> findList(FxTreeMetaDatas entity) throws Exception {
		return fxTreeMetaDatasDao.findList(entity);
	}

	public PageResult<FxTreeMetaDatas> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree_meta_datas where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeMetaDatasDao.findList(sqlContext, page, FxTreeMetaDatas.class);
	}

}
