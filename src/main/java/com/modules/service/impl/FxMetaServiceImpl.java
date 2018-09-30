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
import com.modules.service.FxMetaService;
import com.modules.dao.FxMetaDao;
import com.modules.pojo.FxMeta;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxMetaServiceImpl implements FxMetaService {

	@Autowired
	private FxMetaDao fxMetaDao;

	public void save(FxMeta entity) throws Exception {
		fxMetaDao.save(entity);
	}

	public void save(List<FxMeta> entities) throws Exception {
		fxMetaDao.save(entities);
	}

	public void update(FxMeta entity) throws Exception {
		fxMetaDao.update(entity);
	}

	public void update(FxMeta newEntity, FxMeta oldEntity) throws Exception {
		fxMetaDao.update(newEntity, oldEntity);
	}

	public void delete(FxMeta entity) throws Exception {
		fxMetaDao.delete(entity);
	}

	public FxMeta findObject(FxMeta entity) throws Exception {
		return fxMetaDao.findObject(entity);
	}

	public List<FxMeta> findList(FxMeta entity) throws Exception {
		return fxMetaDao.findList(entity);
	}

	public PageResult<FxMeta> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_meta where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxMetaDao.findList(sqlContext, page, FxMeta.class);
	}

}
