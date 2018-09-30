package com.modules.service;

import com.modules.pojo.FactoryKeyword;
import com.modules.vo.FactoryKeywordVo;
import java.util.Map;
import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface FactoryKeywordService extends BaseService<FactoryKeyword> {

	public PageResult<FactoryKeywordVo> findFactoryKeywordVos(Map<String, Object> params, Page page) throws Exception;

	public void refreshIK() throws Exception;
}
