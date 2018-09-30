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
import com.modules.service.SysWxUserQrcodeService;
import com.modules.pojo.SysWxUserQrcode;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxuserqrcode")
public class SysWxUserQrcodeController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxUserQrcodeController.class);

	@Autowired
	private SysWxUserQrcodeService sysWxUserQrcodeService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWxUserQrcode sysWxUserQrcode, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWxUserQrcode.getId())) {
				sysWxUserQrcodeService.save(sysWxUserQrcode);
			} else {
				sysWxUserQrcodeService.update(sysWxUserQrcode);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxUserQrcode sysWxUserQrcode) {
		try {
			sysWxUserQrcodeService.delete(sysWxUserQrcode);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxUserQrcode sysWxUserQrcode) {
		ModelAndView modelAndView = new ModelAndView("syswxuserqrcode/info");
		try {
	  		if (!StringUtils.isEmpty(sysWxUserQrcode.getId())) {
				SysWxUserQrcode data = sysWxUserQrcodeService.findObject(sysWxUserQrcode);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxUserQrcode sysWxUserQrcode) {
		try {
			SysWxUserQrcode data = sysWxUserQrcodeService.findObject(sysWxUserQrcode);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxuserqrcode/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxuserqrcode/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			PageResult<SysWxUserQrcode> data = sysWxUserQrcodeService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}