package ${basePackage}.${controllerPackage};

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
import ${basePackage}.${servicePackage}.${c.className}Service;
import ${basePackage}.${pojoPackage}.${c.className};

/**
 * 
 * @author Du.Jun
 */
@Controller
@RequestMapping("${c.className?lower_case}")
public class ${c.className}Controller extends BaseController {

	private static Logger logger = Logger.getLogger(${c.className}Controller.class);

	@Autowired
	private ${c.className}Service ${c.className?uncap_first}Service;

    <#if c.containPrimaryKey>
	@RequestMapping("save")
	@ResponseBody
	public Object save(${c.className} ${c.className?uncap_first}, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(${c.className?uncap_first}.getId())) {
				${c.className?uncap_first}Service.save(${c.className?uncap_first});
			} else {
				${c.className?uncap_first}Service.update(${c.className?uncap_first});
			}
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(${c.className} ${c.className?uncap_first}) {
		try {
			${c.className?uncap_first}Service.delete(${c.className?uncap_first});
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}
		return this.getJsonResult();
	}

	@RequestMapping(value = "byid", method = RequestMethod.GET)
	@ResponseBody
	public Object gbyid(${c.className} ${c.className?uncap_first}) {
		ModelAndView modelAndView = new ModelAndView("${c.className?lower_case}/info");
		try {
	  		if (!StringUtils.isEmpty(${c.className?uncap_first}.get${c.primaryKey?cap_first}())) {
				${c.className} data = ${c.className?uncap_first}Service.findObject(${c.className?uncap_first});
				modelAndView.addObject("data", data);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "byid", method = RequestMethod.POST)
	@ResponseBody
	public Object pbyid(${c.className} ${c.className?uncap_first}) {
		try {
			${c.className} data = ${c.className?uncap_first}Service.findObject(${c.className?uncap_first});
			return this.getJsonResult(data);
		} catch (Exception e) {
			logger.error(e);
			return this.getJsonResult("9999", e.getMessage());
		}

	}
	</#if>
	
	@RequestMapping(value = "list")
	public ModelAndView glist(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("${c.className?lower_case}/list");
		return modelAndView;
	}

	@RequestMapping(value = "table")
	public ModelAndView plist(HttpServletRequest request, HttpServletResponse response, String stype, String sfield, Integer currentPage, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView("${c.className?lower_case}/table");
		try {
			Page page = new Page(currentPage, pageSize);
			HashMap<String, Object> params = new HashMap<String, Object>();
			// 参数设置
			if (!StringUtils.isEmpty(stype) && !StringUtils.isEmpty(sfield)) {
				params.put("stype", stype);
				params.put("sfield", sfield);
			}
			PageResult<${c.className}> data = ${c.className?uncap_first}Service.findList(params, page);
			modelAndView.addObject("page", data);
		} catch (Exception e) {
			logger.error(e);
		}
		return modelAndView;

	}

}