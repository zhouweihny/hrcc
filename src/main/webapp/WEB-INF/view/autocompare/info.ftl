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
                     	 <label class="col-sm-3 control-label">对码项目：</label>
                                <div class="col-sm-8">
                                    <select name="spaceid" id="spaceid" class="form-control input-sm">
                                    </select>
                                </div>
                            </div>
	   				    <div class="form-group">
                     	 <label class="col-sm-3 control-label">药品目录：</label>
                                <div class="col-sm-8">
                                    <select name="catalogid" id="catalogid" class="form-control input-sm">
                                    </select>
                                </div>
                            </div>
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/space/autocompare.do','refreshAutocompareList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>

<textarea class="fn-hide" id="T_option">
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_options">
{{#each this}}
<option value="{{catalogid}}">{{name}}</option>
{{/each}}
</textarea>

<script>

$(function(){
  getSpaces();
})


function getSpaces(){
    //查询所有工作空间
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/space/spaces.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#spaceid").temp($("#T_option").val(), json.data);

                    $("#spaceid").off("change").on("change", function(){
                        getCatalogs($(this).val());
                    })

                    getCatalogs(json.data[0].id);
                }else{
                    MyFun.to.i(json.message || "暂无工作空间");
                    unBlock(".wrapper");
                }
            }else{
                MyFun.to.e(json.message || "查询所有工作空间失败");
                unBlock(".wrapper");
            }
        }
    })
}

function getCatalogs(spaceid){
    //查询工作空间下的普通目录
    $.PostJson("${request.contextPath}/space/catalogs.do", "spaceid="+spaceid, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#catalogid").temp($("#T_options").val(), json.data);
                }else{
                    MyFun.to.i(json.message || "暂无药品目录");
                    unBlock(".wrapper");
                }
            }else{
                MyFun.to.e(json.message || "查询工作空间下的普通目录失败");
            }
        }
        unBlock(".wrapper");
    })
}
</script>