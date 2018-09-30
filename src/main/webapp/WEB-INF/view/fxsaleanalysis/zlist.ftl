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
<link href="${request.contextPath}/css/plugins/iCheck/flat/red.css" rel="stylesheet"> 
<script src="${request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>

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
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>综合分析</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                        <input type="hidden" value="1" name="type" id="type" />
                        <div class="form-group col-md-3">
                            <label for="zuiStoreId">门店：</label>
                            <select id="zuiStoreId" class="form-control" style="width: 172px;"></select>
                            <input value="" type="hidden" name="storeid" id="storeid" />
                        </div>
                        <div class="form-group col-md-3">
                            <label for="spaceid">时间类型：</label>
                            <select id="J_type" name="status" style="width: 172px">
                                <option value="">请选择</option>
                                <option value="1" selected="selected">月份</option>
                                <option value="2">季度</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <div class="J_handlerWrap">
                                <div class="checkbox i-checks">
                                    <label><input type="radio" name="zuiFilter" value="1" checked="checked" />销售额分析<i></i></label>
                                </div>
                                <div class="checkbox i-checks">
                                    <label><input type="radio" name="zuiFilter" value="2" />毛利分析<i></i></label>
                                </div>
                                <div class="checkbox i-checks">
                                    <label><input type="radio" name="zuiFilter" value="3" />交易频次分析<i></i></label>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div id="DrugList" data-url="${request.contextPath}/fxsaleanalysis/ztable.do"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<textarea class="fn-hide" id="T_zuiStoreId">
<option value=""></option>
{{#each this}}
<option value="{{id}}">{{name}}</option>
{{/each}}
</textarea>

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

function refreshDrugList(){
    MyFun.ajaxRefreshTable("DrugList");
}

var indexsFlag = getQueryString("indexs") ? getQueryString("indexs") : "";

$(function(){

    if(indexsFlag && indexsFlag == '1'){
        getZuiStore();
    }
})

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
                    MyFun.search('J_formSearch','DrugList');
                })
                $("#zuiStoreId").on("select2:select", function(e){
                    p_getStoreId = $("#zuiStoreId").val();
                    $("#storeid").val(p_getStoreId);
                    MyFun.search('J_formSearch','DrugList');
                })
            }else{
                MyFun.to.e(json.message || "查询药店列表失败");
            }
        }
        unBlock(".wrapper");

        $("#J_type").select2({
            placeholder: "请选择分类",
            allowClear: false,
            language: "zh-CN",
            dropdownAutoWidth: false
        });

        $("#J_type").on("select2:select", function(e){
            MyFun.search('J_formSearch','DrugList');
        })
        
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_flat-red',
            radioClass: 'iradio_flat-red'
        });

        $('.i-checks').on('ifChecked', function(event){  
            showFilter();
        });
        $('.i-checks').on('ifUnchecked', function(event){  
            // showFilter();
        });

        MyFun.search('J_formSearch','DrugList');
    })
}

function showFilter(){
    var _val = $('input[name="zuiFilter"]:checked').val();
    $("#type").val(_val);
    MyFun.search('J_formSearch','DrugList');
}
</script>
</body>
</html>