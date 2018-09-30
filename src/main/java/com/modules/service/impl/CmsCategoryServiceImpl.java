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
import com.modules.service.CmsCategoryService;
import com.modules.dao.CmsCategoryDao;
import com.modules.pojo.CmsCategory;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CmsCategoryServiceImpl implements CmsCategoryService {

	@Autowired
	private CmsCategoryDao cmsCategoryDao;

	public void save(CmsCategory entity) throws Exception {
		cmsCategoryDao.save(entity);
	}

	public void save(List<CmsCategory> entities) throws Exception {
		cmsCategoryDao.save(entities);
	}

	public void update(CmsCategory entity) throws Exception {
		cmsCategoryDao.update(entity);
	}

	public void update(CmsCategory newEntity, CmsCategory oldEntity) throws Exception {
		cmsCategoryDao.update(newEntity, oldEntity);
	}

	public void delete(CmsCategory entity) throws Exception {
		cmsCategoryDao.delete(entity);
	}

	public CmsCategory findObject(CmsCategory entity) throws Exception {
		return cmsCategoryDao.findObject(entity);
	}

	public List<CmsCategory> findList(CmsCategory entity) throws Exception {
		return cmsCategoryDao.findList(entity);
	}

	public PageResult<CmsCategory> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from cms_category where 1=1 ");
		if (params.containsKey("wxid")) {
			sql.append(" and wxid=?");
			ps.add(params.get("wxid"));
		}
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return cmsCategoryDao.findList(sqlContext, page, CmsCategory.class);
	}

}
