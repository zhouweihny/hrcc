<div class="modal-dialog">
  <div class="modal-content">
    <form class="form-horizontal J_judgeChage" id="save_form">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>文本编辑</h5>
          <div class="ibox-tools">
            <a class="close-link"  data-dismiss="modal" >
              <i class="fa fa-times"></i>
            </a>
          </div>
        </div>
      <div class="ibox-content">
        <input  type="hidden" name="id" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
          <input  type="hidden" name="type" value="1">
        <div class="form-group">
          <label class="col-sm-3 control-label">内容：</label>
          <div class="col-sm-8">
            <input type="text" name="content" placeholder="请输入内容" class="form-control J_zname"  value="<#if text??>${text!""}</#if>"> 
            <span class="help-block m-b-none"></span>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary J_zsave" onclick="MyFun.objSave('save_form','${request.contextPath}/syswxmsg/updatecontent.do')">保存</button>
      </div>
    </form>
  </div>
</div>

 