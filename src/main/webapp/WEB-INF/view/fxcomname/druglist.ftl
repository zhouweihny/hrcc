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
<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<link href="${request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<link href="${request.contextPath}/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/BootstrapMenu/BootstrapMenu.min.js"></script>
<script src="${request.contextPath}/js/plugins/rangy/rangy-core.js"></script>

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
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.ztree li span.button.bind {margin-left:2px; margin-right: -1px; background-position:-126px -64px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.del {margin-left:2px; margin-right: -1px; background-position:-110px -65px; vertical-align:top; *vertical-align:middle}

#zuiTree {
    border: 1px solid #ddd;
    width: 250px;
    height: 400px;
    overflow: auto;
    margin-top: -1px;
}
.J_gobackwrap {
    position: relative;
}
.J_gobackwrap a {
    margin: 6px 0 0;
    border: 1px solid #ddd;
    width: 250px;
    height: 40px;
    line-height: 40px;
    display: block;
    padding-left: 8px;
}
.J_gobackwrap h4 {
    border: 1px solid #ddd;
    padding: 12px 10px 11px;
    margin: 0;
    position: absolute;
    top: 0;
    left: 109px;
    cursor: default;
}
#info-formS .modal-dialog .modal-content .ibox-content {
    max-height: 500px;
    height: 500px;
    overflow-y: auto;
}
.J_newComname {
    width: 172px!important;
    display: inline-block;
    vertical-align: middle;
}
#J_formSearch label {
    min-width: 51px;
}

.dropdown-menu {
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1000;
    display: none;
    float: left;
    min-width: 160px;
    padding: 5px 0;
    margin: 2px 0 0;
    font-size: 14px;
    text-align: left;
    list-style: none;
    background-color: #fff;
    -webkit-background-clip: padding-box;
    background-clip: padding-box;
    border: 1px solid #ccc;
    border: 1px solid rgba(0,0,0,.15);
    border-radius: 4px;
    -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
    box-shadow: 0 6px 12px rgba(0,0,0,.175);
}
.dropdown-menu>li>a {
    display: block;
    padding: 6px 20px;
    clear: both;
    font-weight: 400;
    line-height: 1.42857143;
    color: #333;
    white-space: nowrap;
    font-size: 15px;
}
.J_getExitComnameList {
    position: absolute;
    left: 0;
    top: 0;
    background: #fff;
    border: 1px solid #ddd;
    z-index: 9999;
    list-style: none;
    padding: 0;
    margin: 0;
    box-shadow: 3px 3px 3px rgba(0,0,0,.3);
    max-width: 400px;
    max-height: 300px;
    overflow: auto;
}
.J_getExitComnameList:before {
    position: absolute;
    left: 10px;
    top: -16px;
    display: block;
    content: '';
    border-width: 8px;
    border-style: solid;
    border-color: transparent transparent #ddd transparent;
}
.J_getExitComnameList li {
    padding: 0 5px;
    cursor: pointer;
    border-bottom: 1px solid #ddd;
    height: 38px;
    line-height: 38px;
    overflow: hidden;
}
.J_getExitComnameList li:last-child {
    border-bottom: none;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>品种目录</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label for="J_name">品名：</label>
                                <input type="text" class="form-control" id="J_name" placeholder="输入品名" name="name">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="J_name">厂商：</label>
                                <input type="text" class="form-control" id="J_name" placeholder="输入厂商" name="factory">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="J_name">是否对照：</label>
                                <select name="dz" class="form-control">
                                    <option value="">所有</option>
                                    <option value="0">未对照</option>
                                    <option value="1">已对照</option>
                                </select>
                            </div>
                        </div>
                        <div class="row m-t">
                            <div class="form-group col-md-4">
                                <label for="J_name">用户名：</label>
                                <input type="text" class="form-control" id="J_name" placeholder="输入用户名" name="username">
                            </div>
                            
                            <div class="form-group col-md-4">
                                <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','DrugList');"><i class="fa fa-search"></i>查 询</button>
                            </div>
                        </div>
                    </form>
                    <div id="DrugList" data-url="${request.contextPath}/fxcomname/drugtable.do"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<div id="info-formS" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>通用名绑定<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container" id="J_formBind">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>名称</th>
                                    <th>规格</th>
                                    <th>单位</th>
                                    <th>剂型</th>
                                    <th>厂商</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                        <form class="form-inline row J_formSearch_D" id="J_formSearch_D" action="javascript:;">
                            <div class="form-group col-md-3">
                                <label for="spaceid">名称：</label>
                                <input type="text" name="name" value="" id="J_formSearch_D_name" class="form-control" placeholder="请输入通用名" />
                            </div>
                            <div class="form-group col-md-9">
                                <button type="button" class="btn btn-w-m btn-info" id="J_searchBtn_D"><i class="fa fa-search"></i>查 询</button>

                                <a href="javascript:;" id="J_auto_compare" style="margin-left: 10px;">手动新增</a>
                            </div>
                        </form>
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>名称</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>

                    <div class="tabs-container fn-hide" id="J_ztreeBind">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="J_gobackwrap">
                                    <a href="javascript:;" hidefocus="true" id="J_gobackwrap">
                                        <i class="fa fa-chevron-left" aria-hidden="true"></i>
                                        <span>返回</span>
                                    </a>
                                </div>
                                <ul id="zuiTree" class="ztree"></ul>
                            </div>
                            <div class="col-md-9">
                                <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_tree_tbl" aria-describedby="DataTables_Table_0_info">
                                    <thead>
                                        <tr role="row">
                                            <th>名称</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr><td colspan="2" style="text-align: center;">
                                            请点击左侧树目录，查询节点下的通用名列表
                                        </td></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<ul id="J_getExitComnameList" class="J_getExitComnameList fn-hide"></ul>

<textarea class="fn-hide" id="T_tree_tbl">
<tr class="gradeX">
    <td>
        <div class="J_addNeWrap fn-hide">
            <input value="" type="text" class="form-control J_newComname" placeholder="输入新的通用名">
            <a href="javascript:;" onclick="saveNewName(this);">保存</a>
        </div>
    </td>
    <td>
        <a href="javascript:;" onclick="addNewCommname(this);">新增通用名</a>
    </td>
</tr>
{{#each this}}
<tr class="gradeX">
    <td>{{name}}</td>
    <td>
        <a href="javascript:;" onclick="bindCom('{{id}}')">绑定</a>

        <a href="javascript:;" onclick="deletCommname(this);" data-id="{{id}}" style="display: inline-block; margin-left: 20px;">删除</a>
    </td>
</tr>
{{/each}}
</textarea>

<textarea id="T_getExitComnameList" class="fn-hide">
{{#each this}} 
<li>{{name}}</li>
{{/each}}
</textarea>

<script>
var clickFlag = 0,
    zuiTbl = null,
    zuiTblS = null,
    curDrugId = "",
    zuiTree = null,
    newCount = 1,
    curTree3Id = "",
    getExitComnameAjaxTimer = null,
    hideExitComnameTimer = null,
    hideExitComnameFlag = 0;

var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false
    },
    edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false,
        editNameSelectAll: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeDrag: beforeDrag,
        onRename: zTreeOnRename,
        onClick: zTreeClick
    }
};

function refreshDrugList(){
    MyFun.ajaxRefreshTable("DrugList");
}

$(function(){
    MyFun.ajaxRefreshTable("DrugList", "", function(){
        new BootstrapMenu('.J_fac_keywords', {
            fetchElementData: function(rowElem) {
                return $(rowElem);
            },
            actionsGroups: [
                ['add']
            ],
            actions: {
                add: {
                    name: '新增通用名',
                    onClick: function(row) {
                        handleSelection();
                    }
                }
            }
        });
    });

    $("#J_auto_compare").on("click", function(){
        $("#J_formBind").addClass("fn-hide");
        $("#J_ztreeBind").removeClass("fn-hide");
    })
    $("#J_gobackwrap").on("click", function(){
        $("#J_formBind").removeClass("fn-hide");
        $("#J_ztreeBind").addClass("fn-hide");
    })
    $("#J_searchBtn_D").on("click", function(){
        if(zuiTblS){
            zuiTblS.destroy();
            zuiTblS = null;
        }
        getzPageS();
    })

    autoComname();
})

function bindComoname(obj){
    var _selft = $(obj),
        name = _selft.data("name"),
        specifications = _selft.data("specifications"),
        unit = _selft.data("unit"),
        dosageform = _selft.data("dosageform"),
        factory = _selft.data("factory");
    curDrugId = _selft.data("id");
    
    $("#J_formBind").removeClass("fn-hide");
    $("#J_ztreeBind").addClass("fn-hide");

    var _tr = '<tr><td>'+(name || "")+'</td><td>'+(specifications || "")+'</td><td>'+(unit || "")+'</td><td>'+(dosageform || "")+'</td><td>'+(factory || "")+'</td></tr>';
    $("#J_top_tbl tbody").html(_tr);

    $("#info-formS").modal('show');
    $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        if(zuiTbl){
            zuiTbl.destroy();
            zuiTbl = null;
        }
        if(zuiTblS){
            zuiTblS.destroy();
            zuiTblS = null;
        }

        $("#J_formSearch_D").resetForm();
        $("#J_tree_tbl tbody").html("");
    })

    $('#info-formS').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        getzPageS();

        getZtree();
    })
}

function getzPageS(){
    $('#J_bottom_tbl tbody').html("");

    zuiTblS = $('#J_bottom_tbl').DataTable( {
        language: {
            sProcessing: "正在努力加载数据..."
        },
        dom: '<"top">rt<"bottom"flpi><"clear">',
        ordering: false,//关闭表格的排序功能
        serverSide: true,  //启用服务器端分页
        processing: true,  //隐藏加载提示,自行处理
        searching: false, // 禁用搜索
        ajax: function (data, callback, settings) {
            var formData = $("#J_formSearch_D").formSerialize();
            formData += '&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

            //ajax请求数据
            $.ajax({
                type: "post",
                url: "${request.contextPath}/fxcomname/fxcomnames.do",
                cache: false,  //禁用缓存
                data: formData,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    //封装返回数据
                    var returnData = {};
                    returnData.draw = data.draw;
                    if(result && result.totalRows){
                        returnData.recordsTotal = result.totalRows;//返回数据全部记录
                        returnData.recordsFiltered = result.totalRows;
                        returnData.data = result.data;//返回的数据列表
                    }else{
                        returnData.recordsTotal = 0;
                        returnData.recordsFiltered = 0;
                        returnData.data = [];
                    }

                    //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                    //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                    callback(returnData);
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
                    
                }
            });
        },
        columns: [
            { "data": "name", "width":"280px" },
            { "data": "", "width":"70px" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row) {
                    return '<a href="javascript:;" class="J_compare" onclick="bindCom(\''+row.id+'\');">绑定</a>';
                },
                "targets": 1
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
}

function getZtree(pId){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/tree.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                var arr = [];
                if(json.code == '0000'){
                    for (var i=0, l=json.data.length; i<l; i++) {
                        var attr = {};
                        attr.id = json.data[i].id;
                        attr.pId = json.data[i].parentid;
                        attr.name = json.data[i].name;
                        arr.push(attr);
                    }
                }

                $.fn.zTree.init($("#zuiTree"), setting, arr);
                zuiTree = $.fn.zTree.getZTreeObj("zuiTree");

                if(pId){
                    var node = zuiTree.getNodeByParam('id', pId, null);
                    zuiTree.expandNode(node, true, true, true);
                    zuiTree.selectNode(node);
                    getTreeCommonName();
                }
            }else{
                MyFun.to.e(json.message || "查询树失败");
            }
        }
        unBlock(".wrapper");
    })
}

function treeDatafilter(treeId, parentNode, json) {
    if (!json) return null;
    var arr = [];
    if(json.code == '0000'){
        for (var i=0, l=json.data.length; i<l; i++) {
            var attr = {};
            attr.id = json.data[i].id;
            attr.pId = json.data[i].parentid;
            attr.name = json.data[i].name;
            arr.push(attr);
        }

    }
    return arr;
}

function zTreeClick(event, treeId, treeNode) {
    curTree3Id = treeNode.id;
    getTreeCommonName();
}

function getTreeCommonName(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/treecomnames.do", "treeid="+curTree3Id, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data && json.data.data && json.data.data.length){
                    var arr = [];
                    for (var i=0, l=json.data.data.length; i<l; i++) {
                        var attr = {};
                        attr.id = json.data.data[i].id;
                        attr.pId = json.data.data[i].treeid;
                        attr.name = json.data.data[i].name;
                        arr.push(attr);
                    }
                }

                if(zuiTbl){
                    zuiTbl.draw(false);
                    zuiTbl.destroy();
                    zuiTbl = null;
                }

                $("#J_tree_tbl tbody").temp($("#T_tree_tbl").val(), arr);

                zuiTbl = $('#J_tree_tbl').DataTable({
                    language: {
                        sProcessing: "正在努力加载数据..."
                    },
                    dom: '<"top">rt<"bottom"flpi><"clear">',
                    ordering: false,//关闭表格的排序功能
                    serverSide: false,  //启用服务器端分页
                    processing: true,  //隐藏加载提示,自行处理
                    searching: false, // 禁用搜索
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
                MyFun.to.e(json.message || "查询该节点下通用名失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function beforeDrag(treeId, treeNodes) {
    return false;
}

function addHoverDom(treeId, treeNode) {
    var path = treeNode.getPath();
    if(path.length){
        var sObj = $("#" + treeNode.tId + "_span");
        if(treeNode.level > 2){
            if (treeNode.editNameFlag || $("#delBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button del' id='delBtn_" + treeNode.tId
                + "' title='删除节点' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#delBtn_"+treeNode.tId);
            if (btn){
                btn.bind("click", function(){
                    delTreeNode(treeNode);
                    return false;
                });
            }
        }else{
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='新增节点' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn){
                btn.bind("click", function(){
                    pNode = treeNode;
                    var newTreeNode = zuiTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                    zuiTree.editName(newTreeNode[0]);
                    return false;
                });
            }
        }
    }
}

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
    $("#bindBtn_"+treeNode.tId).unbind().remove();
    $("#delBtn_"+treeNode.tId).unbind().remove();
}

function zTreeOnRename(event, treeId, treeNode, isCancel){
    if(treeNode.name.indexOf("new node") !== -1 || !treeNode.name){
        zuiTree.removeNode(treeNode);
    }else{
        zuiAddNode(treeNode, treeNode.pId, treeNode.name);
    }
}

function zuiAddNode(treeNode, pId, name){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtree/save.do", "name="+name+"&parentid="+pId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "新增节点成功");

                zuiTree.destroy();
                curTree3Id = pId;
                getZtree(pId);
            }else{
                MyFun.to.e(json.message || "新增节点失败");
            }
        }
        unBlock(".wrapper");
    })
}

function bindCom(id){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/binding.do", "comnameid="+id+"&drugid="+curDrugId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "绑定通用名成功");

                // $("#info-formS").modal('hide');
                refreshDrugList();
            }else{
                MyFun.to.e(json.message || "绑定通用名失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function addNewCommname(obj){
    $(obj).parent().prev().find(".J_addNeWrap").removeClass("fn-hide").find("input").focus();

    var _J_newComname = $(".J_newComname"),
        _J_getExitComnameList = $("#J_getExitComnameList");

    _J_newComname.off("keyup").on("keyup", function(){
        showExitComname();
    })
    _J_newComname.off("blur").on("blur", function(){
        if(hideExitComnameTimer)
            clearTimeout(hideExitComnameTimer);
        hideExitComnameTimer = setTimeout(function(){
            if(!hideExitComnameFlag){
                _J_getExitComnameList.addClass("fn-hide");
                clearTimeout(hideExitComnameTimer);
            }
        }, 500)
    })
    _J_newComname.off("focus").on("focus", function(){
        if(_J_newComname.val() && _J_getExitComnameList.find("li").length){
            _J_getExitComnameList.removeClass("fn-hide");
        }
    })
}

function saveNewName(obj){
    var _val = $(obj).prev("input").val();
    if(_val)
        saveBindNode(_val);
}

function saveBindNode(name, type){
    if(type && type == '2'){
        hideExitComnameFlag = 1;
        $(".J_newComname").val('');
        $("#J_getExitComnameList").html('').addClass("fn-hide");
    }
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/add.do", "comname="+name+"&treeid="+curTree3Id+"&drugid="+curDrugId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "绑定通用名成功");

                // $("#info-formS").modal('hide');
                getTreeCommonName();
                refreshDrugList();
            }else{
                MyFun.to.e(json.message || "绑定通用名失败");
            }
        }
        if(type && type == '2')
            hideExitComnameFlag = 0;
        unBlock(".modal-dialog");
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

function deletCommname(obj){
    var _id = $(obj).data("id");
    if(_id){
        layer.confirm('确定删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            
            createBlock(".modal-dialog", '正在努力加载数据...');
            $.PostJson("${request.contextPath}/fxcomname/delete.do", "id="+_id, function(state, json){
                if(state == 'success'){
                    if(json && json.code == '0000'){
                        MyFun.to.s(json.message || "删除通用名成功");
                    }else{
                        MyFun.to.e(json.message || "删除通用名失败");
                    }
                }
                unBlock(".modal-dialog");

                getZtree(curTree3Id);
            })
        });
    }
}

function delTreeNode(treeNode){
    var _id = treeNode.id;
    if(_id){
        layer.confirm('确定删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            
            createBlock(".modal-dialog", '正在努力加载数据...');
            $.PostJson("${request.contextPath}/fxtree/delete.do", "id="+_id, function(state, json){
                if(state == 'success'){
                    if(json && json.code == '0000'){
                        MyFun.to.s(json.message || "删除节点成功");
                    }else{
                        MyFun.to.e(json.message || "删除节点失败");
                    }
                }
                unBlock(".modal-dialog");
                curTree3Id = treeNode.pId;
                getZtree(treeNode.pId);
            })
        });
    }
}

function handleSelection() {
    rangySelectionText = rangy.getSelection().toString().replace(/[\r\n]/g, "").replace(/\s+/g, "");
    if (!rangySelectionText) {
        MyFun.to.i("请选择关键字");
        return false;
    }

    if (clickFlag)
        return false;
    clickFlag = 1;
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson('${request.contextPath}/fxcomname/save.do', "name=" + rangySelectionText, function(state, json) {
        if (state == 'success') {
            if (json && json.code == '0000') {
                MyFun.to.s(json.message || "操作成功");
                refreshDrugList();
            } else {
                MyFun.to.e(json.message || "操作失败");
            }
        }
        clickFlag = 0;
        unBlock(".wrapper");
    })
}

function showExitComname(){
    var _this = $(".J_newComname"),
        name = _this.val().replace(/(^\s*)|(\s*$)/g,"");
        _left = _this.offset().left,
        _top = _this.offset().top + 34,
        _with = _this.width(),
        _J_getExitComnameList = $("#J_getExitComnameList");
    if(!name)
        return false;

    if(getExitComnameAjaxTimer)
        clearTimeout(getExitComnameAjaxTimer);
    getExitComnameAjaxTimer = setTimeout(function(){
        $.PostJson('${request.contextPath}/fxcomname/fxcomnames.do', "currentPage=1&pageSize=999&name="+name, function(state, json) {
            if(state == 'success'){
                if(json && json.totalRows){
                    // var cdata = json.data.splice(0, 10);
                    var cdata = json.data;
                    _J_getExitComnameList.removeClass("fn-hide").css({"left": _left, "top": _top}).temp($("#T_getExitComnameList").val(), cdata);

                    _J_getExitComnameList.off("click").on("click", "li", function(){
                        saveBindNode($(this).text(), '2');
                    })
                }else{
                    _J_getExitComnameList.html('').addClass("fn-hide");
                }
            }
        })
    }, 600)
}

function autoComname(){
    $('#J_formSearch_D_name').on("keyup", function(event){
        if(event.keyCode==13){
            $("#J_searchBtn_D").click();
            return false;
        }
    })

    /*$('#J_formSearch_D_name').select2({
        ajax: {
            url: "${request.contextPath}/fxcomname/fxcomnames.do",
            type : "POST",
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
                var _cdata = data.data,
                    // _cArr = [{"id": "-1", "text": "全部"}];
                    _cArr = [];
                if(_cdata.length){
                    for(var i=0, len=_cdata.length; i<len; i++){
                        var attr = {};
                        attr.id = _cdata[i].name;
                        attr.text = _cdata[i].name;
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
        minimumInputLength: 0,
        placeholder: "请输入通用名",
        allowClear: true,
        language: "zh-CN",
        dropdownAutoWidth: true
    })*/
}
</script>
</body>
</html>