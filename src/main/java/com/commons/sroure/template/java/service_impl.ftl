package ${basePackage}.${servicePackage}.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.sql.SqlContext;
import ${basePackage}.${servicePackage}.${c.className}Service;
import ${basePackage}.${daoPackage}.${c.className}Dao;
import ${basePackage}.${pojoPackage}.${c.className};
/**
 * 
 * @author Du.Jun
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ${c.className}ServiceImpl implements ${c.className}Service {

	@Autowired
	private ${c.className}Dao ${c.className?uncap_first}Dao;

	public void save(${c.className} entity) throws Exception {
		${c.className?uncap_first}Dao.save(entity);
	}

	public void save(List<${c.className}> entities) throws Exception {
		${c.className?uncap_first}Dao.save(entities);
	}

	public void update(${c.className} entity) throws Exception {
		${c.className?uncap_first}Dao.update(entity);
	}

	public void update(${c.className} newEntity, ${c.className} oldEntity) throws Exception {
		${c.className?uncap_first}Dao.update(newEntity, oldEntity);
	}

	public void delete(${c.className} entity) throws Exception {
		${c.className?uncap_first}Dao.delete(entity);
	}

	public ${c.className} findObject(${c.className} entity) throws Exception {
		return ${c.className?uncap_first}Dao.findObject(entity);
	}

	public List<${c.className}> findList(${c.className} entity) throws Exception {
		return ${c.className?uncap_first}Dao.findList(entity);
	}

	public PageResult<${c.className}> findList(Map<String, Object> params, Page page) throws Exception {
		SqlContext sqlContext = new SqlContext();
		StringBuilder sql = new StringBuilder();
		List<Object> ps = new ArrayList<Object>();
		<#if c.tableName??> 
		sql.append("select * from ${c.tableName} where 1=1 ");
		<#else>
		sql.append("${c.sql}");
		</#if>
		if (params.containsKey("stype") && params.containsKey("sfield")) {
			sql.append(" order by ").append(params.get("sfield")).append("  ").append(params.get("stype"));
		}
		sqlContext.setSql(sql.toString());
		sqlContext.setParams(ps.toArray());
		return ${c.className?uncap_first}Dao.findList(sqlContext, page, ${c.className}.class);
	}

}
