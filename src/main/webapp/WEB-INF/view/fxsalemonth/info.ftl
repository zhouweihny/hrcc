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
                     	 <label class="col-sm-3 control-label">drugid：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="drugid" placeholder="请输入drugid" class="form-control"  value="<#if data??><#if data.drugid??>${data.drugid}</#if></#if>"> 
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
                     	 <label class="col-sm-3 control-label">xse：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="xse" placeholder="请输入xse" class="form-control"  value="<#if data??><#if data.xse??>${data.xse}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">cb：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="cb" placeholder="请输入cb" class="form-control"  value="<#if data??><#if data.cb??>${data.cb}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">ml：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="ml" placeholder="请输入ml" class="form-control"  value="<#if data??><#if data.ml??>${data.ml}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">mlv：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="mlv" placeholder="请输入mlv" class="form-control"  value="<#if data??><#if data.mlv??>${data.mlv}</#if></#if>"> 
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
                     	 <label class="col-sm-3 control-label">month：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="month" placeholder="请输入month" class="form-control"  value="<#if data??><#if data.month??>${(data.month?string("yyyy-MM-dd HH:mm"))!  
                                     }</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">pc：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="pc" placeholder="请输入pc" class="form-control"  value="<#if data??><#if data.pc??>${data.pc}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">xssl：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="xssl" placeholder="请输入xssl" class="form-control"  value="<#if data??><#if data.xssl??>${data.xssl}</#if></#if>"> 
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
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/fxsalemonth/save.do','refreshFxSaleMonthList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
