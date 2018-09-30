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
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.StoreTypeService;
import com.modules.pojo.StoreType;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("storetype")
public class StoreTypeController extends BaseController {

	private static Logger logger = Logger.getLogger(StoreTypeController.class);

	@Autowired
	private StoreTypeService storeTypeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(StoreType storeType, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(storeType.getId())) {
				storeTypeService.save(storeType);
			} else {
				storeTypeService.update(storeType);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(StoreType storeType) {
		try {
			storeTypeService.delete(storeType);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(StoreType storeType) {
		ModelAndView modelAndView = new ModelAndView("storetype/info");
		try {
			if (!StringUtils.isEmpty(storeType.getId())) {
				StoreType data = storeTypeService.findObject(storeType);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(StoreType storeType) {
		try {
			StoreType data = storeTypeService.findObject(storeType);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("storetype/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("storetype/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<StoreType> data = storeTypeService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "types")
	@ResponseBody
	public Object types(HttpServletRequest request, HttpServletResponse response) {
		List<StoreType> types;
		try {
			types = storeTypeService.findList(new StoreType());
			return this.getJsonResult(types);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

}