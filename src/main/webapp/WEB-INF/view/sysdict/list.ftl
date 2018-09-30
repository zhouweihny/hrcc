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
    <script src="${request.contextPath}/js/jquery.min.js?v=2.1.4" type="text/javascript"></script>
    <script src="${request.contextPath}/js/bootstrap.min.js?v=3.3.6" type="text/javascript"></script>
  	 <script src="${request.contextPath}/js/jquery.form.js?v=1.0.0" type="text/javascript"></script>
    <!-- 自定义js -->
    <script src="${request.contextPath}/js/content.js?v=1.0.0" type="text/javascript"></script>
    <script src="${request.contextPath}/js/my.js?v=1.0.0" type="text/javascript"></script>
    <link href="${request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
 	<link href="${request.contextPath}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
 	<script src="${request.contextPath}/js/plugins/toastr/toastr.min.js" type="text/javascript"></script>
 	<script src="${request.contextPath}/js/plugins/layer/layer.min.js" type="text/javascript"></script>
 	<script src="${request.contextPath}/js/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${request.contextPath}/js/plugins/validate/messages_zh.min.js" type="text/javascript"></script>
    
    <style>
    	.zzz {text-align: center;}
    	.zChildTr {
    	}
    </style>
</head>
  
<#include "../tools/select.ftl"  /> 
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>数据字典</h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                        <div class="form-group col-md-3">
                            <label for="J_name">名称： </label>
                            <input type="text" class="form-control" id="name" placeholder="输入名称" name="name">
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','SysDictList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div class="m-t">
                         <a data-toggle="modal" class="btn btn-primary"   data-target="#info-form" onclick="javascript:MyFun.appendAjaxHtml('${request.contextPath}/sysdict/byid.do','info-form')">新增</a>
                    </div>
                    <div id="SysDictList" data-url="${request.contextPath}/sysdict/table.do">
               
                       	</div>    
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div id="info-form" class="modal fade" aria-hidden="true" ></div>
     
     <script type="text/javascript">
   		 function  refreshSysDictList(){
   			 MyFun.ajaxRefreshTable("SysDictList");
   		 }
     	var G_tr = null,
     		G_td_id = null;
     		
         function refreshdatatable(){
     		 G_tr = $("td[data-id='"+arguments[0][0]+"']").parent('tr');
     		 G_td_id=arguments[0][0];
     		 console.log(G_tr);
     		 refresh ();
   		 }
     		
     
     	function refresh () {
     		if(G_tr.next(".zChildTr").length)
     			G_tr.next(".zChildTr").remove();
	       	G_tr.addClass("open");
        	var _str = '<tr class="zChildTr"><td></td><td colspan="4"><div id="'+G_td_id+'"  data-url="'+"${request.contextPath}/sysdictdata/table.do?dictid="+G_td_id+'"></div></td></tr>';
        	G_tr.after(_str);
            MyFun.ajaxRefreshTable(G_td_id);
        	G_tr.find("i.fa").removeClass("fa-plus-circle").addClass("fa-minus-circle");
		}
     
     	$(function(){
     	 	  MyFun.ajaxRefreshTable("SysDictList");
     		  $('#SysDictList').on('click', 'td.zzz', function () {
		        var _this = $(this);
		        G_td_id = _this.data("id");
		        G_tr = _this.parent('tr');
		 
		        if(G_tr.hasClass("open")){
		        	//关闭
		        	G_tr.next(".zChildTr").remove();
		        	G_tr.removeClass("open");
		        	_this.find("i.fa").addClass("fa-plus-circle").removeClass("fa-minus-circle");
		        }else{
		        	refresh();
		        }
		    })
     	})
     </script>
</body>
</html>
 
