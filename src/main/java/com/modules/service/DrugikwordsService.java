package com.modules.service;

import com.modules.pojo.Drugikwords;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface DrugikwordsService extends BaseService<Drugikwords> {

	public void batchInsertFromDrug() throws Exception;

	public void updateDelwords() throws Exception;
}
