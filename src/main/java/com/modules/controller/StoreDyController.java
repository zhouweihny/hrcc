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
import com.modules.service.StoreDyService;
import com.modules.pojo.Store;
import com.modules.pojo.StoreDy;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("storedy")
public class StoreDyController extends BaseController {

	private static Logger logger = Logger.getLogger(StoreDyController.class);

	@Autowired
	private StoreDyService storeDyService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(StoreDy storeDy, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(storeDy.getId())) {
				storeDyService.save(storeDy);
			} else {
				storeDyService.update(storeDy);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(StoreDy storeDy) {
		try {
			storeDyService.delete(storeDy);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(StoreDy storeDy) {
		ModelAndView modelAndView = new ModelAndView("storedy/info");
		try {
	  		if (!StringUtils.isEmpty(storeDy.getId())) {
				StoreDy data = storeDyService.findObject(storeDy);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(StoreDy storeDy) {
		try {
			StoreDy data = storeDyService.findObject(storeDy);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("storedy/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("storedy/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<StoreDy> data = storeDyService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "storeydys")
	@ResponseBody
	public Object drugs(HttpServletRequest request, HttpServletResponse response, String storeid) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);
		try {
			StoreDy storeDy= new StoreDy();
			if (!StringUtils.isEmpty(storeid) && !StringUtils.isEmpty(storeid)) {
				storeDy.setStoreid(storeid);
			}
			List<StoreDy> data = storeDyService.findList(storeDy);
			return data;
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}
	
}