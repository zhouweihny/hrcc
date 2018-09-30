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
import com.modules.service.FxMetaService;
import com.modules.pojo.FxMeta;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxmeta")
public class FxMetaController extends BaseController {

	private static Logger logger = Logger.getLogger(FxMetaController.class);

	@Autowired
	private FxMetaService fxMetaService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxMeta fxMeta, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxMeta.getId())) {
				fxMetaService.save(fxMeta);
			} else {
				fxMetaService.update(fxMeta);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxMeta fxMeta) {
		try {
			fxMetaService.delete(fxMeta);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxMeta fxMeta) {
		ModelAndView modelAndView = new ModelAndView("fxmeta/info");
		try {
	  		if (!StringUtils.isEmpty(fxMeta.getId())) {
				FxMeta data = fxMetaService.findObject(fxMeta);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxMeta fxMeta) {
		try {
			FxMeta data = fxMetaService.findObject(fxMeta);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxmeta/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxmeta/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxMeta> data = fxMetaService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}