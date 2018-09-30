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
import com.modules.service.PointService;
import com.modules.dao.PointDao;
import com.modules.pojo.Point;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PointServiceImpl implements PointService {

	@Autowired
	private PointDao pointDao;

	public void save(Point entity) throws Exception {
		pointDao.save(entity);
	}

	public void save(List<Point> entities) throws Exception {
		pointDao.save(entities);
	}

	public void update(Point entity) throws Exception {
		pointDao.update(entity);
	}

	public void update(Point newEntity, Point oldEntity) throws Exception {
		pointDao.update(newEntity, oldEntity);
	}

	public void delete(Point entity) throws Exception {
		pointDao.delete(entity);
	}

	public Point findObject(Point entity) throws Exception {
		return pointDao.findObject(entity);
	}

	public List<Point> findList(Point entity) throws Exception {
		return pointDao.findList(entity);
	}

	public PageResult<Point> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_point where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return pointDao.findList(sqlContext, page, Point.class);
	}

}
