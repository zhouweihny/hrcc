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
                     	 <label class="col-sm-3 control-label">通用名词组：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="words" placeholder="请输入通用名词组" class="form-control"  value="<#if data??><#if data.words??>${data.words}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                       </div>
	   				   <div class="form-group">
                     	 <label class="col-sm-3 control-label">通用名：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="word" placeholder="请输入通用名" class="form-control"  value="<#if data??><#if data.word??>${data.word}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                       </div>
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/comwords/save.do','refreshComwordsList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
