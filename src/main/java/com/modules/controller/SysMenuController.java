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
import com.modules.service.SysMenuService;
import com.modules.pojo.SysMenu;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("sysmenu")
public class SysMenuController extends BaseController {

	private static Logger logger = Logger.getLogger(SysMenuController.class);

	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysMenu sysMenu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysMenu.getId())) {
				sysMenuService.save(sysMenu);
			} else {
				sysMenuService.update(sysMenu);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysMenu sysMenu) {
		try {
			if (!StringUtils.isEmpty(sysMenu.getId()))
				sysMenuService.delete(sysMenu);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysMenu sysMenu) {
		ModelAndView modelAndView = new ModelAndView("sysmenu/info");
		try {
			if (!StringUtils.isEmpty(sysMenu.getId())) {
				SysMenu data = sysMenuService.findObject(sysMenu);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysMenu sysMenu) {
		try {
			SysMenu data = sysMenuService.findObject(sysMenu);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("sysmenu/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String parentid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("sysmenu/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(parentid)) {
				params.put("parentid", parentid);
			}

			PageResult<SysMenu> data = sysMenuService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}