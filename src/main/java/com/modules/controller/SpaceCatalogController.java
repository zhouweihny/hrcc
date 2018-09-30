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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.CatalogService;
import com.modules.service.SpaceCatalogService;
import com.modules.vo.SpaceCatalogVo;
import com.modules.pojo.Catalog;
import com.modules.pojo.SpaceCatalog;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("spacecatalog")
public class SpaceCatalogController extends BaseController {

	private static Logger logger = Logger.getLogger(SpaceCatalogController.class);

	@Autowired
	private SpaceCatalogService spaceCatalogService;

	@Autowired
	private CatalogService catalogService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SpaceCatalog spaceCatalog, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(spaceCatalog.getId())) {
				spaceCatalogService.save(spaceCatalog);
			} else {
				spaceCatalogService.update(spaceCatalog);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SpaceCatalog spaceCatalog) {
		try {
			if (!StringUtils.isEmpty(spaceCatalog.getId()))
				spaceCatalogService.delete(spaceCatalog);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SpaceCatalog spaceCatalog) {
		ModelAndView modelAndView = new ModelAndView("spacecatalog/info");
		try {
			if (!StringUtils.isEmpty(spaceCatalog.getId())) {
				SpaceCatalog data = spaceCatalogService.findObject(spaceCatalog);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SpaceCatalog spaceCatalog) {
		try {
			SpaceCatalog data = spaceCatalogService.findObject(spaceCatalog);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("spacecatalog/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("spacecatalog/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<SpaceCatalog> data = spaceCatalogService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "catalogs")
	@ResponseBody
	public Object catalogs(HttpServletRequest request, HttpServletResponse response, String spaceid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {

			Catalog catalog = new Catalog();
			catalog.setUserid(user.getId());

			List<Catalog> catalogs = catalogService.findList(catalog);
			SpaceCatalog spaceCatalog = new SpaceCatalog();
			spaceCatalog.setSpaceid(spaceid);
			List<SpaceCatalog> spaceCatalogs = spaceCatalogService.findList(spaceCatalog);
			List<SpaceCatalogVo> list = new ArrayList<SpaceCatalogVo>();
			for (Catalog c : catalogs) {
				SpaceCatalogVo spaceCatalogVo = new SpaceCatalogVo();
				spaceCatalogVo.setCatalogid(c.getId());
				spaceCatalogVo.setName(c.getName());
				for (SpaceCatalog sc : spaceCatalogs) {
					if (sc.getCatalogid().equals(c.getId())) {
						spaceCatalogVo.setChecked(true);
						if (sc.getStandard()) {
							spaceCatalogVo.setStandard(true);
						}
					}
				}
				list.add(spaceCatalogVo);
			}
			return this.getJsonResult(list);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "updatecatalogs")
	@ResponseBody
	public Object updatecatalogs(HttpServletRequest request, HttpServletResponse response, String spaceid, String catalogids, String standardid) {
		try {
			List<SpaceCatalog> spaceCatalogs = new ArrayList<SpaceCatalog>();
			if (catalogids.indexOf(standardid) < 0) {
				catalogids = catalogids + "," + standardid;
			}
			for (String catalogid : catalogids.split(",")) {
				if (!StringUtils.isEmpty(catalogid)) {
					SpaceCatalog spaceCatalog = new SpaceCatalog();
					spaceCatalog.setCatalogid(catalogid);
					spaceCatalog.setSpaceid(spaceid);
					if (standardid.equals(catalogid)) {
						spaceCatalog.setStandard(true);
					}
					spaceCatalogs.add(spaceCatalog);
				}
			}
			SpaceCatalog spaceCatalog = new SpaceCatalog();
			spaceCatalog.setSpaceid(spaceid);
			spaceCatalogService.delete(spaceCatalog);
			spaceCatalogService.save(spaceCatalogs);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}
}