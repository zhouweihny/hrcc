<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DiseaseAnalysisVoList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th  class="<#if sfield=="ptree" && stype=="desc">sorting_desc<#elseif sfield =="ptree" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DiseaseAnalysisVoList','stype','<#if sfield=="ptree" && stype=="desc">asc<#else>desc</#if>','sfield','ptree');"    >疾病分类</th>
		<th>疾病</th>
		<th class="<#if sfield=="frequency" && stype=="desc">sorting_desc<#elseif sfield =="frequency" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DiseaseAnalysisVoList','stype','<#if sfield=="frequency" && stype=="desc">asc<#else>desc</#if>','sfield','frequency');"    >频次</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page  as a>
	<tr class="gradeX">
		<td>${a.ptree!""}</td>
		<td>${a.disease!""}</td>
		<td>${a.frequency!""}</td>
  		<td></td>
    </tr>
    </#list> 
    </tbody>
    </table>
