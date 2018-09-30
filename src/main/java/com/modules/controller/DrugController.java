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
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.FxComnameTreeService;
import com.modules.service.FxTreeService;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("drug")
public class DrugController extends BaseController {

	private static Logger logger = Logger.getLogger(DrugController.class);

	@Autowired
	private DrugService drugService;

	@Autowired
	private CatalogService catalogService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Drug drug, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(drug.getId())) {
				drugService.save(drug);
			} else {
				drugService.update(drug);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Drug drug) {
		try {
			if (!StringUtils.isEmpty(drug.getId()))
				drugService.delete(drug);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Drug drug) {
		ModelAndView modelAndView = new ModelAndView("drug/info");
		try {
			if (!StringUtils.isEmpty(drug.getId())) {
				Drug data = drugService.findObject(drug);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Drug drug) {
		try {
			Drug data = drugService.findObject(drug);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		String userid = user.getId();
		ModelAndView modelAndView = new ModelAndView("drug/list");
		Catalog catalog = new Catalog();
		catalog.setUserid(userid);
		try {
			List<Catalog> catalogs = catalogService.findList(catalog);
			modelAndView.addObject("catalogs", catalogs);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String catalogid, String factory, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		ModelAndView modelAndView = new ModelAndView("drug/table");
		try {
			String userid = user.getId();

			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置

			params.put("userid", userid);
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			if (!StringUtils.isEmpty(catalogid)) {
				params.put("catalogid", catalogid);
			}
			if (!StringUtils.isEmpty(factory)) {
				params.put("factory", factory);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Drug> data = drugService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "drugs")
	@ResponseBody
	public Object drugs(HttpServletRequest request, HttpServletResponse response, String name) {
		User user = (User) session.getAttribute(Constants.COMPANY);
		try {
			Page page = new Page(1, 200);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			String userid = user.getId();
			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);

			params.put("catalogid", catalog.getId());

			if (!StringUtils.isEmpty(name)) {
				params.put("keyword", name);
			}
			PageResult<Drug> data = drugService.findList(params, page);
			return data;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}