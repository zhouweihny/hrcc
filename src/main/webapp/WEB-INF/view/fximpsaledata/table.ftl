<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxImpsaledataList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('PlanList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >创建时间</th>
		<th>门店</th>
		<th>季度</th>
		<th>销售文件</th>
		<th>库存文件</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
		<td><@_store id=a.storeid userid=Session.COMPANY.id>${store.name!""}</@_store> </td>
		<td>${a.imptype!""}</td>
		<td>${a.name!""}( <a href="${request.contextPath}/fximpsaledata/lists.do?planid=${a.id!''}" >${a.num!""}</a> )
	</td>
		<td>
			<#if a.extend?? && a.extend!=''>
			<@_fximpfilename id=a.extend>
			  ${fxImpfilename.name}(<a href="${request.contextPath}/fximpsaledata/storeslists.do?salefileid=${a.id!''}">${fxImpfilename.num}</a>)
			</@_fximpfilename>
			<#else>
			<a href="javascript:;" onclick="impStore(this, '${a.id!""}', '${a.storeid!""}');" >导入</a>
			</#if>
		</td>
  		<td>
  			<a href="javascript:;" onclick="showAnalysis('${a.id!""}');" >分析统计</a>
  			<a href="javascript:;" onclick="recalculat('${a.id!""}', '${a.storeid!""}');" class="m-l-xs">重算</a>
  		</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxImpsaledataList" page=page />
