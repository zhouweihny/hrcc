package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.excel.ImportExcelUtil;
import com.modules.service.PlanService;
import com.modules.pojo.Plan;
import com.modules.pojo.PlanDetail;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("plan")
public class PlanController extends BaseController {

	private static Logger logger = Logger.getLogger(PlanController.class);

	@Autowired
	private PlanService planService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(Plan plan, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(plan.getId())) {
				planService.save(plan);
			} else {
				planService.update(plan);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Plan plan) {
		try {
			planService.delete(plan);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(Plan plan) {
		ModelAndView modelAndView = new ModelAndView("plan/info");
		try {
			if (!StringUtils.isEmpty(plan.getId())) {
				Plan data = planService.findObject(plan);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(Plan plan) {
		try {
			Plan data = planService.findObject(plan);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("plan/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String planid, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		User company = (User) session.getAttribute(Constants.COMPANY);
		User user = (User) session.getAttribute(Constants.USER);

		ModelAndView modelAndView = new ModelAndView("plan/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置

			if (!StringUtils.isEmpty(planid)) {
				params.put("planid", planid);
			}

			params.put("userid", company.getId());

			// 采购采购员
			if (user.getRoleids().indexOf("9334387B790C42658B7BE0655E155592") > -1) {
				if (!StringUtils.isEmpty(user.getErpusername()))
					params.put("erpusername", user.getErpusername());
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			PageResult<Plan> data = planService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	/**
	 * excel导入目录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/uploadxls")
	@ResponseBody
	public Object uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile, String storecode, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		InputStream xls;
		try {
			xls = uploadfile.getInputStream();
			String name = uploadfile.getOriginalFilename();
			List<PlanDetail> planDetails;
			if (name.toLowerCase().endsWith("xlsx")) {
				planDetails = ImportExcelUtil.importExcel2007(PlanDetail.class, xls);
			} else {
				planDetails = ImportExcelUtil.importExcel2003(PlanDetail.class, xls);
			}
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			planService.upload(name, storecode, userid, planDetails);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "上传成功");
	}

	@RequestMapping(value = "/create")
	@ResponseBody
	public Object create(String planid, HttpServletRequest request, HttpServletResponse response) {
		try {
			planService.create(planid);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", "生成失败" + e.getMessage());
		}
		return this.getJsonResult("0000", "生成成功");
	}

}