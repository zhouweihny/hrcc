package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modules.pojo.Sdrug;
import com.modules.pojo.User;
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

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxsaleanalysis")
public class SaleAnalysisController extends BaseController {

	private static Logger logger = Logger.getLogger(SaleAnalysisController.class);

	@Autowired
	private SaleAnalysisService saleAnalysisService;

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String type, String name, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate, String storeid, String stype, String sfield) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/table");
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			// 参数设置
			if (type.equals("1")) {
				modelAndView = new ModelAndView("fxsaleanalysis/table");
				List<DrugAnalysisVo> data = saleAnalysisService.drugAnalysis(company.getId(), storeid, startdate, enddate, stype, sfield);
				modelAndView.addObject("data", data);
			} else if (type.equals("2")) {
				modelAndView = new ModelAndView("fxsaleanalysis/comtable");
				List<ComAnalysisVo> data = saleAnalysisService.comnameAnalysis(company.getId(), storeid, startdate, enddate, name, stype, sfield);
				modelAndView.addObject("data", data);
			} else if (type.equals("3")) {
				Gson gson = new GsonBuilder().serializeNulls().create();
				modelAndView = new ModelAndView("fxsaleanalysis/treetable");
				List<TreeAnalysisVo> data = saleAnalysisService.treeAnalysis(company.getId(), storeid, startdate, enddate, stype, sfield);
				modelAndView.addObject("data", gson.toJson(this.getJsonResult(data)));
			} else {
				modelAndView = new ModelAndView("fxsaleanalysis/table");
				List<DrugAnalysisVo> data = saleAnalysisService.drugAnalysis(company.getId(), storeid, startdate, enddate, stype, sfield);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "zlist")
	public ModelAndView zlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/zlist");
		return modelAndView;
	}

	@RequestMapping(value = "ztable")
	public ModelAndView zlist(HttpServletRequest request, HttpServletResponse response, String type, Integer status, String storeid) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/ztable");
		try {
			// 参数设置
			if (type.equals("1")) {
				modelAndView = new ModelAndView("fxsaleanalysis/z1table");
				List<SaleReport> data = saleAnalysisService.saleReport(company.getId(), storeid, status);
				modelAndView.addObject("data", data);
			} else if (type.equals("2")) {
				modelAndView = new ModelAndView("fxsaleanalysis/z2table");
				List<MlReport> data = saleAnalysisService.mlReport(company.getId(), storeid, status);
				modelAndView.addObject("data", data);
			} else if (type.equals("3")) {
				modelAndView = new ModelAndView("fxsaleanalysis/z3table");
				List<JyReport> data = saleAnalysisService.jyReport(company.getId(), storeid, status);
				modelAndView.addObject("data", data);
			}
			modelAndView.addObject("status", status);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "eliminatelist")
	public ModelAndView eliminatelist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/eliminatelist");
		return modelAndView;
	}

	@RequestMapping(value = "eliminatetable")
	@ResponseBody
	public Object eliminatetable(HttpServletRequest request, HttpServletResponse response, String treeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM") Date enddate, String impfilenameid, String storeid) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			// 参数设置
			List<DrugVo> data = saleAnalysisService.eliminateAnalysis(company.getId(), storeid, treeid, startdate, enddate);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "pricebandlist")
	public ModelAndView pricebandlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/pricebandlist");
		return modelAndView;
	}

	@RequestMapping(value = "priceband")
	@ResponseBody
	public Object priceband(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate,
			String impfilenameid, String treeid, String storeid) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User company = (User) session.getAttribute(Constants.COMPANY);
			List<PriceBandVo> data = saleAnalysisService.priceBand(company.getId(), storeid, startdate, enddate, treeid);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "scorelist")
	public ModelAndView scorelist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/scorelist");
		return modelAndView;
	}

	@RequestMapping(value = "scoretable")
	@ResponseBody
	public Object scoretable(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate,
			String impfilenameid, String treeid, String storeid) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User company = (User) session.getAttribute(Constants.COMPANY);
			List<DrugVo> data = saleAnalysisService.scoreAnalysis(company.getId(), storeid, treeid, startdate, enddate);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "gmllist")
	public ModelAndView gmllist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/gmllist");
		return modelAndView;
	}

	@RequestMapping(value = "gmltable")
	@ResponseBody
	public Object gmltable(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate,
			String impfilenameid, String treeid, String storeid) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User company = (User) session.getAttribute(Constants.COMPANY);
			List<DrugVo> data = saleAnalysisService.gmlAnalysis(company.getId(), storeid, treeid, startdate, enddate);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "priceband2list")
	public ModelAndView priceband2list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/priceband2list");
		return modelAndView;
	}

	@RequestMapping(value = "priceband2")
	@ResponseBody
	public Object priceband2(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate,
			String treeid, String storeid) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User company = (User) session.getAttribute(Constants.COMPANY);
			List<PriceBandVo2> data = saleAnalysisService.priceBand2(company.getId(), storeid, startdate, enddate, treeid);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "customerbandlist")
	public ModelAndView customerbandlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/customerbandlist");
		return modelAndView;
	}

	@RequestMapping(value = "customer")
	@ResponseBody
	public Object customer(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate,
			String storeid) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User company = (User) session.getAttribute(Constants.COMPANY);
			List<PriceBandVo2> data = saleAnalysisService.customerBand(company.getId(), storeid, startdate, enddate);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "xklist")
	public ModelAndView xklist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsaleanalysis/xklist");
		return modelAndView;
	}

	@RequestMapping(value = "xk")
	@ResponseBody
	public Object xk(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate, String storeid) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User company = (User) session.getAttribute(Constants.COMPANY);
			List<DrugAnalysisVo> data = saleAnalysisService.xkAnalysis(company.getId(), storeid, startdate, enddate);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}