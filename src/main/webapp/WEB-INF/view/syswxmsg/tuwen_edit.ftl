<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>图文编辑</title>

<style>
.modal.in .modal-dialog {
	width: 760px;
}
.modal-open .modal .ibox-content {
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
</style>

</head>
<body>

<div class="modal-dialog">
    <div class="modal-content">
        <form class="form-horizontal" id="save_form">
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
						<div class="col-md-5">
							<div class="J_phone">
								<div class="J_head">
									<h3>微信群发助手</h3>
								</div>
								<div class="J_listview">
									<div class="J_itemwrap">
										<div id="J_itemwrap">
											<!-- <div class="thumbnail item cur">
											    <a href="javascript:;" class="J_conten">
											    	<img src="http://www.dcloud.io/hellomui/images/yuantiao.jpg">
											        <div class="caption">标题</div>
											    </a>
											    <div class="J_bgcover">
											    	<a href="javascript:;" class="J_del">删除</a>
											    	<a href="javascript:;" class="J_edit">编辑</a>
											    </div>
											</div>
											<div class="J_listItem item cur">
												<a href="javascript:;">
													<div class="zmedia">
														<img src="http://www.dcloud.io/hellomui/images/muwu.jpg">
														<div class="zmedia-body">
															<p class="fn-ellip-2 caption">标题</p>
														</div>
													</div>
												</a>
											    <div class="J_bgcover">
											    	<a href="javascript:;" class="J_del">删除</a>
											    	<a href="javascript:;" class="J_edit">编辑</a>
											    </div>
											</div> -->
										</div>

										<div class="J_addItem" id="J_addItem">
											<i class="fa fa-plus"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-7">
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
								    <label class="col-sm-4 control-label">图片：</label>
								    <div class="col-sm-7">
								    	<a class="ui-button-orange J_excelUpload" id="J_excelUpload">图片上传</a>
									</div>
								</div>
								<div class="form-group">
								    <label class="col-sm-4 control-label">描述：</label>
								    <div class="col-sm-7">
										<input type="text" name="description" placeholder="请输入描述" class="form-control ui-input description"  value=""> 
									</div>
								</div>
								<div class="form-group">
								    <label class="col-sm-4 control-label">超链地址：</label>
								    <div class="col-sm-7">
										<input type="text" name="url" placeholder="请输入超链地址" class="form-control ui-input url"  value=""> 
									</div>
								</div>
								<div class="form-group fn-center" style="margin-top: 25px">
									<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary" style="margin-left: 20px;width: 100px;" id="J_saveBtn">提交</button>
								</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>


<script>

var G_itemObj = null,
	nullFlag = 0,
	clickFlag = 0,
	msgId = "",
	articles = null,
	firstFlag = 1;
$(function(){
	
	msgId = '${RequestParameters["id"]}';
	
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
	})

	setTimeout(function(){
		//图片上传初始化
		initExcelUpload();

		addzItem();
	}, 300)

	$("#J_saveBtn").click(function(){
		saveForm();
	})
})

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
		
	});

	if(firstFlag && articles){
		// articles = JSON.parse(articles);
		firstFlag = 0;
		initData();
	}else{
		resetzForm(len);
	}
}

function resetzForm(index){
	var _J_edit_form = $("#J_edit_form");

	G_itemObj = $("#J_itemwrap").find(".item").eq(index);

	_J_edit_form.find(".curPos").text("条目"+(index+1));

	var title = G_itemObj.data("title") || "",
		picUrl = G_itemObj.data("picUrl") || "",
		description = G_itemObj.data("description") || "",
		url = G_itemObj.data("url") || "";

	_J_edit_form.find(".title").val(title).end()
		.find(".description").val(description).end()
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
	    server: '${request.contextPath}/sysimg/upload.do',
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
	    	G_itemObj.data("picUrl", json.message).find("img").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+json.message});
	    }else{
    		MyFun.to.i(json.message || "文件上传失败");
    		G_itemObj.data("picUrl", null);
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
			picUrl = _this.data("picUrl") || _this.data("picurl"),
			description = _this.data("description"),
			url = _this.data("url");

		if(!title){
			MyFun.to.i("请输入标题");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		if(!picUrl){
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
		if(!url){
			MyFun.to.i("请输入链接地址");
			nullFlag = 1;
			_editBtn.click();
			return false;
		}
		nullFlag = 0;
		var attr = {};
		attr.title = title;
		attr.picUrl = picUrl;
		attr.description = description;
		attr.url = url;
		G_json.push(attr);
	})

	if(nullFlag){
		return false;
	}

	var ccontent = JSON.stringify(G_json);
	ccontent = encodeURIComponent(ccontent);
	$.PostJson("${request.contextPath}/syswxmsg/updatecontent.do", "content="+ccontent+"&type=2&id="+msgId, function(state, json){
		if(state == 'success'){
			if(json && json.code == '0000'){
				$('#info-form').modal('hide');
				MyFun.to.s("操作成功");
			}else{
				MyFun.to.e(json.message || "操作失败");
			}
		}
	})
}

function initData(){
	var _J_itemwrap = $("#J_itemwrap"),
		_J_edit_form = $("#J_edit_form"),
		newArticles = articles.slice(1),
		len = newArticles.length;//小图数量

	var str = "";
	for(var i=0; i<len; i++){
		str += '<div class="J_listItem item" data-title="'+newArticles[i].title+'" data-description="'+newArticles[i].description+'" data-picUrl="'+newArticles[i].picUrl+'" data-url="'+newArticles[i].url+'">'+
				    '<a href="javascript:;">'+
				        '<div class="zmedia"> <img src="<@_properties key="img.url">${propertie}</@_properties>'+newArticles[i].picUrl+'">'+
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
		.data("picUrl", articles[0].picUrl)
		.data("description", articles[0].description)
		.data("url", articles[0].url)
		.find("img").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+articles[0].picUrl})
		.next(".caption").text(articles[0].title).end().end()
		.find(".J_bgcover .J_edit").click();
}
</script>
</body>
</html>