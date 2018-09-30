/**
 * @Title: sdfadf.java
 * @Package com.util.weixin
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年9月30日 下午4:08:26
 * @version V1.0
 */

package com.commons.util.wx;

import com.commons.util.wx.bean.msg.ResponseArticleMsg;
import com.commons.util.wx.bean.msg.ResponseArticleMsg.Article;
import com.commons.util.wx.bean.msg.ResponseTextMsg;

/**
 * @author Du.Jun
 *
 */
public class MsgXmlUtil {

	/**
	 * 图文消息转xml
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static String toXml(ResponseArticleMsg msg) throws Exception {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		xml.append("<ToUserName><![CDATA[").append(msg.getToUserName()).append("]]></ToUserName>");
		xml.append("<FromUserName><![CDATA[").append(msg.getFromUserName()).append("]]></FromUserName>");
		xml.append("<CreateTime>").append(msg.getCreateTime()).append("</CreateTime>");
		xml.append("<MsgType><![CDATA[").append(msg.getMsgType()).append("]]></MsgType>");
		xml.append("<ArticleCount>").append(msg.getArticleCount()).append("</ArticleCount>");
		xml.append("<Articles>");
		for (Article article : msg.getArticles()) {
			xml.append("<item>");
			xml.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>");
			xml.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>");
			xml.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>");
			xml.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>");
			xml.append("</item>");
		}
		xml.append("</Articles>");
		xml.append("</xml>");
		return xml.toString();
	}

	/**
	 * 图文消息转xml
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static String toXml(ResponseTextMsg msg) throws Exception {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		xml.append("<ToUserName><![CDATA[").append(msg.getToUserName()).append("]]></ToUserName>");
		xml.append("<FromUserName><![CDATA[").append(msg.getFromUserName()).append("]]></FromUserName>");
		xml.append("<CreateTime>").append(msg.getCreateTime()).append("</CreateTime>");
		xml.append("<MsgType><![CDATA[").append(msg.getMsgType()).append("]]></MsgType>");
		xml.append("<Content><![CDATA[").append(msg.getContent()).append("]]></Content>");
		xml.append("</xml>");
		return xml.toString();
	}
}