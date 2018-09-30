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
import com.modules.service.SysRoleMenuService;
import com.modules.pojo.SysRoleMenu;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("sysrolemenu")
public class SysRoleMenuController extends BaseController {

	private static Logger logger = Logger.getLogger(SysRoleMenuController.class);

	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysRoleMenu sysRoleMenu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysRoleMenu.getId())) {
				sysRoleMenuService.save(sysRoleMenu);
			} else {
				sysRoleMenuService.update(sysRoleMenu);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysRoleMenu sysRoleMenu) {
		try {
			if (!StringUtils.isEmpty(sysRoleMenu.getId()))
				sysRoleMenuService.delete(sysRoleMenu);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysRoleMenu sysRoleMenu) {
		ModelAndView modelAndView = new ModelAndView("sysrolemenu/info");
		try {
			if (!StringUtils.isEmpty(sysRoleMenu.getId())) {
				SysRoleMenu data = sysRoleMenuService.findObject(sysRoleMenu);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysRoleMenu sysRoleMenu) {
		try {
			SysRoleMenu data = sysRoleMenuService.findObject(sysRoleMenu);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("sysrolemenu/list");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			PageResult<SysRoleMenu> data = sysRoleMenuService.findList(params, page);
			modelAndView.addObject("XMLHttpRequest", request.getHeader("x-requested-with") == null ? "" : request.getHeader("x-requested-with"));
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			PageResult<SysRoleMenu> data = sysRoleMenuService.findList(params, page);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

}