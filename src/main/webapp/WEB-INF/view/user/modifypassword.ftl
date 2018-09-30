        <div class="modal-dialog">
            <div class="modal-content">
                  <form class="form-horizontal J_judgeChage" id="save_form" autocomplete="off"  >
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>修改密码</h5>
                        <div class="ibox-tools">
                            <a class="close-link"  data-dismiss="modal" >
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
	 					  	<input  type="hidden" name="id" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
	   				   <#if (data.password!"")!="">
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">旧密码：</label>
                                <div class="col-sm-8">
                                    <input type="password" name="oldpassword" placeholder="请输入旧密码" class="form-control" autocomplete="off"   required > 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                     	 </#if>      
                         <div class="form-group">
                     	 <label class="col-sm-3 control-label">新密码：</label>
                                <div class="col-sm-8">
                                    <input type="password" name="newpassword1" placeholder="请输入新密码" class="form-control" autocomplete="off" required > 
                                    <span class="help-block m-b-none"></span>
                                </div>
                        </div>  
	   				      <div class="form-group">
                     	 <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-8">
                                    <input type="password" name="newpassword2" placeholder="请确认新密码" class="form-control" autocomplete="off"  required> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                        </div>  
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/user/modifypassword.do')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
