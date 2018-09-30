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
import com.modules.service.FxComnameTreeFactoryService;
import com.modules.pojo.FxComnameTreeFactory;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxcomnametreefactory")
public class FxComnameTreeFactoryController extends BaseController {

	private static Logger logger = Logger.getLogger(FxComnameTreeFactoryController.class);

	@Autowired
	private FxComnameTreeFactoryService fxComnameTreeFactoryService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxComnameTreeFactory fxComnameTreeFactory, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxComnameTreeFactory.getId())) {
				FxComnameTreeFactory f = fxComnameTreeFactoryService.findObject(fxComnameTreeFactory);
				if (f == null)
					fxComnameTreeFactoryService.save(fxComnameTreeFactory);
			} else {
				fxComnameTreeFactoryService.update(fxComnameTreeFactory);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxComnameTreeFactory fxComnameTreeFactory) {
		try {
			fxComnameTreeFactoryService.delete(fxComnameTreeFactory);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxComnameTreeFactory fxComnameTreeFactory) {
		ModelAndView modelAndView = new ModelAndView("fxcomnametreefactory/info");
		try {
			if (!StringUtils.isEmpty(fxComnameTreeFactory.getId())) {
				FxComnameTreeFactory data = fxComnameTreeFactoryService.findObject(fxComnameTreeFactory);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxComnameTreeFactory fxComnameTreeFactory) {
		try {
			FxComnameTreeFactory data = fxComnameTreeFactoryService.findObject(fxComnameTreeFactory);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxcomnametreefactory/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxcomnametreefactory/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxComnameTreeFactory> data = fxComnameTreeFactoryService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "factorys")
	@ResponseBody
	public Object factorys(FxComnameTreeFactory fxComnameTreeFactory) {
		try {
			List<FxComnameTreeFactory> datas = fxComnameTreeFactoryService.findList(fxComnameTreeFactory);
			return this.getJsonResult(datas);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

}