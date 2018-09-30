<#include "../tools/select.ftl"  /> 
<div class="modal-dialog">
   <div class="modal-content">
   <form class="form-horizontal J_judgeChage" id="save_form">
   <div class="ibox float-e-margins">
      <div class="ibox-title">
      <h5><#if data??>编辑<#else>新增</#if></h5>
      <div class="ibox-tools">
     	 <a class="close-link"  data-dismiss="modal" ><i class="fa fa-times"></i></a>
      </div>
    </div>
    <div class="ibox-content">
	<input  type="hidden" name="id" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
	<div class="form-group">
      <label class="col-sm-3 control-label">菜单名：</label>
      <div class="col-sm-8">
      <input type="text" name="name" placeholder="请输入菜单名" class="form-control"  value="<#if data??><#if data.name??>${data.name}</#if></#if>"> 
      <span class="help-block m-b-none"></span>
      </div>
   	</div>
	<div class="form-group">
      <label class="col-sm-3 control-label">url：</label>
      <div class="col-sm-8">
        <input type="text" name="url" placeholder="请输入url" class="form-control"  value="<#if data??><#if data.url??>${data.url}</#if></#if>"> 
        <span class="help-block m-b-none"></span>
      </div>
     </div>
     <div class="form-group">
      <label class="col-sm-3 control-label">字体图标：</label>
      <div class="col-sm-8">
        <input type="text" name="icon" placeholder="请输入字体图标" class="form-control"  value="<#if data??><#if data.icon??>${data.icon}</#if></#if>"> 
        <span class="help-block m-b-none"></span>
      </div>
     </div>
	 <div class="form-group">
      <label class="col-sm-3 control-label">上级：</label>
      <div class="col-sm-8">
      <#if data??>
      <@menuSelect  id="parentid" parent=""  headKey="" headText="无" defaultValue="${data.parentid!}"/> 
      <#else>
      <@menuSelect  id="parentid" parent=""  headKey="" headText="无" defaultValue=""/> 
      </#if>
      </div>
      </div>
	  <div class="form-group">
      	 <label class="col-sm-3 control-label">排序：</label>
         <div class="col-sm-8">
         <input type="text" name="sort" placeholder="请输入sort" class="form-control"  value="<#if data??><#if data.sort??>${data.sort}</#if></#if>"> 
         <span class="help-block m-b-none"></span>
         </div>
      </div>
     </form>
 	</div>
   </div>
   <div class="modal-footer">
     <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
     <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/sysmenu/save.do','refreshSysMenuList')">保存</button>
   </div>
   </form>
  </div>
</div>
