<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>联合用药</title>
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/css/jquery-weui.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/swiper/swiper.min.css">
<link rel="stylesheet" href="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.css?v=201511171953">
<style>
body.zui {
    background: #fff;
}
.J_content {
    overflow-y: auto;
}
.J_content .tit {
    text-align: center;
    font-size: .8rem;
    color: #4e809a;
    font-weight: 400;
    margin: 0 15%;
    padding: 10px 0;
}
.swiper-container {
    width: 100%;
    margin: 0 auto;
}
.swiper-slide {
    text-align: center;
    font-size: 18px;
    background: #fff;
}
.J_content .J_section {
    padding: 10px 0;
    width: 100%;
    background: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
}
.J_content .J_section:nth-child(even) {
    background: #F6F7F9;
}
.J_content .des {
    width: 100%;
    font-size: .6rem;
    color: #607D8F;
    padding: 0 .8rem;
}
.J_content .des p {
    font-size: .53rem;
    margin-top: 6px;
    text-align: left;
    text-indent: 1rem;
}
.J_content .zbgwrap {
    width: 40%;
}
.J_content .zbgwrap img {
    width: 100%;
    height: 100%;
}
.J_content .tips {
    background: #2D9569;
    padding: 25px 0;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
}
.J_content .tips h3 {
    width: 20%;
    text-align: center;
}
</style>
</head>
<body class="zui">

<header class="J_header">
    <a class="J_header_back customized" href="javascript:;" id="J_closeZuiSubPage"><i class="fa fa-angle-left"></i></a>
    <h1 class="J_header-title">联合用药</h1>
</header>

<div class="J_content">
    <!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper" id="J_swiperWrap">
            
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_swiperWrap">
{{#each this}}
<div class="swiper-slide">
    {{#each list}}
    <section class="J_section">
        <div class="des">
            <h3>【{{name}}】</h3>
            <p>{{text}}</p>
        </div>
    </section>
    {{/each}}
</div>
{{/each}}
</textarea>

<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/lib/jquery-2.1.4.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/jquery-weui/js/jquery-weui.min.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/js/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/swiper/swiper.min.js"></script>
<script src="${request.contextPath}/js/plugins/jquery-weui/wxzui_common.js?v=201802121203"></script>
<script>

var zswiper = null,
    /*lhyydatas = localStorage.getItem("lhyydatas"),// 全部数据
    lhyyindex = localStorage.getItem("lhyyindex");// 当前点击索引*/

var lhyydatas = [{"tit": "风寒感冒", "list": [{"id": "123", "name": "常识判断", "text": "其起因多是劳累，没休息好，再加上吹风或受凉，导致身体免疫能下降，鼻咽部病毒大量繁殖容易并发细菌感染而发病。"}, {"id": "123", "name": "发病季节", "text": "风寒感冒通常秋冬季节发生较多。"}, {"id": "123", "name": "症状", "text": "恶寒重、发热轻、无汗、头痛身痛、鼻塞、流清涕、咳嗽吐稀白痰、口不渴或渴喜热饮、苔薄白。"}, {"id": "123", "name": "用药原则", "text": "辛温解表（多发汗）为主。"}, {"id": "123", "name": "一般用药", "text": "流感丸、伤风停片、感冒清热软胶囊、感冒解毒颗粒、荆防颗粒、风寒感冒颗粒、九味羌活丸、感冒软胶囊、四季感冒胶囊、氨咖黄敏胶囊、病毒"}, {"id": "123", "name": "联合用药", "text": "中药感冒药物+抗炎药+抗病毒西药 灵等。"}, {"id": "123", "name": "建议", "text": "喝姜糖水、姜粥等，可用热水泡脚，最好加点酒，需要出汗。+抗炎药+抗病毒西药 灵等。"} ] }, {"tit": "风寒感冒2", "list": [{"id": "123", "name": "常识判断", "text": "其起因多是劳累，没休息好，再加上吹风或受凉，导致身体免疫能下降，鼻咽部病毒大量繁殖容易并发细菌感染而发病。"}, {"id": "123", "name": "发病季节", "text": "风寒感冒通常秋冬季节发生较多。"} ] }, {"tit": "风寒感冒3", "list": [{"id": "123", "name": "常识判断", "text": "其起因多是劳累，没休息好，再加上吹风或受凉，导致身体免疫能下降，鼻咽部病毒大量繁殖容易并发细菌感染而发病。"}, {"id": "123", "name": "发病季节", "text": "风寒感冒通常秋冬季节发生较多。"} ] }, {"tit": "风寒感冒4", "list": [{"id": "123", "name": "常识判断", "text": "其起因多是劳累，没休息好，再加上吹风或受凉，导致身体免疫能下降，鼻咽部病毒大量繁殖容易并发细菌感染而发病。"}, {"id": "123", "name": "发病季节", "text": "风寒感冒通常秋冬季节发生较多。"} ] } ];
var lhyyindex = 0;

$(function(){

    // if(lhyydatas)
    //     lhyydatas = JSON.parse(lhyydatas);
    $(".J_header-title").text(lhyydatas[lhyyindex].tit);
    $("#J_swiperWrap").temp($("#T_swiperWrap").val(), lhyydatas);

    var _h = $(window).height(),
        _h1 = $(".J_header").height(),
        _h2 = _h - _h1 - 12;
    $(".swiper-slide").css({"height": _h2, "overflow": "auto"});
    
    zswiper = new Swiper('.swiper-container', {
        initialSlide: lhyyindex,
        onSlideChangeStart: function(swiper){
            $(".J_header-title").text(lhyydatas[swiper.activeIndex].tit);
        }
    });

})

</script>
</body>
</html>