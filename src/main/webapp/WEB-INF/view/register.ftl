<div class="modal-dialog">
  <div class="modal-content">
    <form class="form-horizontal J_judgeChage" id="save_form">
      <div class="ibox float-e-margins">
        <div class="ibox-title">
          <h5>注册</h5>
          <div class="ibox-tools">
            <a class="close-link"  data-dismiss="modal" >
              <i class="fa fa-times"></i>
            </a>
          </div>
        </div>
        <div class="ibox-content">
          <div class="form-group">
          <label class="col-sm-3 control-label">用户名：</label>
            <div class="col-sm-8">
              <input type="text" id="dusername" name="username" placeholder="请输入用户名" class="form-control"> 
              <span class="zrequire" title="必填">*</span>
              <span class="help-block m-b-none"></span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">密码：</label>
            <div class="col-sm-8">
              <input type="password" name="password" id="password" placeholder="请输入密码" class="form-control"  value=""> 
              <span class="zrequire" title="必填">*</span>
              <span class="help-block m-b-none"></span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">密码确认：</label>
            <div class="col-sm-8">
              <input type="password" name="passwordS" id="passwordS" placeholder="请输入密码" class="form-control"  value=""> 
              <span class="zrequire" title="必填">*</span>
              <span class="help-block m-b-none"></span>
            </div>
          </div>
            <div class="form-group">
            <label class="col-sm-3 control-label">类型：</label>
            <div class="col-sm-8">
              <select  name ="roleid"  class="form-control input-sm">
                	<option value="05D8DCFAB55440F88EBCBC8C015BC690">药店</option>
              		<option value="B1CC34941707470D9BF361D1CEF2B08E">供应商</option>
              		<option value="A6B9CC86F7F24156A2CC50895312CC03">采购商</option>
              </select>
              <span class="zrequire" title="必填">*</span>
              <span class="help-block m-b-none"></span>
            </div>
          </div>
          
              <div class="form-group">
            <label class="col-sm-3 control-label">公司名称：</label>
            <div class="col-sm-8">
              <input type="text" name="company" id="J_company" placeholder="请输入公司名称" class="form-control"  value=""> 
              <span class="zrequire" title="必填">*</span>
              <span class="help-block m-b-none"></span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">公司简称：</label>
            <div class="col-sm-8">
              <input type="text" name="scompany" id="J_scompany" placeholder="请输入公司简称" class="form-control"  value=""> 
              <span class="zrequire" title="必填">*</span>
              <span class="help-block m-b-none"></span>
            </div>
          </div>
          
          <div class="form-group">
            <label class="col-sm-3 control-label">邮箱：</label>
            <div class="col-sm-8">
              <input type="text" name="email" placeholder="请输入邮箱" class="form-control"  value=""> 
              <span class="help-block m-b-none"></span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">手机号：</label>
            <div class="col-sm-8">
              <input type="text" name="mobile" placeholder="请输入手机号" class="form-control"  value=""> 
              <span class="help-block m-b-none"></span>
            </div>
          </div>
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
      <button type="button" class="btn btn-primary" id="J_saveRegister">注册</button>
    </div>
</div>
