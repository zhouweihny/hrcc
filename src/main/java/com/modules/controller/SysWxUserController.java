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
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.SysWxUserService;
import com.modules.pojo.SysWxUser;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxuser")
public class SysWxUserController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxUserController.class);

	@Autowired
	private SysWxUserService sysWxUserService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWxUser sysWxUser, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWxUser.getId())) {
				sysWxUserService.save(sysWxUser);
			} else {
				sysWxUserService.update(sysWxUser);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxUser sysWxUser) {
		try {
			sysWxUserService.delete(sysWxUser);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxUser sysWxUser) {
		ModelAndView modelAndView = new ModelAndView("syswxuser/info");
		try {
	  		if (!StringUtils.isEmpty(sysWxUser.getId())) {
				SysWxUser data = sysWxUserService.findObject(sysWxUser);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxUser sysWxUser) {
		try {
			SysWxUser data = sysWxUserService.findObject(sysWxUser);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxuser/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response,String wxid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxuser/table");
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
			PageResult<SysWxUser> data = sysWxUserService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}