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
.J_gobackwrap {
    position: relative;
}
.J_gobackwrap a {
    margin: 10px;
    border: 1px solid #ddd;
    width: 100px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    display: block;
}
.J_gobackwrap h4 {
    border: 1px solid #ddd;
    padding: 12px 10px 11px;
    margin: 0;
    position: absolute;
    top: 0;
    left: 109px;
    cursor: default;
}
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
.minPrice {
    color: red!important;
}
</style>

</head>
<body class="gray-bg">

<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="J_gobackwrap">
                    <a href="javascript:;" hidefocus="true" onclick="history.back();">
                    	<i class="fa fa-chevron-left" aria-hidden="true"></i>
                    	<span>返回上一级</span>
                    </a>
                    <h4><@_purchase id=purchaseid>${purchase.name!""}</@_purchase></h4>
                </div>
                <div class="ibox-content">
                    <form class="form-inline row m-b" id="J_formSearch" action="javascript:;">
                    	  <input type="hidden"   name="purchaseid"  value="${purchaseid}" >
                    	  <input type="hidden"   name="storecode" 	value="${storecode}" id="J_storecode" >
                    	  <input type="hidden"   name="storename" 	value="${storename}" id="J_storename" >
                    	    <div class="form-group col-md-3">
                            <label for="J_name">编码：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入编码" name="code">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">品名：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入品名" name="name">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','PurchaseDetailList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div id="PurchaseDetailList" data-url="${request.contextPath}/purchasedetail/table.do">
                    </div>    
               </div>
          </div>
            </div>
        </div>
    </div>
</div>

<div id="info-form" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>厂商报价<span class="J_quick_dura"></span></h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="J_top_tbl" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>厂商</th>
                                    <th>报价（元）</th>
                                    <th>数量</th>
                                    <th style="text-align: right;">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<textarea class="fn-hide" id="T_list">
<tr>
    <td>{{supplier}}</td>
    <td>{{price}}</td>
    <td>
        <div class="input-group spinner J_numspinner">
            <input type="text" class="form-control text-center" value="{{num}}" data-rule="quantity">
            <div class="input-group-addon">
                <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up"></i></a>
                <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-caret-down"></i></a>
            </div>
        </div>
    </td>
    <td style="text-align: right;">
        <a class="J_addCart" href="javascript:;" onclick="addCart(this);">加入购物车</a>
    </td>
</tr>
</textarea>

<script>

var flyingFlag = 0,
    dataObj = {},
    userId = '${Session.USER.id}',
    endOffset = $("#J_shoppingCartIndex", window.parent.document).offset(),
    flyer = $("#J_flyer"),
    cartCookie = null,
    spinnerTimer = null,
    temp_J_addToCart = null;

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

MyFun.ajaxRefreshTable=function(divid,url,callback){
      if(!url)
          url= $('#'+divid).data("url");
      url= MyFun.urlFun(url,"_",new Date().getTime());
      var data = "{";
      var str =url.split("?")[1] ;   
      var strs = str.split("&");   
      for(var i = 0; i < strs.length; i ++) {   
          data += "'"+strs[i].split("=")[0]+"'" + ":"+"'"+strs[i].split("=")[1]+"'"+",";
      }
      data += "}";
      data = eval('('+data+')');
      var htmlobj=$.ajax({url:url.split("?")[0], data:data,dataType:"json",type:'post',async:false});
      $('#'+divid).empty();
      $('#'+divid).append(htmlobj.responseText);    
      $('#'+divid).data("url",url);

      if(typeof callback === 'function')
        callback();

    judgeCart();
};

function refreshPurchaseDetailList(){
   	MyFun.ajaxRefreshTable("PurchaseDetailList");
}

$(function(){
    // $.cookie('cartCookie', []);
    MyFun.search('J_formSearch','PurchaseDetailList');

})

function addCart(obj){

    dataObj.num = $(obj).parent().prev().find(".J_numspinner input").val();
    //cookie处理
    handleCartCookie();
}

function handleCartCookie(){
    var findFlag = 0;

    cartCookie = $.cookie('parent_cartCookie_'+userId);
    if(cartCookie){
        cartCookie = JSON.parse(cartCookie);
        //存在数据
        for(var i=0,len=cartCookie.length; i<len; i++){
            if(cartCookie[i].puchasedetailid == dataObj.puchasedetailid){

                if(cartCookie[i].supplierid == dataObj.supplierid){
                    //存在同一条
                    findFlag = 1;
                    //更新数量
                    cartCookie[i].num = dataObj.num;
                }
            }
        }
    }

    if(!findFlag){
        if(!cartCookie)
            cartCookie = [];
        cartCookie.push(dataObj);
    }

    //设置、更新cookie
    //7天后失效
    // $.cookie('cartCookie', JSON.stringify(cartCookie), { expires: 7 });
    //直接设置在父页面cookie
    parent.setCartCookie(cartCookie, userId);

    $("#info-form").modal('hide');

    if(temp_J_addToCart.find(".J_hasChose")){
        temp_J_addToCart.find(".J_hasChose").html('（已选'+dataObj.num+'）');
    }else{
        temp_J_addToCart.append('<span class="J_hasChose">（已选'+dataObj.num+'）</span>');
    }
}

function addToCart(obj, id){
    $("#info-form").modal('show');
    $('#info-form').off('hidden.bs.modal').off('shown.bs.modal').on('hidden.bs.modal', function (e) {

    }).on('shown.bs.modal', function(e){
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
    })

    var _this = $(obj);
    temp_J_addToCart = _this;
    //清空
    dataObj = {};
    dataObj.drugid = _this.data("drugid")+'';
    dataObj.code = _this.data("code");
    dataObj.name = _this.data("name");
    dataObj.specifications = _this.data("specifications");
    dataObj.unit = _this.data("unit");
    dataObj.dosageform = _this.data("dosageform");
    dataObj.factory = _this.data("factory");
    dataObj.num = _this.data("num") || '1';
    dataObj.puchasedetailid = id;

    dataObj.price = _this.data("price")+'';
    dataObj.supplierid = _this.data("supplierid")+'';
    dataObj.supplier = _this.data("supplier")+'';
    dataObj.storecode = $("#J_storecode").val();
    dataObj.storename = $("#J_storename").val();

    $("#J_top_tbl tbody").temp($("#T_list").val(), dataObj);

    //判断是否已加入购物车
    var temp_cartCookie = $.cookie('parent_cartCookie_'+userId);
    if(temp_cartCookie){
        temp_cartCookie = JSON.parse(temp_cartCookie);

        for(var i=0,len=temp_cartCookie.length; i<len; i++){
            if(temp_cartCookie[i].puchasedetailid == dataObj.puchasedetailid){
                if(temp_cartCookie[i].supplierid == dataObj.supplierid){
                    $("#J_top_tbl .J_addCart").html("修改数量");
                    $("#J_top_tbl .J_numspinner input").val(temp_cartCookie[i].num);
                }
            }
        }
    }
}

function judgeCart(){
    //遍历table判断是否已加入购物车
    var temp_cartCookie = $.cookie('parent_cartCookie_'+userId);
    if(temp_cartCookie){
        temp_cartCookie = JSON.parse(temp_cartCookie);

        if(temp_cartCookie.length){
            $(".J_addToCart").each(function(){
                var _this = $(this),
                    puchasedetailid = _this.data("id"),
                    supplierid = _this.data("supplierid");

                for(var i=0,len=temp_cartCookie.length; i<len; i++){
                    if(temp_cartCookie[i].puchasedetailid == puchasedetailid){
                        if(temp_cartCookie[i].supplierid == supplierid){
                            _this.append('<span class="J_hasChose">（已选'+temp_cartCookie[i].num+'）</span>');
                        }
                    }
                }
            })
        }
    }

    //标红最低价
    $("#PurchaseDetailList tbody tr").each(function(){
        var tempComArr = [],
            obj = $(this).find(".J_addToCart"),
            len = obj.length;
        obj.each(function(i){
            var _this = $(this),
                price = _this.data("price") || 999999999;
            tempComArr.push(price);
            if(i == len-1){
                var _min = Math.min.apply(null, tempComArr);
                for(var j=0, jlen=tempComArr.length; j<jlen; j++){
                    console.log(tempComArr[j])
                    if(tempComArr[j] == _min)
                        obj.eq(j).addClass("minPrice");
                }
            }
        })
    })
}

</script>
</body>
</html>