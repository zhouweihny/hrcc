package com.modules.service;

import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;

import java.util.List;

import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface BillService extends BaseService<Bill> {

	public void sendNotice(String billid) throws Exception;

	public void sendStatus(String billid) throws Exception;

	public void upload(Bill bill, List<BillDetail> billdetails) throws Exception;

	public void updateNum(String billid) throws Exception;

}
