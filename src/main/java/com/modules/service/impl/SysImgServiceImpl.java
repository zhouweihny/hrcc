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
import com.modules.service.SysImgService;
import com.modules.dao.SysImgDao;
import com.modules.pojo.SysImg;
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysImgServiceImpl implements SysImgService {

	@Autowired
	private SysImgDao sysImgDao;

	public void save(SysImg entity) throws Exception {
		sysImgDao.save(entity);
	}

	public void save(List<SysImg> entities) throws Exception {
		sysImgDao.save(entities);
	}

	public void update(SysImg entity) throws Exception {
		sysImgDao.update(entity);
	}

	public void update(SysImg newEntity, SysImg oldEntity) throws Exception {
		sysImgDao.update(newEntity, oldEntity);
	}

	public void delete(SysImg entity) throws Exception {
		sysImgDao.delete(entity);
	}

	public SysImg findObject(SysImg entity) throws Exception {
		return sysImgDao.findObject(entity);
	}

	public List<SysImg> findList(SysImg entity) throws Exception {
		return sysImgDao.findList(entity);
	}

	public PageResult<SysImg> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from sys_img");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return sysImgDao.findList(sqlContext, page, SysImg.class);
	}

}
