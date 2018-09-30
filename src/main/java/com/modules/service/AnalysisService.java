package com.modules.service;

import java.util.Date;
import java.util.List;

import com.modules.vo.disease.DiseaseAnalysisVo;
import com.modules.vo.saleanalysis.DrugAnalysisVo;

/**
 * 
 * @author Du.Jun
 */
public interface AnalysisService {

	public List<DiseaseAnalysisVo> diseaseAnalysis(String userid, String storeid, Date startdate, Date enddate, String stype, String sfield) throws Exception;

	public List<DrugAnalysisVo> salebytree(String name, String userid, Date startdate, Date enddate, String storeid, final String stype, final String sfield) throws Exception;

	public List<DrugAnalysisVo> salebytree2(String id, String userid, Date startdate, Date enddate, String storeid, final String stype, final String sfield) throws Exception;

}
