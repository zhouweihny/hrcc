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
import com.modules.service.SysWxMsgService;
import com.modules.dao.SysWxMsgDao;
import com.modules.pojo.SysWxMsg;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxMsgServiceImpl implements SysWxMsgService {

	@Autowired
	private SysWxMsgDao sysWxMsgDao;

	public void save(SysWxMsg entity) throws Exception {
		sysWxMsgDao.save(entity);
	}

	public void save(List<SysWxMsg> entities) throws Exception {
		sysWxMsgDao.save(entities);
	}

	public void update(SysWxMsg entity) throws Exception {
		SysWxMsg sysWxMsg = new SysWxMsg();
		sysWxMsg.setId(entity.getId());
		sysWxMsg = sysWxMsgDao.findObject(sysWxMsg);
		if (!sysWxMsg.getType().equals(entity.getType()) && entity.getType() != null)
			entity.setContent("{}");
		sysWxMsgDao.update(entity);
	}

	public void update(SysWxMsg newEntity, SysWxMsg oldEntity) throws Exception {
		sysWxMsgDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxMsg entity) throws Exception {
		sysWxMsgDao.delete(entity);
	}

	public SysWxMsg findObject(SysWxMsg entity) throws Exception {
		return sysWxMsgDao.findObject(entity);
	}

	public List<SysWxMsg> findList(SysWxMsg entity) throws Exception {
		return sysWxMsgDao.findList(entity);
	}

	public PageResult<SysWxMsg> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_msg where 1=1 ");
		if (params.containsKey("wxid")) {
			sql.append(" and wxid = ?");
			ps.add(params.get("wxid"));
		}
		if (params.containsKey("userid")) {
			sql.append(" and wxid in(select wxid from t_user_wx uw where uw.userid=? )");
			ps.add(params.get("userid"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxMsgDao.findList(sqlContext, page, SysWxMsg.class);
	}

}
