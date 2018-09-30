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
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.SysMenuService;
import com.modules.service.SysRoleMenuService;
import com.modules.service.SysRoleService;
import com.modules.pojo.SysMenu;
import com.modules.pojo.SysRole;
import com.modules.pojo.SysRoleMenu;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("sysrole")
public class SysRoleController extends BaseController {

	private static Logger logger = Logger.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysRole.getId())) {
				sysRoleService.save(sysRole);
			} else {
				sysRoleService.update(sysRole);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysRole sysRole) {
		try {
			if (!StringUtils.isEmpty(sysRole.getId()))
				sysRoleService.delete(sysRole);
			sysRoleService.delete(sysRole);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysRole sysRole) {
		ModelAndView modelAndView = new ModelAndView("sysrole/info");
		try {
			if (!StringUtils.isEmpty(sysRole.getId())) {
				SysRole data = sysRoleService.findObject(sysRole);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysRole sysRole) {
		try {
			SysRole data = sysRoleService.findObject(sysRole);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("sysrole/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("sysrole/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(name))
				params.put("name", name);
			PageResult<SysRole> data = sysRoleService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/menutree")
	public ModelAndView menutree(HttpServletRequest request, HttpServletResponse response, String roleid) {
		ModelAndView modelAndView = new ModelAndView("sysrole/menutree");
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("roleid", roleid);
			PageResult<SysRoleMenu> page = sysRoleMenuService.findList(params, new Page(false));
			List<SysMenu> menus = sysMenuService.findList(new SysMenu());
			modelAndView.addObject("roleid", roleid);
			modelAndView.addObject("menus", menus);
			modelAndView.addObject("ownmenus", page.getData());
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/bindmenus")
	@ResponseBody
	public Object bindmenus(HttpServletRequest request, HttpServletResponse response, String roleid, String menuids) {
		try {
			sysRoleMenuService.bindmenus(roleid, menuids);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}
}