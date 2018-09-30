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
import com.modules.service.SysWxMenuService;
import com.modules.service.SysWxService;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxMenu;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxmenu")
public class SysWxMenuController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxMenuController.class);

	@Autowired
	private SysWxMenuService sysWxMenuService;

	@Autowired
	private SysWxService sysWxService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWxMenu sysWxMenu, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWxMenu.getId())) {
				sysWxMenuService.save(sysWxMenu);
			} else {
				if (StringUtils.isEmpty(sysWxMenu.getUrl())) {
					sysWxMenu.setUrl("");
				} else {
					sysWxMenu.setWxmsgid("");
				}

				sysWxMenuService.update(sysWxMenu);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxMenu sysWxMenu) {
		try {
			sysWxMenuService.delete(sysWxMenu);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxMenu sysWxMenu) {
		ModelAndView modelAndView = new ModelAndView("syswxmenu/info");
		try {
			List<SysWx> syswxs = sysWxService.findList(new SysWx());
			List<SysWxMenu> sysmenuwxs = sysWxMenuService.findList(new SysWxMenu());
			modelAndView.addObject("syswxs", syswxs);
			modelAndView.addObject("syswxmenus", sysmenuwxs);
			if (!StringUtils.isEmpty(sysWxMenu.getId())) {
				SysWxMenu data = sysWxMenuService.findObject(sysWxMenu);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxMenu sysWxMenu) {
		try {
			SysWxMenu data = sysWxMenuService.findObject(sysWxMenu);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxmenu/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String wxid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxmenu/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(wxid)) {
				params.put("wxid", wxid);
			}
			User user = (User) this.session.getAttribute(Constants.USER);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				params.put("userid", user.getId());
			PageResult<SysWxMenu> data = sysWxMenuService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "bandmsg", method = RequestMethod.GET)
	@ResponseBody
	public Object bandmsg(SysWxMenu sysWxMenu) {
		ModelAndView modelAndView = new ModelAndView("syswxmenu/bandmsg");
		try {
			if (!StringUtils.isEmpty(sysWxMenu.getId())) {
				SysWxMenu data = sysWxMenuService.findObject(sysWxMenu);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

}