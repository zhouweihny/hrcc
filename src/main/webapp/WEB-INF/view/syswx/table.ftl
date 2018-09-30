<#include "../tools/page.ftl"  />   
<#include "../tools/select.ftl"  />            
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysWxList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>微信号</th>
		<th>公共号名称</th>
		<th>应用ID</th>
		<th>应用密钥</th>
		<th>推广</th>
		<th>备注</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td>${a.code!""}</td>
		<td>${a.name!""}</td>
		<td>${a.appid!""}</td>
		<td>${a.appsecret!""}</td>
		<td><@getDictData   code="common.boolean"  key="${a.ispromotion?string('true', 'false')}" /></td>
		<td>${a.remark!""}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/syswx/bandmsg.do?id=${a.id}','info-form')">欢迎消息</a> <a onclick="javascript:MyFun.objOperate('${request.contextPath}/syswx/syncmenu.do?wxid=${a.id}','确定同步?')">同步菜单</a> <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/syswx/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/syswx/delete.do?id=${a.id}','refreshSysWxList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SysWxList" page=page />
