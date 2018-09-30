package com.modules.controller.wxfx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.modules.pojo.SysWxUser;
import com.modules.pojo.User;
import com.modules.service.SysWxUserService;
import com.modules.service.UserService;
/**
 * 供应商...
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("wxfx")
public class WxfxController extends BaseController {

	private static Logger logger = Logger.getLogger(WxfxController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SysWxUserService sysWxUserService;


	@RequestMapping(value = "binding")
	public ModelAndView bindMobile(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/binding");
		SysWxUser sysWxUser = (SysWxUser) session.getAttribute(Constants.WX);
		if (StringUtils.isNotBlank(sysWxUser.getUserid())) {
			User user = new User();
			user.setId(sysWxUser.getUserid());
			try {
				user = userService.findObject(user);
				modelAndView.addObject("username", user.getUsername());
				session.setAttribute(Constants.USER, user);
				String userid = user.getParentid();
				if (StringUtils.isEmpty(userid)) {
					userid = user.getId();
				}

				User company = new User();
				company.setId(userid);
				company = userService.findObject(company);
				session.setAttribute(Constants.COMPANY, company);

			} catch (Exception e) {
				logger.error(e);
			}

		}
		return modelAndView;
	}

	@RequestMapping(value = "banduser")
	@ResponseBody
	public Object banduser(String username, String password) {
		try {

			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userService.findObject(user);

			if (u == null) {
				return this.getJsonResult("9999", "用户名或密码错误!");
			} else {
				User company;
				if (StringUtils.isNotBlank(u.getParentid())) {
					company = new User();
					company.setId(u.getId());
					company = userService.findObject(company);
				} else {
					company = u;
				}
				if (u.getRoleids().indexOf("05D8DCFAB55440F88EBCBC8C015BC690") > -1) {
					SysWxUser sysWxUser = (SysWxUser) session.getAttribute(Constants.WX);
					sysWxUser.setUserid(u.getId());
					SysWxUser tsysWxUser = new SysWxUser();
					tsysWxUser.setId(sysWxUser.getId());
					tsysWxUser.setUserid(u.getId());
					sysWxUserService.update(tsysWxUser);
					session.setAttribute(Constants.USER, u);
					session.setAttribute(Constants.COMPANY, company);

				} else {
					return this.getJsonResult("9999", "用户名或密码错误!");
				}
				return this.getJsonResult();
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}


	
	/**
	 * 消费者分析
	 * **/
	@RequestMapping(value = "/wxxfzfx")
	public ModelAndView wxxfzfx(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxxfzfx");
		return modelAndView;
	}
	
	/**
	 * 消费者分析排名
	 * **/
	@RequestMapping(value = "/wxxfzfxpm")
	public ModelAndView wxxfzfxpm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxxfzfxpm");
		return modelAndView;
	}
	
	/**
	 * 联合用药文章
	 * **/
	@RequestMapping(value = "/wxhy")
	public ModelAndView wxhy(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxhy");
		return modelAndView;
	}
	
	/**
	 * 下载页
	 * **/
	@RequestMapping(value = "/wxxz")
	public ModelAndView wxxz(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxxz");
		return modelAndView;
	}
	
	/**
	 * 总体分析
	 * **/
	@RequestMapping(value = "/wxztfx")
	public ModelAndView wxztfx(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxztfx");
		return modelAndView;
	}
	
	/**
	 * 联合用药水平
	 * **/
	@RequestMapping(value = "/wxlhyysp")
	public ModelAndView wxlhyysp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxlhyysp");
		return modelAndView;
	}
	
	/**
	 * 联合用药水平详情
	 * **/
	@RequestMapping(value = "/wxlhyyspxq")
	public ModelAndView wxlhyyspxq(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxlhyyspxq");
		return modelAndView;
	}
	
	/**
	 * 路由跳转
	 * **/
	@RequestMapping(value = "/wxlhyyjz")
	public ModelAndView wxlhyyjz(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxlhyyjz");
		return modelAndView;
	}
	
	/**
	 * 联合用药进展
	 * **/
	@RequestMapping(value = "/wxurlredirect")
	public ModelAndView wxurlredirect(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wxfx/wxurlredirect");
		return modelAndView;
	}

}