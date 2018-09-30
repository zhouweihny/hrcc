package com.modules.service;

import java.util.List;

import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface CatalogService extends BaseService<Catalog> {

	public void upload(String name, String userid, List<Drug> list) throws Exception;

	public void upload(String catalogid, List<Drug> list) throws Exception;

}
