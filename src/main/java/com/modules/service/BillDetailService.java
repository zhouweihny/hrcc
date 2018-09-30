package com.modules.service;

import com.modules.pojo.BillDetail;
import com.modules.pojo.User;
import com.modules.vo.apipur.BillDetailStatusVo;

import java.util.Date;
import java.util.List;

import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface BillDetailService extends BaseService<BillDetail> {

	public void updateDrugId(String userid, String billid) throws Exception;

	public User lastDrugCompany(String purchaserid, String drugid) throws Exception;

	public List<BillDetailStatusVo> querybilldetailstatus(String purchaserid, Date updatetime) throws Exception;

	public void updateContractStatus(String erpid, String code, String contractno, String contractnum, Date contracttime) throws Exception;

}
