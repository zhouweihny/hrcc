package com.modules.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

import com.modules.service.SysDictdataService;
import com.modules.pojo.SysDict;
import com.modules.pojo.SysDictdata;

@Component("freemarkerPropertiesDirective")
public class FreemarkerPropertiesDirective implements TemplateDirectiveModel {

	@Autowired
	private PropertiesFactoryBean configProperties;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数
		if (params.containsKey("key")) {
			java.lang.String key = ((TemplateScalarModel) params.get("key")).getAsString();
			try {
				env.setVariable("propertie", ObjectWrapper.DEFAULT_WRAPPER.wrap(configProperties.getObject().getProperty(key)));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}