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
import com.modules.service.SysWxUserQrcodeService;
import com.modules.dao.SysWxUserQrcodeDao;
import com.modules.pojo.SysWxUserQrcode;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxUserQrcodeServiceImpl implements SysWxUserQrcodeService {

	@Autowired
	private SysWxUserQrcodeDao sysWxUserQrcodeDao;

	public void save(SysWxUserQrcode entity) throws Exception {
		sysWxUserQrcodeDao.save(entity);
	}

	public void save(List<SysWxUserQrcode> entities) throws Exception {
		sysWxUserQrcodeDao.save(entities);
	}

	public void update(SysWxUserQrcode entity) throws Exception {
		sysWxUserQrcodeDao.update(entity);
	}

	public void update(SysWxUserQrcode newEntity, SysWxUserQrcode oldEntity) throws Exception {
		sysWxUserQrcodeDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxUserQrcode entity) throws Exception {
		sysWxUserQrcodeDao.delete(entity);
	}

	public SysWxUserQrcode findObject(SysWxUserQrcode entity) throws Exception {
		return sysWxUserQrcodeDao.findObject(entity);
	}

	public List<SysWxUserQrcode> findList(SysWxUserQrcode entity) throws Exception {
		return sysWxUserQrcodeDao.findList(entity);
	}

	public PageResult<SysWxUserQrcode> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_user_qrcode where 1=1 ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxUserQrcodeDao.findList(sqlContext, page, SysWxUserQrcode.class);
	}

}
