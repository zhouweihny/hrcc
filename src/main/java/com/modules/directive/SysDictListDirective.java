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
import com.modules.service.SysDictService;
import com.modules.pojo.SysDict;

@Component("sysDictListDirective")
public class SysDictListDirective implements TemplateDirectiveModel {

	@Autowired
	private SysDictService sysDictService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		// 处理传入的参数
		try {
			SysDict entity = new SysDict();
			List<SysDict> sysDicts = sysDictService.findList(entity);

			env.setVariable("sysDicts", ObjectWrapper.DEFAULT_WRAPPER.wrap(sysDicts));
		} catch (Exception e) {
			throw new TemplateException(e.getMessage(), env);
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}