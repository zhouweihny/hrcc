package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.modules.pojo.Space;
import com.modules.pojo.User;
import com.modules.service.DmStatusVoService;
import com.modules.service.SpaceService;
import com.modules.vo.DmStatusVo;
import com.modules.vo.DmVo;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("dmstatusvo")
public class DmStatusVoController extends BaseController {

	private static Logger logger = Logger.getLogger(DmStatusVoController.class);

	@Autowired
	private DmStatusVoService dmStatusVoService;
	@Autowired
	private SpaceService spaceService;

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("dmstatusvo/list");
		User user = (User) session.getAttribute(Constants.USER);
		try {
			String userid = user.getParentid();
			if (StringUtils.isEmpty(userid)) {
				userid = user.getId();
			}
			Space space = new Space();
			space.setUserid(userid);
			List<Space> spaces = spaceService.findList(space);
			modelAndView.addObject("spaces", spaces);
		} catch (Exception e) {
			logger.error(e);
		}

		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String spaceid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("dmstatusvo/table");
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			params.put("spaceid", spaceid);
			PageResult<DmStatusVo> data = dmStatusVoService.findList(params, new Page(false));
			List<DmVo> dms = new ArrayList<DmVo>();
			loop: for (DmStatusVo statusVo : data.getData()) {
				for (DmVo dm : dms) {
					if (dm.getCatalogid().equals(statusVo.getCatalogid())) {
						if (statusVo.getStatus() == 2) {
							dm.setNdm(statusVo.getNum().intValue() + dm.getNdm());
						}
						if (statusVo.getStatus() == 0) {
							dm.setDm(statusVo.getNum().intValue() + dm.getDm());
						}
						if (statusVo.getStatus() == 1) {
							dm.setIgnore(statusVo.getNum().intValue() + dm.getIgnore());
						}
						if (statusVo.getChecked()) {
							dm.setChecked(statusVo.getNum().intValue());
						}
						dm.setNum(dm.getIgnore() + dm.getDm() + dm.getNdm());
						continue loop;
					}

				}
				DmVo dm = new DmVo();
				dm.setName(statusVo.getName());
				dm.setCatalogid(statusVo.getCatalogid());
				dm.setNdm(0);
				dm.setDm(0);
				dm.setNum(0);
				dm.setIgnore(0);
				dm.setChecked(0);
				if (statusVo.getStatus() == 2) {
					dm.setNdm(statusVo.getNum().intValue());
				}
				if (statusVo.getStatus() == 0) {
					dm.setDm(statusVo.getNum().intValue());
				}
				if (statusVo.getStatus() == 1) {
					dm.setIgnore(statusVo.getNum().intValue());
				}
				if (statusVo.getChecked()) {
					dm.setChecked(statusVo.getNum().intValue());
				}
				dm.setNum(dm.getIgnore() + dm.getDm() + dm.getNdm());
				dms.add(dm);
			}

			PageResult<DmVo> a = new PageResult<DmVo>();
			a.setData(dms);

			modelAndView.addObject("page", a);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}