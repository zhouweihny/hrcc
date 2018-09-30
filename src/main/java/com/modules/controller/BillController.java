package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.AgentService;
import com.modules.service.BillDetailService;
import com.modules.service.BillService;
import com.modules.pojo.Agent;
import com.modules.pojo.Bill;
import com.modules.pojo.BillDetail;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("bill")
public class BillController extends BaseController {

	private static Logger logger = Logger.getLogger(BillController.class);

	@Autowired
	private BillService billService;

	@Autowired
	private AgentService agentService;

	@Autowired
	private BillDetailService billDetailService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Bill bill, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(bill.getId())) {
				billService.save(bill);
			} else {
				if (bill.getStatus() != null) {
					Bill tbill = new Bill();
					tbill.setId(bill.getId());
					tbill = billService.findObject(tbill);
					if (tbill.getStatus() != bill.getStatus()) {
						billService.sendStatus(bill.getId());
					}
				}
				billService.update(bill);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Bill bill) {
		try {
			billService.delete(bill);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Bill bill) {
		ModelAndView modelAndView = new ModelAndView("bill/info");
		try {
			if (!StringUtils.isEmpty(bill.getId())) {
				Bill data = billService.findObject(bill);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Bill bill) {
		try {
			Bill data = billService.findObject(bill);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response, String planid) {
		ModelAndView modelAndView = new ModelAndView("bill/list");
		modelAndView.addObject("planid", planid);
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String planid, String supplier, String agent, String purchaser, Integer status, String stype, String sfield,
			Integer currentPage, Integer pageSize) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);
		ModelAndView modelAndView = new ModelAndView("bill/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			// 供应商处理
			// ---- 供应商二级用户
			if (user.getRoleids().indexOf("F717B4EEF241460891F0E1D3AB84F74E") > -1) {
				params.put("contactid", user.getId());
				params.put("notpurchaser", true);
			}
			if (user.getRoleids().indexOf("B1CC34941707470D9BF361D1CEF2B08E") > -1) {
				params.put("supplierid", user.getId());
				params.put("notpurchaser", true);
			}
			// 代理
			if (user.getRoleids().indexOf("D776C89900D74CACA93834FE68880540") > -1) {
				params.put("agentid", user.getId());
				params.put("notpurchaser", true);
			}

			// 采购处理
			// 采购
			if (user.getRoleids().indexOf("A6B9CC86F7F24156A2CC50895312CC03") > -1||user.getRoleids().indexOf("05D8DCFAB55440F88EBCBC8C015BC690") > -1) {
				params.put("purchaserid", user.getId());
				params.remove("notpurchaser");
			}
			// 采购采购员
			if (user.getRoleids().indexOf("9334387B790C42658B7BE0655E155592") > -1) {
				if (!StringUtils.isEmpty(user.getErpusername()))
					params.put("erpusername", user.getErpusername());
				params.put("companyid", company.getId());
				params.remove("notpurchaser");
			}

			if (!StringUtils.isEmpty(planid)) {
				params.put("planid", planid);
			}

			if (!StringUtils.isEmpty(supplier)) {
				params.put("supplier", supplier);
			}

			if (!StringUtils.isEmpty(purchaser)) {
				params.put("purchaser", purchaser);
			}

			if (!StringUtils.isEmpty(agent)) {
				params.put("agent", agent);
			}

			if (!StringUtils.isEmpty(status)) {
				params.put("status", status);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<Bill> data = billService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "send")
	@ResponseBody
	public Object pbyid(String id) {
		try {
			Bill bill = new Bill();
			bill.setId(id);
			bill.setStatus(0);
			billService.update(bill);
			billService.sendNotice(id);

			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "arrivaltime")
	@ResponseBody
	public Object arrivaltime(HttpServletRequest request, HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM-dd") Date arrivaltime, String billid) {
		try {
			Bill bill = new Bill();
			bill.setId(billid);
			bill.setArrivaltime(arrivaltime);
			bill.setStatus(2);
			billService.update(bill);
			return this.getJsonResult();
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "exportfile")
	public void export(HttpServletRequest request, HttpServletResponse response, String billid) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("billid", billid);
			Page page = new Page(1, 2000);
			PageResult<BillDetail> data = billDetailService.findList(params, page);
			try {
				response.reset();
				response.setContentType("application/csv;charset=GBK");
				response.setHeader("Content-Disposition", "attachment;filename=Data" + System.currentTimeMillis() + ".csv");
				response.setCharacterEncoding("GBK");
				PrintWriter out = response.getWriter();
				out.println("编码, 品名,规格,单位 ,产地,数量,价格,备注");
				for (int i = 1; i <= data.getTotalPages(); i++) {
					PageResult<BillDetail> data2 = billDetailService.findList(params, new Page(i, 2000, false));
					for (BillDetail v : data2.getData()) {
						StringBuilder str = new StringBuilder();
						str.append("\"" + v.getCode()).append("\"\t,");
						str.append("\"" + v.getName()).append("\",");
						str.append("\"" + v.getSpecifications()).append("\",");
						str.append("\"" + v.getUnit()).append("\",");
						str.append("\"" + v.getFactory()).append("\",");
						str.append("\"" + v.getNum()).append("\",");
						str.append("\"" + v.getPrice()).append("\",");
						str.append("\"" + v.getRemark()).append("\",");
						out.println(str.toString().replaceAll("null", ""));
					}
				}
				out.flush();
				out.close();
			} catch (Exception e) {
				logger.error(e);
			}
		} catch (

		Exception e) {
			logger.error(e);
		}
	}

}