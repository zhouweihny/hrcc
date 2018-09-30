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
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.FxSaleMonthService;
import com.modules.pojo.FxSaleMonth;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxsalemonth")
public class FxSaleMonthController extends BaseController {

	private static Logger logger = Logger.getLogger(FxSaleMonthController.class);

	@Autowired
	private FxSaleMonthService fxSaleMonthService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxSaleMonth fxSaleMonth, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxSaleMonth.getId())) {
				fxSaleMonthService.save(fxSaleMonth);
			} else {
				fxSaleMonthService.update(fxSaleMonth);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxSaleMonth fxSaleMonth) {
		try {
			fxSaleMonthService.delete(fxSaleMonth);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxSaleMonth fxSaleMonth) {
		ModelAndView modelAndView = new ModelAndView("fxsalemonth/info");
		try {
			if (!StringUtils.isEmpty(fxSaleMonth.getId())) {
				FxSaleMonth data = fxSaleMonthService.findObject(fxSaleMonth);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxSaleMonth fxSaleMonth) {
		try {
			FxSaleMonth data = fxSaleMonthService.findObject(fxSaleMonth);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsalemonth/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxsalemonth/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxSaleMonth> data = fxSaleMonthService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "monthsbystoreid")
	@ResponseBody
	public Object months(String storeid) {
		try {
			User company = (User) this.session.getAttribute(Constants.COMPANY);
			List<String> data = fxSaleMonthService.monthsByStoreid(storeid, company.getId());
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

}