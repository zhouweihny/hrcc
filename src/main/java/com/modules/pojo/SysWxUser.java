package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>SysWxUser<br>
 */
@com.commons.annotation.Relation("sys_wx_user")
public class SysWxUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "wxid")
	private java.lang.String wxid; // wxid

	@com.commons.annotation.Column(value = "subscribe")
	private java.lang.Boolean subscribe; // subscribe

	@com.commons.annotation.Column(value = "openid")
	private java.lang.String openid; // openid

	@com.commons.annotation.Column(value = "nickname")
	private java.lang.String nickname; // nickname

	@com.commons.annotation.Column(value = "sex")
	private java.lang.String sex; // sex

	@com.commons.annotation.Column(value = "city")
	private java.lang.String city; // city

	@com.commons.annotation.Column(value = "country")
	private java.lang.String country; // country

	@com.commons.annotation.Column(value = "province")
	private java.lang.String province; // province

	@com.commons.annotation.Column(value = "language")
	private java.lang.String language; // language

	@com.commons.annotation.Column(value = "headimgurl")
	private java.lang.String headimgurl; // headimgurl

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "subscribe_time")
	private java.util.Date subscribeTime; // subscribe_time

	@com.commons.annotation.Column(value = "unionid")
	private java.lang.String unionid; // unionid

	@com.commons.annotation.Column(value = "groupid")
	private java.lang.String groupid; // groupid

	@com.commons.annotation.Column(value = "tagid_list")
	private java.lang.String tagidList; // tagid_list

	@com.commons.annotation.Column(value = "mobileno")
	private java.lang.String mobileno; // mobileno

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@com.commons.annotation.Column(value = "remark")
	private java.lang.String remark; // remark

	@com.commons.annotation.Column(value = "disabled")
	private java.lang.Boolean disabled; // disabled

	@com.commons.annotation.Column(value = "operatorid")
	private java.lang.String operatorid; // operatorid

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "createtime")
	private java.util.Date createtime; // createtime

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "updatetime")
	private java.util.Date updatetime; // updatetime

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * @return the wxid
	 */
	public java.lang.String getWxid() {
		return wxid;
	}

	/**
	 * @param wxid
	 *            the wxid to set
	 */
	public void setWxid(java.lang.String wxid) {
		this.wxid = wxid;
	}

	/**
	 * @return the subscribe
	 */
	public java.lang.Boolean getSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe
	 *            the subscribe to set
	 */
	public void setSubscribe(java.lang.Boolean subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * @return the openid
	 */
	public java.lang.String getOpenid() {
		return openid;
	}

	/**
	 * @param openid
	 *            the openid to set
	 */
	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}

	/**
	 * @return the nickname
	 */
	public java.lang.String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the sex
	 */
	public java.lang.String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	/**
	 * @return the city
	 */
	public java.lang.String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public java.lang.String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	/**
	 * @return the province
	 */
	public java.lang.String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	/**
	 * @return the language
	 */
	public java.lang.String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}

	/**
	 * @return the headimgurl
	 */
	public java.lang.String getHeadimgurl() {
		return headimgurl;
	}

	/**
	 * @param headimgurl
	 *            the headimgurl to set
	 */
	public void setHeadimgurl(java.lang.String headimgurl) {
		this.headimgurl = headimgurl;
	}

	/**
	 * @return the subscribeTime
	 */
	public java.util.Date getSubscribeTime() {
		return subscribeTime;
	}

	/**
	 * @param subscribeTime
	 *            the subscribeTime to set
	 */
	public void setSubscribeTime(java.util.Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	/**
	 * @return the unionid
	 */
	public java.lang.String getUnionid() {
		return unionid;
	}

	/**
	 * @param unionid
	 *            the unionid to set
	 */
	public void setUnionid(java.lang.String unionid) {
		this.unionid = unionid;
	}

	/**
	 * @return the groupid
	 */
	public java.lang.String getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(java.lang.String groupid) {
		this.groupid = groupid;
	}

	/**
	 * @return the tagidList
	 */
	public java.lang.String getTagidList() {
		return tagidList;
	}

	/**
	 * @param tagidList
	 *            the tagidList to set
	 */
	public void setTagidList(java.lang.String tagidList) {
		this.tagidList = tagidList;
	}

	public java.lang.String getMobileno() {
		return mobileno;
	}

	public void setMobileno(java.lang.String mobileno) {
		this.mobileno = mobileno;
	}

	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	/**
	 * @return the remark
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * @return the disabled
	 */
	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the operatorid
	 */
	public java.lang.String getOperatorid() {
		return operatorid;
	}

	/**
	 * @param operatorid
	 *            the operatorid to set
	 */
	public void setOperatorid(java.lang.String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * @return the createtime
	 */
	public java.util.Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the updatetime
	 */
	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime
	 *            the updatetime to set
	 */
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

}
