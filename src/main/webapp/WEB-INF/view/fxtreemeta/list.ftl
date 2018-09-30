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
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>报表参数配置</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <#if Session.COMPANY.username =='admin' >
                        <div class="form-group col-md-4">
                            <label for="storetype">门店类型：</label>
                            <select id="storetype" name="storetype" class="form-control m-r" style="width: 172px;" ></select>
                        </div>
                        </#if>
                        <#if Session.COMPANY.username!='admin' >       
                        <input type="hidden" class="form-control" id="J_treeNodeId" placeholder="输入名称" name="treeNodeId">
                        <div class="form-group col-md-4">
                            <label for="J_name">药店：</label>
                            <select id="supplierid" name="supplierid" class="form-control input-sm" style="width: 172px;" ></select>
                        </div>
                        </#if>
                    </form>
                    <div class="m-t">
                        
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <ul id="zuiTree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                                <thead>
                                    <tr role="row">
                                        <th style="width:20px"></th>
                                        <th>名称</th>
                                        <th>备注</th>
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

<div id="info-form" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>规则配置</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container" id="J_infoTpl">
                        
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" style="margin-left: 20px;width: 100px;" id="J_saveBtn" onclick="zsaveAll();">提交</button>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_orderlist">
{{#each this}}
<tr class="gradeX open" data-json="{{retJson2Str this}}">
    {{#if treeMetaDatas}}
    <td class="J_showdetail"><i class="fa fa-minus-circle"></i> </td>
    {{else}}
    <td>&nbsp;</td>
    {{/if}}
    <td>{{code}} - {{metaname}}</td>
    <td>{{remark}}</td>
    <td>
        <a href="javascript:;" onclick="modiFyMeta(this);">修改</a>
    </td>
</tr>
{{#if treeMetaDatas}}
<tr class="zChildTr">
    <td>&nbsp;</td>
    <td colspan="4">
        <table class="table table-striped table-bordered table-hover dataTables-example dataTable"aria-describedby="DataTables_Table_0_info">
            <thead>
                <tr role="row">
                    <th width="100">参数名称</th>
                    <th width="131">参数值</th>
                    <th width="225">备注</th>
                </tr>
            </thead>
            <tbody>
                {{#each treeMetaDatas}}
                <tr class="gradeX">
                    <td>{{code}} - {{metadataname}}</td>
                    <td>{{val}}</td>
                    <td>{{remark}}</td>
                </tr>
                {{/each}}
            </tbody>
        </table>
    </td>
</tr>
{{/if}}
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_infoTpl">
<form class="form-inline row J_formSearch_D" id="J_formSearch_D">
    <div class="row">
        <div class="form-group col-md-4">
            <label for="spaceid">规则名称：</label>
            <input type="text" class="form-control" placeholder="输入规则名称" name="metaname" id="metaname" value="{{metaname}}" readonly="readonly">
        </div>
        <div class="form-group col-md-4">
            <label for="spaceid">作用域：</label>
            <select name="scope" id="scope" class="form-control" style="width: 170px;">
                <option value="0" {{retSelect scope '0'}}>当前节点</option>
                <option value="1" {{retSelect scope '1'}}>下级所有节点</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label for="spaceid">备注：</label>
            <input type="text" class="form-control" placeholder="输入备注" id="remark" name="remark" value="{{remark}}" >
        </div>
    </div>
</form>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tbl" aria-describedby="DataTables_Table_0_info">
    <thead>
        <tr role="row">
            <th width="200">参数名称</th>
            <th width="231">参数值</th>
            <th width="">备注</th>
        </tr>
    </thead>
    <tbody>
        {{#each treeMetaDatas}}
        <tr class="gradeX J_ptr" data-json="{{retJson2Str this}}">
            <td width="200">{{code}} - {{metadataname}}</td>
            <td width="231">
                <input type="text" class="form-control input-sm" value="{{val}}" name="val" />
            </td>
            <td width="">
                <input type="text" class="form-control input-sm" value="{{remark}}" name="remark" />
            </td>
        </tr>
        {{/each}}
    </tbody>
</table>
</textarea>

<textarea class="fn-hide" id="T_methodid">
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_methodidS">
<option value="">请选择</option>
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>

<script>

var zuiTree = null,
    clickFlag = 0,
    G_json = null,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    curTreeId = curStoreId = '',
    curStoreType = '',
    adminFlag = 0;

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
    <#if Session.COMPANY.username =='admin' >
    adminFlag = 1;
    </#if>

    if(indexsFlag && indexsFlag == '1'){
        $("#J_formSearch").addClass("fn-hide");
        $("#J_formSearch").next(".m-t").addClass("fn-hide");
        var p_getStoreId = window.parent.W_p_getStoreId();
        var _option = '<option value="'+p_getStoreId+'">'+p_getStoreId+'</option>';
        $("#supplierid").append(_option);

        var _h = parseFloat(window.parent.W_p_getWindowWh().h * 0.8, 10).toFixed(2);
        $("#zuiTree").css({"height": _h});
    }else{
        if(!adminFlag){
            $('#supplierid').select2({
                ajax: {
                    url: "${request.contextPath}/store/stores.do",
                    type : "POST",
                    dataType: 'json',
                    delay: 250,
                    data: function(params) {
                        return {
                            keywords: params.term, // search term
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
                                attr.id = _cdata[i].id;
                                attr.text = _cdata[i].storecode + ' - ' + _cdata[i].name;
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
                placeholder: "请输入门店名称",
                allowClear: true,
                language: "zh-CN",
                dropdownAutoWidth: true
            })
        }

        var _h = parseFloat($(window).height() * 0.8, 10).toFixed(2);
        $("#zuiTree").css({"height": _h});
    }

    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $(".select2-container").css({"zIndex": "9999"});
        $("#J_infoTpl").html('');
    })
    $('#info-form').off('show.bs.modal').on('show.bs.modal', function (e) {
        $(".select2-container").css({"zIndex": "1"});
    })

    getStoreType();

})

function getStoreType(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/storetype/types.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#storetype").temp($("#T_methodid").val(), json.data);
                    $("#storetype").select2({
                        placeholder: "请选择门店类型",
                        allowClear: false,
                        language: "zh-CN",
                        dropdownAutoWidth: false
                    });

                    curStoreType = json.data[0].id;

                    $("#storetype").on("select2:unselect", function(e){
                        curStoreType = '';
                    })
                    $("#storetype").on("select2:select", function(e){
                        curStoreType = $("#storetype").val();
                    })
                }else{
                    MyFun.to.i(json.message || "查询门店类型失败");
                }
            }else{
                MyFun.to.e(json.message || "查询门店类型失败");
            }
        }

        getzTree();
    })
}

function getzTree(){
    $.PostJson("${request.contextPath}/fxtree/analysistree.do", "storetypeid="+curStoreType, function(state, json){
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
    $("#J_treeNodeId").val(treeNode.id);

    if(adminFlag){
        if(!curStoreType){
            MyFun.to.i("请选择门店类型");
            return false;
        }
    }else{
        curStoreId = $("#supplierid").val() || '';

        if(!curStoreId){
            MyFun.to.i("请选择门店");
            return false;
        }
    }

    if(clickFlag)
        return false;
    clickFlag = 1;
    curTreeId = treeNode.id;
    getDetail();
}

function beforeDrag(treeId, treeNodes) {
    return false;
}

function addHoverDom(treeId, treeNode) {
    //右侧table新增
    return false;

    var sObj = $("#" + treeNode.tId + "_span");

    if(treeNode.level == 2){
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='新增通用名' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn){
            btn.bind("click", function(){
                var newTreeNode = zuiTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                zuiTree.editName(newTreeNode[0]);
                return false;
            });
        }
    }
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
    $.PostJson("${request.contextPath}/fxtreemeta/metabytree.do", "treeid="+curTreeId+"&storeid="+curStoreId+"&storetypeid="+curStoreType, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#J_tbl_body").temp($("#T_orderlist").val(), json.data);

                    $("#J_tbl_body").off("click").on('click', 'td.J_showdetail', function(){
                        var _this = $(this),
                            _id = _this.data("id"),
                            _tr = _this.parent();
                        if(_tr.hasClass("open")){
                            //关闭
                            _tr.removeClass("open").next(".zChildTr").hide();
                            _this.find("i.fa").addClass("fa-plus-circle").removeClass("fa-minus-circle");
                        }else{
                            _tr.addClass("open").next('.zChildTr').show();
                            _this.find("i.fa").removeClass("fa-plus-circle").addClass("fa-minus-circle");
                        }

                    })

                }else{
                    $("#J_tbl_body").html('');
                    MyFun.to.i(json.message || "查询配置信息失败");
                }
            }else{
                MyFun.to.e(json.message || "查询配置信息失败");
            }
        }
        clickFlag = 0;
        unBlock(".wrapper");
    })
}

Handlebars.registerHelper('retSelect', function(val, oldval) {
    if(val == oldval)
        return 'selected';
    return '';
});

function modiFyMeta(obj){
    var _this = $(obj),
        _tr = _this.parent().parent();
    G_json = _tr.data("json");

    $('#info-form').modal('show');
    $("#J_infoTpl").temp($("#T_infoTpl").val(), G_json);

    // getMethodid();
}

function getMethodid(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxmethod/methods.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                if(json.data.length){
                    $("#methodid").temp($("#T_methodid").val(), json.data);
                }else{
                    $("#methodid").html('');
                    MyFun.to.i(json.message || "暂无方法名");
                }
            }else{
                MyFun.to.e(json.message || "查询方法名失败");
            }
        }
        unBlock(".modal-dialog");
    })
}

function zsaveAll(){
    var newJson = {};
    newJson.id = G_json.id;
    newJson.treeid = curTreeId;
    newJson.metaid = G_json.metaid;
    newJson.metaname = G_json.metaname;
    newJson.methodid = $("#methodid").val() || '';
    newJson.methodname = $("#methodid").find('option:selected').text() || '';
    newJson.scope = $("#scope").val();
    newJson.remark = $("#remark").val();
    newJson.storetypeid = curStoreType;

    /*if(!newJson.methodid){
        MyFun.to.i("方法名不能为空");
        return false;
    }*/

    var treeMetaDatas = [],
        emptyFlag = 0,
        _tr = $("#J_bottom_tbl tbody").find("tr.J_ptr");
    if(_tr.length){
        _tr.each(function(){
            var attr = {},
                _this = $(this),
                _json = _this.data("json");

            attr.metadataid = _json.metadataid;
            attr.metadataname = _json.metadataname;
            attr.val = _this.find('input[name="val"]').val();
            attr.remark = _this.find('input[name="remark"]').val();
            treeMetaDatas.push(attr);
        })
    }else{
        emptyFlag = 1;
    }

    newJson.treeMetaDatas = treeMetaDatas;

    if(emptyFlag){
        MyFun.to.i("条件不能有空");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;

    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtreemeta/modifytreemeta.do", "jsonStr="+JSON.stringify(newJson)+"&storeid="+curStoreId, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "修改规则配置成功");

                setTimeout(function(){
                    getDetail();
                    $('#info-form').modal('hide');
                }, 300)
            }else{
                MyFun.to.e(json.message || "修改规则配置失败");
            }
        }
        clickFlag = 0;
        unBlock(".modal-dialog");
    })
}

Handlebars.registerHelper('retSelected', function(rulesCompare, cur) {
    if(rulesCompare == cur)
        return "selected";
    return "";
});

Handlebars.registerHelper('retSelecteddes', function(rulesCompare) {
    var buffer = '大于';
    if(rulesCompare == '2'){
        buffer = '小于';
    }else if(rulesCompare == '3'){
        buffer = '等于';
    }else if(rulesCompare == '4'){
        buffer = '大于等于';
    }else if(rulesCompare == '5'){
        buffer = '小于等于';
    }else if(rulesCompare == '6'){
        buffer = '不等于';
    }
    return buffer;
});

function addrow(obj){
    var _this = $(obj),
        _tbody = _this.parent().parent().parent();

    var str = '<tr> <td width="125"> <select class="form-control rulesCompare"> <option value="1">大于</option> <option value="2">小于</option> <option value="3">等于</option> <option value="4">大于等于</option> <option value="5">小于等于</option> <option value="6">不等于</option> </select> </td> <td width="235"> <input type="text" class="form-control input-sm" value="" name="msg" /> </td> <td width="230"> <input type="text" class="form-control input-sm" value="" name="remark" /> </td> <td width="90"> <a href="javascript:;" class="J_del" onclick="delrow(this);">删除</a> </td> </tr>';
    _tbody.append(str);
}

function delrow(obj){
    var _this = $(obj),
        _tr = _this.parent().parent();
    _tr.remove();
}
</script>
</body>
</html>