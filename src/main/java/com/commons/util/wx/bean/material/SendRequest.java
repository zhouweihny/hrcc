package com.commons.util.wx.bean.material;

public class SendRequest {

	public Filter filter;

	public Mpnews mpnews;

	public String msgtype;
	
	
	
	
	
	
	

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public Mpnews getMpnews() {
		return mpnews;
	}

	public void setMpnews(Mpnews mpnews) {
		this.mpnews = mpnews;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public class Mpnews {

		public String media_id;

		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}

	}

	public class Filter {

		public Boolean is_to_all;

		public Integer groupid;

		public Boolean getIs_to_all() {
			return is_to_all;
		}

		public void setIs_to_all(Boolean is_to_all) {
			this.is_to_all = is_to_all;
		}

		public Integer getGroupid() {
			return groupid;
		}

		public void setGroupid(Integer groupid) {
			this.groupid = groupid;
		}

	}
}
