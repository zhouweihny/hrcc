package com.modules.service;

import java.util.List;

import com.modules.pojo.User;
import com.modules.pojo.UserSupplier;
import com.commons.base.BaseService;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface UserSupplierService extends BaseService<UserSupplier> {

	public List<User> findPurchaserList(String supplierid, Boolean send) throws Exception;

	public PageResult<User> findSupplierList(String keyword) throws Exception;

	public void addMobileno(String id, String mobileno, String supplierid, String userid) throws Exception;

	public void upload(String userid, List<UserSupplier> userSuppliers) throws Exception;
}
