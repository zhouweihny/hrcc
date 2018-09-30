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
import com.modules.service.PlanDetailService;
import com.modules.dao.PlanDetailDao;
import com.modules.pojo.PlanDetail;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanDetailServiceImpl implements PlanDetailService {

	@Autowired
	private PlanDetailDao planDetailDao;

	public void save(PlanDetail entity) throws Exception {
		planDetailDao.save(entity);
	}

	public void save(List<PlanDetail> entities) throws Exception {
		planDetailDao.save(entities);
	}

	public void update(PlanDetail entity) throws Exception {
		planDetailDao.update(entity);
	}

	public void update(PlanDetail newEntity, PlanDetail oldEntity) throws Exception {
		planDetailDao.update(newEntity, oldEntity);
	}

	public void delete(PlanDetail entity) throws Exception {
		planDetailDao.delete(entity);
	}

	public PlanDetail findObject(PlanDetail entity) throws Exception {
		return planDetailDao.findObject(entity);
	}

	public List<PlanDetail> findList(PlanDetail entity) throws Exception {
		return planDetailDao.findList(entity);
	}

	public PageResult<PlanDetail> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_plan_detail where 1=1 ");
		if (params.containsKey("planid")) {
			sql.append(" and planid =? ");
			ps.add(params.get("planid"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return planDetailDao.findList(sqlContext, page, PlanDetail.class);
	}

}
