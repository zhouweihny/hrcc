package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.commons.util.UUIDGenerator;
import com.modules.service.DosageformService;
import com.modules.dao.DosageformDao;
import com.modules.pojo.Dosageform;
import com.modules.pojo.Factory;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DosageformServiceImpl implements DosageformService {

	@Autowired
	private DosageformDao dosageformDao;

	public void save(Dosageform entity) throws Exception {
		dosageformDao.save(entity);
	}

	public void save(List<Dosageform> entities) throws Exception {
		dosageformDao.save(entities);
	}

	public void update(Dosageform entity) throws Exception {
		dosageformDao.update(entity);
	}

	public void update(Dosageform newEntity, Dosageform oldEntity) throws Exception {
		dosageformDao.update(newEntity, oldEntity);
	}

	public void delete(Dosageform entity) throws Exception {
		dosageformDao.delete(entity);
	}

	public Dosageform findObject(Dosageform entity) throws Exception {
		return dosageformDao.findObject(entity);
	}

	public List<Dosageform> findList(Dosageform entity) throws Exception {
		return dosageformDao.findList(entity);
	}

	public PageResult<Dosageform> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_dosageform where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return dosageformDao.findList(sqlContext, page, Dosageform.class);
	}

	public void upload(List<String> list) throws Exception {
		List<Dosageform> dosageforms = new ArrayList<Dosageform>();
		StringBuilder strs = new StringBuilder();
		strs.append("''");
		int i = 0;
		for (String str : list) {
			if (!StringUtils.isEmpty(str)) {
				i++;
				Dosageform dosageform = new Dosageform();
				dosageform.setName(str);
				dosageforms.add(dosageform);
				strs.append(",'").append(str).append("'");
			}
		}
		if (i > 1) {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append("select distinct(code) as code from t_dosageform where name  in(").append(strs).append(") ");
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			Factory factory = dosageformDao.findObject(sqlContext, Factory.class);
			String code;
			if (factory == null) {
				code = UUIDGenerator.getUUID();
			} else {
				code = factory.getCode();
			}

			for (Dosageform f : dosageforms) {
				f.setCode(code);
			}
			dosageformDao.save(dosageforms);
		}
	}

	public void removeRepeat() throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("delete from t_dosageform where id not in (select minid from (select min(id) as minid from t_dosageform group by name) b)");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		dosageformDao.update(sqlContext);
	}
}
