<#include "../tools/select.ftl"  /> 
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
    <script src="${request.contextPath}/js/plugins/rangy/rangy-core.js"></script>
    <script src="${request.contextPath}/js/plugins/blockUI/jquery.blockUI.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/jquery.dataTables_15.js"></script>
<script src="${request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<style type="text/css">
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
.form-inline select.form-control.input-sm {
    max-width: 150px;
}
.modal-dialog {
    width: 1100px!important;
}
#OrderList.dataTable tbody tr td:last-child, #OrderList.dataTable thead tr th:last-child {
    text-align: right;
}
#OrderList.dataTable tbody tr td.dataTables_empty {
    text-align: left;
}
#OrderList.dataTable tbody tr td:last-child a {
    display: block;
}
#OrderList.dataTable tbody tr td:last-child a:hover {
    text-decoration: underline;
}
#OrderList.dataTable tbody tr td:last-child .J_delCheck {
    margin-top: 6px;
}
.OrderList_paginate_jump {
    float: right;
    height: 25px;
    line-height: 25px;
    margin-left: 15px;
}
.OrderList_paginate_jump span {
    cursor: default;
}
.OrderList_paginate_jump input {
    width: 38px;
    height: 27px;
    line-height: 27px;
    margin: 0 3px;
    font-size: 14px;
    text-align: center;
    border: 1px solid #ddd;
    border-radius: 4px;
}
.OrderList_paginate_jump .btn {
    margin-left: 10px;
}
.modal .table.table-striped {
    width: 1050px!important;
}
.modal div.dataTables_info {
    padding-top: 5px;
}
.modal-open .modal .ibox-content {
    max-height: 540px;
    padding-bottom: 0;
}
</style>

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content ">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>品种列表</h5>
                    </div>
                    <div class="ibox-content">
                    <form class="form-inline row" id="J_formSearch" action="javascript:;">
                       	<div class="form-group col-md-3">
                            <label for="J_name">药品目录：</label>
                             <@select id="catalogid" datas=catalogs      key="id" text="name"/>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">品名：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入品名" name="name">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="J_name">厂商：</label>
                            <input type="text" class="form-control" id="J_name" placeholder="输入厂商" name="factory">
                        </div>
                        <div class="form-group col-md-2">
                            <button type="button" class="btn btn-w-m btn-info" onclick="MyFun.search('J_formSearch','DrugList');"><i class="fa fa-search"></i>查 询</button>
                        </div>
                    </form>
                    <div id="DrugList" data-url="${request.contextPath}/factory/ntable.do">
                    </div>    
                   </div>
              </div>
                </div>
            </div>
        </div>
    </div>
<div id="info-form" class="modal fade" aria-hidden="true" ></div>

<div id="info-formS" class="modal fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>厂商字典</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="" aria-describedby="DataTables_Table_0_info" style="margin-bottom: 15px;!important">
                            <tbody>
                                <tr style="background-color: #fff;">
                                    <td id="J_cur_fac"></td>
                                    <td style="text-align: right;width: 140px;">
                                        <a href="javascript:;" onclick="addfac();">新增</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable" id="OrderList" aria-describedby="DataTables_Table_0_info">
                            <thead>
                                <tr role="row">
                                    <th>厂商名称</th>
                                    <th>编码</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script>
var rangySelectionText = "",
    fac_keywords = "",
    fac_code = "",
  clickFlag = 0;

var zuiTbl = null;

  function refreshDrugList(){
   	MyFun.ajaxRefreshTable("DrugList");
  }

  $(function(){

   MyFun.ajaxRefreshTable("DrugList", "", function(){
     var menu = new BootstrapMenu('.J_fac_keywords', {
        fetchElementData: function(rowElem) {
            return $(rowElem);
        },
       actionsGroups: [
         ['search']
       ],
       actions: {
         search: {
           name: '查询厂商字典',
           onClick: function(row) {
            fac_keywords = row.text();
            fac_code = row.data("code");
             handleSelection();
           }
         }
       }
     });
   });
  })

function handleSelection(){
    rangySelectionText = rangy.getSelection().toString().replace(/[\r\n]/g,"").replace(/\s+/g, "");
    if(!rangySelectionText){
      MyFun.to.i("请选择关键字");
      return false;
    }
    
    $("#info-formS").modal('show');
    $('#info-formS').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
        if(zuiTbl){
            zuiTbl.destroy();
            zuiTbl = null;
        }
    })

    $(".modal-open .modal .ibox-content").css({"maxHeight": $(window).height() - 140});

    $("#J_cur_fac").text(fac_keywords);

    getzPage();
}

function getzPage(){
    if(zuiTbl){
        zuiTbl.draw();
    }else{
        zuiTbl = $('#OrderList').DataTable( {
            language: {
                sProcessing: "正在努力加载数据..."
            },
            dom: '<"top">rt<"bottom"flpi><"clear">',
            ordering:false,//关闭表格的排序功能
            serverSide: true,  //启用服务器端分页
            processing: true,  //隐藏加载提示,自行处理
            searching: false, // 禁用搜索
            ajax: function (data, callback, settings) {
                var formData = 'sfield=code&stype=desc&code=&name='+rangySelectionText+'&pageSize='+data.length+'&start='+data.start+'&currentPage='+((data.start / data.length)+1);

                //ajax请求数据
                $.ajax({
                    type: "post",
                    url: "${request.contextPath}/factory/glist.do",
                    cache: false,  //禁用缓存
                    data: formData,  //传入组装的参数
                    dataType: "json",
                    success: function (result) {
                        //封装返回数据
                        var returnData = {};
                        returnData.draw = data.draw;
                        if(result.data && result.data.totalRows){
                            returnData.recordsTotal = result.data.totalRows;//返回数据全部记录
                            returnData.recordsFiltered = result.data.totalRows;
                            returnData.data = result.data.data;//返回的数据列表
                        }else{
                            returnData.recordsTotal = 0;
                            returnData.recordsFiltered = 0;
                            returnData.data = [];
                        }
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕


                        callback(returnData);
                    }
                });
            },
            columns: [
                { "data": "name" },
                { "data": "code" },
                { "data": "" }
            ],
            columnDefs: [
                {
                    "render": function(data, type, row) {
                        return '<a href="javascript:;" class="J_check">新增</a>';
                    },
                    "targets": 2
                }
            ],
            drawCallback: function(settings){
                //增加跳转到某页
                if(!$("#OrderList_paginate_jump").length){
                    var _strArr = [
                        '<div id="OrderList_paginate_jump" class="OrderList_paginate_jump">',
                        '<span>到第</span>',
                        '<input class="input-txt" maxlength="4" value="">',
                        '<span>页</span>',
                        '<a class="btn btn-white">确定</a>',
                        '</div>'
                    ]
                    $("#OrderList_paginate").before(_strArr.join(""));
                    $("#OrderList_paginate_jump").off("click").on("click", ".btn", function(){
                        var _val = $(this).siblings(".input-txt").val();
                        gotoPage('1', _val);
                        return false;
                    }).off("keyup").on("keyup", ".input-txt", function(event){
                        this.value=this.value.replace(/[^0-9]/g,'');
                        if(event.keyCode==13){
                            var _val = $(this).val();
                            gotoPage('1', _val);
                            return false;
                        }
                    })
                }
            }
        });
    }

    $('#OrderList tbody').off("click").on('click', '.J_check', function () {
        var _zparent = $(this).parents('tr');
        G_index = $('#OrderList tbody tr[role="row"]').index(_zparent);
        var data = zuiTbl.row( _zparent ).data();
        
        addKeywords(data);
    })
}

function gotoPage(type, page){
    if(!page)
        return false;
    page = parseInt(page, 10) - 1;
    if(page < 0)
        page = 0;
    if(type == '1'){
        zuiTbl.page(page).draw(false);
        $("#OrderList_paginate_jump .input-txt").val("");
    }else if(type == '2'){
        zuiTblS.page(page).draw(false);
        $("#J_bottom_tbl_paginate_jump .input-txt").val("");
    }
}

function addKeywords(data){
    if(!data.code){
        MyFun.to.i("该条数据错误，请选择其他数据");
        return false;
    }
    if(clickFlag)
        return false;
    clickFlag = 1;
    createBlock(".modal-dialog", '正在努力加载数据...');
    $.PostJson("${request.contextPath}/factory/save.do", "name="+fac_keywords.replace(/&/g, "%26")+"&code="+data.code, function(state, json){
        if(state == 'success'){
            if(json && json.code == '0000'){
                MyFun.to.s(json.message || "新增厂商关键字成功");
                refreshDrugList();
            }else{
                MyFun.to.e(json.message || "新增厂商关键字失败");
            }
        }
        clickFlag = 0;
        unBlock(".modal-dialog");
        $("#info-formS").modal('hide');
    })
}

function addfac(){
    add(fac_code);
}

function add(id){
    createBlock(".wrapper", '正在努力加载数据...');
    $.ajax({
        type: "GET",
        url: "${request.contextPath}/factory/add.do?drugid="+id,
        dataType: "json",
        success: function(data){
         	if(data.code=='0000'){
         		MyFun.to.s(data.message);
                $("#info-formS").modal('hide');
         	    MyFun.ajaxRefreshTable("DrugList");
         	}else
        		MyFun.to.e(data.message);
        },
        complete: function(){
            unBlock(".wrapper");
        }
    });
  
  }
</script>
</body>
</html>

