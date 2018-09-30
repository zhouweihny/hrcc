package com.modules.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import com.modules.service.FxSaleMonthService;
import com.modules.dao.FxSaleMonthDao;
import com.modules.pojo.FxSaleData;
import com.modules.pojo.FxSaleMonth;

/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FxSaleMonthServiceImpl implements FxSaleMonthService {

	@Autowired
	private FxSaleMonthDao fxSaleMonthDao;

	public void save(FxSaleMonth entity) throws Exception {
		fxSaleMonthDao.save(entity);
	}

	public void save(List<FxSaleMonth> entities) throws Exception {
		fxSaleMonthDao.save(entities);
	}

	public void update(FxSaleMonth entity) throws Exception {
		fxSaleMonthDao.update(entity);
	}

	public void update(FxSaleMonth newEntity, FxSaleMonth oldEntity) throws Exception {
		fxSaleMonthDao.update(newEntity, oldEntity);
	}

	public void delete(FxSaleMonth entity) throws Exception {
		fxSaleMonthDao.delete(entity);
	}

	public FxSaleMonth findObject(FxSaleMonth entity) throws Exception {
		return fxSaleMonthDao.findObject(entity);
	}

	public List<FxSaleMonth> findList(FxSaleMonth entity) throws Exception {
		return fxSaleMonthDao.findList(entity);
	}

	public PageResult<FxSaleMonth> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select * from fx_sale_month where 1=1 ");
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxSaleMonthDao.findList(sqlContext, page, FxSaleMonth.class);
	}

	@Override
	public void addSaleData(FxSaleData fxSaleData) throws Exception {
		// TODO Auto-generated method stub
		FxSaleMonth saleMonth = new FxSaleMonth();
		Date month = fxSaleData.getSaledate();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(month);
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		saleMonth.setMonth(cal1.getTime());
		saleMonth.setDrugid(fxSaleData.getDrugid());
		saleMonth.setStoreid(fxSaleData.getStoreid());
		saleMonth = fxSaleMonthDao.findObject(saleMonth);
		if (saleMonth == null) {
			saleMonth = new FxSaleMonth();
			saleMonth.setMonth(cal1.getTime());
			saleMonth.setDrugid(fxSaleData.getDrugid());
			saleMonth.setStoreid(fxSaleData.getStoreid());
			saleMonth.setUserid(fxSaleData.getUserid());
			saleMonth.setCb(fxSaleData.getCostprice());
			saleMonth.setXse(fxSaleData.getPrice());
			saleMonth.setMl(saleMonth.getXse().subtract(saleMonth.getCb()));
			if (saleMonth.getXse().compareTo(new BigDecimal(0)) == 0)
				saleMonth.setMlv(new BigDecimal(0));
			else
				saleMonth.setMlv(saleMonth.getMl().multiply(new BigDecimal(100)).divide(saleMonth.getXse(), 10, RoundingMode.CEILING));
			saleMonth.setPc(1);
			saleMonth.setXssl(fxSaleData.getQty());
			fxSaleMonthDao.save(saleMonth);
		} else {
			saleMonth.setCb(saleMonth.getCb().add(fxSaleData.getCostprice()));
			saleMonth.setXse(saleMonth.getXse().add(fxSaleData.getPrice()));
			saleMonth.setMl(saleMonth.getXse().subtract(saleMonth.getCb()));
			if (saleMonth.getXse().compareTo(new BigDecimal(0)) == 0)
				saleMonth.setMlv(new BigDecimal(0));
			else
				saleMonth.setMlv(saleMonth.getMl().multiply(new BigDecimal(100)).divide(saleMonth.getXse(), 10, RoundingMode.CEILING));
			saleMonth.setPc(saleMonth.getPc() + 1);
			saleMonth.setXssl(saleMonth.getXssl().add(fxSaleData.getQty()));
			fxSaleMonthDao.update(saleMonth);
		}

	}

	@Override
	public List<String> monthsByStoreid(String storeid, String userid) throws Exception {
		// TODO Auto-generated method stub

		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		sql.append("select  date_format(month, '%Y-%m')  from fx_sale_month where month>= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)   ");
		if (StringUtils.isNotBlank(storeid)) {
			sql.append(" and storeid=? ");
			ps.add(storeid);
		}
		if (StringUtils.isNotBlank(userid)) {
			sql.append(" and userid=?  ");
			ps.add(userid);
		}

		sql.append("   group by   month  order by month desc  ");

		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return fxSaleMonthDao.findList(sqlContext, String.class);
	}

}
