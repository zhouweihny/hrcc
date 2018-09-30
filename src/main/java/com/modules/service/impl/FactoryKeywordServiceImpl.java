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
import com.commons.util.KeyWordsUtil;
import com.modules.service.FactoryKeywordService;
import com.modules.vo.FactoryKeywordVo;
import com.modules.dao.FactoryKeywordDao;
import com.modules.pojo.FactoryKeyword;
import com.modules.pojo.Keywords;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FactoryKeywordServiceImpl implements FactoryKeywordService {

	@Autowired
	private FactoryKeywordDao factoryKeywordDao;

	public void save(FactoryKeyword entity) throws Exception {
		factoryKeywordDao.save(entity);

		this.refreshIK();
	}

	public void save(List<FactoryKeyword> entities) throws Exception {
		factoryKeywordDao.save(entities);
		this.refreshIK();
	}

	public void update(FactoryKeyword entity) throws Exception {
		factoryKeywordDao.update(entity);
		this.refreshIK();
	}

	public void update(FactoryKeyword newEntity, FactoryKeyword oldEntity) throws Exception {
		factoryKeywordDao.update(newEntity, oldEntity);
		this.refreshIK();
	}

	public void delete(FactoryKeyword entity) throws Exception {
		factoryKeywordDao.delete(entity);
		this.refreshIK();
	}

	public FactoryKeyword findObject(FactoryKeyword entity) throws Exception {
		return factoryKeywordDao.findObject(entity);
	}

	public List<FactoryKeyword> findList(FactoryKeyword entity) throws Exception {
		return factoryKeywordDao.findList(entity);
	}

	public PageResult<FactoryKeyword> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_factory_keyword where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return factoryKeywordDao.findList(sqlContext, page, FactoryKeyword.class);
	}

	@Override
	public PageResult<FactoryKeywordVo> findFactoryKeywordVos(Map<String, Object> params, Page page) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"SELECT	a.CODE,factorys,fk.word FROM (SELECT CODE,group_concat(NAME) AS factorys	FROM t_factory GROUP BY CODE) AS a LEFT JOIN (SELECT CODE, group_concat(word) AS  word FROM	t_factory_keyword	GROUP BY CODE) fk ON a.CODE = fk.CODE WHERE 1 = 1 ");
		if (params.containsKey("status")) {
			if (params.get("status").equals("1"))
				sql.append("and fk.word is not null ");
			else if (params.get("status").equals("0"))
				sql.append("and fk.word is   null ");
		}
		if (params.containsKey("word")) {
			sql.append("and fk.word like  ? ");
			ps.add("%" + params.get("word") + "%");

		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return factoryKeywordDao.findList(sqlContext, page, FactoryKeywordVo.class);
	}

	@Override
	public void refreshIK() throws Exception {
		// Dictionary dictionary = Dictionary.getSingleton();
		List<FactoryKeyword> list = factoryKeywordDao.findList(new FactoryKeyword());
		KeyWordsUtil.factoryKeywords.clear();
		// List<String> words = new ArrayList<String>();
		for (FactoryKeyword keyword : list) {
			// words.add(keyword.getWord());
			KeyWordsUtil.factoryKeywords.add(keyword.getWord());
		}

	}

}
