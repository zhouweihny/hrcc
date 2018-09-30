<#include "../tools/select.ftl"  /> 
        <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal J_judgeChage" id="save_form">
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
                     	 <label class="col-sm-3 control-label">微信号：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="code" placeholder="请输入微信号" class="form-control" required value="<#if data??><#if data.code??>${data.code}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" placeholder="请输入名称" class="form-control"  value="<#if data??><#if data.name??>${data.name}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">应用id：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="appid" placeholder="请输入应用id" class="form-control"  value="<#if data??><#if data.appid??>${data.appid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">应用密钥：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="appsecret" placeholder="请输入应用密钥" class="form-control"  value="<#if data??><#if data.appsecret??>${data.appsecret}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   			 
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">令牌：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="token" placeholder="请输入令牌" class="form-control"  value="<#if data??><#if data.token??>${data.token}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                            
                             <div class="form-group">
                     	 <label class="col-sm-3 control-label">域名：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="domain" placeholder="请输入域名" class="form-control"  value="<#if data??><#if data.domain??>${data.domain}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                            
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">消息加解密密钥：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="encodingaeskey" placeholder="请输入消息加解密密钥" class="form-control"  value="<#if data??><#if data.encodingaeskey??>${data.encodingaeskey}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                            
                            
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">消息加解密方式：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="encryptType" placeholder="请输入消息加解密方式" class="form-control"  value="<#if data??><#if data.encryptType??>${data.encryptType}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">微信jsdebug：</label>
                                <div class="col-sm-8">
                              	   <div class="checkbox i-checks" id="jsconfigdebugchecks">
                                        <label>
                                            <input type="checkbox"    <#if data??><#if data.jsconfigdebug>checked</#if></#if>  />  
                                             <i></i>
                                        </label>
                                        <input type="hidden" value="<#if data??><#if data.jsconfigdebug>${data.jsconfigdebug?string('true', 'false')}</#if></#if>"  id="jsconfigdebug" name ="jsconfigdebug"/> 
                                    </div>
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                               
                         <div class="form-group">
                          	 <label class="col-sm-3 control-label">是否推广：</label>
                                <div class="col-sm-8">
                          			 <div class="checkbox i-checks" id="ispromotionchecks">
                                        <label>
                                            <input type="checkbox"    <#if data??><#if data.ispromotion>checked</#if></#if>  />  
                                             <i></i>
                                        </label>
                                        <input type="hidden" value="<#if data??><#if data.ispromotion>${data.ispromotion?string('true', 'false')}</#if></#if>"  id="ispromotion" name ="ispromotion"/> 
                                    </div>
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                          
                          
                       
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="remark" placeholder="请输入备注" class="form-control"  value="<#if data??><#if data.remark??>${data.remark}</#if></#if>"> 
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

           <script>
     	   $('#ispromotionchecks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
 				 	$("#ispromotion").val("true");
		   }); 
     
      		$('#ispromotionchecks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
 				$("#ispromotion").val("false");
		   }); 
		   
		    $('#jsconfigdebugchecks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
 				 	$("#jsconfigdebug").val("true");
		   }); 
     
      		$('#jsconfigdebugchecks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
 				$("#jsconfigdebug").val("false");
		   }); 
 
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green' 
            });
             
    </script>
        
        