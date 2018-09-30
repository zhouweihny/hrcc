package com.commons.util.wx;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import com.commons.util.HttpRequest;
import com.commons.util.wx.bean.material.DelMaterialRequest;
import com.commons.util.wx.bean.material.MaterialResponse;
import com.commons.util.wx.bean.material.SendRequest;
import com.commons.util.wx.bean.material.SendRequest.Filter;
import com.commons.util.wx.bean.material.SendRequest.Mpnews;
import com.commons.util.wx.bean.msg.ResponseArticleMsg;
import com.commons.util.wx.bean.msg.ResponseArticleMsg.Article;
import com.commons.util.wx.bean.msg.ResponseTextMsg;
import com.commons.util.wx.bean.res.AccessTokenRequest;
import com.commons.util.wx.bean.res.AccessTokenResponse;
import com.commons.util.wx.bean.res.BaseResponse;
import com.commons.util.wx.bean.res.JsapiticketResponse;
import com.commons.util.wx.bean.res.OpenIdRequest;
import com.commons.util.wx.bean.res.OpenIdResponse;
import com.commons.util.wx.bean.res.QrcodeRequest;
import com.commons.util.wx.bean.res.QrcodeResponse;
import com.commons.util.wx.bean.res.UserInfoRequest;
import com.commons.util.wx.bean.res.UserInfoResponse;
import com.commons.util.wx.bean.template.TemplateMsg;
import com.commons.util.wx.menu.Menu;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WxUtil {

	/**
	 * 获取accesstoken
	 * 
	 * @param accessTokenRequest
	 * @return
	 */
	public static AccessTokenResponse getAccessToken(AccessTokenRequest accessTokenRequest) {
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", accessTokenRequest.appid);
		params.put("secret", accessTokenRequest.secret);
		params.put("grant_type", accessTokenRequest.grant_type);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		AccessTokenResponse response = gson.fromJson(HttpRequest.sendGet(url, params), AccessTokenResponse.class);
		return response;
	}

	/**
	 * 获取openid
	 * 
	 * @param openIdRequest
	 * @return
	 */
	public static OpenIdResponse getOpenid(OpenIdRequest openIdRequest) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", openIdRequest.appid);
		params.put("secret", openIdRequest.appsecret);
		params.put("code", openIdRequest.code);
		params.put("grant_type", openIdRequest.grant_type);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		OpenIdResponse response = gson.fromJson(HttpRequest.sendGet(url, params), OpenIdResponse.class);
		return response;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userInfoRequest
	 * @return
	 */
	public static UserInfoResponse getUserInfo(UserInfoRequest userInfoRequest) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info";
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", userInfoRequest.access_token);
		params.put("openid", userInfoRequest.openid);
		params.put("lang", userInfoRequest.lang);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		UserInfoResponse response = gson.fromJson(HttpRequest.sendGet(url, params), UserInfoResponse.class);
		return response;
	}

	/**
	 * 上传其他永久素材(图片素材的上限为5000，其他类型为1000) 图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * 
	 * @param accessToken
	 * @param file
	 * @param type
	 * @throws Exception
	 */
	public static MaterialResponse addMaterialEver(String accessToken, InputStream inputsteam, String filename, String type) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		// 上传素材
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + accessToken + "&type=" + type;
		String jsonStr = HttpRequest.postFile(url, inputsteam, filename);
		return gson.fromJson(jsonStr, MaterialResponse.class);
	}

	/**
	 * 上传其他永久素材(图片素材的上限为5000，其他类型为1000) 图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * 
	 * @param accessToken
	 * @param file
	 * @param type
	 * @throws Exception
	 */
	public static MaterialResponse addMaterialEver(String accessToken, File file, String type) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		// 上传素材
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + accessToken + "&type=" + type;
		String jsonStr = HttpRequest.postFile(url, file);
		return gson.fromJson(jsonStr, MaterialResponse.class);
	}

	/**
	 * 上传其他永久素材(图片素材的上限为5000，其他类型为1000) 图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * 
	 * @param accessToken
	 * @param file
	 * @param type
	 * @throws Exception
	 */
	public static MaterialResponse delMaterial(String accessToken, String mediaid) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		DelMaterialRequest delMaterialRequest = new DelMaterialRequest();
		delMaterialRequest.setMedia_id(mediaid);
		// 删除
		String url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=" + accessToken;
		System.out.println(gson.toJson(delMaterialRequest));
		String jsonStr = HttpRequest.postJson(url, gson.toJson(delMaterialRequest));
		return gson.fromJson(jsonStr, MaterialResponse.class);
	}

	/**
	 * 上传图片到微信服务器(本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下)
	 * 
	 * @param accessToken
	 * @param file
	 * @throws Exception
	 */
	public static MaterialResponse addMaterialEver(String accessToken, InputStream inputsteam, String filename) throws Exception {
		// 上传图片素材
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken;
		String jsonStr = HttpRequest.postFile(url, inputsteam, filename);
		return gson.fromJson(jsonStr, MaterialResponse.class);

	}

	/**
	 * 上传图片到微信服务器(本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下)
	 * 
	 * @param accessToken
	 * @param file
	 * @throws Exception
	 */
	public static MaterialResponse addMaterialEver(String accessToken, File file) throws Exception {
		// 上传图片素材
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken;
		String jsonStr = HttpRequest.postFile(url, file);
		return gson.fromJson(jsonStr, MaterialResponse.class);

	}

	/**
	 * 新增永久图文素材
	 * 
	 * @param accessToken
	 * @param file
	 * @throws Exception
	 */
	public static MaterialResponse addNews(String accessToken, String json) throws Exception {
		// 上传图片素材
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + accessToken;
		String jsonStr = HttpRequest.postJson(url, json);
		return gson.fromJson(jsonStr, MaterialResponse.class);

	}

	/**
	 * 群发图文素材
	 * 
	 * @param accessToken
	 * @param file
	 * @throws Exception
	 */
	public static BaseResponse sendNews(String accessToken, String mediaid) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + accessToken;
		SendRequest sendRequest = new SendRequest();
		Filter filter = sendRequest.new Filter();
		Mpnews mpnews = sendRequest.new Mpnews();
		sendRequest.setFilter(filter);
		sendRequest.setMpnews(mpnews);
		filter.setIs_to_all(true);
		mpnews.setMedia_id(mediaid);
		sendRequest.setMsgtype("mpnews");
		String jsonStr = HttpRequest.postJson(url, gson.toJson(sendRequest));
		return gson.fromJson(jsonStr, BaseResponse.class);

	}

	/**
	 * 获取被动回复文本消息
	 * 
	 * @param responseMsg
	 * @return
	 * @throws Exception
	 */
	public static String responseMsg(ResponseTextMsg responseMsg) throws Exception {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		xml.append("<ToUserName><![CDATA[").append(responseMsg.toUserName).append("]]></ToUserName>");
		xml.append("<FromUserName><![CDATA[").append(responseMsg.fromUserName).append("]]></FromUserName>");
		xml.append("<CreateTime>").append(responseMsg.createTime).append("</CreateTime>");
		xml.append("<MsgType>").append(responseMsg.msgType).append("</MsgType>");
		xml.append("<Content>").append(responseMsg.content).append("</Content>");
		xml.append("</xml>");
		return xml.toString();
	}

	/**
	 * 获取被动回复图文的xml
	 * 
	 * @param responseMsg
	 * @return
	 * @throws Exception
	 */
	public static String responseMsg(ResponseArticleMsg responseMsg) throws Exception {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		xml.append("<ToUserName><![CDATA[").append(responseMsg.toUserName).append("]]></ToUserName>");
		xml.append("<FromUserName><![CDATA[").append(responseMsg.fromUserName).append("]]></FromUserName>");
		xml.append("<CreateTime>").append(responseMsg.createTime).append("</CreateTime>");
		xml.append("<MsgType>").append(responseMsg.msgType).append("</MsgType>");
		xml.append("<ArticleCount>").append(responseMsg.articles.size()).append("</ArticleCount>");
		xml.append("<Articles>");
		for (Article article : responseMsg.articles) {
			xml.append("<item>");
			xml.append("<Title><![CDATA[").append(article.title).append("]]></Title>");
			xml.append("<Description><![CDATA[").append(article.description).append("]]></Description>");
			xml.append("<PicUrl><![CDATA[").append(article.picUrl).append("]]></PicUrl>");
			xml.append("<Url><![CDATA[").append(article.url).append("]]></Url>");
			xml.append("</item>");
		}
		xml.append("</Articles>");
		xml.append("</xml>");
		return xml.toString();
	}

	/**
	 * 发送模板消息
	 * 
	 * @param msg
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	public static String sendTemplateMsg(TemplateMsg msg, String accessToken) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
		String jsonStr = HttpRequest.postJson(url, gson.toJson(msg));
		return jsonStr;
	}

	/**
	 * 生成二维码
	 * 
	 * @param responseMsg
	 * @return
	 * @throws Exception
	 */
	public static QrcodeResponse responseQrq(QrcodeRequest qrcodeRequest) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + qrcodeRequest.access_token;
		String json;
		// 永久二维码
		if (qrcodeRequest.getType()) {
			json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": " + qrcodeRequest.scenestr + "}}}";
		} else {
			json = "{\"expire_seconds\": " + qrcodeRequest.getExpireseconds() + ", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + qrcodeRequest.sceneid + "}}}";
		}
		String jsonStr = HttpRequest.postJson(url, json);
		return gson.fromJson(jsonStr, QrcodeResponse.class);
	}

	/**
	 * 获取jsticket
	 * 
	 * @param access_token
	 * @return
	 * @throws Exception
	 */
	public static JsapiticketResponse getjsapiticket(String access_token) throws Exception {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		JsapiticketResponse jsapiticket = null;
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
		Map<String, String> params = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		jsapiticket = gson.fromJson(HttpRequest.sendPost(url, params), JsapiticketResponse.class);

		return jsapiticket;
	}

	/**
	 * 同步公共号菜单
	 * 
	 * @param menu
	 * @param access_token
	 * @returna
	 */
	public static BaseResponse syncMenu(Menu menu, String access_token) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
		String jsonStr = HttpRequest.postJson(url, gson.toJson(menu));
		return gson.fromJson(jsonStr, BaseResponse.class);
	}

	public static void main(String args[]) throws Exception {

	}

}
