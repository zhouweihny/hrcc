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
<script src="${request.contextPath}/js/plugins/layer/layer.min_0.9.js"></script>
<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_12" rel="stylesheet">

<style>
div.dataTables_info {
    padding-top: 5px;
    margin-left: 15px;
    display: inline-block;
}
.table-striped>tbody>tr:nth-of-type(odd) {
    background-color: #fff;
}
.table-striped>tbody>tr:nth-of-type(even) {
    background-color: #f9f9f9;
}
.dataTables_wrapper {
    position: relative;
}
.dataTables_wrapper .dataTables_processing {
    position: absolute;
    left: 0;
    top: 0;
    background: rgba(255, 255, 255, .3);
    width: 100%;
    height: 100%;
    z-index: 99;
    text-align: center;
    line-height: 260px;
    font-size: 22px;
}
.modal-dialog {
    width: 1100px!important;
}
.modal-open .modal .ibox-content {
    /*max-height: 550px;*/
}
.modal-dialog .ibox.float-e-margins {
    margin-bottom: 10px;
}
.J_formSearch_D {
    background: #F5F5F6;
    padding: 10px;
    border: 1px solid #e7e7e7;
    border-bottom-color: #ddd;
    margin: 0 0 20px;
}
.J_formSearch_D label {
    display: inline-block;
    vertical-align: middle;
    width: 70px;
    margin-bottom: 0;
}
.J_formSearch_D .form-control {
    vertical-align: middle;
}
.done {
    color: #FF8245;
}
.halfdone {
    color: #FD1243;
}
.fn-mt-15 {
    margin-top: 15px;
    margin-bottom: 10px;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>元数据管理</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline" id="J_formSearch" action="javascript:;">
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label for="spaceid">规则名称：</label>
                                <input type="text" class="form-control" placeholder="输入规则名称" name="rulesname">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="catalogid">方法名：</label>
                                <input type="text" class="form-control" placeholder="输入方法名" name="classname">
                            </div>
                            <div class="form-group col-md-4">
                                <button type="button" class="btn btn-w-m btn-info" id="J_searchBtn" onclick="MyFun.search('J_formSearch','FxMetadataList');"><i class="fa fa-search"></i>查 询</button>
                            </div>
                        </div>
                    </form>
                    <div class="m-t">
                        <button type="button" class="btn btn-primary" id="J_addRules">新增</button>
                    </div>

                    <div id="FxMetadataList" data-url="${request.contextPath}/fxmetadata/table.do">
                    </div>    
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
                    <h5>新增规则</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <form class="form-inline row J_formSearch_D" id="J_formSearch_D" action="javascript:;">
                            <div class="row">
                                <div class="form-group col-md-8">
                                    <label for="spaceid">药品分类：</label>
                                    <select name="group" id="zgroup" class="form-control" style="width: 530px;"></select>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="spaceid">规则名称：</label>
                                    <input type="text" class="form-control" placeholder="输入规则名称" name="name">
                                </div>
                            </div>
                            <div class="row m-t">
                                <div class="form-group col-md-4">
                                    <label for="spaceid">类路径：</label>
                                    <input type="text" class="form-control" placeholder="输入类路径" name="path">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="spaceid">方法名：</label>
                                    <input type="text" class="form-control" placeholder="输入方法名" name="funame">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="spaceid">品种数：</label>
                                    <input type="text" class="form-control" placeholder="输入品种数" name="num">
                                </div>
                            </div>
                            <div class="row m-t">
                                <div class="form-group col-md-4">
                                    <label for="spaceid">平均值：</label>
                                    <input type="text" class="form-control" placeholder="输入平均值" name="avgvalue">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="spaceid">备注：</label>
                                    <input type="text" class="form-control" placeholder="输入备注" name="remark" >
                                </div>
                            </div>
                        </form>
                        <div class="m-t">
                            <button type="button" class="btn btn-primary" id="J_addRulesRow">新增一行</button>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>条件</th>
                                    <th>参数</th>
                                    <th>提示语</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" style="margin-left: 20px;width: 100px;" id="J_saveBtn">提交</button>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_bottom_tbl">
{{#each this}}
<tr>
    <td>
        <select class="form-control rulesCompare">
            <option value="1" {{retSelected condition 1}}>大于</option>
            <option value="2" {{retSelected condition 2}}>小于</option>
            <option value="3" {{retSelected condition 3}}>等于</option>
            <option value="4" {{retSelected condition 4}}>大于等于</option>
            <option value="5" {{retSelected condition 5}}>小于等于</option>
            <option value="6" {{retSelected condition 6}}>不等于</option>
        </select>
    </td>
    <td>
        <input type="text" class="form-control rulesParam" value="{{parameter}}" /> </td>
    <td>
        <input type="text" class="form-control rulesRemark" value="{{message}}" /> </td>
    <td> <a href="javascript:;" class="J_del">删除</a> </td>
</tr>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_options">
{{#each this}}
<option value="{{id}}">{{pathname}}</option>
{{/each}}
</textarea>

<script>

function refreshFxMetadataList(){
    MyFun.ajaxRefreshTable("FxMetadataList");
}

var clickFlag = 0,
    modifyId = '';//修改数据id;

$(function(){
    MyFun.ajaxRefreshTable("FxMetadataList");

    getCombolist();
})

function initPage(){
    $("#J_addRules").on("click", function(){
        modifyId = '';
        createInfoPage();
    })

    $("#J_addRulesRow").on("click", function(){
        addRulesRow();
    })
    $("#J_saveBtn").on("click", function(){
        saveRules();
    })
}

function createInfoPage(){
    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $("#J_bottom_tbl tbody").html('');
    })
    $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        if(!modifyId)
            addNewRules();
        else
            modifyRules();
    })
}

function addNewRules(){
    $("#zgroup").val(null).trigger("change");
    $("#J_formSearch_D").resetForm();
    addRulesRow();
}

function addRulesRow(){
    //新增一行
    var _tr = '<tr> <td> <select class="form-control rulesCompare"> <option value="1">大于</option> <option value="2">小于</option> <option value="3">等于</option> <option value="4">大于等于</option> <option value="5">小于等于</option> <option value="6">不等于</option> </select> </td> <td> <input type="text" class="form-control rulesParam" value="" /> </td> <td> <input type="text" class="form-control rulesRemark" value="" /> </td> <td> <a href="javascript:;" class="J_del">删除</a> </td> </tr>';
    $("#J_bottom_tbl tbody").append(_tr);
    $(".J_del").off("click").on("click", function(){
        $(this).parent().parent().remove();
    })
}

function saveRules(){
    var attr = {},
        item = [],
        _infoForm = $("#info-form"),
        _J_formSearch_D = $("#J_formSearch_D");
        group = _infoForm.find("#zgroup").val(),
        name = _infoForm.find("input[name='name']").val(),
        path = _infoForm.find("input[name='path']").val(),
        funame = _infoForm.find("input[name='funame']").val(),
        num = _infoForm.find("input[name='num']").val(),
        avgvalue = _infoForm.find("input[name='avgvalue']").val(),
        remark = _infoForm.find("input[name='remark']").val();

    if(!group){
        MyFun.to.i("药品分类不能为空");
        return false;
    }
    if(!name){
        MyFun.to.i("规则名称不能为空");
        return false;
    }
    if(!path){
        MyFun.to.i("类路径不能为空");
        return false;
    }
    if(!funame){
        MyFun.to.i("方法名不能为空");
        return false;
    }
    if(!num){
        MyFun.to.i("品种数不能为空");
        return false;
    }
    if(!avgvalue){
        MyFun.to.i("平均值不能为空");
        return false;
    }

    attr.id = modifyId;
    attr.treeid = group;
    attr.name = name;
    attr.classname = path;
    attr.methodname = funame;
    attr.drugnum = num;
    attr.avg = avgvalue;
    attr.explain = remark;
    attr.userid = '';

    var _J_bottom_tbl = $("#J_bottom_tbl"),
        _tr = _J_bottom_tbl.find("tbody tr"),
        emptyFlag = 0;
    if(_tr.length){
        _tr.each(function(){
            var _this = $(this),
                rulesCompare = _this.find(".rulesCompare").val(),
                rulesParam = _this.find(".rulesParam").val(),
                rulesRemark = _this.find(".rulesRemark").val();

            if(rulesParam){
                var _attr = {};
                _attr.condition = rulesCompare;
                _attr.parameter = rulesParam;
                _attr.message = rulesRemark;
                _attr.id = '';
                _attr.metadataid = '';

                item.push(_attr);
            }else{
                emptyFlag = 1;
            }

        })
    }else{
        emptyFlag = 1;
    }

    if(emptyFlag){
        MyFun.to.i("条件不能有空");
    }else{
        attr.items = item;
        if(clickFlag)
            return false;
        clickFlag = 1;
        createBlock(".wrapper", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/fxmetadata/saveJson.do", 'data='+JSON.stringify(attr), function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    
                    MyFun.to.s(json.message || "提交数据成功");
                    $("#info-form").modal('hide');
                    refreshFxMetadataList();

                }else{
                    MyFun.to.e(json.message || "提交数据失败");
                }
            }
            clickFlag = 0;
            unBlock(".wrapper");
        })
    }
}

//修改
function modifyItem(obj){
    modifyId = $(obj).data("id") || '';
    createInfoPage();
}

function modifyRules(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxmetadata/josnList.do", "id="+modifyId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                
                var _infoForm = $("#info-form"),
                    _J_formSearch_D = $("#J_formSearch_D");
                    group = _infoForm.find("#zgroup"),
                    zname = _infoForm.find("input[name='name']"),
                    path = _infoForm.find("input[name='path']"),
                    funame = _infoForm.find("input[name='funame']"),
                    num = _infoForm.find("input[name='num']"),
                    avgvalue = _infoForm.find("input[name='avgvalue']"),
                    remark = _infoForm.find("input[name='remark']");

                group.val(json.data.treeid).trigger("change");
                zname.val(json.data.name);
                path.val(json.data.classname);
                funame.val(json.data.methodname);
                num.val(json.data.drugnum);
                avgvalue.val(json.data.avg);
                remark.val(json.data.explain);

                $("#J_bottom_tbl tbody").temp($("#T_bottom_tbl").val(), json.data.items);

                $(".J_del").off("click").on("click", function(){
                    $(this).parent().parent().remove();
                })

            }else{
                MyFun.to.e(json.message || "查询规则详情失败");
            }
        }
        unBlock(".wrapper");
    })
}

Handlebars.registerHelper('retSelected', function(rulesCompare, cur) {
    if(rulesCompare == cur)
        return "selected";
    return "";
});

function getCombolist(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtree/combolist.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                for(var i=0,len=json.data.length; i<len; i++){
                    var path = json.data[i].path;
                    path = path.split("/");
                    var strArr = [];
                    for(var k=0,klen=path.length; k<klen; k++){
                        for(var m=0,mlen=json.data.length; m<mlen; m++){
                            if(path[k] == json.data[m].id){
                                strArr.push(json.data[m].name);
                                break;
                            }
                        }
                    }
                    json.data[i].pathname = strArr.join(" & ");
                }

                var _cattr = {
                    "id": "",
                    "pathname": "请选择"
                };
                json.data.unshift(_cattr);
                // console.log(JSON.stringify(json))

                $("#zgroup").temp($("#T_options").val(), json.data).select2();

                initPage();
            }else{
                MyFun.to.e(json.message || "查询药品分类失败");
            }
        }
        unBlock(".wrapper");
    })
}
</script>
</body>
</html>

