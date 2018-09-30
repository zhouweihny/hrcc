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
import com.modules.service.SysWxMaterialTwService;
import com.modules.dao.SysWxMaterialTwDao;
import com.modules.pojo.SysWxMaterialTw;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxMaterialTwServiceImpl implements SysWxMaterialTwService {

	@Autowired
	private SysWxMaterialTwDao sysWxMaterialTwDao;

	public void save(SysWxMaterialTw entity) throws Exception {
		sysWxMaterialTwDao.save(entity);
	}

	public void save(List<SysWxMaterialTw> entities) throws Exception {
		sysWxMaterialTwDao.save(entities);
	}

	public void update(SysWxMaterialTw entity) throws Exception {
		sysWxMaterialTwDao.update(entity);
	}

	public void update(SysWxMaterialTw newEntity, SysWxMaterialTw oldEntity) throws Exception {
		sysWxMaterialTwDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxMaterialTw entity) throws Exception {
		sysWxMaterialTwDao.delete(entity);
	}

	public SysWxMaterialTw findObject(SysWxMaterialTw entity) throws Exception {
		return sysWxMaterialTwDao.findObject(entity);
	}

	public List<SysWxMaterialTw> findList(SysWxMaterialTw entity) throws Exception {
		return sysWxMaterialTwDao.findList(entity);
	}

	public PageResult<SysWxMaterialTw> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_material_tw where 1=1 ");
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxMaterialTwDao.findList(sqlContext, page, SysWxMaterialTw.class);
	}

}
