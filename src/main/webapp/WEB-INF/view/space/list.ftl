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
    <script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>

<style>
.fn-hide {
    display: none!important;
}
.zui_item {

}
.zui_item .item {
    float: left;
    width: 170px;
    height: 100px;
    border: 1px solid #ddd;
    margin-right: 14px;
    margin-bottom: 46px;
    position: relative;
    cursor: pointer;
    padding: 5px;
    text-align: center;
}
.zui_item .item.active {
    border-color: #FF8245;
}
.zui_item .item h6 {
    font-size: 14px;
    color: #000;
}
.zui_item .item p {
    font-size: 12px;
}
.zui_item .item .fn-ellip-default {
    margin: 10px;
    text-align: center;
    width: 130px;
}
.zui_item .item .btn {
    display: block;
    width: 168px;
    border-radius: 0;
    margin: 0 auto;
    padding: 8px 0;
    position: absolute;
    bottom: 0;
    left: 0;
    border-right: 0;
    border-left: 0;
}
.zui_item .item .fa {
    position: absolute;
    top: -6px;
    right: -4px;
    padding: 10px;
    font-size: 20px;
    display: block;
}
.zui_item .item .fa-check-square-o {
    color: #FF8245;
    right: -6px;
    display: none;
}
.zui_item .item.active .fa-check-square-o {
    display: block;
}
.zui_item .item.stand {
    border-color: #017CDC;
}
.zui_item .item.stand .fa-check-square-o {
    /* color: #017CDC;
    display: block; */
}
.zui_item .item.active .fa-square-o, .zui_item .item.stand .fa-square-o {
    display: none;
}
.ztips {
    display: inline-block;
    margin-left: 8px;
    color: red;
    vertical-align: middle;
    cursor: default;
    font-size: 12px;
}
</style>

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>对码项目</h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="J_name">名称：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入名称" name="name">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','SpaceList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                        <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/space/byid.do','info-form')">新增</a>
                        <span class="ztips">（新建对码项目并选择添加药品目录！）</span>
                    </div>
                    <div id="SpaceList" data-url="${request.contextPath}/space/table.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>
<div id="info-formS" class="modal fade" aria-hidden="true" ></div>

<script>

  function refreshSpaceList(){
   	MyFun.ajaxRefreshTable("SpaceList");
  }

  $(function(){
     MyFun.ajaxRefreshTable("SpaceList");
  })
</script>
</body>
</html>

