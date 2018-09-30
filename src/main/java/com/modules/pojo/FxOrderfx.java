package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>FxOrderfx<br>
 */
@com.commons.annotation.Relation("fx_orderfx")
public class FxOrderfx implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="orderno")
	private  java.lang.String   orderno; //orderno   
	
	
    @com.commons.annotation.Column(value="xse")
	private  java.math.BigDecimal   xse; //xse   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="rq")
	private  java.util.Date   rq; //rq   
	
	
    @com.commons.annotation.Column(value="schemeid")
	private  java.lang.String   schemeid; //schemeid   
	
	
    @com.commons.annotation.Column(value="treeid")
	private  java.lang.String   treeid; //treeid   
	
	
    @com.commons.annotation.Column(value="storeid")
	private  java.lang.String   storeid; //storeid   
	
	
    @com.commons.annotation.Column(value="userid")
	private  java.lang.String   userid; //userid   
	
	
    @com.commons.annotation.Column(value="disabled")
	private  java.lang.Boolean   disabled; //disabled   
	
	
    @com.commons.annotation.Column(value="operatorid")
	private  java.lang.String   operatorid; //operatorid   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="createtime")
	private  java.util.Date   createtime; //createtime   
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @com.commons.annotation.Column(value="updatetime")
	private  java.util.Date   updatetime; //updatetime   
	
	
    @com.commons.annotation.Column(value="autosendbill")
	private  java.lang.Boolean   autosendbill; //autosendbill   

	/**
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}
	/**
	 * @return the orderno
	 */
	public java.lang.String getOrderno() {
		return orderno;
	}

	/**
	 * @param orderno the orderno to set
	 */
	public void setOrderno(java.lang.String orderno) {
		this.orderno = orderno;
	}
	/**
	 * @return the xse
	 */
	public java.math.BigDecimal getXse() {
		return xse;
	}

	/**
	 * @param xse the xse to set
	 */
	public void setXse(java.math.BigDecimal xse) {
		this.xse = xse;
	}
	/**
	 * @return the rq
	 */
	public java.util.Date getRq() {
		return rq;
	}

	/**
	 * @param rq the rq to set
	 */
	public void setRq(java.util.Date rq) {
		this.rq = rq;
	}
	/**
	 * @return the schemeid
	 */
	public java.lang.String getSchemeid() {
		return schemeid;
	}

	/**
	 * @param schemeid the schemeid to set
	 */
	public void setSchemeid(java.lang.String schemeid) {
		this.schemeid = schemeid;
	}
	/**
	 * @return the treeid
	 */
	public java.lang.String getTreeid() {
		return treeid;
	}

	/**
	 * @param treeid the treeid to set
	 */
	public void setTreeid(java.lang.String treeid) {
		this.treeid = treeid;
	}
	/**
	 * @return the storeid
	 */
	public java.lang.String getStoreid() {
		return storeid;
	}

	/**
	 * @param storeid the storeid to set
	 */
	public void setStoreid(java.lang.String storeid) {
		this.storeid = storeid;
	}
	/**
	 * @return the userid
	 */
	public java.lang.String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}
	/**
	 * @return the disabled
	 */
	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
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
	 * @param operatorid the operatorid to set
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
	 * @param createtime the createtime to set
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
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * @return the autosendbill
	 */
	public java.lang.Boolean getAutosendbill() {
		return autosendbill;
	}

	/**
	 * @param autosendbill the autosendbill to set
	 */
	public void setAutosendbill(java.lang.Boolean autosendbill) {
		this.autosendbill = autosendbill;
	}



}

