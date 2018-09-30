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
import com.modules.service.SysWxAccesstokenService;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxaccesstoken")
public class SysWxAccesstokenController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxAccesstokenController.class);

	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWxAccesstoken sysWxAccesstoken, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWxAccesstoken.getId())) {
				sysWxAccesstokenService.save(sysWxAccesstoken);
			} else {
				sysWxAccesstokenService.update(sysWxAccesstoken);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxAccesstoken sysWxAccesstoken) {
		try {
			sysWxAccesstokenService.delete(sysWxAccesstoken);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxAccesstoken sysWxAccesstoken) {
		ModelAndView modelAndView = new ModelAndView("syswxaccesstoken/info");
		try {
			if (!StringUtils.isEmpty(sysWxAccesstoken.getId())) {
				SysWxAccesstoken data = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxAccesstoken sysWxAccesstoken) {
		try {
			SysWxAccesstoken data = sysWxAccesstokenService.findObject(sysWxAccesstoken);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxaccesstoken/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String wxid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxaccesstoken/table");
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
			PageResult<SysWxAccesstoken> data = sysWxAccesstokenService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping("refresh")
	@ResponseBody
	public Object refresh(String wxid, HttpServletRequest request, HttpServletResponse response) {
		try {
			SysWx sysWx = new SysWx();
			sysWx.setId(wxid);
			sysWxAccesstokenService.refresh(sysWx);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}
}