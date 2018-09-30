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
    padding: 10px 0;
    border: 1px solid #e7e7e7;
    border-bottom-color: #ddd;
    margin: 20px 0;
}
.J_formSearch_D label {
    display: inline-block;
    vertical-align: middle;
    width: 45px;
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
.J_quick_dura {
    font-size: 13px;
    font-weight: normal;
    margin-left: 10px;
}
.fn-hide {
    display: none!important;
}
.J_autoCom_wrap {
    display: inline-block;
    height: 30px;
    line-height: 30px;
    vertical-align: middle;
    cursor: pointer;
}
.J_autoCom_wrap .check_wrap {
    display: inline-block;   
}
.J_autoCom_wrap .check_wrap .fa {
    cursor: pointer;
    font-size: 19px;
    display: inline-block;
    vertical-align: middle;
}
.J_autoCom_wrap .check_wrap .fa-square-o {
    margin-right: 3px;
}
.J_autoCom_wrap label {
    cursor: pointer;
    display: inline-block;
    padding: 0 15px 0 5px;
}
#J_switchCompare label {
    padding: 0 5px 0 0;
}
#J_goToNext, #J_ignoreBtn {
    margin: 0 15px 0 10px;
}
#layerMsg span {
    color: #000;
    font-size: 20px;
    display: inline-block;
    margin: 0 8px 0 5px;
}
#layerMsg span i {
    font-style: normal;
}
#OrderList.dataTable tbody tr td:last-child, .dataTables_scrollHead .dataTable thead tr th:last-child {
    text-align: right;
}
#OrderList.dataTable tbody tr td.dataTables_empty, #OrderList.dataTable tbody tr.done td:last-child {
    text-align: left;
}
#OrderList .J_check, #OrderList .J_delCheck, #OrderList .J_checkdrug {
    display: block;
    text-align: right;
    margin-top: 10px;
}
#OrderList .J_check:hover, #OrderList .J_delCheck:hover, #OrderList .J_checkdrug:hover {
    text-decoration: underline;
}
#OrderList.dataTable tbody tr td:last-child .J_delCheck {
    margin-top: 6px;
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
                    <h5>药品对码</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline" id="J_formSearch" action="javascript:;">
                        <div class="row">
                            <div class="form-group col-md-3">
                                <label for="spaceid">对码项目：</label>
                                <select name="spaceid" id="spaceid" class="form-control input-sm">
                                    
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="catalogid">药品目录：</label>
                                <select name="catalogid" id="catalogid" class="form-control input-sm">
                                    
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="spaceid">对码状态：</label>
                                <select name="compared" id="J_compared" class="form-control input-sm">
                                    <option value="">全部</option>
                                    <option value="0">已对码</option>
                                    <option value="-1">未对码</option>
                                    <option value="1">已忽略</option>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="spaceid">复核状态：</label>
                                <select name="checked" id="J_checked" class="form-control input-sm">
                                    <option value="">全部</option>
                                    <option value="true">已复核</option>
                                    <option value="false">未复核</option>
                                </select>
                            </div>
                        </div>
                        <div class="row fn-mt-15">
                            <div class="form-group col-md-3">
                                <label for="spaceid">药品编码：</label>
                                <input type="text" class="form-control" placeholder="输入药品编码" name="code">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="spaceid">药品名称：</label>
                                <input type="text" class="form-control" placeholder="输入药品名称" name="name">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="spaceid">厂商名称：</label>
                                <input type="text" class="form-control" placeholder="输入厂商" name="factory">
                            </div>
                            <div class="form-group col-md-3">
                                <input type="hidden" class="form-control" placeholder="显示对码品种" name="showcompared" id="showcompared">
                                <div class="J_autoCom_wrap" id="J_switchCompare">
                                    <label for="showcompared">显示对码品种：</label>
                                    <div class="check_wrap">
                                        <i class="fa fa-square-o" aria-hidden="true"></i>
                                        <i class="fa fa-check-square-o fn-hide" aria-hidden="true"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="m-t">
                        <button type="button" class="btn btn-info m-r" id="J_searchBtn"><i class="fa fa-search"></i>查 询</button>

                        <button type="button" class="btn btn-primary" id="J_autoreview">开始自动对码</button>
                    </div>
                    <div>
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>编码</th>
                                    <th>名称</th>
                                    <th>规格</th>
                                    <th>单位</th>
                                    <th>厂商</th>
                                    <th>剂型</th>
                                    <th>复核状态</th>
                                    <th>对码状态</th>
                                    <th aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
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

<div id="info-form" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>药品对码<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>编码</th>
                                    <th>名称</th>
                                    <th>规格</th>
                                    <th>单位</th>
                                    <th>厂商</th>
                                    <th>剂型</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                        <form class="form-inline row J_formSearch_D" id="J_formSearch_D" action="javascript:;">
                            <div class="form-group col-md-3">
                                <label for="spaceid">名称：</label>
                                <input type="text" class="form-control" placeholder="输入药品名称" name="name">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="spaceid">厂商：</label>
                                <input type="text" class="form-control" placeholder="输入厂商" name="factory">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="spaceid">规格：</label>
                                <input type="text" class="form-control" placeholder="输入规格" name="specifications">
                            </div>
                            <div class="form-group col-md-3">
                                <button type="button" class="btn btn-w-m btn-info" id="J_searchBtn_D"><i class="fa fa-search"></i>查 询</button>

                                <a href="javascript:;" id="J_auto_compare" style="margin-left: 10px;">快速匹配</a>
                            </div>
                        </form>
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>编码</th>
                                    <th>名称</th>
                                    <th>规格</th>
                                    <th>单位</th>
                                    <th>厂商</th>
                                    <th>剂型</th>
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
                <div class="J_autoCom_wrap" id="J_autoCom_wrap">
                    <div class="check_wrap">
                        <i class="fa fa-square-o" aria-hidden="true"></i>
                        <i class="fa fa-check-square-o fn-hide" aria-hidden="true"></i>
                    </div>
                    <label title="勾选后系统3s后自动将快速匹配的第一条数据作为对码品种">自动匹配</label>
                </div>
                <button type="button" class="btn btn-primary" id="J_goToNext">跳 过</button>
                <button type="button" class="btn btn-danger" id="J_ignoreBtn">忽 略</button>
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="fn-hide" id="layerMsg_wrap">
    <div id="layerMsg">
    关闭窗口可取消自动匹配，<span><i>3</i>s</span>后将自动匹配。
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

var zuiTbl = null,
    zuiTblS = null,
    curDrugId = "",//当前对码的药品id
    curSpaceid = "",//当前工作空间id
    curCatalogid = "",//当前目录id
    clickFlag = 0,
    compareType = "",
    G_json = null,//当前分页获得数据
    G_index = 0,//当前点击对码索引
    rePullData = 0,//重新拉取标识
    changeFlag = 0,//是否点击了对码
    _D_height = $(window).height()- 190,//弹窗高度
    autoCompareFlag = 0,//是否开启自动匹配
    zui_inteTimer = null,
    _w_time = 3,//倒计时计数
    hasCompared = 0,//是否已对码
    searchType = 0,//0 快速匹配，1手动查询
    zendFlag = 0;//最后一条数据

var searchClickFlag = 1,//查询按钮点击
    searchClickFlag2 = 1;

$(function(){
    $('#OrderList').css({"width": $("#OrderList").parent().prev(".m-t").width()});

    getSpaces();

    $("#J_compared").on("change", function(){
        compareType = $(this).val();
        searchClickFlag = 1;
        getzPage();
    })

    $("#J_searchBtn").on("click", function(){
        searchClickFlag = 1;
        getzPage();
    })

    $("#J_formSearch").on("keyup", "input[type='text']", function(event){
        this.value = this.value.replace(/\s+/g, "");
        if(event.keyCode==13){
            searchClickFlag = 1;
            getzPage();
        }
    })

    $("#J_searchBtn_D").on("click", function(){
        searchType = 1;
        searchClickFlag2 = 1;
        getzPageS();
    })

    $("#J_auto_compare").on("click", function(){
        searchType = 0;
        getzPageS();
    })

    $("#J_formSearch_D").on("keyup", "input[type='text']", function(event){
        this.value = this.value.replace(/\s+/g, "");
        if(event.keyCode==13){
            searchType = 1;
            searchClickFlag2 = 1;
            getzPageS();
        }
    })

    $("#info-form .modal-dialog .modal-content .float-e-margins").css({"cssText": "height: "+_D_height+"px!important"});
    $("#info-form .modal-dialog .modal-content .float-e-margins .ibox-content").css({"cssText": "height: "+(_D_height - 50)+"px!important; max-height: "+(_D_height - 50)+"px!important;"});

    var _J_autoCom_wrap = $("#J_autoCom_wrap");
    _J_autoCom_wrap.on("click", function(){
        if(!autoCompareFlag){
            layer.confirm('勾选后，系统在 3 秒后自动将快速匹配查询到的第一条数据作为对码品种。', {
                btn: ['确定','取消'] //按钮
            }, function(){
                layer.closeAll('dialog');
                _J_autoCom_wrap.find(".fa").toggleClass("fn-hide");
                autoCompareFlag = 1;
                goAutoCompare();
            } );
        }else{
            _J_autoCom_wrap.find(".fa").toggleClass("fn-hide");
            autoCompareFlag = 0;
        }
    })

    var _J_switchCompare = $("#J_switchCompare");
    _J_switchCompare.on("click", function(){
        var showcompared = _J_switchCompare.find(".fa-square-o").hasClass("fn-hide") ? false : true;
        $("#showcompared").val(showcompared);
        _J_switchCompare.find(".fa").toggleClass("fn-hide");
        searchClickFlag = 1;
        getzPage();
    })

    $("#J_goToNext").on("click", function(){
        getNextDrugs();
    })
    $("#J_ignoreBtn").on("click", function(){
        ignoreDrug();
    })

    $("#J_autoreview").on("click", function(){
        autoreview();
    })
})

function exportFile(){
    if(curSpaceid && curCatalogid){
        var  url= "${request.contextPath}/space/exportfile.do?spaceid="+curSpaceid+"&catalogid="+curCatalogid;
        window.open(url, "_blank");
    }else{
        MyFun.to.i("请先选择工作空间和药品目录！");
    }
};

function getSpaces(){
    //查询所有工作空间
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/space/spaces.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#spaceid").temp($("#T_option").val(), json.data);

                    $("#spaceid").off("change").on("change", function(){
                        curSpaceid = $(this).val();
                        getCatalogs(curSpaceid);
                    })

                    curSpaceid = json.data[0].id;
                    getCatalogs(curSpaceid);
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

					curCatalogid = json.data[0].catalogid;
                    $("#catalogid").off("change").on("change", function(){
                        curCatalogid = $(this).val();
                        searchClickFlag = 1;
                        getzPage();

                        //是否正在自动对照中
                        checkcompare();
                    })

                    searchClickFlag = 1;
                    getzPage();

                    checkcompare();
                }else{
                    $("#catalogid").html("");
                    if(zuiTbl){
                        zuiTbl.draw();
                    }
                }
            }else{
                MyFun.to.e(json.message || "查询工作空间下的普通目录失败");
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
                var formData = "";
                if(searchClickFlag){
                    formData = $("#J_formSearch").formSerialize();

                    //设置、更新cookie
                    //一天后失效
                    $.cookie('J_formSearch_data', formData, { expires: 1 });

                    // searchClickFlag = 0;
                }else{
                    //点击翻页时，从cookie中获取
                    formData = $.cookie('J_formSearch_data');
                }
                formData += '&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

                //ajax请求数据
                $.ajax({
                    type: "post",
                    url: "${request.contextPath}/space/drugs.do",
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

                        G_json = returnData.data;

                        if(rePullData){
                            rePullData = 0;
                            G_index = 0;
                            initCheckPage(G_json[0]);
                        }

                        if(result.data && ((data.start + data.length) < result.data.totalRows)){
                            zendFlag = 0;
                        }else{
                            zendFlag = 1;
                        }

                        callback(returnData);
                    }
                });
            },
            columns: [
                { "data": "code" },
                { "data": "name" },
                { "data": "specifications" },
                { "data": "unit" },
                { "data": "factory" },
                { "data": "dosageform" },
                { "data": "" },
                { "data": "" },
                { "data": "" }
            ],
            columnDefs: [
                {
                    "render": function(data, type, row) {
                        if(!row.unit){
                            row.unit = "";
                        }
                        return row.unit;
                    },
                    "targets": 3
                },
                {
                    "render": function(data, type, row) {
                        var buffer = '';
                        if(row.checked==true){
                            buffer = '<span class="done">已复核</span>';
                        }
                        return buffer;
                    },
                    "targets": 6
                },
                {
                    "render": function(data, type, row) {
                        var buffer = '未对码';
                        if(row.compared==0){
                            buffer = '<span class="done">已对码</span>';
                        }else if(row.compared==1){
                            buffer = '<span class="halfdone">已忽略</span>';
                        }
                        return buffer;
                    },
                    "targets": 7
                },
                {
                    "render": function(data, type, row) {
                        if(row.compared == 0){
                            //已对码
                            return '<a href="javascript:;" class="J_check">修改对码</a><a href="javascript:;" class="J_checkdrug">复核</a>';
                        }else{
                            return '<a href="javascript:;" class="J_check">对码</a>';
                        }

                        /*var buffer = ['<a href="javascript:;" class="J_check">对码</a>'];
                        if(row.compared == 0){
                            buffer.push('<a href="javascript:;" class="J_delCheck">删除对码</a><a href="javascript:;" class="J_checkdrug">复核</a>');
                        }

                        return buffer.join("");*/
                    },
                    "targets": 8
                }
            ],
            drawCallback: function(settings){
                var api = this.api();
                var rows = api.rows( {page:'current'} ).nodes();
                api.rows( {page:'current'} ).data().each(function(item, i){
                    if(item.comparecode){
                        var _arr = [
                            '<tr class="done">',
                            '<td>'+(item.comparecode || "")+'</td>',
                            '<td>'+(item.comparename || "")+'</td>',
                            '<td>'+(item.comparespecifications || "")+'</td>',
                            '<td>'+(item.compareunit || "")+'</td>',
                            '<td>'+(item.comparefactory || "")+'</td>',
                            '<td>'+(item.comparedosageform || "")+'</td>',
                            '</tr>'
                        ];
                        $(rows).eq(i).after(_arr.join("")).find("td:gt(5)").attr({"rowspan": "2"});
                    }
                })

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
        changeFlag = 0;
        initCheckPage(data);
    }).on("click", ".J_delCheck", function(){
        var data = zuiTbl.row( $(this).parents('tr') ).data();
        createBlock(".wrapper", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/space/delcompare.do", "drugid="+data.id+"&spaceid="+curSpaceid, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "删除对码成功");
                    if(zuiTbl){
                        zuiTbl.draw(false);
                    }
                }else{
                    MyFun.to.e(json.message || "删除对码失败");
                }
            }
            unBlock(".wrapper");
        })
    }).on("click", ".J_checkdrug", function(){
        var data = zuiTbl.row( $(this).parents('tr') ).data();
        createBlock(".wrapper", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/space/checked.do", "drugid="+data.id+"&sdrugid="+data.compareid+"&spaceid="+curSpaceid, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "复核成功");
                    if(zuiTbl){
                        zuiTbl.draw(false);
                    }
                }else{
                    MyFun.to.e(json.message || "复核失败");
                }
            }
            unBlock(".wrapper");
        })
    })
}

function initCheckPage(data){
    curDrugId = data.id;
    
    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        if(zuiTblS){
            zuiTblS.destroy();
            zuiTblS = null;
        }
        if(zuiTbl && changeFlag){
            zuiTbl.draw(false);
        }
    })

    var _tr = '<tr><td>'+(data.code || "")+'</td><td>'+(data.name || "")+'</td><td>'+(data.specifications || "")+'</td><td>'+(data.unit || "")+'</td><td>'+(data.factory || "")+'</td><td>'+(data.dosageform || "")+'</td><td>&nbsp;</td></tr>'
    $("#J_top_tbl tbody").html(_tr);

    $("#J_formSearch_D input.form-control").eq(0).val(data.name || "");
    $("#J_formSearch_D input.form-control").eq(1).val(data.factory || "");
    // $("#J_formSearch_D input.form-control").eq(2).val(data.specifications || "");

    //查询该药品已对码的药品
    getDrugCompared();
}

function getDrugCompared(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/space/findcompared.do", "drugid="+curDrugId+"&spaceid="+curSpaceid, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data && json.data.id){
                    var _tr = '<tr class="done"><td>'+(json.data.code || "")+'</td><td>'+(json.data.name || "")+'</td><td>'+(json.data.specifications || "")+'</td><td>'+(json.data.unit || "")+'</td><td>'+(json.data.factory || "")+'</td><td>'+(json.data.dosageform || "")+'</td><td><a href="javascript:;" class="J_delCheck">删除对码</a></td></tr>'
                    $("#J_top_tbl tbody").append(_tr).off("click").on("click", ".J_delCheck", function(){
                        var _tr = $(this).parent().parent();
                        createBlock(".modal-dialog", '正在努力加载数据...');
                        $.PostJson("${request.contextPath}/space/delcompare.do", "drugid="+curDrugId+"&spaceid="+curSpaceid, function(state, json){
                            if(state == 'success'){
                                if(json && json.code == '0000'){
                                    MyFun.to.s(json.message || "删除对码成功");
                                    _tr.remove();
                                    changeFlag = 1;
                                }else{
                                    MyFun.to.e(json.message || "删除对码失败");
                                }
                            }
                            unBlock(".modal-dialog");
                        })
                    });
                    hasCompared = 1;
                }else{
                    $("#J_top_tbl tbody").append('<tr><td colspan="7">暂无对码</td></tr>');
                    hasCompared = 0;
                }
            }else{
                MyFun.to.e(json.message || "查询已对码药品失败");
                hasCompared = 0;
            }
        }
        //默认为快速匹配
        searchType = 0;
        getzPageS();
        unBlock(".modal-dialog");
    })
}

function getzPageS(){
    if(zuiTblS){
        zuiTblS.destroy();
        zuiTblS = null;
    }

    if(!searchType){
        var quick_dura_s = (new Date()).getTime(),
            quick_dura_e = 0,
            quick_dura = 0;
    }

    zuiTblS = $('#J_bottom_tbl').DataTable( {
        language: {
            sProcessing: "正在努力加载数据..."
        },
        dom: '<"top">rt<"bottom"flpi><"clear">',
        ordering:false,//关闭表格的排序功能
        serverSide: true,  //启用服务器端分页
        processing: true,  //隐藏加载提示,自行处理
        searching: false, // 禁用搜索
        ajax: function (data, callback, settings) {
            var formData = "";
            if(searchType){

                if(searchClickFlag2){
                    formData = $("#J_formSearch_D").formSerialize();

                    //设置、更新cookie
                    //一天后失效
                    $.cookie('J_formSearch_d_data', formData, { expires: 1 });

                    // searchClickFlag2 = 0;
                }else{
                    //点击翻页时，从cookie中获取
                    formData = $.cookie('J_formSearch_d_data');
                }

            }else{
                formData = 'drugid='+curDrugId;
            }
            formData += '&spaceid='+curSpaceid+'&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

            //ajax请求数据
            $.ajax({
                type: "post",
                url: "${request.contextPath}/space/querycomparedrugs.do",
                cache: false,  //禁用缓存
                data: formData,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    if(result.code == '0000'){
                        //封装返回数据
                        var returnData = {};
                        returnData.draw = data.draw;
                        if(result.data.data && result.data.data.totalRows){
                            returnData.recordsTotal = result.data.data.totalRows;//返回数据全部记录
                            returnData.recordsFiltered = result.data.data.totalRows;
                            returnData.data = result.data.data.data;//返回的数据列表
                        }else{
                            returnData.recordsTotal = 0;
                            returnData.recordsFiltered = 0;
                            returnData.data = [];
                        }

                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    }else{
                        //封装返回数据
                        var returnData = {};
                        returnData.draw = data.draw;
                        returnData.recordsTotal = 0;
                        returnData.recordsFiltered = 0;
                        returnData.data = [];

                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                        MyFun.to.e(result.message || "系统错误，请稍后重试");
                    }
                },
                error: function(e) {
                    if(e.statusText == 'timeout'){
                        MyFun.to.e("连接超时！");
                    }else if(e.status == 500){
                        MyFun.to.e("服务器错误");
                    }else{
                        MyFun.to.e("系统错误，请稍后重试");
                    }
                },
                complete: function(xhr, textStatus){
                    /*if(!searchType){
                        quick_dura_e = (new Date()).getTime();
                        quick_dura = quick_dura_e - quick_dura_s;

                        var str = '';
                        if(xhr.responseJSON && xhr.responseJSON.code == '0000'){
                            str = '（前端耗时：'+quick_dura+'ms，后台耗时：'+(xhr.responseJSON.data.time || "0")+'ms）';
                        }else{
                            str = '（前端耗时：'+quick_dura+'ms）';
                        }

                        $(".J_quick_dura").html(str);
                    }else{
                        $(".J_quick_dura").html('');
                    }*/
                }
            });
        },
        columns: [
            { "data": "code", "width":"100px" },
            { "data": "name", "width":"280px" },
            { "data": "specifications", "width":"280px" },
            { "data": "unit", "width":"85px" },
            { "data": "factory", "width":"170px" },
            { "data": "dosageform", "width":"70px" },
            { "data": "", "width":"70px" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row) {
                    if(!row.unit){
                        row.unit = "";
                    }
                    return row.unit;
                },
                "targets": 3
            },
            {
                "render": function(data, type, row) {
                    return '<a href="javascript:;" class="J_compare">选择</a>';
                },
                "targets": 6
            }
        ],
        drawCallback: function(settings){
            //增加跳转到某页
            if(!$("#J_bottom_tbl_paginate_jump").length){
                var _strArr = [
                    '<div id="J_bottom_tbl_paginate_jump" class="OrderList_paginate_jump">',
                    '<span>到第</span>',
                    '<input class="input-txt" maxlength="4" value="">',
                    '<span>页</span>',
                    '<a class="btn btn-white">确定</a>',
                    '</div>'
                ]
                $("#J_bottom_tbl_paginate").before(_strArr.join(""));
                $("#J_bottom_tbl_paginate_jump").off("click").on("click", ".btn", function(){
                    var _val = $(this).siblings(".input-txt").val();
                    gotoPage('2', _val);
                    return false;
                }).off("keyup").on("keyup", ".input-txt", function(event){
                    this.value=this.value.replace(/[^0-9]/g,'');
                    if(event.keyCode==13){
                        var _val = $(this).val();
                        gotoPage('2', _val);
                        return false;
                    }
                })
            }
        }
    });

    if(searchType){
        $("#J_bottom_tbl").next(".bottom").removeClass("fn-hide");
    }else{
        $("#J_bottom_tbl").next(".bottom").addClass("fn-hide");
    }

    $('#J_bottom_tbl tbody').off("click").on('click', '.J_compare', function () {
        var data = zuiTblS.row( $(this).parents('tr') ).data();
        if(!data || !data.id){
            getNextDrugs();
            MyFun.to.i("未找到匹配数据，跳转至下一条");
            return false;
        }
        if(clickFlag)
            return false;
        clickFlag = 1;
        createBlock(".modal-dialog", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/space/compare.do", "drugid="+curDrugId+"&sdrugid="+data.id+"&spaceid="+curSpaceid, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "对码成功");
                }else{
                    MyFun.to.e(json.message || "对码失败");
                }
            }
            clickFlag = 0;
            changeFlag = 1;
            unBlock(".modal-dialog");

            getNextDrugs();
        })
    });

    if(autoCompareFlag){
        //自动匹配
        goAutoCompare();

    }else{
        //解锁按钮
        clickFlag = 0;
    }
}

//获取下一条数据
function getNextDrugs(){

    /**
     * 跨域cookie无法取得
     * 重新取formData = $("#J_formSearch").formSerialize();
    **/
    searchClickFlag = 1;
    searchClickFlag2 = 1;
    
    if((G_index+1) == $('#OrderList tbody tr[role="row"]').length){
        if(zendFlag){
            //最后一页，最后一条
            layer.alert('已经是最后一条数据了，对码结束', function(index){
                autoCompareFlag = 0;
                $("#J_autoCom_wrap").find(".fa-square-o").removeClass("fn-hide").siblings(".fa").addClass("fn-hide");
                unBlock(".ibox-content");
                $("#info-form").modal('hide');
                layer.close(index);
            });
            return false;
        }else{
            $("#J_formSearch_D").find("input").val("");
            //翻页，重新拉取G_json

            /**
             * modby zw 2018年1月11日10:25:27
             * 修正由于跳过、忽略、对码等按钮操作，导致数据查询结果重置，
             * 导致重新拉取的数据比之前的要少，next page可能不存在。
            **/

            if(changeFlag && zendFlag){
                zuiTbl.page('first').draw(false);
            }else{
                zuiTbl.page('next').draw(false);
            }

            rePullData = 1;
        }
    }else{
        $("#J_formSearch_D").find("input").val("");
        G_index++;
        initCheckPage(G_json[G_index]);
    }
}

function showMyLayer(callback){
    layer.open({
        // time: 3000,
        content: $('#layerMsg_wrap').html(),
        btn: "取消自动匹配",
        success: function(layero, index){
            zui_inteTimer = setInterval(function(){
                _w_time --;
                if(_w_time <= 0){
                    _w_time = 3;
                    $('#layerMsg span i').text('3');
                    if(typeof callback == 'function')
                        callback();
                }else{
                    $('#layerMsg span i').text(_w_time);
                }
            }, 1000)
        },
        yes: function(index, layero){
            clearInterval(zui_inteTimer);
            zui_inteTimer = null;
            $("#J_autoCom_wrap").find(".fa").toggleClass("fn-hide");
            autoCompareFlag = 0;
            clickFlag = 0;
            layer.closeAll();
        },
        cancel: function(){
            clearInterval(zui_inteTimer);
            zui_inteTimer = null;
            $("#J_autoCom_wrap").find(".fa").toggleClass("fn-hide");
            autoCompareFlag = 0;
            clickFlag = 0;
        },
        end: function(){
            _w_time = 3;
            $('#layerMsg span i').text('3');
        },
        offset: 'rt'
    });
}

function goAutoCompare(){
    if(hasCompared){
        //已对码的直接跳过
        getNextDrugs();
        MyFun.to.i("已对码，直接跳转至下一条");
        return false;
    }

    if(searchType){
        //点击了查询按钮，需重新触发快速匹配
        searchType = 0;
        getzPageS();
        return false;
    }

    //锁住按钮
    clickFlag = 1;
    //显示倒计时
    showMyLayer(function(){
        layer.closeAll();
        clearInterval(zui_inteTimer);
        zui_inteTimer = null;
        createBlock(".modal-dialog", '正在努力加载数据...');
        var data = zuiTblS.row(0).data();
        if(!data || !data.id){
            getNextDrugs();
            MyFun.to.i("未找到匹配数据，跳转至下一条");
            unBlock(".ibox-content");
            return false;
        }
        $.PostJson("${request.contextPath}/space/compare.do", "drugid="+curDrugId+"&sdrugid="+data.id+"&spaceid="+curSpaceid, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "对码成功");
                }else{
                    MyFun.to.e(json.message || "对码失败");
                }
            }
            clickFlag = 0;
            changeFlag = 1;
            unBlock(".modal-dialog");

            getNextDrugs();
        })
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

function ignoreDrug(){
    if(clickFlag)
        return false;
    clickFlag = 1;

    layer.confirm("是否确定忽略该品种对照？", {btn: ['确定','取消'] }, function(){
        layer.closeAll('dialog');
        createBlock(".modal-dialog", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/space/ignore.do", "drugid="+curDrugId+"&spaceid="+curSpaceid, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "忽略对码成功");
                }else{
                    MyFun.to.e(json.message || "忽略对码失败");
                }
            }
            clickFlag = 0;
            changeFlag = 1;
            unBlock(".modal-dialog");

            getNextDrugs();
        })
    }, function(){
        clickFlag = 0;
    } );
}

function autoreview(){
    if(clickFlag)
        return false;
    clickFlag = 1;

    layer.confirm("是否开始自动对码？", {btn: ['确定','取消'] }, function(){
        layer.closeAll('dialog');

        createBlock(".wrapper", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/space/autocompare.do", "catalogid="+curCatalogid+"&spaceid="+curSpaceid, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "自动对码成功");
                    $("#J_autoreview").text("正在自动对码中...").attr({"disabled": "disabled"});
                }else{
                    MyFun.to.e(json.message || "自动对码失败");
                }
            }
            clickFlag = 0;
            unBlock(".wrapper");
        })
    }, function(){
        clickFlag = 0;
    } );
}

function checkcompare(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/space/checkcompare.do", "catalogid="+curCatalogid+"&spaceid="+curSpaceid, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data == false){
                    $("#J_autoreview").text("正在自动对码中...").attr({"disabled": "disabled"});
                }else{
                    $("#J_autoreview").text("开始自动对码").removeAttr("disabled");
                }
            }else{
                MyFun.to.e(json.message || "查询自动对码状态失败");
            }
        }
        unBlock(".wrapper");
    })
}

</script>
</body>
</html>