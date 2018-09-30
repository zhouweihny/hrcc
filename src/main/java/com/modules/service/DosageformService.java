package com.modules.service;

import java.util.List;

import com.modules.pojo.Dosageform;
import com.commons.base.BaseService;
/**
 * 
 * @author Du.Jun
 */
public interface DosageformService extends BaseService<Dosageform> {
	
	public void upload(List<String> list) throws Exception;
	
	public void removeRepeat() throws Exception;

}
