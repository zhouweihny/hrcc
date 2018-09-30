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
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<link href="${request.contextPath}/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
<link href="${request.contextPath}/js/plugins/bootstrap-multiselect/bootstrap-multiselect.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>

<style>
.J_handlerWrap {

}
.J_handlerWrap .i-checks {
    display: inline-block;
    margin-bottom: 0;
}
.J_handlerWrap .i-checks label {
    padding-left: 0;
    padding-right: 20px;
}
.J_handlerWrap .i-checks label .iradio_flat-red {
    margin: 0 5px 4px 0;
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
.multiselect-native-select .dropdown-toggle {
    text-align: left;
}
.multiselect-native-select .btn-group,
.multiselect-native-select .dropdown-toggle,
.multiselect-native-select .multiselect-container {
    width: 100%;
}
.multiselect-native-select .multiselect-container label.checkbox {
    display: inline-block;
    width: 100%;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>培训管理</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="zuiStoreId">店员：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入店员" name="test">
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-w-m btn-info" onclick="getDetail();"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                        <a data-toggle="modal" class="btn btn-primary" onclick="addForm();">新增</a>
                    </div>
                    <div id="FxTreeTaskList" data-url="${request.contextPath}/fxtreetask/table.do"></div>
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
                    <h5>培训管理<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container form-horizontal" id="J_formwrap">
                        
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveForm();">保存</button>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_formwrap">
<div class="form-group">
    <label class="col-sm-3 control-label">门店选择：</label>
    <div class="col-sm-8">
        <select id="zuiStoreId" class="form-control"></select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">节点选择：</label>
    <div class="col-sm-8">
        <input value="" type="text" class="form-control" id="spaceidS" readonly/>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">任务名：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" placeholder="输入任务名" name="taskname">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">培训师：</label>
    <div class="col-sm-8">
        <select id="J_pxname" class="form-control"></select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">店员：</label>
    <div class="col-sm-8">
        <select id="J_dyname" class="form-control"></select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">开始时间：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" name="startdate" id="startdate" readonly placeholder="请输入开始时间">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">结束时间：</label>
    <div class="col-sm-8">
        <input type="text" class="form-control" name="enddate" id="enddate" readonly placeholder="请输入结束时间">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">备注：</label>
    <div class="col-sm-8">
        <input type="text" name="remark" placeholder="请输入备注" class="form-control"  value=""> 
    </div>
</div>
</textarea>

<textarea class="fn-hide" id="T_zuiStoreId">
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>

<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="zuiTree" class="ztree"></ul>
</div>

<script>

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

    $('#'+divid).empty();
    createBlock(".wrapper");
    $.ajax({
        type: "post",
        url: url.split("?")[0],
        dataType: "json",
        data:data,
        success: function(data){

        },
        complete:function(XMLHttpRequest,status){ 
            $('#'+divid).append(XMLHttpRequest.responseText);    
            $('#'+divid).data("url",url);
            if(typeof callback === 'function')
                callback();
            unBlock(".wrapper");
        }
    });
};

var zuiTree = null,
    curTreeId = '',
    curStoreId = '',
    clickFlag = 0, 
    tempJson = {};

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

// 获取当日与当月1号
var curDate = new Date(),
    cdate = getCurDate(curDate),
    _m = ((curDate.getMonth()+1)<10) ? ("0"+(curDate.getMonth()+1)) : (curDate.getMonth()+1),
    firstdate = curDate.getFullYear() + "-"+ _m + "-01",
    dayx = new Date( curDate.getFullYear() , _m , 0 );
    lastdate =  curDate.getFullYear() + "-"+ _m + "-"+ dayx.getDate();

function refreshDrugList(){
    MyFun.ajaxRefreshTable("FxTreeTaskList");
}

$(function(){
    getDetail();
})

function getDetail(){
    MyFun.search('J_formSearch','FxTreeTaskList');
}

function addForm(){
    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $("#J_formwrap").html("");
        tempJson = {};
    })
    $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        $("#J_formwrap").html($("#T_formwrap").val());

        initBtns();
    })
}

function initBtns(){
    getZuiStore();
}

function getZuiStore(){
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/store/stores.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.totalRows !== 0){
                $("#zuiStoreId").temp($("#T_zuiStoreId").val(), json.data);

                curStoreId = json.data[0].id;

                $("#zuiStoreId").select2({
                    placeholder: "请选择门店",
                    allowClear: false,
                    language: "zh-CN",
                    dropdownAutoWidth: false
                });

                $("#zuiStoreId").on("select2:select", function(e){
                    curStoreId = $(this).val();
                    getZuiDy(true);
                })

                getZtree();
            }else{
                MyFun.to.e(json.message || "查询药店列表失败");
            }
        }
    })
}

function getZtree(){
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

                curTreeId = '';
                $.fn.zTree.init($("#zuiTree"), setting, arr);
                zuiTree = $.fn.zTree.getZTreeObj("zuiTree");

                if(tempJson && tempJson.id){
                    var node = zuiTree.getNodeByParam('id', tempJson.treeid, null);
                    zuiTree.selectNode(node);
                    curTreeId = tempJson.treeid;
                    $("#spaceidS").val(node.name);
                }

                $("#spaceidS").off("click").on("click", function(){
                    var _this = $(this),
                        _offset = _this.offset();
                    $("#menuContent").css({left:_offset.left + "px", top:_offset.top + _this.outerHeight() + "px"}).slideDown("fast");

                    $("body").on("mousedown", onBodyDown);
                })

            }else{
                MyFun.to.e(json.message || "查询树失败");
            }
        }

        getZuiPx();
    })
}

function zTreeClick(event, treeId, treeNode) {
    curTreeId = treeNode.id;
    $("#spaceidS").val(treeNode.name);
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

function getZuiPx(){
    $.PostJson("${request.contextPath}/pxs/pxses.do", "", function(state, json){
        if(state == 'success'){
            if(json){
                $("#J_pxname").temp($("#T_zuiStoreId").val(), json);
                $("#J_pxname").select2({
                    placeholder: "请选择培训师",
                    allowClear: false,
                    language: "zh-CN",
                    dropdownAutoWidth: false
                });

                getZuiDy();
            }else{
                MyFun.to.e(json.message || "查询培训师列表失败");
            }
        }
    })
}

function getZuiDy(flag){
    if(flag)
        createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/storedy/storeydys.do", "storeid="+curStoreId, function(state, json){
        if(state == 'success'){
            if(json){
                $("#J_dyname").temp($("#T_zuiStoreId").val(), json);
                $("#J_dyname").select2({
                    placeholder: "请选择店员",
                    allowClear: false,
                    language: "zh-CN",
                    dropdownAutoWidth: false
                });
                /*$("#J_dyname").multiselect("destroy").multiselect({
                    nonSelectedText: '请选择',
                    nSelectedText: '个已选择',
                    allSelectedText: '全选',
                    includeSelectAllOption: true,
                    selectAllText: '全选',
                    selectAllValue: '',
                    onChange: function(){
                        
                    },
                    onSelectAll: function(){
                        
                    },
                    onDeselectAll: function(){
                        
                    }
                });*/

            }else{
                MyFun.to.e(json.message || "查询店员列表失败");
            }
        }
        if(!flag)
            initDates();
        unBlock(".modal-dialog");
    })
}

function initDates(){
    var date1 = cdate,
        date2 = lastdate;

    if(tempJson && tempJson.id){
        var _J_formwrapObj = $("#J_formwrap");
        _J_formwrapObj.find("input[name='taskname']").val(tempJson.jobid);
        date1 = tempJson.startdate;
        date2 = tempJson.enddate;
        _J_formwrapObj.find("input[name='remark']").val(tempJson.remark);

        $("#zuiStoreId").val(tempJson.storeid).trigger('change');
        $("#J_pxname").val(tempJson.trainerid).trigger('change');
        $("#J_dyname").val(tempJson.clerkid).trigger('change');
    }

    //日期范围限制
    $("#startdate").val(date1);
    $("#enddate").val(date2);
    var start = {
        elem: '#startdate',
        format: 'YYYY-MM-DD',
        min: '2017-01-01',
        max: date2,
        start: date1,  //开始日期
        istime: false,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#enddate',
        format: 'YYYY-MM-DD',
        min: date1,
        start: date2,
        istime: false,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
}

function saveForm(){
    var storeid = $("#zuiStoreId").val(),
        treeid = curTreeId,
        _J_formwrapObj = $("#J_formwrap"),
        taskname = _J_formwrapObj.find("input[name='taskname']").val(),
        pxname = $("#J_pxname").val(),
        dyname = $("#J_dyname").val(),
        startdate = _J_formwrapObj.find("input[name='startdate']").val(),
        enddate = _J_formwrapObj.find("input[name='enddate']").val(),
        remark = _J_formwrapObj.find("input[name='remark']").val();

    if(!storeid){
        MyFun.to.i("请选择门店");
        return false;
    }
    if(!treeid){
        MyFun.to.i("请选择树节点");
        return false;
    }
    if(!taskname){
        MyFun.to.i("请输入任务名");
        return false;
    }
    if(!pxname){
        MyFun.to.i("请选择培训师");
        return false;
    }
    if(!dyname){
        MyFun.to.i("请选择店员");
        return false;
    }

    if(clickFlag)
        return false;
    clickFlag = 1;

    var cid = '';
    if(tempJson && tempJson.id){
        cid = tempJson.id;
    }
    // var str = "id="+cid+"&storeid="+storeid+"&treeid="+treeid+"&jobid="+taskname+"&pxname="+pxname+"&dyname="+dyname.join(",")+"&startdate="+startdate+"&enddate="+enddate+"&remark="+remark;
    var str = "id="+cid+"&storeid="+storeid+"&treeid="+treeid+"&jobid="+taskname+"&pxname="+pxname+"&dyname="+dyname+"&startdate="+startdate+"&enddate="+enddate+"&remark="+remark;

    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtreetask/add.do", str, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "操作成功");

                $("#info-form").modal('hide');
                getDetail();
            }else{
                MyFun.to.e(json.message || "操作失败");
            }
        }
        unBlock(".modal-dialog");
        clickFlag = 0;
    })
}

/**
 * 获取当前时间
 * type为true表示带时分秒
**/
function getCurDate(cDate, type){
    if(type && type == '2'){
        cDate = cDate.formatDD( "yyyy-MM-DD hh:mm:ss");
    }else if(type){
        cDate = cDate.formatDD( "yyyy-MM-DD hh:mm");
    }else{
        cDate = cDate.formatDD( "yyyy-MM-DD");
    }
    return cDate;
}
Date.prototype.formatDD = function( formatStr){ 
    var date = this;
    var str = formatStr; 
    str=str.replace(/yyyy|YYYY/,date.getFullYear()); 
    str=str.replace(/yy|YY/,(date.getYear() % 100)>9?(date.getYear() % 100).toString():"0" + (date.getYear() % 100)); 
    str=str.replace(/MM/,date.getMonth()>8?(date.getMonth()+1).toString():"0" + (date.getMonth()+1)); 
    str=str.replace(/M/g,date.getMonth()+1); 
    str=str.replace(/dd|DD/,date.getDate()>9?date.getDate().toString():"0" + date.getDate()); 
    str=str.replace(/d|D/g,date.getDate()); 
    str=str.replace(/hh|HH/,date.getHours()>9?date.getHours().toString():"0" + date.getHours()); 
    str=str.replace(/h|H/g,date.getHours()); 
    str=str.replace(/mm/,date.getMinutes()>9?date.getMinutes().toString():"0" + date.getMinutes()); 
    str=str.replace(/m/g,date.getMinutes()); 
    str=str.replace(/ss|SS/,date.getSeconds()>9?date.getSeconds().toString():"0" + date.getSeconds()); 
    str=str.replace(/s|S/g,date.getSeconds()); 
    return str; 
}

function modFormdata(obj, id){
    var _this = $(obj);
    tempJson = {
        id: id,
        storeid: _this.data("storeid"),
        treeid: _this.data("treeid"),
        startdate: _this.data("startdate"),
        enddate: _this.data("enddate"),
        trainerid: _this.data("trainerid"),
        clerkid: _this.data("clerkid"),
        jobid: _this.data("jobid"),
        remark: _this.data("remark")
    }

    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        $("#J_formwrap").html("");
        tempJson = {};
    })
    $('#info-form').off('shown.bs.modal').on('shown.bs.modal', function (e) {
        $("#J_formwrap").html($("#T_formwrap").val());

        initBtns();
    })

}

function refreshFxTreeTaskList(){
    MyFun.ajaxRefreshTable("FxTreeTaskList");
}
</script>
</body>
</html>
