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
import com.modules.service.CmsContentService;
import com.modules.dao.CmsContentDao;
import com.modules.pojo.CmsContent;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CmsContentServiceImpl implements CmsContentService {

	@Autowired
	private CmsContentDao cmsContentDao;

	public void save(CmsContent entity) throws Exception {
		cmsContentDao.save(entity);
	}

	public void save(List<CmsContent> entities) throws Exception {
		cmsContentDao.save(entities);
	}

	public void update(CmsContent entity) throws Exception {
		cmsContentDao.update(entity);
	}

	public void update(CmsContent newEntity, CmsContent oldEntity) throws Exception {
		cmsContentDao.update(newEntity, oldEntity);
	}

	public void delete(CmsContent entity) throws Exception {
		cmsContentDao.delete(entity);
	}

	public CmsContent findObject(CmsContent entity) throws Exception {
		return cmsContentDao.findObject(entity);
	}

	public List<CmsContent> findList(CmsContent entity) throws Exception {
		return cmsContentDao.findList(entity);
	}

	public PageResult<CmsContent> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from cms_content where 1=1 ");
		if (params.containsKey("wxid")) {
			sql.append("   and wxid=?");
			ps.add(params.get("wxid"));
		}
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		if (params.containsKey("status")) {
			sql.append("   and status=?");
			ps.add(params.get("status"));
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return cmsContentDao.findList(sqlContext, page, CmsContent.class);
	}

	public PageResult<CmsContent> findListA(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select id,title,description,author,cover from cms_content where 1=1 ");
		if (params.containsKey("categoryid")) {
			sql.append("   and categoryid=?");
			ps.add(params.get("categoryid"));
		}
		sql.append("	and status='2' order by topdate desc");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return cmsContentDao.findList(sqlContext, page, CmsContent.class);
	}
}
