<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>图文编辑</title>

<style>
#mainModal {
	width: 1070px;
	height: 608px;
	overflow: hidden;
}
#myModal .modal-dialog {
	width: 800px;
	/*height: 400px;*/
}
#mainModal .ibox-content {
	max-height: 550px!important;
	padding: 10px 20px 5px 20px;
}
.J_phone {
	background: url(${request.contextPath}/img/iphone.png) 0 0 no-repeat;
    width: 260px;
    height: 528px;
    position: relative;
    background-size: 260px 536px;
}
.J_phone .J_head {
	background: #323232;
	position: absolute;
	top: 66px;
	left: 14px;
	width: 228px;
	text-align: center;
}
.J_phone .J_head h3 {
	font-size: 14px;
	height: 25px;
	line-height: 25px;
	color: #fff;
	font-weight: normal;
	cursor: default;
}
.J_phone .J_listview {
	position: absolute;
	width: 229px;
	background: #F3F3F3;
	top: 103px;
	left: 13px;
	height: 366px;
}
.J_phone .J_listview .J_itemwrap {
	padding: 16px 16px 10px;
	height: 356px;
	overflow-y: auto;
	margin-top: 5px;
}
.J_phone .J_listview .J_itemwrap .thumbnail {
	position: relative;
	border-radius: 0;
    margin-bottom: 0;
    padding-bottom: 12px;
    cursor: pointer;
}
.J_phone .J_listview .J_itemwrap .thumbnail .caption {
	position: absolute;
	bottom: 12px;
	left: 4px;
	height: 30px;
	line-height: 30px;
	padding: 0;
	display: block;
	width: calc(100% - 8px);
	background: rgba(0,0,0,.4);
	color: #fff;
	padding-left: 8px;
	word-break:keep-all;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
}
.J_phone .J_listview .J_itemwrap .thumbnail .J_conten {
	width: 100%;
	height: 120px;
	background: #EBEBEB;
	display: flex;
    align-items:center;
    justify-content: center;
}
.J_phone .J_listview .J_itemwrap .thumbnail .J_conten img {
	width: 100%;
	height: 100%;
	margin-bottom: 0;
}
.fn-ellip-2 {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;/**可改为其他数字**/
    overflow: hidden;
}
.J_listItem {
	background: #fff;
	border: 1px solid #ddd;
	padding: 7px;
	/*margin-top: -1px;*/
	border-top: 0;
	cursor: pointer;
}
.zmedia {
	overflow: hidden;
	position: relative;
}
.zmedia img {
	line-height: 38px;
	max-width: 38px;
	height: 38px;
	float: right;
	margin-left: 15px;
}
.zmedia .zmedia-body {
	overflow: hidden;
}
.zmedia .zmedia-body p {
	font-size: 14px;
	margin: 0;
	color: #8f8f94;
	word-wrap: break-word;
	word-break: break-all;
}
.form-horizontal .form-group .curPos {
	font-size: 16px;
	vertical-align: middle;
	font-weight: bold;
	height: 27px;
	line-height: 27px;
}
.fn-center {
	text-align: center;
}
.J_edit_form {
	margin-top: 65px;
}
.J_edit_form .J_tempSave {
	padding: 7px 50px;
	margin: 10px 0 0 -40px;
}
.J_addItem {
	background: #fff;
    border: 1px solid #ddd;
    padding: 40px;
    margin-top: 20px;
    text-align: center;
    cursor: pointer;
}
.J_addItem .fa-plus {
	font-size: 40px;
}
.J_phone .J_listview .J_itemwrap .item.cur {
	border: 1px solid red;
}
/**
 * webuploader
**/
.webuploader-container {
	position: relative;
}
.webuploader-element-invisible {
	position: absolute !important;
	clip: rect(1px 1px 1px 1px); /* IE6, IE7 */
    clip: rect(1px,1px,1px,1px);
}
.webuploader-pick {
	display: block;
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	top: 0;
}
.webuploader-pick-disable {
	opacity: 0.6;
	pointer-events:none;
}
.ui-button-orange {
	display: inline-block;
	text-align: center;
	text-decoration: none;
	vertical-align: middle;
	cursor: pointer;
	font-size: 14px;
	font-family: inherit;
	font-weight: bold;
	border-radius: 2px;
	padding: 0 20px;
	background-image: none;
	height: 30px; 
	width: 100px!important;
	line-height: 30px;
	color: #fff;
	border: 1px solid #F67823;
	background-color: #F67823;
}
.ui-button-orange:hover {
	border: 1px solid #FF7E27;
	background-color: #FF7E27;
	color: #fff;
}
.ui-button-orange:active {
	border: 1px solid #ED7524;
	background-color: #ED7524;
	box-shadow: 1px 1px 3px #DB5A58 inset;
}
.J_phone .J_listview .J_itemwrap .J_bgcover {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0,0,0,.2);
	cursor: default;
	display: flex;
    align-items:center;
    justify-content: center;
    opacity: 0;
}
.J_phone .J_listview .J_itemwrap .J_bgcover a {
	color: #fff;
	font-size: 16px;
	display: inline-block;
	padding: 6px;
	border-radius: 8px;
}
.J_phone .J_listview .J_itemwrap .J_bgcover a:hover {
	color: #000;
	background: #fff;
}
.J_phone .J_listview .J_itemwrap .item:hover .J_bgcover {
	opacity: 1;
}
.J_phone .J_listview .J_itemwrap .J_bgcover .J_del {
	margin-right: 10px;
}

#edui_fixedlayer {
	z-index: 3001!important;
}
.myModal {

}
.myModal .modal-body {
	padding: 0 0 0 15px;
}
.myModal .itemList {
	list-style: none;
	margin: 0 0 0 -14px;
	padding: 0;
	height: 399px;
	overflow: auto;
}
.myModal .itemList li {
	height: 32px;
    line-height: 32px;
    cursor: pointer;
    width: 100%;
    word-break:keep-all;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    padding: 0 15px;
    color: #000;
}
.myModal .itemList li:hover, .myModal .itemList li.cur {
	background-color: #f4f5f9;
}
.myModal .itemList li span {
	display: inline-block;
	padding-left: 3px;
	color: #8d8d8d;
}
.myModal .itemList li .fa-plus {
	display: inline-block;
	color: #8d8d8d;
}
.myModal .content {
	padding: 0;
	margin-left: -15px;
	border-left: 1px solid #e7e7eb;
}
.myModal .content .J_uploadWrap {
	padding: 10px 20px;
	border-bottom: 1px solid #e7e7eb;
	height: 52px;
}
.myModal .content .J_uploadWrap .J_excelUpload {
	float: right;
	width: 106px;
    height: 32px;
    line-height: 32px;
    padding: 0;
    margin: 0;
}
.myModal .content .J_imgListWrap {
	padding: 20px;
	height: 332px;
	overflow: auto;
	margin-bottom: 15px;
}
.myModal .content .J_imgListWrap ul {
	list-style: none;
	margin: 0;
	padding: 0;
}
.myModal .content .J_imgListWrap ul li {
	float: left;
	margin-right: 21px;
	margin-bottom: 13px;
	text-align: center;
	position: relative;
	border: 1px solid #e7e7eb;
	cursor: pointer;
}
.myModal .content .J_imgListWrap ul li .pic_box {
	width: 117px;
	height: 117px;
	position: relative;
	overflow: hidden;
}
.myModal .content .J_imgListWrap ul li .pic_box .pic {
	width: 117px;
	height: 117px;
	border-bottom: 1px solid #e7e7eb;
	display: block;
}
.myModal .content .J_imgListWrap ul li .tit {
	display: block;
	height: 32px;
	line-height: 32px;
    word-break:keep-all;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    padding: 0 9px;
}
.fn-hide {
	display: none;
}
/* 清理浮动 */
.fn-clear:after {
    visibility:hidden;
    display:block;
    font-size:0;
    content:" ";
    clear:both;
    height:0;
}
.fn-clear {
    zoom:1; /* for IE6 IE7 */
}
.fn-right {
	float: right;
}
.fn-left {
	float: left;
}
.pagination a {
    text-decoration: none;
    border: 1px solid #AAE;
    color: #15B;
}
.pagination a, .pagination span {
    display: inline-block;
    padding: 0.1em 0.4em;
    margin-right: 5px;
    margin-bottom: 5px;
}
.pagination .current {
    background: #26B;
    color: #fff;
    border: 1px solid #AAE;
}
.pagination .current.prev, .pagination .current.next{
    color:#999;
    border-color:#999;
    background:#fff;
}
.data-table {
	margin: 0 20px;
	z-index: 9;
	/*width: 100%;*/
}
.data-table table tr th {
	padding: 10px 9px;
	text-align: center;
}
.data-table table tr td {
	text-align: center;
}
.data-table table tr td a {
	color: #08c;
	padding: 0 3px;
}
.data-table table tr td.fn-text-right a {
	display: block;
	margin-bottom: 3px;
}
.data-table .dataTbl-first {
	text-align: right;
}
.data-table .dataTbl-first .ui-checkbox {
	vertical-align: middle;
	margin-left: 5px;
}
.dataTbl-bottom {
	margin: 20px 0;
}
.dataTbl-bottom .paginationTools .ui-input {
	padding: 3px;
	height: 22px;
	line-height: 22px;
	width: 30px;
	vertical-align: top;
}
.dataTbl-bottom .paginationTools .ui-button {
	width: 34px;
	height: 22px;
	line-height: 20px;
	padding: 1px 5px;
	border: 1px solid #E3E3E8;
	display: inline-block;
	text-align: center;
}
.dataTbl-bottom .paginationTools .des {
	color: #8E8E8E;
}
.dataTbl-bottom .paginationTools .des, .dataTbl-bottom .paginationTools .total {
	color: #8E8E8E;
}
.dataTbl-bottom .paginationTools .total span {
	font-size: 13px;
	font-weight: bold;
	color: #808080;
}
.dataTbl-bottom .pagination {
    font-size:12px;
    margin: 0 auto;
}
.dataTbl-bottom .pagination a {
    text-decoration: none;
	border: solid 1px #AAE;
	color: #15B;
}
.dataTbl-bottom .pagination a, .dataTbl-bottom .pagination span {
    display: block;
    float: left;
    padding: 2px 8px;
    margin-right: 5px;
	margin-bottom: 5px;
}
.dataTbl-bottom .pagination .current {
    background: #08c;
    color: #fff;
	border: solid 1px #007EB7;
}
.pagination .current.prev, .pagination .current.next{
	color:#999;
	border-color:#999;
	background:#fff;
}
.data-table tbody .dataTbl-first .rownum, .data-table tbody .dataTbl-first .ui-checkbox, .data-table tbody .dataTbl-first .medIcon {
	vertical-align: middle;
}
.data-table tbody .dataTbl-first .rownum {
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	display: inline-block;
}
.data-table tbody .J_desHover {
	font-size: 16px;
	padding: 10px 20px;
}
#J_excelUploadS {
	height: 20px !important;
	width: 20px !important;
}
</style>

</head>
<body>

<div class="modal-dialog" id="mainModal">
    <div class="modal-content">
        <div class="form-horizontal">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>创建消息</h5>
                    <div class="ibox-tools">
                        <a class="close-link" data-dismiss="modal">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
						<div class="col-md-3">
							<div class="J_phone">
								<div class="J_head">
									<h3>微信群发助手</h3>
								</div>
								<div class="J_listview">
									<div class="J_itemwrap">
										<div id="J_itemwrap">
											
										</div>

										<div class="J_addItem" id="J_addItem">
											<i class="fa fa-plus"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="J_edit_form" id="J_edit_form">
								<div class="form-group">
								    <label class="col-sm-4 control-label">当前编辑：</label>
								    <div class="col-sm-7 curPos">暂无</div>
								</div>
								<div class="form-group">
								    <label class="col-sm-4 control-label">标题：</label>
								    <div class="col-sm-7">
										<input type="text" name="title" placeholder="请输入标题" class="form-control ui-input title"  value=""> 
									</div>
								</div>
								<div class="form-group">
								    <label class="col-sm-4 control-label">封面图片：</label>
								    <div class="col-sm-7">
								    	<a class="ui-button-orange" id="J_showImgMana">选择图片</a>
									</div>
								</div>
								<div class="form-group">
								    <label class="col-sm-4 control-label">作者：</label>
								    <div class="col-sm-7">
										<input type="text" name="author" placeholder="请输入作者" class="form-control ui-input author"  value=""> 
									</div>
								</div>
								<div class="form-group">
								    <label class="col-sm-4 control-label">描述：</label>
								    <div class="col-sm-7">
										<input type="text" name="description" placeholder="请输入描述" class="form-control ui-input description"  value=""> 
									</div>
								</div>
								<div class="form-group">
								    <label class="col-sm-4 control-label">原文地址：</label>
								    <div class="col-sm-7">
										<input type="text" name="url" placeholder="请输入原文地址" class="form-control ui-input url"  value=""> 
									</div>
								</div>
								<div class="form-group fn-center" style="margin-top: 25px">
									<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary" style="margin-left: 20px;width: 100px;" id="J_saveBtn">提交</button>
								</div>
							</div>
						</div>
						<div class="col-md-5">
							<script id="J_ueditor" type="text/plain" style="width:100%;height:425px;"></script>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade myModal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close J_closeImgMana" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
        		<h4 class="modal-title" id="myModalLabel">选择图片</h4>
			</div>
			<div class="modal-body">
                <div class="row">
					<div class="col-md-3">
						<ul class="itemList" id="J_itemList">
							<li class="cur">
								全部图片<span>(0)</span>
							</li>
						</ul>
					</div>
					<div class="col-md-9 content">
						<div class="J_uploadWrap">
							<span class="btn btn-success J_excelUpload" id="J_excelUpload">本地上传</span>
						</div>
						<div class="J_imgListWrap" id="J_imgListWrap">
							<ul class="fn-clear" id="J_imgList">
								
							</ul>
							<div class="dataTbl-bottom fn-hide fn-clear">
						        <div class="page pagination fn-left" id="Pagination">
						        	<!-- pagination -->
						        </div>
						        <div class="paginationTools fn-right">
						        	<span class="total">共 <span></span> 条，</span>
						        	<span class="des">到第</span>
						        	<input id="gotoPage" value="" type="text" class="ui-input" />
						        	<span class="des">页</span>
						        	<a class="ui-button ui-button-normal" href="javascript:;" hidefocus="true" id="">
						        		<span>GO</span>
						        	</a>
						        </div>
							</div>
						</div>
					</div>
                </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default J_closeImgMana">关闭</button>
	        	<button type="button" class="btn btn-primary" id="J_saveImgMana">确定</button>
			</div>
	    </div>
	</div>
</div>

<textarea class="fn-hide" id="T_imgList">
{{#each this}}
<li>
	<div class="pic_box">
		<img src='<@_properties key="img.url">${propertie}</@_properties>{{path}}' class="pic" data-crc="{{path}}" data-id="{{mediaid}}">
	</div>
	<span class="tit">{{remark}}</span>
</li>
{{/each}}
</textarea>

<script>
window.UEDITOR_HOME_URL = '${request.contextPath}/js/plugins/ueditor/';
</script>
<script src="${request.contextPath}/js/my.js?v=1.0.0"></script>
<script src="${request.contextPath}/js/plugins/ueditor/ueditor.config.js"></script>
<script src="${request.contextPath}/js/plugins/ueditor/ueditor.all.js"></script>
<script src="${request.contextPath}/js/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/pagination/1.2.1/jquery.pagination.js"></script>
<script>

var G_itemObj = null,
	nullFlag = 0,
	clickFlag = 0,
	msgId = "",
	wxid = "",
	articles = null,
	firstFlag = 1,
	G_ueditor = [],
	G_ueditorObj = null,
	oldIndex = 0;
$(function(){

	msgId = '${RequestParameters["id"]}';
	wxid = '${data.wxid}';
	
	<#if articles??>
	articles = ${articles};
	</#if>
	
	$("#J_addItem").on("click", function(){
		addzItem();
	})

	$("#J_edit_form").find(".title").on("keyup", function(){
		var _val = $(this).val();
		G_itemObj.data("title", _val).find(".caption").text(_val);
	}).end().find(".description").on("keyup", function(){
		var _val = $(this).val();
		G_itemObj.data("description", _val);
	}).end().find(".url").on("keyup", function(){
		var _val = $(this).val();
		G_itemObj.data("url", _val);
	}).end().find(".author").on("keyup", function(){
		var _val = $(this).val();
		G_itemObj.data("author", _val);
	})


	var $m_btn = $('#J_showImgMana');
	var $modal = $('#myModal');
    $m_btn.on('click', function(){
		$modal.css({"background": "rgba(0,0,0,.4)"}).modal('show');
    });

    // 测试 bootstrap 居中
    $modal.on('show.bs.modal', function(){
		var $this = $(this);
		var $modal_dialog = $this.find('.modal-dialog');
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
		$this.css('display', 'block');
		$modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - 140 - $modal_dialog.height()) / 2) });

		//图片上传初始化
		initExcelUpload();

		//图片分类列表初始化
		initImgList();
    });

    $(".J_closeImgMana").on("click", function(){
    	$modal.css('display', 'none').modal('hide');
    })

	//ueditor初始化
	initUeditor();

	$("#J_saveBtn").click(function(){
		saveForm();
	})

})

function initUeditor(){
	//配置编辑器
	var UEC = UEDITOR_CONFIG;
	//重新配置提示项
	UEC.toolbars = [[
		'source', '|','undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', '|','fontfamily', 'fontsize', 'forecolor', 'backcolor', '|',
		'insertorderedlist', 'insertunorderedlist', 'selectall', '|','justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|', 'emotion', 'map', 'template', 'background', '|', 'preview', '|'
	]];
	UEC.serverUrl = '${request.contextPath}/syswxmaterialimg/uploadtwimg.do';

	UE.registerUI('button',function(editor,uiName){
	    //注册按钮执行时的command命令，使用命令默认就会带有回退操作
	    editor.registerCommand(uiName,{
	        execCommand:function(){
	            // alert('execCommand:' + uiName)
	        }
	    });

	    //创建一个button
	    var btn = new UE.ui.Button({
	    	id: "J_excelUploadS",
	        //按钮的名字
	        name: "图片上传",
	        //提示
	        title: "图片上传",
	        //需要添加的额外样式，指定icon图标，这里默认使用一个重复的icon
	        cssRules :'background-position: -380px 0;',
	        //点击时执行的命令
	        onclick:function () {
	            //这里可以不用执行命令,做你自己的操作也可
	        	editor.execCommand(uiName);
	        }
	    });

	    //当点到编辑内容上时，按钮要做的状态反射
	    editor.addListener('selectionchange', function () {
	        var state = editor.queryCommandState(uiName);
	        if (state == -1) {
	            btn.setDisabled(true);
	            btn.setChecked(false);
	        } else {
	            btn.setDisabled(false);
	            btn.setChecked(state);
	        }
	    });

	    //因为你是添加button,所以需要返回这个button
	    return btn;
	}/*index 指定添加到工具栏上的那个位置，默认时追加到最后,editorId 指定这个UI是那个编辑器实例上的，默认是页面上所有的编辑器都会添加这个按钮*/);

	UE.getEditor('J_ueditor', {
	    allowDivTransToP: false//防止div生成p
	    ,zIndex : 3000
	    ,enableAutoSave: false //禁止自动保存
	}).addListener("ready",function(){
		G_ueditorObj = UE.getEditor('J_ueditor');

		setTimeout(function(){
			//图片上传初始化
			initExcelUploadS();
		}, 200)

		addzItem();
	});

	UE.getEditor('J_ueditor').addListener('blur', function(){
		var _index = $("#J_itemwrap").find(".item.cur").index();
		G_ueditor[_index] = G_ueditorObj.getContent();
	})
}

function addzItem(){
	var _J_itemwrap = $("#J_itemwrap"),
		len = _J_itemwrap.find(".item").length,
		str = "";

	if(len > 7){
		MyFun.to.i("只能创建8个条目");
		return false;
	}

	if(!len){
		//创建主条目
		str = '<div class="thumbnail item cur"> <a href="javascript:;" class="J_conten"> <img src="${request.contextPath}/img/default1.png"> <div class="caption">标题</div> </a> <div class="J_bgcover">  <a href="javascript:;" class="J_edit">编辑</a> </div> </div>';
		
	}else{
		str = '<div class="J_listItem item cur"> <a href="javascript:;"> <div class="zmedia"> <img src="${request.contextPath}/img/default2.png"> <div class="zmedia-body"> <p class="fn-ellip-2 caption">标题</p> </div> </div> </a> <div class="J_bgcover"> <a href="javascript:;" class="J_del">删除</a> <a href="javascript:;" class="J_edit">编辑</a> </div> </div>';
	}

	_J_itemwrap.find(".item").removeClass("cur").end().append(str).find(".J_edit").off("click").on("click", function(){
		var _this = $(this).parent().parent(),
			_index = _this.index();

		if(G_ueditor[_index])
			G_ueditorObj.setContent(G_ueditor[_index]);
		else
			G_ueditorObj.setContent('<p class="ue_t">请编辑正文内容</p>', false);

		_this.addClass("cur").siblings(".item").removeClass("cur");
		resetzForm(_index);
	}).end().find(".J_del").off("click").on("click", function(){
		var _this = $(this).parent().parent(),
			_index = _this.index();

		if(_index == 0){
			MyFun.to.i("至少要有一个大图");
			return false;
		}else{
			if(_this.prev().length){
				_this.prev().find(".J_edit").click();
			}
			_this.remove();
		}
		
		G_ueditor.splice(_index, 1);
	});

	if(firstFlag && articles){
		firstFlag = 0;
		initData();
	}else{
		resetzForm(len);
		G_ueditorObj.setContent('<p class="ue_t">请编辑正文内容</p>', false);
	}
}

function resetzForm(index){
	var _J_edit_form = $("#J_edit_form");

	G_itemObj = $("#J_itemwrap").find(".item").eq(index);

	_J_edit_form.find(".curPos").text("条目"+(index+1));

	var title = G_itemObj.data("title") || "",
		picurl = G_itemObj.data("picurl") || "",
		author = G_itemObj.data("author") || "",
		description = G_itemObj.data("description") || "",
		url = G_itemObj.data("url") || "";

	_J_edit_form.find(".title").val(title).end()
		.find(".description").val(description).end()
		.find(".author").val(author).end()
		.find(".url").val(url);
}

function initExcelUpload(){
	// 初始化Web Uploader
	var uploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf: '${request.contextPath}/js/plugins/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: '${request.contextPath}/syswxmaterialimg/save.do',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
			id: $("#J_excelUpload"), // id
			multiple: false  // false  单选 
        },
	    fileVal:"uploadfile",//设置文件上传域的name
	    formData: {
	    	"group": "图文编辑"
	    },
	    // fileNumLimit: window._IE ? "1" : "5",
	    fileNumLimit: '1',
	    paste: document.body,//ctrl+v粘贴
	    duplicate: true, //可重复上传同一文件
	    // 只允许选择excel文件。只有IE会识别
	    // http://www.w3school.com.cn/media/media_mimeref.asp
	    accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
        }
	});

	uploader.on("filesQueued", function(files){
		
	})

	//当某个文件的分块在发送前触发，主要用来询问是否要添加附带参数，大文件在开起分片上传的前提下此事件可能会触发多次。
	uploader.on('uploadBeforeSend', function (obj, data, headers) {
	    data.group = 'groupid';
	    data.remark = data.name || "";
	    data.wxid = wxid;
	});

	uploader.on("uploadStart", function(file){
		if(clickFlag)
			return false;
		clickFlag = 1;
		// createBlock("J_wrap", '正在上传...');
		console.log("正在上传");
	})

	// 当有文件添加进来的时候
	uploader.on( 'uploadSuccess', function( file, json ) {
	    if(json && json.code == '0000'){
	    	var mediaid = json.data.mediaid,
	    		path = json.data.path;
	    	G_itemObj.data("picurl", path).data("picid", mediaid).find("img").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+path});
	    	$(".J_closeImgMana").click();
	    }else{
    		MyFun.to.i(json.message || "文件上传失败");
    		G_itemObj.data("picurl", null);
	    }
	});

	uploader.on("error", function(type){
		if(type == 'F_DUPLICATE'){
			MyFun.to.i("请勿上传重复文件");
		}else if(type == 'Q_EXCEED_NUM_LIMIT'){
			MyFun.to.i("文件上传已达上限，将默认只上传第一个文件");
		}else{
			MyFun.to.i("文件上传失败，失败类型："+type);
		}
	})
	
	// 完成上传完了，成功或者失败。
	uploader.on( 'uploadComplete', function( file ) {
		//防止堵塞
		uploader.removeFile( file );
        clickFlag = 0;
    	// unBlock("J_wrap");
    	console.log("结束");
	});
}

function saveForm(){
	var _J_itemwrap = $("#J_itemwrap"),
		G_json = [];

	_J_itemwrap.find(".item").each(function(){
		var _this = $(this),
			_editBtn = _this.find(".J_edit"),
			index = _this.index(),
			title = _this.data("title"),
			picurl = _this.data("picurl"),
			picid = _this.data("picid"),
			description = _this.data("description"),
			url = _this.data("url"),
			author = _this.data("author"),
			editorContent = G_ueditor[index];

		if(!title){
			MyFun.to.i("请输入标题");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		if(!picid){
			MyFun.to.i("请上传图片");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		if(!description){
			MyFun.to.i("请输入描述");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		if(!author){
			MyFun.to.i("请输入作者");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		if(!url){
			MyFun.to.i("请输入原文地址");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		if(!editorContent || editorContent == '<p class=\"ue_t\">请编辑正文内容</p>'){
			MyFun.to.i("请编辑正文内容");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		nullFlag = 0;
		var attr = {};
		attr.title = title;//标题
		attr.thumb_media_id = picid;//图片素材ID
		attr.author = author;//作者
		attr.digest = description;//摘要
		attr.show_cover_pic = '0';//显示封面
		attr.content_source_url = url;//原文地址
		attr.content = editorContent.replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26");
		attr.picurl = picurl;//图片地址
		G_json.push(attr);
	})

	if(nullFlag){
		return false;
	}

	console.log(JSON.stringify(G_json))
	var ccontent = JSON.stringify(G_json);
	ccontent = encodeURIComponent(ccontent);
	var ztoast = window.toastr;
	ztoast.options = {
		"closeButton": false,
		"debug": false,
		"positionClass": "toast-top-right",
		"onclick": null,
		"showDuration": "30000",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	}
	ztoast.info("正在保存，请稍后...");

	$.PostJson("${request.contextPath}/syswxmaterialtw/updatecontent.do", "content="+ccontent+"&id="+msgId, function(state, json){
		if(state == 'success'){
			if(json && json.code == '0000'){
				$('#info-form').modal('hide');
				MyFun.to.s("操作成功");
			}else{
				MyFun.to.e(json.message || "操作失败");
			}
		}
		ztoast.clear();
	})
}

function initData(){
	for(var k=0,ken=articles.length; k<ken; k++){
		//假数据
		// articles[k].content = k+","+k+","+k+","+k;
		G_ueditor.push(articles[k].content.replace(/%25/g, '%').replace(/%2B/g, '+').replace(/%26/g, "&"));
	}

	var _J_itemwrap = $("#J_itemwrap"),
		_J_edit_form = $("#J_edit_form"),
		newArticles = articles.slice(1),
		len = newArticles.length;//小图数量

	var str = "";
	for(var i=0; i<len; i++){
		str += '<div class="J_listItem item" data-title="'+newArticles[i].title+'" data-description="'+newArticles[i].digest+'" data-picurl="'+newArticles[i].picurl+'" data-url="'+newArticles[i].content_source_url+'" data-author="'+newArticles[i].author+'" data-picid="'+newArticles[i].thumb_media_id+'">'+
				    '<a href="javascript:;">'+
				        '<div class="zmedia"> <img src="<@_properties key="img.url">${propertie}</@_properties>'+newArticles[i].picurl+'">'+
				            '<div class="zmedia-body">'+
				                '<p class="fn-ellip-2 caption">'+newArticles[i].title+'</p>'+
				            '</div>'+
				        '</div>'+
				    '</a>'+
				    '<div class="J_bgcover"> <a href="javascript:;" class="J_del">删除</a> <a href="javascript:;" class="J_edit">编辑</a> </div>'+
				'</div>';
	}

	_J_itemwrap.append(str).find(".J_edit").off("click").on("click", function(){
		var _this = $(this).parent().parent(),
			_index = _this.index();
		_this.addClass("cur").siblings(".item").removeClass("cur");
		G_ueditorObj.setContent(G_ueditor[_index]);
		resetzForm(_index);
	}).end().find(".J_del").off("click").on("click", function(){
		var _this = $(this).parent().parent(),
			_index = _this.index();
		if(_index == 0){
			MyFun.to.i("至少要有一个大图");
			return false;
		}else{
			if(_this.prev().length){
				_this.prev().find(".J_edit").click();
			}
			_this.remove();
		}
	});

	_J_itemwrap.find(".thumbnail").data("title", articles[0].title)
		.data("picurl", articles[0].picurl)
		.data("description", articles[0].digest)
		.data("url", articles[0].content_source_url)
		.data("author", articles[0].author)
		.data("picid", articles[0].thumb_media_id)
		.find("img").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+articles[0].picurl})
		.next(".caption").text(articles[0].title).end().end()
		.find(".J_bgcover .J_edit").click();
	G_ueditorObj.setContent(G_ueditor[0]);
}

function initImgList(){
	var str = 'wxid='+wxid;
	var pageParam = {
		url: '${request.contextPath}/syswxmaterialimg/list.do',
		perPage: "10",
		formStr: str,
		dataTpl: "T_imgList",
		linkTo: "J_imgListWrap",
		dataContainId: "J_imgList"
	}
	getPage(pageParam, function(json){
		$("#J_itemList li:eq(0) span").text('('+json.totalRows+')');
		$("#J_imgList").on("click", "li", function(){
			var _this = $(this),
				imgSrc = _this.find(".pic").data("crc"),
				picid = _this.find(".pic").data("id");

			G_itemObj.data("picurl", imgSrc).data("picid", picid).find("img").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+imgSrc});

			$(".J_closeImgMana").click();
		})
	})
}

//分页方法
function getPage(pageParam,callback){
	var newPageParam = $.extend({},{
		url: "ui/json/tableData.json?1=1",
		page_index: 0,
		perPage: 10,
		onepage: true,
		formStr: "",
		paginId: "Pagination",
		dataContainId: "dataTbody",
		blockId: "dataTable",
		dataTpl: "dataTbl_tpl",
		linkTo: "dataTable",
		gotoPage: "gotoPage"
	},pageParam);

  	//跳转页面时将goto 的值去除
	$("#"+newPageParam.gotoPage).val("");
  	$.PostJson(newPageParam.url, newPageParam.formStr+"&currentPage="+(newPageParam.page_index+1)+"&pageSize="+newPageParam.perPage, function(state,json){
  		$("#"+newPageParam.dataContainId).html("");
  		if(state=='success'){
  			if(json && json.code == '0000'){
			  	if(json.data.totalRows && json.data.totalRows != '0'){
			  		$("#"+newPageParam.dataContainId).temp($("#"+newPageParam.dataTpl).val() ,  json.data.data );
					if(newPageParam.onepage){
						$("#"+newPageParam.paginId).pagination( json.data.totalRows , {
							'items_per_page'      : newPageParam.perPage,
							'num_display_entries' : 4,
							'num_edge_entries'    : 1,
							'link_to'             : '#'+newPageParam.linkTo ,
							'prev_text'           : "上一页",
							'next_text'           : "下一页",
							'call_callback_at_once' : false,  //控制分页控件第一次不触发callback.
							'gotoPage'            : newPageParam.gotoPage,
							'callback'            : function(page_index, jq){
								newPageParam = $.extend({},newPageParam,{
									page_index: page_index,
									onepage: false
								});
								getPage(newPageParam, callback);
							}
						});

						$(".dataTbl-bottom").show();

						$("#"+newPageParam.gotoPage).parent().find(".total span").html(json.data.totalRows);
					}

					if(typeof callback == 'function'){
						callback(json.data);
					}
			  	}else {
			  		var _tdLength = $("#"+newPageParam.dataContainId).prev("thead").find("tr th").length;
			  		$("#"+newPageParam.dataContainId).html('<tr><td class="fn-center" colspan="'+_tdLength+'"><b>暂无数据，请更改搜索条件！</b></td></tr>');
					if(json && json.code == '9999')
						MyFun.to.e(json.message || "系统错误，请稍后再试！");
					$(".dataTbl-bottom").hide();
			  	}
  			}else {
  				MyFun.to.e("系统错误，请稍后再试！");
  				$(".dataTbl-bottom").hide();
  			}
  		}else {
			MyFun.to.e("系统错误，请稍后再试！");
			$(".dataTbl-bottom").hide();
		}
  	})
}

function initExcelUploadS(){
	// 初始化Web Uploader
	var uploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf: '${request.contextPath}/js/plugins/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: '${request.contextPath}/syswxmaterialimg/uploadtwimg.do',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
			id: $("#J_excelUploadS"), // id
			multiple: false  // false  单选 
        },
	    fileVal:"uploadfile",//设置文件上传域的name
	    formData: {},
	    // fileNumLimit: window._IE ? "1" : "5",
	    fileNumLimit: '1',
	    paste: false,//ctrl+v粘贴
	    duplicate: true, //可重复上传同一文件
	    // 只允许选择excel文件。只有IE会识别
	    // http://www.w3school.com.cn/media/media_mimeref.asp
	    accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
        }
	});

	uploader.on("filesQueued", function(files){
		
	})

	//当某个文件的分块在发送前触发，主要用来询问是否要添加附带参数，大文件在开起分片上传的前提下此事件可能会触发多次。
	uploader.on('uploadBeforeSend', function (obj, data, headers) {
	    data.wxid = wxid;
	});

	uploader.on("uploadStart", function(file){
		if(clickFlag)
			return false;
		clickFlag = 1;
		// createBlock("J_wrap", '正在上传...');
		console.log("正在上传");
	})

	// 当有文件添加进来的时候
	uploader.on( 'uploadSuccess', function( file, json ) {
	    if(json && json.code == '0000'){

	    	G_ueditorObj.focus();  
	    	G_ueditorObj.execCommand('inserthtml', '<img src="'+json.data.url+'" />'); 

	    }else{
    		MyFun.to.i(json.message || "文件上传失败");
	    }
	});

	uploader.on("error", function(type){
		if(type == 'F_DUPLICATE'){
			MyFun.to.i("请勿上传重复文件");
		}else if(type == 'Q_EXCEED_NUM_LIMIT'){
			MyFun.to.i("文件上传已达上限，将默认只上传第一个文件");
		}else{
			MyFun.to.i("文件上传失败，失败类型："+type);
		}
	})
	
	// 完成上传完了，成功或者失败。
	uploader.on( 'uploadComplete', function( file ) {
		//防止堵塞
		uploader.removeFile( file );
        clickFlag = 0;
    	// unBlock("J_wrap");
    	console.log("结束");
	});
}

</script>
</body>
</html>