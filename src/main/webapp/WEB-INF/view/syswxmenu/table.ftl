<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysWxMenuList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>公共号</th>
		<th>类型</th>
		<th>名称</th>
		<th>url</th>
		<th>消息模板</th>
		<th>层级</th>
		<th>排序</th>
		<th>备注</th>
		<th>操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx></td>
		<td>${a.type!""}</td>
		<td>${a.name!""}</td>
		<td>${a.url!""}</td>
		<td><@_syswxmsg id="${a.wxmsgid!}" >${sysWxMsg.name!}</@_syswxmsg></td>
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx><#list a.path?split("/") as id>/<@_syswxmenu id="${id!}" >${sysWxMenu.name!}</@_syswxmenu></#list></td>
		<td>${a.sort!""}</td>
		<td>${a.remark!""}</td>
  		<td><#if a.type=="click"><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/syswxmenu/bandmsg.do?id=${a.id}','info-form')">绑定消息</a> </#if> <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/syswxmenu/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/syswxmenu/delete.do?id=${a.id}','refreshSysWxMenuList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SysWxMenuList" page=page />
