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
import com.modules.service.FxComnameTreeFactoryService;
import com.modules.dao.FxComnameTreeFactoryDao;
import com.modules.pojo.FxComnameTreeFactory;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxComnameTreeFactoryServiceImpl implements FxComnameTreeFactoryService {

	@Autowired
	private FxComnameTreeFactoryDao fxComnameTreeFactoryDao;

	public void save(FxComnameTreeFactory entity) throws Exception {
		fxComnameTreeFactoryDao.save(entity);
	}

	public void save(List<FxComnameTreeFactory> entities) throws Exception {
		fxComnameTreeFactoryDao.save(entities);
	}

	public void update(FxComnameTreeFactory entity) throws Exception {
		fxComnameTreeFactoryDao.update(entity);
	}

	public void update(FxComnameTreeFactory newEntity, FxComnameTreeFactory oldEntity) throws Exception {
		fxComnameTreeFactoryDao.update(newEntity, oldEntity);
	}

	public void delete(FxComnameTreeFactory entity) throws Exception {
		fxComnameTreeFactoryDao.delete(entity);
	}

	public FxComnameTreeFactory findObject(FxComnameTreeFactory entity) throws Exception {
		return fxComnameTreeFactoryDao.findObject(entity);
	}

	public List<FxComnameTreeFactory> findList(FxComnameTreeFactory entity) throws Exception {
		return fxComnameTreeFactoryDao.findList(entity);
	}

	public PageResult<FxComnameTreeFactory> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_comname_tree_factory where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxComnameTreeFactoryDao.findList(sqlContext, page, FxComnameTreeFactory.class);
	}

}
