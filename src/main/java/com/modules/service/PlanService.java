package com.modules.service;

import com.modules.pojo.Plan;
import com.modules.pojo.PlanDetail;
import java.util.List;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface PlanService extends BaseService<Plan> {

	public void upload(String name, String storecode, String userid, List<PlanDetail> list) throws Exception;

	public void create(String planid) throws Exception;

	public void upload(Plan plan, List<PlanDetail> plandetails) throws Exception;
}
