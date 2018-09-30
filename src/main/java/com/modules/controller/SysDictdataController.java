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
import com.modules.service.SysDictdataService;
import com.modules.pojo.SysDictdata;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("sysdictdata")
public class SysDictdataController extends BaseController {

	private static Logger logger = Logger.getLogger(SysDictdataController.class);

	@Autowired
	private SysDictdataService sysDictdataService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysDictdata sysDictdata, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysDictdata.getId())) {
				sysDictdataService.save(sysDictdata);
			} else {
				sysDictdataService.update(sysDictdata);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysDictdata sysDictdata) {
		try {
			sysDictdataService.delete(sysDictdata);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysDictdata sysDictdata) {
		ModelAndView modelAndView = new ModelAndView("sysdictdata/info");
		try {
			if (!StringUtils.isEmpty(sysDictdata.getId())) {
				SysDictdata data = sysDictdataService.findObject(sysDictdata);
				modelAndView.addObject("data", data);
			} else
				modelAndView.addObject("data", sysDictdata);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysDictdata sysDictdata) {
		try {
			SysDictdata data = sysDictdataService.findObject(sysDictdata);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/table")
	public ModelAndView datatable(HttpServletRequest request, HttpServletResponse response, String dictid) {
		ModelAndView modelAndView = new ModelAndView("sysdictdata/table");
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("dictid", dictid);
			// 参数设置
			PageResult<SysDictdata> data = sysDictdataService.findList(params, new Page(false));
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

}