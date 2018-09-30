<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysMenuList" aria-describedby="DataTables_Table_0_info">
  <thead>
   <tr role="row">
	<th>名称</th>
	<th>url</th>
	<th>上级菜单</th>
	<th>排序</th>
  	<th>层级</th>
  	<th>图标</th>
  	<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
  </thead>
  <tbody>
  <#list page.data as a>
  <tr class="gradeX">
	<td>${a.name!""}</td>
	<td>${a.url!""}</td>
	<td><@_sysmenu id="${a.parentid!}" >${sysMenu.name!}</@_sysmenu></td>
	<td>${a.sort!""}</td>
	<td><#list a.path?split("/") as id>/<@_sysmenu id="${id!}" >${sysMenu.name!}</@_sysmenu></#list></td>
	<td><i class="${a.icon!'fa fa-desktop'}"></i></td>
  	<td><a data-toggle="modal" data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/sysmenu/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/sysmenu/delete.do?id=${a.id}','refreshSysMenuList')">删除</a></td>
  </tr>
  </#list> 
 </tbody>
</table>
<@MyPage tableid="SysMenuList" page=page />
