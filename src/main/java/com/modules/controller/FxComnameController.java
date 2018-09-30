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

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.FxComnameService;
import com.modules.service.FxComnameTreeService;
import com.modules.service.FxTreeService;
import com.modules.vo.ComnameVo;
import com.modules.vo.saleanalysis.DrugAnalysisVo;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.FxComname;
import com.modules.pojo.FxComnameTree;
import com.modules.pojo.FxTree;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxcomname")
public class FxComnameController extends BaseController {

	private static Logger logger = Logger.getLogger(FxComnameController.class);

	@Autowired
	private FxComnameService fxComnameService;

	@Autowired
	private FxComnameTreeService fxComnameTreeService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private DrugService drugService;

	@Autowired
	private FxTreeService fxTreeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxComname fxComname, HttpServletRequest request, HttpServletResponse response) {

		User user = (User) session.getAttribute(Constants.USER);

		try {
			if (StringUtils.isEmpty(fxComname.getId())) {

				if (org.apache.commons.lang3.StringUtils.isNotBlank(fxComname.getName())) {
					FxComname fxC = new FxComname();
					fxC.setName(fxComname.getName());
					fxC = fxComnameService.findObject(fxC);
					if (fxC == null) {
						fxComname.setOperatorid(user.getId());
						fxComnameService.save(fxComname);
					} else {
						this.getJsonResult("9999", "通用名已存在");
					}
				} else {
					this.getJsonResult("9999", "通用名不能为空");
				}
			} else {
				fxComnameService.update(fxComname);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxComname fxComname) {
		try {
			if (!StringUtils.isEmpty(fxComname.getId())) {
				fxComnameService.delete(fxComname);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxComname fxComname) {
		ModelAndView modelAndView = new ModelAndView("fxcomname/info");
		try {
			if (!StringUtils.isEmpty(fxComname.getId())) {
				FxComname data = fxComnameService.findObject(fxComname);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxComname fxComname) {
		try {
			FxComname data = fxComnameService.findObject(fxComname);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxcomname/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxcomname/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}
			PageResult<FxComname> data = fxComnameService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "fxcomnames")
	@ResponseBody
	public Object fxcomnames(HttpServletRequest request, HttpServletResponse response, String name, String stype, String sfield, Integer currentPage, Integer pageSize) {
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			PageResult<FxComname> data = fxComnameService.findList(params, page);
			return data;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@RequestMapping(value = "autocompare", method = RequestMethod.GET)
	@ResponseBody
	public Object autocompare() {
		User company = (User) session.getAttribute(Constants.COMPANY);
		String companyid = company.getId();
		try {
			List<Drug> drugs = drugService.findNoComnameList(companyid);
			for (Drug d : drugs) {
				try {
					String fxComnameid = fxComnameService.compareDrug(d.getName());
					d.setComnameid(fxComnameid);
					drugService.update(d);

				} catch (Exception e) {
					logger.error(e);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "druglist")
	public ModelAndView druglist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxcomname/druglist");
		return modelAndView;
	}

	@RequestMapping(value = "drugtable")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String username, String dz, String name, String factory, String stype, String sfield, Integer currentPage,
			Integer pageSize) {
		User user = (User) session.getAttribute(Constants.USER);
		ModelAndView modelAndView = new ModelAndView("fxcomname/drugtable");
		try {

			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			// String userid = user.getId();
			//
			// Catalog catalog = new Catalog();
			// catalog.setMyself(true);
			// catalog.setUserid(user.getId());
			// catalog = catalogService.findObject(catalog);
			//
			// if (catalog == null) {
			// catalog = new Catalog();
			// catalog.setName("经营品种");
			// catalog.setMyself(true);
			// catalog.setRemark("经营品种");
			// catalog.setUserid(userid);
			// catalogService.save(catalog);
			// }
			// params.put("userid", userid);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN)) {
				params.put("comnameuserid", user.getId());
			}

			if (!StringUtils.isEmpty(name)) {
				params.put("name", name);
			}

			if (!StringUtils.isEmpty(dz)) {
				params.put("dz", dz);
			}

			if (!StringUtils.isEmpty(username)) {
				params.put("username", username);
			}

			if (!StringUtils.isEmpty(factory)) {
				params.put("factory", factory);
			}

			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", "desc");
				params.put("sfield", "name");
			}

			PageResult<Drug> data = drugService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "binding")
	@ResponseBody
	public Object binding(String comnameid, String drugid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {
			Drug drug = new Drug();
			drug.setId(drugid);
			drug.setComnameid(comnameid);
			drug.setComnameuserid(user.getId());
			drugService.update(drug);

			fxComnameService.bandingAddComname(drugid, comnameid);
		} catch (Exception e) {
			logger.error(e);
		}

		return this.getJsonResult();
	}

	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(String comname, String treeid, String drugid) {
		User user = (User) session.getAttribute(Constants.USER);
		try {

			FxComname fxComname = new FxComname();
			fxComname.setName(comname);
			fxComname = fxComnameService.findObject(fxComname);

			if (fxComname == null) {
				fxComname = new FxComname();
				fxComname.setName(comname);
				fxComname.setOperatorid(user.getId());
				fxComnameService.save(fxComname);
			}

			FxComnameTree fxComnameTree = new FxComnameTree();
			fxComnameTree.setComnameid(fxComname.getId());
			fxComnameTree.setTreeid(treeid);
			fxComnameTree = fxComnameTreeService.findObject(fxComnameTree);

			if (fxComnameTree == null) {
				fxComnameTree = new FxComnameTree();
				fxComnameTree.setComnameid(fxComname.getId());
				fxComnameTree.setTreeid(treeid);
				fxComnameTree.setOperatorid(user.getId());
				fxComnameTreeService.save(fxComnameTree);
			}
			if (!StringUtils.isEmpty(drugid)) {
				Drug drug = new Drug();
				drug.setId(drugid);
				drug.setComnameid(fxComname.getId());
				drug.setComnameuserid(user.getId());
				drugService.update(drug);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "tree")
	@ResponseBody
	public Object tree() {
		try {
			List<FxTree> trees = fxTreeService.findList(new FxTree());

			Collections.sort(trees, new Comparator<FxTree>() {
				@Override
				public int compare(FxTree o1, FxTree o2) {
					if (o1.getSort() == o2.getSort())
						return 0;
					return -o1.getSort().compareTo(o2.getSort());
				}
			});

			return this.getJsonResult(trees);
		} catch (Exception e) {
			logger.error(e);
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "treecomnames")
	@ResponseBody
	public Object treecomnames(String treeid, String rootid, Integer status, String name, Integer currentPage, Integer pageSize) {
		try {

			FxTree tree = new FxTree();
			tree.setId(treeid);
			tree = fxTreeService.findObject(tree);

			PageResult<ComnameVo> treecomnames = fxComnameService.findByTreePath(treeid, rootid, name, status, currentPage, pageSize);
			return this.getJsonResult(treecomnames);
		} catch (Exception e) {
			logger.error(e);
		}
		return this.getJsonResult();
	}

}