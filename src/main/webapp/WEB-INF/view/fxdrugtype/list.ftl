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
<link href="${request.contextPath}/js/plugins/bootstrap-multiselect/bootstrap-multiselect.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>
<link href="${request.contextPath}/js/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/js/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<link href="${request.contextPath}/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>

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
.fn-hide {
    /*display: none!important;*/
}
.multiselect-native-select {
    
}
.multiselect-native-select .multiselect, .multiselect-native-select .multiselect-container {
    width: 172px;
}
.multiselect-native-select .multiselect-container label.checkbox {
    display: inline-block;
    width: 100%;
}
.saledate_wrap {
    position: relative;
}
.saledate_wrap input {
    cursor: pointer;
}
.saledate_wrap i {
    position: absolute; bottom: 9px; left: 236px; top: auto; cursor: pointer;
}
.daterangepicker select.monthselect {
    margin-right: 8%;
    width: 34%;
    vertical-align: middle;
}
.daterangepicker select.yearselect {
    width: 40%;
    vertical-align: middle;
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
.modal-dialog {
    width: 1100px!important;
}
.modal-open .modal .ibox-content {
    max-height: 550px;
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
#zuiTree {
    border: 1px solid #23b7e5;
    width: 272px;
    height: 260px;
    overflow: auto;
    background: #fff;
    z-index: 999;
    margin-top: -1px;
}
#spaceidS {
    cursor: pointer;
}
#menuContent {
    z-index: 99999;
}
#J_bottom_tblS_filter label {
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
                    <h5>药品分析</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline" id="J_formSearch" action="javascript:;">
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label for="spaceid">药品分类：</label>
                                <!-- <select id="spaceidS" class="form-control" style="width: 172px;"></select> -->
                                <input value="" type="text" class="form-control" id="spaceidS" style="width: 172px;" readonly/>
                                <input value="" type="hidden" name="spaceid" id="spaceid" />
                            </div>
                            <div class="form-group col-md-4">
                                <label for="spaceid">门店选择：</label>
                                <select id="storeidS" class="form-control" style="width: 172px;"></select>
                                <input value="" type="hidden" name="storeid" id="storeid" />
                            </div>
                            <div class="form-group col-md-4" id="J_anaType">
                                <label for="spaceid">分析类型：</label>
                                <select id="compared" class="form-control" multiple="multiple">
                                    <option value="1">淘汰</option>
                                    <option value="2">价格</option>
                                    <option value="3">库存</option>
                                    <option value="4">毛利</option>
                                </select>
                                <input value="" type="hidden" name="type" />
                            </div>
                        </div>
                        <div class="row m-t">
                            <div class="form-group col-md-4 saledate_wrap">
                                <label for="spaceid">文件名称：</label>
                                <!-- <input type="text" class="form-control" placeholder="销售时间" id="saledate" readonly="readonly">
                                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                <input value="" type="hidden" name="startdate" />
                                <input value="" type="hidden" name="enddate" /> -->
                                <select id="saleFileS" class="form-control" style="width: 172px;"></select>
                                <input value="" type="hidden" name="salefile" id="saleFile" />
                            </div>
                            <div class="form-group col-md-4">
                                <button type="button" class="btn btn-w-m btn-info" id="J_searchBtn" onclick="zuiSearch();"><i class="fa fa-search"></i>查 询</button>
                            </div>
                        </div>
                    </form>
                    <div class="m-t">
                        <!-- <button type="button" class="btn btn-primary">导入</button> -->
                    </div>

                    <div id="FxDrugdataList" data-url="${request.contextPath}/fxdrugtype/table.do"></div>
               </div>
            </div>
        </div>
    </div>
</div>

<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="zuiTree" class="ztree"></ul>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>明细<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <table class="table table-striped table-bordered table-hover dataTable" id="J_bottom_tblS" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>编码</th>
                                    <th>名称</th>
                                    <th>规格</th>
                                    <th>单位</th>
                                    <th>厂商</th>
                                    <th>数量</th>
                                    <th>成本单价</th>
                                    <th>销售单价</th>
                                </tr>
                            </thead>
                            <tbody id="J_detailBody">
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-formS" class="modal fade" aria-hidden="true" >
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

<textarea class="fn-hide" id="T_bodyTpl">
{{#each this}}
<tr>
    <td>{{code}}</td>
    <td>{{name}}</td>
    <td>{{specifications}}</td>
    <td>{{unit}}</td>
    <td>{{factory}}</td>
    <td>{{qty}}</td>
    <td>{{costprice}}</td>
    <td>{{price}}</td>
</tr>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_options">
{{#each this}}
<option value="{{id}}">{{pathname}}</option>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_infoTpl">
<form class="form-inline row J_formSearch_D" id="J_formSearch_D" action="javascript:;">
    <div class="row">
        <div class="form-group col-md-4">
            <label for="spaceid">规则名称：</label>
            <input type="text" class="form-control" placeholder="输入规则名称" name="metaname" id="metaname" value="{{metaname}}" readonly="readonly">
        </div>
        <div class="form-group col-md-4">
            <label for="spaceid">方法名：</label>
            <select name="methodid" id="methodid" class="form-control" style="width: 170px;"></select>
        </div>
        <div class="form-group col-md-4">
            <label for="spaceid">作用域：</label>
            <select name="scope" id="scope" class="form-control" style="width: 170px;">
                <option value="0" {{retSelect scope '0'}}>当前节点</option>
                <option value="1" {{retSelect scope '1'}}>下级所有节点</option>
            </select>
        </div>
    </div>
    <div class="row m-t">
        <div class="form-group col-md-12">
            <label for="spaceid">备注：</label>
            <input type="text" class="form-control" placeholder="输入备注" id="remark" name="remark" value="{{remark}}" >
        </div>
    </div>
</form>
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_bottom_tbl" aria-describedby="DataTables_Table_0_info">
    <thead>
        <tr role="row">
            <th width="100">参数名称</th>
            <th width="131">参数值</th>
            <th width="128">条件</th>
            <th width="228">提示语</th>
            <th width="225">备注</th>
            <th width="95">操作</th>
        </tr>
    </thead>
    <tbody>
        {{#each treeMetaDatas}}
        <tr class="gradeX J_ptr" data-json="{{retJson2Str this}}">
            <td width="100">{{metadataname}}</td>
            <td width="131">
                <input type="text" class="form-control input-sm" value="{{val}}" name="val" />
            </td>
            <td colspan="4">
                <table class="table table-striped table-bordered table-hover dataTables-example dataTable J_bottom_sub_tbl" aria-describedby="DataTables_Table_0_info">
                    <tbody>
                        {{#if msgs}}
                            {{#each msgs}}
                            <tr data-treemetadatasid="{{treemetadatasid}}">
                                <td width="125">
                                    <select class="form-control rulesCompare">
                                        <option value="1" {{retSelected condition 1}}>大于</option>
                                        <option value="2" {{retSelected condition 2}}>小于</option>
                                        <option value="3" {{retSelected condition 3}}>等于</option>
                                        <option value="4" {{retSelected condition 4}}>大于等于</option>
                                        <option value="5" {{retSelected condition 5}}>小于等于</option>
                                        <option value="6" {{retSelected condition 6}}>不等于</option>
                                    </select>
                                </td>
                                <td width="235">
                                    <input type="text" class="form-control input-sm" value="{{msg}}" name="msg" />
                                </td>
                                <td width="230">
                                    <input type="text" class="form-control input-sm" value="{{remark}}" name="remark" />
                                </td>
                                <td width="90">
                                    {{#if_eq @index compare = '0'}}
                                    <a href="javascript:;" class="J_addrow" onclick="addrow(this);">新增一行</a>
                                    {{else}}
                                    <a href="javascript:;" class="J_del" onclick="delrow(this);">删除</a>
                                    {{/if_eq}}
                                </td>
                            </tr>
                            {{/each}}
                        {{else}}
                            <tr>
                                <td width="125">
                                    <select class="form-control rulesCompare">
                                        <option value="1">大于</option>
                                        <option value="2">小于</option>
                                        <option value="3">等于</option>
                                        <option value="4">大于等于</option>
                                        <option value="5">小于等于</option>
                                        <option value="6">不等于</option>
                                    </select>
                                </td>
                                <td width="235">
                                    <input type="text" class="form-control input-sm" value="" name="msg" />
                                </td>
                                <td width="230">
                                    <input type="text" class="form-control input-sm" value="" name="remark" />
                                </td>
                                <td width="90">
                                    <a href="javascript:;" class="J_addrow" onclick="addrow(this);">新增一行</a>
                                </td>
                            </tr>
                        {{/if}}
                    </tbody>
                </table>
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

<script>

var zuiTbl = null,
    zuiTree = null,
    curTreeId = '',
    dataItems = null,
    indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "",
    type = getQueryString("type") ? getQueryString("type") : "",
    curStoreid = '',
    clickFlag = 0,
    G_json = null,
    zuiTblD = null,
    curSaleFile = '';

var setting = {
    view: {
        selectedMulti: false
    },
    edit: {
        enable: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: zTreeClick
    }
};

MyFun.ajaxRefreshTable=function(divid,url,callback){
    if(!url)
        url= $('#'+divid).data("url");
    url= MyFun.urlFun(url,"_",new Date().getTime());
    var data = "{";
    var str =url.split("?")[1] ;   
    var strs = str.split("&");   
    for(var i = 0; i < strs.length; i ++) {   
        data += "'"+strs[i].split("=")[0]+"'" + ":"+"'"+strs[i].split("=")[1]+"'"+",";
    }
    data += "}";
    data = eval('('+data+')');

    /*console.log(data)
    return false;*/

    createBlock(".wrapper");
    $.ajax({
        type: "post",
        url: url.split("?")[0],
        dataType: "json",
        data:data,
        success: function(data){

        },
        complete:function(XMLHttpRequest,status){ 
            $('#'+divid).empty();
            $('#'+divid).append(XMLHttpRequest.responseText);    
            $('#'+divid).data("url",url);
            if(typeof callback === 'function')
                callback();
            $(".ibox-content .dataTables-example").next(".row").hide();

            if(zuiTbl){
                zuiTbl.destroy();
                zuiTbl = null;
            }

            zuiTbl = $('.dataTables-example.dataTable').DataTable({
                language: {
                    sProcessing: "正在努力加载数据..."
                },
                dom: '<"top">rt<"bottom"flpi><"clear">',
                ordering:true,//关闭表格的排序功能
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
            unBlock(".wrapper");
        }
    });
};  

function refreshFxMetadataList(){
    MyFun.ajaxRefreshTable("FxDrugdataList");
}

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        curStoreid = window.parent.W_p_getStoreId(),
        curSaleFile = window.parent.W_p_getSaleFileId();

        var _html = '<div class="row"> <div class="form-group col-md-4"> <label for="spaceid">药品分类：</label> <input value="" type="text" class="form-control" id="spaceidS" style="width: 172px;" readonly/> <input value="" type="hidden" name="spaceid" id="spaceid" /> <input value="" type="hidden" name="storeid" id="storeid" /> <input value="" type="hidden" name="type" /> <input value="" type="hidden" name="salefile" id="saleFile" /> </div> <div class="form-group col-md-4"> <button type="button" class="btn btn-w-m btn-info" id="J_searchBtn" onclick="zuiSearch();"><i class="fa fa-search"></i>查 询</button> </div> </div>';
        $("#J_formSearch").html(_html);
        $("#J_formSearch").find("#storeid").val(curStoreid).end()
                            .find("input[name='type']").val(type).end()
                            .find("#saleFile").val(curSaleFile);

    }else{
        $('#storeidS').select2({
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

        $("#storeidS").on("select2:unselect", function(e){
            curStoreid = '';
            $("#storeid").val('');
        })
        $("#storeidS").on("select2:select", function(e){
            curStoreid = $("#storeidS").val();
            $("#storeid").val(curStoreid);
        })

        $('#saleFileS').select2({
            ajax: {
                url: "${request.contextPath}/fximpfilename/combobox.do",
                type : "POST",
                dataType: 'json',
                delay: 250,
                data: function(params) {
                    return {
                        storeid: curStoreid, // search term
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
                            attr.text = _cdata[i].name + ' - ' + _cdata[i].createtime;
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
            placeholder: "请输入销售文件名称",
            allowClear: true,
            language: "zh-CN",
            dropdownAutoWidth: true
        })

        $("#saleFileS").on("select2:unselect", function(e){
            curSaleFile = '';
            $("#saleFile").val('');
        })
        $("#saleFileS").on("select2:select", function(e){
            curSaleFile = $("#saleFileS").val();
            $("#saleFile").val(curSaleFile);
        })
        $("#saleFileS").on("select2:opening", function(e){
            if(!curStoreid){
                MyFun.to.i("请选择门店");
                return false;
            }
        })
    }

    getZtree();

})

function getDetail(obj, index){
    if(!dataItems)
        dataItems = $(".dataTables-example.dataTable tbody").data("items");
    var detail = dataItems[parseInt(index, 10)].items;

    if(detail.length){
        $(".select2-container").css({"zIndex": "1"});

        $("#info-form").modal('show');
        $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
            $("#J_detailBody").html('');
            if(zuiTblD){
                zuiTblD.destroy();
                zuiTblD = null;
            }

            $(".select2-container").css({"zIndex": "9999"});
        })
        $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
            $("#J_detailBody").temp($("#T_bodyTpl").val(), detail);

            zuiTblD = $('#J_bottom_tblS').DataTable( {
                language: {
                    sProcessing: "正在努力加载数据...",
                    sSearch: "快速查找："
                },
                dom: '<"top"f>rt<"bottom"lpi><"clear">',
                ordering: false,//关闭表格的排序功能
                serverSide: false,  //启用服务器端分页
                processing: true,  //隐藏加载提示,自行处理
                searching: true, // 禁用搜索
                drawCallback: function(settings){
                    
                }
            });
        })
    }else{
        MyFun.to.i("暂无明细");
        return false;
    }
}

function getZtree(){
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

                $("#spaceidS").off("click").on("click", function(){
                    var _this = $(this),
                        _offset = _this.offset();
                    $("#menuContent").css({left:_offset.left + "px", top:_offset.top + _this.outerHeight() + "px"}).slideDown("fast");

                    $("body").on("mousedown", onBodyDown);
                })

                initPage();
            }else{
                MyFun.to.e(json.message || "查询树失败");
            }
        }
        unBlock(".wrapper");
    })
}

function initPage(){
    if(!indexsFlag){
        $("#compared").multiselect({
            nonSelectedText: '请选择',
            nSelectedText: '个已选择',
            allSelectedText: '全选',
            includeSelectAllOption: true,
            selectAllText: '全选',
            selectAllValue: '',
            onChange: function(){
                $("#J_formSearch input[name='type']").val($("#compared").val());
            },
            onSelectAll: function(){
                $("#J_formSearch input[name='type']").val($("#compared").val());
                // $("#J_searchBtn").click();
            },
            onDeselectAll: function(){
                $("#J_formSearch input[name='type']").val('');
            }
        });
    }

    moment.locale('zh-cn', {months : '一月_二月_三月_四月_五月_六月_七月_八月_九月_十月_十一月_十二月'.split('_'), monthsShort : '1月_2月_3月_4月_5月_6月_7月_8月_9月_10月_11月_12月'.split('_'), weekdays : '星期日_星期一_星期二_星期三_星期四_星期五_星期六'.split('_'), weekdaysShort : '周日_周一_周二_周三_周四_周五_周六'.split('_'), weekdaysMin : '日_一_二_三_四_五_六'.split('_'), longDateFormat : {LT : 'Ah点mm分', LTS : 'Ah点m分s秒', L : 'YYYY-MM-DD', LL : 'YYYY年MMMD日', LLL : 'YYYY年MMMD日Ah点mm分', LLLL : 'YYYY年MMMD日ddddAh点mm分', l : 'YYYY-MM-DD', ll : 'YYYY年MMMD日', lll : 'YYYY年MMMD日Ah点mm分', llll : 'YYYY年MMMD日ddddAh点mm分'}, meridiemParse: /凌晨|早上|上午|中午|下午|晚上/, meridiemHour: function (hour, meridiem) {if (hour === 12) {hour = 0; } if (meridiem === '凌晨' || meridiem === '早上' || meridiem === '上午') {return hour; } else if (meridiem === '下午' || meridiem === '晚上') {return hour + 12; } else {return hour >= 11 ? hour : hour + 12; } }, meridiem : function (hour, minute, isLower) {var hm = hour * 100 + minute; if (hm < 600) {return '凌晨'; } else if (hm < 900) {return '早上'; } else if (hm < 1130) {return '上午'; } else if (hm < 1230) {return '中午'; } else if (hm < 1800) {return '下午'; } else {return '晚上'; } }, calendar : {sameDay : function () {return this.minutes() === 0 ? '[今天]Ah[点整]' : '[今天]LT'; }, nextDay : function () {return this.minutes() === 0 ? '[明天]Ah[点整]' : '[明天]LT'; }, lastDay : function () {return this.minutes() === 0 ? '[昨天]Ah[点整]' : '[昨天]LT'; }, nextWeek : function () {var startOfWeek, prefix; startOfWeek = _moment__default().startOf('week'); prefix = this.unix() - startOfWeek.unix() >= 7 * 24 * 3600 ? '[下]' : '[本]'; return this.minutes() === 0 ? prefix + 'dddAh点整' : prefix + 'dddAh点mm'; }, lastWeek : function () {var startOfWeek, prefix; startOfWeek = _moment__default().startOf('week'); prefix = this.unix() < startOfWeek.unix()  ? '[上]' : '[本]'; return this.minutes() === 0 ? prefix + 'dddAh点整' : prefix + 'dddAh点mm'; }, sameElse : 'LL'}, ordinalParse: /\d{1,2}(日|月|周)/, ordinal : function (number, period) {switch (period) {case 'd': case 'D': case 'DDD': return number + '日'; case 'M': return number + '月'; case 'w': case 'W': return number + '周'; default: return number; } }, relativeTime : {future : '%s内', past : '%s前', s : '几秒', m : '1 分钟', mm : '%d 分钟', h : '1 小时', hh : '%d 小时', d : '1 天', dd : '%d 天', M : '1 个月', MM : '%d 个月', y : '1 年', yy : '%d 年'}, week : {dow : 1, doy : 4 } });

    var curDay = moment().format('YYYY-MM-01'),//当月1号
        beginDay = moment().subtract(1, 'months').format('YYYY-MM-01');//上月1号

    $("#J_formSearch input[name='startdate']").val(beginDay);
    $("#J_formSearch input[name='enddate']").val(curDay);
    $("#saledate").daterangepicker({
        "showDropdowns": true,
        "autoApply": true,
        "startDate": beginDay,
        "endDate": curDay,
        "minDate": "YYYY-MM-DD",
        "maxDate": moment().format('YYYY-MM-DD')
    }, function(start, end) {
        $("#J_formSearch input[name='startdate']").val(moment(start).format("L"));
        $("#J_formSearch input[name='enddate']").val(moment(end).format("L"));
    });

    if(indexsFlag){
        setTimeout(function(){
            zuiSearch();
        }, 500)
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

function modMetal(obj, treeid, metaid){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtreemeta/modifystoretreemeta.do", "treeid="+treeid+"&storeid="+curStoreid+"&metaid="+metaid, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                
                G_json = json.data;

                $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
                    $(".select2-container").css({"zIndex": "9999"});
                    $("#J_infoTpl").html('');
                })
                $('#info-formS').off('show.bs.modal').on('show.bs.modal', function (e) {
                    $(".select2-container").css({"zIndex": "1"});
                })

                $('#info-formS').modal('show');
                $("#J_infoTpl").temp($("#T_infoTpl").val(), G_json);

                getMethodid();
            }else{
                MyFun.to.e(json.message || "查询报表参数失败");
            }
        }
        unBlock(".wrapper");
    })
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


Handlebars.registerHelper('retSelect', function(val, oldval) {
    if(val == oldval)
        return 'selected';
    return '';
});

Handlebars.registerHelper('retSelected', function(rulesCompare, cur) {
    if(rulesCompare == cur)
        return "selected";
    return "";
});

function zuiSearch(){
    if(!curStoreid){
        MyFun.to.i("请选择门店");
        return false;
    }
    MyFun.search('J_formSearch','FxDrugdataList');
}

function zsaveAll(){
    var newJson = {};
    newJson.id = G_json.id;
    newJson.treeid = G_json.treeid;
    newJson.metaid = G_json.metaid;
    newJson.metaname = G_json.metaname;
    newJson.methodid = $("#methodid").val();
    newJson.methodname = $("#methodid").find('option:selected').text();
    newJson.scope = $("#scope").val();
    newJson.remark = $("#remark").val();

    if(!newJson.methodid){
        MyFun.to.i("方法名不能为空");
        return false;
    }

    var treeMetaDatas = [],
        emptyFlag = 0,
        _tr = $("#J_bottom_tbl tbody").find("tr.J_ptr");
    if(_tr.length){
        _tr.each(function(){
            var attr = {},
                msgs = [],
                _this = $(this),
                _json = _this.data("json"),
                sub_tbl = _this.find(".J_bottom_sub_tbl tbody tr");

            attr.metadataid = _json.metadataid;
            attr.metadataname = _json.metadataname;
            attr.val = _this.find('input[name="val"]').val();
            attr.msg = '';
            attr.remark = '';
            if(attr.val){
                if(sub_tbl.length){
                    sub_tbl.each(function(){
                        var _self = $(this),
                            sattr = {};
                        sattr.treemetadatasid = _self.data('treemetadatasid') || '';
                        sattr.condition = _self.find(".rulesCompare").val();
                        sattr.msg = _self.find("input[name='msg']").val();
                        sattr.remark = _self.find("input[name='remark']").val();

                        if(sattr.msg)
                            msgs.push(sattr);
                        else
                            emptyFlag = 1;
                    })
                }
                attr.msgs = msgs;
                treeMetaDatas.push(attr);
            }else{
                emptyFlag = 1;
            }
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
    $.PostJson("${request.contextPath}/fxtreemeta/modifytreemeta.do", "jsonStr="+JSON.stringify(newJson)+"&storeid="+curStoreid, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "修改规则配置成功");
                $('#info-formS').modal('hide');
                
                setTimeout(function(){
                    refreshFxMetadataList();
                }, 300)
            }else{
                MyFun.to.e(json.message || "修改规则配置失败");
            }
        }
        clickFlag = 0;
        unBlock(".modal-dialog");
    })
}

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

function zTreeClick(event, treeId, treeNode) {
    curTreeId = treeNode.id;
    $("#spaceidS").val(treeNode.name);
    $("#spaceid").val(curTreeId);
    hideMenu();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}

function hideMenu(){
    $("#menuContent").fadeOut("fast");
    $("body").off("mousedown", onBodyDown);
}
</script>
</body>
</html>