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
import com.commons.util.KeyWordsUtil;
import com.modules.service.DrugwordsService;
import com.modules.service.FxComnameService;
import com.modules.service.KeywordsService;
import com.modules.dao.KeywordsDao;
import com.modules.pojo.Drugwords;
import com.modules.pojo.FxComname;
import com.modules.pojo.Keywords;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KeywordsServiceImpl implements KeywordsService {

	@Autowired
	private KeywordsDao keywordsDao;

	@Autowired
	private FxComnameService fxComnameService;

	@Autowired
	private DrugwordsService drugwordsService;

	public void save(Keywords entity) throws Exception {
		keywordsDao.save(entity);
	}

	public void save(List<Keywords> entities) throws Exception {
		keywordsDao.save(entities);
	}

	public void update(Keywords entity) throws Exception {
		keywordsDao.update(entity);
	}

	public void update(Keywords newEntity, Keywords oldEntity) throws Exception {
		keywordsDao.update(newEntity, oldEntity);
	}

	public void delete(Keywords entity) throws Exception {
		keywordsDao.delete(entity);
	}

	public Keywords findObject(Keywords entity) throws Exception {
		return keywordsDao.findObject(entity);
	}

	public List<Keywords> findList(Keywords entity) throws Exception {
		return keywordsDao.findList(entity);
	}

	public PageResult<Keywords> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_keywords where 1=1 ");
		if (params.containsKey("word")) {
			sql.append(" and word like ?");
			ps.add("%" + params.get("word") + "%");
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return keywordsDao.findList(sqlContext, page, Keywords.class);
	}

	@Override
	public void upload(String userid, List<String> list) throws Exception {
		List<Keywords> keywords = new ArrayList<Keywords>();
		for (String str : list) {
			if (!StringUtils.isEmpty(str)) {
				Keywords keyword = new Keywords();
				keyword.setWord(str);
				keyword.setOperatorid(userid);
				keywords.add(keyword);
			}
		}
		if (keywords.size() > 0)
			keywordsDao.save(keywords);

	}

	@Override
	public void removeRepeat() throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("delete from t_keywords where id not in (select minid from (select min(id) as minid from t_keywords group by word) b)");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		keywordsDao.update(sqlContext);
	}

	@Override
	public void refreshIK() throws Exception {
		// TODO Auto-generated method stub
		// Configuration cfg = new MyConfiguration(); // 加载词库
		// cfg.setUseSmart(false); // 设置智能分词
		// Dictionary.initial(cfg);
		// Dictionary dictionary = Dictionary.getSingleton();
		List<Keywords> list = keywordsDao.findList(new Keywords());
		KeyWordsUtil.namewords.clear();

		FxComname comname = new FxComname();
		List<FxComname> comnames = fxComnameService.findList(comname);

		// List<String> words = new ArrayList<String>();
		for (Keywords keyword : list) {
			// words.add(keyword.getWord());
			KeyWordsUtil.namewords.add(keyword.getWord());
		}

		for (FxComname c : comnames) {
			KeyWordsUtil.namewords.add(c.getName());
		}

		List<Drugwords> drugwords = drugwordsService.findList(new Drugwords());

		for (Drugwords d : drugwords) {
			KeyWordsUtil.namewords.add(d.getName());
		}

		// dictionary.addWords(words); // 自动添加自定义词
	}
}
