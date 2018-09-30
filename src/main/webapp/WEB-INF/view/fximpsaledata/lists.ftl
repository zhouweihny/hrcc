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
<script src="${request.contextPath}/js/my.js?v=1.0.2"></script>
<link href="${request.contextPath}/css/animate.css" rel="stylesheet">
<link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js"></script>
<script src="${request.contextPath}/js/plugins/layer/layer.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/jquery.nicescroll/jquery.nicescroll.min.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/select2.js"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">

<style>
.J_gobackwrap {
	position: relative;
}
.J_gobackwrap a {
	margin: 10px;
	border: 1px solid #ddd;
	width: 100px;
	height: 40px;
	line-height: 40px;
	text-align: center;
	display: block;
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
.wrapper .table.dataTables-example.dataTable {
	width: 2000px;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
	<div class="J_gobackwrap">
		<a href="javascript:;" hidefocus="true" onclick="history.back();">
			<i class="fa fa-chevron-left" aria-hidden="true"></i>
			<span>返回上一级</span>
		</a>
		<h4><@_fximpfilename id=planid>${fxImpfilename.name!""}</@_fximpfilename></h4>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form class="form-inline row" id="J_formSearch" action="javascript:;">
					    <div class="form-group col-md-4">
					        <label for="J_name">品种名：</label>
					        <input type="text" class="form-control" id="J_name" placeholder="输入品种名" name="name">
					    </div>
					    <div class="form-group col-md-2">
					        <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','FxImpsaledataList');"><i class="fa fa-search"></i>查 询</button>
					    </div>
					</form>
					<div id="FxImpsaledataList" data-url="${request.contextPath}/fximpsaledata/tables.do?planid=${planid}"></div>
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
            if(zuiBrowser.versions.android || zuiBrowser.versions.iPad){

            }else{
            	var _w = $(window).width() - $(".zui-navbar").width() - 45;
            	$("#FxImpsaledataList").css({"width": _w, "paddingBottom": "30px"}).niceScroll();
            }
            unBlock(".wrapper");
        }
    });
};

$(function(){
	MyFun.search('J_formSearch','FxImpsaledataList');
})

function refreshFxImpsaledataList(){
	MyFun.ajaxRefreshTable("FxImpsaledataList");
}
</script>
</body>
</html>