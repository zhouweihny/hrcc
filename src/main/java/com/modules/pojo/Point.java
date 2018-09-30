package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>Point<br>
 */
@com.commons.annotation.Relation("t_point")
public class Point implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="userid")
	private  java.lang.String   userid; //userid   
	
	
    @com.commons.annotation.Column(value="point")
	private  java.lang.Integer   point; //point   
	
	
    @com.commons.annotation.Column(value="operate")
	private  java.lang.String   operate; //operate   
	
	
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
	 * @return the point
	 */
	public java.lang.Integer getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(java.lang.Integer point) {
		this.point = point;
	}
	/**
	 * @return the operate
	 */
	public java.lang.String getOperate() {
		return operate;
	}

	/**
	 * @param operate the operate to set
	 */
	public void setOperate(java.lang.String operate) {
		this.operate = operate;
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



}

