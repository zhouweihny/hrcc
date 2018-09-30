<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FxSaleFileList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FxSaleFileList','stype','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>','sfield','createtime');"    >导入时间</th>
		<th>文件名</th>
		<th>门店</th>
		<th>销售时间</th>
		<th>销售总数</th>
		<th>已导入</th>
		<th>错误数据</th>
		<th>处理状态</th>
		<th>原数据文件</th>
		<th>错误数据文件</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.name!""}</td>
		<td><@_store id=a.storeid userid=Session.COMPANY.id>${store.name!""}</@_store> </td>
   		<td><#if a.status==2><#if a.startdate ?? >${(a.startdate?string("yyyy-MM-dd"))!}至 ${(a.enddate?string("yyyy-MM-dd"))!}</#if></#if></td>
		<td>${a.num!""}</td>
		<td>${a.processed!""}</td>
		<td>${a.error!""}</td>
		<td><#if a.status==0>待处理</#if><#if a.status==1>处理中</#if><#if a.status==2>处理完成</#if></td>
		<td><a target="_blank" href="${request.contextPath}/fxsalefile/download/file/${a.id}.do">下载</a></td>
		<td><#if a.error!=0><a target="_blank" href="${request.contextPath}/fxsalefile/download/error/${a.id}.do">下载</a></#if></td>
  		<td></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FxSaleFileList" page=page />
