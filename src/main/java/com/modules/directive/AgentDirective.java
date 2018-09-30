package com.modules.directive;

import java.io.IOException;
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
import com.modules.service.AgentService;
import com.modules.pojo.Agent;

@Component("agentDirective")
public class AgentDirective implements TemplateDirectiveModel {

	@Autowired
	private AgentService agentService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数

		if (params.containsKey("id")) {
			java.lang.String primarykey = ((TemplateScalarModel) params.get("id")).getAsString();
			try {
				Agent entity = new Agent();
				entity.setId(primarykey);
				Agent agent = agentService.findObject(entity);
				if (agent == null)
					agent = new Agent();
				env.setVariable("agent", ObjectWrapper.DEFAULT_WRAPPER.wrap(agent));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (params.containsKey("mobileno") && params.containsKey("purchaserid")) {
			java.lang.String mobileno = ((TemplateScalarModel) params.get("mobileno")).getAsString();
			java.lang.String purchaserid = ((TemplateScalarModel) params.get("purchaserid")).getAsString();
			try {
				Agent entity = new Agent();
				entity.setMobileno(mobileno);
				entity.setPurchaserid(purchaserid); 
				Agent agent = agentService.findObject(entity);
				if (agent == null)
					agent = new Agent();
				env.setVariable("agent", ObjectWrapper.DEFAULT_WRAPPER.wrap(agent));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}