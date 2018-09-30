package com.commons.util.wx.bean.material;

import java.util.List;

public class ArticleMaterial {

	public List<Article> articles;// 是 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public class Article {

		public String title;// 标题
		public String thumb_media_id;// 图文消息的封面图片素材id（必须是永久mediaID）
		public String author;// 作者
		public String digest;// 否 点击图文消息跳转链接
		public Integer show_cover_pic; // 是否显示封面，0为false，即不显示，1为true，即显示
		public String content;// 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源"上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
		public String content_source_url;// 是 图文消息的原文地址，即点击“阅读原文”后的URL
		public String picurl;//封面地址

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getThumb_media_id() {
			return thumb_media_id;
		}

		public void setThumb_media_id(String thumb_media_id) {
			this.thumb_media_id = thumb_media_id;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDigest() {
			return digest;
		}

		public void setDigest(String digest) {
			this.digest = digest;
		}

		public Integer getShow_cover_pic() {
			return show_cover_pic;
		}

		public void setShow_cover_pic(Integer show_cover_pic) {
			this.show_cover_pic = show_cover_pic;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent_source_url() {
			return content_source_url;
		}

		public void setContent_source_url(String content_source_url) {
			this.content_source_url = content_source_url;
		}

		public String getPicurl() {
			return picurl;
		}

		public void setPicurl(String picurl) {
			this.picurl = picurl;
		}

		 
		
	}

}
