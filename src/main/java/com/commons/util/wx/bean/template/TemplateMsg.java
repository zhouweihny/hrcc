package com.commons.util.wx.bean.template;

import java.util.List;
import java.util.Map;

public class TemplateMsg {
	private String touser;
	private String template_id;
	private String url;
	private String topcolor = "#173177";

	private Map<String, Vdata> data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public Map<String, Vdata> getData() {
		return data;
	}

	public void setData(Map<String, Vdata> data) {
		this.data = data;
	}

	 

}