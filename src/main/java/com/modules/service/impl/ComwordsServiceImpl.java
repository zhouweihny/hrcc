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
import com.modules.service.ComwordsService;
import com.modules.dao.ComwordsDao;
import com.modules.pojo.Comwords;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ComwordsServiceImpl implements ComwordsService {

	@Autowired
	private ComwordsDao comwordsDao;

	public void save(Comwords entity) throws Exception {
		comwordsDao.save(entity);
	}

	public void save(List<Comwords> entities) throws Exception {
		comwordsDao.save(entities);
	}

	public void update(Comwords entity) throws Exception {
		comwordsDao.update(entity);
	}

	public void update(Comwords newEntity, Comwords oldEntity) throws Exception {
		comwordsDao.update(newEntity, oldEntity);
	}

	public void delete(Comwords entity) throws Exception {
		comwordsDao.delete(entity);
	}

	public Comwords findObject(Comwords entity) throws Exception {
		return comwordsDao.findObject(entity);
	}

	public List<Comwords> findList(Comwords entity) throws Exception {
		return comwordsDao.findList(entity);
	}

	public PageResult<Comwords> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_comwords where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return comwordsDao.findList(sqlContext, page, Comwords.class);
	}

}
