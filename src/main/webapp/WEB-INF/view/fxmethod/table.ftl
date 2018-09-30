<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxMethodList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>名称</th>
		<th>类</th>
		<th>方法</th>
		<th>备注</th>						
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.name!""}</td>
		<td>${a.classn!""}</td>
		<td>${a.methodn!""}</td>
		<td>${a.remark!""}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxMethodList" page=page />
