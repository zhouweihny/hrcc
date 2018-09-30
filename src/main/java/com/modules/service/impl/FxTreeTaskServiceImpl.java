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
import com.modules.service.FxTreeTaskService;
import com.modules.dao.FxTreeTaskDao;
import com.modules.pojo.FxTreeTask;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxTreeTaskServiceImpl implements FxTreeTaskService {

	@Autowired
	private FxTreeTaskDao fxTreeTaskDao;

	public void save(FxTreeTask entity) throws Exception {
		fxTreeTaskDao.save(entity);
	}

	public void save(List<FxTreeTask> entities) throws Exception {
		fxTreeTaskDao.save(entities);
	}

	public void update(FxTreeTask entity) throws Exception {
		fxTreeTaskDao.update(entity);
	}

	public void update(FxTreeTask newEntity, FxTreeTask oldEntity) throws Exception {
		fxTreeTaskDao.update(newEntity, oldEntity);
	}

	public void delete(FxTreeTask entity) throws Exception {
		fxTreeTaskDao.delete(entity);
	}

	public FxTreeTask findObject(FxTreeTask entity) throws Exception {
		return fxTreeTaskDao.findObject(entity);
	}

	public List<FxTreeTask> findList(FxTreeTask entity) throws Exception {
		return fxTreeTaskDao.findList(entity);
	}

	public PageResult<FxTreeTask> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree_task where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeTaskDao.findList(sqlContext, page, FxTreeTask.class);
	}

}
