package com.modules.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.commons.util.MyStringUtil;
import com.modules.service.DrugService;
import com.modules.dao.CatalogDao;
import com.modules.dao.DrugDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DrugServiceImpl implements DrugService {

	@Autowired
	private DrugDao drugDao;

	@Autowired
	private CatalogDao catalogDao;

	public void save(Drug entity) throws Exception {
		entity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getName())));
		entity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getFactory())));
		if (StringUtils.isBlank(entity.getComnameid()))
			entity.setComnameid("00000");
		drugDao.save(entity);
	}

	public void save(List<Drug> entities) throws Exception {
		for (Drug entity : entities) {
			entity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getName())));
			entity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getFactory())));
			if (StringUtils.isBlank(entity.getComnameid()))
				entity.setComnameid("00000");
		}
		drugDao.save(entities);
	}

	public void update(Drug entity) throws Exception {
		if (StringUtils.isNotBlank(entity.getFactory()))
			entity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getFactory())));
		if (StringUtils.isNotBlank(entity.getName()))
			entity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(entity.getName())));
		drugDao.update(entity);
	}

	public void update(Drug newEntity, Drug oldEntity) throws Exception {
		if (StringUtils.isNotBlank(newEntity.getFactory()))
			newEntity.setFactory(MyStringUtil.filterString(MyStringUtil.ToDBC(newEntity.getFactory())));
		if (StringUtils.isNotBlank(newEntity.getName()))
			newEntity.setName(MyStringUtil.filterString(MyStringUtil.ToDBC(newEntity.getName())));
		drugDao.update(newEntity, oldEntity);
	}

	public void delete(Drug entity) throws Exception {
		drugDao.delete(entity);
	}

	public Drug findObject(Drug entity) throws Exception {
		return drugDao.findObject(entity);
	}

	public List<Drug> findList(Drug entity) throws Exception {
		return drugDao.findList(entity);
	}

	public PageResult<Drug> findList(Map<String, Object> params, Page page) throws Exception {

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug where 1=1 ");
		if (params.containsKey("name")) {
			sql.append(" and  name like ?");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("keyword")) {
			sql.append(" and  (name like ? or code like ?)");
			ps.add("%" + params.get("keyword") + "%");
			ps.add("%" + params.get("keyword") + "%");
		}

		if (params.containsKey("factory")) {
			sql.append(" and  factory like ?");
			ps.add("%" + params.get("factory") + "%");
		}

		if (params.containsKey("catalogid")) {
			sql.append(" and  catalogid = ?");
			ps.add(params.get("catalogid"));
		}

		if (params.containsKey("comnameuserid")) {
			sql.append(" and ( comnameuserid = ? or  not exists (select id from fx_comname where t_drug.comnameid=fx_comname.id))");
			ps.add(params.get("comnameuserid"));
		}

		if (params.containsKey("dz")) {
			if (params.get("dz").equals("1")) {
				sql.append(" and   comnameid  in (select id from fx_comname)");
			} else {
				sql.append(" and  not exists (select id from fx_comname where t_drug.comnameid=fx_comname.id)  ");
			}
		} else {
			if (params.containsKey("treeid")) {
				sql.append(" and   comnameid not  in (select fx_comname_tree.comnameid from fx_comname_tree ,fx_tree where fx_comname_tree.treeid=fx_tree.id and fx_tree.path like  ? )");
				ps.add(params.get("treeid") + "%");
			}
		}

		if (params.containsKey("username")) {
			SqlContext sqlContext2 = new SqlContext();
			StringBuilder sql2 = new StringBuilder();
			List<Object> ps2 = new ArrayList<Object>();
			ps2.add("%" + params.get("username") + "%");
			sql2.append("	select  * from t_catalog where userid in (select id from t_user where username like ? )");
			sqlContext2.setSql(sql2.toString());
			sqlContext2.setParams(ps2.toArray());
			List<Catalog> catalogs = catalogDao.findList(sqlContext2, Catalog.class);
			sql.append(" and  catalogid in (''  ");
			for (Catalog c : catalogs) {
				sql.append(",'").append(c.getId()).append("'");
			}
			sql.append(" ) ");
		}

		if (params.containsKey("userid")) {
			Catalog catalog = new Catalog();
			catalog.setUserid(params.get("userid").toString());
			List<Catalog> list = catalogDao.findList(catalog);
			StringBuffer str = new StringBuffer();
			str.append("(''");
			for (Catalog c : list) {
				str.append(",'").append(c.getId()).append("'");
			}
			str.append(")");
			sql.append(" and  catalogid in  ").append(str);
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugDao.findList(sqlContext, page, Drug.class);
	}

	@Override
	public void removeRepeat(String catalogid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("delete from t_drug where catalogid=? and id not in (select id from (select max(id) as id from t_drug where catalogid=? group by code) b)");
		ps.add(catalogid);
		ps.add(catalogid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugDao.update(sqlContext);
	}

	@Override
	public void tempTo(String catalogid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();

		sql.append(
				"insert into t_drug (id, code, catalogid,name, specifications,factory ,unit,dosageform) select id, code, catalogid,name, specifications,factory ,unit,dosageform from t_drug_temp where catalogid=? and id not in( select id from t_drug where catalogid = ?)group by id, code ,name, specifications,factory ,unit,dosageform");
		ps.add(catalogid);
		ps.add(catalogid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugDao.update(sqlContext);

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append(" delete from t_drug_temp where catalogid=?  ");
		ps.add(catalogid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugDao.update(sqlContext);

	}

	@Override
	public PageResult<Drug> findnList(Map<String, Object> params, Page page) throws Exception {
		// TODO Auto-generated method stub

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug where 1=1 and not EXISTS (select * from t_factory where name=factory)");

		if (params.containsKey("name")) {
			sql.append(" and  name like ?");
			ps.add("%" + params.get("name") + "%");
		}

		if (params.containsKey("factory")) {
			sql.append(" and  factory like ?");
			ps.add("%" + params.get("factory") + "%");
		}

		if (params.containsKey("catalogid")) {
			sql.append(" and  catalogid = ?");
			ps.add(params.get("catalogid"));
		}

		if (params.containsKey("userid")) {
			Catalog catalog = new Catalog();
			catalog.setUserid(params.get("userid").toString());
			List<Catalog> list = catalogDao.findList(catalog);
			StringBuffer str = new StringBuffer();
			str.append("(''");
			for (Catalog c : list) {
				str.append(",'").append(c.getId()).append("'");
			}
			str.append(")");
			sql.append(" and  catalogid in  ").append(str);
		}

		sql.append(" order by code asc ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugDao.findList(sqlContext, page, Drug.class);
	}

	public List<Drug> findNoComnameList(String userid) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_drug where 1=1 ");
		sql.append(" and  not exists (select id from fx_comname where t_drug.comnameid=fx_comname.id)  ");
		sql.append(" and  catalogid in (select  id from t_catalog where userid  =? )");
		ps.add(userid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return drugDao.findList(sqlContext, Drug.class);
	}

	@Override
	public void updateGgNPpflag(String catalogid) throws Exception {
		// TODO Auto-generated method stub

		// 更新广告品种
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("update t_drug set  ggflag=1 where id in(select * from( ");
		sql.append(" select  d.id  from  t_drug d, fx_comname_tree_factory ct,fx_tree t     ");
		sql.append(" where  d.comnameid=ct.comnameid and ct.treeid=t.id  and  d.factory like concat('%', ct.factory, '%')  ");
		sql.append("    and d.catalogid=?     and  t.path like '36A8746039AB40C39D0B7CD75DE45650/C8A8A96AE271431AB1C620BB65E813D1%' )a ) ");
		ps.add(catalogid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugDao.update(sqlContext);

		// 更新牌品种
		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append("update t_drug set  ppflag=1 where id in(select * from( ");
		sql.append(" select  d.id  from  t_drug d, fx_comname_tree_factory ct,fx_tree t     ");
		sql.append(" where  d.comnameid=ct.comnameid and ct.treeid=t.id  and  d.factory like concat('%', ct.factory, '%')  ");
		sql.append("    and d.catalogid=?     and  t.path like '36A8746039AB40C39D0B7CD75DE45650/F7427790DBAB47E5BE2F2A7A518DFAEF%' )a ) ");
		ps.add(catalogid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		drugDao.update(sqlContext);

	}
}
