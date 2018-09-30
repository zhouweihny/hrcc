<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title><@_cmscategory id="${categoryid}" >${cmsCategory.name!}</@_cmscategory></title>
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/css/jquery-weui.min.css">
<style>
body.zui {
    background: #fff;
}
.zui .J_content {
    /*padding-top: 30px;*/
}
.zui .weui-media-box_appmsg .weui-media-box__hd {
    width: 100px;
    height: 70px;
    line-height: 70px;
}
.fn-hide {
	display: none;
}
</style>
</head>

<body class="zui">

<div class="J_wrap">

    <div class="weui-pull-to-refresh__layer">
        <div class='weui-pull-to-refresh__arrow'></div>
        <div class='weui-pull-to-refresh__preloader'></div>
        <div class="down">下拉刷新</div>
        <div class="up">释放刷新</div>
        <div class="refresh">正在刷新</div>
    </div>

    <div id="list" class="J_content">
        <div class="weui-panel__bd" id="J_wxcontent">
            
        </div>
    </div>
    
    <div class="weui-loadmore">
        <i class="weui-loading"></i>
        <span class="weui-loadmore__tips">正在加载</span>
    </div>
</div>

<textarea class="fn-hide" id="T_wxcontent">
{{#each this}}
<a href="${request.contextPath}/wx/c/content/{{id}}.do" class="weui-media-box weui-media-box_appmsg item">
    <div class="weui-media-box__hd">
        <img class="weui-media-box__thumb" src="<@_properties key='img.url'>${propertie}</@_properties>{{cover}}" alt="">
    </div>
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">{{title}}</h4>
        <p class="weui-media-box__desc">{{description}}</p>
    </div>
</a>
{{/each}}
</textarea>

<script src="${request.contextPath}/js/plugins/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui.min.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/my.js"></script>
<script>

var loadingMore = false,//加载更多
    currentPage = 1,
    categoryid = "";

$(function(){
    // 会导致下拉刷新跟item链接冲突
    // FastClick.attach(document.body);

    categoryid = window.location.href.slice(1, window.location.href.indexOf('.do')).split("/").pop();

    $(document.body).infinite().on("infinite", function() {
        getCategory();
    });

    $(document.body).pullToRefresh().on("pull-to-refresh", function(e) {
        window.location.reload();
    });

    getCategory();
})

function getCategory(){
    if(loadingMore) return;
    loadingMore = true;
    var dataLen = 0;
    $.PostJson("${request.contextPath}/wx/c/category/"+categoryid+"/"+currentPage+".do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                dataLen = json.data.data.length;
                if(dataLen){
                    $("#J_wxcontent").tempAppend($("#T_wxcontent").val(), json.data.data);
                }
            }
        }

        if($("#J_wxcontent .item").length === json.data.totalRows){
            currentPage = 0;
            loadingMore = true;
            $(".weui-loadmore").remove();
        }else{
            currentPage++;
            loadingMore = false;
        }
    })
}
</script>
</body>
</html>