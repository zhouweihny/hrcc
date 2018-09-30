package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.commons.util.KeyWordsUtil;
import com.commons.util.MyStringUtil;
import com.modules.service.DrugwordsService;
import com.modules.service.FxComnameService;
import com.modules.vo.ComnameVo;
import com.modules.vo.DrugVo;
import com.modules.dao.ComwordsDao;
import com.modules.dao.DrugDao;
import com.modules.dao.FxComnameDao;
import com.modules.dao.FxTreeDao;
import com.modules.pojo.Comwords;
import com.modules.pojo.Drug;
import com.modules.pojo.Drugwords;
import com.modules.pojo.Factory;
import com.modules.pojo.FxComname;
import com.modules.pojo.FxTree;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxComnameServiceImpl implements FxComnameService {

	@Autowired
	private FxComnameDao fxComnameDao;

	@Autowired
	private DrugDao drugDao;

	@Autowired
	private FxTreeDao fxTreeDao;

	@Autowired
	private ComwordsDao comwordsDao;

	@Autowired
	private DrugwordsService drugwordsService;

	public void save(FxComname entity) throws Exception {
		fxComnameDao.save(entity);
	}

	public void save(List<FxComname> entities) throws Exception {
		fxComnameDao.save(entities);
	}

	public void update(FxComname entity) throws Exception {
		fxComnameDao.update(entity);
	}

	public void update(FxComname newEntity, FxComname oldEntity) throws Exception {
		fxComnameDao.update(newEntity, oldEntity);
	}

	public void delete(FxComname entity) throws Exception {
		fxComnameDao.delete(entity);
	}

	public FxComname findObject(FxComname entity) throws Exception {
		return fxComnameDao.findObject(entity);
	}

	public List<FxComname> findList(FxComname entity) throws Exception {
		return fxComnameDao.findList(entity);
	}

	public PageResult<FxComname> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_comname where 1=1 ");

		if (params.containsKey("name")) {
			sql.append(" and name like ? ");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxComnameDao.findList(sqlContext, page, FxComname.class);
	}

	@Override
	public String compareDrug(String name) throws Exception {

		// 传药品id 系统自动匹配近似值
		// Drug drug = new Drug();
		// drug.setId(drugid);
		// drug = drugDao.findObject(drug);
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		List<String> words = KeyWordsUtil.segDrugname(name, false);

		if (words.indexOf(name) < 0) {
			words.add(name);
		}

		sql.append(" select * from t_drugwords where code in (select code from t_drugwords where name in(");
		for (int i = 0; i < words.size(); i++) {
			if (i == 0)
				sql.append("?");
			else
				sql.append(",?");
		}

		sql.append(") ) ");
		ps.addAll(words);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<Drugwords> drugwords = drugDao.findList(sqlContext, Drugwords.class);
		sql.delete(0, sql.length());
		ps.clear();

		for (Drugwords d : drugwords) {
			if (words.indexOf(d.getName()) < 0)
				words.add(d.getName());
		}

		sql.delete(0, sql.length());
		ps.clear();
		sql.append("select id,name ,count(1) as num from (  ");
		for (String w : words) {
			if (w.length() != 1) {
				sql.append(" select * from fx_comname where     name like ?   union all");
				ps.add("%" + w + "%");
			}
		}

		sql.delete(sql.length() - 9, sql.length());
		sql.append(") as a GROUP BY  id,name order  by count(1) desc ");
		// ps.add(count);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		PageResult<FxComname> datavo = drugDao.findList(sqlContext, new Page(false), FxComname.class);
		if (datavo.getData().size() == 0) {
			return null;
		}

		for (FxComname comname : datavo.getData()) {
			if (name.equals(comname.getName()))
				return comname.getId();
		}

		sql.delete(0, sql.length());
		ps.clear();
		for (Drugwords d : drugwords) {
			if (words.indexOf(d.getName()) < 0)
				words.add(d.getName());
		}

		Comwords comword = null;

		if (datavo.getData().size() > 0) {
			sql.delete(0, sql.length());
			ps.clear();
			sql.append(" select * from t_comwords where  1=1    ");
			for (FxComname comname : datavo.getData()) {
				if (name.contains(comname.getName())) {
					sql.append(" and words like ?   ");
					ps.add("%" + comname.getName() + "%");
				}
			}
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			comword = drugDao.findObject(sqlContext, Comwords.class);
		}

		if (comword != null) {
			for (FxComname comname : datavo.getData()) {
				if (comword.getWord().equals(comname.getName()))
					return comname.getId();
			}
		}

		for (FxComname comname : datavo.getData()) {
			if (name.contains(comname.getName())) {
				return comname.getId();
			}
		}

		for (FxComname comname : datavo.getData()) {
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append(" select * from t_drugwords where code in (select code from t_drugwords where name =?)");
			ps.add(comname.getName());
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			drugwords = drugDao.findList(sqlContext, Drugwords.class);
			for (Drugwords d : drugwords) {
				if (name.contains(d.getName())) {
					return comname.getId();
				}
			}
		}

		return datavo.getData().get(0).getId();
	}

	public void bandingAddComname(String drugid, String comnameid) throws Exception {

		// 传药品id 系统自动匹配近似值
		Drug drug = new Drug();
		drug.setId(drugid);
		drug = drugDao.findObject(drug);
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		Comwords comword = null;
		List<FxComname> comnames = fxComnameDao.findList(new FxComname());
		List<String> coms = new ArrayList<String>();

		String usedcomname = "";

		for (FxComname com : comnames) {
			if (drug.getName().contains(com.getName()))
				coms.add(com.getName());
			if (com.getId().equals(comnameid))
				usedcomname = com.getName();

		}

		if (coms.size() > 1) {
			String words = "";

			sql.append(" select * from t_comwords where  1=1    ");
			for (String comname : coms) {
				if (drug.getName().contains(comname)) {
					sql.append(" and words like ?   ");
					ps.add("%" + comname + "%");
					words = words + comname + "/";
				}
			}
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			comword = drugDao.findObject(sqlContext, Comwords.class);
			if (comword == null || comword.getWords().split("/").length != coms.size()) {
				Comwords comwords = new Comwords();
				comwords.setWords(words.substring(0, words.length() - 1));
				comwords.setWord(usedcomname);
				comwordsDao.save(comwords);
			} else if (comword != null && comword.getWords().split("/").length == coms.size()) {
				comword.setWord(usedcomname);
				comwordsDao.update(comword);
			}
		}
	}

	@Override
	public List<FxComname> findBytreeid(String treeid) throws Exception {
		// TODO Auto-generated method stub

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select * from fx_comname where id in (select comnameid from fx_comname_tree where treeid=?)");
		ps.add(treeid);

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<FxComname> comnames = fxComnameDao.findList(sqlContext, FxComname.class);
		return comnames;
	}

	@Override
	public PageResult<ComnameVo> findByTreePath(String treeid, String rootid, String name, Integer status, Integer currentPage, Integer pageSize) throws Exception {

		Page page = new Page(currentPage, pageSize);

		if (status == null) {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append(" select c.*,ct.fwflag from fx_comname c,fx_comname_tree ct where c.id=ct.comnameid and   ct.treeid=? ");
			ps.add(treeid);
			if (StringUtils.isNotBlank(name)) {
				sql.append(" and name like ?   ");
				ps.add("%" + name + "%");
			}
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			return fxComnameDao.findList(sqlContext, page, ComnameVo.class);
		}

		FxTree fxTree = new FxTree();
		fxTree.setId(treeid);
		fxTree = fxTreeDao.findObject(fxTree);

		// TODO Auto-generated method stub
		if (0 == status) {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append("select * from fx_comname where id  not in(select ct.comnameid from fx_comname_tree ct,fx_tree t where ct.treeid=t.id and t.path  like  ? )  ");
			if (rootid.equals("654CA2E6C2164A148F287B57A4483AF7")) {
				ps.add(rootid + "%");
			} else {
				ps.add(fxTree.getPath() + "%");
			}
			if (StringUtils.isNotBlank(name)) {
				sql.append(" and name like ?   ");
				ps.add("%" + name + "%");
			}
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			return fxComnameDao.findList(sqlContext, page, ComnameVo.class);
		} else {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append(" select c.*,ct.fwflag from fx_comname c,fx_comname_tree ct where c.id=ct.comnameid and ct.treeid=? ");
			ps.add(treeid);
			if (StringUtils.isNotBlank(name)) {
				sql.append(" and name like ?   ");
				ps.add("%" + name + "%");
			}
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			return fxComnameDao.findList(sqlContext, page, ComnameVo.class);
		}
	}

	public void updateScore() throws Exception {

		List<FxComname> comnames = fxComnameDao.findList(new FxComname());
		this.countScore(comnames);
		for (FxComname comname : comnames) {
			fxComnameDao.update(comname);
		}
	}

	private List<FxComname> countScore(List<FxComname> dvs) {
		Collections.sort(dvs, new Comparator<FxComname>() {
			@Override
			public int compare(FxComname o1, FxComname o2) {
				return -o1.getXse().compareTo(o2.getXse());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			FxComname dv = dvs.get(i - 1);
			dv.setPf(2 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<FxComname>() {
			@Override
			public int compare(FxComname o1, FxComname o2) {
				return -o1.getPc().compareTo(o2.getPc());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			FxComname dv = dvs.get(i - 1);
			dv.setPf(dv.getPf() + 5 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<FxComname>() {
			@Override
			public int compare(FxComname o1, FxComname o2) {
				return -o1.getMl().compareTo(o2.getMl());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			FxComname dv = dvs.get(i - 1);
			dv.setPf(dv.getPf() + 2 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		return dvs;
	}

}
