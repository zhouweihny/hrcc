<div class="modal-dialog">
    <div class="modal-content">
        <form class="form-horizontal J_judgeChage" id="save_form">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>绑定目录</h5>
                    <div class="ibox-tools">
                        <a class="close-link" data-dismiss="modal">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <dl class="zui_item fn-clear" id="J_catalogItems">
                    </dl>
                </div>
            </div>
        </form>
        <div class="modal-footer">
            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary" style="margin-left: 20px;width: 100px;" id="J_saveBtn">提交</button>
        </div>
    </div>
</div>
<textarea class="fn-hide" id="T_catalogItems">
{{#each this}}
<dd class="item {{retNewCheck checked standard}}" data-id="{{catalogid}}">
    <h6 class="fn-ellip-default two" title="{{name}}">{{name}}</h6>
    <button type="button" class="btn btn-w-m btn-default J_setDefaultBtn">设为参考目录</button>
    <i class="fa fa-square-o" aria-hidden="true"></i>
    <i class="fa fa-check-square-o" aria-hidden="true"></i>
</dd>
{{/each}}
</textarea>

<script>

var spaceid = '${RequestParameters["id"]}',//工作区间id
    catalogids = [],//公有目录 逗号分隔
    standardid = '',//参照目录
    clickFlag = 0,
    hasChooseStand = 0;//是否已选择了参照目录

$(function(){
    initPage();

    $("#J_saveBtn").on("click", function(){
        if(!standardid){
            MyFun.to.e("请选择一个参照目录");
            return false;
        }

        layer.confirm('一旦选择了参照目录，以后将无法修改！', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            if(clickFlag)
                return false;
            clickFlag = 1;
            $("#J_catalogItems .item").each(function(){
                var _this = $(this);
                if(_this.hasClass("active")){
                 var _id = _this.data("id");
                 catalogids.push(_id);   
                }
            })

            $.PostJson("${request.contextPath}/spacecatalog/updatecatalogs.do", "standardid="+standardid+"&spaceid="+spaceid+"&catalogids="+catalogids.join(","), function(state, json){
                if(state == 'success'){
                    if(json && json.code == '0000'){
                        $('#info-formS').modal('hide');
                        MyFun.to.s("操作成功");
                    }else{
                        MyFun.to.e(json.message || "操作失败");
                    }
                }
                clickFlag = 0;
            })
        } );
    })
})

function initPage(){
    //查询工作空间目录
    $.PostJson("${request.contextPath}/spacecatalog/catalogs.do", "spaceid="+spaceid, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                $("#J_catalogItems").temp($("#T_catalogItems").val(), json.data);

                if($("#J_catalogItems .item.stand").length){
                    hasChooseStand = 1;
                    standardid = $("#J_catalogItems .item.stand").data("id");
                    $(".J_setDefaultBtn").remove();
                    $(".zui_item .item").css({"height": "60px"});
                }else{
                    /*$("#J_catalogItems .item").hover(function(){
                        $(this).find(".J_setDefaultBtn").removeClass("fn-hide");
                    }, function(){
                        $(this).find(".J_setDefaultBtn").addClass("fn-hide");
                    })*/
                }

                $("#J_catalogItems").on("click", ".item", function(e){
                    stopEvent(e);
                    var _this = $(this);
                    _this.toggleClass("active");
                }).on("click", ".J_setDefaultBtn", function(e){
                    stopEvent(e);
                    var _this = $(this),
                        _parent = _this.parent(".item");
                    if(_parent.hasClass("stand")){
                        _parent.removeClass("stand");
                        standardid = null;
                    }else{
                        _parent.siblings().removeClass("stand").end().addClass("stand");
                        standardid = _parent.data("id");
                    }
                })
            }else{
                MyFun.to.e(json.message || "查询工作区间目录失败");
            }
        }
    })
}

Handlebars.registerHelper('retNewCheck', function(checked, stand){
    var buffer = "";
    if(stand)
        buffer = 'stand';
    else if(checked)
        buffer = 'active';
    return buffer;
});

</script>