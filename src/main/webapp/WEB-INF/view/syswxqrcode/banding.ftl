<#include "../tools/select.ftl"  /> 
<div class="modal-dialog">
    <div class="modal-content">
        <form class="J_judgeChage" id="save_form">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>分配业务员</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
				  	<input type="hidden" name="qrcodeid" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
                    <div class="form-group">
                        <div class="control-label col-md-12">
                            <label >业务员：</label>
                       		<select id="J_rep_select" class="form-control input-sm" style="width: 200px;" name="repid"></select>
                  		</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/repqrcode/save.do','refreshSysWxQrcodeList')">保存</button>
         	</div>
        </form>
    </div>
</div>

<script>
 <#if rep??>   var str = '<option value="${rep.id!""}" selected="selected">${rep.name!""}</option>';
    $('#J_rep_select').append(str);
 </#if>   
$('#J_rep_select').select2({
    ajax: {
        url: "${request.contextPath}/rep/reps.do",
        dataType: 'json',
        delay: 250,
        data: function(params) {
            return {
                name: params.term, // search term
                page: params.page
            };
        },
        processResults: function(data, params) {
            params.page = params.page || 1;
            var _cdata = data.data.data,
                // _cArr = [{"id": "-1", "text": "全部"}];
                _cArr = [];
            if(_cdata.length){
                for(var i=0, len=_cdata.length; i<len; i++){
                    var attr = {};
                    attr.id = _cdata[i].id;
                    attr.text = _cdata[i].name+"("+_cdata[i].mobileno+")";
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
    placeholder: "请输入业务员名称",
    allowClear: true,
    language: "zh-CN",
    dropdownAutoWidth: true
})
</script>