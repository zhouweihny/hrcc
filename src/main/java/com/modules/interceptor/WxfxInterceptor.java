package com.modules.interceptor;

import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.commons.base.Constants;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.res.OpenIdRequest;
import com.commons.util.wx.bean.res.OpenIdResponse;
import com.commons.util.wx.bean.res.UserInfoRequest;
import com.commons.util.wx.bean.res.UserInfoResponse;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.SysWxUser;
import com.modules.pojo.User;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxService;
import com.modules.service.SysWxUserService;
import com.modules.service.UserService;

/**
 * @author Du.Jun
 * 
 */
@Repository
public class WxfxInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@Autowired
	private SysWxService sysWxService;

	@Autowired
	private SysWxUserService sysWxUserService;

	@Autowired
	private UserService userService;

 
	private String wscode="sanxing";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getContextPath();

		String clearsession = request.getParameter("clearsession");
		if (!StringUtils.isEmpty(clearsession)) {
			request.getSession().removeAttribute(Constants.WX);
			request.getSession().removeAttribute(Constants.COMPANY);
			request.getSession().removeAttribute(Constants.USER);
		}

		if (request.getSession().getAttribute(Constants.WX) == null) {
			String code = request.getParameter("code");
			SysWx sysWx = new SysWx();
			sysWx.setCode(wscode);
			sysWx = sysWxService.findObject(sysWx);
			OpenIdRequest openIdRequest = new OpenIdRequest();
			openIdRequest.setCode(code);
			openIdRequest.setAppid(sysWx.getAppid());
			openIdRequest.setAppsecret(sysWx.getAppsecret());
			SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
			sysWxAccesstoken.setWxid(sysWx.getId());
			sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
			String openid = "";

			if (!StringUtils.isEmpty(code)) {
				SysWxUser sysWxUser = new SysWxUser();
				if (code.equals("test")) {
					sysWxUser.setOpenid("oM3ui0394NQk8KbRw0nY4JPxuS80");
				} else {
					OpenIdResponse openIdResponse = WxUtil.getOpenid(openIdRequest);
					if (openIdResponse.getOpenid() != null) {
						sysWxUser.setOpenid(openIdResponse.getOpenid());
						openid = openIdResponse.getOpenid();
					}
				}
				sysWxUser = sysWxUserService.findObject(sysWxUser);
				if (sysWxUser == null) {
					UserInfoRequest userInfoRequest = new UserInfoRequest();
					userInfoRequest.setAccess_token(sysWxAccesstoken.getAccesstoken());
					userInfoRequest.setOpenid(openid);
					UserInfoResponse userInfoResponse = WxUtil.getUserInfo(userInfoRequest);
					sysWxUser = new SysWxUser();
					sysWxUser.setWxid(sysWx.getId());
					sysWxUser.setCity(userInfoResponse.getCity());
					sysWxUser.setNickname(userInfoResponse.getNickname());
					sysWxUser.setCountry(userInfoResponse.getCountry());
					sysWxUser.setCreatetime(new Date());
					sysWxUser.setHeadimgurl(userInfoResponse.getHeadimgurl());
					sysWxUser.setLanguage(userInfoResponse.getLanguage());
					sysWxUser.setOpenid(userInfoResponse.getOpenid());
					sysWxUser.setProvince(userInfoResponse.getProvince());
					sysWxUser.setSex(userInfoResponse.getSex() + "");
					sysWxUser.setSubscribe(true);
					sysWxUser.setSubscribeTime(new Date());
					sysWxUser.setUnionid(userInfoResponse.getUnionid());
					sysWxUserService.save(sysWxUser);
				} else {
					sysWxUser.setSubscribe(true);
					sysWxUserService.update(sysWxUser);
				}
				request.getSession().setAttribute(Constants.WX, sysWxUser);
				if (StringUtils.isEmpty(sysWxUser.getUserid())) {
					response.sendRedirect(path + "/wxfx/binding.do?redirect=" + URLEncoder.encode(request.getRequestURI() + "?" + request.getQueryString(), "utf-8"));
					return false;
				}
				User user = new User();
				user.setId(sysWxUser.getUserid());
				user = userService.findObject(user);
				if (user == null || StringUtils.isEmpty(sysWxUser.getUserid())) {
					response.sendRedirect(path + "/wxfx/binding.do?redirect=" + URLEncoder.encode(request.getRequestURI() + "?" + request.getQueryString(), "utf-8"));
					return false;
				} else {
					request.getSession().setAttribute(Constants.USER, user);

					String userid = user.getParentid();
					if (StringUtils.isEmpty(userid)) {
						userid = user.getId();
					}
					User company = new User();
					company.setId(userid);
					company = userService.findObject(company);
					request.getSession().setAttribute(Constants.COMPANY, company);
				}
			}
		}

		return super.preHandle(request, response, handler);
	}
}
