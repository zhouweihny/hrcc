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

import java.util.Date;
import java.util.HashMap;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.FxTreeTaskService;
import com.modules.pojo.Drug;
import com.modules.pojo.FxComname;
import com.modules.pojo.FxComnameTree;
import com.modules.pojo.FxTreeTask;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxtreetask")
public class FxTreeTaskController extends BaseController {

	private static Logger logger = Logger.getLogger(FxTreeTaskController.class);

	@Autowired
	private FxTreeTaskService fxTreeTaskService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxTreeTask fxTreeTask, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxTreeTask.getId())) {
				fxTreeTaskService.save(fxTreeTask);
			} else {
				fxTreeTaskService.update(fxTreeTask);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxTreeTask fxTreeTask) {
		try {
			fxTreeTaskService.delete(fxTreeTask);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxTreeTask fxTreeTask) {
		ModelAndView modelAndView = new ModelAndView("fxtreetask/info");
		try {
	  		if (!StringUtils.isEmpty(fxTreeTask.getId())) {
				FxTreeTask data = fxTreeTaskService.findObject(fxTreeTask);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxTreeTask fxTreeTask) {
		try {
			FxTreeTask data = fxTreeTaskService.findObject(fxTreeTask);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtreetask/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxtreetask/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxTreeTask> data = fxTreeTaskService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(String storeid, String treeid,@DateTimeFormat(pattern = "yyyy-MM-dd")Date startdate,@DateTimeFormat(pattern = "yyyy-MM-dd")Date enddate,String pxname,String dyname,String taskname,String remark,String id,String jobid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			
			
			FxTreeTask fxTask = new FxTreeTask();
			fxTask.setId(id);
			fxTask = fxTreeTaskService.findObject(fxTask);
			if(fxTask==null)
			{
				fxTask = new FxTreeTask();
				fxTask.setTreeid(treeid);
				fxTask.setStoreid(storeid);
				fxTask.setBegintime(startdate);
				fxTask.setEndtime(enddate);
				fxTask.setUserid(user.getId());
				fxTask.setTrainerid(pxname);
				fxTask.setClerkid(dyname);
				fxTask.setRemarks(remark);
				fxTask.setJobid(jobid);
				fxTask.setWritetime(new Date());
				fxTreeTaskService.save(fxTask);
			}else{
				fxTask.setTreeid(treeid);
				fxTask.setStoreid(storeid);
				fxTask.setBegintime(startdate);
				fxTask.setEndtime(enddate);
				fxTask.setUserid(user.getId());
				fxTask.setTrainerid(pxname);
				fxTask.setClerkid(dyname);
				fxTask.setRemarks(remark);
				fxTask.setJobid(jobid);
				fxTask.setWritetime(new Date());
				fxTreeTaskService.update(fxTask);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return this.getJsonResult(); 
	}
	
}