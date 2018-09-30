package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.HashMap;

import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.FileUtil;
import com.commons.util.UUIDGenerator;
import com.commons.util.wx.WxUtil;
import com.commons.util.wx.bean.material.MaterialResponse;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.SysWxMaterialImgService;
import com.modules.pojo.SysWxAccesstoken;
import com.modules.pojo.SysWxMaterialImg;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("syswxmaterialimg")
public class SysWxMaterialImgController extends BaseController {

	private static Logger logger = Logger.getLogger(SysWxMaterialImgController.class);

	@Autowired
	private SysWxMaterialImgService sysWxMaterialImgService;

	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;

	@Value("#{configProperties['img.upload']}")
	private String path;

	@RequestMapping("save")
	@ResponseBody
	public Object save(@RequestParam("uploadfile") MultipartFile uploadfile, String group, String wxid, String remark, HttpServletRequest request, HttpServletResponse response) {
		try {
			String extName = uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			String fileName = UUIDGenerator.getUUID() + extName;
			FileUtil.saveToFile(path + fileName, uploadfile.getInputStream());
			SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
			sysWxAccesstoken.setWxid(wxid);
			sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
			if (sysWxAccesstoken != null) {
				MaterialResponse materialResponse = WxUtil.addMaterialEver(sysWxAccesstoken.getAccesstoken(), new File(path + fileName), "image");
				SysWxMaterialImg sysWxMaterialImg = new SysWxMaterialImg();
				sysWxMaterialImg.setWxid(wxid);
				sysWxMaterialImg.setIgroup(group);
				sysWxMaterialImg.setMediaid(materialResponse.getMedia_id());
				sysWxMaterialImg.setUrl(materialResponse.getUrl());
				sysWxMaterialImg.setPath(fileName);
				sysWxMaterialImg.setRemark(remark);
				sysWxMaterialImgService.save(sysWxMaterialImg);
				return this.getJsonResult(sysWxMaterialImg);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("uploadtwimg")
	@ResponseBody
	public Object save(@RequestParam(value = "uploadfile", required = false) MultipartFile uploadfile, String wxid, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (uploadfile != null) {
				String extName = uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
				String fileName = UUIDGenerator.getUUID() + extName;
				FileUtil.saveToFile(path + fileName, uploadfile.getInputStream());
				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(wxid);
				sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				if (sysWxAccesstoken != null) {
					MaterialResponse materialResponse = WxUtil.addMaterialEver(sysWxAccesstoken.getAccesstoken(), new File(path + fileName), "image");
					return this.getJsonResult(materialResponse);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(SysWxMaterialImg sysWxMaterialImg) {
		try {
			sysWxMaterialImg = sysWxMaterialImgService.findObject(sysWxMaterialImg);
			if (sysWxMaterialImg != null) {
				SysWxAccesstoken sysWxAccesstoken = new SysWxAccesstoken();
				sysWxAccesstoken.setWxid(sysWxMaterialImg.getWxid());
				sysWxAccesstoken = sysWxAccesstokenService.findObject(sysWxAccesstoken);
				if (sysWxAccesstoken != null) {
					MaterialResponse materialResponse = WxUtil.delMaterial(sysWxAccesstoken.getAccesstoken(), sysWxMaterialImg.getMediaid());
					if (materialResponse != null && materialResponse.getErrmsg().equals("ok")) {
						sysWxMaterialImgService.delete(sysWxMaterialImg);
					} else {
						return this.getJsonResult("9999", materialResponse.getErrmsg());
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(SysWxMaterialImg sysWxMaterialImg) {
		ModelAndView modelAndView = new ModelAndView("syswxmaterialimg/info");
		try {
			if (!StringUtils.isEmpty(sysWxMaterialImg.getId())) {
				SysWxMaterialImg data = sysWxMaterialImgService.findObject(sysWxMaterialImg);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(SysWxMaterialImg sysWxMaterialImg) {
		try {
			SysWxMaterialImg data = sysWxMaterialImgService.findObject(sysWxMaterialImg);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("syswxmaterialimg/list");
		return modelAndView;
	}

	@RequestMapping(value = "/table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String wxid, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("syswxmaterialimg/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(wxid)) {
				params.put("wxid", wxid);
			}
			User user = (User) this.session.getAttribute(Constants.USER);
			if (!user.getUsername().equalsIgnoreCase(Constants.ADMIN))
				params.put("userid", user.getId());
			// 参数设置
			PageResult<SysWxMaterialImg> data = sysWxMaterialImgService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(HttpServletRequest request, HttpServletResponse response, String wxid, Integer currentPage, Integer pageSize) {
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(wxid)) {
				params.put("wxid", wxid);
			}
			// 参数设置
			PageResult<SysWxMaterialImg> data = sysWxMaterialImgService.findList(params, page);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}