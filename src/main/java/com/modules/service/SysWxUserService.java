package com.modules.service;

import java.util.Map;

import com.modules.pojo.SysWxUser;
import com.modules.vo.Wx;
import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface SysWxUserService extends BaseService<SysWxUser> {

	public PageResult<Wx> selectWxList(Map<String, Object> params, Page page) throws Exception;

}
