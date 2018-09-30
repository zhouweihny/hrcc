<#include "../tools/page.ftl"  />    
<#include "../tools/select.ftl"  />        
<#assign sfield="">        
<#if  RequestParameters["sfield"]??><#assign sfield=RequestParameters["sfield"]></#if>       
<#if  RequestParameters["stype"]?? ><#assign stype=RequestParameters["stype"]></#if>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="CmsContentList" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>公共号</th>
		<th>分类</th>
		<th>标题</th>
		<th>作者</th>
		<th>创建人</th>
	    <th>状态</th>
	    <th>发布人</th>
	    <th class="<#if sfield=="createtime" && stype=="desc">sorting_desc<#elseif sfield =="createtime" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.ajaxOrderTable('CmsContentList','createtime','<#if sfield=="createtime" && stype=="desc">asc<#else>desc</#if>');">创建时间</th>
	    <th class="<#if sfield=="publishdate" && stype=="desc">sorting_desc<#elseif sfield =="publishdate" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.ajaxOrderTable('CmsContentList','publishdate','<#if sfield=="publishdate" && stype=="desc">asc<#else>desc</#if>');">发布时间</th>
	    <th class="<#if sfield=="topdate" && stype=="desc">sorting_desc<#elseif sfield =="topdate" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.ajaxOrderTable('CmsContentList','topdate','<#if sfield=="topdate" && stype=="desc">asc<#else>desc</#if>');" >置顶时间</th>
        <th class="<#if sfield=="clicks" && stype=="desc">sorting_desc<#elseif sfield =="clicks" && stype=="asc">sorting_asc<#else>sorting</#if>" aria-controls="DataTables_Table_0" rowspan="1" colspan="1"   onclick="javascript:MyFun.ajaxOrderTable('CmsContentList','clicks','<#if sfield=="clicks" && stype=="desc">asc<#else>desc</#if>');" >点击数</th>
		<th>操作</th>
	</tr>
	</thead>
 	<tbody>
	<#list  page.data as a>
	<tr class="gradeX">
		<td><@_syswx id="${a.wxid!}" >${sysWx.name!}</@_syswx></td>
		<td><@_cmscategory id="${a.categoryid!}" >${cmsCategory.name!}</@_cmscategory></td>
		<td>${a.title!""}</td>
		<td>${a.author!""}</td>
		<td><@_user id="${a.createuserid!}" >${user.username!}</@_user></td>
		<td><@getDictData code="cms.status" key="${a.status}" /></td>
		<td><@_user id="${a.publishuserid!}" >${user.username!}</@_user></td>
		<td>${(a.createtime?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.publishdate?string("yyyy-MM-dd HH:mm"))!}</td>
   		<td>${(a.topdate?string("yyyy-MM-dd HH:mm"))!}</td>
		<td>${a.clicks!""}</td>
  		<td><a data-toggle="modal" data-backdrop="static" data-target="#info-formS" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/cmscontent/contentEidt.do.do?id=${a.id}','info-formS')">修改</a> <a   onclick="javascript:MyFun.objDel('${request.contextPath}/cmscontent/delete.do?id=${a.id}','refreshCmsContentList')">删除</a> <a href="${request.contextPath}/cmscontent/preview/${a.id}.do" target="_blank">预览</a> <a onclick="javascript:MyFun.objOperate('${request.contextPath}/cmscontent/release.do?id=${a.id}','确定发布?','refreshCmsContentList')"" target="_blank">发布</a>   <a onclick="javascript:MyFun.objOperate('${request.contextPath}/cmscontent/top.do?id=${a.id}','确定置顶?','refreshCmsContentList')"" target="_blank">置顶</a>  <a onclick="javascript:MyFun.objOperate('${request.contextPath}/cmscontent/canceltop.do?id=${a.id}','确定取消置顶?','refreshCmsContentList')"" target="_blank">取消置顶</a>  <a onclick="javascript:MyFun.objOperate('${request.contextPath}/cmscontent/cancel.do?id=${a.id}','确定取消发布?','refreshCmsContentList')"" target="_blank">取消</a> <a href="javascript:;" data-clipboard-text='${basePath}/wx/c/content/${a.id}.do' title="${basePath}/wx/c/content/${a.id}.do" class="zuiclipboard">复制链接</a></td>
    </tr>
    </#list> 
    </tbody>
    </table>
<@MyPage tableid="CmsContentList" page=page />

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