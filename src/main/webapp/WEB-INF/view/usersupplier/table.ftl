<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="UserSupplierList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>erp编码</th>
	    <th>erp名称</th>
		<th>手机号</th>
		<th>平台公司名称</th>
		<th>是否常用</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
	  	<td>${a.code!""}</td>
  		<td>${a.name!""}</td>
	  	<td>${a.mobile!""}</td>
		<td><#if (a.supplierid!"")!=""><@_user id=a.supplierid>${user.company!""}</@_user></#if></td>
		<td><#if a.used>是<#else>否</#if> </th>
  		<td><a  data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/usersupplier/byid.do?id=${a.id}','info-form')">修改</a> <a onclick="javascript:MyFun.objDel('${request.contextPath}/usersupplier/delete.do?id=${a.id}','refreshUserSupplierList')">删除</a>  <#if (a.supplierid!"")!="" ><#if a.used><a onclick="javascript:MyFun.objOperate('${request.contextPath}/usersupplier/save.do?id=${a.id}&used=0','确定取消常用?','refreshUserSupplierList')">取消常用</a><#else><a onclick="javascript:MyFun.objOperate('${request.contextPath}/usersupplier/save.do?id=${a.id}&used=1','确定设置常用?','refreshUserSupplierList')">设置常用</a></#if></#if></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="UserSupplierList" page=page />
