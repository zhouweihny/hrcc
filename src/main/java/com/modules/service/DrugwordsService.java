package com.modules.service;

import java.util.List;

import com.modules.pojo.Drugwords;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface DrugwordsService extends BaseService<Drugwords> {

	public void upload(String userid, List<String> list) throws Exception;

	public void removeRepeat() throws Exception;

}
