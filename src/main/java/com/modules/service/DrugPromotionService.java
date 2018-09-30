package com.modules.service;

import com.modules.pojo.DrugPromotion;
import com.modules.vo.DrugPromotionExcelVo;
import com.modules.vo.UserVo;

import java.util.List;

import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface DrugPromotionService extends BaseService<DrugPromotion> {

	public void upload(List<DrugPromotionExcelVo> DrugPromotionExcelVos, String userid) throws Exception;

	public List<UserVo> selected(String supplierid, String promotionid) throws Exception;

}
