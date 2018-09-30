package com.modules.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.modules.vo.DrugVo;
import com.modules.vo.PriceBandVo;
import com.modules.vo.PriceBandVo2;
import com.modules.vo.saleanalysis.ComAnalysisVo;
import com.modules.vo.saleanalysis.DrugAnalysisVo;
import com.modules.vo.saleanalysis.JyReport;
import com.modules.vo.saleanalysis.MlReport;
import com.modules.vo.saleanalysis.SaleReport;
import com.modules.vo.saleanalysis.TreeAnalysisVo;

/**
 * 
 * @author Du.Jun
 */
public interface SaleAnalysisService {

	public List<DrugAnalysisVo> drugAnalysis(String userid, String storeid, Date startdate, Date enddate, final String stype, final String sfield) throws Exception;

	public List<ComAnalysisVo> comnameAnalysis(String userid, String storeid, Date startdate, Date enddate, String name, final String stype, final String sfield) throws Exception;

	public List<TreeAnalysisVo> treeAnalysis(String userid, String storeid, Date startdate, Date enddate, final String stype, final String sfield) throws Exception;

	public List<SaleReport> saleReport(String userid, String storeid, Integer status) throws Exception;

	public List<MlReport> mlReport(String userid, String storeid, Integer status) throws Exception;

	public List<JyReport> jyReport(String userid, String storeid, Integer status) throws Exception;

	public List<DrugVo> eliminateAnalysis(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception;

	public List<PriceBandVo> priceBand(String userid, String storeid, Date startdate, Date enddate, String treeid) throws Exception;

	public List<PriceBandVo2> priceBand2(String userid, String storeid, Date startdate, Date enddate, String treeid) throws Exception;

	public List<DrugVo> scoreAnalysis(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception;

	public List<DrugVo> gmlAnalysis(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception;

	public List<PriceBandVo2> customerBand(String userid, String storeid, Date startdate, Date enddate) throws Exception;

	public List<DrugAnalysisVo> countByTreeid(String userid, String storeid, String treeid, Date startdate, Date enddate) throws Exception;

	public List<DrugAnalysisVo> xkAnalysis(String userid, String storeid, Date startdate, Date enddate) throws Exception;
}
