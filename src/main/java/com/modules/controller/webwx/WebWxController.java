package com.modules.controller.webwx;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.res.AccessTokenRequest;
import com.commons.util.wx.bean.res.AccessTokenResponse;
import com.commons.util.wx.bean.res.OpenIdRequest;
import com.commons.util.wx.bean.res.OpenIdResponse;
import com.commons.util.wx.bean.res.UserInfoRequest;
import com.commons.util.wx.bean.res.UserInfoResponse;
import com.modules.pojo.SysWxUser;
import com.modules.pojo.User;
import com.modules.service.SysWxUserService;
import com.modules.service.UserService;

@Controller
@RequestMapping("webwx")
public class WebWxController extends BaseController {

	private static Logger logger = Logger.getLogger(WebWxController.class);

	@Value("#{configProperties['webwx.appid']}")
	private String appId;
	@Value("#{configProperties['webwx.appsecret']}")
	private String secret;
	private String state = "998";
	@Value("#{configProperties['webwx.redirect_uri']}")
	private String redirect_uri;

	@Autowired
	private SysWxUserService sysWxUseService;

	@Autowired
	private UserService userSerivce;

	@RequestMapping("qrlogin")
	public ModelAndView qrConnectURL() {
		String uri = "https://open.weixin.qq.com/connect/qrconnect?appid=" + appId + "&redirect_uri=" + redirect_uri
				+ "&response_type=code&scope=snsapi_login&state=" + state + "#wechat_redirect";
		return new ModelAndView(new RedirectView(uri));
	}

	/**
	 * PC扫码登陆回调 获取AccessToken以及用户信息跟微信公众号授权用户用户信息一样
	 */

	@RequestMapping("login")
	public ModelAndView webCallBack(String code, String state) {

		ModelAndView modelAndView = new ModelAndView();
		
//		AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
//		accessTokenRequest.setAppid(appId);
//		accessTokenRequest.setSecret(secret);
//		AccessTokenResponse accessTokenResponse = WxUtil.getAccessToken(accessTokenRequest);

		OpenIdRequest openIdRequest = new OpenIdRequest();
		openIdRequest.setAppid(appId);
		openIdRequest.setAppsecret(secret);
		openIdRequest.setCode(code);
		OpenIdResponse openIdResponse = WxUtil.getOpenid(openIdRequest);
 

		SysWxUser sysWxUser = new SysWxUser();
		sysWxUser.setWxid("9CC6C3AB366E430F82893A8035AB8A6E");
		sysWxUser.setUnionid(openIdResponse.unionid);
		try {
			sysWxUser = sysWxUseService.findObject(sysWxUser);
			if (sysWxUser != null && sysWxUser.getUserid() != null) {
				User user = new User();
				user.setId(sysWxUser.getUserid());
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
					if (u.getRoleids().indexOf("05D8DCFAB55440F88EBCBC8C015BC690") > -1) {
						return new ModelAndView("redirect:/indexs.do");
					}
					return new ModelAndView("redirect:/index.do");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/login.do?wxerr=9999");
	}

}
