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
                     			<label class="col-sm-3 control-label">ERP供应商编码：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="code" placeholder="请输入ERP供应商编码" class="form-control"  value="<#if data??><#if data.code??> ${data.code}</#if></#if> "> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                        </div>
                        <div class="form-group">
                     			<label class="col-sm-3 control-label">ERP供应商名称：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" placeholder="ERP供应商名称" class="form-control"  value="<#if data??><#if data.name??> ${data.name}</#if></#if> "> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                        </div>
	   				    <div class="form-group">
                     			<label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="mobile" placeholder="请输入手机号" class="form-control"  value="<#if data??><#if data.mobile??> ${data.mobile}</#if></#if> "> 
                                    <span class="help-block m-b-none"></span>
                                </div>
                        </div>
               			<div class="form-group">
            				<label class="col-sm-3 control-label">平台供应商：</label>
           					 <div class="col-sm-8">
              				<select id="supplierid" name="supplierid" class="form-control " style="width: 360px;" ></select>
            				</div>
          				</div>
                        
                       </form>
                    </div>
                </div>
                  <div class="modal-footer">
                                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                            <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/usersupplier/save.do','refreshUserSupplierList')">保存</button>
             	</div>
          	 	</form>
            </div>
        </div>
<script>

var supplierid='<#if data??>${data.supplierid!""}</#if>';
var company ='<#if data??><#if (data.supplierid!"")!=""><@_user id=data.supplierid>${user.company!""}</@_user></#if></#if>';

$(function(){

$('#supplierid').select2({
    ajax: {
        url: "${request.contextPath}/usersupplier/suppliers.do",
        type : "POST",
        dataType: 'json',
        delay: 250,
        data: function(params) {
            return {
                keyword: params.term, // search term
                page: params.page
            };
        },
        processResults: function(data, params) {
            params.page = params.page || 1;
            var _cdata = data.data,
                // _cArr = [{"id": "-1", "text": "全部"}];
                _cArr = [];
            if(_cdata.length){
                for(var i=0, len=_cdata.length; i<len; i++){
                    var attr = {};
                    attr.id = _cdata[i].id;
                    attr.text = _cdata[i].company;
                    _cArr.push(attr);
                }
            }
            return {
                results: _cArr,
                pagination: {
                    more: (params.page * 30) < data.data.totalRows
                }
            };
        },
        cache: true
    },
    escapeMarkup: function(markup) {
        return markup;
    }, // let our custom formatter work
    minimumInputLength: 1,
    placeholder: "请输入供应商名称",
    allowClear: true,
    language: "zh-CN",
    dropdownAutoWidth: true
})

if(supplierid){
    $("#supplierid").append("<option value='"+supplierid+"' selected>"+company+"</option>");
}

})

</script>