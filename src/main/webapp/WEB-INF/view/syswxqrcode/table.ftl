<#include "../tools/page.ftl"  /> 
<#include "../tools/select.ftl"  />        
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysWxQrcodeList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
	    <th>公共号</th>
	    <th>scenestr</th>
	    <th>二维码地址</th>
	    <th>是否使用</th>
	    <th>备注</th>
	    <th>创建时间</th>
		<th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx></td>
		<td>${a.scenestr!""}</td>
		<td>https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${a.ticket!""}</td>
		<td><@getDictData   code="common.boolean"  key="${a.used?string('true', 'false')}" /></td>
		<td>${a.remark!""}</td>
   		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
  		<td>
  			<a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/syswxqrcode/byid.do?id=${a.id}','info-form')">修改</a> 
  		    <a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/syswxqrcode/banding.do?id=${a.id}','info-form')">分配业务员</a>
  		 </td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SysWxQrcodeList" page=page />
