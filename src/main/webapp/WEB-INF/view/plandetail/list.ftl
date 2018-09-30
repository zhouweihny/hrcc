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
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="J_gobackwrap">
                    <a href="javascript:;" hidefocus="true" onclick="history.back();">
                    	<i class="fa fa-chevron-left" aria-hidden="true"></i>
                    	<span>返回上一级</span>
                    </a>
                    <h4><@_plan id=planid>${plan.name!""}</@_plan></h4>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>基本 <small>分类，查找</small></h5>
                    </div>
                    <div class="ibox-content">
                    <div id="PlanDetailList" data-url="${request.contextPath}/plandetail/table.do?planid=${planid}">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<script>

  function refreshPlanDetailList(){
   	MyFun.ajaxRefreshTable("PlanDetailList");
  }

  $(function(){
     MyFun.ajaxRefreshTable("PlanDetailList");
  })
</script>
</body>
</html>

