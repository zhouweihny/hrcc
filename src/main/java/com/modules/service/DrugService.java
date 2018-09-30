package com.modules.service;

import com.modules.pojo.Drug;

import java.util.List;
import java.util.Map;

import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface DrugService extends BaseService<Drug> {

	public void removeRepeat(String catalogid) throws Exception;

	public void tempTo(String catalogid) throws Exception;

	public PageResult<Drug> findnList(Map<String, Object> params, Page page) throws Exception;

	public List<Drug> findNoComnameList(String userid) throws Exception;

	public void updateGgNPpflag(String catalogid) throws Exception;

}
