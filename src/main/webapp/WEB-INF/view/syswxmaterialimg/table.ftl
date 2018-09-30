<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysWxMaterialImgList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>公共号</th>
		<th>素材id</th>
		<th>本地路径</th>
		<th>微信服务器地址</th>
		<th>备注</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx></td>
		<td>${a.mediaid!""}</td>
		<td>${a.path!""}</td>
		<td>${a.url!""}</td>
		<td>${a.remark!""}</td>
  		<td><a   onclick="javascript:MyFun.objDel('${request.contextPath}/syswxmaterialimg/delete.do?id=${a.id}','refreshSysWxMaterialImgList')">删除</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SysWxMaterialImgList" page=page />
