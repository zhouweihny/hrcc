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

import com.modules.service.SysWxMsgService;
import com.modules.pojo.SysWxMsg;

@Component("sysWxMsgListDirective")
public class SysWxMsgListDirective implements TemplateDirectiveModel {

	@Autowired
	private SysWxMsgService sysWxMsgService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数

		if (params.containsKey("wxid")) {
			java.lang.String wxid = ((TemplateScalarModel) params.get("wxid")).getAsString();
			try {
				SysWxMsg entity = new SysWxMsg();
				entity.setWxid(wxid);
				List<SysWxMsg> sysWxMsgs = sysWxMsgService.findList(entity);
				env.setVariable("sysWxMsgs", ObjectWrapper.DEFAULT_WRAPPER.wrap(sysWxMsgs));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}