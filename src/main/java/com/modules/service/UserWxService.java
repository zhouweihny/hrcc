package com.modules.service;

import java.util.Map;

import com.modules.pojo.UserWx;
import com.modules.vo.Wx;
import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface UserWxService extends BaseService<UserWx> {

	public PageResult<Wx> findWxList(Map<String, Object> params, Page page) throws Exception;

}
