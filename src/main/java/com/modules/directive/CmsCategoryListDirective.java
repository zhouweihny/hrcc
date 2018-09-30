package com.modules.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

import com.modules.service.CmsCategoryService;
import com.modules.pojo.CmsCategory;

@Component("cmsCategoryListDirective")
public class CmsCategoryListDirective implements TemplateDirectiveModel {

	@Autowired
	private CmsCategoryService cmsCategoryService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数

		if (params.containsKey("wxid")) {
			java.lang.String wxid = ((TemplateScalarModel) params.get("wxid")).getAsString();
			try {
				CmsCategory entity = new CmsCategory();
				entity.setWxid(wxid);
				List<CmsCategory> cmsCategories = cmsCategoryService.findList(entity);
				env.setVariable("cmsCategories", ObjectWrapper.DEFAULT_WRAPPER.wrap(cmsCategories));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}