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
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>

<style>
.J_gobackwrap a {
    margin: 10px;
    border: 1px solid #ddd;
    width: 100px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    display: block;
}
.J_numspinner input {
    width: 60px!important;
}
.J_numspinner .input-group-addon {
    position: relative;
    width: 40px;
    right: 1px;
    height: 30px;
    border: 1px solid #cfdadd;
}
.J_numspinner .input-group-addon a {
    position: absolute;
    width: 100%;
    margin: 0;
    left: 0;
}
.J_numspinner .input-group-addon .spin-up {
    top: 0;
}
.J_numspinner .input-group-addon .spin-down {
    bottom: 0;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="J_gobackwrap">
                    <a href="${request.contextPath}/supplierpurchase/list.do">
                    	<i class="fa fa-chevron-left" aria-hidden="true"></i>
                    	<span>返回上一级</span>
                    </a>
                </div>
                <div class="ibox-content">
                <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                	 <input type="hidden"   name="purchaseid"  value="${purchaseid}" >
                	    <div class="form-group col-md-3">
                        <label for="J_name">编码：</label>
                        <input type="text" class="form-control" id="J_name" placeholder="输入编码" name="code">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="J_name">品名：</label>
                        <input type="text" class="form-control" id="J_name" placeholder="输入品名" name="name">
                    </div>
                    <div class="form-group col-md-2">
                        <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','PurchaseDetailList');"><i class="fa fa-search"></i>查 询</button>
                    </div>
                </form>
                <div id="PurchaseDetailList" data-url="${request.contextPath}/supplierpurchase/detailtable.do">
                </div>    
               </div>
          </div>
            </div>
        </div>
    </div>
</div>

<script>

 

function refreshPurchaseDetailList(){
   	MyFun.ajaxRefreshTable("PurchaseDetailList");
}

$(function(){
    MyFun.search('J_formSearch','PurchaseDetailList');

})


 
 

</script>
</body>
</html>