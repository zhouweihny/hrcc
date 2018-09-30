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
                     	 <label class="col-sm-3 control-label">code：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="code" placeholder="请输入code" class="form-control"  value="<#if data??><#if data.code??>${data.code}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">name：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" placeholder="请输入name" class="form-control"  value="<#if data??><#if data.name??>${data.name}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">disabled：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="disabled" placeholder="请输入disabled" class="form-control"  value="<#if data??><#if data.disabled??>${(data.disabled?string("true","false"))! 
                               		 }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">operatorid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="operatorid" placeholder="请输入operatorid" class="form-control"  value="<#if data??><#if data.operatorid??>${data.operatorid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">createtime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="createtime" placeholder="请输入createtime" class="form-control"  value="<#if data??><#if data.createtime??>${(data.createtime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">updatetime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="updatetime" placeholder="请输入updatetime" class="form-control"  value="<#if data??><#if data.updatetime??>${(data.updatetime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">storeid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="storeid" placeholder="请输入storeid" class="form-control"  value="<#if data??><#if data.storeid??>${data.storeid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">treeid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="treeid" placeholder="请输入treeid" class="form-control"  value="<#if data??><#if data.treeid??>${data.treeid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">begintime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="begintime" placeholder="请输入begintime" class="form-control"  value="<#if data??><#if data.begintime??>${(data.begintime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">endtime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="endtime" placeholder="请输入endtime" class="form-control"  value="<#if data??><#if data.endtime??>${(data.endtime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">writetime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="writetime" placeholder="请输入writetime" class="form-control"  value="<#if data??><#if data.writetime??>${(data.writetime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">readtime：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="readtime" placeholder="请输入readtime" class="form-control"  value="<#if data??><#if data.readtime??>${(data.readtime?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">userid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="userid" placeholder="请输入userid" class="form-control"  value="<#if data??><#if data.userid??>${data.userid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">trainerid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="trainerid" placeholder="请输入trainerid" class="form-control"  value="<#if data??><#if data.trainerid??>${data.trainerid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">clerkid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="clerkid" placeholder="请输入clerkid" class="form-control"  value="<#if data??><#if data.clerkid??>${data.clerkid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">jobid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="jobid" placeholder="请输入jobid" class="form-control"  value="<#if data??><#if data.jobid??>${data.jobid}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">remarks：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="remarks" placeholder="请输入remarks" class="form-control"  value="<#if data??><#if data.remarks??>${data.remarks}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/fxtreetask/save.do','refreshFxTreeTaskList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
