package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>Sdrug<br>
 */
@com.commons.annotation.Relation("t_sdrug")
public class Sdrug implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="name")
	private  java.lang.String   name; //name   
	
	
    @com.commons.annotation.Column(value="factory")
	private  java.lang.String   factory; //factory   
	
	
    @com.commons.annotation.Column(value="comnameid")
	private  java.lang.String   comnameid; //comnameid   
	
	
    @com.commons.annotation.Column(value="xse")
	private  java.math.BigDecimal   xse; //xse   
	
	
    @com.commons.annotation.Column(value="disable")
	private  java.lang.Boolean   disable; //disable   
	
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
	 * @return the name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * @return the factory
	 */
	public java.lang.String getFactory() {
		return factory;
	}

	/**
	 * @param factory the factory to set
	 */
	public void setFactory(java.lang.String factory) {
		this.factory = factory;
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
	 * @return the disable
	 */
	public java.lang.Boolean getDisable() {
		return disable;
	}

	/**
	 * @param disable the disable to set
	 */
	public void setDisable(java.lang.Boolean disable) {
		this.disable = disable;
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

