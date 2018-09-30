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
import com.modules.service.SysWxMaterialImgService;
import com.modules.dao.SysWxMaterialImgDao;
import com.modules.pojo.SysWxMaterialImg;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxMaterialImgServiceImpl implements SysWxMaterialImgService {

	@Autowired
	private SysWxMaterialImgDao sysWxMaterialImgDao;

	public void save(SysWxMaterialImg entity) throws Exception {
		sysWxMaterialImgDao.save(entity);
	}

	public void save(List<SysWxMaterialImg> entities) throws Exception {
		sysWxMaterialImgDao.save(entities);
	}

	public void update(SysWxMaterialImg entity) throws Exception {
		sysWxMaterialImgDao.update(entity);
	}

	public void update(SysWxMaterialImg newEntity, SysWxMaterialImg oldEntity) throws Exception {
		sysWxMaterialImgDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxMaterialImg entity) throws Exception {
		sysWxMaterialImgDao.delete(entity);
	}

	public SysWxMaterialImg findObject(SysWxMaterialImg entity) throws Exception {
		return sysWxMaterialImgDao.findObject(entity);
	}

	public List<SysWxMaterialImg> findList(SysWxMaterialImg entity) throws Exception {
		return sysWxMaterialImgDao.findList(entity);
	}

	public PageResult<SysWxMaterialImg> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_material_img where 1=1 ");
		if (params.containsKey("wxid")) {
			sql.append("  and  wxid=? ");
			ps.add(params.get("wxid"));
		}
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxMaterialImgDao.findList(sqlContext, page, SysWxMaterialImg.class);
	}

}
