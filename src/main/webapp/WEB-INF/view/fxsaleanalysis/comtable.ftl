<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
	   	<th>序号</th>
		<th>名称</th>
		<th class="<#if sfield=="pgs" && stype=="desc">sorting_desc<#elseif sfield =="pgs" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="pgs" && stype=="desc">asc<#else>desc</#if>','sfield','pgs');" >品规数</th>
		<th class="<#if sfield=="xse" && stype=="desc">sorting_desc<#elseif sfield =="xse" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="xse" && stype=="desc">asc<#else>desc</#if>','sfield','xse');"  >销售额</th>
		<th class="<#if sfield=="xszb" && stype=="desc">sorting_desc<#elseif sfield =="xszb" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="xszb" && stype=="desc">asc<#else>desc</#if>','sfield','xszb');" >销售占比</th>
		<th class="<#if sfield=="xssl" && stype=="desc">sorting_desc<#elseif sfield =="xssl" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="xssl" && stype=="desc">asc<#else>desc</#if>','sfield','xssl');" >销售数量</th>
		<th class="<#if sfield=="pjsj" && stype=="desc">sorting_desc<#elseif sfield =="pjsj" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="pjsj" && stype=="desc">asc<#else>desc</#if>','sfield','pjsj');" >平均售价</th>
		<th class="<#if sfield=="ml" && stype=="desc">sorting_desc<#elseif sfield =="ml" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="ml" && stype=="desc">asc<#else>desc</#if>','sfield','ml');" >毛利</th>
		<th class="<#if sfield=="mlv" && stype=="desc">sorting_desc<#elseif sfield =="mlv" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugList','stype','<#if sfield=="mlv" && stype=="desc">asc<#else>desc</#if>','sfield','mlv');" >毛利率</th>
	</tr>
	</thead>
 	<tbody>
	<#list   data as a>
	<tr class="gradeX">
		<td>${a_index+1}</td>
		<td >${a.name!""}</td>
		<td style="text-align: right;">${a.pgs!""}</td>
		<td style="text-align: right;">${a.xse?string("0.00")}</td>
		<td style="text-align: right;">${(a.xszb/100)?string("0.00")}%</td>
		<td style="text-align: right;">${a.xssl}</td>
		<td style="text-align: right;">${a.pjsj?string("0.00")}</td>
		<td style="text-align: right;">${a.ml?string("0.00")}</td>
		<td style="text-align: right;">${a.mlv?string("0.00")}% </td>
    </tr>
    </#list> 
    </tbody>
    </table>
