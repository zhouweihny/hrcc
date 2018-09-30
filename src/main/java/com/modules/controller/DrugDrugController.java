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
import com.modules.service.DrugDrugService;
import com.modules.pojo.DrugDrug;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("drugdrug")
public class DrugDrugController extends BaseController {

	private static Logger logger = Logger.getLogger(DrugDrugController.class);

	@Autowired
	private DrugDrugService drugDrugService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(DrugDrug drugDrug, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(drugDrug.getId())) {
				drugDrugService.save(drugDrug);
			} else {
				drugDrugService.update(drugDrug);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(DrugDrug drugDrug) {
		try {
			if (!StringUtils.isEmpty(drugDrug.getId()))
				drugDrugService.delete(drugDrug);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(DrugDrug drugDrug) {
		ModelAndView modelAndView = new ModelAndView("drugdrug/info");
		try {
			if (!StringUtils.isEmpty(drugDrug.getId())) {
				DrugDrug data = drugDrugService.findObject(drugDrug);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(DrugDrug drugDrug) {
		try {
			DrugDrug data = drugDrugService.findObject(drugDrug);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("drugdrug/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("drugdrug/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<DrugDrug> data = drugDrugService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}