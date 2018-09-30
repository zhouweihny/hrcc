<#include "../tools/page.ftl"  />       
<#include "../tools/select.ftl"  />      
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="SysWxUserList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>公共号</th>
		<th>头像</th>
		<th>昵称</th>
		<th>性别</th>
		<th>所在地</th>
	    <th>关注</th>
		<th>关注时间</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx></td>
		<td><img class="img-circle" style="width: 40px; height: 40px;" alt="image" src="${a.headimgurl!""}"></td>
		<td>${a.nickname!""}</td>
		<td><@getDictData code="common.sex" key="${a.sex}"/></td>
		<td>${a.country!""}${a.province!""}${a.city!""}</td>
		<td>${(a.subscribe?string("已关注","已取消关注"))!}</td>   
   		<td>${(a.subscribeTime?string("yyyy-MM-dd HH:mm"))!}</td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="SysWxUserList" page=page />
