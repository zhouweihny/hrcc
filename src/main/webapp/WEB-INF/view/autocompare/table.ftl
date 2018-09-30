<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="AutocompareList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>对码项目</th>
		<th>药品目录</th>
		<th class="<#if sfield=="startdate" && stype=="desc">sorting_desc<#elseif sfield =="startdate" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('AutocompareList','stype','<#if sfield=="startdate" && stype=="desc">asc<#else>desc</#if>','sfield','startdate');"    >开始时间</th>
		<th class="<#if sfield=="enddate" && stype=="desc">sorting_desc<#elseif sfield =="enddate" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('AutocompareList','stype','<#if sfield=="enddate" && stype=="desc">asc<#else>desc</#if>','sfield','enddate');"    >完成时间</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_space id=a.spaceid >${space.name} </@></td>
		<td><@_catalog id=a.catalogid >${catalog.name} </@></td>
   		<td>${(a.startdate?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.enddate?string("yyyy-MM-dd HH:mm"))!}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="AutocompareList" page=page />
