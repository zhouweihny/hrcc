package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>SpaceCatalog<br>
 */
@com.commons.annotation.Relation("t_space_catalog")
public class SpaceCatalog implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="spaceid")
	private  java.lang.String   spaceid; //spaceid   
	
	
    @com.commons.annotation.Column(value="catalogid")
	private  java.lang.String   catalogid; //catalogid   
	
	
    @com.commons.annotation.Column(value="standard")
	private  java.lang.Boolean   standard; //standard   
	
	
    @com.commons.annotation.Column(value="remark")
	private  java.lang.String   remark; //remark   
	
	
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
	 * @return the spaceid
	 */
	public java.lang.String getSpaceid() {
		return spaceid;
	}

	/**
	 * @param spaceid the spaceid to set
	 */
	public void setSpaceid(java.lang.String spaceid) {
		this.spaceid = spaceid;
	}
	/**
	 * @return the catalogid
	 */
	public java.lang.String getCatalogid() {
		return catalogid;
	}

	/**
	 * @param catalogid the catalogid to set
	 */
	public void setCatalogid(java.lang.String catalogid) {
		this.catalogid = catalogid;
	}
	/**
	 * @return the standard
	 */
	public java.lang.Boolean getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(java.lang.Boolean standard) {
		this.standard = standard;
	}
	/**
	 * @return the remark
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
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

