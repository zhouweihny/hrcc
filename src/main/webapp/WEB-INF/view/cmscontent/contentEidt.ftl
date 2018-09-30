<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>图文编辑</title>

<link rel="stylesheet" href="${request.contextPath}/js/plugins/colorpicker/colorpicker.css">
<link rel="stylesheet" href="${request.contextPath}/css/contentEdit.css?v=201704111137">

<style>
#colorPicker {
	position: relative;
	width: 25px;
	height: 25px;
	background: url(${request.contextPath}/js/plugins/colorpicker/images/select4.png) 0 0 no-repeat;
	background-size: 100%;
	margin: -3px 0 0 -2px;
}
#colorPicker div {
	position: absolute;
	top: 5px;
	left: 5px;
	width: 15px;
	height: 15px;
	background: url(${request.contextPath}/js/plugins/colorpicker/images/color.png) 0 0 no-repeat;
	background-size: 100%;
}
.menuContent {
  position: relative;
  z-index: 9999;
}
.menuContent .ztree {
  margin-top: 0;
  border: 1px solid #a9a9a9;
  background: #f0f6e4;
  width: 220px;
  height: 260px;
  overflow-y: scroll;
  overflow-x: auto;
  position: absolute;
  left: 21px;
  top: 25px;
  display: none;
}
.J_imgPrev {
	border: 1px solid #ddd;
	padding: 2px;
	border-radius: 3px;
}
#J_excelUploadS div {
	width: 22px;
	height: 22px;
}
.J_con .m3 {
	position: relative;
}
#J_ueditor {
	width: 100%;
	position: absolute;
	top: -92px;
	right: 1px;
}
#J_ueditor .edui-editor, #J_ueditor .edui-editor .edui-editor-toolbarbox {
	border-radius: 0;
}
#mainModal .ibox {
	margin-bottom: 0;
}
</style>

</head>
<body>

<div class="modal-dialog" id="mainModal">
  <div class="modal-content">
    <form class="form-horizontal" id="save_form">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>内容编辑</h5>
          <div class="ibox-tools">
            <a class="close-link" data-dismiss="modal">
              <i class="fa fa-times"></i>
            </a>
          </div>
        </div>
        <div class="ibox-content">
        	<div class="J_ztab">
        		<div class="tabs-container">
              <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">封面编辑</a></li>
                  <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">正文编辑</a></li>
	            </ul>
	            <div class="tab-content">
	              <div id="tab-1" class="tab-pane active">
                  <div class="panel-body">
                    <div class="J_edit_form" id="J_edit_form">
                      <input  type="hidden" id="awxid" name="wxid" value="">
	   							  		<input  type="hidden" id="categoryid" name="categoryid" value="">
                    	<div class="form-group">
                        <label class="col-sm-2 control-label">分类：</label>
                        <div class="col-sm-7">
									    		<div id="menuContent" class="menuContent">
														<input  readonly  required   value=""  type="text" name="treeMenu" id="treeMenu" class="form-control" readonly  />
											 			<ul id="treeDemo" class="ztree"></ul>
													</div>
                          <span class="help-block m-b-none"></span>
						  					</div>                         
                      </div>
                      <div class="form-group">
                  	    <label class="col-sm-2 control-label">标题：</label>
                  	    <div class="col-sm-7">
                          <input type="text" name="title" placeholder="请输入标题" class="form-control ui-input title"  value=""> 
                        </div>
                      </div>
                      <div class="form-group">
                  	    <label class="col-sm-2 control-label">封面图片：</label>
                  	    <div class="col-sm-7">
                          <a class="ui-button-orange" id="J_excelUpload">选择图片</a>
                          <img src="${request.contextPath}/img/default2.png" width="30" height="30" id="J_imgPrev" class="J_imgPrev">	
                        </div>
                      </div>
                      <div class="form-group">
                  	    <label class="col-sm-2 control-label">作者：</label>
                  	    <div class="col-sm-7">
                    			<input type="text" name="author" placeholder="请输入作者" class="form-control ui-input author"  value=""> 
                        </div>
                      </div>
                      <div class="form-group">
                    	    <label class="col-sm-2 control-label">描述：</label>
                    	    <div class="col-sm-7">
                      			<input type="text" name="description" placeholder="请输入描述" class="form-control ui-input description"  value=""> 
                          </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div id="tab-2" class="tab-pane">
                  <div class="panel-body">
                    <div class="J_contentWrap">
                    	<div class="J_colorPick">
                    		<ul id="colorlist">
                    		    <li class="bgc01 trans coloritem" color="#c53f46"></li>
                    		    <li class="bgc02 trans coloritem" color="#ffcb15"></li>
                    		    <li class="bgc03 trans coloritem" color="#ffa921"></li>
                    		    <li class="bgc04 trans coloritem" color="#fde800"></li>
                    		    <li class="bgc05 trans coloritem" color="#87c943"></li>
                    		    <li class="bgc06 trans coloritem" color="#129527"></li>
                    		    <li class="bgc07 trans coloritem" color="#00589c"></li>
                    		    <li class="bgc08 trans coloritem" color="#009e96"></li>
                    		    <li class="bgc09 trans coloritem" color="#aa89bd"></li>
                    		    <li class="bgcBtn bgcBtnjs">
                    		        <div id="colorPicker" title="选择其他颜色"><div></div></div>
                    		    </li>
                    		</ul>
                    	</div>
                    	<div class="J_con fn-clear">
                    		<div class="md m1">
                    			<ul id="J_listTab" class="J_listTab">
                    				<li t="title" class="curt"><a href="javascript:">标题</a></li>
				                    <li t="content"><a href="javascript:">内容</a></li>
				                    <li t="jeDate"><a href="javascript:">节日</a></li>
				                    <li t="tuImg"><a href="javascript:">图片</a></li>
				                    <li t="pinTu"><a href="javascript:">拼图</a></li>
				                    <li t="Follow"><a href="javascript:">关注/原文</a></li>
				                    <li t="fenLine"><a href="javascript:">分割线</a></li>
				                    <li t="spread"><a href="javascript:">互推</a></li>
				                    <li t="Chother"><a href="javascript:">其他</a></li>
                  				</ul>
												</div>
                        <div class="md m2">
                          <div id="J_conpart" class="J_conpart"></div>
        								</div>
        								<div class="md m3">
          								<script id="J_ueditor" type="text/plain" style="width:100%;height:500px;"></script>
        								</div>
                      </div>
                    </div>
                	</div>
            		</div>
                <div class="form-group fn-center" style="margin: 15px 0 0;">
              		<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
              		<button type="button" class="btn btn-primary" style="margin-left: 20px;width: 100px;" id="J_saveBtn">提交</button>
              	</div>
              </div>
            </div>
        	</div>
          <div class="row">
						<div class="col-md-5"></div>
						<div class="col-md-7"></div>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>

<script>

var setting = {
  view: {
    dblClickExpand: false
  },
  data: {
    simpleData: {
	    enable: true
    }
  },
  callback: {
    beforeClick: beforeClick,
    onClick: onClick
  }
};

var zNodes =[
  <@_syswxlist>
    <#if (sysWxs?size > 0)>
    	<#list  sysWxs as wx >
        {id:"${wx.id}", pId:'', name:"${wx.name}",type:"wx",wxid:"${wx.id}"},
        <@_cmscategorylist wxid=wx.id>  
          <#list  cmsCategories as cmscategory >
            {id:"${cmscategory.id}", pId:"${wx.id}", name:"${cmscategory.name}",type:"cmscategory",wxid:"${wx.id}"},
          </#list>
        </@_cmscategorylist>   
      </#list>
    </#if> 
	</@_syswxlist>    
];

function beforeClick(treeId, treeNode) {
	if(treeNode.type!="cmscategory") {
		MyFun.to.e("请选择分类");
		return false;
	}    
}

function onClick(e, treeId, treeNode) {
  var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
  nodes = zTree.getSelectedNodes(),
  v = "";
  nodes.sort(function compare(a,b){return a.id-b.id;});
  for (var i=0, l=nodes.length; i<l; i++) {
    v += nodes[i].name + ",";
  }
  if (v.length > 0 ) v = v.substring(0, v.length-1);
  var cityObj = $("#treeMenu");
  cityObj.attr("value", v);
  $('#awxid').val(treeNode.wxid);
  if(treeNode.type=='cmscategory')
 	 	$('#categoryid').val(treeNode.id);
  hideMenu();
}

function showMenu() {
  $("#menuContent .ztree").css({"display": "block"});
  $(document).bind("mousedown", onBodyDown);
}
function hideMenu() {
  $("#menuContent .ztree").css({"display": "none"});
  $(document).unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
  if(!(event.target.id == 'treeMenu' || event.target.id == 'treeDemo' || $(event.target).parents("#menuContent").length>0)){
    hideMenu();
  }
}

var G_itemObj = null,
	nullFlag = 0,
	clickFlag = 0,
	msgId = "",
	articles = null,
	G_ueditor = null,
	G_ueditorObj = null,
	picurl = "";
	picid = "";
	G_json = [];
	changeFlag = 0;//表示新增 1修改

var qrColorPicker = null;

$(function(){

	<#if data??>
	articles = ${data};
	</#if>
	if(articles && articles.title){
		changeFlag = 1;
	}

	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
  $("#treeMenu").click(function(){
    showMenu();
  })
	
	setTimeout(function(){
		//图片上传初始化
		initExcelUpload();
		initUeditorTab();
	}, 200)

	$("#J_saveBtn").click(function(){
		saveForm();
	})

	$('#info-formS').on('hidden.bs.modal', function () {
    // 关闭Dialog前移除编辑器
    try{
    	G_ueditorObj.destroy();
    }catch(e){
    	console.log(e)
    }
	});
})

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
    	// G_itemObj.data("picurl", path).data("picid", mediaid).find("img").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+path});
    	picurl = json.message;

    	$("#J_imgPrev").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+picurl});

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

function initUeditorTab(){
	var g = {
    jsonPath: {title:[{id:"title_001",path:"template/default/temp/title/001.html"},{id:"title_002",path:"template/default/temp/title/002.html"},{id:"title_003",path:"template/default/temp/title/003.html"},{id:"title_004",path:"template/default/temp/title/004.html"},{id:"title_005",path:"template/default/temp/title/005.html"},{id:"title_006",path:"template/default/temp/title/006.html"},{id:"title_007",path:"template/default/temp/title/007.html"},{id:"title_008",path:"template/default/temp/title/008.html"},{id:"title_009",path:"template/default/temp/title/009.html"},{id:"title_010",path:"template/default/temp/title/010.html"},{id:"title_011",path:"template/default/temp/title/011.html"},{id:"title_012",path:"template/default/temp/title/012.html"},{id:"title_013",path:"template/default/temp/title/013.html"},{id:"title_014",path:"template/default/temp/title/014.html"},{id:"title_015",path:"template/default/temp/title/015.html"}],content:[{id:"content_001",path:"template/default/temp/content/001.html"},{id:"content_002",path:"template/default/temp/content/002.html"},{id:"content_003",path:"template/default/temp/content/003.html"},{id:"content_004",path:"template/default/temp/content/004.html"},{id:"content_005",path:"template/default/temp/content/005.html"},{id:"content_006",path:"template/default/temp/content/006.html"},{id:"content_007",path:"template/default/temp/content/007.html"},{id:"content_008",path:"template/default/temp/content/008.html"},{id:"content_009",path:"template/default/temp/content/009.html"},{id:"content_010",path:"template/default/temp/content/010.html"},{id:"content_011",path:"template/default/temp/content/011.html"},{id:"content_012",path:"template/default/temp/content/012.html"},{id:"content_013",path:"template/default/temp/content/013.html"},{id:"content_014",path:"template/default/temp/content/014.html"},{id:"content_015",path:"template/default/temp/content/015.html"},{id:"content_016",path:"template/default/temp/content/016.html"}],spread:[{id:"spread_001",path:"template/default/temp/spread/001.html"},{id:"spread_002",path:"template/default/temp/spread/002.html"},{id:"spread_003",path:"template/default/temp/spread/003.html"},{id:"spread_004",path:"template/default/temp/spread/004.html"}],jeDate:[{id:"jeDate_001",path:"template/default/temp/jeDate/001.html"},{id:"jeDate_002",path:"template/default/temp/jeDate/002.html"},{id:"jeDate_003",path:"template/default/temp/jeDate/003.html"},{id:"jeDate_004",path:"template/default/temp/jeDate/004.html"},{id:"jeDate_005",path:"template/default/temp/jeDate/005.html"},{id:"jeDate_006",path:"template/default/temp/jeDate/006.html"},{id:"jeDate_007",path:"template/default/temp/jeDate/007.html"},{id:"jeDate_008",path:"template/default/temp/jeDate/008.html"},{id:"jeDate_009",path:"template/default/temp/jeDate/009.html"},{id:"jeDate_010",path:"template/default/temp/jeDate/010.html"},{id:"jeDate_011",path:"template/default/temp/jeDate/011.html"},{id:"jeDate_012",path:"template/default/temp/jeDate/012.html"},{id:"jeDate_013",path:"template/default/temp/jeDate/013.html"},{id:"jeDate_014",path:"template/default/temp/jeDate/014.html"},{id:"jeDate_015",path:"template/default/temp/jeDate/015.html"},{id:"jeDate_016",path:"template/default/temp/jeDate/016.html"},{id:"jeDate_017",path:"template/default/temp/jeDate/017.html"},{id:"jeDate_018",path:"template/default/temp/jeDate/018.html"},{id:"jeDate_019",path:"template/default/temp/jeDate/019.html"},{id:"jeDate_020",path:"template/default/temp/jeDate/020.html"},{id:"jeDate_021",path:"template/default/temp/jeDate/021.html"},{id:"jeDate_022",path:"template/default/temp/jeDate/022.html"}],tuImg:[{id:"tuImg_001",path:"template/default/temp/tuImg/001.html"},{id:"tuImg_002",path:"template/default/temp/tuImg/002.html"},{id:"tuImg_003",path:"template/default/temp/tuImg/003.html"},{id:"tuImg_004",path:"template/default/temp/tuImg/004.html"},{id:"tuImg_005",path:"template/default/temp/tuImg/005.html"},{id:"tuImg_006",path:"template/default/temp/tuImg/006.html"},{id:"tuImg_007",path:"template/default/temp/tuImg/007.html"},{id:"tuImg_008",path:"template/default/temp/tuImg/008.html"},{id:"tuImg_009",path:"template/default/temp/tuImg/009.html"},{id:"tuImg_010",path:"template/default/temp/tuImg/010.html"},{id:"tuImg_011",path:"template/default/temp/tuImg/011.html"}],pinTu:[{id:"pinTu_001",path:"template/default/temp/pinTu/001.html"},{id:"pinTu_002",path:"template/default/temp/pinTu/002.html"},{id:"pinTu_003",path:"template/default/temp/pinTu/003.html"},{id:"pinTu_004",path:"template/default/temp/pinTu/004.html"},{id:"pinTu_005",path:"template/default/temp/pinTu/005.html"},{id:"pinTu_006",path:"template/default/temp/pinTu/006.html"},{id:"pinTu_007",path:"template/default/temp/pinTu/007.html"},{id:"pinTu_008",path:"template/default/temp/pinTu/008.html"},{id:"pinTu_009",path:"template/default/temp/pinTu/009.html"},{id:"pinTu_010",path:"template/default/temp/pinTu/011.html"},{id:"pinTu_011",path:"template/default/temp/pinTu/012.html"}],fenLine:[{id:"fenLine_001",path:"template/default/temp/fenLine/001.html"},{id:"fenLine_002",path:"template/default/temp/fenLine/002.html"},{id:"fenLine_003",path:"template/default/temp/fenLine/003.html"},{id:"fenLine_004",path:"template/default/temp/fenLine/004.html"},{id:"fenLine_005",path:"template/default/temp/fenLine/005.html"},{id:"fenLine_006",path:"template/default/temp/fenLine/006.html"},{id:"fenLine_007",path:"template/default/temp/fenLine/007.html"},{id:"fenLine_008",path:"template/default/temp/fenLine/008.html"},{id:"fenLine_009",path:"template/default/temp/fenLine/009.html"},{id:"fenLine_010",path:"template/default/temp/fenLine/010.html"},{id:"fenLine_011",path:"template/default/temp/fenLine/011.html"},{id:"fenLine_012",path:"template/default/temp/fenLine/012.html"},{id:"fenLine_013",path:"template/default/temp/fenLine/013.html"},{id:"fenLine_014",path:"template/default/temp/fenLine/014.html"},{id:"fenLine_015",path:"template/default/temp/fenLine/015.html"},{id:"fenLine_016",path:"template/default/temp/fenLine/016.html"},{id:"fenLine_017",path:"template/default/temp/fenLine/017.html"},{id:"fenLine_018",path:"template/default/temp/fenLine/018.html"},{id:"fenLine_019",path:"template/default/temp/fenLine/019.html"},{id:"fenLine_020",path:"template/default/temp/fenLine/020.html"},{id:"fenLine_021",path:"template/default/temp/fenLine/021.html"},{id:"fenLine_022",path:"template/default/temp/fenLine/022.html"},{id:"fenLine_023",path:"template/default/temp/fenLine/023.html"},{id:"fenLine_024",path:"template/default/temp/fenLine/024.html"},{id:"fenLine_025",path:"template/default/temp/fenLine/025.html"},{id:"fenLine_026",path:"template/default/temp/fenLine/026.html"},{id:"fenLine_027",path:"template/default/temp/fenLine/027.html"},{id:"fenLine_028",path:"template/default/temp/fenLine/028.html"},{id:"fenLine_029",path:"template/default/temp/fenLine/029.html"},{id:"fenLine_030",path:"template/default/temp/fenLine/030.html"},{id:"fenLine_031",path:"template/default/temp/fenLine/031.html"},{id:"fenLine_032",path:"template/default/temp/fenLine/032.html"},{id:"fenLine_033",path:"template/default/temp/fenLine/033.html"},{id:"fenLine_034",path:"template/default/temp/fenLine/034.html"},{id:"fenLine_035",path:"template/default/temp/fenLine/035.html"}],Chother:[{id:"Chother_001",path:"template/default/temp/Chother/001.html"},{id:"Chother_002",path:"template/default/temp/Chother/002.html"},{id:"Chother_003",path:"template/default/temp/Chother/003.html"},{id:"Chother_004",path:"template/default/temp/Chother/004.html"}],Follow:[{id:"Follow_001",path:"template/default/temp/Follow/001.html"},{id:"Follow_002",path:"template/default/temp/Follow/002.html"},{id:"Follow_003",path:"template/default/temp/Follow/003.html"},{id:"Follow_004",path:"template/default/temp/Follow/004.html"},{id:"Follow_005",path:"template/default/temp/Follow/005.html"},{id:"Follow_006",path:"template/default/temp/Follow/006.html"},{id:"Follow_007",path:"template/default/temp/Follow/007.html"},{id:"Follow_008",path:"template/default/temp/Follow/008.html"}]},
	    listbox: $("#listbox")
	};
	
	//加载调色板
	$("#colorPicker").ColorPicker({
    color: '#EFEFEF',
    onShow: function (colpkr) {
        $(colpkr).fadeIn(500);
        return false;
    },
    onHide: function (colpkr) {
        $(colpkr).fadeOut(500);
        return false;
    },
    onChange: function (hsb, hex, rgb, colpkr) {
        $('#colorPicker div').css('background', '#' + hex);
        setTempColor('#' + hex);
    }
  });

  $("#colorlist").on("click", ".coloritem", function(){
  	var _this = $(this),
  		color = _this.attr("color");
  	setTempColor(color);
  })

  $("#J_listTab").on("click", "li", function(){
  	if(clickFlag)
  		return false;
  	clickFlag = 1;
		$("#J_conpart .zitem").hide();
		$(this).addClass("curt").siblings("li").removeClass("curt");
    	var _type = $(this).attr("t"),
    		cdata = g.jsonPath[_type],
    		cnt = 0;

		for(var i=0, len=cdata.length; i<len; i++){
			var item = cdata[i];
			if($("#" + item.id).length){
				$("[id^='" + _type + "']").show();
			    var znicescroll = $(".J_con .m2").getNiceScroll();
			    if(znicescroll.length){
			    	znicescroll.resize();
			    	znicescroll[0].doScrollTop(0);
			    }else{
				    $(".J_con .m2").niceScroll();
				    znicescroll[0].doScrollTop(0);
			    }
				clickFlag = 0;
				break;
			}else{
				$.AjaxHtml("${request.contextPath}/"+item.path, "", function(state, html){
					var _html = '<div id="' + item.id + '" class="zitem">'+html+'</div>';
					$("#J_conpart").append(_html);

					if(cnt == cdata.length - 1){
				    var znicescroll = $(".J_con .m2").getNiceScroll();
				    if(znicescroll.length){
				    	znicescroll.resize();
				    	znicescroll[0].doScrollTop(0);
				    }else{
					    $(".J_con .m2").niceScroll();
				    }
				    clickFlag = 0;
				    cnt = 0;

				    $("#J_conpart").find("img").each(function(){
				    	var _src = $(this).attr("src"),
				    		hasAlter = $(this).data("hasAlter");
				    	if(hasAlter) return;
				    	if(_src.indexOf("template/default/temp") !== -1){
				    		_src = _src.replace("template/default/temp", "${request.contextPath}/template/default/temp");
				    	}else if(_src.indexOf("../template/default/temp") !== -1){
				    		_src = _src.replace("../template/default/temp", "${request.contextPath}/template/default/temp");
				    	}
				    	
				    	$(this).attr("src", _src).data("hasAlter", "1");
				    }).end().find(".zitem").off("click").on("click", function(){
				    	var _str = $(this).html();
				    	 setTimeout(function(){
				    	    G_ueditorObj.execCommand('insertHtml', _str);
				    	}, 300)
				    })
					}else{
  					cnt++;
					}
				})
			}
		}
  }).find("li:eq(0)").click();

	//ueditor初始化
	initUeditor();
}

function initUeditor(){
	//配置编辑器
	var UEC = UEDITOR_CONFIG;
	//重新配置提示项
	UEC.toolbars = [[
		'source', '|','undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', '|','fontfamily', 'fontsize', 'forecolor', 'backcolor', '|',
		'insertorderedlist', 'insertunorderedlist', 'selectall', '|','justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|', 'emotion', 'map', 'template', 'background', '|'
	]];
	UEC.serverUrl = '${request.contextPath}/syswxmaterialimg/uploadtwimg.do';

	UE.registerUI('zimgUpload',function(editor,uiName){
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
    ,initialFrameHeight: 430//设置编辑器高度
    ,initialFrameWidth: 460
    ,scaleEnabled: false
    ,autoHeightEnabled: false
    ,elementPathEnabled: false//不显示底下路径
    ,wordCount: false//不统计字数
	}).addListener("ready",function(){
		G_ueditorObj = UE.getEditor('J_ueditor');

		setTimeout(function(){
			//图片上传初始化
			initExcelUploadS();
		}, 200)

		if(changeFlag){
			initData();
		}
	});
}

function initExcelUploadS(){
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
	  	G_ueditorObj.execCommand('inserthtml', '<img src="<@_properties key="img.url">${propertie}</@_properties>'+json.message+'" width="80%" height="80%" />');
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

(function($){
  $.extend({
  	/*
  	* 基本的ajax，返回html 
  	*/
    AjaxHtml: function(url, datas , callback) {
      $.ajax({
        url: url ,
        type: "GET",
        async: false,
        data : datas +"&_=" + (new Date()).getTime(),
        cache: false,
        dataType: "html",
				beforeSend: function (xhr) {
			    xhr.overrideMimeType("text/plain; charset=utf-8");
				},
        success: function(html) {
          callback("success", html);
        },
        error: function() {
          callback("error", null);
        }
      });
    }
  });
})(jQuery);

function setTempColor(color) {
  var _listBox = $("#J_conpart"),
  	list = _listBox.find(".bgc");
  for (var i = 0; i < list.length; i++) {
    try {
      var bgcrgba = $(list[i]).css("backgroundColor").split(',')
      if (bgcrgba.toString().indexOf("rgba") >= 0) {
        var opacity = bgcrgba[bgcrgba.length - 1].replace(")", "");
        $(list[i]).css("opacity", opacity)
      }
    } finally {
      $(list[i]).css("backgroundColor", color);
    }
  }
  _listBox.find(".fc").css("color", color);
  _listBox.find(".bc").css("borderColor", color);
  _listBox.find(".btc").css("borderTopColor", color);
  _listBox.find(".brc").css("borderRightColor", color);
  _listBox.find(".bbc").css("borderBottomColor", color);
  _listBox.find(".blc").css("borderLeftColor", color);
}

function saveForm(){
	var _J_edit_form = $("#J_edit_form"),
		wxid = _J_edit_form.find("#awxid").val(),
		categoryid = _J_edit_form.find("#categoryid").val(),
		title = _J_edit_form.find(".title").val(),
		author = _J_edit_form.find(".author").val(),
		description = _J_edit_form.find(".description").val(),
		editorContent = G_ueditorObj.getContent();
  
  title = title.replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26");
  author = author.replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26");
  description = description.replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26");
	if(!wxid){
		MyFun.to.i("请选择公众号");
		nullFlag = 1;
		$(".nav-tabs li:eq(0) a").click();
		return false;
	}
	if(!categoryid){
		MyFun.to.i("请选择分类");
		nullFlag = 1;
		$(".nav-tabs li:eq(0) a").click();
		return false;
	}
	if(!title){
		MyFun.to.i("请输入标题");
		nullFlag = 1;
		$(".nav-tabs li:eq(0) a").click();
		return false;
	}
	if(!picurl){
		MyFun.to.i("请上传图片");
		nullFlag = 1;
		$(".nav-tabs li:eq(0) a").click();
		return false;
	}
	if(!author){
		MyFun.to.i("请输入作者");
		nullFlag = 1;
		$(".nav-tabs li:eq(0) a").click();
		return false;
	}
	if(!description){
		MyFun.to.i("请输入描述");
		nullFlag = 1;
		$(".nav-tabs li:eq(0) a").click();
		return false;
	}
	if(!editorContent || editorContent == '<p class=\"ue_t\">请编辑正文内容</p>'){
		MyFun.to.i("请编辑正文内容");
		nullFlag = 1;
		$(".nav-tabs li:eq(1) a").click();
		return false;
	}
	nullFlag = 0;
	
	if(clickFlag)
		return false;
	clickFlag = 1;
	
	var content = editorContent.replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26");
	var str = 'categoryid='+categoryid+'&wxid='+wxid+'&title='+title+'&author='+author+'&description='+description+'&cover='+picurl+'&content='+content+"&id="+msgId;
	// console.log(str)

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

	$.PostJson("${request.contextPath}/cmscontent/save.do", str, function(state, json){
		if(state == 'success'){
			if(json && json.code == '0000'){
				$('#info-formS').modal('hide');
				MyFun.to.s("操作成功");
				// window.location.reload();

				MyFun.search('J_formSearch','CmsContentList');
			}else{
				MyFun.to.e(json.message || "操作失败");
			}
		}
		clickFlag = 0;
		ztoast.clear();
	})
}

function initData(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var node = treeObj.getNodeByParam("id", articles.categoryid);
	if(node && node.id){
		treeObj.selectNode(node);
		$("#treeMenu").val(node.name);
	}

	$(".title").val(articles.title);
	$(".author").val(articles.author);
	$(".description").val(articles.description);
	$('#awxid').val(articles.wxid);
	$('#categoryid').val(articles.categoryid);

	picurl = articles.cover;
	$("#J_imgPrev").attr({"src": '<@_properties key="img.url">${propertie}</@_properties>'+picurl});
	msgId = articles.id;

	try{
		G_ueditorObj.setContent(articles.content);
	}catch(err){
		G_ueditorObj.setContent("");
		console.log(err);
	}
}
</script>
</body>
</html>