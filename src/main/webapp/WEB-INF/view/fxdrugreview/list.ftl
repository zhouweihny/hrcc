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

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>供应商药品审核</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="J_name">供应商：</label>
                            <input type="text" class="form-control" placeholder="输入供应商" name="companyid">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">品种名：</label>
                            <input type="text" class="form-control" placeholder="输入品种名" name="drugname">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">状态：</label>
                            <select name="status" class="form-control">
                                <option value="">全部</option>
                                <option value="0">不通过</option>
                                <option value="1">通过</option>
                                <option value="3">待审核</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','FxDrugReviewList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div id="FxDrugReviewList" data-url="${request.contextPath}/fxdrugreview/gytable.do"></div>
               </div>
            </div>
        </div>
    </div>
</div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>

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
            unBlock(".wrapper");
        }
    });
};

function refreshFxDrugReviewList(){
   	MyFun.ajaxRefreshTable("FxDrugReviewList");
}

$(function(){
    MyFun.search('J_formSearch','FxDrugReviewList');
})

function auditDrug(obj, type){
    var _this = $(obj),
        _id = _this.data("id"),
        res = '不通过',
        _pTd = _this.parent(),
        _prevTd = _pTd.prev("td");
    if(type == '1')
        res = '通过';

    layer.confirm('确定'+res+'吗？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        layer.closeAll('dialog');
        
        createBlock(".wrapper", '正在努力加载数据...');
        $.PostJson("${request.contextPath}/fxdrugreview/audit.do", "drugid="+_id+"&status="+type, function(state, json){
            if(state == 'success'){
                if(json && json.code == '0000'){
                    MyFun.to.s(json.message);
                    _prevTd.text(res);
                    _pTd.html('');
                }else{
                    MyFun.to.e(json.message);
                }
            }
            unBlock(".wrapper");
        })
    });
}

</script>
</body>
</html>