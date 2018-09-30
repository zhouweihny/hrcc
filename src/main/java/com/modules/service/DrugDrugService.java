package com.modules.service;

import java.util.Date;
import java.util.List;

import com.modules.pojo.DrugDrug;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface DrugDrugService extends BaseService<DrugDrug> {

	public List<DrugDrug> findcheckedbycatalogid(String spaceid, String catalogid, Date lastExportTime) throws Exception;

}
