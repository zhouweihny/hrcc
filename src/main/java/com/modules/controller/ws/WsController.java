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
 * 供应商...
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("ws")
public class WsController extends BaseController {

	private static Logger logger = Logger.getLogger(WsController.class);
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
		ModelAndView modelAndView = new ModelAndView("ws/binding");
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
					if (u.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1 || u.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1
							|| company.getRoleids().indexOf("B1CC34941707470D9BF361D1CEF2B08E") > -1) {
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
				if (u.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1 || u.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1
						|| company.getRoleids().indexOf("B1CC34941707470D9BF361D1CEF2B08E") > -1) {
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
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);
		try {
			if (user.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1 || company.getRoleids().indexOf("B1CC34941707470D9BF361D1CEF2B08E") > -1
					|| user.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1) {
				Bill tbill = new Bill();
				tbill.setId(billid);
				tbill = billService.findObject(tbill);
				if (tbill.getStatus() < 1) {
					Bill bill = new Bill();
					bill.setId(billid);
					bill.setStatus(1);
					billService.update(bill);
				}
			}

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
	@ResponseBody
	public Object billlist(Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("ws/billpage_item");
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			// 供应商处理
			// ---- 供应商二级用户
			if (user.getRoleids().indexOf("F717B4EEF241460891F0E1D3AB84F74E") > -1) {
				params.put("contactid", user.getId());
				params.put("notpurchaser", true);
			}
			if (user.getRoleids().indexOf("B1CC34941707470D9BF361D1CEF2B08E") > -1) {
				params.put("supplierid", user.getId());
				params.put("notpurchaser", true);
			}

			// 代理
			if (user.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1) {
				params.put("agentid", user.getId());
				params.put("notpurchaser", true);
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
	public ModelAndView supperorderpage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("ws/billpage");
		return modelAndView;
	}

	@RequestMapping(value = "billpagesub")
	public ModelAndView billpagesub(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("ws/billpagesub");
		return modelAndView;
	}

}