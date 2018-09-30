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
<script src="${request.contextPath}/js/plugins/BootstrapMenu/BootstrapMenu.min.js"></script>
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<link href="${request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<style>
.dropdown-menu {
	position: absolute;
	top: 100%;
	left: 0;
	z-index: 1000;
	display: none;
	float: left;
	min-width: 160px;
	padding: 5px 0;
	margin: 2px 0 0;
	font-size: 14px;
	text-align: left;
	list-style: none;
	background-color: #fff;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	border: 1px solid #ccc;
	border: 1px solid rgba(0,0,0,.15);
	border-radius: 4px;
	-webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
	box-shadow: 0 6px 12px rgba(0,0,0,.175);
}
.dropdown-menu>li>a {
	display: block;
	padding: 6px 20px;
	clear: both;
	font-weight: 400;
	line-height: 1.42857143;
	color: #333;
	white-space: nowrap;
	font-size: 15px;
}
.J_separate span {
	display: inline-block;
	padding: 5px 10px;
	border: 1px solid #ddd;
	margin: 0 5px 10px;
	cursor: default;
	color: #525151;
}
</style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg zui">

<div class="zheader">
	<h1>文档</h1>
</div>

<div class="container fn-clear">
	<div class="J_mainCon">
		<div class="J_codeWrap">
			<table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
			    <thead>
			        <tr role="row">
			            <th>编码</th>
			            <th>名称</th>
			        </tr>
			    </thead>
			    <tbody id="J_tbl_body">
			    	<tr>
			    		<td>1</td>
			    		<td class="J_name">名称1</td>
			    	</tr>
			    	<tr>
			    		<td>2</td>
			    		<td class="J_name">下拉2</td>
			    	</tr>
			    	<tr>
			    		<td>3</td>
			    		<td class="J_name">名称3</td>
			    	</tr>
			    </tbody>
			</table>
		</div>
	</div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true"  ></div>

<script>
var curRow = null;

var G_html = [
	{
		label: '<input type="text" value="" class="J_input" />'
	},
	{
		label: `
			<select class="J_select">
				<option value="1">下拉1</option>
				<option value="2">下拉2</option>
				<option value="3">下拉3</option>
			</select>
			`
	}
]

$(function () {

	var menu = new BootstrapMenu('#OrderList .J_name', {
		fetchElementData: function(row) {
			curRow = $(row);
		},
		actionsGroups: [
			['add'], ['del']
		],
		actions: {
			add: {
				name: '插入输入框',
				onClick: function() {
					handleSelection('1');
				}
			}, 
			del: {
				name: '插入下拉框',
				onClick: function() {
					handleSelection('2');
				}
			}
		}
	});

	$("#OrderList .J_name").each(function(){
		$(this).on("dblclick", function(){
			curRow = $(this);
			var _ztype = curRow.data("ztype");
			handleSelection(_ztype);
		})
	}) 

});

function handleSelection(type){
	if(!type)
		return false;
	curRow.data("ztype", type);

	if(type == '1'){

		var _oldVal = curRow.text();
		curRow.html(G_html[0].label);
		var _input = curRow.find(".J_input");
		_input.val(_oldVal).focus();
		_input.off("blur").on("blur", function(){
			curRow.text(_input.val());
		})

	}else if(type == '2'){

		var _oldVal = curRow.text();
		curRow.html(G_html[1].label);
		var _select = curRow.find(".J_select");
		_select.find("option").each(function(){
			var _this = $(this),
				_val = _this.val(),
				_txt = _this.text();
			if(_oldVal === _txt){
				_this.attr({"selected": "selected"});
			}
		})
		_select.off("blur").on("blur", function(){
			var _text = _select.find("option:selected").text();
			curRow.text(_text);
		})
		_select.focus();

	}
}
</script>
</body>
</html>