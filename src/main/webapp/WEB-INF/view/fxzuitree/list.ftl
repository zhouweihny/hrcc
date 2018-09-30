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
<link href="${request.contextPath}/js/plugins/ztree/css/metroStyle/metroStyle.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>

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
/* .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}*/
.ztree li span.button.del {margin-left:2px; margin-right: -1px; background-position:-188px -42px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.del:hover {background-position:-167px -42px;}
.ztree li span.button.rename {margin-left:2px; margin-right: -1px; background-position:-188px -21px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.rename:hover {background-position:-167px -21px;}
.ztree li span.button.sort {margin-left:2px; margin-right: -1px; background-position:-188px -63px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.sort:hover {background-position:-167px -63px;}

#zuiTree {
    border: 1px solid #ddd;
    width: 250px;
    height: 400px;
    overflow: auto;
    margin-top: -1px;
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
.J_newKeys {
    width: 172px!important;
    display: inline-block;
    vertical-align: middle;
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
                    <div class="row">
                        <div class="col-md-3">
                            <ul id="zuiTree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <div id="OrderListWrap">
                                <form class="form-inline row" id="J_formSearch" action="javascript:;">
                                    <div class="form-group col-md-4 saledate_wrap">
                                        <label for="J_name">通用名：</label>
                                        <input type="text" class="form-control" placeholder="请输入通用名" id="J_name" name="name" style="width: 142px;">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="spaceid">绑定状态：</label>
                                        <select id="J_status" name="status" class="form-control" style="width: 132px;">
                                            <option value="0" selected="selected">未绑定</option>
                                            <option value="1">已绑定</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <button type="button" class="btn btn-w-m btn-info" onclick="getTreeCommonName();"><i class="fa fa-search"></i>查 询</button>
                                    </div>
                                </form>
                                <div class="m-t">
                                    <a class="btn btn-primary" id="J_addNewName">新增通用名</a>
                                </div>
                                <div>
                                    <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                                        <thead>
                                            <tr role="row">
                                                <th>名称</th>
                                                <th>状态</th>
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
        </div>
    </div>
</div>

<ul id="J_getExitComnameList" class="J_getExitComnameList fn-hide"></ul>

<textarea id="T_getExitComnameList" class="fn-hide">
{{#each this}} 
<li>{{name}}</li>
{{/each}}
</textarea>

<div id="info-form" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>新增通用名<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <form class="form-horizontal" id="J_formSearch" action="javascript:;">
                            <div class="form-group row">
                                <label class="col-md-3 control-label" for="J_name">通用名：</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control J_newComname" placeholder="输入新的通用名">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white m-r" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveNewName();">提交</button>
            </div>
        </div>
    </div>
</div>

<div id="info-formS" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>厂商关键字<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_tree_tbl" aria-describedby="DataTables_Table_0_info">
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
                </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_tree_tbl">
<tr class="gradeX">
    <td>
        <div class="J_addNeWKeyWrap fn-hide">
            <input value="" type="text" class="form-control J_newKeys" placeholder="输入新的关键字">
            <a href="javascript:;" onclick="saveNewKeys(this);">保存</a>
        </div>
    </td>
    <td>
        <a href="javascript:;" onclick="addNewKeys(this);">新增关键字</a>
    </td>
</tr>
{{#each this}}
<tr class="gradeX">
    <td>{{factory}}</td>
    <td>
        <a href="javascript:;" onclick="delFacKeys('{{id}}')">删除</a>
    </td>
</tr>
{{/each}}
</textarea>

<div id="info-sort" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>节点排序</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <form class="form-horizontal" id="J_formSearch" action="javascript:;">
                            <div class="form-group row">
                                <label class="col-md-3 control-label" for="J_sortName">节点名：</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control J_sortName" value="" readonly="readonly">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 control-label" for="J_sortVal">排序值：</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control J_sortVal" placeholder="请输入大于0的正整数">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white m-r" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveSort();">提交</button>
            </div>
        </div>
    </div>
</div>

<script>
var clickFlag = 0,
    zuiTree = null,
    zuiTbl = null,
    newCount = 1,
    curTree3Id = "",
    curDrugId = "",
    pNode = null,
    getExitComnameAjaxTimer = null,
    hideExitComnameTimer = null,
    hideExitComnameFlag = 0,
    rootid = '',
    curStatus = 0,
    curTreePath = '',
    comnameid = '',
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    p_getStoreId = '',
    p_getFileId = '',
    renameFlag = 0,
    oldName = '',
    zanalysis = 0;//根节点analysis为1确定病种

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
        },
        key: {
            title: "title"
        }
    },
    callback: {
        beforeDrag: beforeDrag,
        beforeRename: zTreeBeforeRename,
        onRename: zTreeOnRename,
        onClick: zTreeClick
    }
};

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        p_getStoreId = window.parent.W_p_getStoreId();
        p_getFileId = window.parent.W_p_getSaleFileId();

        var wh = window.parent.W_p_getWindowWh(),
            _w = parseFloat(wh.w * 0.71, 10).toFixed(2),
            _h = parseFloat(wh.h * 0.8, 10).toFixed(2);
        $("#zuiTree").css({"height": _h});
        // $("#OrderList").css("cssText", "width: "+_w+"px !important;table-layout: fixed;").parent().addClass("table-responsive");

    }else{
        $("#zuiTree").css({"height": $(window).height() - 120});
    }

    getZtree();

    $("#J_addNewName").on("click", function(){
        $("#info-form").modal('show');
        $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
            $(".J_newComname").val('');
        })
        $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
            addNewCommname();
        })
    })
})

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
                        attr.sort = json.data[i].sort;
                        attr.title = json.data[i].name+" - "+json.data[i].sort;
                        attr.analysis = json.data[i].analysis;
                        arr.push(attr);
                    }
                }

                $.fn.zTree.init($("#zuiTree"), setting, arr);
                zuiTree = $.fn.zTree.getZTreeObj("zuiTree");

                if(!pId)
                    pId = curTree3Id = '18346A0044A148D1A818627B25510E59';//病种配方树
                var node = zuiTree.getNodeByParam('id', pId, null);
                if(!node.pId)
                    zanalysis = 1;
                zuiTree.expandNode(node);
                zuiTree.selectNode(node);
                getTreeCommonName();

            }else{
                MyFun.to.e(json.message || "查询树失败");
            }
        }
        unBlock(".wrapper");
    })
}

function treeDatafilter(treeId, parentNode, json) {
    if (!json) return null;
    
    return arr;
}

function zTreeClick(event, treeId, treeNode) {
    curTreePath = treeNode.getPath();
    rootid = curTreePath[0].id;
    curTree3Id = treeNode.id;

    if(curTreePath[0].analysis && curTreePath[0].analysis == 1)
        zanalysis = 1;
    else
        zanalysis = 0;

    getTreeCommonName();
}

function beforeDrag(treeId, treeNodes) {
    return false;
}

function addHoverDom(treeId, treeNode) {
    var path = treeNode.getPath();
    if(path.length){
        var sObj = $("#" + treeNode.tId + "_span");

        if(path[0].id == '0646772636684747996D030BE556BB73'){
            //病种分类树
            if(treeNode.level > 1){
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
                        renameFlag = 0;
                        var newTreeNode = zuiTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                        zuiTree.editName(newTreeNode[0]);
                        return false;
                    });
                }
            }
        }else{
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
                        renameFlag = 0;
                        var newTreeNode = zuiTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                        zuiTree.editName(newTreeNode[0]);
                        return false;
                    });
                }
            }
        }

        if(treeNode.level > 0){
            if (treeNode.editNameFlag || $("#renameBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button rename' id='renameBtn_" + treeNode.tId
                + "' title='重命名节点' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#renameBtn_"+treeNode.tId);
            if (btn){
                btn.bind("click", function(){
                    pNode = treeNode;
                    renameFlag = 1;
                    oldName = pNode.name;
                    zuiTree.editName(pNode);
                    return false;
                });
            }

            if (treeNode.editNameFlag || $("#sortBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button sort' id='sortBtn_" + treeNode.tId
                + "' title='节点排序' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#sortBtn_"+treeNode.tId);
            if (btn){
                btn.bind("click", function(){
                    zuiSortTree(treeNode);
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
    $("#renameBtn_"+treeNode.tId).unbind().remove();
    $("#sortBtn_"+treeNode.tId).unbind().remove();
}

function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
    if(!newName){
        zuiTree.cancelEditName();
        return false;
    }
    return true;
}
function zTreeOnRename(event, treeId, treeNode, isCancel){
    if(renameFlag){
        var _newname = treeNode.name;
        if(_newname == oldName){
            return;
        }
        zuiRenameNode(treeNode, treeNode.pId, _newname);

    }else if(treeNode.name.indexOf("new node") !== -1 || !treeNode.name){
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

function zuiRenameNode(treeNode, pId, name){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtree/update.do", "name="+name+"&id="+treeNode.id, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "重命名节点成功");
                renameFlag = 0;
                oldName = '';
                zuiTree.destroy();
                curTree3Id = pId;
                getZtree(pId);
            }else{
                MyFun.to.e(json.message || "重命名节点失败");
            }
        }
        unBlock(".wrapper");
    })
}

function getTreeCommonName(){
    if(!curTree3Id){
        MyFun.to.i("请先选树择节点");
        return false;
    }
    if(zuiTbl){
        // zuiTbl.draw(false);
        zuiTbl.destroy();
        zuiTbl = null;
    }
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
            curStatus = $("#J_status").val();
            var formData = "treeid="+curTree3Id+"&rootid="+rootid+"&status="+curStatus+"&name="+$("#J_name").val();
            formData += '&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

            //ajax请求数据
            $.ajax({
                type: "post",
                url: "${request.contextPath}/fxcomname/treecomnames.do",
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
            { "data": "status" },
            { "data": "" }
        ],
        columnDefs: [
            {
                "render": function(data, type, row) {
                    var buffer = '未绑定';
                    if(curStatus==1)
                        buffer = '<span class="done">已绑定</span>';
                    return buffer;
                },
                "targets": 1
            },
            {
                "render": function(data, type, row) {
                    var buffer = '';
                    if(curStatus == 0){
                        buffer = '<a href="javascript:;" onclick="bindCom(this);" data-name="'+row.name+'">绑定</a>';
                    }else{
                        buffer = '<a href="javascript:;" onclick="deletCommname(this);" data-id="'+row.id+'">删除</a>';
                        if(curTreePath.length && curTreePath[0].id == '36A8746039AB40C39D0B7CD75DE45650'){
                            //扩展树，增加厂商关键字
                            buffer += '<a class="m-l" href="javascript:;" onclick="addFacKeyWrod(this);" data-id="'+row.id+'">厂商关键字</a>'
                        }
                        if(zanalysis){
                            //确认病种
                            if(row.fwflag){
                                //已确定
                                buffer += '<a class="m-l" href="javascript:;" onclick="confirmFWflag(this);" data-type="0" data-id="'+row.id+'">取消确定</a>'
                            }else{
                                //未确定
                                buffer += '<a class="m-l" href="javascript:;" onclick="confirmFWflag(this);" data-type="1" data-id="'+row.id+'">确定病种</a>'
                            }
                        }
                    }
                    return buffer;
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

function addNewCommname(){

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

function saveNewName(){
    var _val = $('.J_newComname').val();
    if(_val){
        createBlock(".modal-dialog", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/fxcomname/add.do", "comname="+_val+"&treeid="+curTree3Id, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message || "新增通用名成功");

                    $("#info-form").modal('hide');
                }else{
                    MyFun.to.e(json.message || "新增通用名失败");
                }
            }
            unBlock(".modal-dialog");

            getZtree(curTree3Id);
        })
    }
}

function deletCommname(obj){
    var _id = $(obj).data("id");
    if(_id){
        layer.confirm('确定删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            
            createBlock(".wrapper", '正在努力加载数据...');
            $.PostJson("${request.contextPath}/fxcomnametree/delete.do", "comnameid="+_id+"&treeid="+curTree3Id, function(state, json){
                if(state == 'success'){
                    if(json && json.code == '0000'){
                        MyFun.to.s(json.message || "删除通用名成功");

                        zuiTbl.draw(false);
                    }else{
                        MyFun.to.e(json.message || "删除通用名失败");
                    }
                }
                unBlock(".wrapper");

                // getZtree(curTree3Id);
            })
        });
    }
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

function delTreeNode(treeNode){
    var _id = treeNode.id;
    if(_id){
        layer.confirm('确定删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            
            createBlock(".wrapper", '正在努力加载数据...');
            $.PostJson("${request.contextPath}/fxtree/delete.do", "id="+_id, function(state, json){
                if(state == 'success'){
                    if(json && json.code == '0000'){
                        MyFun.to.s(json.message || "删除节点成功");
                    }else{
                        MyFun.to.e(json.message || "删除节点失败");
                    }
                }
                unBlock(".wrapper");
                curTree3Id = treeNode.pId;
                getZtree(treeNode.pId);
            })
        });
    }
}

function showExitComname(){
    var _this = $(".J_newComname"),
        name = _this.val().replace(/(^\s*)|(\s*$)/g,"");
        _left = _this.offset().left,
        _top = _this.offset().top + 34,
        _with = _this.width() + 24,
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
                    _J_getExitComnameList.removeClass("fn-hide").css({"left": _left, "top": _top, "width": _with}).temp($("#T_getExitComnameList").val(), cdata);

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

function saveBindNode(name, type){
    if(type && type == '2'){
        hideExitComnameFlag = 1;
        $(".J_newComname").val('');
        $("#J_getExitComnameList").html('').addClass("fn-hide");
    }

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/add.do", "comname="+name+"&treeid="+curTree3Id, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "新增通用名成功");

                $("#info-form").modal('hide');
            }else{
                MyFun.to.e(json.message || "新增通用名失败");
            }
        }
        if(type && type == '2')
            hideExitComnameFlag = 0;
        unBlock(".wrapper");

        getZtree(curTree3Id);
    })
}

function bindCom(obj){
    var _name = $(obj).data("name");
    if(!_name){
        MyFun.to.i("数据错误");
        return false;
    }
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomname/add.do", "comname="+_name+"&treeid="+curTree3Id, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "绑定通用名成功");

                // getZtree(curTree3Id);

                zuiTbl.draw(false);
            }else{
                MyFun.to.e(json.message || "绑定通用名失败");
            }
        }
        unBlock(".wrapper");
    })
}

function addFacKeyWrod(obj){
    comnameid = $(obj).data("id");
    $("#info-formS").modal('show');
    $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $(".J_newKeys").val('');
        $("#J_tree_tbl tbody").html("'");
    })
    $('#info-formS').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        showFacKeys();
    })
}

function showFacKeys(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomnametreefactory/factorys.do", "comnameid="+comnameid+"&treeid="+curTree3Id, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                
                $("#J_tree_tbl tbody").temp($("#T_tree_tbl").val(), json.data);

            }else{
                MyFun.to.e(json.message || "查询厂商关键字失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function addNewKeys(obj){
    $(obj).parent().prev().find(".J_addNeWKeyWrap").removeClass("fn-hide").find("input").focus();
}
function saveNewKeys(obj){
    var _val = $(obj).prev("input").val();
    if(!_val)
        return false;
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomnametreefactory/save.do", "comnameid="+comnameid+"&treeid="+curTree3Id+"&factory="+_val, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "新增厂商关键字成功");

                // $("#info-formS").modal('hide');
                showFacKeys();
            }else{
                MyFun.to.e(json.message || "新增厂商关键字失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function delFacKeys(_id){
    if(!_id)
        return false;
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomnametreefactory/delete.do", "id="+_id, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "删除厂商关键字成功");
                showFacKeys();
            }else{
                MyFun.to.e(json.message || "删除厂商关键字失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function zuiSortTree(treeNode){
    pNode = treeNode.pId;
    curTree3Id = treeNode.id;

    $("#info-sort").modal('show');
    $('#info-sort').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        
    })
    $('#info-sort').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        $(".J_sortVal").val(treeNode.sort || '');
        $(".J_sortName").val(treeNode.name);
    })
}
function saveSort(){
    var _val = $(".J_sortVal").val() || '';
    _val = parseInt(_val, 10) || 0;

    if(!_val){
        MyFun.to.i("请输入大于0的正整数");
        return false;
    }

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtree/save.do", "id="+curTree3Id+"&sort="+_val, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "设置节点排序成功");

                $("#info-sort").modal('hide');

                zuiTree.destroy();
                curTree3Id = pNode;
                getZtree(curTree3Id);
            }else{
                MyFun.to.e(json.message || "设置节点排序失败");
            }
        }
        unBlock(".wrapper");
    })
}

function confirmFWflag(obj){
    var _this = $(obj),
        _type = _this.data("type"),
        _id = _this.data("id"),
        res = '确定病种',
        resOppo = '取消确定',
        resSucc = '确定病种成功',
        resFail = '确定病种失败';

    if(!_type){
        res = '取消确定';
        resOppo = '确定病种',
        resSucc = '取消确定病种成功';
        resFail = '取消确定病种失败';
    }

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxcomnametree/update.do", "comnameid="+_id+"&fwflag="+_type+"&treeid="+curTree3Id, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || resSucc);
                _type = (_type == 1) ? 0 : 1;
                _this.text(resOppo).data("type", _type);
            }else{
                MyFun.to.e(json.message || resFail);
            }
        }
        unBlock(".wrapper");
    })
}
</script>
</body>
</html>