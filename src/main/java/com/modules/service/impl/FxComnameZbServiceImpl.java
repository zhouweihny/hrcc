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
import com.modules.service.FxComnameZbService;
import com.modules.dao.FxComnameZbDao;
import com.modules.pojo.FxComnameZb;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxComnameZbServiceImpl implements FxComnameZbService {

	@Autowired
	private FxComnameZbDao fxComnameZbDao;

	public void save(FxComnameZb entity) throws Exception {
		fxComnameZbDao.save(entity);
	}

	public void save(List<FxComnameZb> entities) throws Exception {
		fxComnameZbDao.save(entities);
	}

	public void update(FxComnameZb entity) throws Exception {
		fxComnameZbDao.update(entity);
	}

	public void update(FxComnameZb newEntity, FxComnameZb oldEntity) throws Exception {
		fxComnameZbDao.update(newEntity, oldEntity);
	}

	public void delete(FxComnameZb entity) throws Exception {
		fxComnameZbDao.delete(entity);
	}

	public FxComnameZb findObject(FxComnameZb entity) throws Exception {
		return fxComnameZbDao.findObject(entity);
	}

	public List<FxComnameZb> findList(FxComnameZb entity) throws Exception {
		return fxComnameZbDao.findList(entity);
	}

	public PageResult<FxComnameZb> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_comname_zb where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxComnameZbDao.findList(sqlContext, page, FxComnameZb.class);
	}

}
