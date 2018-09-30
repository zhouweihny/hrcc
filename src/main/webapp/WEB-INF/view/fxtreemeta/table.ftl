<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxTreeMetaList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>树节点</th>
		<th>报表</th>
		<th>方法</th>
		<th>门店</th>
		<th>userid</th>
		<th>作用域</th>
		<th>标准</th>
		<th>备注</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.treeid!""}</td>
		<td>${a.metaid!""}</td>
		<td>${a.methodid!""}</td>
		<td>${a.storeid!""}</td>
		<td>${a.userid!""}</td>
		<td>${a.scope!""}</td>
   		<td>${(a.standard?string("true","false"))!}</td>    		
		<td>${a.remark!""}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxtreemeta/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/fxtreemeta/delete.do?id=${a.id}','refreshFxTreeMetaList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxTreeMetaList" page=page />
