<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="code" && stype=="desc">sorting_desc<#elseif sfield =="code" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="code" && stype=="desc">asc<#else>desc</#if>','sfield','code');"   >编码</th>
		<th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >名称</th>
		<th>通用名</th>
        <th>树节点</th>
		<th>规格</th>
		<th>单位</th>
		<th>厂商</th>
		<th>税率</th>
		<th>价格</th>
		<th>操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.code!""}</td>
		<td>${a.name!""}</td>
		<td><#if a.comnameid ??><@_fxcomname id="${a.comnameid}"><#if fxComname??>${fxComname.name!""}</#if></@_fxcomname></#if></td>
		<td><#if a.comnameid ??><@_fxcomnametree comnameid="${a.comnameid}"><#list treepaths as path>${path!""} <br></#list></@_fxcomnametree></#if></td>
		<td>${a.specifications!""}</td>
		<td>${a.unit!""}</td>
		<td>${a.factory!""}</td>
	    <td><#if a.taxrate!=0>${a.taxrate!""}</#if></td>
		<td>${a.price!""}</td>
		<td>
			<a href="javascript:;" onclick="bindComoname(this);" data-id='${a.id!""}' data-code='${a.code!""}' data-name='${a.name!""}' data-specifications='${a.specifications!""}' data-unit='${a.unit!""}' data-dosageform='${a.dosageform!""}' data-factory='${a.factory!""}'>通用名绑定</a>
		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugList" page=page />
