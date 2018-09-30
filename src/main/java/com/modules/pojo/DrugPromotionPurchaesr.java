package com.modules.pojo;

import java.io.Serializable;
/**
 * 
 * @author Du.Jun
 * <b>功能：</b>DrugPromotionPurchaesr<br>
 */
@com.commons.annotation.Relation("t_drug_promotion_purchaesr")
public class DrugPromotionPurchaesr implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	
    @com.commons.annotation.Column(value="id",isId=true, generateId = true)
	private  java.lang.String   id; //id   
	
	
    @com.commons.annotation.Column(value="promotionid")
	private  java.lang.String   promotionid; //promotionid   
	
	
    @com.commons.annotation.Column(value="purchaserid")
	private  java.lang.String   purchaserid; //purchaserid   
	
	
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
	 * @return the promotionid
	 */
	public java.lang.String getPromotionid() {
		return promotionid;
	}

	/**
	 * @param promotionid the promotionid to set
	 */
	public void setPromotionid(java.lang.String promotionid) {
		this.promotionid = promotionid;
	}
	/**
	 * @return the purchaserid
	 */
	public java.lang.String getPurchaserid() {
		return purchaserid;
	}

	/**
	 * @param purchaserid the purchaserid to set
	 */
	public void setPurchaserid(java.lang.String purchaserid) {
		this.purchaserid = purchaserid;
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

