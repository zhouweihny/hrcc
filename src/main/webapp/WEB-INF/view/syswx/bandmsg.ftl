<#include "../tools/select.ftl"  /> 
        <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal" id="save_form">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><#if data??>编辑<#else>新增</#if></h5>
                        <div class="ibox-tools">
                            <a class="close-link"  data-dismiss="modal" >
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
	 					  	<input  type="hidden" name="id" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">消息模板：</label>
                                <div class="col-sm-8">
                                    <@_syswxmsglist  wxid="${data.id}">
                                     	<#if data??>
                                    	 <@select id="wxmsgid" datas=sysWxMsgs  defaultValue="${data.wxmsgid!''}" key="id" text="name" />
                                    	 <#else>   
                                   		 <@select id="wxmsgid" datas=sysWxMsgs  defaultValue="" key="id" text="name" />
                               			</#if> 
                                    </@_syswxmsglist>
                                    <span class="help-block m-b-none"></span>
                                </div>
                        </div>
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/syswx/save.do','refreshSysWxList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>

</script>