package com.modules.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.commons.util.DateUtil;
import com.modules.service.FxDrugStoreService;
import com.modules.service.FxTreeMetaService;
import com.modules.service.FxTreeService;
import com.modules.service.FxTreeStoreService;
import com.modules.service.StoreService;
import com.modules.vo.DrugVo;
import com.modules.vo.TreeMetaDataVo;
import com.modules.vo.TreeMetaVo;
import com.modules.vo.saleanalysis.DrugAnalysisVo;
import com.modules.dao.FxTreeDao;
import com.modules.pojo.Drug;
import com.modules.pojo.FxComname;
import com.modules.pojo.FxComnameZb;
import com.modules.pojo.FxDrugStore;
import com.modules.pojo.FxTree;
import com.modules.pojo.FxTreeStore;
import com.modules.pojo.Store;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxTreeServiceImpl implements FxTreeService {

	@Autowired
	private FxTreeDao fxTreeDao;

	@Autowired
	private FxTreeMetaService fxTreeMetaService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private FxTreeService fxTreeService;

	@Autowired
	private FxDrugStoreService fxDrugStoreService;
	@Autowired
	private FxTreeStoreService fxTreeStoreService;

	public void save(FxTree entity) throws Exception {
		fxTreeDao.save(entity);
	}

	public void save(List<FxTree> entities) throws Exception {
		fxTreeDao.save(entities);
	}

	public void update(FxTree entity) throws Exception {
		fxTreeDao.update(entity);
	}

	public void update(FxTree newEntity, FxTree oldEntity) throws Exception {
		fxTreeDao.update(newEntity, oldEntity);
	}

	public void delete(FxTree entity) throws Exception {
		fxTreeDao.delete(entity);
	}

	public FxTree findObject(FxTree entity) throws Exception {
		return fxTreeDao.findObject(entity);
	}

	public List<FxTree> findList(FxTree entity) throws Exception {
		return fxTreeDao.findList(entity);
	}

	public PageResult<FxTree> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree where 1=1 ");

		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeDao.findList(sqlContext, page, FxTree.class);
	}

	public List<FxTree> findByPath(String path) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree where 1=1 and path like ?");
		ps.add(path + "%");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeDao.findList(sqlContext, FxTree.class);
	}

	@Override
	public List<FxTree> customtree(String storeid) throws Exception {

		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select * from fx_tree where id in (select treeid from fx_tree_store where storeid =?) order by path asc");
		ps.add(storeid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<FxTree> data = fxTreeDao.findList(sqlContext, FxTree.class);

		if (data.size() == 0) {
			data = fxTreeService.findByPath("18346A0044A148D1A818627B25510E59");
		}

		FxTreeStore fxTreeStore = new FxTreeStore();
		fxTreeStore.setStoreid(storeid);
		List<FxTreeStore> fxTreeStores = fxTreeStoreService.findList(fxTreeStore);

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append("select fc.name, fc.id,ct.treeid parentid from  fx_comname fc ,fx_comname_tree ct     where  ");
		sql.append("	  fc.id=ct.comnameid  ");
		if (fxTreeStores.size() != 0) {
			sql.append("	and ct.treeid in (select treeid from fx_tree_store where storeid=?)    ");
		} else {
			sql.append(
					"  and ct.treeid in (select id from fx_tree where path like '18346A0044A148D1A818627B25510E59%'  )  ");
		}
		sql.append("	   and  fc.xse!=0  order by fc.pf desc  ");
		if (fxTreeStores.size() != 0) {
			ps.add(storeid);
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		data.addAll(fxTreeDao.findList(sqlContext, FxTree.class));
		return data;
	}

	public List<DrugVo> customgydrugs(String storeid, String userid, String treeid, String comnameid, String name,
			String stype, String sfield) throws Exception {

		// Store store = new Store();
		// store.setId(storeid);
		// store = storeService.findObject(store);
		// FxTree rootTree = fxTreeService.findAnalysisRootTree(userid);

		FxTree fxTree = new FxTree();
		fxTree.setId(treeid);
		fxTree = fxTreeDao.findObject(fxTree);

		String result = "";
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();

		sql.append(
				"select a.* ,a.ybflag ybpz,ywflag ywpz,bbflag bbpz,a.ztflag ztpz,a.ppflag pppz,a.ggflag ggpz from( ");
		sql.append("  select  d.* ,u.company,u.id supplierid  from  t_drug d, ");
		sql.append(" t_catalog c,t_user u ,fx_comname_tree ct,fx_tree t    ");
		sql.append(" where  d.comnameid=ct.comnameid and ct.treeid=t.id ");
		sql.append(
				"  and      c.myself=true and c.userid=u.id and d.catalogid=c.id and u.id in(select userid from t_user_role where roleid='B1CC34941707470D9BF361D1CEF2B08E')  ");

		sql.append("and  t.path like ?  ");
		ps.add(fxTree.getPath() + "%");

		if (StringUtils.isNotBlank(name)) {
			sql.append(" and  d.name like ?");
			ps.add("%" + name + "%");
		}

		sql.append(" ) a where 1=1 ");

		if (StringUtils.isNotBlank(comnameid)) {
			sql.append(" 	and a.comnameid =?  ");
			ps.add(comnameid);
		}

		if (StringUtils.isNotBlank(stype) && StringUtils.isNotBlank(sfield) && stype.equals("name")) {
			sql.append(" order by ").append(sfield).append("  ").append(stype);
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugVo> data = fxTreeDao.findList(sqlContext, DrugVo.class);

		// FxDrugStore fxDrugStore = new FxDrugStore();
		// fxDrugStore.setStoreid(storeid);
		// List<FxDrugStore> dses = fxDrugStoreService.findList(fxDrugStore);
		// loop: for (DrugVo dv : data) {
		// dv.setZypz(false);
		// dv.setChecked(false);
		// for (FxDrugStore ds : dses) {
		// if (ds.getDrugid().equals(dv.getId())) {
		// if (ds.getZy())
		// dv.setZypz(true);
		// dv.setChecked(ds.getStatus());
		// continue loop;
		// }
		// }
		// dv.setChecked(false);
		// }

		for (DrugVo d : data) {
			if (d.getMlv() == null && d.getPrice() != null && d.getCostprice() != null
					&& d.getPrice().compareTo(new BigDecimal(0)) != 0
					&& d.getCostprice().compareTo(new BigDecimal(0)) != 0) {
				d.setMlv(d.getPrice().subtract(d.getCostprice())
						.multiply(new BigDecimal(100).divide(d.getPrice(), 10, RoundingMode.CEILING)));
			}
		}

		// if (!treeid.equals("0000")) {
		// 高毛率定义
		// TreeMetaVo gmtv = fxTreeMetaService.findByTreepathNCode(treeid, "gm", userid,
		// storeid, store.getTypeid());
		//
		// BigDecimal gmbfb = new BigDecimal(0);
		// BigDecimal jgdsx = new BigDecimal(0);
		// BigDecimal jgdxx = new BigDecimal(0);
		// BigDecimal pzzszb = new BigDecimal(0);
		// BigDecimal gmzb = new BigDecimal(0);
		// BigDecimal ppszb = new BigDecimal(0);
		// try {
		// for (TreeMetaDataVo p : gmtv.getTreeMetaDatas()) {
		// if (p.getCode().equals("bfb")) {
		// gmbfb = new BigDecimal(p.getVal());
		// }
		// }
		//
		// for (DrugVo dv : data) {
		// if (dv.getMlv() != null) {
		// if (dv.getMlv().compareTo(gmbfb) == 1)
		// dv.setGmlpz(true);
		// }
		//
		// }
		//
		// } catch (Exception e) {
		// if (StringUtils.isNotBlank(result))
		// result = result + ",获取其他参数失败!";
		// else
		// result = "获取参数失败!";
		// }
		// }

		return data;

	}

	@Override
	public Map customdrug(String userid, String comnameid, Date startdate, Date enddate, String catalogid,
			String treeid, String storeid, String name, Integer status, Integer currentPage, Integer pageSize,
			final String stype, final String sfield) throws Exception {

		Map datas = new HashMap();

		Store store = new Store();
		store.setId(storeid);
		store = storeService.findObject(store);

		FxTree rootTree = fxTreeService.findAnalysisRootTree(userid);
		FxTreeStore fxTreeStore = new FxTreeStore();
		fxTreeStore.setStoreid(storeid);

		List<FxTreeStore> fxTreeStores = fxTreeStoreService.findList(fxTreeStore);

		String result = "";
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select a.id,a.name,a.code,a.factory,a.costprice,a.price,a.specifications,a.unit,a.fccommon common,a.comnameid,a.sdrugid,a.storeid,a.supplierid,a.supplier,IFNULL(a.xssx,\"\"),IFNULL(a.zy,false),IFNULL(a.ll,false) ,a.ybflag ybpz,ywflag ywpz,bbflag bbpz,a.ztflag ztpz,a.ppflag pppz,a.ggflag ggpz from( ");
		sql.append("select d.* ,  fc.name fccommon, '连锁总部' supplier ,'' supplierid ");

		if (fxTreeStores.size() == 0) {
			sql.append(",? storeid ");
			ps.add(storeid);
		} else {
			sql.append(",ts.storeid");
		}

		sql.append(" from  t_drug d,fx_comname_tree ct,fx_tree t ,fx_comname fc   ");

		if (fxTreeStores.size() != 0) {
			sql.append(",fx_tree_store ts ");
		}

		sql.append(" where  d.comnameid=ct.comnameid and ct.treeid=t.id  and fc.id=d.comnameid ");

		if (fxTreeStores.size() != 0) {
			sql.append("and ts.storeid=?   and ts.treeid= t.id ");
			ps.add(storeid);
		}

		sql.append("   and d.catalogid=?  ");
		ps.add(catalogid);

		FxTree fxTree = new FxTree();
		fxTree.setId(treeid);
		fxTree = fxTreeDao.findObject(fxTree);

		if (treeid.equals("0000")) {
			sql.append(" 	and( t.path like ? ) ");
			ps.add(rootTree.getPath() + "%");
		} else {
			sql.append("and  t.path like ?  ");
			ps.add(fxTree.getPath() + "%");
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and  d.name like ?");
			ps.add("%" + name + "%");
		}

		if (!(userid.equals("6E424AD659C04C1BBB4CCB6EE085BD72"))) {
			sql.append(
					"union select  d.*,ts.storeid storeid,fc.name fccommon ,  u.company,u.id supplierid  from  t_drug d, ");

			sql.append(" t_catalog c,t_user u ,fx_comname_tree ct,fx_tree t ,fx_tree_store ts,fx_comname fc  ");
			sql.append(" where  d.comnameid=ct.comnameid and ct.treeid=t.id and fc.id=d.comnameid ");
			sql.append(
					"  and ts.storeid=?   and ts.treeid= t.id   and c.myself=true and c.userid=u.id and d.catalogid=c.id and u.id in(select userid from t_user_role where roleid='B1CC34941707470D9BF361D1CEF2B08E')  ");
			ps.add(storeid);
			if (treeid.equals("0000")) {
				sql.append(" and t.path like ?    ");
				ps.add(rootTree.getPath() + "%");
			} else {
				sql.append("and  t.path like ?  ");
				ps.add(fxTree.getPath() + "%");
			}
			if (StringUtils.isNotBlank(name)) {
				sql.append(" and  d.name like ?");
				ps.add("%" + name + "%");
			}
		}

		sql.append(" ) a where 1=1 ");

		if (StringUtils.isNotBlank(comnameid)) {
			sql.append(" 	and a.comnameid =?  ");
			ps.add(comnameid);
		}

		if (StringUtils.isNotBlank(stype) && StringUtils.isNotBlank(sfield) && stype.equals("name")) {
			sql.append(" order by ").append(sfield).append("  ").append(stype);
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugVo> data = fxTreeDao.findList(sqlContext, DrugVo.class);

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append(
				"  select drugid from fx_drug_store where   (xsrq<DATE_ADD(?,INTERVAL -3 MONTH)    or xsrq is null) and storeid=? and kc=0  ");
		ps.add(startdate);
		ps.add(storeid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<String> dtpz = fxTreeDao.findList(sqlContext, String.class);
		Iterator<DrugVo> itd = data.iterator();
		while (itd.hasNext()) {
			DrugVo x = itd.next();
			for (String id : dtpz) {
				if (id.equals(x.getId())) {
					itd.remove();
				}
			}
		}

		FxDrugStore fxDrugStore = new FxDrugStore();
		fxDrugStore.setStoreid(storeid);
		List<FxDrugStore> dses = fxDrugStoreService.findList(fxDrugStore);
		loop: for (DrugVo dv : data) {
			dv.setZypz(false);
			dv.setChecked(false);
			for (FxDrugStore ds : dses) {
				if (ds.getDrugid().equals(dv.getId())) {
					if (ds.getZy())
						dv.setZypz(true);
					dv.setChecked(ds.getStatus());
					continue loop;
				}
			}
			dv.setChecked(false);
		}

		Iterator<DrugVo> it = data.iterator();

		while (it.hasNext()) {
			DrugVo x = it.next();
			if (status == 1) {
				if (!x.getChecked()) {
					it.remove();
				}
			} else if (status == 2) {
				if (x.getChecked()) {
					it.remove();
				}
			}
		}

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append(
				"  select drugid,sum(pc) pc   ,sum(xse) xse,sum(xssl) xssl,IFNULL(sum(xse)/sum(xssl),0)  pjsj,sum(ml) ml,IFNULL( sum(ml)*100 /sum(xse),0) mlv ");
		sql.append(" from fx_sale_month where  drugid in('' ");
		for (DrugVo d : data) {
			sql.append(",?");
			ps.add(d.getId());
		}
		sql.append("  )");
		if (startdate != null) {
			sql.append(" and month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and month<=?  ");
			ps.add(enddate);
		}
		if (storeid != null) {
			sql.append(" and storeid=?  ");
			ps.add(storeid);
		}
		if (userid != null) {
			sql.append(" and userid=?  ");
			ps.add(userid);
		}
		sql.append("   group by drugid ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos = fxTreeDao.findList(sqlContext, DrugAnalysisVo.class);
		for (DrugAnalysisVo dv : drugAnalysisVos) {
			for (DrugVo d : data) {
				if (dv.getDrugid().equals(d.getId())) {
					d.setXse(dv.getXse());
					d.setMl(dv.getMl());
					d.setXsmlv(dv.getMlv());
					d.setXssl(dv.getXssl());
					d.setGmpc(dv.getPc());
				}
			}
		}

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append(
				"  select drugid,sum(pc) pc   ,sum(xse) xse,sum(xssl) xssl,IFNULL(sum(xse)/sum(xssl),0)  pjsj,sum(ml) ml,IFNULL(sum(ml)*100 /sum(xse),0) mlv ");
		sql.append(" from fx_sale_month where      1=1 ");
		if (startdate != null) {
			sql.append(" and month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and month<=?  ");
			ps.add(enddate);
		}
		if (storeid != null) {
			sql.append(" and storeid=?  ");
			ps.add(storeid);
		}
		if (userid != null) {
			sql.append(" and userid=?  ");
			ps.add(userid);
		}
		sql.append("   group by drugid ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos2 = fxTreeDao.findList(sqlContext, DrugAnalysisVo.class);

		this.countScore80(drugAnalysisVos2);

		for (DrugVo d : data) {
			d.setXse(new BigDecimal(0));
			d.setMl(new BigDecimal(0));
			d.setXsmlv(new BigDecimal(0));
			d.setXssl(new BigDecimal(0));

			for (DrugAnalysisVo dv : drugAnalysisVos2) {
				if (dv.getDrugid().equals(d.getId())) {
					d.setXse(dv.getXse());
					d.setMl(dv.getMl());
					d.setXsmlv(dv.getMlv());
					d.setXssl(dv.getXssl());
					d.setGmpc(dv.getPc());
					d.setIs80(dv.getIs80());
				}
			}
		}

		for (DrugVo d : data) {
			if (d.getMlv() == null && d.getPrice() != null && d.getCostprice() != null
					&& d.getPrice().compareTo(new BigDecimal(0)) != 0
					&& d.getCostprice().compareTo(new BigDecimal(0)) != 0) {
				d.setMlv(d.getPrice().subtract(d.getCostprice())
						.multiply(new BigDecimal(100).divide(d.getPrice(), 10, RoundingMode.CEILING)));
			}
		}

		if (!treeid.equals("0000")) {
			// 获取淘汰品种
			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append(
					"  select drugid from fx_drug_store where (bhrq<DATE_ADD(?,INTERVAL -3 MONTH) and (xsrq<DATE_ADD(?,INTERVAL -3 MONTH)    or xsrq is null)) and storeid=?  ");
			ps.add(startdate);
			ps.add(startdate);
			ps.add(storeid);
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			List<String> ttpz = fxTreeDao.findList(sqlContext, String.class);

			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append("select count(1)   from ( select drugid from  fx_drug_store  where  1=1 and status=1 ");
			if (storeid != null) {
				sql.append(" and storeid=?  ");
				ps.add(storeid);
			}
			if (userid != null) {
				sql.append(" and userid=?  ");
				ps.add(userid);
			}
			sql.append("   group by drugid ) a");

			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			Integer pzzs = fxTreeDao.findE(sqlContext, Integer.class);

			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append(
					"select c.id  from fx_comname_tree ct ,fx_tree t ,fx_comname  c  where ct.treeid=t.id and c.id=ct.comnameid and t.path like ? ORDER BY xse desc  ");

			ps.add(fxTree.getPath() + "%");
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			List<String> comnameids = fxTreeDao.findList(sqlContext, String.class);

			int selectpp = 0;
			int selectgml = 0;
			int pznum = 0;

			// 推荐数量
			Integer tjsl = 0;
			// 淘汰数量
			Integer ttsl = 0;

			List<String> ttSdrugids = new ArrayList<String>();

			loop: for (DrugVo dv : data) {
				dv.setTjpz(false);
				dv.setTtpz(false);
				dv.setGmlpz(false);
				for (String id : ttpz) {
					if (dv.getId().equals(id) && dv.getChecked()) {
						dv.setTtpz(true);
						ttsl++;
						if (StringUtils.isNotBlank(dv.getSdrugid()))
							ttSdrugids.add(dv.getSdrugid());
						continue loop;
					}
				}

				if (dv.getIs80() != null) {
					dv.setZy(true);
					dv.setZypz(true);
				}

				if (dv.getZypz() && (dv.getIs80() == null)) {
					dv.setTtpz(true);
					ttsl++;
				}

			}

			// 高毛率定义
			TreeMetaVo gmtv = fxTreeMetaService.findByTreepathNCode(treeid, "gm", userid, storeid, store.getTypeid());
			// 价格带
			TreeMetaVo jgtv = fxTreeMetaService.findByTreepathNCode(treeid, "jgd", userid, storeid, store.getTypeid());
			// 品种数
			TreeMetaVo pztv = fxTreeMetaService.findByTreepathNCode(treeid, "pzs", userid, storeid, store.getTypeid());

			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append(
					"select c.comnameid,c.zb ,c.pzsl from fx_comname_tree ct ,fx_tree t ,fx_comname_zb  c  where ct.treeid=t.id and c.comnameid=ct.comnameid and t.path like ?  and c.storetypeid=? ");
			ps.add(fxTree.getPath() + "%");
			ps.add(store.getTypeid());
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			List<FxComnameZb> comnamezbs = fxTreeDao.findList(sqlContext, FxComnameZb.class);

			Map<String, Integer> comnameSl = new HashMap<String, Integer>();
			for (FxComnameZb czb : comnamezbs) {
				if (czb.getPzsl() != null)
					comnameSl.put(czb.getComnameid(), czb.getPzsl().intValue());
			}

			BigDecimal gmbfb = new BigDecimal(0);
			BigDecimal jgdsx = new BigDecimal(0);
			BigDecimal jgdxx = new BigDecimal(0);
			BigDecimal pzzszb = new BigDecimal(0);
			BigDecimal gmzb = new BigDecimal(0);
			BigDecimal ppszb = new BigDecimal(0);
			try {

				for (TreeMetaDataVo p : gmtv.getTreeMetaDatas()) {
					if (p.getCode().equals("bfb")) {
						gmbfb = new BigDecimal(p.getVal());
					}
				}

				for (TreeMetaDataVo p : jgtv.getTreeMetaDatas()) {
					if (p.getCode().equals("sx")) {
						jgdsx = new BigDecimal(p.getVal());
					}
					if (p.getCode().equals("xx")) {
						jgdxx = new BigDecimal(p.getVal());
					}
				}

				for (TreeMetaDataVo p : pztv.getTreeMetaDatas()) {
					if (p.getCode().equals("zszb")) {
						pzzszb = new BigDecimal(p.getVal());
					}
					if (p.getCode().equals("gmzb")) {
						gmzb = new BigDecimal(p.getVal());
					}
					if (p.getCode().equals("ppszb")) {
						ppszb = new BigDecimal(p.getVal());
					}
				}

				BigDecimal selectjgsx = new BigDecimal(0);
				BigDecimal selectjgxx = new BigDecimal(9999999);

				for (DrugVo dv : data) {
					if (dv.getMlv() != null) {
						if (dv.getMlv().compareTo(gmbfb) == 1)
							dv.setGmlpz(true);
					}
					if (dv.getPrice() != null && dv.getChecked()) {
						if (selectjgsx.compareTo(dv.getPrice()) == -1) {
							selectjgsx = dv.getPrice();
						}

						if (selectjgxx.compareTo(dv.getPrice()) == 1) {
							selectjgxx = dv.getPrice();
						}

					}
				}

				BigDecimal pc = jgdsx.subtract(jgdxx).divide(new BigDecimal(3), 10, RoundingMode.CEILING);

				if (selectjgsx.compareTo(jgdsx.subtract(pc)) == -1) {
					if (StringUtils.isNotBlank(result))
						result = result + ",";
					result = result + "缺乏高端品种";

					for (DrugVo dv : data) {
						if (dv.getPrice() != null && dv.getPrice().compareTo(jgdsx.subtract(pc)) == 1
								&& !dv.getChecked()) {
							dv.setTjpz(true);
						}
					}

				}

				if (selectjgxx.compareTo(jgdxx.add(pc)) == 1) {
					if (StringUtils.isNotBlank(result))
						result = result + ",";
					result = result + "缺乏低端品种";
					for (DrugVo dv : data) {
						if (dv.getPrice() != null && dv.getPrice().compareTo(jgdxx.subtract(pc)) == -1
								&& !dv.getChecked()) {
							dv.setTjpz(true);
						}
					}

				}

				for (DrugVo dv : data) {
					if (dv.getPppz() && dv.getGmlpz() && !dv.getChecked()) {
						dv.setTjpz(true);
					}
				}

				for (DrugVo dv : data) {
					if (dv.getPppz() && dv.getGmlpz() && !dv.getChecked()) {
						dv.setTjpz(true);
					}
				}

				for (DrugVo dv : data) {
					if (dv.getGmlpz() && dv.getChecked()) {
						selectgml++;
					}

					if (dv.getPppz() && dv.getChecked()) {
						selectpp++;
					}
					if (dv.getChecked())
						pznum++;

				}

				Integer heli = new BigDecimal(pzzs).multiply(pzzszb).multiply(new BigDecimal(1))
						.divide(new BigDecimal(100)).intValue();

				if (new BigDecimal(pznum)
						.compareTo(new BigDecimal(pzzs).multiply(pzzszb).divide(new BigDecimal(100))) == -1) {
					if (StringUtils.isNotBlank(result))
						result = result + ",";
					result = result + "品种数量偏少";
				}

				if (new BigDecimal(pznum)
						.compareTo(new BigDecimal(pzzs).multiply(pzzszb).divide(new BigDecimal(100))) == 1) {
					if (StringUtils.isNotBlank(result))
						result = result + ",";
					result = result + "品种数量偏多 ";
				}

				if (pznum != 0 && new BigDecimal(selectpp).multiply(new BigDecimal(100))
						.divide(new BigDecimal(pznum), 10, RoundingMode.CEILING).compareTo(ppszb) == -1) {
					if (StringUtils.isNotBlank(result))
						result = result + ",";
					result = result + "缺少品牌品种";
					for (DrugVo dv : data) {
						if (!dv.getChecked() && dv.getPppz()) {
							dv.setTjpz(true);
						}
					}
				}

				if (pznum != 0 && new BigDecimal(selectgml).multiply(new BigDecimal(100))
						.divide(new BigDecimal(pznum), 10, RoundingMode.CEILING).compareTo(gmzb) == -1) {
					if (StringUtils.isNotBlank(result))
						result = result + ",";
					result = result + "缺少高毛利品种";
					for (DrugVo dv : data) {
						if (!dv.getChecked() && dv.getGmlpz()) {
							dv.setTjpz(true);
						}
					}
				}

				for (DrugVo dv : data) {
					if (!dv.getChecked()) {
						dv.setTjpz(false);
					}
				}

				for (DrugVo dv : data) {
					if (dv.getMlv() == null) {
						dv.setMlv(new BigDecimal(0));

					}
					if (!dv.getChecked() && dv.getZtpz()) {
						dv.setTjpz(true);
					}
				}

				Collections.sort(data, (d1, d2) -> d2.getMlv().compareTo(d1.getMlv()));

				List<String> tjSdrugids = new ArrayList<String>();

				tjsl = heli - pznum + ttsl;
				if (tjsl > 0) {
					loop: for (String id : comnameids) {
						Integer num = comnameSl.get(id);
						if (num == null)
							num = 1;
						Integer snum = 0;
						for (DrugVo dv : data) {
							if (dv.getChecked() && dv.getComnameid().equals(id) && !dv.getTjpz()) {
								snum++;
							}
						}
						if (num - snum > 0) {
							for (DrugVo dv : data) {
								if (!dv.getChecked() && dv.getGmlpz() && dv.getPppz() && dv.getComnameid().equals(id)) {
									if (ttSdrugids.indexOf(dv.getSdrugid()) < 0
											&& tjSdrugids.indexOf(dv.getSdrugid()) < 0) {
										if (StringUtils.isNotBlank(dv.getSdrugid()))
											tjSdrugids.add(dv.getSdrugid());
										if ("H".equals(dv.getXssx()) || "Z".equals(dv.getXssx())
												|| "ZT".equals(dv.getXssx()) || "F".equals(dv.getXssx())) {
											if (dv.getZy()) {
												if (store.getZyq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}
											} else if (dv.getLl()) {
												if (store.getLlq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}
											} else {
												dv.setTjpz(true);
												tjsl--;
												snum++;
											}
										}
										if (tjsl == 0)
											break loop;
										if (snum == num)
											continue loop;
									}
								}
							}
						}
						if (num - snum > 0) {
							for (DrugVo dv : data) {
								if (!dv.getChecked() && dv.getPppz() && !dv.getTjpz() && dv.getComnameid().equals(id)) {
									if (ttSdrugids.indexOf(dv.getSdrugid()) < 0
											&& tjSdrugids.indexOf(dv.getSdrugid()) < 0) {
										if (StringUtils.isNotBlank(dv.getSdrugid()))
											tjSdrugids.add(dv.getSdrugid());
										if ("H".equals(dv.getXssx()) || "Z".equals(dv.getXssx())
												|| "ZT".equals(dv.getXssx()) || "F".equals(dv.getXssx())) {
											if (dv.getZy()) {
												if (store.getZyq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}
											} else if (dv.getLl()) {
												if (store.getLlq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}
											} else {
												dv.setTjpz(true);
												tjsl--;
												snum++;
											}
										}
										if (tjsl == 0)
											break loop;
										if (snum == num)
											continue loop;
									}
								}
							}
						}

						if (num - snum > 0) {
							for (DrugVo dv : data) {
								if (!dv.getChecked() && dv.getGmlpz() && !dv.getTjpz()
										&& dv.getComnameid().equals(id)) {
									if (ttSdrugids.indexOf(dv.getSdrugid()) < 0
											&& tjSdrugids.indexOf(dv.getSdrugid()) < 0) {
										if (StringUtils.isNotBlank(dv.getSdrugid()))
											tjSdrugids.add(dv.getSdrugid());
										if ("H".equals(dv.getXssx()) || "Z".equals(dv.getXssx())
												|| "ZT".equals(dv.getXssx()) || "F".equals(dv.getXssx())) {
											if (dv.getZy()) {
												if (store.getZyq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}

											} else if (dv.getLl()) {
												if (store.getLlq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}
											} else {
												dv.setTjpz(true);
												tjsl--;
												snum++;
											}
										}
										if (tjsl == 0)
											break loop;
										if (snum == num)
											continue loop;
									}
								}
							}
						}

						if (num - snum > 0) {
							for (DrugVo dv : data) {
								if (!dv.getChecked() && !dv.getTjpz() && dv.getComnameid().equals(id)) {
									if (ttSdrugids.indexOf(dv.getSdrugid()) < 0
											&& tjSdrugids.indexOf(dv.getSdrugid()) < 0) {
										if (StringUtils.isNotBlank(dv.getSdrugid()))
											tjSdrugids.add(dv.getSdrugid());
										if ("H".equals(dv.getXssx()) || "Z".equals(dv.getXssx())
												|| "ZT".equals(dv.getXssx()) || "F".equals(dv.getXssx())) {
											if (dv.getZy()) {
												if (store.getZyq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}
											} else if (dv.getLl()) {
												if (store.getLlq()) {
													dv.setTjpz(true);
													tjsl--;
													snum++;
												}
											} else {
												dv.setTjpz(true);
												tjsl--;
												snum++;
											}
										}
										tjsl--;
										snum++;
										if (tjsl == 0)
											break loop;
										if (snum == num)
											continue loop;
									}
								}
							}
						}

					}

				}

				datas.put("hlpz", heli);
				datas.put("pznum", pznum);
				datas.put("gmbfb", gmbfb);

			} catch (Exception e) {
				if (StringUtils.isNotBlank(result))
					result = result + ",获取其他参数失败!";
				else
					result = "获取参数失败!";
			}
		}

		List<DrugVo> fxs = new ArrayList<DrugVo>();

		DrugVo dx1 = new DrugVo();
		dx1.setName("广告");
		dx1.setXse(new BigDecimal(0));
		dx1.setGmpc(0);
		dx1.setMl(new BigDecimal(0));
		fxs.add(dx1);

		DrugVo dx2 = new DrugVo();
		dx2.setName("品牌");
		dx2.setXse(new BigDecimal(0));
		dx2.setGmpc(0);
		dx2.setMl(new BigDecimal(0));
		fxs.add(dx2);

		DrugVo dx3 = new DrugVo();
		dx3.setName("高毛利");
		dx3.setXse(new BigDecimal(0));
		dx3.setGmpc(0);
		dx3.setMl(new BigDecimal(0));
		fxs.add(dx3);

		DrugVo dx4 = new DrugVo();
		dx4.setName("必备");
		dx4.setXse(new BigDecimal(0));
		dx4.setGmpc(0);
		dx4.setMl(new BigDecimal(0));
		fxs.add(dx4);

		DrugVo dx5 = new DrugVo();
		dx5.setName("医保");
		dx5.setXse(new BigDecimal(0));
		dx5.setGmpc(0);
		dx5.setMl(new BigDecimal(0));
		fxs.add(dx5);

		DrugVo dx6 = new DrugVo();
		dx6.setName("院外");
		dx6.setXse(new BigDecimal(0));
		dx6.setGmpc(0);
		dx6.setMl(new BigDecimal(0));
		fxs.add(dx6);

		DrugVo dx7 = new DrugVo();
		dx7.setName("其他");
		dx7.setXse(new BigDecimal(0));
		dx7.setGmpc(0);
		dx7.setMl(new BigDecimal(0));
		fxs.add(dx7);

		for (DrugVo dv : data) {
			boolean flag = true;
			if (dv.getXse() != null && dv.getMl() != null && dv.getGmpc() != null && dv.getZypz()) {

				if (dv.getGgpz() && dv.getZypz()) {
					flag = false;
					dx1.setXse(dx1.getXse().add(dv.getXse()));
					dx1.setMl(dx1.getMl().add(dv.getMl()));
					dx1.setGmpc(dx1.getGmpc() + dv.getGmpc());
				}
				if (dv.getPppz() && dv.getZypz()) {
					flag = false;
					dx2.setXse(dx2.getXse().add(dv.getXse()));
					dx2.setMl(dx2.getMl().add(dv.getMl()));
					dx2.setGmpc(dx2.getGmpc() + dv.getGmpc());
				}
				if (dv.getGmlpz() && dv.getZypz()) {
					flag = false;
					dx3.setXse(dx3.getXse().add(dv.getXse()));
					dx3.setMl(dx3.getMl().add(dv.getMl()));
					dx3.setGmpc(dx3.getGmpc() + dv.getGmpc());
				}
				if (dv.getBbpz() && dv.getZypz()) {
					flag = false;
					dx4.setXse(dx4.getXse().add(dv.getXse()));
					dx4.setMl(dx4.getMl().add(dv.getMl()));
					dx4.setGmpc(dx4.getGmpc() + dv.getGmpc());
				}
				if (dv.getYbpz() && dv.getZypz()) {
					flag = false;
					dx5.setXse(dx5.getXse().add(dv.getXse()));
					dx5.setMl(dx5.getMl().add(dv.getMl()));
					dx5.setGmpc(dx5.getGmpc() + dv.getGmpc());

				}
				if (dv.getYwpz() && dv.getZypz()) {
					flag = false;
					dx6.setXse(dx6.getXse().add(dv.getXse()));
					dx6.setMl(dx6.getMl().add(dv.getMl()));
					dx6.setGmpc(dx6.getGmpc() + dv.getGmpc());

				}
				if (flag && dv.getZypz()) {
					flag = false;
					dx7.setXse(dx7.getXse().add(dv.getXse()));
					dx7.setMl(dx7.getMl().add(dv.getMl()));
					dx7.setGmpc(dx7.getGmpc() + dv.getGmpc());
				}
			}
		}

		Collections.sort(data, new Comparator<DrugVo>() {
			@Override
			public int compare(DrugVo o1, DrugVo o2) {
				if (null == o1.getComxse())
					return 1;
				if (null == o2.getComxse())
					return -1;
				if (o2.getComxse() == o1.getComxse())
					return 0;
				return -o1.getComxse().compareTo(o2.getComxse());
			}
		});

		datas.put("fxdata", fxs);
		datas.put("data", data);
		datas.put("result", result);

		return datas;
	}

	private void countScore80(List<DrugAnalysisVo> dvs) {

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getXse().compareTo(o2.getXse());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			DrugAnalysisVo dv = dvs.get(i - 1);
			dv.setNum(4 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getPc().compareTo(o2.getPc());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			DrugAnalysisVo dv = dvs.get(i - 1);
			dv.setNum(dv.getNum() + 4 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getMl().compareTo(o2.getMl());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			DrugAnalysisVo dv = dvs.get(i - 1);
			dv.setNum(dv.getNum() + 2 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getNum().compareTo(o2.getNum());
			}
		});

		for (DrugAnalysisVo dv : dvs) {
			if ((dvs.indexOf(dv) + 1) * 10 < dvs.size() * 8) {
				dv.setIs80(true);
			} else {
				dv.setIs80(false);
			}
		}
	}

	private void countScore802(List<DrugAnalysisVo> dvs) {

		BigDecimal totalxse = new BigDecimal(0);
		Integer totalpc = 0;
		BigDecimal totalml = new BigDecimal(0);

		for (DrugAnalysisVo dv : dvs) {
			totalxse = totalxse.add(dv.getXse());
			totalpc = totalpc + dv.getPc();
			totalml = totalml.add(dv.getMl());
			dv.setIs80(false);
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getXse().compareTo(o2.getXse());
			}
		});

		int count80 = 0;
		for (DrugAnalysisVo dv : dvs) {
			if (dv.getIs80())
				count80++;
			dv.setIs80(false);
		}

		BigDecimal xse = new BigDecimal(0);
		for (DrugAnalysisVo dv : dvs) {
			xse = xse.add(dv.getXse());
			dv.setIs80(true);
			if (xse.compareTo(totalxse.multiply(new BigDecimal(0.8))) == 1)
				break;
		}

		count80 = 0;
		for (DrugAnalysisVo dv : dvs) {
			if (dv.getIs80())
				count80++;
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getPc().compareTo(o2.getPc());
			}
		});

		Integer pc = 0;
		for (DrugAnalysisVo dv : dvs) {
			pc = pc + dv.getPc();
			dv.setIs80(true);
			if (new BigDecimal(pc).compareTo(new BigDecimal(totalpc).multiply(new BigDecimal(0.8))) == 1)
				break;
		}

		count80 = 0;
		for (DrugAnalysisVo dv : dvs) {
			if (dv.getIs80())
				count80++;
		}
		System.out.println(count80 + "   :  " + dvs.size());

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getMl().compareTo(o2.getMl());
			}
		});

		BigDecimal ml = new BigDecimal(0);
		for (DrugAnalysisVo dv : dvs) {
			ml = ml.add(dv.getMl());
			dv.setIs80(true);
			if (ml.compareTo(totalml.multiply(new BigDecimal(0.8))) == 1)
				break;
		}

		count80 = 0;
		for (DrugAnalysisVo dv : dvs) {
			if (dv.getIs80())
				count80++;
		}

	}

	public void updateName(String id, String name) throws Exception {

		FxTree tree = new FxTree();
		tree.setId(id);
		tree = fxTreeDao.findObject(tree);

		FxTree tree2 = new FxTree();
		tree2.setName(name);
		tree2.setParentid(tree.getParentid());
		tree2 = fxTreeDao.findObject(tree2);

		// 如果含有父节点下含有相同节点名的子节点
		if (tree2 != null && tree2.getId() != id) {
			SqlContext sqlContext = new SqlContext();
			StringBuilder sql = new StringBuilder();
			List<Object> ps = new ArrayList<Object>();
			// 将当前节点下子节点变更父节点
			sql.append("update  fx_tree set parentid=? where parentid=? ");
			ps.add(tree2.getId());
			ps.add(tree.getId());
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeDao.update(sqlContext);
			// 将现有节点下所有子节点变更path路径
			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append("update  fx_tree set path=replace(path,?,?) where path like ? ");
			ps.add(tree.getId());
			ps.add(tree2.getId());
			ps.add(tree.getPath() + "%");
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeDao.update(sqlContext);
			// 将与当前节点通用名绑定节点变更绑定节点id
			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append("update  fx_comname_tree set treeid=?  where treeid=? ");
			ps.add(tree2.getId());
			ps.add(tree.getId());
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeDao.update(sqlContext);

			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append("update  fx_comname_tree_factory set treeid=?  where treeid=? ");
			ps.add(tree2.getId());
			ps.add(tree.getId());
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeDao.update(sqlContext);
			// 更新连锁绑定节点
			sqlContext = new SqlContext();
			sql = new StringBuilder();
			ps = new ArrayList<Object>();
			sql.append("update  fx_tree_store set treeid=?  where treeid=? ");
			ps.add(tree2.getId());
			ps.add(tree.getId());
			sqlContext.setSql(sql.toString());
			sqlContext.setParams(ps.toArray());
			fxTreeDao.update(sqlContext);
			// 删除当前节点
			tree = new FxTree();
			tree.setId(id);
			fxTreeDao.delete(tree);
		} else {
			tree.setName(name);
			fxTreeDao.update(tree);
		}

	}

	@Override
	public List<FxTree> findAnalysisTree(String userid) throws Exception {
		FxTree fxTree = findAnalysisRootTree(userid);
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_tree where  path like ? ");
		ps.add(fxTree.getPath() + "%");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeDao.findList(sqlContext, FxTree.class);
	}

	@Override
	public FxTree findAnalysisRootTree(String userid) throws Exception {
		// TODO Auto-generated method stub
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"select * from fx_tree where userid=(select case when count(1)=0 then ? else userid end from  fx_tree where userid=? and analysis=true and parentid is null) ");
		sql.append("and analysis=true and parentid is null");
		ps.add("F8DD2C67A39047789B75F6574F766A14");
		ps.add(userid);
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxTreeDao.findObject(sqlContext, FxTree.class);
	}

}
