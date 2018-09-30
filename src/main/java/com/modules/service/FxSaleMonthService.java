package com.modules.service;

import com.modules.pojo.FxSaleData;
import com.modules.pojo.FxSaleMonth;

import java.util.List;

import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface FxSaleMonthService extends BaseService<FxSaleMonth> {

	public void addSaleData(FxSaleData fxSaleData) throws Exception;

	public List<String> monthsByStoreid(String storeid, String userid) throws Exception;

}
