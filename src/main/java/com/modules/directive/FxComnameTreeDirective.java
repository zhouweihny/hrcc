package com.modules.directive;

import java.io.IOException;
import java.util.ArrayList;
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
import com.modules.service.FxComnameTreeService;
import com.modules.service.FxTreeService;
import com.modules.pojo.FxComnameTree;
import com.modules.pojo.FxTree;

@Component("fxComnameTreeDirective")
public class FxComnameTreeDirective implements TemplateDirectiveModel {

	@Autowired
	private FxComnameTreeService fxComnameTreeService;

	@Autowired
	private FxTreeService fxTreeService;

	private static List<FxTree> trees;

	private static long time = 0;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		// TODO Auto-generated method stub
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		// 处理传入的参数
		if (params.containsKey("comnameid")) {
			java.lang.String comnameid = ((TemplateScalarModel) params.get("comnameid")).getAsString();
			try {
				FxComnameTree entity = new FxComnameTree();
				entity.setComnameid(comnameid);
				List<FxComnameTree> fxComnameTrees = fxComnameTreeService.findList(entity);

				if (trees == null || System.currentTimeMillis() - time > 1000 * 60) {
					trees = fxTreeService.findList(new FxTree());
				}
				List<String> treepaths = new ArrayList<String>();

				List<String> treepaths2 = new ArrayList<String>();

				for (FxComnameTree ct : fxComnameTrees) {
					loop: for (FxTree tree : trees) {
						if (tree.getId().equals(ct.getTreeid())) {
							treepaths.add(tree.getPath());
							break loop;
						}
					}
				}
				for (String path : treepaths) {
					String[] ps = path.split("/");
					String pstr = "";
					for (String p : ps) {
						for (FxTree tree : trees) {
							if (p.equals(tree.getId())) {
								pstr = pstr + "/" + tree.getName();
							}
						}
					}
					treepaths2.add(pstr);
				}
				env.setVariable("treepaths", ObjectWrapper.DEFAULT_WRAPPER.wrap(treepaths2));
			} catch (Exception e) {
				throw new TemplateException(e.getMessage(), env);
			}
		}

		if (body != null) {
			body.render(env.getOut());
		}

	}

}