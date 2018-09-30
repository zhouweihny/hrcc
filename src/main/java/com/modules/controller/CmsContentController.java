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

import java.util.Date;
import java.util.HashMap;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modules.service.CmsContentService;
import com.modules.pojo.CmsContent;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("cmscontent")
public class CmsContentController extends BaseController {

	private static Logger logger = Logger.getLogger(CmsContentController.class);

	@Autowired
	private CmsContentService cmsContentService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(CmsContent cmsContent, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(cmsContent.getId())) {
				User user = (User) this.session.getAttribute(Constants.USER);
				cmsContent.setCreateuserid(user.getId());
				cmsContent.setStatus(1);
				cmsContent.setTopdate(new Date());
				cmsContentService.save(cmsContent);
			} else {
				cmsContentService.update(cmsContent);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(CmsContent cmsContent) {
		try {
			cmsContentService.delete(cmsContent);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("top")
	@ResponseBody
	public Object top(String id) {
		try {
			CmsContent cmsContent = new CmsContent();
			cmsContent.setId(id);
			cmsContent.setTopdate(new Date());
			cmsContentService.update(cmsContent);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("canceltop")
	@ResponseBody
	public Object canceltop(String id) {
		try {
			CmsContent cmsContent = new CmsContent();
			cmsContent.setId(id);
			cmsContent = cmsContentService.findObject(cmsContent);
			cmsContent.setTopdate(cmsContent.getPublishdate());
			cmsContentService.update(cmsContent);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("cancel")
	@ResponseBody
	public Object cancel(String id) {
		try {
			CmsContent cmsContent = new CmsContent();
			cmsContent.setId(id);
			cmsContent.setStatus(3);
			cmsContentService.update(cmsContent);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("release")
	@ResponseBody
	public Object release(String id) {
		try {
			CmsContent cmsContent = new CmsContent();
			cmsContent.setId(id);
			cmsContent.setStatus(2);
			cmsContent.setPublishdate(new Date());
			cmsContentService.update(cmsContent);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(CmsContent cmsContent) {
		ModelAndView modelAndView = new ModelAndView("cmscontent/info");
		try {
			if (!StringUtils.isEmpty(cmsContent.getId())) {
				CmsContent data = cmsContentService.findObject(cmsContent);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(CmsContent cmsContent) {
		try {
			CmsContent data = cmsContentService.findObject(cmsContent);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("cmscontent/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String wxid, String status, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("cmscontent/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();

			if (!StringUtils.isEmpty(wxid))
				params.put("wxid", wxid);
			if (!StringUtils.isEmpty(status))
				params.put("status", status);
			if (!StringUtils.isEmpty(stype))
				params.put("stype", stype);
			if (!StringUtils.isEmpty(sfield))
				params.put("sfield", sfield);
			User user = (User) this.session.getAttribute(Constants.USER);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				params.put("userid", user.getId());
			// 参数设置
			PageResult<CmsContent> data = cmsContentService.findList(params, page);
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

	@RequestMapping(value = "/contentEidt")
	public ModelAndView contentEidt(HttpServletRequest request, HttpServletResponse response, String id) {
		ModelAndView modelAndView = new ModelAndView("cmscontent/contentEidt");
		if (!StringUtils.isEmpty(id)) {
			CmsContent cmsContent = new CmsContent();
			cmsContent.setId(id);
			try {
				CmsContent data = cmsContentService.findObject(cmsContent);
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				modelAndView.addObject("data", gson.toJson(data));
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return modelAndView;

	}

	@RequestMapping(value = "/preview/{contentid}")
	@ResponseBody
	public ModelAndView content(HttpServletRequest request, @PathVariable("contentid") String contentid) {
		ModelAndView modelAndView = new ModelAndView("cmscontent/content");
		try {
			if (!StringUtils.isEmpty(contentid)) {
				CmsContent data = new CmsContent();
				data.setId(contentid);
				data = cmsContentService.findObject(data);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}
	
	/**
	 * 联合用药展示
	 * **/
	@RequestMapping(value = "/lhyypage")
	public ModelAndView lhyypage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("cmscontent/lhyypage");
		return modelAndView;
	}
	
	/**
	 * 联合用药内容编辑
	 * **/
	@RequestMapping(value = "/lhyyedit")
	public ModelAndView lhyyedit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("cmscontent/lhyyedit");
		return modelAndView;
	}

}