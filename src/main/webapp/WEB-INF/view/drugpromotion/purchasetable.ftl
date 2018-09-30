<#include "../tools/page.ftl"  />       
<#include "../tools/select.ftl"  />  
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugPromotionList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th>供应商</th>
		<th>品名</th>
		<th>规格</th>
		<th>厂商</th>
		<th>内容</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
	   <td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
       <td><@_user  id=a.supplierid>${user.company}</@_user></td>
	   <@_drug id=a.drugid >
	   <td>${drug.name}</td>
	   <td>${drug.specifications}</td>
	   <td>${drug.factory}</td>
	   </@_drug>
		<td>${a.content!""}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugPromotionList" page=page />
