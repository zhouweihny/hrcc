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
import com.modules.service.SysWxQrcodeService;
import com.modules.dao.SysWxQrcodeDao;
import com.modules.pojo.SysWxQrcode;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysWxQrcodeServiceImpl implements SysWxQrcodeService {

	@Autowired
	private SysWxQrcodeDao sysWxQrcodeDao;

	public void save(SysWxQrcode entity) throws Exception {
		sysWxQrcodeDao.save(entity);
	}

	public void save(List<SysWxQrcode> entities) throws Exception {
		sysWxQrcodeDao.save(entities);
	}

	public void update(SysWxQrcode entity) throws Exception {
		sysWxQrcodeDao.update(entity);
	}

	public void update(SysWxQrcode newEntity, SysWxQrcode oldEntity) throws Exception {
		sysWxQrcodeDao.update(newEntity, oldEntity);
	}

	public void delete(SysWxQrcode entity) throws Exception {
		sysWxQrcodeDao.delete(entity);
	}

	public SysWxQrcode findObject(SysWxQrcode entity) throws Exception {
		return sysWxQrcodeDao.findObject(entity);
	}

	public List<SysWxQrcode> findList(SysWxQrcode entity) throws Exception {
		return sysWxQrcodeDao.findList(entity);
	}

	public PageResult<SysWxQrcode> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_wx_qrcode where 1=1 ");
		if (params.containsKey("wxid")) {
			sql.append(" and wxid=?");
			ps.add(params.get("wxid"));
		}
		sql.append(" order by createtime desc");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysWxQrcodeDao.findList(sqlContext, page, SysWxQrcode.class);
	}

}
