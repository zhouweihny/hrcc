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
<link href="${request.contextPath}/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">

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
#J_formSearch label {
    vertical-align: 3px;
}
.OrderList_paginate_jump {
    float: right;
    height: 25px;
    line-height: 25px;
    margin-left: 15px;
}
.OrderList_paginate_jump span {
    cursor: default;
}
.OrderList_paginate_jump input {
    width: 38px;
    height: 27px;
    line-height: 27px;
    margin: 0 3px;
    font-size: 14px;
    text-align: center;
    border: 1px solid #ddd;
    border-radius: 4px;
}
.OrderList_paginate_jump .btn {
    margin-left: 10px;
}
.fn-text-right {
    text-align: right;
}
.select2-container {
    margin-bottom: 8px;
}
.J_storeFileSplit {
    vertical-align: 2px;
    margin: 0 5px;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>集客品种分析</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                        <input value="" type="hidden" name="startdate" id="startdate" />
                        <input value="" type="hidden" name="enddate" id="enddate" />
                        <input value="" type="hidden" name="storeid" id="storeid" />
                        <div class="form-group col-md-3">
                            <label for="zuiStoreId">门店选择：</label>
                            <select id="zuiStoreId" class="form-control"></select>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_startdate">销售日期：</label>
                            <select class="form-control J_zuiStoreDate" id="J_startdate"></select>
                            <em class="J_storeFileSplit">至</em>
                            <select class="form-control J_zuiStoreDate" id="J_enddate"></select>
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-w-m btn-info" onclick="getDetail();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                        <thead>
                            <tr role="row">
                                <th>编码</th>
                                <th width="12%">名称</th>
                                <th>规格</th>
                                <th>厂商</th>
                           		<th>销售额</th>
                                <th>销售数量</th>
                             	<th>毛利</th>
                           		<th>毛利率</th>
                           	    <th>订单数量</th>
                                <th>订单销售额</th>
                                <th>订单毛利</th>
                                <th>订单毛利率</th>
                            </tr>
                        </thead>
                        <tbody id="J_tbl_body"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<textarea class="fn-hide" id="T_tbl_body">
{{#each this}}
<tr>
    <td>{{code}}</td>
    <td>{{name}}</td>
    <td>{{specifications}}</td>
    <td>{{factory}}</td>
    <td class="fn-text-right">{{retFix xse}}</td>
    <td class="fn-text-right">{{xssl}}</td>
    <td class="fn-text-right">{{retFix ml}}</td>
    <td class="fn-text-right">{{retFixS mlv}}</td>
    <td class="fn-text-right">{{ordernum}}</td>
    <td class="fn-text-right">{{retFix orderxse}}</td>
    <td class="fn-text-right">{{retFix orderml}}</td>
    <td class="fn-text-right">{{retFixS ordermlv}}</td>
</tr>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_zuiStoreId">
<option value=""></option>
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>
<textarea class="fn-hide" id="T_zuiStoreDate">
{{#each this}}
<option value="{{this}}">{{this}}</option>
{{/each}}
</textarea>

<script>

var clickFlag = 0,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    p_getStoreId = '',
    p_getFileId = '',
    startdate = enddate = '',
    zuiTbl = null;

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        getZuiStore();
    }
})

function getZuiStore(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/store/stores.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.totalRows !== 0){
                $("#zuiStoreId").temp($("#T_zuiStoreId").val(), json.data).find("option:eq(1)").attr({"selected":"selected"});
                p_getStoreId = $("#zuiStoreId").val();
                $("#storeid").val(p_getStoreId);

                $("#zuiStoreId").select2({
                    placeholder: "请选择门店",
                    allowClear: false,
                    language: "zh-CN",
                    dropdownAutoWidth: false
                });

                $("#zuiStoreId").on("select2:unselect", function(e){
                    p_getStoreId = '';
                    $("#storeid").val('');
                })
                $("#zuiStoreId").on("select2:select", function(e){
                    p_getStoreId = $("#zuiStoreId").val();
                    $("#storeid").val(p_getStoreId);
                    getStoreFileDate();
                })

                getStoreFileDate();
            }else{
                MyFun.to.e(json.message || "查询药店列表失败");
            }
        }
        unBlock(".wrapper");
    })
}

function getStoreFileDate(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxsalemonth/monthsbystoreid.do", "storeid="+p_getStoreId, function(state, json){
        if(state == 'success'){
            if(json){
                $(".J_zuiStoreDate").temp($("#T_zuiStoreDate").val(), json.data);
                var len = json.data.length;
                if(len){
                    var _index = (len<2) ? 0 : (len<3) ? 1 : 2;

                    $("#J_startdate").find("option:eq("+_index+")").attr({"selected":"selected"});
                    $("#J_enddate").find("option:eq(0)").attr({"selected":"selected"});
                    startdate = $("#J_startdate").val();
                    enddate = $("#J_enddate").val();
                    $("#startdate").val(startdate);
                    $("#enddate").val(enddate);

                    $(".J_zuiStoreDate").select2({
                        placeholder: "请选择销售日期",
                        allowClear: false,
                        language: "zh-CN",
                        dropdownAutoWidth: false
                    });

                    $("#J_startdate").on("select2:select", function(e){
                        startdate = $("#J_startdate").val();
                        $("#startdate").val(startdate);
                        judgeFileDate();
                    })
                    $("#J_enddate").on("select2:select", function(e){
                        enddate = $("#J_enddate").val();
                        $("#enddate").val(enddate);
                    })
                    judgeFileDate();
                }else{
                    MyFun.to.e(json.message || "暂无药店销售数据");
                    startdate = "";
                    enddate = "";
                    $("#startdate").val("");
                    $("#enddate").val("");
                    $("#J_startdate").val(null).trigger("change");
                    $("#J_enddate").val(null).trigger("change");
                }
            }else{
                MyFun.to.e(json.message || "查询药店销售日期失败");
            }
        }
        unBlock(".wrapper");

        setTimeout(function(){
            initZuiStorePage();
        }, 300)
    })
}

function judgeFileDate(){
    var s1 = startdate,
        s2 = enddate,
        obj2 = $("#J_enddate"),
        opt2 = obj2.find("option");
    s1 = zdate2Int(s1);
    s2 = zdate2Int(s2);

    if(s2 < s1){
        obj2.val(startdate).trigger("change");
        enddate = startdate;
        $("#enddate").val(enddate);
    }

    //选择开始日期，重置结束日期
    opt2.each(function(i){
        var _this = $(this),
            _val = _this.val();
        _val = zdate2Int(_val);
        if(_val < s1){
            _this.attr({"disabled": "disabled"});
        }else{
            _this.removeAttr("disabled");
        }
    })
    obj2.select2();
}
// 2018-03 --- 201803
function zdate2Int(date){
    return parseInt(date.split("-").join(""), 10);
}

function initZuiStorePage(){
    getDetail();
}

function getDetail(){

    createBlock(".wrapper", '正在努力加载数据...');
    var formData = $("#J_formSearch").formSerialize();
    $.PostJson("${request.contextPath}/fxsaleanalysis/xk.do", formData, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(zuiTbl){
                    zuiTbl.destroy();
                    zuiTbl = null;
                }

                if(json.data && json.data.length){

                    $("#J_tbl_body").temp($("#T_tbl_body").val(), json.data);

                    zuiTbl = $('#OrderList').DataTable({
                        language: {
                            sProcessing: "正在努力加载数据...",
                            sSearch: "筛选："
                        },
                        dom: '<"top">frt<"bottom">lpi<"clear">',
                        ordering:true, //打开表格的排序功能
                        // order: [], //默认不排序
                        order: [[ 1, "desc" ]], //初始化以第二列排序
                        serverSide: false,  //启用服务器端分页
                        processing: true,  //隐藏加载提示,自行处理
                        searching: false, // 禁用搜索
                        columnDefs: [
                            {"orderable": false, "targets": 2},
                            {"orderable": false, "targets": 4}
                        ],
                        drawCallback: function(settings){
                            //增加跳转到某页
                            if(!$("#OrderList_paginate_jump").length){
                                var _strArr = [
                                    '<div id="OrderList_paginate_jump" class="OrderList_paginate_jump">',
                                    '<span>到第</span>',
                                    '<input class="input-txt" maxlength="4" value="">',
                                    '<span>页</span>',
                                    '<a class="btn btn-white">确定</a>',
                                    '</div>'
                                ]
                                $("#OrderList_paginate").before(_strArr.join(""));
                                $("#OrderList_paginate_jump").off("click").on("click", ".btn", function(){
                                    var _val = $(this).siblings(".input-txt").val();
                                    gotoPage('1', _val);
                                    return false;
                                }).off("keyup").on("keyup", ".input-txt", function(event){
                                    this.value=this.value.replace(/[^0-9]/g,'');
                                    if(event.keyCode==13){
                                        var _val = $(this).val();
                                        gotoPage('1', _val);
                                        return false;
                                    }
                                })
                            }
                        }
                    });
                }else{
                    $("#J_tbl_body").html('<tr><td colspan="12" style="text-align: center;">暂无数据</td></tr>');
                }

            }else{
                if(zuiTbl){
                    zuiTbl.destroy();
                    zuiTbl = null;
                }
                $("#J_tbl_body").html('<tr><td colspan="12" style="text-align: center;">暂无数据</td></tr>');
                MyFun.to.e(json.message || "查询集客品种失败");
            }
        }
        unBlock(".wrapper");
    })
}

function gotoPage(type, page){
    if(!page)
        return false;
    page = parseInt(page, 10) - 1;
    if(page < 0)
        page = 0;
    if(type == '1'){
        zuiTbl.page(page).draw(false);
        $("#OrderList_paginate_jump .input-txt").val("");
    }else if(type == '2'){
        zuiTblS.page(page).draw(false);
        $("#J_bottom_tbl_paginate_jump .input-txt").val("");
    }
}

Handlebars.registerHelper('retFix', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = _val.toFixed(2);

    var intSum = _val.substring(0, _val.indexOf(".")).replace( /\B(?=(?:\d{3})+$)/g, ',' );//取到整数部分
    var dot = _val.substring(_val.length, _val.indexOf("."))//取到小数部分搜索

    return intSum + dot;
});

Handlebars.registerHelper('retFixS', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = _val.toFixed(2);
    return _val+'%';
});

function zuiaccMul(arg1,arg2) {  
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length}catch(e){}  
    try{m+=s2.split(".")[1].length}catch(e){}  
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);  
}  

function zuiaccDiv(arg1,arg2){  
    var t1=0,t2=0,r1,r2;  
    try{t1=arg1.toString().split(".")[1].length}catch(e){}  
    try{t2=arg2.toString().split(".")[1].length}catch(e){}  
    with(Math){  
        r1=Number(arg1.toString().replace(".",""));  
        r2=Number(arg2.toString().replace(".",""));  
        return (r1/r2)*pow(10,t2-t1);  
    }  
}
</script>
</body>
</html>