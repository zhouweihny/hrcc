package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.UserRoleService;
import com.modules.service.UserService;
import com.modules.pojo.SysRole;
import com.modules.pojo.User;
import com.modules.pojo.UserRole;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(User user, HttpServletRequest request, HttpServletResponse response) {
		User u = (User) session.getAttribute(Constants.USER);
		User company = (User) session.getAttribute(Constants.COMPANY);
		List<String> roleids = new ArrayList<String>();
		try {
			if (StringUtils.isEmpty(user.getId())) {
				User us = new User();
				us.setUsername(user.getUsername());
				us = userService.findObject(us);

				if (us == null) {
					user.setPassword(DigestUtils.md5Hex(user.getPassword()));
					user.setRoleids(roleids);
					if (company.getRoleids().indexOf("001592F74BBD467F86FC7C0671C1AE1D") > -1) {
						roleids.add("D065F713B0434A24871A27356EC34740");
					} else if (company.getRoleids().indexOf("A6B9CC86F7F24156A2CC50895312CC03") > -1) {
						roleids.add("9334387B790C42658B7BE0655E155592");
					} else if (company.getRoleids().indexOf("05D8DCFAB55440F88EBCBC8C015BC690") > -1) {
						roleids.add("05D8DCFAB55440F88EBCBC8C015BC690");
					}
					user.setParentid(u.getId());
					user.setNewflag(true);
					userService.save(user);
				} else {
					if (StringUtils.isEmpty(us.getParentid())) {
						user.setParentid(u.getId());
						UserRole userRole = new UserRole();
						UserRole tuserRole = new UserRole();
						if (company.getRoleids().indexOf("001592F74BBD467F86FC7C0671C1AE1D") > -1) {
							userRole.setRoleid("D065F713B0434A24871A27356EC34740");
							tuserRole.setRoleid("D065F713B0434A24871A27356EC34740");
						} else if (company.getRoleids().indexOf("A6B9CC86F7F24156A2CC50895312CC03") > -1) {
							userRole.setRoleid("9334387B790C42658B7BE0655E155592");
							tuserRole.setRoleid("D065F713B0434A24871A27356EC34740");
						}
						userRole.setUserid(us.getId());
						user.setPassword(DigestUtils.md5Hex(user.getPassword()));
						tuserRole = userRoleService.findObject(tuserRole);
						if (tuserRole == null)
							userRoleService.save(userRole);
						userService.update(user);
					}
					return this.getJsonResult("9999", "用户名已存在!");
				}
			} else {
				userService.update(user);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "modifypassword", method = RequestMethod.GET)
	public ModelAndView modifypassword(User user, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("user/modifypassword");
		modelAndView.addObject("data", user);
		return modelAndView;

	}

	@RequestMapping(value = "modifypassword", method = RequestMethod.POST)
	@ResponseBody
	public Object pmodifypassword(String id, String oldpassword, String newpassword1, String newpassword2, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!StringUtils.isEmpty(id)) {
				User user = new User();
				user.setId(id);
				user = userService.findObject(user);
				if (user != null && user.getPassword() == null) {
					if (newpassword1.equals(newpassword2)) {
						user.setPassword(DigestUtils.md5Hex(newpassword1));
						userService.update(user);
						User sessionUser = (User) session.getAttribute(Constants.USER);
						if (sessionUser.getId().equals(id)) {
							sessionUser.setPassword(newpassword1);
						}
					} else {
						return this.getJsonResult("9999", "两次密码不一致");
					}
				} else if (user != null && user.getPassword().equals(DigestUtils.md5Hex(oldpassword))) {
					if (newpassword1.equals(newpassword2)) {
						user.setPassword(DigestUtils.md5Hex(newpassword1));
						userService.update(user);
						User sessionUser = (User) session.getAttribute(Constants.USER);
						if (sessionUser.getId().equals(id)) {
							sessionUser.setPassword(newpassword1);
						}
					} else {
						return this.getJsonResult("9999", "两次密码不一致");
					}
				} else {
					return this.getJsonResult("9999", "输入密码错误");
				}
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(User user) {
		try {
			if (!StringUtils.isEmpty(user.getId()))
				userService.delete(user);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(User user) {
		ModelAndView modelAndView = new ModelAndView("user/info");
		try {
			if (!StringUtils.isEmpty(user.getId())) {
				User data = userService.findObject(user);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(User user) {
		try {
			User data = userService.findObject(user);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "/list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("user/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String username, String mobile, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("user/table");
		User u = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!u.getUsername().equals(Constants.ADMIN)) {
				params.put("parentid", u.getId());
			}
			if (!StringUtils.isEmpty(name))
				params.put("name", name);
			if (!StringUtils.isEmpty(mobile))
				params.put("mobile", mobile);
			if (!StringUtils.isEmpty(username))
				params.put("username", username);
			PageResult<User> data = userService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}