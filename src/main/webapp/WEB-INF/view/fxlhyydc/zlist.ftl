<#include "../tools/select.ftl"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> - 数据表格</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
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
<script src="${request.contextPath}/js/plugins/select2/js/select2.js?v=1"></script>
<script src="${request.contextPath}/js/plugins/select2/js/i18n/zh-CN.js"></script>
<link href="${request.contextPath}/js/plugins/select2/css/select2.min.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<link href="${request.contextPath}/js/plugins/ztree/css/metroStyle/metroStyle.css?v=_1" rel="stylesheet">
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.core.min.js"></script>
<script src="${request.contextPath}/js/plugins/ztree/js/jquery.ztree.exedit.js"></script>
<link href="${request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet"> 
<script src="${request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>

<style>
#supplierid {
    display: inline-block;
    width: 80%;
}
.select2-container {
    margin-right: 10px;
}
@media (min-width: 768px){
    .modal-dialog {
        width: 800px;
    }
}
.J_searchWrap {
    padding: 30px 10px 10px;
    border: 1px solid #ddd;
}
.J_searchWrap .ibox-content {
    padding: 0!important;
    border: 1px solid #ddd;
    margin: 10px;
}
.J_desWrap {
    border: 1px solid #ddd;
    margin-top: 50px;
    position: relative;
    margin-bottom: 20px;
}
.J_desWrap .tit, .J_itemsWrap .tit {
    position: absolute;
    top: -35px;
    left: -1px;
    width: 70px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    border: 1px solid #ddd;
    cursor: default;
}
.J_desWrap .tit {
    left: auto;
    right: -1px;
}
.J_desWrap ul {
    list-style: none;
    padding: 10px 10px 0;
    overflow-y: auto;
}
.J_desWrap ul li {
    float: left;
    margin-right: 10px;
    padding: 6px;
    border: 1px solid #ddd;
    margin-bottom: 10px;
    border-radius: 5px;
    cursor: pointer;
    background: #ddd;
    height: 32px;
}
.J_desWrap ul li.act {
    background: #027194;
    border-color: #027194;
    color: #fff;
}
.J_desWrap ul li.act .J_del {
    display: inline-block;
}
.J_desWrap .J_del {
    display: none;
    width: 30px;
    text-align: center;
}
.J_desWrap .J_del .fa {
    font-size: 16px;
    color: #7d7272;
}
.J_desWrap ul li.act .fa {
    color: #fff;
}
.J_subWrap {
    margin-top: 15px;
    text-align: center;
}
.J_treeWrap {
    margin-top: 20px;
    position: relative;
}
#zuiTree {
    border: 1px solid #ddd;
    width: 100%;
    height: 400px;
    overflow: auto;
    margin-top: 5px;
}
#J_name {
    width: 60%;
    display: inline-block;
    margin-top: 5px;
}
.J_conWrap {

}
.J_itemsWrap {
    margin-top: 50px;
    border: 1px solid #ddd;
    position: relative;
    padding: 10px;
}
.J_itemsWrap ul {
    list-style: none;
    margin: 0;
    padding: 0;
    overflow-y: auto;
}
.J_itemsWrap .J_items {
    margin-bottom: 10px;
    border: 1px solid #ddd;
    padding: 10px 6px;
    position: relative;
}
.J_itemsWrap .J_items .J_del_items {
    position: absolute;
    right: -1px;
    top: -1px;
    width: 25px;
    height: 25px;
    line-height: 25px;
    text-align: center;
    border: 1px solid #ddd;
    cursor: pointer;
}
.J_itemsWrap .J_items .J_item {
    display: block;
    margin: 5px 0;
    cursor: default;
}
.J_itemsWrap .J_items .J_item .J_del_item {
    cursor: pointer;
}
#J_randomBtn {
    display: block;
    margin-top: 50px;
    cursor: default;
    text-align: center;
}
#J_randomBtn .fa-random {
    display: inline-block;
    font-size: 30px;
    cursor: pointer;
    height: 40px;
    line-height: 40px;
    width: 40px;
}
.checkbox label {
    padding-left: 0;
    font-weight: 700;
}
.icheckbox_square-green {
    margin-left: 4px;
}
</style>

</head>
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>联合用药生成</h5>
                </div>
                <div class="J_searchWrap">
                    <div class="ibox-content row">
                        <div class="form-group col-md-9">
                            <label for="supplierid">通用名：</label>
                            <select id="supplierid" name="supplierid" class="form-control m-r"></select>
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="addGroup();"><i class="fa fa-plus"></i>添加分组</button>
                        </div>
                    </div>
                </div>

                <div class="J_conWrap J_step1">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="J_itemsWrap">
                                <p class="tit">组类</p>
                                <ul id="J_itemsWrap">
                                    
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-1">
                            <a href="javascript:;" id="J_randomBtn" title="生成预览">
                                <i class="fa fa-random" aria-hidden="true" onclick="showPreviw();"></i>
                            </a>
                        </div>
                        <div class="col-md-7">
                            <div class="J_desWrap">
                                <p class="tit">预览</p>
                                <ul class="fn-clear" id="J_desWrap">
                                    
                                </ul>
                            </div>

                            <div>
                                <button type="button" class="btn btn-w-m btn-default m-r zall" onclick="checkAll(this);">全选/全不选</button>
                                <button type="button" class="btn btn-w-m btn-info" onclick="nextStep();">下一步</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="J_step2 fn-hide">
                    <div class="J_treeWrap">
                        <div class="row">
                            <div class="col-md-4">
                                <ul id="zuiTree" class="ztree"></ul>
                            </div>
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="J_name">联合用药名称：</label>
                                        <input type="text" class="form-control" id="J_name" placeholder="输入联合用药名称" name="name">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="checkbox i-checks">
                                            <label>生成后清空组：<input id="J_clearItems" type="checkbox" name="zuiFilter" /></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="J_subWrap">
                        <div class="fn-center">
                            <button type="button" class="btn btn-w-m btn-default m-r" onclick="backPrev();">返 回</button>
                            <button type="button" class="btn btn-w-m btn-danger" onclick="subDatas();">提 交</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_top_tpl">
{{#each this}}
<li class="act" data-ids="{{ids}}">
    {{names}}
    <a href="javascript:;" class="J_del"><i class="fa fa-check" aria-hidden="true"></i></a>
</li>
{{/each}}
</textarea>

<textarea class="fn-hide" id="T_items_tpl">
<li class="J_items">
    <i class="fa fa-close J_del_items" aria-hidden="true" title="删除分组"></i>
    {{#each this}}
    <p class="J_item" data-id="{{id}}" data-name="{{name}}">
        <i class="fa fa-close J_del_item" aria-hidden="true" title="删除条目"></i>
        <span>{{name}}</span>
    </p>
    {{/each}}
</li>
</textarea>

<script>
var clickFlag = 0,
    zuiTree = null,
    curTreeId = '',
    G_temp = [],
    G_tempS = [],
    _supplieridObj = null;

var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false
    },
    edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false,
        editNameSelectAll: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeDrag: beforeDrag,
        onRename: zTreeOnRename,
        onClick: zTreeClick
    }
};

Array.prototype.unique = function() {
    // n为hash表，r为临时数组
    var n = {}, r = [];
    for (var i = 0; i < this.length; i++) {
        // 如果hash表中没有当前项
        if (!n[this[i]]) {
            // 存入hash表
            n[this[i]] = true;
            // 把当前数组的当前项push到临时数组里面
            r.push(this[i]); 
        }
    }
    return r;
}
var NiceTools = {
    /*
     * 功能:删除数组元素.
     * 参数:dx删除元素的下标.
     * 返回:在原数组上删除后的数组
     */
    removeByIndex : function(arrays , dx){
        if(isNaN(dx)||dx>arrays.length){return false;}
        for(var i=0,n=0;i<arrays.length;i++)
        {
            if(arrays[i]!=arrays[dx])
            {
                arrays[n++]=arrays[i]
            }
        }
        arrays.length-=1
        return arrays;
    },
    //删除指定的item,根据数组中的值
    removeByValue : function(arrays, item ){
        for( var i = 0 ; i < arrays.length ; i++ ){
            if( item == arrays[i] )
            {
                break;
            }
        }
        if( i == arrays.length ){
            return;
        }
        for( var j = i ; j < arrays.length - 1 ; j++ ){
            arrays[ j ] = arrays[ j + 1 ];
        }
        arrays.length--;
        return arrays;
    }
}

$(function(){
    _supplieridObj = $('#supplierid');

    _supplieridObj.select2({
        ajax: {
            url: "${request.contextPath}/fxcomname/fxcomnames.do",
            type : "POST",
            dataType: 'json',
            delay: 250,
            data: function(params) {
                return {
                    name: params.term, // search term
                    currentPage: params.page || 1,
                    pageSize: 10
                };
            },
            processResults: function(data, params) {
                params.page = params.page || 1;
                var _cdata = data.data,
                    _cArr = [];
                if(_cdata.length){
                    for(var i=0, len=_cdata.length; i<len; i++){
                        var attr = {};
                        attr.id = _cdata[i].id;
                        attr.text = _cdata[i].name;
                        if(G_tempS && G_tempS.length){
                            for(var k=0,klen=G_tempS.length; k<klen; k++){
                                if(_cdata[i].id == G_tempS[k]){
                                    attr.disabled = true;
                                }
                            }
                        }
                        _cArr.push(attr);
                    }
                }
                return {
                    results: _cArr,
                    pagination: {
                        more: (params.page * 10) < data.totalRows
                    }
                };
            },
            cache: false
        },
        escapeMarkup: function(markup) {
            return markup;
        }, // let our custom formatter work
        minimumInputLength: 0,
        placeholder: "请输入通用名",
        allowClear: false,
        language: "zh-CN",
        dropdownAutoWidth: true,
        multiple: true,
        closeOnSelect: false,
        maximumSelectionLength : 10
    })

    _supplieridObj.on("select2:select", function(e){
        var _val = $(this).val();
        for(var i=0, len=_val.length; i<len; i++){
            G_tempS.push(_val[i]);
        }
        G_tempS = G_tempS.unique();
    })

    _supplieridObj.on("select2:unselect", function(e){
        var removeData = e.params.data;
        // 移除
        G_tempS = NiceTools.removeByValue(G_tempS, removeData.id);

        var _val = $(this).val();
        if(!_val){
            return false;
        }
        for(var i=0, len=_val.length; i<len; i++){
            G_tempS.push(_val[i]);
        }
        G_tempS = G_tempS.unique();
    })

    $(".J_desWrap").off("click").on("click", "li", function(){
        var _this = $(this);
        _this.toggleClass("act");
    })

    var _h = $(window).height() - 276;
    $("#zuiTree, #J_itemsWrap, #J_randomBtn").css({"height": _h});
    $("#J_desWrap").css({"height": _h - 43});
    $("#J_randomBtn").css({"line-height": _h+"px"});
    getzTree();

    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green'
    });

})

function addGroup(){
    $(".J_step1").removeClass("fn-hide");
    $(".J_step2").addClass("fn-hide");

    var arr = _supplieridObj.select2('data'),
        carr = [];
    for(var i=0,len=arr.length; i<len; i++){
        var attr = {
            id: arr[i].id,
            name: arr[i].text
        }
        carr.push(attr);
    }
    if(!carr.length){
        MyFun.to.i("请至少选择一个通用名");
        return false;
    }

    var _J_itemsWrap = $("#J_itemsWrap");
    _J_itemsWrap.tempAppend($("#T_items_tpl").val(), carr);
    _supplieridObj.val(null).trigger("change");

    _J_itemsWrap.off("click").on("click", ".J_del_items", function(){
        var _this = $(this),
            _parent = _this.parent();

        _parent.find(".J_item").each(function(){
            var _id = $(this).data("id");
            G_tempS = NiceTools.removeByValue(G_tempS, _id);
        })
        _parent.remove();
    }).on("click", ".J_del_item", function(){
        var _this = $(this),
            _parent = _this.parent(),
            _parentS = _parent.parent();
        var _id = _parent.data("id");
        G_tempS = NiceTools.removeByValue(G_tempS, _id);
        if(_parentS.find(".J_item").length == 1)
            _parentS.remove();
        _parent.remove();
    })
}

function showPreviw(){
    /*$(".J_step1, .J_desWrap").removeClass("fn-hide");
    $(".J_step2, .J_treeWrap").addClass("fn-hide");

    */
    G_temp = [];

    var _J_itemsWrap = $("#J_itemsWrap"),
        cJson = [];
    if(_J_itemsWrap.find(".J_items").length <= 1){
        return false;
    }
    _J_itemsWrap.find(".J_items").each(function(){
        var arr = [];
        $(this).find(".J_item").each(function(){
            var _self = $(this),
                attr = {
                id: _self.data("id"),
                name: _self.data("name")
            };
            arr.push(attr);
        })
        cJson.push(arr);
    })

    // console.log(JSON.stringify(cJson))
    // cJson = [[{"id": "00361BF126BB4BE2B332477D97EA7D60", "name": "吲哚美辛贴片"} ], [{"id": "0040C75DEBCE4C749D47F5EC7C3AE339", "name": "中型近端板-胫骨近端外侧III型"}, {"id": "0041BF33FACB468D8986485711E8880D", "name": "麝香祛痛搽剂"} ], [{"id": "00495E7390FC405B948503A8455256F1", "name": "贝那鲁肽注射液"}, {"id": "004B47BF26B341778A0399608C79928B", "name": "玄明粉"} ] ];
    
    var dJson = [];
    /*for(var i=0,len=cJson.length; i<len; i++){
        var newJson = DescartesUtils.descartes(cJson);
        console.log(JSON.stringify(newJson))
        dJson = dJson.concat(newJson);
        cJson = cJson.splice(1);
        if(cJson.length == 1)
            cJson.length = 0;
    }*/
    dJson = DescartesUtils.descartes(cJson);
    // console.log(JSON.stringify(dJson))

    for(var k=0,klen=dJson.length; k<klen; k++){
        var _ids = [],
            _names = [];
        for(var m=0,mlen=dJson[k].length; m<mlen; m++){
            _ids.push(dJson[k][m].id);
            _names.push(dJson[k][m].name);
        }
        var cattr = {
            ids: _ids.join(','),
            names: _names.join('，')
        }
        G_temp.push(cattr);
    }
    // console.log(JSON.stringify(G_temp))

    $("#J_desWrap").temp($("#T_top_tpl").val(), G_temp);
}

/**
 * Created by Hawk on 2016/6/18.
 */
var DescartesUtils = {

    /**
     * 如果传入的参数只有一个数组，求笛卡尔积结果
     * @param arr1 一维数组
     * @returns {Array}
     */
    descartes1:function(arr1){
        // 返回结果，是一个二维数组
        var result = [];
        var i = 0;
        for (i = 0; i < arr1.length; i++) {
            var item1 = arr1[i];
            result.push([item1]);
        }
        return result;
    },

    /**
     * 如果传入的参数只有两个数组，求笛卡尔积结果
     * @param arr1 一维数组
     * @param arr2 一维数组
     * @returns {Array}
     */
    descartes2: function(arr1, arr2) {
        // 返回结果，是一个二维数组
        var result = [];
        var i = 0, j = 0;
        for (i = 0; i < arr1.length; i++) {
            var item1 = arr1[i];
            for (j = 0; j < arr2.length; j++) {
                var item2 = arr2[j];
                result.push([item1, item2]);
            }
        }
        return result;
    },

    /**
     *
     * @param arr2D 二维数组
     * @param arr1D 一维数组
     * @returns {Array}
     */
    descartes2DAnd1D: function(arr2D, arr1D) {
        var i = 0, j = 0;
        // 返回结果，是一个二维数组
        var result = [];

        for (i = 0; i < arr2D.length; i++) {
            var arrOf2D = arr2D[i];
            for (j = 0; j < arr1D.length; j++) {
                var item1D = arr1D[j];
                result.push(arrOf2D.concat(item1D));
            }
        }

        return result;
    },

    descartes3: function(list) {
        var listLength = list.length;
        var i = 0, j = 0;
        // 返回结果，是一个二维数组
        var result = [];
        // 为了便于观察，采用这种顺序
        var arr2D = DescartesUtils.descartes2(list[0], list[1]);
        for (i = 2; i < listLength; i++) {
            var arrOfList = list[i];
            arr2D = DescartesUtils.descartes2DAnd1D(arr2D, arrOfList);
        }
        return arr2D;
    },

    //笛卡儿积组合
    descartes: function(list)
    {
        if (!list) {
            return [];
        }
        if (list.length <= 0) {
            return [];
        }
        if (list.length == 1) {
            return DescartesUtils.descartes1(list[0]);
        }
        if (list.length == 2) {
            return DescartesUtils.descartes2(list[0], list[1]);
        }
        if (list.length >= 3) {
            return DescartesUtils.descartes3(list);
        }
    }
};


function checkAll(obj){
    var _this = $(obj),
        _J_desWrap = $("#J_desWrap");
    if(_this.hasClass("zall")){
        _J_desWrap.find("li").removeClass("act");
        _this.removeClass("zall");
    }else{
        _J_desWrap.find("li").addClass("act");
        _this.addClass("zall");
    }
}

function subDatas(){
    var name = $("#J_name").val().replace(/\%/g, "%25").replace(/\+/g, "%2B").replace(/\&/g, "%26");
    if(!name){
        MyFun.to.i("请输入联合用药名称");
        return false;
    }
    if(!curTreeId){
        MyFun.to.i("请选择第三级节点");
        return false;
    }

    var _J_desWrap = $("#J_desWrap");
    var idsArr = [];
    _J_desWrap.find("li.act").each(function(){
        var _this = $(this),
            ids = _this.data("ids");
        idsArr.push(ids);
    })
    if(!idsArr.length){
        MyFun.to.i("请至少选择一个");
        return false;
    }

    var jsonstr = {
        name: name,
        treeid: curTreeId,
        comnames: idsArr
    }

    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxlhyydc/savelhyy.do", "jsonstr="+JSON.stringify(jsonstr), function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "生成成功");
                
                var zflag = $("#J_clearItems").is(":checked");
                if(zflag){
                    // 清空组类
                    _J_desWrap.html("");
                    $("#J_itemsWrap").html("");
                    $("#J_name").val("");
                    G_temp = [];
                    G_tempS = [];
                }

                $(".J_step1").removeClass("fn-hide");
                $(".J_step2").addClass("fn-hide");
            }else{
                MyFun.to.e(json.message || "生成失败");
            }
        }
        clickFlag = 0;
        unBlock(".wrapper");
    })
}

function nextStep(){
    if(!$("#J_desWrap").find("li.act").length){
        MyFun.to.i("请至少选择一个");
        return false;
    }
    $(".J_step2").removeClass("fn-hide");
    $(".J_step1").addClass("fn-hide");

    setTimeout(function(){
        $("#J_name").focus();
    }, 50)
}

function backPrev(){
    $(".J_step1").removeClass("fn-hide");
    $(".J_step2").addClass("fn-hide");
}

function getzTree(){
    createBlock(".wrapper", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/fxtree/analysistree.do", "", function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                var arr = [];
                if(json.code == '0000'){
                    for (var i=0, l=json.data.length; i<l; i++) {
                        var attr = {};
                        attr.id = json.data[i].id;
                        attr.pId = json.data[i].parentid;
                        attr.name = json.data[i].name;
                        arr.push(attr);
                    }
                }

                $.fn.zTree.init($("#zuiTree"), setting, arr);
                zuiTree = $.fn.zTree.getZTreeObj("zuiTree");

                var zuiNodes = zuiTree.transformToArray(zuiTree.getNodes());
                var node = zuiNodes[0];
                // curTreeId = node.id;
                zuiTree.expandNode(node);

            }else{
                MyFun.to.e(json.message || "查询病种分类树失败");
            }
        }
        unBlock(".wrapper");
    })
}

function treeDatafilter(treeId, parentNode, json) {
    if (!json) return null;
    var arr = [];
    if(json.code == '0000'){
        for (var i=0, l=json.data.length; i<l; i++) {
            var attr = {};
            attr.id = json.data[i].id;
            attr.pId = json.data[i].parentid;
            attr.name = json.data[i].name;
            arr.push(attr);
        }

    }
    return arr;
}

function zTreeClick(event, treeId, treeNode) {
    if(treeNode.level == 2){
        curTreeId = treeNode.id;
    }else{
        MyFun.to.i("请选择第三级节点");
    }
}

function beforeDrag(treeId, treeNodes) {
    return false;
}

function addHoverDom(treeId, treeNode) {
    return false;
}

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
    $("#bindBtn_"+treeNode.tId).unbind().remove();
}

function zTreeOnRename(event, treeId, treeNode, isCancel){
    if(treeNode.name.indexOf("new node") !== -1 || !treeNode.name){
        zuiTree.removeNode(treeNode);
    }else{
        zuiAddNode(treeNode.pId, treeNode.name);
    }
}

</script>
</body>
</html>