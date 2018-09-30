package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.CmsCategoryService;
import com.modules.pojo.CmsCategory;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("cmscategory")
public class CmsCategoryController extends BaseController {

	private static Logger logger = Logger.getLogger(CmsCategoryController.class);

	@Autowired
	private CmsCategoryService cmsCategoryService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(CmsCategory cmsCategory, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(cmsCategory.getId())) {
				cmsCategoryService.save(cmsCategory);
			} else {
				cmsCategoryService.update(cmsCategory);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(CmsCategory cmsCategory) {
		try {
			cmsCategoryService.delete(cmsCategory);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(CmsCategory cmsCategory) {
		ModelAndView modelAndView = new ModelAndView("cmscategory/info");
		try {
			if (!StringUtils.isEmpty(cmsCategory.getId())) {
				CmsCategory data = cmsCategoryService.findObject(cmsCategory);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(CmsCategory cmsCategory) {
		try {
			CmsCategory data = cmsCategoryService.findObject(cmsCategory);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("cmscategory/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String wxid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("cmscategory/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置

			if (!StringUtils.isEmpty(wxid))
				params.put("wxid", wxid);
			User user = (User) this.session.getAttribute(Constants.USER);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				params.put("userid", user.getId());
			PageResult<CmsCategory> data = cmsCategoryService.findList(params, page);
			modelAndView.addObject("page", data);
			String scheme = request.getScheme();
			String serverName = request.getServerName();
			int port = request.getServerPort();
			String path = request.getContextPath();
			String basePath = scheme + "://" + serverName + ":" + port + path;
			modelAndView.addObject("basePath", basePath);

		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "/preview/{categoryid}")
	@ResponseBody
	public ModelAndView categorya(HttpServletRequest request, @PathVariable("categoryid") String categoryid) {
		ModelAndView modelAndView = new ModelAndView("cmscategory/category");
		modelAndView.addObject("categoryid", categoryid);
		return modelAndView;
	}
}