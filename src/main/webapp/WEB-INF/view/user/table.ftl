<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="UserList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>用户名</th>
		<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")>
		<th>erp用户名</th>
		</#if>
		<th>邮箱</th>
		<th>手机</th>
		<th>qq</th>
		<th>备注</th>
		<th>姓名</th>
		<th>角色</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.username!""}</td>
		<#if Session.COMPANY.roleids?seq_contains("A6B9CC86F7F24156A2CC50895312CC03")>
		<th>${a.erpusername!""}</th>
		</#if>
		<td>${a.email!""}</td>
		<td>${a.mobile!""}</td>
		<td>${a.qq!""}</td>
		<td>${a.remark!""}</td>
		<td>${a.name!""}</td>
		<td><@_sysrole id=a.roleid>${sysRole.name!""}</@_sysrole></td>
  		<td>
  			<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/user/byid.do?id=${a.id}','info-form')">修改</a>
  			<a onclick="javascript:MyFun.objDel('${request.contextPath}/user/delete.do?id=${a.id}','refreshUserList')">删除</a>
  			<a onclick="bindStores('${a.id}');">绑定门店</a>
  		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="UserList" page=page />
