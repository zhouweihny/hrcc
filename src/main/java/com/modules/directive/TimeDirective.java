package com.modules.directive;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

@Component("timeDirective")
public class TimeDirective implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数

		if (params.containsKey("time")) {
			java.lang.String time = ((TemplateScalarModel) params.get("time")).getAsString();
			try {
				Long str = Long.parseLong(time);
				Date date = new Date(str);
				env.setVariable("time", ObjectWrapper.DEFAULT_WRAPPER.wrap(date));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}