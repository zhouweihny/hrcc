package com.modules.service;

import com.modules.pojo.FxOrderfx;
import com.modules.vo.LhyyVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface FxOrderfxService extends BaseService<FxOrderfx> {

	public void fxOrderno(String orderno, String storeid, Date rq, BigDecimal xse, String userid) throws Exception;

	public void refresh(Date rq) throws Exception;

	public void refresh(String orderno, String storeid) throws Exception;

	public List<LhyyVo> lhyyfx(Date startdate, Date enddate, String storeid) throws Exception;
	
	
	public List<LhyyVo> lhyyfx(Date startdate, Date enddate, String storeid,String treeid) throws Exception;
	
	
	public List<LhyyVo> lhyyfx2(Date startdate, Date enddate, String storeid) throws Exception;

}
