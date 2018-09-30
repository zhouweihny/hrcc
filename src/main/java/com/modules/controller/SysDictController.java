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
import com.modules.service.SysDictService;
import com.modules.pojo.SysDict;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("sysdict")
public class SysDictController extends BaseController {

	private static Logger logger = Logger.getLogger(SysDictController.class);

	@Autowired
	private SysDictService sysDictService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysDict sysDict, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysDict.getId())) {
				sysDictService.save(sysDict);
			} else {
				sysDictService.update(sysDict);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysDict sysDict) {
		try {
			if (!StringUtils.isEmpty(sysDict.getId()))
				sysDictService.delete(sysDict);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysDict sysDict) {
		ModelAndView modelAndView = new ModelAndView("sysdict/info");
		try {
			if (!StringUtils.isEmpty(sysDict.getId())) {
				SysDict data = sysDictService.findObject(sysDict);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysDict sysDict) {
		try {
			SysDict data = sysDictService.findObject(sysDict);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("sysdict/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView table(HttpServletRequest request, HttpServletResponse response, String name, String code, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("sysdict/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(name))
				params.put("name", name);
			if (!StringUtils.isEmpty(code))
				params.put("code", code);
			PageResult<SysDict> data = sysDictService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

}