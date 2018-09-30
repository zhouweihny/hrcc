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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.DateUtil;
import com.modules.service.CatalogService;
import com.modules.service.FxTreeService;
import com.modules.service.FxTreeStoreService;
import com.modules.service.SaleAnalysisService;
import com.modules.vo.saleanalysis.DrugAnalysisVo;
import com.modules.pojo.Catalog;
import com.modules.pojo.FxTree;
import com.modules.pojo.FxTreeStore;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxtreestore")
public class FxTreeStoreController extends BaseController {

	private static Logger logger = Logger.getLogger(FxTreeStoreController.class);

	@Autowired
	private FxTreeStoreService fxTreeStoreService;

	@Autowired
	private FxTreeService fxTreeService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private SaleAnalysisService saleAnalysisService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxTreeStore fxTreeStore, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxTreeStore.getId())) {
				fxTreeStoreService.save(fxTreeStore);
			} else {
				fxTreeStoreService.update(fxTreeStore);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxTreeStore fxTreeStore) {
		try {
			fxTreeStoreService.delete(fxTreeStore);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxTreeStore fxTreeStore) {
		ModelAndView modelAndView = new ModelAndView("fxtreestore/info");
		try {
			if (!StringUtils.isEmpty(fxTreeStore.getId())) {
				FxTreeStore data = fxTreeStoreService.findObject(fxTreeStore);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxTreeStore fxTreeStore) {
		try {
			FxTreeStore data = fxTreeStoreService.findObject(fxTreeStore);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtreestore/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("fxtreestore/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<FxTreeStore> data = fxTreeStoreService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping("addlist")
	@ResponseBody
	public Object addlist(String storeid, String treeids, HttpServletRequest request, HttpServletResponse response) {
		try {

			String[] ids = treeids.split(",");

			FxTreeStore fxTreeStore = new FxTreeStore();
			fxTreeStore.setStoreid(storeid);
			List<FxTreeStore> fxTreeStores = fxTreeStoreService.findList(fxTreeStore);

			loop: for (FxTreeStore fs : fxTreeStores) {
				for (String id : ids) {
					if (fs.getTreeid().equals(id)) {
						continue loop;
					}
				}

				FxTreeStore fs2 = new FxTreeStore();
				fs2.setId(fs.getId());
				fxTreeStoreService.delete(fs2);
			}

			loop: for (String id : ids) {
				for (FxTreeStore fs : fxTreeStores) {
					if (fs.getTreeid().equals(id)) {
						continue loop;
					}
				}

				FxTreeStore fs = new FxTreeStore();
				fs.setStoreid(storeid);
				fs.setTreeid(id);
				fxTreeStoreService.save(fs);
			}

		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("selected")
	@ResponseBody
	public Object selected(String storeid, HttpServletRequest request, HttpServletResponse response) {
		try {
			FxTreeStore fxTreeStore = new FxTreeStore();
			fxTreeStore.setStoreid(storeid);
			List<FxTreeStore> fxTreeStores = fxTreeStoreService.findList(fxTreeStore);
			List<String> treeids = new ArrayList<String>();
			for (FxTreeStore ts : fxTreeStores) {
				treeids.add(ts.getTreeid());
			}
			return this.getJsonResult(treeids);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping("customtree")
	@ResponseBody
	public Object customtree(String storeid, HttpServletRequest request, HttpServletResponse response) {
		try {
			User company = (User) this.session.getAttribute(Constants.COMPANY);
			List<FxTree> fxTrees = fxTreeService.customtree(storeid);

			FxTree rootTree = fxTreeService.findAnalysisRootTree(company.getId());

			boolean flag = true;

			for (FxTree tree : fxTrees) {
				if (tree.getParentid() == null) {
					flag = false;
					break;
				}
			}
			if (flag) {
				for (FxTree tree : fxTrees) {
					if (tree.getParentid().equals(rootTree.getId()) || tree.getParentid().equals("36A8746039AB40C39D0B7CD75DE45650"))
						tree.setParentid("0000");
				}
				FxTree tree = new FxTree();
				tree.setId("0000");
				tree.setName("自定义品类树");
				fxTrees.add(tree);
			}
			Collections.sort(fxTrees, new Comparator<FxTree>() {
				@Override
				public int compare(FxTree o1, FxTree o2) {
					if (o1.getSort() != null && o2.getSort() != null) {
						if (o1.getSort() == o2.getSort())
							return 0;
						return -o1.getSort().compareTo(o2.getSort());
					} else
						return 0;
				}
			});

			return this.getJsonResult(fxTrees);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping("customgysdrug")
	@ResponseBody
	public Object customdrug(String storeid, String name, String comnameid, String treeid, Integer status, Integer currentPage, Integer pageSize, String stype, String sfield,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = (User) session.getAttribute(Constants.COMPANY);
			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);
			return this.getJsonResult(fxTreeService.customgydrugs(storeid, user.getId(), treeid, comnameid, name, stype, sfield));
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping("customdrug")
	@ResponseBody
	public Object customdrug(@DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate, String storeid, String name, String comnameid, String treeid,
			Integer status, Integer currentPage, Integer pageSize, String stype, String sfield, HttpServletRequest request, HttpServletResponse response) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User user = (User) session.getAttribute(Constants.COMPANY);
			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);
			return this.getJsonResult(fxTreeService.customdrug(user.getId(), comnameid, startdate, enddate, catalog.getId(), treeid, storeid, name, status, currentPage, pageSize, stype, sfield));
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "zlist")
	public ModelAndView zlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtreestore/zlist");
		return modelAndView;
	}

	@RequestMapping("countbytreeid")
	@ResponseBody
	public Object countbytreeid(String userid, String storeid, @DateTimeFormat(pattern = "yyyy-MM") Date startdate, @DateTimeFormat(pattern = "yyyy-MM") Date enddate, String treeid, String name,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			enddate = DateUtil.getSpecifiedMonthAfter(enddate);
			User user = (User) session.getAttribute(Constants.COMPANY);
			Catalog catalog = new Catalog();
			catalog.setMyself(true);
			catalog.setUserid(user.getId());
			catalog = catalogService.findObject(catalog);
			List<DrugAnalysisVo> data = saleAnalysisService.countByTreeid(user.getId(), storeid, treeid, startdate, enddate);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

	@RequestMapping(value = "managementlist")
	public ModelAndView managementlist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtreestore/managementlist");
		return modelAndView;
	}

	@RequestMapping(value = "zlists")
	public ModelAndView zlists(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxtreestore/zlists");
		return modelAndView;
	}

}