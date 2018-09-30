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
import com.commons.util.UUIDGenerator;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.res.QrcodeRequest;
import com.commons.util.wx.bean.res.QrcodeResponse;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxQrcodeService;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.SysWxQrcode;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxqrcode")
public class SysWxQrcodeController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxQrcodeController.class);

	@Autowired
	private SysWxQrcodeService sysWxQrcodeService;
	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@RequestMapping("save")
	@ResponseBody
	public Object save(SysWxQrcode sysWxQrcode, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(sysWxQrcode.getId())) {
				QrcodeRequest qrcodeRequest = new QrcodeRequest();
				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(sysWxQrcode.getWxid());
				sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				qrcodeRequest.setAccess_token(sysWxAccesstoken.getAccesstoken());
				qrcodeRequest.setScenestr(UUIDGenerator.getUUID());
				qrcodeRequest.setType(true);
				QrcodeResponse qrcodeResponse = WxUtil.responseQrq(qrcodeRequest);
				if (StringUtils.isEmpty(qrcodeResponse.getErrcode())) {
					sysWxQrcode.setScenestr(qrcodeRequest.getScenestr());
					sysWxQrcode.setTicket(qrcodeResponse.getTicket());
					sysWxQrcode.setUrl(qrcodeResponse.getUrl());
					sysWxQrcode.setUsed(false);
					sysWxQrcodeService.save(sysWxQrcode);
				} else {
					return this.getJsonResult(qrcodeResponse.errcode, qrcodeResponse.errmsg);
				}
			} else {
				sysWxQrcodeService.update(sysWxQrcode);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxQrcode sysWxQrcode) {
		try {
			sysWxQrcodeService.delete(sysWxQrcode);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxQrcode sysWxQrcode) {
		ModelAndView modelAndView = new ModelAndView("syswxqrcode/info");
		try {
			if (!StringUtils.isEmpty(sysWxQrcode.getId())) {
				SysWxQrcode data = sysWxQrcodeService.findObject(sysWxQrcode);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	 

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxQrcode sysWxQrcode) {
		try {
			SysWxQrcode data = sysWxQrcodeService.findObject(sysWxQrcode);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxqrcode/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String wxid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxqrcode/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(wxid))
				params.put("wxid", wxid);
			PageResult<SysWxQrcode> data = sysWxQrcodeService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}