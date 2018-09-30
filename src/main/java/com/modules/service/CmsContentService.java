package com.modules.service;

import java.util.Map;

import com.modules.pojo.CmsContent;
import com.commons.base.BaseService;
import com.commons.sql.Page;
import com.commons.sql.PageResult;

/**
 * 
 * @author Du.Jun
 */
public interface CmsContentService extends BaseService<CmsContent> {

	public PageResult<CmsContent> findListA(Map<String, Object> params, Page page) throws Exception;

}
