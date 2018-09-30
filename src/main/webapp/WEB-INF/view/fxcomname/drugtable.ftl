<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>名称</th>
		<th>通用名</th>
		<th>规格</th>
		<th>单位</th>
		<th>厂商</th>
	    <th>操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td class="J_fac_keywords">${a.name!""}</td>
		<td><#if a.comnameid ??><@_fxcomname id="${a.comnameid!''}">${fxComname.name!''}</@_fxcomname></#if></td>
		<td>${a.specifications!""}</td>
		<td>${a.unit!""}</td>
		<td>${a.factory!""}</td>
		<td>
			<a href="javascript:;" onclick="bindComoname(this);" data-id='${a.id!""}' data-code='${a.code!""}' data-name='${a.name!""}' data-specifications='${a.specifications!""}' data-unit='${a.unit!""}' data-dosageform='${a.dosageform!""}' data-factory='${a.factory!""}'>绑定</a>
		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugList" page=page />
