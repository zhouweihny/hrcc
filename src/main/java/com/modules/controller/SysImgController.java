package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import com.commons.base.BaseController;
import com.commons.util.FileUtil;
import com.commons.util.UUIDGenerator;
/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("sysimg")
public class SysImgController extends BaseController {

	private static Logger logger = Logger.getLogger(SysImgController.class);

	@Value("#{configProperties['img.upload']}")
	private String path;

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile, String group, HttpServletRequest request, HttpServletResponse response) {
		try {
			String extName = uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			String fileName = UUIDGenerator.getUUID() + extName;
			FileUtil.saveToFile(path + fileName, uploadfile.getInputStream());
			return this.getJsonResult("0000", fileName);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
	}

}