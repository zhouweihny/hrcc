package com.modules.service;

import java.util.List;
import com.modules.pojo.SupplierPurchase;
import com.modules.vo.PurchaseDetailExcelVo;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface SupplierPurchaseService extends BaseService<SupplierPurchase> {

	public List<SupplierPurchase> findList(String purchaserid, String supplierid, Boolean send) throws Exception;

	public Integer findCount(String purchaseid, String supplierid) throws Exception;

	public void upload(String userid, String purchaseid, List<PurchaseDetailExcelVo> planDetails) throws Exception;

}
