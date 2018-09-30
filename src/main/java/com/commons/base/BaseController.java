/**
 * @Title: BaseController.java
 * @Package com.base
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年7月7日 上午8:55:47
 * @version V1.0
 */

package com.commons.base;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * @author Du.Jun
 *
 */
public abstract class BaseController {

	@Autowired
	public HttpSession session;

	public JsonResult getJsonResult() {
		JsonResult result = new JsonResult();
		result.setMessage("操作成功");
		return result;
	}

	public JsonResult getJsonResult(Object data) {
		JsonResult result = new JsonResult();
		result.setData(data);
		return result;
	}

	public JsonResult getJsonResult(String errorcode, String message) {
		JsonResult result = new JsonResult();
		result.setCode(errorcode);
		result.setMessage(message);
		return result;
	}

	public JSONPObject getJsonp(String callbackparam, JsonResult jsonResult) {
		return new JSONPObject(callbackparam, jsonResult);
	}

}
