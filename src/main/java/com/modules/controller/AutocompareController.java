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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.AutocompareService;
import com.modules.pojo.Autocompare;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("autocompare")
public class AutocompareController extends BaseController {

	private static Logger logger = Logger.getLogger(AutocompareController.class);

	@Autowired
	private AutocompareService autocompareService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Autocompare autocompare, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(autocompare.getId())) {
				autocompareService.save(autocompare);
			} else {
				autocompareService.update(autocompare);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Autocompare autocompare) {
		try {
			if (!StringUtils.isEmpty(autocompare.getId()))
				autocompareService.delete(autocompare);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Autocompare autocompare) {
		ModelAndView modelAndView = new ModelAndView("autocompare/info");
		try {
			if (!StringUtils.isEmpty(autocompare.getId())) {
				Autocompare data = autocompareService.findObject(autocompare);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Autocompare autocompare) {
		try {
			Autocompare data = autocompareService.findObject(autocompare);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("autocompare/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startdate,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date enddate, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("autocompare/table");
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("userid", user.getId());
			if (!StringUtils.isEmpty(startdate))
				params.put("startdate", startdate);
			if (!StringUtils.isEmpty(enddate))
				params.put("enddate", enddate);
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Autocompare> data = autocompareService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}