<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="FactoryList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th onclick="javascript:zuiCheckAll(this);" class="J_autoCom_wrap">
			<div class="check_wrap">
	        <i class="fa fa-square-o" aria-hidden="true"></i>
	        <i class="fa fa-check-square-o fn-hide" aria-hidden="true"></i>
	    </div>
	    <label>全 选</label>
		</th>
		<th class="<#if sfield=="name" && stype=="desc">sorting_desc<#elseif sfield =="name" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FactoryList','stype','<#if sfield=="name" && stype=="desc">asc<#else>desc</#if>','sfield','name');"    >厂商名称</th>
		<th class="<#if sfield=="code" && stype=="desc">sorting_desc<#elseif sfield =="code" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.searchParams('FactoryList','stype','<#if sfield=="code" && stype=="desc">asc<#else>desc</#if>','sfield','code');"    >编码</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td onclick="zuiCheckSelf(this);" class="J_autoCom_wrap">
			<div class="check_wrap">
	        <i class="fa fa-square-o" aria-hidden="true" data-id='${a.id!""}' ></i>
	        <i class="fa fa-check-square-o fn-hide" aria-hidden="true"></i>
	    </div>
		</td>
		<td>${a.name!""}</td>
		<td>${a.code!""}</td>
  		<td> <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/factory/byid.do?code=${a.code!""}','info-form')">新增</a> <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/factory/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/factory/delete.do?id=${a.id}','refreshFactoryList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="FactoryList" page=page />
