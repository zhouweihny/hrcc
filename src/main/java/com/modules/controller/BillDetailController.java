package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.UUIDGenerator;
import com.modules.service.BillDetailService;
import com.modules.service.BillService;
import com.modules.vo.BillDetailVo;
import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("billdetail")
public class BillDetailController extends BaseController {

	private static Logger logger = Logger.getLogger(BillDetailController.class);

	@Autowired
	private BillDetailService billDetailService;

	@Autowired
	private BillService billService;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@RequestMapping("save")
	@ResponseBody
	public Object save(BillDetail billDetail, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(billDetail.getId())) {
				billDetailService.save(billDetail);
			} else {
				billDetailService.update(billDetail);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("update")
	@ResponseBody
	public Object updatestatus(BillDetail billDetail, HttpServletRequest request, HttpServletResponse response) {
		try {
			billDetailService.update(billDetail);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(BillDetail billDetail) {
		try {
			billDetailService.delete(billDetail);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(BillDetail billDetail) {
		ModelAndView modelAndView = new ModelAndView("billdetail/info");
		try {
			if (!StringUtils.isEmpty(billDetail.getId())) {
				BillDetail data = billDetailService.findObject(billDetail);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(BillDetail billDetail) {
		try {
			BillDetail data = billDetailService.findObject(billDetail);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String billid) {
		ModelAndView modelAndView = new ModelAndView("billdetail/list");
		modelAndView.addObject("billid", billid);
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String billid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("billdetail/table");
		User company = (User) session.getAttribute(Constants.USER);
		User user = (User) session.getAttribute(Constants.USER);

		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			params.put("billid", billid);
			PageResult<BillDetail> data = billDetailService.findList(params, page);
			modelAndView.addObject("page", data);
			// if ("B1CC34941707470D9BF361D1CEF2B08E".equals(company.getRoleid())) {

			if (company.getRoleids().indexOf("B1CC34941707470D9BF361D1CEF2B08E") > -1 || user.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1) {
				Bill tbill = new Bill();
				tbill.setId(billid);
				tbill = billService.findObject(tbill);
				if (tbill.getStatus() < 1) {
					Bill bill = new Bill();
					bill.setId(billid);
					bill.setStatus(1);
					billService.update(bill);
				}
			}

		} catch (

		Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping("savebill")
	@ResponseBody
	public Object savebill(@RequestBody List<BillDetailVo> billDetailVos) {
		try {
			User user = (User) session.getAttribute(Constants.USER);
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}

			List<BillDetail> billDetails = new ArrayList<BillDetail>();
			List<Bill> bills = new ArrayList<Bill>();
			Date now = new Date();

			loop: for (BillDetailVo billDetailVo : billDetailVos) {
				BillDetail billDetail = dozerBeanMapper.map(billDetailVo, BillDetail.class);
				billDetail.setPurchaserid(userid);
				billDetails.add(billDetail);
				for (Bill bill : bills) {
					if (bill.getSupplierid().equals(billDetailVo.getSupplierid()) && bill.getStorecode().equals(billDetailVo.getStorecode())) {
						billDetail.setBillid(bill.getId());
						continue loop;
					}
				}
				Bill bill = new Bill();
				bill.setId(UUIDGenerator.getUUID());
				DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
				String date = format1.format(new Date());
				bill.setName(date + billDetailVo.getStorename());
				bill.setStorecode(billDetailVo.getStorecode());
				bill.setCode(String.valueOf(System.currentTimeMillis()));
				bill.setPurchaserid(userid);
				bill.setSupplierid(billDetailVo.getSupplierid());
				bill.setCreatetime(now);
				billDetail.setBillid(bill.getId());
				bill.setStatus(0);
				bills.add(bill);
			}
			for (Bill bill : bills) {
				bill.setNum(0);
				for (BillDetail billDetail : billDetails) {
					if (billDetail.getBillid().equals(bill.getId())) {
						bill.setNum(bill.getNum() + 1);
					}
				}
			}

			billService.save(bills);
			billDetailService.save(billDetails);
			for (Bill bill : bills) {
				billDetailService.updateDrugId(userid, bill.getId());
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

}