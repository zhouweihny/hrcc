package com.modules.service;

import java.util.Date;
import java.util.List;

import com.modules.pojo.DrugDrug;
import com.modules.pojo.DrugDrugDel;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface DrugDrugDelService extends BaseService<DrugDrugDel> {

	public List<DrugDrug> findbycatalogid(String spaceid, String catalogid, Date lastExportTime) throws Exception;
}
