package com.modules.service;

import com.modules.pojo.Autocompare;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface AutocompareService extends BaseService<Autocompare> {

	public void autoCompare(Autocompare autocompare) throws Exception;

}
