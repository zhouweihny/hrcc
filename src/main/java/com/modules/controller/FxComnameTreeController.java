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
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.FxComnameTreeFactoryService;
import com.modules.service.FxComnameTreeService;
import com.modules.pojo.FxComnameTree;
import com.modules.pojo.FxComnameTreeFactory;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxcomnametree")
public class FxComnameTreeController extends BaseController {

	private static Logger logger = Logger.getLogger(FxComnameTreeController.class);

	@Autowired
	private FxComnameTreeService fxComnameTreeService;

	@Autowired
	private FxComnameTreeFactoryService fxComnameTreeFactoryService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxComnameTree fxComnameTree, HttpServletRequest request, HttpServletResponse response) {
		try {

			User user = (User) this.session.getAttribute(Constants.USER);
			if (StringUtils.isEmpty(fxComnameTree.getId())) {
				fxComnameTree.setOperatorid(user.getId());
				fxComnameTreeService.save(fxComnameTree);
			} else {
				fxComnameTree.setOperatorid(user.getId());
				fxComnameTreeService.update(fxComnameTree);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("update")
	@ResponseBody
	public Object update(FxComnameTree fxComnameTree, HttpServletRequest request, HttpServletResponse response) {
		try {

			if (org.apache.commons.lang3.StringUtils.isNotBlank(fxComnameTree.getTreeid()) && org.apache.commons.lang3.StringUtils.isNotBlank(fxComnameTree.getComnameid())) {
				FxComnameTree oldfxComnameTree = new FxComnameTree();
				oldfxComnameTree.setTreeid(fxComnameTree.getTreeid());
				oldfxComnameTree.setComnameid(fxComnameTree.getComnameid());
				fxComnameTreeService.update(fxComnameTree, oldfxComnameTree);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxComnameTree fxComnameTree) {
		try {

			FxComnameTree t = fxComnameTreeService.findObject(fxComnameTree);
			if (t != null) {
				fxComnameTreeService.delete(fxComnameTree);
				FxComnameTreeFactory fxComnameTreeFactory = new FxComnameTreeFactory();
				fxComnameTreeFactory.setComnameid(fxComnameTree.getComnameid());
				fxComnameTreeFactory.setTreeid(fxComnameTree.getTreeid());
				fxComnameTreeFactoryService.delete(fxComnameTreeFactory);
			}

		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxComnameTree fxComnameTree) {
		ModelAndView modelAndView = new ModelAndView("fxcomnametree/info");
		try {
			if (!StringUtils.isEmpty(fxComnameTree.getId())) {
				FxComnameTree data = fxComnameTreeService.findObject(fxComnameTree);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxComnameTree fxComnameTree) {
		try {
			FxComnameTree data = fxComnameTreeService.findObject(fxComnameTree);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxcomnametree/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxcomnametree/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxComnameTree> data = fxComnameTreeService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}