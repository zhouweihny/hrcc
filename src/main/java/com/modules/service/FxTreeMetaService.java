package com.modules.service;

import com.modules.pojo.FxTreeMeta;
import com.modules.vo.TreeMetaVo;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface FxTreeMetaService extends BaseService<FxTreeMeta> {

	public TreeMetaVo findByTreepathNCode(String treeId, String code, String userid, String storeid, String storetypeid) throws Exception;

	public TreeMetaVo findByTreepath(String treeid, String metaid, String userid, String storeid, String storetypeid) throws Exception;

	public void refreshCache(String companyid) throws Exception;

}
