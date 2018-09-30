package com.commons.sroure;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * @author Du.Jun
 *
 */
public class SoureGenerate {

	public static final String basePackage = "com.modules";// 基础包名
	public static final String pojoPackage = "pojo";// 实体包名
	public static final String voPackage = "vo";// 实体包名
	public static final String daoPackage = "dao";// dao层
	public static final String servicePackage = "service";// service层
	public static final String controllerPackage = "controller";// contrller层
	public static final String directivePackage = "directive";// 指令
	public static final String viewPageage = "view";
	public static final String basePath = "F:\\workspaceClientt\\cc";
	public static String jdbcUrl;
	public static String jdbcUsername;
	public static String jdbcPpassword;
	public static String jdbcDriverClassName;
	private static Configuration config;
	private static Map<String, Object> model = new HashMap<String, Object>();

	public static void init() {
		jdbcDriverClassName = PropertiesHelper.get("jdbc.driverClassName");
		jdbcUrl = PropertiesHelper.get("jdbc.url");
		jdbcUsername = PropertiesHelper.get("jdbc.username");
		jdbcPpassword = PropertiesHelper.get("jdbc.password");
		config = new freemarker.template.Configuration(Configuration.getVersion());
		config.setClassForTemplateLoading(SoureGenerate.class, "");
		config.setDefaultEncoding("utf-8");
		model.put("pojoPackage", pojoPackage);
		model.put("voPackage", voPackage);
		model.put("daoPackage", daoPackage);
		model.put("servicePackage", servicePackage);
		model.put("controllerPackage", controllerPackage);
		model.put("basePackage", basePackage);
		model.put("directivePackage", directivePackage);

	}

	public static void main(String args[]) throws Exception {
		init();
		// String[] tables = {
		// "sys_dict","sys_dictdata","sys_menu","sys_role","sys_role_menu","sys_wx","sys_wx_accesstoken","sys_wx_menu","sys_wx_msg","sys_wx_qrcode","sys_wx_user","t_user"};
		//
		String[] tables = { "fx_metadata","fx_metadataitem","fx_impsaledata" };
		genFromTable(tables);

		// StringBuilder sql = new StringBuilder();
		// sql.append("		 select b.fnumber as code ,b.fprodplace as manufacturer ,b.fname_l2 as   name,b.fwarrantnumber as zhunzi  ,b.fmodel as specifications, a.qty as stock,b.fhelpcode as helpcode from( select * from ");
		// sql.append(" t_bd_material bab  where  bab.fnumber in (select fid from t_st_ceshi16)) b left join ");
		// sql.append(" (select	b.fnumber,sum(a.fqty) as qty from t_st_hrstorebill a, t_bd_material b  ");
		// sql.append(" where a.fmaterialid=b.fid and  b.fnumber in (select fid from t_st_ceshi16)");
		// sql.append(" and a.fstorageorgunitid=(select fid from  t_org_storage where fnumber='1110') and a.fwarehouseid=(select fid from t_db_warehouse where fnumber='5040')");
		// sql.append(" group by b.fnumber	 ) a on b.fnumber=a.fnumber  ");
		//
		// genFromSql("select * from (select d.id as districtid  ,d.name as district ,c.id as cityid, c.name as city ,p.id as provinceid,p.name as province from t_district d left join t_city c on c.id=d.cityid left join   t_province p on p.id=c.proid ) t",
		// "District");
	}

	public static void genFromSql(String sql, String className) throws Exception {
		_Class c = new CreateClass(getConnection()).getClassData(sql, className);
		model.put("pojoPackage", voPackage);
		model.put("c", c);
		genVo(c, true);
		genDao(c, true);
		genService(c, true);
		genController(c, true);
		genView(c, true);

	}

	public static void genFromTable(String[] tables) throws Exception {
		for (String table : tables) {
			_Class c = new CreateClass(getConnection()).getClassData(table);
			model.put("c", c);
			genPojo(c, true);
			genDao(c, true);
			genService(c, true);
			
			genController(c, true);
			genView(c, true);
			genFreeMarkerDirective(c, true);

		}
	}

	/**
	 * 生成controller层
	 * 
	 * @param c
	 * @param cover
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genController(_Class c, boolean cover) throws IOException, TemplateException {
		String controllerPath = basePath + "/src/main/java/" + basePackage.replace(".", "/") + "/" + controllerPackage.replace(".", "/") + "/";
		FreeMarkerUtils.makeFileByFile("template/java/controller.ftl", controllerPath + c.getClassName() + "Controller.java", config, model, cover);
	}

	/**
	 * 生成service层
	 * 
	 * @param c
	 * @param cover
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genService(_Class c, boolean cover) throws IOException, TemplateException {
		String servicePath = basePath + "/src/main/java/" + basePackage.replace(".", "/") + "/" + servicePackage.replace(".", "/") + "/";
		FreeMarkerUtils.makeFileByFile("template/java/service.ftl", servicePath + c.getClassName() + "Service.java", config, model, cover);
		FreeMarkerUtils.makeFileByFile("template/java/service_impl.ftl", servicePath + "impl/" + c.getClassName() + "ServiceImpl.java", config, model, cover);
	}

	/**
	 * 生成dao层
	 * 
	 * @param c
	 * @param cover
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genDao(_Class c, boolean cover) throws IOException, TemplateException {
		String daoPath = basePath + "/src/main/java/" + basePackage.replace(".", "/") + "/" + daoPackage.replace(".", "/") + "/";
		FreeMarkerUtils.makeFileByFile("template/java/dao.ftl", daoPath + c.getClassName() + "Dao.java", config, model, cover);
		FreeMarkerUtils.makeFileByFile("template/java/dao_impl.ftl", daoPath + "impl/" + c.getClassName() + "DaoImpl.java", config, model, cover);
	}

	/**
	 * 生成vo层
	 * 
	 * @param c
	 * @param cover
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genVo(_Class c, boolean cover) throws IOException, TemplateException {
		String pojoPath = basePath + "/src/main/java/" + basePackage.replace(".", "/") + "/" + voPackage.replace(".", "/") + "/";
		FreeMarkerUtils.makeFileByFile("template/java/vo.ftl", pojoPath + c.getClassName() + ".java", config, model, cover);
	}

	/**
	 * 生成pojo层
	 * 
	 * @param c
	 * @param cover
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genPojo(_Class c, boolean cover) throws IOException, TemplateException {
		String pojoPath = basePath + "/src/main/java/" + basePackage.replace(".", "/") + "/" + pojoPackage.replace(".", "/") + "/";
		FreeMarkerUtils.makeFileByFile("template/java/pojo.ftl", pojoPath + c.getClassName() + ".java", config, model, cover);
	}

	/**
	 * 生成freemarker指令
	 * 
	 * @param c
	 * @param cover
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genFreeMarkerDirective(_Class c, boolean cover) throws IOException, TemplateException {
		String directivePath = basePath + "/src/main/java/" + basePackage.replace(".", "/") + "/" + directivePackage.replace(".", "/") + "/";
		FreeMarkerUtils.makeFileByFile("template/java/directivebyId.ftl", directivePath + c.getClassName() + "Directive.java", config, model, cover);
		FreeMarkerDirectiveUtil.modifiedSpringXml(basePath + "/src/main/resources/spring/application-controller.xml", c.getClassName(), c.getClassName() + "Directive");
	}

	/**
	 * 生成view层
	 * 
	 * @param c
	 * @param cover
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void genView(_Class c, boolean cover) throws IOException, TemplateException {
		String viewPath = basePath + "/src/main/webapp/WEB-INF/" + viewPageage.replace(".", "/") + "/";
		FreeMarkerUtils.makeFileByFile("template/html/list.ftl", viewPath + c.getClassName().toLowerCase() + "/list.ftl", config, model, cover);
		FreeMarkerUtils.makeFileByFile("template/html/table.ftl", viewPath + c.getClassName().toLowerCase() + "/table.ftl", config, model, cover);
		if (c.getContainPrimaryKey())// 含有主键
		{
			FreeMarkerUtils.makeFileByFile("template/html/info.ftl", viewPath + c.getClassName().toLowerCase() + "/info.ftl", config, model, cover);
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(jdbcDriverClassName);
		return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPpassword);
	}
}
