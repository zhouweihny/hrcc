package com.modules.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.base.BaseController;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.res.OpenIdRequest;
import com.commons.util.wx.bean.res.OpenIdResponse;
import com.commons.util.wx.bean.res.QrcodeRequest;
import com.commons.util.wx.bean.res.QrcodeResponse;
import com.commons.util.wx.bean.res.UserInfoRequest;
import com.commons.util.wx.bean.res.UserInfoResponse;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxService;

@Controller
@RequestMapping("wx/i")
public class WxInterfaceContorller extends BaseController {

	private static Logger logger = Logger.getLogger(WxInterfaceContorller.class);
	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;
	@Autowired
	private SysWxService sysWxService;

	/**
	 * 获取微信accestoken
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/accesstoken")
	@ResponseBody
	public Object accesstoken(HttpServletRequest request, HttpServletResponse response, String wxcode) throws Exception {
		SysWx sysWx = new SysWx();
		sysWx.setCode(wxcode);
		sysWx = sysWxService.findObject(sysWx);
		SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
		sysWxAccesstoken.setWxid(sysWx.getId());
		sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
		return this.getJsonResult(sysWxAccesstoken.getAccesstoken());
	}

	/**
	 * 获取微信userinfo
	 * 
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userinfo")
	@ResponseBody
	public Object accesstoken(HttpServletRequest request, HttpServletResponse response, String wxcode, String code) throws Exception {
		SysWx sysWx = new SysWx();
		sysWx.setCode(wxcode);
		sysWx = sysWxService.findObject(sysWx);
		SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
		sysWxAccesstoken.setWxid(sysWx.getId());
		sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
		OpenIdRequest openIdRequest = new OpenIdRequest();
		openIdRequest.setAppid(sysWx.getAppid());
		openIdRequest.setAppsecret(sysWx.getAppsecret());
		openIdRequest.setCode(code);
		OpenIdResponse openIdResponse = WxUtil.getOpenid(openIdRequest);
		UserInfoRequest userInfoRequest = new UserInfoRequest();
		userInfoRequest.setAccess_token(sysWxAccesstoken.getAccesstoken());
		userInfoRequest.setOpenid(openIdResponse.getOpenid());
		UserInfoResponse userInfoResponse = WxUtil.getUserInfo(userInfoRequest);
		return this.getJsonResult(userInfoResponse);
	}

	/**
	 * 获取生成公共号二维码
	 * 
	 * @param request
	 * @param response
	 * @param wxcode
	 *            微信code
	 * @param type
	 *            生成类型 true 永久, false 临时
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/qrcode")
	@ResponseBody
	public Object qrcode(HttpServletRequest request, HttpServletResponse response, String wxcode, Boolean type, String scenestr, Integer sceneid, Integer expireseconds) throws Exception {
		SysWx sysWx = new SysWx();
		sysWx.setCode(wxcode);
		sysWx = sysWxService.findObject(sysWx);
		SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
		sysWxAccesstoken.setWxid(sysWx.getId());
		sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
		String accesstoken = sysWxAccesstoken.getAccesstoken();
		QrcodeRequest qrcodeRequest = new QrcodeRequest();
		qrcodeRequest.setAccess_token(accesstoken);
		qrcodeRequest.setExpireseconds(expireseconds);
		qrcodeRequest.setType(type);
		qrcodeRequest.setScenestr(scenestr);
		qrcodeRequest.setSceneid(sceneid);
		QrcodeResponse qrcodeResponse = WxUtil.responseQrq(qrcodeRequest);
		return this.getJsonResult(qrcodeResponse);
	}
}
