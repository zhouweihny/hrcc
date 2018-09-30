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
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<style>
.zuiTreeCheck {
    cursor: pointer;
}
.zuiTreeCheck .fa {
    width: 25px;
    height: 25px;
    text-align: center;
    line-height: 25px;
    font-size: 22px;
    cursor: pointer;
    vertical-align: middle;
}
.zuiTreeCheck .fa-square-o {
    display: inline-block;
    font-size: 24px;
}
#info-formS .modal-dialog {
    width: 1100px!important;
}
#info-formS .modal-open .modal .ibox-content {
    /*max-height: 550px;*/
}
#info-formS .OrderList_paginate_jump {
    float: right;
    height: 25px;
    line-height: 25px;
    margin-left: 15px;
}
#info-formS .OrderList_paginate_jump span {
    cursor: default;
}
#info-formS .OrderList_paginate_jump input {
    width: 38px;
    height: 27px;
    line-height: 27px;
    margin: 0 3px;
    font-size: 14px;
    text-align: center;
    border: 1px solid #ddd;
    border-radius: 4px;
}
#info-formS .OrderList_paginate_jump .btn {
    margin-left: 10px;
}
#info-formS div.dataTables_info {
    padding-top: 5px;
}
#info-formS div.dataTables_length label {
    margin-right: 10px;
}
#info-formS .dataTables_wrapper {
    padding-bottom: 0; 
}
#info-formS div.dataTables_filter label {
    float: left;
}
</style>

</head>
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户管理</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="J_name">用户名：</label>
                            <input type="text" class="form-control" id="J_username" placeholder="输入用户名" name="username">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">手机号：</label>
                            <input type="text" class="form-control" id="J_mobile" placeholder="输入手机号" name="mobile">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">姓名：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入姓名" name="name">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','UserList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                        <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/user/byid.do','info-form')">新增</a>
                    </div>
                    <div id="UserList" data-url="${request.contextPath}/user/table.do"></div>
               </div>
            </div>
        </div>
    </div>
</div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>
<div id="info-formS" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>选择门店</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info" id="OrderList">
                            <thead>
                                <tr role="row">
                                    <th>名称</th>
                                    <th>地址</th>
                                    <th style="text-align:right;">操作</th>
                                </tr>
                            </thead>
                            <tbody id="J_storeList">
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white m-r" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-danger" onclick="bindUserStores();">提交</button>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_storeList">
{{#each this}}
<tr>
    <td>{{name}}</td>
    <td>{{address}}</td>
    <td style="text-align:right;">
        {{#if disabled}}
        <div class="zuiTreeCheck" data-storeid="{{id}}">
            <i class="fa fa-check-square-o" aria-hidden="true"></i>
        </div>
        {{else}}
        <div class="zuiTreeCheck" data-storeid="{{id}}">
            <i class="fa fa-square-o" aria-hidden="true"></i>
        </div>
        {{/if}}
    </td>
</tr>
{{/each}}
</textarea>

<script>
var userid = '',
    zuiTbl = null,
    clickFlag = 0;

function refreshUserList(){
   	MyFun.ajaxRefreshTable("UserList");
}

$(function(){
     MyFun.ajaxRefreshTable("UserList");
})

function bindStores(zuserid){
    userid = zuserid;
    $("#info-formS").modal('show');
    $('#info-formS').off('hidden.bs.modal').off('shown.bs.modal').on('hidden.bs.modal', function (e) {
        if(zuiTbl){
            zuiTbl.destroy();
            zuiTbl = null;
        }
        $("#J_storeList").html('');
    }).on('shown.bs.modal', function(e){
        getStores();
    })
}

function getStores(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/userstore/storesbyuserid.do", "userid="+userid, function(state, json){
        if(state == 'success'){
            if(json && json.data.length){
                $("#J_storeList").temp($("#T_storeList").val(), json.data);

                $("#J_storeList").off("click").on("click", ".zuiTreeCheck", function(){
                    var _this = $(this),
                        _fa = _this.find(".fa");
                    if(_fa.hasClass("fa-square-o"))
                        _fa.removeClass("fa-square-o").addClass("fa-check-square-o");
                    else
                        _fa.addClass("fa-square-o").removeClass("fa-check-square-o");
                })

                zuiTbl = $('#OrderList').DataTable({
                    language: {
                        sProcessing: "正在努力加载数据...",
                        sSearch: "筛选："
                    },
                    dom: '<"top">frt<"bottom">lpi<"clear">',
                    ordering:true, //打开表格的排序功能
                    order: [], //默认不排序
                    // order: [[ 1, "desc" ]], //初始化以第二列排序
                    serverSide: false,  //启用服务器端分页
                    processing: true,  //隐藏加载提示,自行处理
                    searching: true, // 开启搜索
                    search: {
                        smart: true,
                        regex: true
                    },
                    columnDefs: [
                        {"orderable": false, "targets": 2}
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
                MyFun.to.e(json.message || "查询药店列表失败");
            }
        }
        unBlock(".wrapper");
    })
}

function bindUserStores(){
    if(clickFlag)
        return false;
    clickFlag = 1;
    var storeids = [];
    $(".fa-check-square-o").each(function(){
        var storeid = $(this).parent().data("storeid");
        storeids.push(storeid);
    })
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/userstore/adduserstore.do", "userid="+userid+"&storeids="+storeids.join(","), function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "绑定门店成功");

                $("#info-formS").modal('hide');
            }else{
                MyFun.to.e(json.message || "绑定门店失败");
            }
        }
        clickFlag = 0;
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
</script>
</body>
</html>