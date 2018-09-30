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
import  com.modules.service.UserSupplierService;
import  com.modules.pojo.UserSupplier;

@Component("userSupplierDirective")
public class UserSupplierDirective implements TemplateDirectiveModel {

	@Autowired
	private UserSupplierService  userSupplierService;
 

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数
			
		if(params.containsKey("id")){
		  java.lang.String  primarykey =  ((TemplateScalarModel) params.get("id")).getAsString();
			try {
				UserSupplier entity = new UserSupplier();
				entity.setId(primarykey);
				UserSupplier	userSupplier = userSupplierService.findObject(entity);
				if (userSupplier == null)
					userSupplier = new UserSupplier();
				env.setVariable("userSupplier", ObjectWrapper.DEFAULT_WRAPPER.wrap(userSupplier));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if(params.containsKey("code")&&params.containsKey("userid")){
			  java.lang.String  code =  ((TemplateScalarModel) params.get("code")).getAsString();
			  java.lang.String  userid =  ((TemplateScalarModel) params.get("userid")).getAsString();
				try {
					UserSupplier entity = new UserSupplier();
					entity.setCode(code); 
					entity.setUserid(userid);
					UserSupplier	userSupplier = userSupplierService.findObject(entity);
					if (userSupplier == null)
						userSupplier = new UserSupplier();
					env.setVariable("userSupplier", ObjectWrapper.DEFAULT_WRAPPER.wrap(userSupplier));
				} catch (Exception e) {
					throw new TemplateException(e.getMessage(), env);
				}
			}
		
		if (body != null) {
			body.render(env.getOut());
		}

	}

}