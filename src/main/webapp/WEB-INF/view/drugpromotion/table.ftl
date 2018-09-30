<#include "../tools/page.ftl"  />       
<#include "../tools/select.ftl"  />  
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="DrugPromotionList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('DrugPromotionList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th>编码</th>
		<th>品名</th>
		<th>规格</th>
		<th>厂商</th>
		<th>内容</th>
		<th>开始时间</th>
		<th>结束时间</th>
		<th>备注</th>
		<th>状态</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
	   <td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
	   <@_drug id=a.drugid >
	   <th>${drug.code}</th>
	   <th>${drug.name}</th>
	   <th>${drug.specifications}</th>
	   <th>${drug.factory}</th>
	   </@_drug>
		<td>${a.content!""}</td>
   		<td>${(a.startdate?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.enddate?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.remark!""}</td>
		<td><@getDictData code="drug.promotion.status" key=a.status?string  /></td>
  		<td><a href="javascript:;" onclick="sendPurchase('${a.id!""}');">选择客户</a> <#if a.status==0><a   onclick="javascript:MyFun.objOperate('${request.contextPath}/drugpromotion/save.do?id=${a.id}&status=1','确定启用?','refreshDrugPromotionList')">启用</a>  <#else><a   onclick="javascript:MyFun.objOperate('${request.contextPath}/drugpromotion/save.do?id=${a.id}&status=0','确定停用?','refreshDrugPromotionList')">停用</a>   </#if></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="DrugPromotionList" page=page />
