package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>FxComnameZb<br>
 */
@com.commons.annotation.Relation("fx_comname_zb")
public class FxComnameZb implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="comnameid")
	private  java.lang.String   comnameid; //comnameid   
	
	
    @com.commons.annotation.Column(value="storetypeid")
	private  java.lang.String   storetypeid; //storetypeid   
	
	
    @com.commons.annotation.Column(value="zb")
	private  java.math.BigDecimal   zb; //zb   
	
	
    @com.commons.annotation.Column(value="ppsl")
	private  java.math.BigDecimal   ppsl; //ppsl   
	
	
    @com.commons.annotation.Column(value="pzsl")
	private  java.math.BigDecimal   pzsl; //pzsl   
	
	
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
	 * @return the comnameid
	 */
	public java.lang.String getComnameid() {
		return comnameid;
	}

	/**
	 * @param comnameid the comnameid to set
	 */
	public void setComnameid(java.lang.String comnameid) {
		this.comnameid = comnameid;
	}
	/**
	 * @return the storetypeid
	 */
	public java.lang.String getStoretypeid() {
		return storetypeid;
	}

	/**
	 * @param storetypeid the storetypeid to set
	 */
	public void setStoretypeid(java.lang.String storetypeid) {
		this.storetypeid = storetypeid;
	}
	/**
	 * @return the zb
	 */
	public java.math.BigDecimal getZb() {
		return zb;
	}

	/**
	 * @param zb the zb to set
	 */
	public void setZb(java.math.BigDecimal zb) {
		this.zb = zb;
	}
	/**
	 * @return the ppsl
	 */
	public java.math.BigDecimal getPpsl() {
		return ppsl;
	}

	/**
	 * @param ppsl the ppsl to set
	 */
	public void setPpsl(java.math.BigDecimal ppsl) {
		this.ppsl = ppsl;
	}
	/**
	 * @return the pzsl
	 */
	public java.math.BigDecimal getPzsl() {
		return pzsl;
	}

	/**
	 * @param pzsl the pzsl to set
	 */
	public void setPzsl(java.math.BigDecimal pzsl) {
		this.pzsl = pzsl;
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

