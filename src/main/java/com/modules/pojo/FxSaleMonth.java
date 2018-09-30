package com.modules.pojo;

import java.io.Serializable;

/**
 * 
 * @author Du.Jun <b>功能：</b>FxSaleMonth<br>
 */
@com.commons.annotation.Relation("fx_sale_month")
public class FxSaleMonth implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.commons.annotation.Column(value = "id", isId = true, generateId = true)
	private java.lang.String id; // id

	@com.commons.annotation.Column(value = "drugid")
	private java.lang.String drugid; // drugid

	@com.commons.annotation.Column(value = "storeid")
	private java.lang.String storeid; // storeid

	@com.commons.annotation.Column(value = "xse")
	private java.math.BigDecimal xse; // xse

	@com.commons.annotation.Column(value = "cb")
	private java.math.BigDecimal cb; // cb

	@com.commons.annotation.Column(value = "ml")
	private java.math.BigDecimal ml; // ml

	@com.commons.annotation.Column(value = "mlv")
	private java.math.BigDecimal mlv; // mlv

	@com.commons.annotation.Column(value = "userid")
	private java.lang.String userid; // userid

	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@com.commons.annotation.Column(value = "month")
	private java.util.Date month; // month

	@com.commons.annotation.Column(value = "pc")
	private java.lang.Integer pc; // pc

	@com.commons.annotation.Column(value = "xssl")
	private java.math.BigDecimal xssl; // xssl

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
	 * @return the drugid
	 */
	public java.lang.String getDrugid() {
		return drugid;
	}

	/**
	 * @param drugid
	 *            the drugid to set
	 */
	public void setDrugid(java.lang.String drugid) {
		this.drugid = drugid;
	}

	/**
	 * @return the storeid
	 */
	public java.lang.String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid
	 *            the storeid to set
	 */
	public void setStoreid(java.lang.String storeid) {
		this.storeid = storeid;
	}

	/**
	 * @return the xse
	 */
	public java.math.BigDecimal getXse() {
		return xse;
	}

	/**
	 * @param xse
	 *            the xse to set
	 */
	public void setXse(java.math.BigDecimal xse) {
		this.xse = xse;
	}

	/**
	 * @return the cb
	 */
	public java.math.BigDecimal getCb() {
		return cb;
	}

	/**
	 * @param cb
	 *            the cb to set
	 */
	public void setCb(java.math.BigDecimal cb) {
		this.cb = cb;
	}

	/**
	 * @return the ml
	 */
	public java.math.BigDecimal getMl() {
		return ml;
	}

	/**
	 * @param ml
	 *            the ml to set
	 */
	public void setMl(java.math.BigDecimal ml) {
		this.ml = ml;
	}

	/**
	 * @return the mlv
	 */
	public java.math.BigDecimal getMlv() {
		return mlv;
	}

	/**
	 * @param mlv
	 *            the mlv to set
	 */
	public void setMlv(java.math.BigDecimal mlv) {
		this.mlv = mlv;
	}

	/**
	 * @return the userid
	 */
	public java.lang.String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	/**
	 * @return the month
	 */
	public java.util.Date getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(java.util.Date month) {
		this.month = month;
	}

	/**
	 * @return the pc
	 */
	public java.lang.Integer getPc() {
		return pc;
	}

	/**
	 * @param pc
	 *            the pc to set
	 */
	public void setPc(java.lang.Integer pc) {
		this.pc = pc;
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

	public java.math.BigDecimal getXssl() {
		return xssl;
	}

	public void setXssl(java.math.BigDecimal xssl) {
		this.xssl = xssl;
	}

}
