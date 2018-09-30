<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysWxAccesstokenList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>公共号</th>
		<th>accesstoken</th>
		<th>更新时间</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx></td>
		<td>${a.accesstoken!""}</td>
		<td>${(a.updatetime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td> <a   onclick="javascript:MyFun.objOperate('${request.contextPath}/syswxaccesstoken/refresh.do?wxid=${a.wxid}','确定刷新','refreshSysWxAccesstokenList')">刷新</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SysWxAccesstokenList" page=page />
