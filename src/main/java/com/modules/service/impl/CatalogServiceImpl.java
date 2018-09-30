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
import com.modules.service.CatalogService;
import com.modules.dao.CatalogDao;
import com.modules.dao.DrugDao;
import com.modules.dao.DrugDrugDao;
import com.modules.dao.SpaceCatalogDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogDao catalogDao;

	@Autowired
	private SpaceCatalogDao spaceCatalogDao;

	@Autowired
	private DrugDrugDao drugdrugDao;

	@Autowired
	private DrugDao drugDao;

	public void save(Catalog entity) throws Exception {
		catalogDao.save(entity);
	}

	public void save(List<Catalog> entities) throws Exception {
		catalogDao.save(entities);
	}

	public void update(Catalog entity) throws Exception {
		catalogDao.update(entity);
	}

	public void update(Catalog newEntity, Catalog oldEntity) throws Exception {
		catalogDao.update(newEntity, oldEntity);
	}

	public void delete(Catalog entity) throws Exception {
		List<Catalog> catalogs = catalogDao.findList(entity);
		StringBuffer ids = new StringBuffer();
		ids.append("''");
		for (Catalog s : catalogs) {
			ids.append(",'").append(s.getId()).append("'");
		}

		SqlContext sqlContext = new SqlContext();
		List<Object> ps = new ArrayList<Object>();

		sqlContext.setSql("delete from t_space_catalog where catalogid in (" + ids + ")");
		sqlContext.setParams(ps.toArray());
		spaceCatalogDao.update(sqlContext);

		sqlContext.setSql("delete from t_drug_drug where drugid in (select id from t_drug  where catalogid in (" + ids + ")) or sdrugid in (select id from t_drug  where catalogid in (" + ids + "))");
		sqlContext.setParams(ps.toArray());
		drugdrugDao.update(sqlContext);

		sqlContext.setSql("delete from t_drug  where catalogid in (" + ids + ")");
		sqlContext.setParams(ps.toArray());
		drugDao.update(sqlContext);

		sqlContext.setSql("delete from t_autocompare  where catalogid in (" + ids + ")");
		sqlContext.setParams(ps.toArray());
		drugDao.update(sqlContext);

		catalogDao.delete(entity);

	}

	public Catalog findObject(Catalog entity) throws Exception {
		return catalogDao.findObject(entity);
	}

	public List<Catalog> findList(Catalog entity) throws Exception {
		return catalogDao.findList(entity);
	}

	public PageResult<Catalog> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_catalog where 1=1 ");
		if (params.containsKey("userid")) {
			sql.append("and userid =? ");
			ps.add(params.get("userid"));
		}

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return catalogDao.findList(sqlContext, page, Catalog.class);
	}

	public void upload(String name, String userid, List<Drug> list) throws Exception {
		Catalog entity = new Catalog();
		entity.setName(name);
		entity.setUserid(userid);
		entity.setMyself(false);
		catalogDao.save(entity);
		for (Drug d : list) {
			d.setCatalogid(entity.getId());
		}
		drugDao.save(list);

	}

	public void upload(String catalogid, List<Drug> list) throws Exception {
		Drug drug;
		for (Drug d : list) {
			d.setCatalogid(catalogid);
			drug = new Drug();
			drug.setCatalogid(catalogid);
			drug.setCode(d.getCode());
			drug = drugDao.findObject(drug);
			if (drug == null) {
				drugDao.save(d);
			} else {
				d.setId(drug.getId());
				drugDao.update(d);
			}
		}

	}
}
