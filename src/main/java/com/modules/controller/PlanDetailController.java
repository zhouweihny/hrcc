package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import com.commons.base.BaseController;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.PlanDetailService;
import com.modules.pojo.PlanDetail;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("plandetail")
public class PlanDetailController extends BaseController {

	private static Logger logger = Logger.getLogger(PlanDetailController.class);

	@Autowired
	private PlanDetailService planDetailService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(PlanDetail planDetail, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(planDetail.getId())) {
				planDetailService.save(planDetail);
			} else {
				planDetailService.update(planDetail);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(PlanDetail planDetail) {
		try {
			planDetailService.delete(planDetail);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(PlanDetail planDetail) {
		ModelAndView modelAndView = new ModelAndView("plandetail/info");
		try {
			if (!StringUtils.isEmpty(planDetail.getId())) {
				PlanDetail data = planDetailService.findObject(planDetail);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(PlanDetail planDetail) {
		try {
			PlanDetail data = planDetailService.findObject(planDetail);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String planid) {
		ModelAndView modelAndView = new ModelAndView("plandetail/list");
		modelAndView.addObject("planid", planid);
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String planid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("plandetail/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置

			if (!StringUtils.isEmpty(planid)) {
				params.put("planid", planid);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<PlanDetail> data = planDetailService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}