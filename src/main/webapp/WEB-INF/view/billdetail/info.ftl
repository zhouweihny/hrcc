<#include "../tools/select.ftl"  /> 
<div class="modal-dialog">
	<div class="modal-content">
		<form class="form-horizontal J_judgeChage" id="save_form">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5><#if data??>编辑<#else>新增</#if></h5>
					<div class="ibox-tools">
						<a class="close-link"  data-dismiss="modal" >
							<i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<input  type="hidden" name="id" value="<#if data??><#if data.id??>${data.id}</#if></#if>">
					<div class="form-group">
						<label class="col-sm-3 control-label">状态：</label>
						<div class="col-sm-8">
							<select id="J_status" class="form-control input-sm status" name="status">
								<option value="0">有货</option>
								<option value="1">缺货</option>
								<option value="2">断货</option>
								<option value="3">近效期</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">备注：</label>
						<div class="col-sm-8">
							<input type="text" name="remark" placeholder="输入备注" class="form-control"  value="<#if data??><#if data.remark??>${data.remark}</#if></#if>"> 
							<span class="help-block m-b-none"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="MyFun.objSave('save_form','${request.contextPath}/billdetail/update.do','refreshBillDetailList')">保存</button>
			</div>
		</form>
	</div>
</div>