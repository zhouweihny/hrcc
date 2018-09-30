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
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

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
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>联合用药分析</h5>
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
                       <div class="form-group col-md-3">
                        <a target="_blank" class="btn btn-primary fn-mr-20"  onclick="getZtable()">近一月详情 </a>
                       </div>
                    </form>
                    <div class="row">
                        <div class="col-md-12">
                            <div id="J_tbl_wrap">
                                <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                                    <thead>
                                        <tr role="row">
                                            <th width="6%">一级节点</th>
                                            <th width="10%">二级节点</th>
                                            <th>订单数</th>
                                            <th width="9%">销售额</th>
                                            <th>平均客单价</th>
                                           	<th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="J_tbl_body"></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<textarea class="fn-hide" id="T_tbl_body">
{{#each this}}
<tr>
    <td>{{lv1}}</td>
    <td>{{lv2}}</td>
    <td class="fn-text-right">{{zbs}}</td>
    <td class="fn-text-right">{{retFixS xse}}</td>
    <td class="fn-text-right">{{retFixS kdj}}</td>
   	<td class="fn-text-right"><a target="_blank"  href="https://wenku.baidu.com/view/49e4fd28f68a6529647d27284b73f242336c3113.html">资料 </a></td>
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
    G_json = null,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    zuiTbl = null,
    startdate = enddate = '',
    p_getStoreId = '',
    p_getFileId = '';

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        getZuiStore();
    }
})


function getZtable(){
window.open("${request.contextPath}/fxorderfx/ztable.do?storeid="+p_getStoreId);  
}

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
    setTimeout(function(){
        getDetail();
    }, 300)
}

function getDetail(){
    if(zuiTbl){
        zuiTbl.destroy();
        zuiTbl = null;
    }
    $("#OrderList_wrapper").html("");

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxorderfx/table2.do", "storeid="+p_getStoreId+"&startdate="+startdate+"&enddate="+enddate, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data && json.data.length){
                    $("#J_tbl_body").temp($("#T_tbl_body").val(), json.data);
                    
                    zuiTbl = $('#OrderList').DataTable({
                        language: {
                            sProcessing: "正在努力加载数据..."
                        },
                        dom: '<"top">rt<"bottom"flpi><"clear">',
                        ordering:false,//关闭表格的排序功能
                        serverSide: false,  //启用服务器端分页
                        processing: true,  //隐藏加载提示,自行处理
                        searching: false, // 禁用搜索
                        paging: false,
                        columnDefs: [
                            {"orderable": false, "targets": 3}
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
                    $("#J_tbl_body").html('<tr><td colspan="7" style="text-align: center;">暂无数据</td></tr>');
                }
            }else{
                MyFun.to.e(json.message || "查询失败");
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

Handlebars.registerHelper('retFixS', function(value){
    var _val = value+'';
    if(!_val || _val == 'null' || _val == 'undefined'){
        return '';
    }
    _val = parseFloat(value, 10);
    _val = _val.toFixed(2);
    return _val;
});
</script>
</body>
</html>