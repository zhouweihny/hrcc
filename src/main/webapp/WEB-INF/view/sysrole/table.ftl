<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysRoleList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>名称</th>
		<th>备注</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.name!""}</td>
		<td>${a.remark!""}</td>
  		<td>
  			<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/sysrole/menutree.do?roleid=${a.id}','info-form')">绑定权限</a>
  			<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/sysrole/byid.do?id=${a.id}','info-form')">修改</a> 
  			<a onclick="javascript:MyFun.objDel('${request.contextPath}/sysrole/delete.do?id=${a.id}','refreshSysRoleList')">删除</a>
  		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SysRoleList" page=page />
