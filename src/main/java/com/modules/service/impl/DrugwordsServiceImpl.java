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
import com.modules.service.DrugwordsService;
import com.modules.dao.DrugwordsDao;
import com.modules.pojo.Drugwords;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugwordsServiceImpl implements DrugwordsService {

	@Autowired
	private DrugwordsDao drugwordsDao;

	public void save(Drugwords entity) throws Exception {
		drugwordsDao.save(entity);
	}

	public void save(List<Drugwords> entities) throws Exception {
		drugwordsDao.save(entities);
	}

	public void update(Drugwords entity) throws Exception {
		drugwordsDao.update(entity);
	}

	public void update(Drugwords newEntity, Drugwords oldEntity) throws Exception {
		drugwordsDao.update(newEntity, oldEntity);
	}

	public void delete(Drugwords entity) throws Exception {
		drugwordsDao.delete(entity);
	}

	public Drugwords findObject(Drugwords entity) throws Exception {
		return drugwordsDao.findObject(entity);
	}

	public List<Drugwords> findList(Drugwords entity) throws Exception {
		return drugwordsDao.findList(entity);
	}

	public PageResult<Drugwords> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drugwords where 1=1 ");
		if (params.containsKey("name")) {
			sql.append(" and  name like ?");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("code")) {
			sql.append(" and  code like ?");
			ps.add("%" + params.get("code") + "%");
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugwordsDao.findList(sqlContext, page, Drugwords.class);
	}

	public void upload(String userid, List<String> list) throws Exception {
		List<Drugwords> drugwords = new ArrayList<Drugwords>();
		StringBuilder strs = new StringBuilder();
		strs.append("''");
		int i = 0;
		for (String str : list) {
			if (!StringUtils.isEmpty(str)) {
				i++;
				Drugwords word = new Drugwords();
				word.setName(str);
				drugwords.add(word);
				strs.append(",'").append(str).append("'");
			}
		}
		if (i > 1) {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append("select distinct(code) as code from t_drugwords where name  in(").append(strs).append(") ");
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			Drugwords drugword = drugwordsDao.findObject(sqlContext, Drugwords.class);
			String code;
			if (drugword == null) {
				code = UUIDGenerator.getUUID();
			} else {
				code = drugword.getCode();
			}

			for (Drugwords f : drugwords) {
				f.setCode(code);
				f.setOperatorid(userid);
			}
			drugwordsDao.save(drugwords);
		}
	}

	public void removeRepeat() throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("delete from t_drugwords where id not in (select minid from (select min(id) as minid from t_drugwords group by name) b)");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugwordsDao.update(sqlContext);
	}
}
