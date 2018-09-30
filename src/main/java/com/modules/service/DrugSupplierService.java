package com.modules.service;

import com.modules.pojo.DrugSupplier;
import com.modules.vo.DrugSupplierExcelVo;
import com.modules.vo.DrugSupplierVo;
import java.util.List;
import java.util.Map;
import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface DrugSupplierService extends BaseService<DrugSupplier> {

	public PageResult<DrugSupplierVo> findDrugSupplierVoList(Map<String, Object> params, Page page) throws Exception;

	public void upload(String supplierid, String purchaserid, List<DrugSupplierExcelVo> list) throws Exception;

}
