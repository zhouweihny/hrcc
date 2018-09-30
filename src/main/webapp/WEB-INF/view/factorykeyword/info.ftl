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
	 					  	<input  type="hidden" name="code" value="${code}">
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">关键字：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="word" placeholder="请输入关键字" class="form-control"  value=""> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                            </div>
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/factorykeyword/save.do','refreshFactoryKeywordList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
