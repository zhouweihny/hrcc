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
import com.modules.service.FactoryKeywordService;
import com.modules.vo.FactoryKeywordVo;
import com.modules.pojo.FactoryKeyword;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("factorykeyword")
public class FactoryKeywordController extends BaseController {

	private static Logger logger = Logger.getLogger(FactoryKeywordController.class);

	@Autowired
	private FactoryKeywordService factoryKeywordService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FactoryKeyword factoryKeyword, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(factoryKeyword.getId())) {
				factoryKeywordService.save(factoryKeyword);
			} else {
				factoryKeywordService.update(factoryKeyword);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FactoryKeyword factoryKeyword) {
		try {
			factoryKeywordService.delete(factoryKeyword);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(String code) {
		ModelAndView modelAndView = new ModelAndView("factorykeyword/info");
		try {
			modelAndView.addObject("code", code);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FactoryKeyword factoryKeyword) {
		try {
			FactoryKeyword data = factoryKeywordService.findObject(factoryKeyword);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("factorykeyword/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String status, String word, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("factorykeyword/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(word)) {
				params.put("word", word);
			}

			if (!StringUtils.isEmpty(status)) {
				params.put("status", status);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FactoryKeywordVo> data = factoryKeywordService.findFactoryKeywordVos(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}