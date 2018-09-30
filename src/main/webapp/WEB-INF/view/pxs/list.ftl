<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> - 数据表格</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<!-- Data Tables -->
<link href="${request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<!-- 全局js -->
<script src="${request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${request.contextPath}/js/jquery.form.js?v=1.0.0"></script>
<!-- 自定义js -->
<script src="${request.contextPath}/js/content.js?v=1.0.0"></script>
<script src="${request.contextPath}/js/my.js?v=1.0.0"></script>
<link href="${request.contextPath}/css/animate.css" rel="stylesheet">
<link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<link href="${request.contextPath}/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>

<style>

</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>培训师管理</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="zuiStoreId">培训师：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入培训师" name="test">
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-w-m btn-info" onclick="getDetail();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                        <a class="btn btn-primary" onclick="addForm();">新增</a>
                    </div>
                    <div id="PxsList" data-url="${request.contextPath}/pxs/table.do"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>培训师管理<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container form-horizontal" id="J_formwrap">
                        
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveForm();">保存</button>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_formwrap">
<div class="form-group">
    <label class="col-sm-3 control-label">名称：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" placeholder="输入培训师名称" name="dyname">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">联系电话：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" placeholder="输入联系电话" name="phone">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">状态：</label>
    <div class="col-sm-8">
        <select id="J_state" class="form-control">
            <option value="1">正常</option>
            <option value="2">冻结</option>
        </select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">备注：</label>
    <div class="col-sm-8">
        <input type="text" name="remark" placeholder="请输入备注" class="form-control"  value=""> 
    </div>
</div>
</textarea>

<script>

MyFun.ajaxRefreshTable=function(divid,url,callback){
    if(!url)
        url= $('#'+divid).data("url");
    url= MyFun.urlFun(url,"_",new Date().getTime());
    var data = "{";
    var str =url.split("?")[1] ;   
    var strs = str.split("&");   
    for(var i = 0; i < strs.length; i ++) {   
        data += "'"+strs[i].split("=")[0]+"'" + ":"+"'"+strs[i].split("=")[1]+"'"+",";
    }
    data += "}";
    data = eval('('+data+')');

    $('#'+divid).empty();
    createBlock(".wrapper");
    $.ajax({
        type: "post",
        url: url.split("?")[0],
        dataType: "json",
        data:data,
        success: function(data){

        },
        complete:function(XMLHttpRequest,status){ 
            $('#'+divid).append(XMLHttpRequest.responseText);    
            $('#'+divid).data("url",url);
            if(typeof callback === 'function')
                callback();
            unBlock(".wrapper");
        }
    });
};

var clickFlag = 0, 
    tempJson = {};

function refreshDrugList(){
    MyFun.ajaxRefreshTable("PxsList");
}

$(function(){
    getDetail();
})

function getDetail(){
    MyFun.search('J_formSearch','PxsList');
}

function addForm(){
    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $("#J_formwrap").html("");
        tempJson = {};
    })
    $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        $("#J_formwrap").html($("#T_formwrap").val());

        initBtns();
    })
}

function initBtns(){
    initDates();
}

function initDates(){
    $("#J_state").select2({
        placeholder: "请选择状态",
        allowClear: false,
        language: "zh-CN",
        dropdownAutoWidth: false
    });
    if(tempJson && tempJson.id){
        var _J_formwrapObj = $("#J_formwrap");
        _J_formwrapObj.find("input[name='dyname']").val(tempJson.dyname);
        _J_formwrapObj.find("input[name='phone']").val(tempJson.phone);
        _J_formwrapObj.find("input[name='remark']").val(tempJson.remark);

        $("#J_state").val(tempJson.state).trigger('change');
    }
}

function saveForm(){
    var state = $("#J_state").val(),
        _J_formwrapObj = $("#J_formwrap"),
        dyname = _J_formwrapObj.find("input[name='dyname']").val(),
        phone = _J_formwrapObj.find("input[name='phone']").val(),
        remark = _J_formwrapObj.find("input[name='remark']").val();

    if(!dyname){
        MyFun.to.i("请输入培训师名称");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;

    var cid = '';
    if(tempJson && tempJson.id){
        cid = tempJson.id;
    }
    var str = "id="+cid+"&name="+dyname+"&phone="+phone+"&remark="+remark+"&status="+state;

    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/pxs/save.do", str, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "操作成功");

                $("#info-form").modal('hide');
                getDetail();
            }else{
                MyFun.to.e(json.message || "操作失败");
            }
        }
        unBlock(".modal-dialog");
        clickFlag = 0;
    })
}

function modFormdata(obj, id){
    var _J_formwrapObj = $("#J_formwrap");
    _J_formwrapObj.find("input[name='dyname']").val(tempJson.dyname);
    _J_formwrapObj.find("input[name='phone']").val(tempJson.phone);
    _J_formwrapObj.find("input[name='remark']").val(tempJson.remark);

    $("#J_state").val(tempJson.state).trigger('change');


    var _this = $(obj);
    tempJson = {
        id: id,
        state: _this.data("state"),
        dyname: _this.data("dyname"),
        phone: _this.data("phone"),
        remark: _this.data("remark")
    }

    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $("#J_formwrap").html("");
        tempJson = {};
    })
    $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        $("#J_formwrap").html($("#T_formwrap").val());

        initBtns();
    })

}
</script>
</body>
</html>
