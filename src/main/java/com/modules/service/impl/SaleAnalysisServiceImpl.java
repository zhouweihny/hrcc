package com.modules.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.commons.sql.SqlContext;
import com.commons.util.DateUtil;
import com.modules.dao.FxSaleDataDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.FxComname;
import com.modules.pojo.FxTree;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.FxTreeService;
import com.modules.service.SaleAnalysisService;
import com.modules.vo.DrugVo;
import com.modules.vo.PriceBandVo;
import com.modules.vo.PriceBandVo2;
import com.modules.vo.saleanalysis.ComAnalysisVo;
import com.modules.vo.saleanalysis.DrugAnalysisVo;
import com.modules.vo.saleanalysis.JyReport;
import com.modules.vo.saleanalysis.MlReport;
import com.modules.vo.saleanalysis.SaleReport;
import com.modules.vo.saleanalysis.TreeAnalysisVo;

@Service
public class SaleAnalysisServiceImpl implements SaleAnalysisService {

	@Autowired
	private FxSaleDataDao fxSaleDataDao;

	@Autowired
	private DrugService drugService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private FxTreeService fxTreeService;

	@Override
	public List<DrugAnalysisVo> drugAnalysis(String userid, String storeid, Date startdate, Date enddate, final String stype, final String sfield) throws Exception {
		// TODO Auto-generated method stub

		Catalog catalog = new Catalog();
		catalog.setMyself(true);
		catalog.setUserid(userid);
		catalog = catalogService.findObject(catalog);

		Drug drug = new Drug();
		drug.setCatalogid(catalog.getId());

		List<Drug> drugs = drugService.findList(drug);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select drugid  ,sum(xse) xse,sum(xssl) xssl,IFNULL(sum(xse)/sum(xssl),0)  pjsj,sum(ml) ml,IFNULL( sum(ml)*100 /sum(xse),0) mlv from fx_sale_month where 1=1 ");
		if (startdate != null) {
			sql.append(" and month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and userid=?  ");
			ps.add(userid);
		}

		sql.append(" group by drugid ");

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());

		List<DrugAnalysisVo> drugAnalysisVos = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);
		BigDecimal total = new BigDecimal(0);

		for (DrugAnalysisVo da : drugAnalysisVos) {
			total = total.add(da.getXse());
			for (Drug d : drugs) {
				if (da.getDrugid().equals(d.getId())) {
					da.setCode(d.getCode());
					da.setName(d.getName());
					da.setSpecifications(d.getSpecifications());
					da.setFactory(d.getFactory());
					break;
				}
			}
		}

		for (DrugAnalysisVo da : drugAnalysisVos) {
			da.setXszb(da.getXse().multiply(new BigDecimal(10000)).divide(total, 10, RoundingMode.CEILING));
		}

		if (StringUtils.isNotBlank(sfield) && StringUtils.isNotBlank(stype))
			Collections.sort(drugAnalysisVos, new Comparator<DrugAnalysisVo>() {
				@Override
				public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
					if (sfield.equals("ml")) {
						if (o1.getMl() == o2.getMl())
							return 0;

						if (stype.equals("asc"))
							return o1.getMl().compareTo(o2.getMl());
						else {
							return -o1.getMl().compareTo(o2.getMl());
						}
					}
					if (sfield.equals("mlv")) {
						if (o1.getMlv() == o2.getMlv())
							return 0;

						if (stype.equals("asc"))
							return o1.getMlv().compareTo(o2.getMlv());
						else {
							return -o1.getMlv().compareTo(o2.getMlv());
						}
					}
					if (sfield.equals("xse")) {
						if (o1.getXse() == o2.getXse())
							return 0;

						if (stype.equals("asc"))
							return o1.getXse().compareTo(o2.getXse());
						else {
							return -o1.getXse().compareTo(o2.getXse());
						}
					}
					if (sfield.equals("xszb")) {
						if (o1.getXszb() == o2.getXszb())
							return 0;

						if (stype.equals("asc"))
							return o1.getXszb().compareTo(o2.getXszb());
						else {
							return -o1.getXszb().compareTo(o2.getXszb());
						}
					}
					if (sfield.equals("xssl")) {
						if (o1.getXssl() == o2.getXssl())
							return 0;

						if (stype.equals("asc"))
							return o1.getXssl().compareTo(o2.getXssl());
						else {
							return -o1.getXssl().compareTo(o2.getXssl());
						}
					}
					if (sfield.equals("pjsj")) {
						if (o1.getPjsj() == o2.getPjsj())
							return 0;

						if (stype.equals("asc"))
							return o1.getPjsj().compareTo(o2.getPjsj());
						else {
							return -o1.getPjsj().compareTo(o2.getPjsj());
						}
					}
					return 0;
				}
			});

		return drugAnalysisVos;
	}

	@Override
	public List<ComAnalysisVo> comnameAnalysis(String userid, String storeid, Date startdate, Date enddate, String name, final String stype, final String sfield) throws Exception {
		// TODO Auto-generated method stub
		// List<FxComname> coms = fxComnameService.findList(new FxComname());

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				" select comnameid,name, sum(xse) xse,sum(xssl) xssl,IFNULL(sum(xse)/sum(xssl),0) pjsj,IFNULL(sum(ml),0) ml,IFNULL(sum(ml)*100/sum(xse),0) mlv ,count(1) pgs from ( select drugid ,c.name ,d.comnameid ,sum(s.xse) xse,sum(xssl) xssl,IFNULL(sum(s.xse)/sum(s.xssl),0) pjsj,sum(ml) ml,sum(s.ml)/sum(s.xse)  mlv from fx_sale_month  s, fx_comname c,t_drug d where d.comnameid=c.id and d.id=s.drugid  ");

		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append("   and c.name like ? ");
			ps.add("%" + name + "%");
		}

		sql.append("   group by drugid ) a group by  comnameid ");

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());

		List<ComAnalysisVo> comAnalysisVos = fxSaleDataDao.findList(sqlContext, ComAnalysisVo.class);
		BigDecimal total = new BigDecimal(0);

		for (ComAnalysisVo da : comAnalysisVos) {
			total = total.add(da.getXse());
			if (StringUtils.isBlank(da.getComnameid())) {
				da.setName("未对照通用品种");
				continue;
			}
		}

		for (ComAnalysisVo da : comAnalysisVos) {
			da.setXszb(da.getXse().multiply(new BigDecimal(10000)).divide(total, 10, RoundingMode.CEILING));
		}

		if (StringUtils.isNotBlank(sfield) && StringUtils.isNotBlank(stype))
			Collections.sort(comAnalysisVos, new Comparator<ComAnalysisVo>() {
				@Override
				public int compare(ComAnalysisVo o1, ComAnalysisVo o2) {

					if (sfield.equals("pgs")) {

						if (o1.getPgs() == o2.getPgs())
							return 0;

						if (stype.equals("asc"))
							return o1.getPgs().compareTo(o2.getPgs());
						else {
							return -o1.getPgs().compareTo(o2.getPgs());
						}
					}

					if (sfield.equals("ml")) {
						if (o1.getMl() == o2.getMl())
							return 0;

						if (stype.equals("asc"))
							return o1.getMl().compareTo(o2.getMl());
						else {
							return -o1.getMl().compareTo(o2.getMl());
						}
					}
					if (sfield.equals("mlv")) {
						if (o1.getMlv() == o2.getMlv())
							return 0;

						if (stype.equals("asc"))
							return o1.getMlv().compareTo(o2.getMlv());
						else {
							return -o1.getMlv().compareTo(o2.getMlv());
						}
					}
					if (sfield.equals("xse")) {
						if (o1.getXse() == o2.getXse())
							return 0;

						if (stype.equals("asc"))
							return o1.getXse().compareTo(o2.getXse());
						else {
							return -o1.getXse().compareTo(o2.getXse());
						}
					}
					if (sfield.equals("xszb")) {
						if (o1.getXszb() == o2.getXszb())
							return 0;

						if (stype.equals("asc"))
							return o1.getXszb().compareTo(o2.getXszb());
						else {
							return -o1.getXszb().compareTo(o2.getXszb());
						}
					}
					if (sfield.equals("xssl")) {
						if (o1.getXssl() == o2.getXssl())
							return 0;

						if (stype.equals("asc"))
							return o1.getXssl().compareTo(o2.getXssl());
						else {
							return -o1.getXssl().compareTo(o2.getXssl());
						}
					}
					if (sfield.equals("pjsj")) {
						if (o1.getPjsj() == o2.getPjsj())
							return 0;

						if (stype.equals("asc"))
							return o1.getPjsj().compareTo(o2.getPjsj());
						else {
							return -o1.getPjsj().compareTo(o2.getPjsj());
						}
					}
					return 0;
				}
			});

		return comAnalysisVos;
	}

	@Override
	public List<TreeAnalysisVo> treeAnalysis(String userid, String storeid, Date startdate, Date enddate, final String stype, final String sfield) throws Exception {
		// TODO Auto-generated method stub
		FxTree rootTree = fxTreeService.findAnalysisRootTree(userid);

		List<FxTree> treedata = fxTreeService.findAnalysisTree(userid);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				" select c.name name,t.id treeid,sum(s.xssl) xssl,sum(s.xse) xse,sum(s.ml) ml,IFNULL( sum(s.xse)/sum(s.xssl),0) pjsj,IFNULL( sum(s.ml)*100/sum(s.xse),0) mlv,count(1) pgs  from  fx_sale_month s ,t_drug d,fx_comname_tree ct,fx_tree t   ");
		sql.append(",fx_comname c where s.drugid=d.id and ct.comnameid=d.comnameid    and c.id=d.comnameid  and ct.treeid=t.id and t.path like  ?   ");
		sql.append(" and s.userid=?   ");
		ps.add(rootTree.getId() + "%");
		ps.add(userid);
		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("   group by c.name,t.id ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<TreeAnalysisVo> treeAnalysisVos = fxSaleDataDao.findList(sqlContext, TreeAnalysisVo.class);

		List<TreeAnalysisVo> treeAnalysisVos2 = new ArrayList<TreeAnalysisVo>();

		BigDecimal total = new BigDecimal(0);

		for (TreeAnalysisVo da : treeAnalysisVos) {
			total = total.add(da.getXse());
		}
		if (total.compareTo(new BigDecimal(0)) == 0) {
			return treeAnalysisVos2;
		}

		// 一级分类
		for (FxTree tree : treedata) {
			if (tree.getPath().split("/").length == 2) {
				TreeAnalysisVo tv = new TreeAnalysisVo();
				tv.setTreeid(tree.getId());
				tv.setName(tree.getName());
				treeAnalysisVos2.add(tv);
				List<TreeAnalysisVo> treeAnalysisVos3 = new ArrayList<TreeAnalysisVo>();
				tv.setItems(treeAnalysisVos3);
			}
		}
		// 二级分类
		for (TreeAnalysisVo tv2 : treeAnalysisVos2) {
			for (FxTree tree : treedata) {
				if (tree.getParentid() != null)
					if (tree.getParentid().equals(tv2.getTreeid())) {
						TreeAnalysisVo tv = new TreeAnalysisVo();
						tv.setTreeid(tree.getId());
						tv.setName(tree.getName());
						tv2.getItems().add(tv);
						List<TreeAnalysisVo> treeAnalysisVos4 = new ArrayList<TreeAnalysisVo>();
						tv.setItems(treeAnalysisVos4);
					}
			}
		}

		/// 三级分类
		for (TreeAnalysisVo tv2 : treeAnalysisVos2) {
			for (TreeAnalysisVo tv3 : tv2.getItems()) {
				// for (FxTree tree : treedata) {
				// if (tree.getParentid() != null)
				// if (tree.getParentid().equals(tv3.getTreeid())) {
				// TreeAnalysisVo tv = new TreeAnalysisVo();
				// tv.setTreeid(tree.getId());
				// tv.setName(tree.getName());
				// tv3.getItems().add(tv);
				for (TreeAnalysisVo ttv : treeAnalysisVos) {
					if (ttv.getTreeid().equals(tv3.getTreeid())) {
						ttv.setXszb(ttv.getXse().multiply(new BigDecimal(10000)).divide(total, 10, RoundingMode.CEILING));
						// tv.setMl(ttv.getMl());
						// tv.setMlv(ttv.getMlv());
						// tv.setPgs(ttv.getPgs());
						// tv.setPjsj(ttv.getPjsj());
						// tv.setXse(ttv.getXse());
						// tv.setXssl(ttv.getXssl());
						// tv.setXszb(ttv.getXszb());

						tv3.getItems().add(ttv);
						// break loop;
					}
					// }
					// }
				}
			}
		}

		for (TreeAnalysisVo tv2 : treeAnalysisVos2) {
			for (TreeAnalysisVo tv3 : tv2.getItems()) {
				BigDecimal ml = new BigDecimal(0);
				BigDecimal mlv = new BigDecimal(0);
				Integer pgs = 0;
				BigDecimal pjsj = new BigDecimal(0);
				BigDecimal xse = new BigDecimal(0);
				BigDecimal xssl = new BigDecimal(0);
				BigDecimal xszb = new BigDecimal(0);
				int i = 0;
				if (tv3.getItems() != null) {
					for (TreeAnalysisVo ttv : tv3.getItems()) {
						if (ttv.getMl() == null) {
							continue;
						}
						if (ttv.getXse().compareTo(new BigDecimal(0)) == 1)
							i++;
						ml = ml.add(ttv.getMl());
						mlv = mlv.add(ttv.getMlv());
						pgs = pgs + ttv.getPgs();
						pjsj = pjsj.add(ttv.getPjsj());
						xse = xse.add(ttv.getXse());
						xssl = xssl.add(ttv.getXssl());
						xszb = xszb.add(ttv.getXszb());
					}
				}
				tv3.setMl(ml);
				tv3.setMlv(mlv);
				if (i > 0)
					tv3.setMlv(mlv.divide(new BigDecimal(i), 10, RoundingMode.CEILING));
				tv3.setPgs(pgs);

				tv3.setPjsj(pjsj);
				if (i > 0)
					tv3.setPjsj(pjsj.divide(new BigDecimal(i), 10, RoundingMode.CEILING));

				tv3.setXse(xse);
				tv3.setXssl(xssl);
				tv3.setXszb(xse.multiply(new BigDecimal(10000)).divide(total, 10, RoundingMode.CEILING));
			}
		}

		for (TreeAnalysisVo tv2 : treeAnalysisVos2) {
			BigDecimal ml = new BigDecimal(0);
			BigDecimal mlv = new BigDecimal(0);
			Integer pgs = 0;
			BigDecimal pjsj = new BigDecimal(0);
			BigDecimal xse = new BigDecimal(0);
			BigDecimal xssl = new BigDecimal(0);
			BigDecimal xszb = new BigDecimal(0);
			int i = 0;
			if (tv2.getItems() != null)
				for (TreeAnalysisVo ttv : tv2.getItems()) {
					if (ttv.getMl() == null) {
						continue;
					}
					if (ttv.getXse().compareTo(new BigDecimal(0)) == 1)
						i++;
					ml = ml.add(ttv.getMl());
					mlv = mlv.add(ttv.getMlv());
					pgs = pgs + ttv.getPgs();
					pjsj = pjsj.add(ttv.getPjsj());
					xse = xse.add(ttv.getXse());
					xssl = xssl.add(ttv.getXssl());
					xszb = xszb.add(ttv.getXszb());
				}
			tv2.setMl(ml);
			tv2.setMlv(mlv);
			if (i > 0)
				tv2.setMlv(mlv.divide(new BigDecimal(i), 10, RoundingMode.CEILING));
			tv2.setPgs(pgs);
			tv2.setPjsj(pjsj);
			if (i > 0)
				tv2.setPjsj(pjsj.divide(new BigDecimal(i), 10, RoundingMode.CEILING));

			tv2.setXse(xse);
			tv2.setXssl(xssl);
			tv2.setXszb(xse.multiply(new BigDecimal(10000)).divide(total, 10, RoundingMode.CEILING));
		}

		if (StringUtils.isNotBlank(sfield) && StringUtils.isNotBlank(stype))
			Collections.sort(treeAnalysisVos2, new Comparator<TreeAnalysisVo>() {
				@Override
				public int compare(TreeAnalysisVo o1, TreeAnalysisVo o2) {
					if (sfield.equals("ml")) {
						if (o1.getMl() == o2.getMl())
							return 0;

						if (stype.equals("asc"))
							return o1.getMl().compareTo(o2.getMl());
						else {
							return -o1.getMl().compareTo(o2.getMl());
						}
					}
					if (sfield.equals("mlv")) {
						if (o1.getMlv() == o2.getMlv())
							return 0;

						if (stype.equals("asc"))
							return o1.getMlv().compareTo(o2.getMlv());
						else {
							return -o1.getMlv().compareTo(o2.getMlv());
						}
					}
					if (sfield.equals("xse")) {
						if (o1.getXse() == o2.getXse())
							return 0;

						if (stype.equals("asc"))
							return o1.getXse().compareTo(o2.getXse());
						else {
							return -o1.getXse().compareTo(o2.getXse());
						}
					}
					if (sfield.equals("xszb")) {
						if (o1.getXszb() == o2.getXszb())
							return 0;

						if (stype.equals("asc"))
							return o1.getXszb().compareTo(o2.getXszb());
						else {
							return -o1.getXszb().compareTo(o2.getXszb());
						}
					}
					if (sfield.equals("xssl")) {
						if (o1.getXssl() == o2.getXssl())
							return 0;

						if (stype.equals("asc"))
							return o1.getXssl().compareTo(o2.getXssl());
						else {
							return -o1.getXssl().compareTo(o2.getXssl());
						}
					}
					if (sfield.equals("pjsj")) {
						if (o1.getPjsj() == o2.getPjsj())
							return 0;

						if (stype.equals("asc"))
							return o1.getPjsj().compareTo(o2.getPjsj());
						else {
							return -o1.getPjsj().compareTo(o2.getPjsj());
						}
					}
					return 0;
				}
			});

		return treeAnalysisVos2;
	}

	@Override
	public List<SaleReport> saleReport(String userid, String storeid, Integer status) throws Exception {
		// TODO Auto-generated method stub

		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		Date currYearFirst = calendar.getTime();

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		if (status == 1) {
			sql.append("select date_format(month, '%m') yf ,sum(xse) xse from fx_sale_month where month>= ? and month<=?   ");
		} else if (status == 2) {
			sql.append("select concat(FLOOR((date_format(month, '%m')+2)/3)) yf ,sum(price) xse from fx_sale_month where month>= ? and month<=?  ");
		}
		ps.add(currYearFirst);
		ps.add(currYearLast);
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and userid=?  ");
			ps.add(userid);
		}

		if (status == 1) {
			sql.append("  group by date_format(month, '%m')   ");
		} else if (status == 2) {
			sql.append(" group by concat(date_format(month, '%Y'),FLOOR((date_format(month, '%m')+2)/3))   ");
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<SaleReport> salereports = fxSaleDataDao.findList(sqlContext, SaleReport.class);

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear - 1);
		Date aFirst = calendar.getTime();

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear - 1);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date aLast = calendar.getTime();
		ps.clear();
		ps.add(aFirst);
		ps.add(aLast);
		if (StringUtils.isNotBlank(storeid)) {
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			ps.add(userid);
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<SaleReport> salereports2 = fxSaleDataDao.findList(sqlContext, SaleReport.class);

		for (SaleReport sr : salereports) {
			for (SaleReport sr2 : salereports2) {
				if (sr2.getYf().equals(sr.getYf())) {
					sr.setTb((sr.getXse().subtract(sr2.getXse())).multiply(new BigDecimal(100)).divide(sr2.getXse(), 10, RoundingMode.CEILING));
					break;
				}
			}
		}
		for (int i = 0; i < salereports.size(); i++) {
			if (i == 0) {
				salereports.get(i).setLjxse(salereports.get(i).getXse());
				if (salereports2.size() > 0)
					salereports.get(i).setHb((salereports.get(i).getXse().subtract(salereports2.get(salereports2.size() - 1).getXse())).multiply(new BigDecimal(100))
							.divide(salereports2.get(salereports2.size() - 1).getXse(), 10, RoundingMode.CEILING));
			} else {
				salereports.get(i).setLjxse(salereports.get(i).getXse().add(salereports.get(i - 1).getLjxse()));
				salereports.get(i)
						.setHb((salereports.get(i).getXse().subtract(salereports.get(i - 1).getXse())).multiply(new BigDecimal(100)).divide(salereports.get(i - 1).getXse(), 10, RoundingMode.CEILING));
			}
		}

		for (SaleReport sr : salereports) {
			if (status == 1)
				sr.setRjxse(sr.getXse().divide(new BigDecimal(DateUtil.getMonthLastDay(currentYear, Integer.parseInt(sr.getYf()))), 10, RoundingMode.CEILING));
			else if (status == 2)
				sr.setRjxse(sr.getXse().divide(new BigDecimal(DateUtil.getSeasonLastDay(currentYear, Integer.parseInt(sr.getYf()))), 10, RoundingMode.CEILING));
		}

		return salereports;
	}

	@Override
	public List<MlReport> mlReport(String userid, String storeid, Integer status) throws Exception {
		// TODO Auto-generated method stub
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		Date currYearFirst = calendar.getTime();

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();

		if (status == 1) {
			sql.append("select date_format(month, '%m') yf ,sum(ml) ml from fx_sale_month where month>= ? and month<=?    ");
		} else if (status == 2) {
			sql.append("select concat(FLOOR((date_format(month, '%m')+2)/3)) yf ,sum(ml) ml from fx_sale_month where month>= ? and month<=?  ");
		}
		ps.add(currYearFirst);
		ps.add(currYearLast);
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and userid=?  ");
			ps.add(userid);
		}
		if (status == 1) {
			sql.append(" group by date_format(month, '%m')   ");
		} else if (status == 2) {
			sql.append("  group by concat(date_format(month, '%Y'),FLOOR((date_format(month, '%m')+2)/3))  ");
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<MlReport> mlreports = fxSaleDataDao.findList(sqlContext, MlReport.class);

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear - 1);
		Date aFirst = calendar.getTime();

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear - 1);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date aLast = calendar.getTime();
		ps.clear();
		ps.add(aFirst);
		ps.add(aLast);
		if (StringUtils.isNotBlank(storeid)) {
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			ps.add(userid);
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<MlReport> mlreports2 = fxSaleDataDao.findList(sqlContext, MlReport.class);

		for (MlReport sr : mlreports) {
			for (MlReport sr2 : mlreports2) {
				if (sr2.getYf().equals(sr.getYf())) {
					sr.setTb((sr.getMl().subtract(sr2.getMl())).multiply(new BigDecimal(100)).divide(sr2.getMl(), 10, RoundingMode.CEILING));
					break;
				}
			}
		}
		for (int i = 0; i < mlreports.size(); i++) {
			if (i == 0) {
				mlreports.get(i).setLjml(mlreports.get(i).getMl());
				if (mlreports2.size() > 0)
					mlreports.get(i).setHb((mlreports.get(i).getMl().subtract(mlreports2.get(mlreports2.size() - 1).getMl())).multiply(new BigDecimal(100))
							.divide(mlreports2.get(mlreports2.size() - 1).getMl(), 10, RoundingMode.CEILING));
			} else {
				mlreports.get(i).setLjml(mlreports.get(i).getMl().add(mlreports.get(i - 1).getLjml()));
				mlreports.get(i).setHb((mlreports.get(i).getMl().subtract(mlreports.get(i - 1).getMl())).multiply(new BigDecimal(100)).divide(mlreports.get(i - 1).getMl(), 10, RoundingMode.CEILING));
			}
		}

		for (MlReport mr : mlreports) {
			if (status == 1)
				mr.setRjml(mr.getMl().divide(new BigDecimal(DateUtil.getMonthLastDay(currentYear, Integer.parseInt(mr.getYf()))), 10, RoundingMode.CEILING));
			else if (status == 2)
				mr.setRjml(mr.getMl().divide(new BigDecimal(DateUtil.getSeasonLastDay(currentYear, Integer.parseInt(mr.getYf()))), 10, RoundingMode.CEILING));
		}

		return mlreports;
	}

	@Override
	public List<JyReport> jyReport(String userid, String storeid, Integer status) throws Exception {
		// TODO Auto-generated method stub
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		Date currYearFirst = calendar.getTime();

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();

		if (status == 1) {
			sql.append(
					"select  CAST( count(1)  AS decimal)  as jycs,yf,avg(price) as pjkdj from  (select orderno,date_format(saledate, '%m') yf,sum(price) price from fx_sale_data where saledate>= ? and saledate<=?  ");
		} else if (status == 2) {
			sql.append(
					"select  CAST( count(1)  AS decimal)  as jycs,yf,avg(price) as pjkdj from  (select orderno,concat(FLOOR((date_format(saledate, '%m')+2)/3)) yf,sum(price) price from fx_sale_data where saledate>= ? and saledate<=?   ");
		}
		ps.add(currYearFirst);
		ps.add(currYearLast);
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and userid=?  ");
			ps.add(userid);
		}
		if (status == 1) {
			sql.append("  group by orderno,date_format(saledate, '%m')) a group by  yf ");
		} else if (status == 2) {
			sql.append("  group by orderno,concat(date_format(saledate, '%Y'),FLOOR((date_format(saledate, '%m')+2)/3))) a group by  yf ");
		}

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<JyReport> mlreports = fxSaleDataDao.findList(sqlContext, JyReport.class);

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear - 1);
		Date aFirst = calendar.getTime();

		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear - 1);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date aLast = calendar.getTime();
		ps.clear();
		ps.add(aFirst);
		ps.add(aLast);
		if (StringUtils.isNotBlank(storeid)) {
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			ps.add(userid);
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<JyReport> mlreports2 = fxSaleDataDao.findList(sqlContext, JyReport.class);

		for (JyReport sr : mlreports) {
			for (JyReport sr2 : mlreports2) {
				if (sr2.getYf().equals(sr.getYf())) {
					sr.setTb((sr.getJycs().subtract(sr2.getJycs())).multiply(new BigDecimal(100)).divide(sr2.getJycs(), 10, RoundingMode.CEILING));
					break;
				}
			}
		}
		for (int i = 0; i < mlreports.size(); i++) {
			if (i == 0) {
				mlreports.get(i).setLjjycs(mlreports.get(i).getJycs());
				if (mlreports2.size() > 0)
					mlreports.get(i).setHb((mlreports.get(i).getJycs().subtract(mlreports2.get(mlreports2.size() - 1).getJycs())).multiply(new BigDecimal(100))
							.divide(mlreports2.get(mlreports2.size() - 1).getJycs(), 10, RoundingMode.CEILING));
			} else {
				mlreports.get(i).setLjjycs(mlreports.get(i).getJycs().add(mlreports.get(i - 1).getLjjycs()));
				mlreports.get(i)
						.setHb((mlreports.get(i).getJycs().subtract(mlreports.get(i - 1).getJycs())).multiply(new BigDecimal(100)).divide(mlreports.get(i - 1).getJycs(), 10, RoundingMode.CEILING));
			}
		}

		for (JyReport mr : mlreports) {

			if (status == 1)
				mr.setRjjycs(mr.getJycs().divide(new BigDecimal(DateUtil.getMonthLastDay(currentYear, Integer.parseInt(mr.getYf()))), 10, RoundingMode.CEILING));
			else if (status == 2)
				mr.setRjjycs(mr.getJycs().divide(new BigDecimal(DateUtil.getSeasonLastDay(currentYear, Integer.parseInt(mr.getYf()))), 10, RoundingMode.CEILING));

		}

		return mlreports;
	}

	@Override
	public List<DrugVo> eliminateAnalysis(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception {
		// TODO Auto-generated method stub
		List<DrugAnalysisVo> lv30 = new ArrayList<DrugAnalysisVo>();
		List<DrugAnalysisVo> lv5 = new ArrayList<DrugAnalysisVo>();
		List<DrugVo> ds = new ArrayList<DrugVo>();
		FxTree fxtree = new FxTree();
		fxtree.setId(treeid);
		fxtree = fxTreeService.findObject(fxtree);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select drugid,treeid,d.code,d.name,d.specifications,d.factory  ,sum(s.xse) xse,sum(xssl) xssl ,sum(s.ml) ml,IFNULL(sum(s.ml)*100 /sum(s.xse),0) mlv");
		sql.append("  from fx_sale_month s,t_drug d,fx_comname_tree ct,fx_tree t where  d.id=s.drugid and ct.comnameid=d.comnameid ");

		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("  and ct.treeid=t.id and path like ?  group by drugid,d.code,d.name,d.specifications,d.factory,treeid");
		ps.add(fxtree.getPath() + "%");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);
		List<FxTree> fxTrees = fxTreeService.findByPath("654CA2E6C2164A148F287B57A4483AF7");

		Map<String, List<DrugAnalysisVo>> mtdvs = new HashMap<String, List<DrugAnalysisVo>>();
		for (FxTree tree : fxTrees) {
			if (tree.getPath().split("/").length == 4) {
				for (DrugAnalysisVo dv : drugAnalysisVos) {
					if (dv.getTreeid().equals(tree.getId())) {
						dv.setTreeid(tree.getPath());
						if (!mtdvs.containsKey(tree.getId())) {
							mtdvs.put(tree.getId(), new ArrayList<DrugAnalysisVo>());
						}
						mtdvs.get(tree.getId()).add(dv);
					}
				}
			}
		}
		for (Map.Entry<String, List<DrugAnalysisVo>> entry : mtdvs.entrySet()) {
			for (Map.Entry<DrugAnalysisVo, Integer> scoresMap : countScore(entry.getValue()).entrySet()) {
				if (scoresMap.getValue() <= 30) {
					lv30.add(scoresMap.getKey());
				}
			}
			lv5.addAll(countMlv(entry.getValue()));
		}

		for (DrugAnalysisVo id : lv30) {
			DrugVo d = new DrugVo();
			d.setId(id.getDrugid());
			d.setCode(id.getCode());
			d.setSpecifications(id.getSpecifications());
			d.setFactory(id.getFactory());
			d.setTree(id.getTreeid());
			d.setName(id.getName());
			d.setRemark("综合评分小于30");
			ds.add(d);
		}

		loop: for (DrugAnalysisVo id : lv5) {
			for (DrugVo d : ds) {
				if (d.getId().equals(id.getDrugid())) {
					d.setRemark(d.getRemark() + ",毛利小于平均毛利5%");
					continue loop;
				}
			}
			DrugVo d = new DrugVo();
			d.setId(id.getDrugid());
			d.setCode(id.getCode());
			d.setTree(id.getTreeid());
			d.setSpecifications(id.getSpecifications());
			d.setFactory(id.getFactory());
			d.setName(id.getName());
			d.setRemark("毛利小于平均毛利5%");
			ds.add(d);
		}

		for (DrugVo d : ds) {
			for (FxTree tree : fxTrees) {
				d.setTree(d.getTree().replaceAll(tree.getId(), tree.getName()));
			}
		}
		return ds;
	}

	private Map<DrugAnalysisVo, Integer> countScore(List<DrugAnalysisVo> dvs) {
		Map<DrugAnalysisVo, Integer> scoresMap = new HashMap<DrugAnalysisVo, Integer>();
		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getXse().compareTo(o2.getXse());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			DrugAnalysisVo dv = dvs.get(i - 1);
			scoresMap.put(dv, 3 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getXssl().compareTo(o2.getXssl());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			DrugAnalysisVo dv = dvs.get(i - 1);
			scoresMap.put(dv, scoresMap.get(dv) + 3 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getMl().compareTo(o2.getMl());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			DrugAnalysisVo dv = dvs.get(i - 1);
			scoresMap.put(dv, scoresMap.get(dv) + 2 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				return -o1.getMlv().compareTo(o2.getMlv());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			DrugAnalysisVo dv = dvs.get(i - 1);
			scoresMap.put(dv, scoresMap.get(dv) + 2 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		return scoresMap;
	}

	private BigDecimal countPjmlv(List<DrugAnalysisVo> dvs) {
		BigDecimal ml = new BigDecimal(0);
		BigDecimal xse = new BigDecimal(0);
		for (DrugAnalysisVo dv : dvs) {
			ml = ml.add(dv.getMl());
			xse = xse.add(dv.getXse());
		}
		if (xse.compareTo(new BigDecimal(0)) > 0) {
			return ml.divide(xse, 10, RoundingMode.CEILING).multiply(new BigDecimal(100));
		} else {
			return new BigDecimal(0);
		}

	}

	private List<DrugAnalysisVo> countMlv(List<DrugAnalysisVo> dvs) {
		List<DrugAnalysisVo> drugids = new ArrayList<DrugAnalysisVo>();
		BigDecimal pjmlv = countPjmlv(dvs);
		for (DrugAnalysisVo dv : dvs) {
			if (pjmlv.subtract(dv.getMlv()).compareTo(new BigDecimal(5)) > 0) {
				drugids.add(dv);
			}
		}
		return drugids;
	}

	@Override
	public List<PriceBandVo> priceBand(String userid, String storeid, Date startdate, Date enddate, String treeid) throws Exception {
		// TODO Auto-generated method stub

		FxTree tree = new FxTree();
		tree.setId(treeid);
		tree = fxTreeService.findObject(tree);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select   sum(s.xse) xse,sum(xssl) xssl , sum(s.xse)/sum(xssl)  price,sum(s.ml) ml  ");
		sql.append("  from fx_sale_month s,t_drug d,fx_comname_tree ct,fx_tree t where   d.id=s.drugid and ct.comnameid=d.comnameid ");
		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("  and ct.treeid=t.id and path like  ?  group by   drugid");
		ps.add(tree.getPath() + "%");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		List<PriceBandVo> data = new ArrayList<PriceBandVo>();

		BigDecimal minPrice = null;
		BigDecimal maxPrice = null;

		for (DrugAnalysisVo dav : drugAnalysisVos) {
			if (dav.getPrice() != null) {
				if (minPrice == null) {
					minPrice = dav.getPrice();
					maxPrice = dav.getPrice();
				}
				if (minPrice.compareTo(dav.getPrice()) == 1) {
					minPrice = dav.getPrice();
				}
				if (maxPrice.compareTo(dav.getPrice()) == -1) {
					maxPrice = dav.getPrice();
				}
			}
		}

		if (minPrice == null)
			return data;
		minPrice = minPrice.divide(new BigDecimal(10)).setScale(0, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(10));
		maxPrice = maxPrice.divide(new BigDecimal(10)).setScale(0, BigDecimal.ROUND_UP).multiply(new BigDecimal(10));

		BigDecimal num = maxPrice.subtract(minPrice).divide(new BigDecimal(10), 10, RoundingMode.CEILING);

		int count = 10;

		if (maxPrice.subtract(minPrice).compareTo(new BigDecimal(10)) == -1) {
			count = maxPrice.subtract(minPrice).divide(new BigDecimal(2), 10, RoundingMode.CEILING).intValue();
		}

		if (maxPrice.compareTo(minPrice) == 0) {
			count = 0;
		}

		for (int i = 0; i <= count; i++) {
			PriceBandVo pv = new PriceBandVo();
			pv.setPzs(0);
			pv.setPrice((minPrice.intValue() + i * num.doubleValue()) + "-" + (minPrice.intValue() + (i + 1) * num.doubleValue()));
			pv.setXse(new BigDecimal(0));
			pv.setMl(new BigDecimal(0));
			for (DrugAnalysisVo dav : drugAnalysisVos) {
				if (dav.getPrice() != null) {
					if (dav.getPrice().compareTo(minPrice.add(num.multiply(new BigDecimal(i)))) >= 0 && dav.getPrice().compareTo(minPrice.add(num.multiply(new BigDecimal(i + 1)))) == -1) {
						pv.setPzs(pv.getPzs() + 1);
						pv.setXse(pv.getXse().add(dav.getXse()));
						pv.setMl(pv.getMl().add(dav.getMl()));
					}
				}
			}
			data.add(pv);
		}

		return data;
	}

	@Override
	public List<DrugVo> scoreAnalysis(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception {
		// TODO Auto-generated method stub
		List<DrugVo> ds = new ArrayList<DrugVo>();
		FxTree fxtree = new FxTree();
		fxtree.setId(treeid);
		fxtree = fxTreeService.findObject(fxtree);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select drugid,treeid,d.code,d.name,d.specifications,d.factory  ,sum(s.xse) xse,sum(xssl) xssl ,sum(s.ml) ml,IFNULL(sum(s.ml)*100 /sum(s.xse),0) mlv");
		sql.append("  from fx_sale_month s,t_drug d,fx_comname_tree ct,fx_tree t where  d.id=s.drugid and ct.comnameid=d.comnameid ");
		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("  and ct.treeid=t.id and path like ?  group by drugid,d.code,d.name,d.specifications,d.factory,treeid");
		ps.add(fxtree.getPath() + "%");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);
		List<FxTree> fxTrees = fxTreeService.findByPath("654CA2E6C2164A148F287B57A4483AF7");

		loop: for (DrugAnalysisVo id : drugAnalysisVos) {
			for (DrugVo d : ds) {
				if (d.getId().equals(id.getDrugid()) && d.getTreeid().equals(id.getTreeid())) {
					continue loop;
				}
			}
			DrugVo d = new DrugVo();
			d.setId(id.getDrugid());
			d.setCode(id.getCode());
			d.setTreeid(id.getTreeid());
			d.setTree(id.getTreeid());
			d.setSpecifications(id.getSpecifications());
			d.setFactory(id.getFactory());
			d.setName(id.getName());
			d.setXse(id.getXse());
			d.setXssl(id.getXssl());
			d.setMl(id.getMl());
			d.setMlv(id.getMlv());
			ds.add(d);
		}

		Map<String, List<DrugAnalysisVo>> mtdvs = new HashMap<String, List<DrugAnalysisVo>>();
		for (FxTree tree : fxTrees) {
			if (tree.getPath().split("/").length == 4) {
				for (DrugAnalysisVo dv : drugAnalysisVos) {
					if (dv.getTreeid().equals(tree.getId())) {
						dv.setTreeid(tree.getId());
						if (!mtdvs.containsKey(tree.getId())) {
							mtdvs.put(tree.getId(), new ArrayList<DrugAnalysisVo>());
						}
						mtdvs.get(tree.getId()).add(dv);
					}
				}
			}
		}
		for (Map.Entry<String, List<DrugAnalysisVo>> entry : mtdvs.entrySet()) {
			for (Map.Entry<DrugAnalysisVo, Integer> scoresMap : countScore(entry.getValue()).entrySet()) {
				for (DrugVo dv : ds) {
					if (scoresMap.getKey().getDrugid().equals(dv.getId()) && dv.getTreeid().equals(scoresMap.getKey().getTreeid())) {
						dv.setScore(scoresMap.getValue());
						dv.setPjmlv(countPjmlv(entry.getValue()));
					}
				}
			}
		}
		for (DrugVo d : ds) {
			for (FxTree tree : fxTrees) {
				d.setTree(d.getTree().replaceAll(tree.getId(), tree.getName()));
			}
		}
		return ds;
	}

	@Override
	public List<DrugVo> gmlAnalysis(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception {
		// TODO Auto-generated method stub

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select count(1) from (select orderno from fx_sale_data  where  1=1");
		if (startdate != null) {
			sql.append(" and  saledate >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and  saledate<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and  userid=?  ");
			ps.add(userid);
		}
		sql.append(" group by orderno) a");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		Integer total = fxSaleDataDao.findE(sqlContext, Integer.class);

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append("select drugid id,count(1)  gmpc from( select orderno,drugid from fx_sale_data  where 1=1  ");
		if (startdate != null) {
			sql.append(" and  saledate >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and saledate<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and  storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and  userid=?  ");
			ps.add(userid);
		}
		sql.append("   group by orderno,drugid ) a group by drugid order by count(1) desc");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugVo> data = fxSaleDataDao.findList(sqlContext, DrugVo.class);

		Catalog catalog = new Catalog();
		catalog.setMyself(true);
		catalog.setUserid(userid);
		catalog = catalogService.findObject(catalog);

		Drug drug = new Drug();
		drug.setCatalogid(catalog.getId());
		List<Drug> drugs = drugService.findList(drug);

		for (DrugVo dv : data) {
			for (Drug d : drugs) {
				if (dv.getId().equals(d.getId())) {
					dv.setCode(d.getCode());
					dv.setName(d.getName());
					dv.setSpecifications(d.getSpecifications());
					dv.setFactory(d.getFactory());
					dv.setUnit(d.getUnit());
					dv.setGmlv(new BigDecimal(dv.getGmpc()).divide(new BigDecimal(total), 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
				}
			}
		}

		return data;
	}

	@Override
	public List<PriceBandVo2> priceBand2(String userid, String storeid, Date startdate, Date enddate, String treeid) throws Exception {
		// TODO Auto-generated method stub

		List<PriceBandVo2> data = new ArrayList<PriceBandVo2>();

		data.add(new PriceBandVo2("10元以下", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("10-15元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("15-20元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("20-25元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("25-30元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("30-35元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("35-40元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("40-60元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("60-80元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("80-100元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("100-200元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("200元以上", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select drugid,IFNULL(avg(s.xse/s.xssl),0) price ,sum(s.xse) xse, sum(s.ml) ml   from fx_sale_month s  where  1=1 ");
		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("  group by drugid  ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		BigDecimal total = new BigDecimal(0);
		BigDecimal mltotal = new BigDecimal(0);

		for (DrugAnalysisVo dv : drugAnalysisVos) {
			total = dv.getXse().add(total);
			mltotal = dv.getMl().add(mltotal);
		}

		for (DrugAnalysisVo dv : drugAnalysisVos) {
			loop: for (PriceBandVo2 pd : data) {
				if (dv.getPrice().compareTo(new BigDecimal(0)) == 1)
					if (dv.getPrice().subtract(new BigDecimal(10)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("10元以下")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(15)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("10-15元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(20)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("15-20元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(25)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("20-25元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(30)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("25-30元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(35)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("30-35元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(40)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("35-40元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(60)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("40-60元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(80)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("60-80元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(100)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("80-100元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if (dv.getPrice().subtract(new BigDecimal(200)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("100-200元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					} else if ((dv.getPrice().subtract(new BigDecimal(200)).compareTo(new BigDecimal(0)) == 1 || dv.getPrice().subtract(new BigDecimal(200)).compareTo(new BigDecimal(0)) == 0)
							&& pd.getJgd().equals("200元以上")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break loop;
					}
			}

		}

		for (PriceBandVo2 pd : data) {
			pd.setXszb(pd.getXszb().divide(total, 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
			pd.setMlzb(pd.getMlzb().divide(mltotal, 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
			pd.setDpzb(pd.getDpzb().divide(new BigDecimal(drugAnalysisVos.size()), 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
		}

		return data;
	}

	@Override
	public List<PriceBandVo2> customerBand(String userid, String storeid, Date startdate, Date enddate) throws Exception {
		// TODO Auto-generated method stub

		List<PriceBandVo2> data = new ArrayList<PriceBandVo2>();
		data.add(new PriceBandVo2("10元以下", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("10-15元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("15-20元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("20-25元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("25-30元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("30-35元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("35-40元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("40-60元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("60-80元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("80-100元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("100-200元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("200-300元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("300-500元", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));
		data.add(new PriceBandVo2("500元以上", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0));

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select orderno,IFNULL(sum(s.price),0) price ,IFNULL(sum(s.price),0) xse, sum(s.price)-sum(s.costprice) ml  from fx_sale_data s  where 1=1  ");
		if (startdate != null) {
			sql.append(" and s.saledate >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.saledate<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("  group by orderno ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		BigDecimal total = new BigDecimal(0);
		BigDecimal mltotal = new BigDecimal(0);

		for (DrugAnalysisVo dv : drugAnalysisVos) {
			total = dv.getXse().add(total);
			mltotal = dv.getMl().add(mltotal);
		}

		for (DrugAnalysisVo dv : drugAnalysisVos) {
			for (PriceBandVo2 pd : data) {
				if (dv.getPrice().compareTo(new BigDecimal(0)) == 1)
					if (dv.getPrice().subtract(new BigDecimal(10)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("10元以下")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setPzs(pd.getPzs() + 1);
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(15)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("10-15元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(20)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("15-20元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(25)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("20-25元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(30)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("25-30元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(35)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("30-35元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(40)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("35-40元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(60)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("40-60元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(80)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("60-80元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(100)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("80-100元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(200)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("100-200元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(300)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("200-300元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if (dv.getPrice().subtract(new BigDecimal(500)).compareTo(new BigDecimal(0)) == -1 && pd.getJgd().equals("300-500元")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					} else if ((dv.getPrice().subtract(new BigDecimal(500)).compareTo(new BigDecimal(0)) == 1 || dv.getPrice().subtract(new BigDecimal(500)).compareTo(new BigDecimal(0)) == 0)
							&& pd.getJgd().equals("500元以上")) {
						pd.setXszb(pd.getXszb().add(dv.getXse()));
						pd.setMlzb(pd.getMlzb().add(dv.getMl()));
						pd.setDpzb(pd.getDpzb().add(new BigDecimal(1)));
						pd.setPzs(pd.getPzs() + 1);
						break;
					}
			}

		}

		for (PriceBandVo2 pd : data) {
			pd.setXszb(pd.getXszb().divide(total, 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
			pd.setMlzb(pd.getMlzb().divide(mltotal, 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
			pd.setDpzb(pd.getDpzb().divide(new BigDecimal(drugAnalysisVos.size()), 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
		}
		return data;
	}

	public List<DrugAnalysisVo> countByTreeid(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception {

		FxTree fxtree = new FxTree();
		fxtree.setId(treeid);
		fxtree = fxTreeService.findObject(fxtree);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select  sum(s.xse) xse,sum(s.ml) ml ,IFNULL((sum(ml))/sum(s.xse),0)  mlv ");
		sql.append("  from fx_sale_month s,t_drug d,fx_comname_tree ct,fx_tree t where   d.id=s.drugid and ct.comnameid=d.comnameid ");

		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("  and ct.treeid=t.id and path like ?  ");
		ps.add(fxtree.getPath() + "%");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVos = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append("  select count(1) from ( select drugid ");
		sql.append("  from fx_sale_month s,t_drug d,fx_comname_tree ct,fx_tree t where  d.id=s.drugid and ct.comnameid=d.comnameid ");
		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (storeid != null) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (userid != null) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("  and ct.treeid=t.id and path like ?  ");
		ps.add(fxtree.getPath() + "%");
		sql.append("   group by drugid ) a");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		Integer num = fxSaleDataDao.findE(sqlContext, Integer.class);
		drugAnalysisVos.get(0).setNum(num);

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append(" select  sum(s.xse) xse  ,sum(s.ml) ml , IFNULL( sum(s.ml)/sum(s.xse),0)  mlv    ");
		sql.append("  from fx_sale_month s  where  1=1  ");
		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> drugAnalysisVo2s = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append("  select count(1) from ( select drugid ");
		sql.append("  from fx_sale_month s where  1=1 ");
		if (startdate != null) {
			sql.append(" and s.month >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.month<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}
		sql.append("    group by drugid ) a");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		Integer num2 = fxSaleDataDao.findE(sqlContext, Integer.class);
		drugAnalysisVo2s.get(0).setNum(num2);
		drugAnalysisVos.addAll(drugAnalysisVo2s);
		return drugAnalysisVos;

	}

	public List<DrugAnalysisVo> xkAnalysis(String userid, String storeid, Date startdate, Date enddate) throws Exception {

		List<DrugAnalysisVo> data = new ArrayList<DrugAnalysisVo>();
		List<String> ordernos = new ArrayList<String>();

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select   drugid,orderno ,sum(qty) xssl, sum(s.price) xse,sum(s.price)-sum(s.costprice) ml from fx_sale_data s   where   1=1");
		if (startdate != null) {
			sql.append(" and s.saledate >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.saledate<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}

		sql.append(" group by drugid,orderno having IFNULL((sum(s.price)-sum(s.costprice))/sum(s.price),0) <0.05");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> xkDs = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		loop: for (DrugAnalysisVo xk : xkDs) {
			for (DrugAnalysisVo d : data) {
				if (xk.getDrugid().equals(d.getDrugid())) {
					d.setXse(d.getXse().add(xk.getXse()));
					d.setMl(d.getMl().add(xk.getMl()));
					d.setXssl(d.getXssl().add(xk.getXssl()));
					continue loop;
				}
			}
			xk.setOrderxse(new BigDecimal(0));
			xk.setOrderml(new BigDecimal(0));
			xk.setOrdernum(0);
			data.add(xk);
		}

		loop: for (DrugAnalysisVo xk : xkDs) {
			for (String orderno : ordernos) {
				if (xk.getOrderno().equals(orderno)) {
					continue loop;
				}
			}
			ordernos.add(xk.getOrderno());
		}

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append(" select orderno, sum(s.price) xse,sum(s.price)-sum(s.costprice) ml ,IFNULL((sum(s.price)-sum(s.costprice))/sum(s.price),0)  mlv  from fx_sale_data s where 1=1 ");
		if (startdate != null) {
			sql.append(" and s.saledate >=?  ");
			ps.add(startdate);
		}
		if (enddate != null) {
			sql.append(" and s.saledate<=?  ");
			ps.add(enddate);
		}
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and s.storeid=?  ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and s.userid=?  ");
			ps.add(userid);
		}

		sql.append("and orderno in (''");
		for (String orderno : ordernos) {
			sql.append(",?");
			ps.add(orderno);
		}
		sql.append(") group by orderno");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<DrugAnalysisVo> orderds = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		for (DrugAnalysisVo d : data) {
			for (DrugAnalysisVo xk : xkDs) {
				if (xk.getDrugid().equals(d.getDrugid())) {
					for (DrugAnalysisVo o : orderds) {
						if (o.getOrderno().equals(xk.getOrderno())) {
							d.setOrderml(o.getMl().add(d.getOrderml()));
							d.setOrderxse(o.getXse().add(d.getOrderxse()));
							d.setOrdernum(d.getOrdernum() + 1);
						}
					}
				}
			}
		}

		sqlContext = new SqlContext();
		sql = new StringBuilder();
		ps = new ArrayList<Object>();
		sql.append(" select *  from t_drug  where id in(''");
		for (DrugAnalysisVo dv : data) {
			sql.append(",?");
			ps.add(dv.getDrugid());
		}
		sql.append(")  ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		List<Drug> drugs = fxSaleDataDao.findList(sqlContext, Drug.class);

		loop: for (DrugAnalysisVo xk : data) {
			if (xk.getXse().compareTo(new BigDecimal(0)) != 0)
				xk.setMlv(xk.getMl().divide(xk.getXse(), 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
			if (xk.getOrderxse().compareTo(new BigDecimal(0)) != 0)
				xk.setOrdermlv(xk.getOrderml().divide(xk.getOrderxse(), 10, RoundingMode.CEILING).multiply(new BigDecimal(100)));
			for (Drug d : drugs) {
				if (xk.getDrugid().equals(d.getId())) {
					xk.setCode(d.getCode());
					xk.setName(d.getName());
					xk.setSpecifications(d.getSpecifications());
					xk.setFactory(d.getFactory());
					continue loop;
				}
			}
		}

		Iterator<DrugAnalysisVo> it = data.iterator();
		while (it.hasNext()) {
			DrugAnalysisVo x = it.next();
			if (x.getXse().compareTo(new BigDecimal(0)) == 0) {
				it.remove();
			}
		}

		Collections.sort(data, new Comparator<DrugAnalysisVo>() {
			@Override
			public int compare(DrugAnalysisVo o1, DrugAnalysisVo o2) {
				if (o1.getOrderml() == o2.getOrderml())
					return 0;
				return -o1.getOrderml().compareTo(o2.getOrderml());
			}
		});

		return data;
	}

	private List<FxComname> countComScore(List<FxComname> dvs) {
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
			dv.setPf(dv.getPc() + 5 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		Collections.sort(dvs, new Comparator<FxComname>() {
			@Override
			public int compare(FxComname o1, FxComname o2) {
				return -o1.getMl().compareTo(o2.getMl());
			}
		});

		for (int i = 1; i <= dvs.size(); i++) {
			FxComname dv = dvs.get(i - 1);
			dv.setPf(dv.getPf() + +2 * 10 * (dvs.size() + 1 - i) / dvs.size());
		}

		return dvs;
	}

}
