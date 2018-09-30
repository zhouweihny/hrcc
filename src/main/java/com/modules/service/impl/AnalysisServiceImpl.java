package com.modules.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.commons.sql.SqlContext;
import com.modules.dao.FxSaleDataDao;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.FxTree;
import com.modules.service.AnalysisService;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.FxTreeService;
import com.modules.vo.disease.DiseaseAnalysisVo;
import com.modules.vo.saleanalysis.DrugAnalysisVo;

@Service
public class AnalysisServiceImpl implements AnalysisService {

	@Autowired
	private FxTreeService fxTreeService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private FxSaleDataDao fxSaleDataDao;

	@Override
	public List<DiseaseAnalysisVo> diseaseAnalysis(String userid, String storeid, Date startdate, Date enddate, String stype, String sfield) throws Exception {
		// TODO Auto-generated method stub
		FxTree rootTree = fxTreeService.findAnalysisRootTree(userid);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(" select t.name name,t.id id,t.parentid ptreeid,sum(pc) frequency  from  fx_sale_month s ,t_drug d,fx_comname_tree ct,fx_tree t  ");
		sql.append(" where s.drugid=d.id and d.comnameid=ct.comnameid and ct.treeid=t.id and t.path like  ? and ct.fwflag=true  ");
		sql.append(" and s.userid=?   ");
		ps.add(rootTree.getId() + "%");
		ps.add(userid);

		if (startdate != null) {
			sql.append("	 and s.month >=?  ");
			ps.add(startdate);
		}

		if (enddate != null) {
			sql.append("	and s.month<=?  ");
			ps.add(enddate);
		}

		if (StringUtils.isNotBlank(storeid)) {
			sql.append("	and s.storeid=?  ");
			ps.add(storeid);
		}
		sql.append(" 	 group by t.name,t.id,t.parentid  ");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxSaleDataDao.findList(sqlContext, DiseaseAnalysisVo.class);
	}

	@Override
	public List<DrugAnalysisVo> salebytree(String treeid, String companyid, Date startdate, Date enddate, String storeid, final String stype, final String sfield) throws Exception {

		List<DrugAnalysisVo> data = new ArrayList<DrugAnalysisVo>();

		Catalog catalog = new Catalog();
		catalog.setMyself(true);
		catalog.setUserid(companyid);
		catalog = catalogService.findObject(catalog);

		Drug drug = new Drug();
		drug.setCatalogid(catalog.getId());
		List<Drug> drugs = drugService.findList(drug);

		// TODO Auto-generated method stub
		FxTree fxTree = new FxTree();
		fxTree.setId(treeid);
		fxTree = fxTreeService.findObject(fxTree);

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"   select  s.drugid ,sum(qty) xssl,s.name,sum(s.price) xse,sum(s.price)-sum(s.costprice) ml,IFNULL( sum(s.price)/sum(qty),0) pjsj,IFNULL( (sum(s.price)-sum(s.costprice))/sum(s.price),0) mlv,count(1) pc  from fx_sale_data s, fx_comname_tree ct,fx_tree t,t_drug d where path like ? ");
		sql.append("   and d.comnameid=ct.comnameid and ct.treeid=t.id   and d.catalogid=? ");
		ps.add(fxTree.getPath() + "%");
		ps.add(catalog.getId());
		if (startdate != null) {
			sql.append("	 and s.saledate >=?  ");
			ps.add(startdate);
		}

		if (enddate != null) {
			sql.append("	and s.saledate<=?  ");
			ps.add(enddate);
		}

		if (StringUtils.isNotBlank(storeid)) {
			sql.append("	and s.storeid=?  ");
			ps.add(storeid);
		}
		sql.append(" 	  and s.drugid =d.id group by s.drugid");

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		data = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		for (DrugAnalysisVo da : data) {
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

		if (StringUtils.isNotBlank(sfield) && StringUtils.isNotBlank(stype))
			Collections.sort(data, new Comparator<DrugAnalysisVo>() {
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

					if (sfield.equals("xssl")) {
						if (o1.getXssl() == o2.getXssl())
							return 0;

						if (stype.equals("asc"))
							return o1.getXssl().compareTo(o2.getXssl());
						else {
							return -o1.getXssl().compareTo(o2.getXssl());
						}
					}
					return 0;
				}
			});

		return data;
	}

	@Override
	public List<DrugAnalysisVo> salebytree2(String id, String companyid, Date startdate, Date enddate, String storeid, final String stype, final String sfield) throws Exception {

		List<DrugAnalysisVo> data = new ArrayList<DrugAnalysisVo>();

		Catalog catalog = new Catalog();
		catalog.setMyself(true);
		catalog.setUserid(companyid);
		catalog = catalogService.findObject(catalog);

		Drug drug = new Drug();
		drug.setCatalogid(catalog.getId());
		List<Drug> drugs = drugService.findList(drug);

		// TODO Auto-generated method stub
		FxTree fxTree = new FxTree();
		fxTree.setId(id);
		fxTree = fxTreeService.findObject(fxTree);
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append(
				"   select  s.drugid ,sum(xssl) xssl,d.name,sum(s.xse) xse,sum(s.ml) ml,IFNULL( sum(s.xse)/sum(xssl),0) pjsj,IFNULL( sum(ml)*100/sum(s.xse),0) mlv,sum(s.pc) pc  from fx_sale_month s, fx_comname_tree ct,fx_tree t,t_drug d where path like ? ");
		sql.append("   and d.comnameid=ct.comnameid and ct.treeid=t.id   and d.catalogid=? ");

		ps.add(fxTree.getPath() + "%");
		ps.add(catalog.getId());

		if (startdate != null) {
			sql.append("	 and s.month >=?  ");
			ps.add(startdate);
		}

		if (enddate != null) {
			sql.append("	and s.month<=?  ");
			ps.add(enddate);
		}

		if (StringUtils.isNotBlank(storeid)) {
			sql.append("	and s.storeid=?  ");
			ps.add(storeid);
		}
		sql.append(" 	  and s.drugid =d.id group by s.drugid");
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		data = fxSaleDataDao.findList(sqlContext, DrugAnalysisVo.class);

		for (DrugAnalysisVo da : data) {
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

		if (StringUtils.isNotBlank(sfield) && StringUtils.isNotBlank(stype))
			Collections.sort(data, new Comparator<DrugAnalysisVo>() {
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

					if (sfield.equals("xssl")) {
						if (o1.getXssl() == o2.getXssl())
							return 0;

						if (stype.equals("asc"))
							return o1.getXssl().compareTo(o2.getXssl());
						else {
							return -o1.getXssl().compareTo(o2.getXssl());
						}
					}
					return 0;
				}
			});

		return data;
	}

}
