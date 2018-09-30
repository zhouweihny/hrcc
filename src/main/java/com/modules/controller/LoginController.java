package com.modules.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.netease.NeteaseUtil;
import com.commons.util.netease.bean.NeteaseResponse;
import com.modules.pojo.Catalog;
import com.modules.pojo.Space;
import com.modules.pojo.SpaceCatalog;
import com.modules.pojo.SysMenu;
import com.modules.pojo.SysRole;
import com.modules.pojo.SysRoleMenu;
import com.modules.pojo.User;
import com.modules.service.CatalogService;
import com.modules.service.FxTreeMetaService;
import com.modules.service.SpaceCatalogService;
import com.modules.service.SpaceService;
import com.modules.service.SysMenuService;
import com.modules.service.SysRoleMenuService;
import com.modules.service.UserRoleService;
import com.modules.service.UserService;

@Controller
@RequestMapping
public class LoginController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private UserService userSerivce;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SpaceService spaceService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private SpaceCatalogService spaceCatalogService;
	@Autowired
	private FxTreeMetaService fxTreeMetaService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView glogin(String wxerr) {
		ModelAndView modelAndView = new ModelAndView("login");
		if (StringUtils.isNotBlank(wxerr)) {
			modelAndView.addObject("message", "请关注公告号\"三行品类管理\",绑定用户!");
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView plogin(String username, String password) {
		ModelAndView modelAndView = new ModelAndView("index");
		try {
			User user = new User();
			user.setUsername(username);
			password = DigestUtils.md5Hex(password);
			user.setPassword(password);
			User u = userSerivce.findObject(user);
			if (u == null) {
				modelAndView.addObject("message", "用户名或密码错误");
				modelAndView.setViewName("login");
			} else {
				session.setAttribute(Constants.USER, u);
				String userid = u.getParentid();
				if (StringUtils.isBlank(userid)) {
					userid = u.getId();
				}
				User company = new User();
				company.setId(userid);
				company = userSerivce.findObject(company);
				session.setAttribute(Constants.COMPANY, company);

				final String companyid = userid;
				if (u.getRoleids().indexOf("05D8DCFAB55440F88EBCBC8C015BC690") > -1) {
					new Thread() {
						public void run() {
							try {
//								fxTreeMetaService.refreshCache(companyid);
							} catch (Exception e) {
								logger.error(e);
							}
						}
					}.start();

					return new ModelAndView("redirect:/indexs.do");
				}

				return new ModelAndView("redirect:/index.do");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "sendcode")
	@ResponseBody
	public Object sendcode(String mobileno) {
		try {
			NeteaseResponse neteaseResponse = NeteaseUtil.sendCode(mobileno);
			if (neteaseResponse.getCode().equals("200")) {
				return this.getJsonResult();
			}
			return this.getJsonResult("9999", "获取验证码失败!");
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "verifycode")
	@ResponseBody
	public Object verifycode(String mobileno, String code) {
		try {
			NeteaseResponse neteaseResponse = NeteaseUtil.verifyCode(mobileno, code);
			if (neteaseResponse.getCode().equals("200")) {

				User user = new User();
				user.setMobile(mobileno);
				User u = userSerivce.findObject(user);
				if (u == null) {
					return this.getJsonResult("9999", "用户名不存在!");
				} else {
					session.setAttribute(Constants.USER, u);
					String userid = u.getParentid();
					if (StringUtils.isBlank(userid)) {
						userid = u.getId();
					}
					User company = new User();
					company.setId(userid);
					company = userSerivce.findObject(company);
					session.setAttribute(Constants.COMPANY, company);
					return this.getJsonResult();
				}
			}
			return this.getJsonResult("9999", "验证失败!");
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}

	@RequestMapping(value = "closenew")
	@ResponseBody
	public Object closenew() {
		User user = (User) session.getAttribute(Constants.USER);
		user.setNewflag(false);
		try {
			userSerivce.update(user);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(HttpServletRequest request, User user) {
		try {

			User u = new User();
			u.setUsername(user.getUsername());
			u = userSerivce.findObject(u);
			if (u == null) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
				user.setNewflag(true);
				List<String> roleids = new ArrayList<String>();
				roleids.add(request.getParameter("roleid"));
				user.setRoleids(roleids);
				userSerivce.save(user);

				Space space = new Space();
				space.setMyself(true);
				space.setName("品种对码项目");
				space.setUserid(user.getId());
				spaceService.save(space);

				Catalog catalog = new Catalog();
				catalog.setName(user.getCompany() + "品种目录");
				catalog.setMyself(true);
				catalog.setRemark("品种");
				catalog.setUserid(user.getId());
				catalogService.save(catalog);

				SpaceCatalog spaceCatalog = new SpaceCatalog();
				spaceCatalog.setCatalogid(catalog.getId());
				spaceCatalog.setSpaceid(space.getId());
				spaceCatalog.setStandard(true);
				spaceCatalogService.save(spaceCatalog);

				session.setAttribute(Constants.USER, user);
				String userid = user.getParentid();
				if (StringUtils.isBlank(userid)) {
					userid = user.getId();
				}
				User company = new User();
				company.setId(userid);
				company = userSerivce.findObject(company);
				session.setAttribute(Constants.COMPANY, company);
				return this.getJsonResult();
			} else {
				return this.getJsonResult("9999", "用户名已存在!");
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView modelAndView = new ModelAndView("login");
		session.removeAttribute(Constants.USER);
		return modelAndView;
	}

	@RequestMapping(value = "index", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("index");
		PageResult<SysMenu> menus;
		try {
			User user = (User) session.getAttribute(Constants.USER);

			if (user.getRoleids().indexOf("05D8DCFAB55440F88EBCBC8C015BC690") > -1) {
				return new ModelAndView("redirect:/indexs.do");
			}

			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			menus = sysMenuService.findList(new HashMap<String, Object>(), new Page(false));
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN)) {
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("userid", user.getId());
				PageResult<SysRoleMenu> page = sysRoleMenuService.findList(params, new Page(false));
				List<SysMenu> tms = new ArrayList<SysMenu>();
				for (SysMenu sm : menus.getData()) {
					for (SysRoleMenu rm : page.getData()) {
						if (rm.getMenuid().equals(sm.getId())) {
							tms.add(sm);
							break;
						}
					}
				}

				Iterator<SysMenu> iter = menus.getData().iterator();
				boolean flag = true;
				while (iter.hasNext()) {
					SysMenu sm = iter.next();
					for (SysMenu rm : tms) {
						if (rm.getPath().indexOf(sm.getId()) > -1) {
							flag = false;
							break;
						}
					}
					if (flag)
						iter.remove();
					flag = true;
				}

			}
			modelAndView.addObject("menus", menus.getData());
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "indexs", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public ModelAndView indexs(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("indexs");
		try {
			User user = (User) session.getAttribute(Constants.USER);
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "zuiDoc")
	public ModelAndView zuiDoc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("zuiDoc");
		return modelAndView;
	}
}