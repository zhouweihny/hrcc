package com.modules.service;

import java.util.List;

import com.modules.pojo.Keywords;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface KeywordsService extends BaseService<Keywords> {

	public void upload(String userid, List<String> list) throws Exception;

	public void removeRepeat() throws Exception;

	public void refreshIK() throws Exception;
}
