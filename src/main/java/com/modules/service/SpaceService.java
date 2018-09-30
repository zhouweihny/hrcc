package com.modules.service;

import java.util.List;
import java.util.Map;

import com.modules.pojo.Drug;
import com.modules.pojo.Sdrug;
import com.modules.pojo.Space;
import com.modules.vo.DrugDrugVo;
import com.modules.vo.DrugVo;
import com.modules.vo.IerData;
import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface SpaceService extends BaseService<Space> {

	public PageResult<DrugVo> findDrugs(Map<String, Object> params, Page page) throws Exception;

	public PageResult<Drug> findcomparedrugs(Map<String, Object> params, Page page) throws Exception;
	
	public  Sdrug  findcompareSdrugs(Drug drug) throws Exception;

	public Drug findcompared(String drugid, String spaceid) throws Exception;

	public PageResult<DrugDrugVo> findcatalogdrugs(Map<String, Object> params, Page page) throws Exception;

	public List<IerData> findcheckedDrug(String spaceid, String catalogid, List<String> codes) throws Exception;
}
