<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 数据表格</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico"> <link href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <!-- Data Tables -->
    <link href="${request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet"> 
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
    <script src="${request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>公共号管理</h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="J_name">公共号名称：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入公共号名称" name="name">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','SysWxList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                         <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/syswx/byid.do','info-form')">新增</a>
                    </div>
                    <div id="SysWxList" data-url="${request.contextPath}/syswx/table.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<script>

  function refreshSysWxList(){
   	MyFun.ajaxRefreshTable("SysWxList");
  }

  $(function(){
     MyFun.ajaxRefreshTable("SysWxList");
  })
</script>
</body>
</html>

