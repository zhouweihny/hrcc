package com.modules.service;

import java.util.List;

import com.modules.pojo.Factory;
import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface FactoryService extends BaseService<Factory> {

	public void upload(String userid, List<String> list) throws Exception;

	public void removeRepeat() throws Exception;

	public PageResult<Factory> findFactoryCode(Page page) throws Exception;

	public PageResult<Factory> findNIFactory(Page page) throws Exception;

	public void merge(String userid, List<String> ids) throws Exception;

}
