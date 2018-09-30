package com.modules.controller.wx;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.util.wx.EventXmlUtil;
import com.commons.util.wx.MsgXmlUtil;
import com.commons.util.wx.SignUtil;
import com.commons.util.wx.WxJsApiSign;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.BaseMsg;
import com.commons.util.wx.bean.WxJsConfig;
import com.commons.util.wx.bean.event.BaseEvent;
import com.commons.util.wx.bean.event.ClickEvent;
import com.commons.util.wx.bean.event.ScanEvent;
import com.commons.util.wx.bean.event.SubscribeEvent;
import com.commons.util.wx.bean.msg.ResponseArticleMsg;
import com.commons.util.wx.bean.msg.ResponseArticleMsg.Article;
import com.commons.util.wx.bean.msg.ResponseTextMsg;
import com.commons.util.wx.bean.res.UserInfoRequest;
import com.commons.util.wx.bean.res.UserInfoResponse;
import com.google.gson.Gson;
import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.SysWxMenu;
import com.modules.pojo.SysWxMsg;
import com.modules.pojo.SysWxUser;
import com.modules.pojo.SysWxUserQrcode;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxMenuService;
import com.modules.service.SysWxMsgService;
import com.modules.service.SysWxService;
import com.modules.service.SysWxUserQrcodeService;
import com.modules.service.SysWxUserService;

@Controller
@RequestMapping("wx")
public class WxContorller {
	private static Logger logger = Logger.getLogger(WxContorller.class);

	@Autowired
	private SysWxService sysWxService;

	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@Autowired
	private SysWxMenuService sysWxMenuService;

	@Autowired
	private SysWxUserService sysWxUserService;

	@Autowired
	private SysWxMsgService sysWxMsgService;
	@Autowired
	private SysWxUserQrcodeService sysWxUserQrcodeService;
	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@Value("#{configProperties['img.url']}")
	private String imgUrl;

	@RequestMapping(value = "/{code}/deal", method = RequestMethod.POST)
	@ResponseBody
	public Object deal(HttpServletRequest request, @PathVariable("code") String code) {

		String xml;
		try {
			xml = IOUtils.toString(request.getInputStream(), "UTF-8");

			BaseMsg baseMsg = EventXmlUtil.parseXml(xml, BaseMsg.class);

			if ("event".equalsIgnoreCase(baseMsg.getMsgType())) {
				BaseEvent baseEvent = EventXmlUtil.parseXml(xml, BaseEvent.class);
				SysWx sysWx = new SysWx();
				sysWx.setCode(code);
				sysWx = sysWxService.findObject(sysWx);
				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(sysWx.getId());
				sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				String event = baseEvent.getEvent();
				// 已关注时
				if (event.equalsIgnoreCase("scan")) {
					ScanEvent scanEvent = EventXmlUtil.parseXml(xml, ScanEvent.class);
					SysWxUser sysWxUser = new SysWxUser();
					sysWxUser.setOpenid(baseEvent.getFromUserName());
					sysWxUser = sysWxUserService.findObject(sysWxUser);
					String qrscene = scanEvent.getEventKey();
					SysWxUserQrcode sysWxUserQrcode = new SysWxUserQrcode();
					sysWxUserQrcode.setWxuserid(sysWxUser.getId());
					sysWxUserQrcode.setQrscene(qrscene);
					SysWxUserQrcode tsysWxUserQrcode = sysWxUserQrcodeService.findObject(sysWxUserQrcode);
					if (tsysWxUserQrcode == null) {
						sysWxUserQrcodeService.save(sysWxUserQrcode);
					} else {
						dozerBeanMapper.map(sysWxUserQrcode, tsysWxUserQrcode);
						sysWxUserQrcodeService.update(tsysWxUserQrcode);
					}
				}
				// 关注事件
				else if (event.equalsIgnoreCase("subscribe")) {
					SubscribeEvent subscribeEvent = EventXmlUtil.parseXml(xml, SubscribeEvent.class);

					String qrscene = "";

					UserInfoRequest userInfoRequest = new UserInfoRequest();
					userInfoRequest.setAccess_token(sysWxAccesstoken.getAccesstoken());
					userInfoRequest.setOpenid(baseEvent.getFromUserName());
					UserInfoResponse userInfoResponse = WxUtil.getUserInfo(userInfoRequest);
					SysWxUser sysWxUser = new SysWxUser();
					sysWxUser.setOpenid(baseEvent.getFromUserName());
					sysWxUser = sysWxUserService.findObject(sysWxUser);
					if (sysWxUser == null) {
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

					// 判断是否通过二维码扫码关注
					if (!StringUtils.isEmpty(subscribeEvent.getEventKey())) {
						qrscene = subscribeEvent.getEventKey();
						SysWxUserQrcode sysWxUserQrcode = new SysWxUserQrcode();
						sysWxUserQrcode.setWxuserid(sysWxUser.getId());
						sysWxUserQrcode.setQrscene(qrscene);
						SysWxUserQrcode tsysWxUserQrcode = sysWxUserQrcodeService.findObject(sysWxUserQrcode);

						if (tsysWxUserQrcode == null) {
							sysWxUserQrcodeService.save(sysWxUserQrcode);
						} else {
							dozerBeanMapper.map(sysWxUserQrcode, tsysWxUserQrcode);
							sysWxUserQrcodeService.update(tsysWxUserQrcode);
						}
					}
					if (!StringUtils.isEmpty(sysWx.getWxmsgid())) {
						return getWxMsgByid(sysWx.getWxmsgid(), baseEvent.fromUserName, baseEvent.toUserName);
					}

				}
				// 取消关注
				else if (event.equalsIgnoreCase("unsubscribe")) {
					UserInfoRequest userInfoRequest = new UserInfoRequest();
					userInfoRequest.setAccess_token(sysWxAccesstoken.getAccesstoken());
					userInfoRequest.setOpenid(baseEvent.getFromUserName());
					SysWxUser sysWxUser = new SysWxUser();
					sysWxUser.setOpenid(baseEvent.getFromUserName());
					sysWxUser = sysWxUserService.findObject(sysWxUser);
					if (sysWxUser != null) {
						sysWxUser.setSubscribe(false);
						sysWxUserService.update(sysWxUser);
					}
				}
				// 点击菜单拉取消息时的事件推送
				else if (event.equalsIgnoreCase("click")) {
					ClickEvent clickEvent = EventXmlUtil.parseXml(xml, ClickEvent.class);

					SysWxMenu sysWxMenu = new SysWxMenu();
					sysWxMenu.setId(clickEvent.getEventKey());
					sysWxMenu = sysWxMenuService.findObject(sysWxMenu);
					if (sysWxMenu != null && !StringUtils.isEmpty(sysWxMenu.getWxmsgid())) {
						return getWxMsgByid(sysWxMenu.getWxmsgid(), baseEvent.fromUserName, baseEvent.toUserName);
					}
				}
				// 点击菜单跳转链接时的事件推送
				else if (event.equalsIgnoreCase("view")) {

				}

			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "/{code}/deal", method = RequestMethod.GET)
	@ResponseBody
	public String dealget(HttpServletRequest request, @PathVariable("code") String code, String signature, String timestamp, String nonce, String echostr) {
		SysWx sysWx = new SysWx();
		sysWx.setCode(code);
		try {
			sysWx = sysWxService.findObject(sysWx);
		} catch (Exception e) {
			logger.error(e);
		}

		if (SignUtil.checkSignature(sysWx.getToken(), signature, timestamp, nonce))
			return echostr;
		else
			return null;
	}

	@RequestMapping(value = "/{code}/jsapiconfig")
	@ResponseBody
	public Object jsapiconfig(HttpServletRequest request, @PathVariable("code") String code, String jsapistr, String url) {
		SysWx sysWx = new SysWx();
		sysWx.setCode(code);
		try {
			sysWx = sysWxService.findObject(sysWx);

			SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
			sysWxAccesstoken.setWxid(sysWx.getId());
			sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
			String[] jsapi = jsapistr.split(",");
			WxJsConfig wxJsConfig = new WxJsConfig();
			wxJsConfig.setDebug(false);
			wxJsConfig.setAppId(sysWx.getAppid());
			WxJsApiSign.sign(WxUtil.getjsapiticket(sysWxAccesstoken.getAccesstoken()).getTicket(), url, wxJsConfig);
			wxJsConfig.setJsApiList(Arrays.asList(jsapi));
			return wxJsConfig;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	private String getWxMsgByid(String id, String toUserName, String fromUserName) throws Exception {
		SysWxMsg sysWxMsg = new SysWxMsg();
		sysWxMsg.setId(id);
		sysWxMsg = sysWxMsgService.findObject(sysWxMsg);
		Gson gson = new Gson();
		if ("1".equals(sysWxMsg.getType())) {
			ResponseTextMsg msg = gson.fromJson(sysWxMsg.getContent(), ResponseTextMsg.class);
			msg.setFromUserName(fromUserName);
			msg.setToUserName(toUserName);
			msg.setCreateTime(System.currentTimeMillis());
			return MsgXmlUtil.toXml(msg);
		} else if ("2".equals(sysWxMsg.getType())) {
			ResponseArticleMsg msg = gson.fromJson(sysWxMsg.getContent(), ResponseArticleMsg.class);
			for (Article article : msg.getArticles()) {
				article.setPicUrl(imgUrl + article.getPicUrl());
			}
			msg.setFromUserName(fromUserName);
			msg.setToUserName(toUserName);
			msg.setCreateTime(System.currentTimeMillis());
			return MsgXmlUtil.toXml(msg);
		}
		return null;
	}
}
