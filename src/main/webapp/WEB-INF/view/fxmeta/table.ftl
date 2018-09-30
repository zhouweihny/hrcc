<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxMetaList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th style="width:20px"></th>
		<th>编码</th>
		<th>名称</th>
		<th>备注</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td class="zzz" data-id="${a.id}" ><i class="fa fa-plus-circle"></i> </td>
		<td>${a.code!""}</td>
		<td>${a.name!""}</td>
		<td>${a.remark!""}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxmeta/byid.do?id=${a.id}','info-form')">修改</a>
  		 	<a onclick="javascript:MyFun.objDel('${request.contextPath}/fxmeta/delete.do?id=${a.id}','refreshFxMetaList')">删除</a>
  			<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/fxmetadatas/byid.do?metaid=${a.id}','info-form')">新增参数</a>
  		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxMetaList" page=page />
