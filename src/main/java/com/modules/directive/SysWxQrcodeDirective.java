package  com.modules.directive;
 

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
import  com.modules.service.SysWxQrcodeService;
import  com.modules.pojo.SysWxQrcode;

@Component("sysWxQrcodeDirective")
public class SysWxQrcodeDirective implements TemplateDirectiveModel {

	@Autowired
	private SysWxQrcodeService  sysWxQrcodeService;
 

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数
			
		if(params.containsKey("id")){
		  java.lang.String  primarykey =  ((TemplateScalarModel) params.get("id")).getAsString();
			try {
				SysWxQrcode entity = new SysWxQrcode();
				entity.setId(primarykey);
				SysWxQrcode	sysWxQrcode = sysWxQrcodeService.findObject(entity);
				if (sysWxQrcode == null)
					sysWxQrcode = new SysWxQrcode();
				env.setVariable("sysWxQrcode", ObjectWrapper.DEFAULT_WRAPPER.wrap(sysWxQrcode));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}