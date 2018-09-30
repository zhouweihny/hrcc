<div class="modal-dialog">
    <div class="modal-content">
        <form class="form-horizontal J_judgeChage" id="save_form">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>区域、科室分配</h5>
                    <div class="ibox-tools">
                        <a class="close-link"  data-dismiss="modal" >
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">区域分配</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">科室分配</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <ul id="zTreeList" class="ztree"></ul>
                                </div>
                            </div>
                            <div id="tab-2" class="tab-pane">
                                <div class="panel-body">
                                    <strong>请选择科室</strong>
                                    <dl class="zui_ks" id="J_zui_ks">
                                        
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="modal-footer">
            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
        </div>
    </div>
</div>

<script>

$(function(){
    console.log("88888")
})

</script>