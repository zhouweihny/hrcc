package com.modules.service;

import com.modules.pojo.SupplierPurchaseDetail;
import com.modules.vo.SupplierPurchaseDetailPirceVo;

import java.util.List;

import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface SupplierPurchaseDetailService extends BaseService<SupplierPurchaseDetail> {

	public List<SupplierPurchaseDetailPirceVo> findprice(String puchasedetailid) throws Exception;

}
