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
                     	 <label class="col-sm-3 control-label">厂商：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" placeholder="请输入厂商" class="form-control"  value="<#if data??><#if data.name??>${data.name}</#if></#if>"> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
	   			        <#if RequestParameters["code"]??>  	    <div class="form-group">
                     	 <label class="col-sm-3 control-label">编码：</label>
                                <div class="col-sm-8">
                          		 <input type="text" name="code" placeholder="请输入编码" class="form-control" readonly="readonly" value="${RequestParameters["code"]!""}">
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                        </#if> 
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/factory/save.do','refreshFactoryList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
