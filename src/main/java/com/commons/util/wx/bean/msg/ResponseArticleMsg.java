package com.commons.util.wx.bean.msg;

import java.util.List;

public class ResponseArticleMsg {

	public String toUserName;// 是 接收方帐号（收到的OpenID）
	public String fromUserName;// 是 开发者微信号
	public Long createTime;// 是 消息创建时间 （整型）
	public String msgType = "news";// 是 news
	public Integer articleCount;// 是 图文消息个数，限制为8条以内
	public List<Article> articles;// 是 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public class Article {
		public String title;// 否 图文消息标题
		public String description;// 否 图文消息描述
		public String picUrl;// 否 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
		public String url;// 否 点击图文消息跳转链接

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

}
