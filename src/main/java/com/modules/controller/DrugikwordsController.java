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
import com.modules.service.DrugikwordsService;
import com.modules.pojo.Drugikwords;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("drugikwords")
public class DrugikwordsController extends BaseController {

	private static Logger logger = Logger.getLogger(DrugikwordsController.class);

	@Autowired
	private DrugikwordsService drugikwordsService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Drugikwords drugikwords, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(drugikwords.getId())) {
				drugikwordsService.save(drugikwords);
			} else {
				drugikwordsService.update(drugikwords);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Drugikwords drugikwords) {
		try {
			if (!StringUtils.isEmpty(drugikwords.getId()))
				drugikwordsService.delete(drugikwords);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("ignore")
	@ResponseBody
	public Object ignore(String id) {
		try {

			Drugikwords drugikwords = new Drugikwords();
			drugikwords.setId(id);
			drugikwords.setDisabled(true);
			drugikwordsService.update(drugikwords);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Drugikwords drugikwords) {
		ModelAndView modelAndView = new ModelAndView("drugikwords/info");
		try {
			if (!StringUtils.isEmpty(drugikwords.getId())) {
				Drugikwords data = drugikwordsService.findObject(drugikwords);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Drugikwords drugikwords) {
		try {
			Drugikwords data = drugikwordsService.findObject(drugikwords);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("drugikwords/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String iskeywords, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("drugikwords/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			if (!StringUtils.isEmpty(iskeywords)) {
				params.put("iskeywords", iskeywords);
			}
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Drugikwords> data = drugikwordsService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}