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
import com.modules.service.FxTreeMetaDatasMsgService;
import com.modules.dao.FxTreeMetaDatasMsgDao;
import com.modules.pojo.FxTreeMetaDatasMsg;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxTreeMetaDatasMsgServiceImpl implements FxTreeMetaDatasMsgService {

	@Autowired
	private FxTreeMetaDatasMsgDao fxTreeMetaDatasMsgDao;

	public void save(FxTreeMetaDatasMsg entity) throws Exception {
		fxTreeMetaDatasMsgDao.save(entity);
	}

	public void save(List<FxTreeMetaDatasMsg> entities) throws Exception {
		fxTreeMetaDatasMsgDao.save(entities);
	}

	public void update(FxTreeMetaDatasMsg entity) throws Exception {
		fxTreeMetaDatasMsgDao.update(entity);
	}

	public void update(FxTreeMetaDatasMsg newEntity, FxTreeMetaDatasMsg oldEntity) throws Exception {
		fxTreeMetaDatasMsgDao.update(newEntity, oldEntity);
	}

	public void delete(FxTreeMetaDatasMsg entity) throws Exception {
		fxTreeMetaDatasMsgDao.delete(entity);
	}

	public FxTreeMetaDatasMsg findObject(FxTreeMetaDatasMsg entity) throws Exception {
		return fxTreeMetaDatasMsgDao.findObject(entity);
	}

	public List<FxTreeMetaDatasMsg> findList(FxTreeMetaDatasMsg entity) throws Exception {
		return fxTreeMetaDatasMsgDao.findList(entity);
	}

	public PageResult<FxTreeMetaDatasMsg> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree_meta_datas_msg where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeMetaDatasMsgDao.findList(sqlContext, page, FxTreeMetaDatasMsg.class);
	}

}
