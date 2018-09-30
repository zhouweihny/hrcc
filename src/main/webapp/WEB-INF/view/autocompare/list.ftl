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
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
    <script src="${request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>自动对照</h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                     	<div class="form-group col-md-6">
                                            <input placeholder="开始日期" class="form-control layer-date" id="startdate" name="startdate" readonly  >
                                            <input placeholder="结束日期" class="form-control layer-date" id="enddate" name="enddate" readonly >
                        </div>
                    
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','AutocompareList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                         <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/autocompare/byid.do','info-form')">新增</a>
                    </div>
                    <div id="AutocompareList" data-url="${request.contextPath}/autocompare/table.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<script>

  //日期范围限制
        var start = {
            elem: '#startdate',
            format: 'YYYY-MM-DD hh:mm',
            min: '2017-01-01 00:00:00',
            max: laydate.now(+1),
            start: '2017-01-01 00:00',  //开始日期
            istime: false,
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            elem: '#enddate',
            format: 'YYYY-MM-DD hh:mm',
            min: '2017-01-01 00:00',
            max: laydate.now(+1),
            istime: false,
            istoday: false,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        laydate(start);
        laydate(end);

  function refreshAutocompareList(){
   	MyFun.ajaxRefreshTable("AutocompareList");
  }

  $(function(){
     // getSpaces();
     MyFun.ajaxRefreshTable("AutocompareList");
  })
</script>
</body>
</html>