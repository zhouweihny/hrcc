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
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.UUIDGenerator;
import com.modules.service.FxTreeService;
import com.modules.pojo.FxTree;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxtree")
public class FxTreeController extends BaseController {

	private static Logger logger = Logger.getLogger(FxTreeController.class);

	@Autowired
	private FxTreeService fxTreeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxTree fxTree, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);

		try {
			if (StringUtils.isEmpty(fxTree.getId())) {
				FxTree pTree = new FxTree();
				pTree.setId(fxTree.getParentid());
				pTree = fxTreeService.findObject(pTree);

				if (pTree != null) {
					fxTree.setId(UUIDGenerator.getUUID());
					fxTree.setPath(pTree.getPath() + "/" + fxTree.getId());
				}
				fxTree.setOperatorid(user.getId());
				fxTreeService.save(fxTree);
			} else {
				fxTreeService.update(fxTree);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("update")
	@ResponseBody
	public Object update(String id, String name, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			FxTree pTree = new FxTree();
			pTree.setId(id);
			pTree = fxTreeService.findObject(pTree);
			fxTreeService.updateName(id, name);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxTree fxTree) {
		try {
			if (!StringUtils.isEmpty(fxTree.getId())) {
				fxTreeService.delete(fxTree);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxTree fxTree) {
		ModelAndView modelAndView = new ModelAndView("fxtree/info");
		try {
			if (!StringUtils.isEmpty(fxTree.getId())) {
				FxTree data = fxTreeService.findObject(fxTree);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "analysistree" )
	@ResponseBody
	public Object findAnalysisTree() {
		User company = (User) session.getAttribute(Constants.COMPANY);
		try {
			List<FxTree> datas = fxTreeService.findAnalysisTree(company.getId());
			return this.getJsonResult(datas);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxTree fxTree) {
		try {
			FxTree data = fxTreeService.findObject(fxTree);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtree/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxtree/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxTree> data = fxTreeService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "combolist", method = RequestMethod.POST)
	@ResponseBody
	public Object combolist() {
		try {
			FxTree tree = new FxTree();
			List<FxTree> data = fxTreeService.findList(tree);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "zlist")
	public ModelAndView zlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxzuitree/list");
		return modelAndView;
	}
}