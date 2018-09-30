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
<script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/handlebars/handlebars.js"></script>
<script src="${request.contextPath}/js/plugins/cookie/1.4.1/jquery.cookie.js"></script>

<style>
.J_numspinner input {
    width: 60px!important;
}
.J_numspinner .input-group-addon {
    position: relative;
    width: 40px;
    right: 1px;
    height: 30px;
    border: 1px solid #cfdadd;
}
.J_numspinner .input-group-addon a {
    position: absolute;
    width: 100%;
    margin: 0;
    left: 0;
}
.J_numspinner .input-group-addon .spin-up {
    top: 0;
}
.J_numspinner .input-group-addon .spin-down {
    bottom: 0;
}
#J_orderlist .J_list_wraper .tit {
    font-size: 14px;
    border: 1px solid #ddd;
    display: inline-block;
    padding: 8px 10px;
    margin: 15px 0 -1px;
    cursor: default;
}
#J_orderlist .table.dataTable {
    margin-top: 0!important;
}
#J_orderlist p {
    cursor: default;
    text-align: center;
    border: 1px solid #ddd;
    padding: 10px;
    margin-top: 15px;
    background: #ddd;
}
#J_orderlist .J_list_wraper {
    position: relative;
}
#J_orderlist .J_list_wraper .J_subOrder {
    position: absolute;
    right: 0;
    top: 22px;
    border-radius: 0;
    margin: 0;
}
.toolbars {
    padding: 11px 10px 5px;
    position: fixed;
    bottom: 51px;
    left: 0;
    width: 100%;
    background: #ddd;
    text-align: right;
    z-index: 999;
}
.J_orderlist {
    margin-bottom: 60px;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>我的购物车</h5>
                </div>
                <div class="ibox-content">
                    <div class="J_orderlist">

                        <div class="toolbars">
                            <button type="button" class="btn btn-w-m btn-default m-r" id="J_clearAll">清空</button>
                            <button type="button" class="btn btn-w-m btn-danger" id="J_subOrder">全部提交</button>
                        </div>

                        <div id="J_orderlist">
                            
                        </div>
                        
                   </div>
               </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_orderlist">
{{#each this}}
<div class="J_list_wraper">
    <div class="tit"><span>{{#if storename}}{{storename}}{{else}}无{{/if}}</span></div>
    <button type="button" class="btn btn-w-m btn-danger m-r J_subOrder">提交</button>
    <table class="table table-striped table-bordered table-hover dataTables-example dataTable" aria-describedby="DataTables_Table_0_info">
        <thead>
            <tr role="row">
                <th>编码</th>
                <th>名称</th>
                <th>规格</th>
                <th>单位</th>
                <th>剂型</th>
                <th>厂商</th>
                <th>数量</th>
                <th>供应商</th>
                <th style="text-align: right;">操作</th>
            </tr>
        </thead>
        <tbody>
            {{#each data}}
            <tr data-json='{{retJson2Str this}}' class="J_datalistTr">
                <td>{{code}}</td>
                <td>{{name}}</td>
                <td>{{specifications}}</td>
                <td>{{unit}}</td>
                <td>{{dosageform}}</td>
                <td>{{factory}}</td>
                <td>
                    <div class="input-group spinner J_numspinner">
                        <input type="text" class="form-control text-center" value="{{num}}" data-rule="quantity">
                        <div class="input-group-addon">
                            <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up"></i></a>
                            <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-caret-down"></i></a>
                        </div>
                    </div>
                </td>
                <td>{{supplier}}</td>
                <td style="text-align: right;"><a class="J_del" href="javascript:;">删除</a></td>
            </tr>
            {{/each}}
        </tbody>
    </table>
</div>
{{/each}}
</textarea>

<script>

var cartCookie = [],
    userId = '${Session.USER.id}',
    clickFlag = 0;

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
    refreshzPage();
})

function subOrder(){
    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock(".J_orderlist", '正在努力加载数据...');

    cartCookie = [];
    $("#J_orderlist tbody tr").each(function(){
        var _this = $(this),
            _json = _this.data("json");
        _json.num = _this.find(".J_numspinner input").val();
        cartCookie.push(_json);
    })

    $.ajax({ 
        type:"POST", 
        url:"${request.contextPath}/billdetail/savebill.do", 
        dataType:"json",      
        contentType:"application/json",               
        data:JSON.stringify(cartCookie),
        success:function(json){ 
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "提交订单成功");
                
                cartCookie = [];
                parent.setCartCookie(cartCookie, userId);
                $("#J_orderlist").html('<p>暂无数据</p>');
            }else{
                MyFun.to.e(json.message || "提交订单失败");
            }
        },
        complete:function(XMLHttpRequest,status){ 
            clickFlag = 0;
            unBlock(".J_orderlist");
        }
    }); 
}

//json转换str
Handlebars.registerHelper('retJson2Str', function(json, options) {
    return JSON.stringify(json);
});

function refreshzPage(){
    cartCookie = $.cookie('parent_cartCookie_'+userId);
    if(cartCookie){
        cartCookie = JSON.parse(cartCookie);

        // console.log(JSON.stringify(cartCookie))

        if(cartCookie.length){

            //拆分药店
            var temp_json = splitStores(cartCookie);

            $("#J_orderlist").temp($("#T_orderlist").val(), temp_json);

            $(".J_numspinner").each(function(){
                var _J_numspinner = $(this),
                    _input = _J_numspinner.find("input"),
                    _up = _J_numspinner.find(".spin-up"),
                    _down = _J_numspinner.find(".spin-down");
                _up.off("click").on("click", function(){
                    _input.val(parseInt(_input.val(), 10)+1);
                })
                _down.off("click").on("click", function(){
                    var _val = parseInt(_input.val(), 10)-1;
                    _input.val(_val<1 ? 1 : _val);
                })
                _input.off("keyup").on("keyup", function(){
                    var newVal = _input.val().replace(/[^\d]/g,'');
                    _input.val(parseInt(newVal, 10));
                }).blur(function(){
                    _input.val(parseInt($(this).val(), 10) || 1);
                })
            })

            $("#J_orderlist").off("click").on("click", ".J_del", function(){
                var _this = $(this);
                layer.confirm("是否确定删除该条商品？", {
                    btn: ['确定', '取消'] //按钮
                }, function() {
                    layer.closeAll('dialog');
                    var _parent = _this.parent().parent(),
                        _index = $(".J_del").index(_this),
                        data = _parent.data("json");
                    //删除该条
                    cartCookie = NiceTools.removeByIndex(cartCookie, _index);
                    parent.setCartCookie(cartCookie, userId);
                    _parent.remove();
                });
            }).on("click", ".J_subOrder", function(){
                var _this = $(this),
                    _parent = _this.parent(),
                    tempArr = [],
                    arrIndex = [];

                _parent.find("tbody tr").each(function(){
                    var _self = $(this),
                        _index = $(".J_datalistTr").index(_self),
                        _json = _self.data("json");
                    _json.num = _self.find(".J_numspinner input").val();
                    arrIndex.push(_index);
                    tempArr.push(_json);
                })
                subOrderS(_parent, arrIndex, tempArr);
            })
        }else{
            $("#J_orderlist").html('<p>暂无数据</p>');
        }
    }else{
        $("#J_orderlist").html('<p>暂无数据</p>');
    }

    $("#J_subOrder").off("click").on("click", function(){
        if(cartCookie && cartCookie.length){
            layer.confirm("是否确定全部提交？", {
                btn: ['确定', '取消'] //按钮
            }, function() {
                layer.closeAll('dialog');
                subOrder();
            });
        }else{
            MyFun.to.i("请挑选药品");
        }
    })

    $("#J_clearAll").off("click").on("click", function(){
        layer.confirm("是否确认清空购物车？", {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            cartCookie = [];
            parent.setCartCookie(cartCookie, userId);
            $("#J_orderlist").html('<p>暂无数据</p>');
        }, function(){
            
        });
    })
}

function splitStores(oldArr){
    var newArr = [],
        tempAttr = {};

    for(var i=0,len=oldArr.length; i<len; i++){
        var ai = oldArr[i];
        if(!tempAttr[ai.storecode]){
            newArr.push({
                storecode: ai.storecode,
                storename: ai.storename,
                data: [ai]
            });
            tempAttr[ai.storecode] = ai;
        }else{
            for(var j = 0; j < newArr.length; j++){
                var dj = newArr[j];
                if(dj.storecode == ai.storecode){
                    dj.data.push(ai);
                    break;
                }
            }
        }
    }
     
    return newArr;
}

function subOrderS(obj, arrIndex, tempArr){
    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock(".J_orderlist", '正在努力加载数据...');

    $.ajax({ 
        type:"POST", 
        url:"${request.contextPath}/billdetail/savebill.do", 
        dataType:"json",      
        contentType:"application/json",               
        data:JSON.stringify(tempArr),
        success:function(json){ 
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "提交订单成功");
                
                for(var i=0,len=arrIndex.length; i<len; i++){
                    if(i !== 0)
                        arrIndex[i]--;
                    cartCookie = NiceTools.removeByIndex(cartCookie, arrIndex[i]);
                }

                parent.setCartCookie(cartCookie, userId);
                obj.remove();
            }else{
                MyFun.to.e(json.message || "提交订单失败");
            }
        },
        complete:function(XMLHttpRequest,status){ 
            clickFlag = 0;
            unBlock(".J_orderlist");
        }
    });
}
</script>
</body>
</html>