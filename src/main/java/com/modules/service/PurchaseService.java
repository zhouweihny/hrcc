package com.modules.service;

import java.util.List;

import com.modules.pojo.Purchase;
import com.modules.pojo.PurchaseDetail;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface PurchaseService extends BaseService<Purchase> {

	public void upload(String name, String storecode, String userid, List<PurchaseDetail> list) throws Exception;

	public void send(String purchaseid, String userid, String[] supplerids) throws Exception;

	public void sendNoitce(String purchaseid, String userid, String[] supplierids) throws Exception;

}
