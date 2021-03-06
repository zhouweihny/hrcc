<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>药品目录</th>
		<th>编码</th>
		<th>通用名</th>
		<th>名称</th>
		<th>规格</th>
		<th>单位</th>
		<th>剂型</th>
		<th>厂商</th>
		<th>操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_catalog id=a.catalogid >${catalog.name} </@> </td>
		<td>${a.code!""}</td>
		<td>${a.common!""}</td>
		<td>${a.name!""}</td>
		<td>${a.specifications!""}</td>
		<td>${a.unit!""}</td>
		<td>${a.dosageform!""}</td>
		<td class="J_fac_keywords" data-code='${a.id}'>${a.factory!""}</td>
		<td><a onclick="add('${a.id}')" >新增</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugList" page=page />
