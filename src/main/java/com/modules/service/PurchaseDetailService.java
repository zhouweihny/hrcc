package com.modules.service;

import com.modules.pojo.PurchaseDetail;
import com.modules.vo.PurchaseDetailVo;

import java.util.Map;

import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface PurchaseDetailService extends BaseService<PurchaseDetail> {

	public PageResult<PurchaseDetailVo> findPurchaseDetailVoList(Map<String, Object> params, Page page) throws Exception;

}
