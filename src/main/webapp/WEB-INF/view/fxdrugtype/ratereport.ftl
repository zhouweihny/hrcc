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
.fn-mt-15 {
    margin-top: 15px;
    margin-bottom: 10px;
}
.fn-hide {
    /*display: none!important;*/
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
.form-inline .form-group.fn-hide {
    display: none;
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
.select2-container {
    margin-bottom: 8px;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>药品分析</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline" id="J_formSearch" action="javascript:;">
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label for="spaceid">药品分类：</label>
                                <input value="" type="text" class="form-control" id="spaceidS" style="width: 172px;" readonly/>
                                <input value="" type="hidden" name="spaceid" id="spaceid" />
                            </div>
                            <div class="form-group col-md-4">
                                <button type="button" class="btn btn-w-m btn-info" id="J_searchBtn" onclick="zuiSearch();"><i class="fa fa-search"></i>查 询</button>
                            </div>
                        </div>
                    </form>
                    <div class="m-t">
                        <!-- <button type="button" class="btn btn-primary">导入</button> -->
                    </div>

                    <div id="OrderListWrap">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>分类</th>
                                    <th>销售金额</th>
                                    <th>毛利</th>
                                    <th>毛利率</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
               </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_OrderList">
{{#each this}}
<tr>
    <td>{{treename1}} / {{treename2}} / {{treename3}}{{#if treename4}} / {{treename4}}{{/if}}</td>
    <td>{{totalAmt}}</td>
    <td>{{fluentAmt}}</td>
    <td>{{fluentRate}}</td>
</tr>
{{/each}}
</textarea>

<script>

var zuiTbl = null,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    type = getQueryString("type") ? getQueryString("type") : "",
    clickFlag = 0,
    curStoreid = '',
    curSaleFile = '';

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        curStoreid = window.parent.W_p_getStoreId(),
        curSaleFile = window.parent.W_p_getSaleFileId();


    }else{

    }

    getzPageS();

})

function zuiSearch(){
    if(zuiTbl){
        zuiTbl.destroy();
        zuiTbl = null;
    }
    getzPageS();
}

function getzPageS(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxdrugtype/ratereportjson.do", 'storeid='+curStoreid+'&salefile='+curSaleFile, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.code == '0000'){
                    if(json.data.length){
                        $("#OrderList tbody").temp($("#T_OrderList").val(), json.data);
                    }else{
                        $("#OrderList tbody").html('<td colspan="4"><p style="text-align: center;line-height: 40px;height: 30px;">暂无数据</p></td>');
                    }
                }
            }else{
                MyFun.to.e(json.message || "查询毛利率失败");
                $("#OrderList tbody").html('');
            }
        }
        unBlock(".wrapper");
    })
}

function getzPage(){
    if(zuiTbl){
        zuiTbl.draw();
    }else{
        zuiTbl = $('#OrderList').DataTable( {
            language: {
                sProcessing: "正在努力加载数据..."
            },
            dom: '<"top">rt<"bottom"flpi><"clear">',
            ordering:false,//关闭表格的排序功能
            serverSide: true,  //启用服务器端分页
            processing: true,  //隐藏加载提示,自行处理
            searching: false, // 禁用搜索
            ajax: function (data, callback, settings) {
                var formData = 'storeid='+curStoreid+'&salefile='+curSaleFile+'&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

                //ajax请求数据
                $.ajax({
                    type: "post",
                    url: "${request.contextPath}/fxdrugtype/ratereportjson.do",
                    cache: false,  //禁用缓存
                    data: formData,  //传入组装的参数
                    dataType: "json",
                    success: function (result) {
                        //封装返回数据
                        var returnData = {};
                        returnData.draw = data.draw;
                        if(result.data && result.data.totalRows){
                            returnData.recordsTotal = result.data.totalRows;//返回数据全部记录
                            returnData.recordsFiltered = result.data.totalRows;
                            returnData.data = result.data.data;//返回的数据列表
                        }else{
                            returnData.recordsTotal = 0;
                            returnData.recordsFiltered = 0;
                            returnData.data = [];
                        }
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕


                        callback(returnData);
                    }
                });
            },
            columns: [
                { "data": "name" },
                { "data": "code" },
                { "data": "" }
            ],
            columnDefs: [
                {
                    "render": function(data, type, row) {
                        return '<a href="javascript:;" class="J_check">新增</a>';
                    },
                    "targets": 2
                }
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
    }

    $('#OrderList tbody').off("click").on('click', '.J_check', function () {
        var _zparent = $(this).parents('tr');
        G_index = $('#OrderList tbody tr[role="row"]').index(_zparent);
        var data = zuiTbl.row( _zparent ).data();
        
        addKeywords(data);
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
</script>
</body>
</html>