package com.modules.service;

import com.modules.pojo.FxTree;
import com.modules.vo.DrugVo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface FxTreeService extends BaseService<FxTree> {

	public List<FxTree> findByPath(String path) throws Exception;

	public List<FxTree> customtree(String storeid) throws Exception;

	public List<DrugVo> customgydrugs(String storeid, String userid, String treeid, String comnameid, String name, String stype, String sfield) throws Exception;

	public Map customdrug(String userid, String comnameid, Date startdate, Date enddate, String catalogid, String treeid, String storeid, String name, Integer status, Integer currentPage,
			Integer pageSize, String stype, String sfield) throws Exception;

	public void updateName(String id, String name) throws Exception;

	public List<FxTree> findAnalysisTree(String userid) throws Exception;

	public FxTree findAnalysisRootTree(String userid) throws Exception;

}
