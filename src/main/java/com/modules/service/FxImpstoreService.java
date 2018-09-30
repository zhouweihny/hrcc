package com.modules.service;

import com.modules.pojo.FxImpstore;

import java.util.List;

import com.commons.base.BaseService;
/**
 * 
 * @author Du.Jun
 */
public interface FxImpstoreService extends BaseService<FxImpstore> {

	void upload(String id, String userid, List<FxImpstore> fxImpstores);

}
