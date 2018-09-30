<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxDrugdataList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>分类</th>
		<th>类型</th>
		<th>类型值</th>
		<th>实际值</th>
		<th>提示语</th>
		<th>操作</th>
	</tr>
	</thead>
 	<tbody data-items='${items!""}'>
 	<#if page??>
 		<#list  page.data as a>
 		<tr class="gradeX">
 			<td>${a.treename1!""} / ${a.treename2!""} / ${a.treename3!""} <#if a.treename4?? && a.treename4!=''>/ ${a.treename4!""}</#if></td>
 			<td>${a.meatadataName!""}</td>
 			<td>${a.typevalue!""}</td>
 			<td>${a.factvalue!""}</td>
 			<td>${a.messagers!""}</td>
 			<td>
 				<a href="javascript:;" onclick="getDetail(this, '${a_index}');">详情</a>
 				<a href="javascript:;" onclick="modMetal(this, '${a.treeid!""}', '${a.metaid!""}');">修改配置</a>
 			</td>
 	    </tr>
 	    </#list>
 	</#if>
    </tbody>
    </table>
<#if page??> 
<@MyPage tableid="FxMetadataList" page=page />
</#if>