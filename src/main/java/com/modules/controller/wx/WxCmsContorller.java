package com.modules.controller.wx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.commons.base.BaseController;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.pojo.CmsContent;
import com.modules.service.CmsContentService;

@Controller
@RequestMapping("wx/c")
public class WxCmsContorller extends BaseController {
	private static Logger logger = Logger.getLogger(WxCmsContorller.class);

	@Autowired
	private CmsContentService cmsContentService;

	@RequestMapping(value = "/category/{categoryid}")
	@ResponseBody
	public ModelAndView categorya(HttpServletRequest request, @PathVariable("categoryid") String categoryid) {
		ModelAndView modelAndView = new ModelAndView("wx/category");
		modelAndView.addObject("categoryid", categoryid);
		return modelAndView;
	}

	@RequestMapping(value = "/category/{categoryid}/{currentPage}")
	@ResponseBody
	public Object category(HttpServletRequest request, @PathVariable("categoryid") String categoryid, @PathVariable("currentPage") Integer currentPage) {
		Page page = new Page(currentPage, 10);
		HashMap<String, Object> params = new HashMap<String, Object>();
		try {
			params.put("categoryid", categoryid);
			PageResult<CmsContent> data = cmsContentService.findListA(params, page);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "/content/{contentid}")
	@ResponseBody
	public ModelAndView content(HttpServletRequest request, @PathVariable("contentid") String contentid) {
		ModelAndView modelAndView = new ModelAndView("wx/content");
		try {
			if (!StringUtils.isEmpty(contentid)) {
				CmsContent data = new CmsContent();
				data.setId(contentid);
				data = cmsContentService.findObject(data);
				data.setClicks(data.getClicks() + 1);
				String scheme = request.getScheme();
				String serverName = request.getServerName();
				int port = request.getServerPort();
				String path = request.getContextPath();
				String basePath = scheme + "://" + serverName + ":" + port + path;
				modelAndView.addObject("basePath", basePath);
				cmsContentService.update(data);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

}
