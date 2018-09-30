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
import com.modules.service.DrugPromotionPurchaesrService;
import com.modules.pojo.DrugPromotionPurchaesr;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("drugpromotionpurchaesr")
public class DrugPromotionPurchaesrController extends BaseController {

	private static Logger logger = Logger.getLogger(DrugPromotionPurchaesrController.class);

	@Autowired
	private DrugPromotionPurchaesrService drugPromotionPurchaesrService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(DrugPromotionPurchaesr drugPromotionPurchaesr, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(drugPromotionPurchaesr.getId())) {
				drugPromotionPurchaesrService.save(drugPromotionPurchaesr);
			} else {
				drugPromotionPurchaesrService.update(drugPromotionPurchaesr);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(DrugPromotionPurchaesr drugPromotionPurchaesr) {
		try {
			drugPromotionPurchaesrService.delete(drugPromotionPurchaesr);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(DrugPromotionPurchaesr drugPromotionPurchaesr) {
		ModelAndView modelAndView = new ModelAndView("drugpromotionpurchaesr/info");
		try {
	  		if (!StringUtils.isEmpty(drugPromotionPurchaesr.getId())) {
				DrugPromotionPurchaesr data = drugPromotionPurchaesrService.findObject(drugPromotionPurchaesr);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(DrugPromotionPurchaesr drugPromotionPurchaesr) {
		try {
			DrugPromotionPurchaesr data = drugPromotionPurchaesrService.findObject(drugPromotionPurchaesr);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("drugpromotionpurchaesr/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("drugpromotionpurchaesr/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<DrugPromotionPurchaesr> data = drugPromotionPurchaesrService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}