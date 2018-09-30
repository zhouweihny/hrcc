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
<link href="${request.contextPath}/js/plugins/ztree/css/metroStyle/metroStyle.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<style>
#zuiTree {
    border: 1px solid #ddd;
    width: 100%;
    height: 400px;
    overflow: auto;
    margin-top: 5px;
}
#supplierid {
    display: inline-block;
}
.select2-container {
    margin-right: 10px;
}
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
.J_showdetail {
    cursor: pointer;
}
.zChildTr {
    /*display: none;*/
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
.select2-container {
    margin-bottom: 8px;
}
#J_formSearch label {
    vertical-align: middle;
}
.ztree li span.button.del {margin-left:2px; margin-right: -1px; background-position:-188px -42px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.del:hover {background-position:-167px -42px;}
.ztree li span.button.rename {margin-left:2px; margin-right: -1px; background-position:-188px -21px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.rename:hover {background-position:-167px -21px;}
.ztree li span.button.sort {margin-left:2px; margin-right: -1px; background-position:-188px -63px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.sort:hover {background-position:-167px -63px;}

#J_saveBtn {
    float: right;
}
.name {
    width: 120px;
}
.text {
    width: 520px;
}
.btns a {

}
.J_up {

}
.J_down {

}
.J_del {

}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>联合用药文章编辑</h5>
                </div>
                <div class="ibox-content">
                    <div class="m-t">
                        
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <ul id="zuiTree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <div class="m-b fn-clear">
                                <button type="button" class="btn btn-primary" id="J_addRulesRow">新增一行</button>
                                <button type="button" class="btn btn-danger" id="J_saveBtn">提交</button>
                            </div>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                                <thead>
                                    <tr role="row">
                                        <th>条目</th>
                                        <th>内容</th>
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

<textarea class="fn-hide" id="T_body">
{{#each this}}
<tr data-id="{{id}}">
    <td>
        <input type="text" class="form-control name" value="{{name}}">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容" value="{{text}}">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
{{/each}}
</textarea>
<textarea class="fn-hide" id="T_body_default">
<tr>
    <td>
        <input type="text" class="form-control name" value="常识判断">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
<tr>
    <td>
        <input type="text" class="form-control name" value="发病季节">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
<tr>
    <td>
        <input type="text" class="form-control name" value="症状">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
<tr>
    <td>
        <input type="text" class="form-control name" value="用药原则">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
<tr>
    <td>
        <input type="text" class="form-control name" value="一般用药">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
<tr>
    <td>
        <input type="text" class="form-control name" value="联合用药">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
<tr>
    <td>
        <input type="text" class="form-control name" value="建议">
    </td>
    <td>
        <input type="text" class="form-control text" placeholder="输入条目内容">
    </td>
    <td class="btns">
        <a href="javascript:;" class="J_up">上移一行</a>
        <a href="javascript:;" class="J_down">下移一行</a>
        <a href="javascript:;" class="J_del">删除</a>
    </td>
</tr>
</textarea>

<script>

var zuiTree = null,
    clickFlag = 0,
    G_json = null,
    curTreeId = curStoreId = '',
    adminFlag = 0,
    cwordid = '';

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

$(function(){
    var _h = parseFloat($(window).height() * 0.8, 10).toFixed(2);
    $("#zuiTree").css({"height": _h});
    getzTree();

    $("#J_saveBtn").on("click", function(){
        saveDatas();
    })

    $("#J_addRulesRow").on("click", function(){
        if(!curTreeId){
            MyFun.to.i("请选择节点");
            return false;
        }
        var _tr = '<tr> <td> <input type="text" class="form-control name" value=""> </td> <td> <input type="text" class="form-control text" placeholder="输入条目内容"> </td> <td class="btns"> <a href="javascript:;" class="J_up">上移一行</a> <a href="javascript:;" class="J_down">下移一行</a> <a href="javascript:;" class="J_del">删除</a> </td> </tr>';
        $("#J_tbl_body").append(_tr);
        initBtns();
    })
})

function getzTree(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtree/analysistree.do", "", function(state, json){
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

                var zuiNodes = zuiTree.transformToArray(zuiTree.getNodes());
                var node = zuiNodes[0];
                // curTreeId = node.id;
                zuiTree.expandNode(node);

            }else{
                MyFun.to.e(json.message || "查询病种分类树失败");
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
    if(treeNode.level == 2){
        if(clickFlag)
            return false;
        clickFlag = 1;
        curTreeId = treeNode.id;
        getDetail();
    }else{
        MyFun.to.i("请选择第三级节点");
    }
}

function beforeDrag(treeId, treeNodes) {
    return false;
}

function addHoverDom(treeId, treeNode) {
    return false;
}

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
    $("#bindBtn_"+treeNode.tId).unbind().remove();
}

function zTreeOnRename(event, treeId, treeNode, isCancel){
    if(treeNode.name.indexOf("new node") !== -1 || !treeNode.name){
        zuiTree.removeNode(treeNode);
    }else{
        zuiAddNode(treeNode.pId, treeNode.name);
    }
}

function getDetail(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxlhyydc/byid.do", "treeid="+curTreeId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data){
                    var content = json.data.content;
                    content = JSON.parse(content);

                    cwordid = json.data.id;

                    $("#J_tbl_body").temp($("#T_body").val(), content.list);
                }else{
                    cwordid = '';
                    $("#J_tbl_body").temp($("#T_body_default").val(), {});
                }
            }else{
                MyFun.to.e(json.message || "查询文章信息失败");
            }
        }
        clickFlag = 0;
        initBtns();
        unBlock(".wrapper");
    })
}

function initBtns(){
    $("#J_tbl_body").off("click").on("click", ".J_up", function(){
        var _this = $(this),
            _tr = _this.parent().parent();
        if(_tr.prev().length)
            _tr.prev().before(_tr);
        else
            MyFun.to.i("已经是第一个了");
    }).on("click", ".J_down", function(){
        var _this = $(this),
            _tr = _this.parent().parent();
        if(_tr.next().length)
            _tr.next().after(_tr);
        else
            MyFun.to.i("已经是最后一个了");
    }).on("click", ".J_del", function(){
        var _this = $(this),
            _tr = _this.parent().parent();
        _tr.remove();
    })

}

function saveDatas(){
    var cattr = {},
        arr = [],
        emptyFlag = 0;

    $("#J_tbl_body tr").each(function(){
        var _this = $(this);
        var attr = {
            id: _this.data("id") || '',
            name: _this.find(".name").val().replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26"),
            text: _this.find(".text").val().replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26")
        }

        if(attr.name && attr.text){
            arr.push(attr);
        }else{
            emptyFlag = 1;
        }
    })

    if(emptyFlag){
        MyFun.to.i("请输入完整数据");
        return false;
    }

    cattr.list = arr;

    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxlhyydc/save.do", "treeid="+curTreeId+"&content="+JSON.stringify(cattr)+"&id="+cwordid, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "提交文章成功");
                setTimeout(function(){
                    getDetail();
                }, 200)
            }else{
                MyFun.to.e(json.message || "新增文章失败");
            }
        }
        unBlock(".wrapper");
    })
}
</script>
</body>
</html>