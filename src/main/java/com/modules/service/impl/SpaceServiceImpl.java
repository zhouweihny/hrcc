package com.modules.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
import com.commons.util.MyStringUtil;
import com.modules.service.SpaceService;
import com.modules.vo.DrugDrugVo;
import com.modules.vo.DrugVo;
import com.modules.vo.IerData;
import com.modules.dao.DrugDao;
import com.modules.dao.DrugDrugDao;
import com.modules.dao.FactoryDao;
import com.modules.dao.SpaceCatalogDao;
import com.modules.dao.SpaceDao;
import com.modules.pojo.Drug;
import com.modules.pojo.Drugwords;
import com.modules.pojo.Factory;
import com.modules.pojo.Sdrug;
import com.modules.pojo.Space;
import com.modules.pojo.SpaceCatalog;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpaceServiceImpl implements SpaceService {

	@Autowired
	private SpaceDao spaceDao;

	@Autowired
	private FactoryDao factoryDao;

	@Autowired
	private DrugDao drugDao;

	@Autowired
	private DrugDrugDao drugDrugDao;

	@Autowired
	private SpaceCatalogDao spaceCatalogDao;

	public void save(Space entity) throws Exception {
		spaceDao.save(entity);
	}

	public void save(List<Space> entities) throws Exception {
		spaceDao.save(entities);
	}

	public void update(Space entity) throws Exception {
		spaceDao.update(entity);
	}

	public void update(Space newEntity, Space oldEntity) throws Exception {
		spaceDao.update(newEntity, oldEntity);
	}

	public void delete(Space entity) throws Exception {
		List<Space> spaces = spaceDao.findList(entity);
		StringBuffer ids = new StringBuffer();
		ids.append("''");
		for (Space s : spaces) {
			ids.append(",'").append(s.getId()).append("'");
		}
		SqlContext sqlContext = new SqlContext();
		List<Object> ps = new ArrayList<Object>();
		sqlContext.setSql("delete from t_drug_drug where spaceid in (" + ids + ")");
		sqlContext.setParams(ps.toArray());
		drugDrugDao.update(sqlContext);

		sqlContext.setSql("delete from t_space_catalog where spaceid in (" + ids + ")");
		sqlContext.setParams(ps.toArray());
		spaceCatalogDao.update(sqlContext);

		spaceDao.delete(entity);
	}

	public Space findObject(Space entity) throws Exception {
		return spaceDao.findObject(entity);
	}

	public List<Space> findList(Space entity) throws Exception {
		return spaceDao.findList(entity);
	}

	public PageResult<Space> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from t_space where 1=1 ");
		if (params.containsKey("userid")) {
			sql.append("and userid =? ");
			ps.add(params.get("userid"));
		}
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return spaceDao.findList(sqlContext, page, Space.class);
	}

	@Override
	public PageResult<DrugVo> findDrugs(Map<String, Object> params, Page page) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from  (select t.* , case when  dd.num=0 then 0 when dd.num=1 then 1 else -1 end  as compared  ,dd.sdrugid as compareid ,dd.checked as checked");
		if (params.containsKey("showcompared")) {
			if (params.get("showcompared").equals(true)) {
				sql.append(
						" ,d.code as comparecode,d.common ascomparecommon ,d.name as comparename, d.specifications as comparespecifications , d.unit as compareunit,d.dosageform as comparedosageform,d.factory as comparefactory ");
			}
		}

		sql.append(" from t_drug t left join t_drug_drug dd on dd.drugid=t.id and dd.spaceid=?  ");

		if (params.containsKey("showcompared")) {
			if (params.get("showcompared").equals(true)) {
				sql.append("	left join  t_drug d on d.id=dd.sdrugid ");
			}
		}

		sql.append(" where t.catalogid =? ");

		ps.add(params.get("spaceid"));
		ps.add(params.get("catalogid"));

		if (params.containsKey("code")) {
			sql.append(" and t.code  like ? ");
			ps.add("%" + params.get("code") + "%");
		}

		if (params.containsKey("name")) {
			sql.append(" and t.name  like ? ");
			ps.add("%" + params.get("name") + "%");
		}
		if (params.containsKey("factory")) {
			sql.append(" and  t.factory like ? ");
			ps.add("%" + params.get("factory") + "%");
		}
		sql.append(" order by  code asc");
		sql.append(" ) as b where 1=1 ");
		if (params.containsKey("compared")) {
			sql.append(" and compared =? ");
			ps.add(params.get("compared"));
		}

		if (params.containsKey("checked")) {
			sql.append(" and checked =? ");
			ps.add(params.get("checked"));
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return spaceDao.findList(sqlContext, page, DrugVo.class);

	}

	@Override
	public PageResult<Drug> findcomparedrugs(Map<String, Object> params, Page page) throws Exception {
		SpaceCatalog spaceCatalog = new SpaceCatalog();
		spaceCatalog.setSpaceid(params.get("spaceid").toString());
		spaceCatalog.setStandard(true);
		spaceCatalog = spaceCatalogDao.findObject(spaceCatalog);

		if (params.containsKey("drugid")) { // 传药品id 系统自动匹配近似值
			// TODO Auto-generated method stub

			PageResult<Drug> data = new PageResult<Drug>();
			data.setCurrentPage(1);
			data.setTotalPages(1);
			data.setTotalRows(1);
			data.setData(new ArrayList<Drug>());
			Integer numflag = 0;

			Drug drug = new Drug();
			drug.setId(params.get("drugid").toString());
			drug = drugDao.findObject(drug);

			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append(" select * from t_factory where code= (select min(code) from t_factory where name=? ) ");
			ps.add(drug.getFactory());
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			List<Factory> factorys = spaceDao.findList(sqlContext, Factory.class);
			List<String> fstrs = new ArrayList<String>();
			fstrs.add(drug.getFactory());
			for (Factory f : factorys) {
				fstrs.add(f.getName());
			}
			sql.delete(0, sql.length());
			ps.clear();

			List<String> words = KeyWordsUtil.segDrugname(drug.getName(), Boolean.parseBoolean(params.get("smart").toString()));

			if (words.indexOf(drug.getName()) < 0) {
				words.add(drug.getName());
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
			List<Drugwords> drugwords = spaceDao.findList(sqlContext, Drugwords.class);
			sql.delete(0, sql.length());
			ps.clear();

			for (Drugwords d : drugwords) {
				if (words.indexOf(d.getName()) < 0)
					words.add(d.getName());
			}

			sql.delete(0, sql.length());
			ps.clear();
			sql.append("select id,name,code,catalogid,common,dosageform,factory,specifications,unit,zunzi ,count(1) as num from (  ");
			boolean flag = false;
			for (String w : words) {
				if (w.length() != 1) {
					flag = true;
					sql.append(" select * from t_drug where factory in(");
					for (int i = 0; i < fstrs.size(); i++) {
						if (i == 0)
							sql.append("?");
						else
							sql.append(",?");
					}
					sql.append(")  and name like ? and catalogid=?  union all");
					ps.addAll(fstrs);
					ps.add("%" + w + "%");
					ps.add(spaceCatalog.getCatalogid());
				}
			}
			if (!flag)
				return data;

			sql.delete(sql.length() - 9, sql.length());
			sql.append(") as a GROUP BY  id,name,code,catalogid,common,dosageform,factory,specifications,unit,zunzi order  by count(1) desc ");
			// ps.add(count);
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			PageResult<DrugVo> datavo = spaceDao.findList(sqlContext, new Page(false), DrugVo.class);

			for (DrugVo dv : datavo.getData()) {
//				if (numflag == 0) {
//					numflag = dv.getNum();
//				}
//				if (numflag != dv.getNum())
//					break;

				Drug d = new Drug();
				d.setId(dv.getId());
				d.setName(dv.getName());
				d.setCode(dv.getCode());
				d.setCatalogid(dv.getCatalogid());
				d.setCommon(dv.getCommon());
				d.setDosageform(dv.getDosageform());
				d.setFactory(dv.getFactory());
				d.setSpecifications(dv.getSpecifications());
				d.setUnit(dv.getUnit());
				d.setZunzi(dv.getZunzi());
				data.getData().add(d);
			}

			List<Drug> drugs2 = findcomparedrugs(drug, spaceCatalog);

			for (Drug dd : drugs2) {
				boolean flag2 = true;
				for (Drug d : data.getData()) {
					if (d.getCode().equals(dd.getCode())) {
						flag2 = false;
						break;
					}
				}
				if (flag2) {
					data.getData().add(dd);
				}
			}

			// 规格处理..
			List<String> gunits = MyStringUtil.findGunit(drug.getSpecifications());
			List<String> lunits = MyStringUtil.findLunit(drug.getSpecifications());

			Iterator<Drug> it = data.getData().iterator();
			while (it.hasNext()) {
				Drug d = it.next();
				if ((d.getSpecifications().contains("袋") || d.getSpecifications().contains("瓶")) && (drug.getSpecifications().contains("袋") || drug.getSpecifications().contains("瓶"))) {
					if (!((d.getSpecifications().contains("袋") && drug.getSpecifications().contains("袋")) || (d.getSpecifications().contains("瓶") && drug.getSpecifications().contains("瓶")))) {
						it.remove();
						continue;
					}
				}
				List<String> dgunits = MyStringUtil.findGunit(d.getSpecifications());
				List<String> dlunits = MyStringUtil.findLunit(d.getSpecifications());

				boolean gflag = false;
				if (dgunits.size() != 0 && gunits.size() != 0) {
					gflag = true;
					for (String dg : dgunits) {
						for (String g : gunits) {
							if (dg.equals(g)) {
								gflag = false;
								break;
							}
						}
					}
				}

				boolean lflag = false;
				if (dlunits.size() != 0 && lunits.size() != 0) {
					lflag = true;
					for (String dl : dlunits) {
						for (String l : lunits) {
							if (dl.equals(l)) {
								lflag = false;
								break;
							}
						}
					}
				}
				if (gflag || lflag) {
					it.remove();
				}
			}

			final List<String> skeys = MyStringUtil.findnums(drug.getSpecifications());

			Collections.sort(data.getData(), new Comparator<Drug>() {
				@Override
				public int compare(Drug o1, Drug o2) {
					// TODO Auto-generated method stub
					int count1 = 0;
					int count2 = 0;
					List<String> o1s = MyStringUtil.findnums(o1.getSpecifications());
					List<String> o2s = MyStringUtil.findnums(o2.getSpecifications());

					for (String s : skeys) {
						for (String o : o1s) {
							if (s.equals(o)) {
								count1++;
								o1s.remove(o);
								break;
							}

						}
					}

					for (String s : skeys) {
						for (String o : o2s) {
							if (s.equals(o)) {
								count2++;
								o2s.remove(o);
								break;
							}

						}
					}
					if (count1 < count2)
						return 1;
					if (count1 > count2)
						return -1;
					return 0;
				}

			});

			return data;
		} else {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			sql.append("select * from t_drug where 1=1 and catalogid=? ");
			ps.add(spaceCatalog.getCatalogid());
			if (params.containsKey("name")) {
				sql.append(" and  name like ?");
				ps.add("%" + params.get("name") + "%");
			}
			if (params.containsKey("factory")) {
				sql.append(" and  factory like ?");
				ps.add("%" + params.get("factory") + "%");
			}
			if (params.containsKey("specifications")) {
				sql.append(" and  specifications like ?");
				ps.add("%" + params.get("specifications") + "%");
			}
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			return spaceDao.findList(sqlContext, page, Drug.class);
		}

	}

	public List<Drug> findcomparedrugs(Drug drug, SpaceCatalog spaceCatalog) throws Exception {
		List<Drug> data = new ArrayList<Drug>();
		Integer numflag = 0;
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		List<String> words = KeyWordsUtil.segDrugname(drug.getName(), false);
		if (words.indexOf(drug.getName()) < 0) {
			words.add(drug.getName());
		}
		List<String> factorywords = KeyWordsUtil.segFactoryKeywords(drug.getFactory(), false);
		if (factorywords.indexOf(drug.getFactory()) < 0) {
			factorywords.add(drug.getFactory());
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
		List<Drugwords> drugwords = spaceDao.findList(sqlContext, Drugwords.class);
		sql.delete(0, sql.length());
		ps.clear();

		for (Drugwords d : drugwords) {
			if (words.indexOf(d.getName()) < 0)
				words.add(d.getName());
		}

		sql.delete(0, sql.length());
		ps.clear();
		sql.append("select id,name,code,catalogid,common,dosageform,factory,specifications,unit,zunzi ,count(1) as num from (  ");
		boolean flag = false;
		for (String w : words) {
			if (w.length() != 1) {
				flag = true;
				sql.append(" select * from t_drug where ( ");
				for (int i = 0; i < factorywords.size(); i++) {
					if (i == 0)
						sql.append("factory like ? ");
					else
						sql.append("  or   factory like ? ");
					ps.add("%" + factorywords.get(i) + "%");
				}
				sql.append(" 	  )and name like ? and catalogid=?  union all");
				ps.add("%" + w + "%");
				ps.add(spaceCatalog.getCatalogid());
			}
		}
		if (!flag)
			return data;

		sql.delete(sql.length() - 9, sql.length());
		sql.append(") as a GROUP BY  id,name,code,catalogid,common,dosageform,factory,specifications,unit,zunzi order  by count(1) desc ");
		// ps.add(count);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		PageResult<DrugVo> datavo = spaceDao.findList(sqlContext, new Page(false), DrugVo.class);

		for (DrugVo dv : datavo.getData()) {
			if (numflag == 0) {
				numflag = dv.getNum();
			}
			if (numflag != dv.getNum())
				break;

			Drug d = new Drug();
			d.setId(dv.getId());
			d.setName(dv.getName());
			d.setCode(dv.getCode());
			d.setCatalogid(dv.getCatalogid());
			d.setCommon(dv.getCommon());
			d.setDosageform(dv.getDosageform());
			d.setFactory(dv.getFactory());
			d.setSpecifications(dv.getSpecifications());
			d.setUnit(dv.getUnit());
			d.setZunzi(dv.getZunzi());
			data.add(d);
		}
		return data;
	}

	@Override
	public Sdrug findcompareSdrugs(Drug drug) throws Exception {

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select * from t_factory where code= (select min(code) from t_factory where name=? ) ");
		ps.add(drug.getFactory());
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<Factory> factorys = spaceDao.findList(sqlContext, Factory.class);
		List<String> fstrs = new ArrayList<String>();
		fstrs.add(drug.getFactory());
		for (Factory f : factorys) {
			fstrs.add(f.getName());
		}
		sql.delete(0, sql.length());
		ps.clear();

		List<String> words = KeyWordsUtil.segDrugname(drug.getName(), false);

		if (words.indexOf(drug.getName()) < 0) {
			words.add(drug.getName());
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
		List<Drugwords> drugwords = spaceDao.findList(sqlContext, Drugwords.class);
		sql.delete(0, sql.length());
		ps.clear();

		for (Drugwords d : drugwords) {
			if (words.indexOf(d.getName()) < 0)
				words.add(d.getName());
		}
		sql.delete(0, sql.length());
		ps.clear();
		sql.append("select id,name,code,common,dosageform,factory,specifications,unit,zunzi ,count(1) as num from (  ");
		boolean flag = false;
		for (String w : words) {
			if (w.length() != 1) {
				flag = true;
				sql.append(" select * from t_sdrug where factory in(");
				for (int i = 0; i < fstrs.size(); i++) {
					if (i == 0)
						sql.append("?");
					else
						sql.append(",?");
				}
				sql.append(")  and name like ?   union all");
				ps.addAll(fstrs);
				ps.add("%" + w + "%");
			}
		}
		if (!flag)
			return null;

		sql.delete(sql.length() - 9, sql.length());
		sql.append(") as a GROUP BY  id,name,code,common,dosageform,factory,specifications,unit,zunzi order  by count(1) desc ");
		// ps.add(count);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		PageResult<Sdrug> datavo = spaceDao.findList(sqlContext, new Page(false), Sdrug.class);
		if (datavo.getData().size() > 0)
			return datavo.getData().get(0);
		return null;
	}

	@Override
	public Drug findcompared(String drugid, String spaceid) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select t.*    from t_drug  t ,t_drug_drug dd     where  dd.spaceid=? and  dd.sdrugid=t.id   and dd.drugid=? ");
		ps.add(spaceid);
		ps.add(drugid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return spaceDao.findObject(sqlContext, Drug.class);
	}

	@Override
	public PageResult<DrugDrugVo> findcatalogdrugs(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"  select t.* ,s.code as scode, s.name as sname ,s.common as scommon, s.specifications as sspecifications  , s.factory as sfactory, s.unit as sunit ,s.dosageform as sdosageform,s.zunzi as szunzi");
		sql.append(" from t_drug  t left join  t_drug_drug dd on t.id=dd.drugid and dd.num=0 and   dd.spaceid=? left join  t_drug s   on dd.sdrugid=s.id ");
		sql.append(" where  t.catalogid=? ");
		ps.add(params.get("spaceid"));
		ps.add(params.get("catalogid"));

		if (params.containsKey("checked")) {
			sql.append(" and dd.checked =? ");
			ps.add(params.get("checked"));
		}
		sql.append("  order by t.code asc ");

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return spaceDao.findList(sqlContext, page, DrugDrugVo.class);
	}

	@Override
	public List<IerData> findcheckedDrug(String spaceid, String catalogid, List<String> codes) throws Exception {
		// TODO Auto-generated method stub
		List<IerData> list = new ArrayList<IerData>();
		int count;
		if (codes.size() % 1000 == 0)
			count = codes.size() / 1000;
		else
			count = codes.size() / 1000 + 1;

		for (int i = 1; i <= count; i++) {
			SqlContext sqlContext = new SqlContext();
			List<Object> ps = new ArrayList<Object>();
			ps.add(spaceid);
			ps.add(catalogid);
			if (codes.size() < 1000 * i - 1) {
				StringBuilder sql = new StringBuilder();
				sql.append("	select  d1.code as scode,d2.code  as code from t_drug_drug dd ,t_drug d1 ,t_drug d2 where  spaceid=? ");
				sql.append("	and dd.sdrugid =d1.id and d2.id=dd.drugid and d2.catalogid=? and d2.code in ( ");
				StringBuffer fstrs = new StringBuffer();
				fstrs.append("''");
				for (String code : codes.subList((1000) * (i - 1), codes.size())) {
					fstrs.append(",'").append(code).append("'");
				}
				sql.append(fstrs).append(")");
				sqlContext.setParams(ps.toArray());
				sqlContext.setSql(sql.toString());

				list.addAll(spaceDao.findList(sqlContext, new Page(false), IerData.class).getData());
			} else {
				StringBuilder sql = new StringBuilder();
				sql.append("	select  d1.code as scode,d2.code  as code from t_drug_drug dd ,t_drug d1 ,t_drug d2 where  spaceid=? ");
				sql.append("	and dd.sdrugid =d1.id and d2.id=dd.drugid and d2.catalogid=? and d2.code in ( ");
				StringBuffer fstrs = new StringBuffer();
				fstrs.append("''");
				for (String code : codes.subList((1000) * (i - 1), 1000 * i - 1)) {
					fstrs.append(",'").append(code).append("'");
				}
				sql.append(fstrs).append(")");
				sqlContext.setParams(ps.toArray());
				sqlContext.setSql(sql.toString());

				list.addAll(spaceDao.findList(sqlContext, new Page(false), IerData.class).getData());
			}
		}

		return list;
	}
}
