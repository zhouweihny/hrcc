package com.modules.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import com.commons.base.BaseController;
import com.commons.base.Constants;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.UUIDGenerator;
import com.commons.util.excel.ImportExcelUtil;
import com.modules.service.FxSaleFileService;
import com.modules.pojo.FxSaleData;
import com.modules.pojo.FxSaleFile;
import com.modules.pojo.User;

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("fxsalefile")
public class FxSaleFileController extends BaseController {

	private static Logger logger = Logger.getLogger(FxSaleFileController.class);

	@Autowired
	private FxSaleFileService fxSaleFileService;

	@Value("#{configProperties['file.sale.path']}")
	private String path;

	@RequestMapping("save")
	@ResponseBody
	public Object save(FxSaleFile fxSaleFile, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(fxSaleFile.getId())) {
				fxSaleFileService.save(fxSaleFile);
			} else {
				fxSaleFileService.update(fxSaleFile);
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(FxSaleFile fxSaleFile) {
		try {
			fxSaleFileService.delete(fxSaleFile);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(FxSaleFile fxSaleFile) {
		ModelAndView modelAndView = new ModelAndView("fxsalefile/info");
		try {
			if (!StringUtils.isEmpty(fxSaleFile.getId())) {
				FxSaleFile data = fxSaleFileService.findObject(fxSaleFile);
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(FxSaleFile fxSaleFile) {
		try {
			FxSaleFile data = fxSaleFileService.findObject(fxSaleFile);
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}

	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("fxsalefile/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, Integer status, String storeid, String sfield, Integer currentPage, Integer pageSize) {

		User user = (User) session.getAttribute(Constants.USER);
		User company = (User) session.getAttribute(Constants.COMPANY);
		ModelAndView modelAndView = new ModelAndView("fxsalefile/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}

			if (!StringUtils.isEmpty(storeid)) {
				params.put("storeid", storeid);
			}
			if (!StringUtils.isEmpty(status)) {
				params.put("status", status);
			}
			params.put("userid", company.getId());

			PageResult<FxSaleFile> data = fxSaleFileService.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "combobox")
	@ResponseBody
	public Object drugs(HttpServletRequest request, HttpServletResponse response, String storeid) {
		try {
			Page page = new Page(1, 200);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("status", 2);
			if (!StringUtils.isEmpty(storeid)) {
				params.put("storeid", storeid);
			}
			PageResult<FxSaleFile> data = fxSaleFileService.findList(params, page);
			return data;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * excel导入目录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile, String storeid, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constants.USER);
		User company = (User) session.getAttribute(Constants.COMPANY);
		InputStream xls;
		try {
			xls = uploadfile.getInputStream();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(new Date());

			File file = new File(path + File.separator + dateStr);
			if (!file.exists()) {
				file.mkdirs();
			}

			String path = file.getPath() + File.separator + UUIDGenerator.getUUID();
			String errorpath = path + "_error";
			String name = uploadfile.getOriginalFilename();
			int num = 0;
			if (name.toLowerCase().endsWith("xlsx")) {
				path = path + ".xlsx";
				errorpath = errorpath + ".xlsx";
			} else {
				path = path + ".xls";
				errorpath = errorpath + ".xls";
			}
			FileUtils.copyInputStreamToFile(xls, new File(path));
			num = ImportExcelUtil.countRows(FxSaleData.class, new File(path));
			FxSaleFile fxSaleFile = new FxSaleFile();
			fxSaleFile.setNum(num - 1);
			fxSaleFile.setName(name);
			fxSaleFile.setStoreid(storeid);
			fxSaleFile.setProcessed(0);
			fxSaleFile.setError(0);
			fxSaleFile.setFilepath(path);
			fxSaleFile.setErrorpath(errorpath);
			fxSaleFile.setStatus(0);
			fxSaleFile.setUserid(company.getId());
			fxSaleFile.setOperatorid(user.getId());
			fxSaleFileService.save(fxSaleFile);
		} catch (Exception e) {
			return this.getJsonResult("9999", "上传失败" + e.getMessage());
		}

		return this.getJsonResult("0000", "上传成功");
	}

	@RequestMapping("/download/{type}/{id}")
	public void download(@PathVariable String type, @PathVariable String id, HttpServletResponse response) throws IOException {
		FxSaleFile fxSaleFile = new FxSaleFile();
		fxSaleFile.setId(id);
		try {
			fxSaleFile = fxSaleFileService.findObject(fxSaleFile);
		} catch (Exception e) {
			logger.error(e);
		}
		File file;
		if ("file".equals(type)) {
			file = new File(fxSaleFile.getFilepath());
		} else {
			file = new File(fxSaleFile.getErrorpath());
		}
		// 和上面一样，将content数据使用流放入缓存body中
		byte[] body = null;

		ByteArrayInputStream is = new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(file)));
		body = new byte[is.available()];
		is.read(body);
		// 将ContentType、Header等需要的设置放进 HttpServletResponse传回前端
		String fileName = URLEncoder.encode(fxSaleFile.getName(), "utf-8");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setContentLength(body.length);
		// 获取Servlet的输出流ServletOutputStream
		ServletOutputStream sos = response.getOutputStream();
		sos.write(body);
		if (sos != null) {
			sos.close();
		}
	}
}