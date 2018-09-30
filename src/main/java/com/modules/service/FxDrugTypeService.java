package com.modules.service;

import com.modules.vo.FxDrugType;

import java.util.List;
import java.util.Map;

import com.commons.base.BaseService;
/**
 * 
 * @author Du.Jun
 */
public interface FxDrugTypeService extends BaseService<FxDrugType> {

		public List<FxDrugType> findList(Map<String, Object> params) throws Exception;
		
		public List<FxDrugType> findAllList(Map<String, Object> params) throws Exception;
		
		public List<FxDrugType> findStoreList(Map<String, Object> params) throws Exception ;
		
		public List<FxDrugType> findDrugTypeSaleLevel(Map<String, Object> params)throws Exception ;
		
		public List<FxDrugType> findDrugTypeSaleDrugnum(Map<String, Object> params)throws Exception ;
		
		public List<FxDrugType> findDrugTypeSaleml(Map<String, Object> params)throws Exception ;
		
		public List<FxDrugType> findDrugTypecomname(Map<String, Object> params)throws Exception ;
}
