package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.util.DateUtil;
import com.modules.pojo.FxTree;
import com.modules.pojo.User;
import com.modules.service.AnalysisService;
import com.modules.service.FxTreeService;
import com.modules.vo.disease.DiseaseAnalysisVo;
import com.modules.vo.saleanalysis.DrugAnalysisVo;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("analysis")
public class AnalysisController extends BaseController {

	private static Logger logger = Logger.getLogger(AnalysisController.class);

	@Autowired
	private AnalysisService analysisService;

	@Autowired
	private FxTreeService fxTreeService;

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("diseaseanalysisvo/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	@ResponseBody
	public Object plist(HttpServletRequest request, HttpServletResponse response, String impfilenameid, String storeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate, final String stype, final String sfield, Integer currentPage, Integer pageSize) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			// 参数设置
			FxTree rootTree = fxTreeService.findAnalysisRootTree(company.getId());
			List<FxTree> trees = fxTreeService.findAnalysisTree(company.getId());
//			List<FxTree> trees2 = fxTreeService.findByPath("36A8746039AB40C39D0B7CD75DE45650");
//			trees.addAll(trees2);
			List<DiseaseAnalysisVo> data = analysisService.diseaseAnalysis(company.getId(), storeid, startdate, enddate, stype, sfield);

			Integer total = 0;

			for (DiseaseAnalysisVo cdv : data) {
				total = total + cdv.getFrequency();
			}

			List<DiseaseAnalysisVo> result = new ArrayList<DiseaseAnalysisVo>();
			for (FxTree tree : trees) {
				if (tree.getParentid() != null && (tree.getParentid().equals(rootTree.getId()) || tree.getParentid().equals("36A8746039AB40C39D0B7CD75DE45650"))) {
					DiseaseAnalysisVo dv = new DiseaseAnalysisVo();
					dv.setId(tree.getId());
					dv.setName(tree.getName());
					dv.setFrequency(0);
					dv.setItems(new ArrayList<DiseaseAnalysisVo>());
					result.add(dv);
					for (DiseaseAnalysisVo cdv : data) {
						if (cdv.getPtreeid() != null && cdv.getPtreeid().equals(dv.getId())) {
							dv.getItems().add(cdv);
							dv.setFrequency(dv.getFrequency() + cdv.getFrequency());
						}
					}

					if (total != 0)
						dv.setProportion(new BigDecimal(dv.getFrequency()).multiply(new BigDecimal(100)).divide(new BigDecimal(total), 10, RoundingMode.CEILING));
				}
			}

			for (DiseaseAnalysisVo tree : result) {
				loop: for (FxTree tree2 : trees) {
					if (tree2.getParentid() != null && tree2.getParentid().equals(tree.getId())) {
						for (DiseaseAnalysisVo dv2 : tree.getItems()) {
							if (dv2.getId().equals(tree2.getId()))
								continue loop;

						}

						DiseaseAnalysisVo tdv = new DiseaseAnalysisVo();
						tdv.setName(tree2.getName());
						tdv.setPtreeid(tree2.getParentid());
						tdv.setProportion(new BigDecimal(0));
						tdv.setFrequency(0);
						tdv.setId(tree2.getId());
						tree.getItems().add(tdv);
						continue loop;
					}
				}

			}
//
//			for (DiseaseAnalysisVo cdv : result) {
//				for (FxTree tree : trees2) {
//					if (cdv.getId().equals(tree.getParentid())) {
//						for (FxTree tree2 : trees2) {
//							if (tree2.getId().equals(cdv.getId())) {
//								DiseaseAnalysisVo dv = new DiseaseAnalysisVo();
//								dv.setName(tree.getName());
//								dv.setPtreeid(tree.getParentid());
//								dv.setProportion(new BigDecimal(0));
//								dv.setFrequency(0);
//								dv.setId(tree.getId());
//								cdv.getItems().add(dv);
//							}
//						}
//					}
//				}
//			}

			for (DiseaseAnalysisVo cdv : data) {
				if (total != 0)
					cdv.setProportion(new BigDecimal(cdv.getFrequency()).multiply(new BigDecimal(100)).divide(new BigDecimal(total), 10, RoundingMode.CEILING));
			}

			if (!StringUtils.isEmpty(sfield) && !StringUtils.isEmpty(stype))
				Collections.sort(result, new Comparator<DiseaseAnalysisVo>() {
					@Override
					public int compare(DiseaseAnalysisVo o1, DiseaseAnalysisVo o2) {
						if (sfield.equals("frequency")) {
							if (o1.getFrequency() == o2.getFrequency())
								return 0;
							if (stype.equals("asc"))
								return o1.getFrequency().compareTo(o2.getFrequency());
							else {
								return -o1.getFrequency().compareTo(o2.getFrequency());
							}
						}

						if (sfield.equals("proportion")) {
							if (o1.getProportion() == o2.getProportion())
								return 0;
							if (stype.equals("asc"))
								return o1.getProportion().compareTo(o2.getProportion());
							else {
								return -o1.getProportion().compareTo(o2.getProportion());
							}
						}

						return 0;
					}
				});

			return this.getJsonResult(result);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "salebytree")
	@ResponseBody
	public Object salebytree(HttpServletRequest request, HttpServletResponse response, String storeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate, String treeid, String stype, String sfield) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			// 参数设置
			List<DrugAnalysisVo> data = analysisService.salebytree(treeid, company.getId(), startdate, enddate, storeid, stype, sfield);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "salebytree2")
	@ResponseBody
	public Object salebytree2(HttpServletRequest request, HttpServletResponse response, String impfilenameid, String storeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate, String id, String stype, String sfield) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			// 参数设置
			List<DrugAnalysisVo> data = analysisService.salebytree2(id, company.getId(), startdate, enddate, storeid, stype, sfield);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "diseaselist")
	public ModelAndView diseaselist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("diseaseanalysisvo/diseaselist");
		return modelAndView;
	}

}