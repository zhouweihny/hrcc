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
import com.modules.service.FxTreeMetaDatasService;
import com.modules.pojo.FxTreeMetaDatas;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxtreemetadatas")
public class FxTreeMetaDatasController extends BaseController {

	private static Logger logger = Logger.getLogger(FxTreeMetaDatasController.class);

	@Autowired
	private FxTreeMetaDatasService fxTreeMetaDatasService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxTreeMetaDatas fxTreeMetaDatas, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxTreeMetaDatas.getId())) {
				fxTreeMetaDatasService.save(fxTreeMetaDatas);
			} else {
				fxTreeMetaDatasService.update(fxTreeMetaDatas);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxTreeMetaDatas fxTreeMetaDatas) {
		try {
			fxTreeMetaDatasService.delete(fxTreeMetaDatas);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxTreeMetaDatas fxTreeMetaDatas) {
		ModelAndView modelAndView = new ModelAndView("fxtreemetadatas/info");
		try {
	  		if (!StringUtils.isEmpty(fxTreeMetaDatas.getId())) {
				FxTreeMetaDatas data = fxTreeMetaDatasService.findObject(fxTreeMetaDatas);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxTreeMetaDatas fxTreeMetaDatas) {
		try {
			FxTreeMetaDatas data = fxTreeMetaDatasService.findObject(fxTreeMetaDatas);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtreemetadatas/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxtreemetadatas/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxTreeMetaDatas> data = fxTreeMetaDatasService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}