package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import com.commons.base.BaseController;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.FxDrugReviewService;
import com.modules.vo.drugFX.FxDrugReviewVo;
import com.modules.pojo.FxDrugReview;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxdrugreview")
public class FxDrugReviewController extends BaseController {

	private static Logger logger = Logger.getLogger(FxDrugReviewController.class);

	@Autowired
	private FxDrugReviewService fxDrugReviewService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxDrugReview fxDrugReview, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxDrugReview.getId())) {
				fxDrugReviewService.save(fxDrugReview);
			} else {
				fxDrugReviewService.update(fxDrugReview);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxDrugReview fxDrugReview) {
		try {
			fxDrugReviewService.delete(fxDrugReview);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxDrugReview fxDrugReview) {
		ModelAndView modelAndView = new ModelAndView("fxdrugreview/info");
		try {
	  		if (!StringUtils.isEmpty(fxDrugReview.getId())) {
				FxDrugReview data = fxDrugReviewService.findObject(fxDrugReview);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxDrugReview fxDrugReview) {
		try {
			FxDrugReview data = fxDrugReviewService.findObject(fxDrugReview);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxdrugreview/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxdrugreview/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxDrugReview> data = fxDrugReviewService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
		}
	
	@RequestMapping(value = "gytable")
	public ModelAndView gytable(HttpServletRequest request, HttpServletResponse response, String companyid, String drugname, Integer status,String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxdrugreview/gytable");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			
			PageResult<FxDrugReviewVo> data = fxDrugReviewService.findByDrugReview(companyid,drugname,status, currentPage, pageSize);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}
	
	
	@RequestMapping("audit")
	@ResponseBody
	public Object audit(String drugid,Integer status, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!StringUtils.isEmpty(drugid)) {
				FxDrugReview entity = new FxDrugReview();
				entity.setDrugid(drugid);
			
				if(fxDrugReviewService.findObject(entity) !=null)
				{
					entity.setStatus(status);
					fxDrugReviewService.update(entity);
					
				}else{
					entity.setStatus(status);
					fxDrugReviewService.save(entity);
				}
			
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

}