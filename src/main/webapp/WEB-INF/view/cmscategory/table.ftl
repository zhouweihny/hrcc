<#include "../tools/page.ftl"  />       
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="CmsCategoryList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>公共号</th>
		<th>名称</th>
		<th>排序</th>
		<th>操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx></td>
		<td>${a.name!""}</td>
		<td>${a.sort!""}</td>
  		<td><a data-toggle="modal"  data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/cmscategory/byid.do?id=${a.id}','info-form')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/cmscategory/delete.do?id=${a.id}','refreshCmsCategoryList')">删除</a> <a href="${request.contextPath}/cmscategory/preview/${a.id}.do" target="_blank">预览</a> <a href="javascript:;" data-clipboard-text='${basePath}/wx/c/category/${a.id}.do' title="${basePath}/wx/c/category/${a.id}.do" class="zuiclipboard">复制链接</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="CmsCategoryList" page=page />

<script>
$(function(){
	var client = new ZeroClipboard($(".zuiclipboard"),{
	    swfPath: "${request.contextPath}/js/plugins/zeroclipboard/ZeroClipboard.swf"
	});

	client.on( "ready", function( readyEvent ) {
		client.on( "aftercopy", function( event ) {
			toastr.clear();
			MyFun.to.s("已复制到剪切板: " + event.data["text/plain"] );
		});
	});
})
</script>