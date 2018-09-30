package com.modules.service;

import com.modules.pojo.FxComname;
import com.modules.vo.ComnameVo;

import java.util.List;

import com.commons.base.BaseService;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface FxComnameService extends BaseService<FxComname> {

	public String compareDrug(String name) throws Exception;

	public void bandingAddComname(String drugid, String comnameid) throws Exception;

	public List<FxComname> findBytreeid(String treeid) throws Exception;

	public PageResult<ComnameVo> findByTreePath(String treeid, String rootid, String name, Integer status, Integer currentPage, Integer pageSize) throws Exception;

	public void updateScore() throws Exception;

}
