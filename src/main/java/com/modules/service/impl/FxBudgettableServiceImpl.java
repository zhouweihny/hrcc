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
import com.modules.service.FxBudgettableService;
import com.modules.dao.FxBudgettableDao;
import com.modules.pojo.FxBudgettable;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxBudgettableServiceImpl implements FxBudgettableService {

	@Autowired
	private FxBudgettableDao fxBudgettableDao;

	public void save(FxBudgettable entity) throws Exception {
		fxBudgettableDao.save(entity);
	}

	public void save(List<FxBudgettable> entities) throws Exception {
		fxBudgettableDao.save(entities);
	}

	public void update(FxBudgettable entity) throws Exception {
		fxBudgettableDao.update(entity);
	}

	public void update(FxBudgettable newEntity, FxBudgettable oldEntity) throws Exception {
		fxBudgettableDao.update(newEntity, oldEntity);
	}

	public void delete(FxBudgettable entity) throws Exception {
		fxBudgettableDao.delete(entity);
	}

	public FxBudgettable findObject(FxBudgettable entity) throws Exception {
		return fxBudgettableDao.findObject(entity);
	}

	public List<FxBudgettable> findList(FxBudgettable entity) throws Exception {
		return fxBudgettableDao.findList(entity);
	}

	public PageResult<FxBudgettable> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_budgettable where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxBudgettableDao.findList(sqlContext, page, FxBudgettable.class);
	}

}
