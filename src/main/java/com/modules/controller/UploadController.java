package com.modules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import com.commons.base.BaseController;
import com.modules.pojo.Accesstoken;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {

	private static Logger logger = Logger.getLogger(UploadController.class);

	@RequestMapping("progress")
	@ResponseBody
	public Object save(Accesstoken accesstoken, HttpServletRequest request, HttpServletResponse response) {
		return this.getJsonResult(this.session.getAttribute("upload.progress"));
	}
}