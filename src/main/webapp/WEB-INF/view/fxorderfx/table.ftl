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
    <script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>

    <style>
        #J_excelUpload .webuploader-pick {
            cursor: pointer;
        }
        #J_excelUpload input[type="file"] {
            opacity: 0;
        }
        .J_autoCom_wrap {
            cursor: pointer;
            width: 80px;
        }
        .J_autoCom_wrap .check_wrap {
            display: inline-block;   
        }
        .J_autoCom_wrap .check_wrap .fa {
            cursor: pointer;
            font-size: 19px;
            display: inline-block;
            vertical-align: middle;
        }
        .J_autoCom_wrap .check_wrap .fa-square-o {
            margin-right: 3px;
        }
        .J_autoCom_wrap label {
            cursor: pointer;
            display: inline-block;
            padding: 0 15px 0 5px;
            margin-bottom: 0;
            vertical-align: middle;
        }
        .fn-hide {
            display: none!important;
        }
    </style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>厂商字典</h5>
                    </div>
                    <div class="ibox-content">
                    <div id="FactoryList" data-url="${request.contextPath}/factory/table.do">
<table class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
	<thead>
	<tr role="row">
		<th>一级节点</th>
		<th>二级节点</th>
		<th>时间</th>
		<th>订单数</th>
		<th>联合用药订单数</th>
		<th>销售额</th>
		<th>平均客单价</th>
		<th>联合用药平均客单价</th>
	</tr>
	</thead>
 	<tbody>
	<#list data as a>
	<tr class="gradeX">
		<td rowspan="4">${a.lv1!""}</td>
		<td rowspan="4">${a.lv2!""}</td>
	    <td>前一周</td>
		<td>${a.zbs1!""}</td>
		<td>${a.bs1!""}</td>
		<td>${a.xse1!""}</td>
		<td>${a.kdj1!""}</td>
		<td>${a.avdj1!""}</td>
    </tr>
    <tr class="gradeX">
    <td>前二周</td>
    	<td>${a.zbs2!""}</td>
		<td>${a.bs2!""}</td>
		<td>${a.xse2!""}</td>
		<td>${a.kdj2!""}</td>
		<td>${a.avdj2!""}</td>
    </tr>
   <tr class="gradeX">
   		<td>前三周</td>
    	<td>${a.zbs3!"0"}</td>
		<td>${a.bs3!""}</td>
		<td>${a.xse3!""}</td>
		<td>${a.kdj3!""}</td>
		<td>${a.avdj3!""}</td>
   </tr>
   <tr class="gradeX">
   		<td>前四周</td>
    	<td>${a.zbs4!""}</td>
		<td>${a.bs4!""}</td>
		<td>${a.xse4!""}</td>
		<td>${a.kdj4!""}</td>
		<td>${a.avdj4!""}</td>
    </tr>			
    </#list> 
    </tbody>
    </table>
     </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>


 
</body>
</html>

    