package com.modules.service;

import com.modules.pojo.SysWx;
import com.modules.pojo.SysWxAccesstoken;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface SysWxAccesstokenService extends BaseService<SysWxAccesstoken> {

	public void refresh(SysWx entity) throws Exception;

}
