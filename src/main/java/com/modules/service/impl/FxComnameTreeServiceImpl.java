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
import com.modules.service.FxComnameTreeService;
import com.modules.dao.FxComnameTreeDao;
import com.modules.pojo.FxComnameTree;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxComnameTreeServiceImpl implements FxComnameTreeService {

	@Autowired
	private FxComnameTreeDao fxComnameTreeDao;

	public void save(FxComnameTree entity) throws Exception {
		fxComnameTreeDao.save(entity);
	}

	public void save(List<FxComnameTree> entities) throws Exception {
		fxComnameTreeDao.save(entities);
	}

	public void update(FxComnameTree entity) throws Exception {
		fxComnameTreeDao.update(entity);
	}

	public void update(FxComnameTree newEntity, FxComnameTree oldEntity) throws Exception {
		fxComnameTreeDao.update(newEntity, oldEntity);
	}

	public void delete(FxComnameTree entity) throws Exception {
		fxComnameTreeDao.delete(entity);
	}

	public FxComnameTree findObject(FxComnameTree entity) throws Exception {
		return fxComnameTreeDao.findObject(entity);
	}

	public List<FxComnameTree> findList(FxComnameTree entity) throws Exception {
		return fxComnameTreeDao.findList(entity);
	}

	public PageResult<FxComnameTree> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_comname_tree where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxComnameTreeDao.findList(sqlContext, page, FxComnameTree.class);
	}

}
