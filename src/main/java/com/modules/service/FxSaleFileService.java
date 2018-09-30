package com.modules.service;

import com.modules.pojo.FxSaleData;
import com.modules.pojo.FxSaleFile;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface FxSaleFileService extends BaseService<FxSaleFile> {

	public void addProcessed(FxSaleFile saleFile,FxSaleData saleData) throws Exception;

	public void addError(String fileid) throws Exception;

}
