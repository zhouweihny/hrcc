package com.modules.service;

import com.modules.pojo.FxComname;
import com.modules.pojo.FxDrugReview;
import com.modules.vo.drugFX.FxDrugReviewVo;
import com.commons.base.BaseService;
import com.commons.sql.PageResult;
/**
 * 
 * @author Du.Jun
 */
public interface FxDrugReviewService extends BaseService<FxDrugReview> {
	
	public PageResult<FxDrugReviewVo> findByDrugReview(String company, String drugname, Integer status, Integer currentPage, Integer pageSize) throws Exception;


}
