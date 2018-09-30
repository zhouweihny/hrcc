package com.modules.controller.ws;

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
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.netease.NeteaseUtil;
import com.commons.util.netease.bean.NeteaseResponse;
import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;
import com.modules.pojo.SysWxUser;
import com.modules.pojo.User;
import com.modules.service.BillDetailService;
import com.modules.service.BillService;
import com.modules.service.PurchaseDetailService;
import com.modules.service.SysWxUserService;
import com.modules.service.UserService;
import com.modules.vo.PurchaseDetailVo;

/**
 * 采购商
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("wsp")
public class WspController extends BaseController {

	private static Logger logger = Logger.getLogger(WspController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SysWxUserService sysWxUserService;
	@Autowired
	private PurchaseDetailService purchaseDetailService;
	@Autowired
	private BillDetailService billDetailService;
	@Autowired
	private BillService billService;

	@RequestMapping(value = "binding")
	public ModelAndView bindMobile(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wsp/binding");
		SysWxUser sysWxUser = (SysWxUser) session.getAttribute(Constants.WX);
		if (StringUtils.isNotBlank(sysWxUser.getUserid())) {
			User user = new User();
			user.setId(sysWxUser.getUserid());
			try {
				user = userService.findObject(user);
				modelAndView.addObject("username", user.getUsername());
				modelAndView.addObject("mobileno", user.getMobile());

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

	@RequestMapping(value = "sendcode")
	@ResponseBody
	public Object sendcode(String mobileno) {
		try {
			User user = new User();
			user.setMobile(mobileno);
			User u = userService.findObject(user);
			if (u == null) {
				return this.getJsonResult("9999", "该手机用户不存在!");
			}

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
	public Object verifycode(String mobileno, String mcode) {
		try {
			NeteaseResponse neteaseResponse = NeteaseUtil.verifyCode(mobileno, mcode);
			if (neteaseResponse.getCode().equals("200")) {
				User user = new User();
				user.setMobile(mobileno);
				User u = userService.findObject(user);

				if (u == null) {
					return this.getJsonResult("9999", "该手机用户不存在!");
				} else {
					User company;
					if (StringUtils.isNotBlank(u.getParentid())) {
						company = new User();
						company.setId(u.getId());
						company = userService.findObject(company);
					} else {
						company = u;
					}
					if (u.getRoleids().indexOf("A6B9CC86F7F24156A2CC50895312CC03") > -1 || u.getRoleids().indexOf("9334387B790C42658B7BE0655E155592") > -1) {
						SysWxUser sysWxUser = (SysWxUser) session.getAttribute(Constants.WX);
						sysWxUser.setUserid(u.getId());
						SysWxUser tsysWxUser = new SysWxUser();
						tsysWxUser.setId(sysWxUser.getId());
						tsysWxUser.setUserid(u.getId());
						sysWxUserService.update(tsysWxUser);
						session.setAttribute(Constants.USER, u);
						session.setAttribute(Constants.COMPANY, company);
					} else {
						return this.getJsonResult("9999", "该手机用户不存在!");
					}
					return this.getJsonResult();
				}
			}
			return this.getJsonResult("9999", "验证失败!");
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
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
				if (u.getRoleids().indexOf("A6B9CC86F7F24156A2CC50895312CC03") > -1 || u.getRoleids().indexOf("9334387B790C42658B7BE0655E155592") > -1) {
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

	@RequestMapping(value = "billdetail")
	@ResponseBody
	public Object billdetail(String billid, Integer currentPage, Integer pageSize) {
		try {

			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("billid", billid);
			PageResult<BillDetail> data = billDetailService.findList(params, page);

			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "purchasedetail")
	@ResponseBody
	public Object purchasedetail(String purchaseid, Integer currentPage, Integer pageSize) {
		try {

			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			params.put("purchaseid", purchaseid);
			PageResult<PurchaseDetailVo> data = purchaseDetailService.findPurchaseDetailVoList(params, page);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "billpageitem")
	public ModelAndView billlist(Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("wsp/billpage_item");
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);

		try {
			Page page = new Page(currentPage, pageSize, true);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 采购处理
			// 采购
			if (user.getRoleids().indexOf("A6B9CC86F7F24156A2CC50895312CC03") > -1) {
				params.put("purchaserid", user.getId());
			}

			// 采购采购员
			if (user.getRoleids().indexOf("9334387B790C42658B7BE0655E155592") > -1) {
				if (!StringUtils.isEmpty(user.getErpusername()))
					params.put("erpusername", user.getErpusername());
				params.put("companyid", company.getId());
			}

			params.put("stype", "desc");
			params.put("sfield", "createtime");
			PageResult<Bill> data = billService.findList(params, page);
			modelAndView.addObject("data", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "billpage")
	public ModelAndView purchaseorderpage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wsp/billpage");
		return modelAndView;
	}

	@RequestMapping(value = "billdetailpage")
	public ModelAndView purchaseorderdetailpage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wsp/billdetailpage");
		return modelAndView;
	}

	@RequestMapping(value = "billpagesub")
	public ModelAndView billpagesub(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("wsp/billpagesub");
		return modelAndView;
	}
}