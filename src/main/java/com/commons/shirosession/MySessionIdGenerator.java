package com.commons.shirosession;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import com.commons.util.UUIDGenerator;

import java.io.Serializable;

/**
 * sessionId生成工具类
 * 
 * @author Vincent
 * @time 2015/7/27 11:45
 */
public class MySessionIdGenerator implements SessionIdGenerator {

	public Serializable generateId(Session session) {
		// 自定义规则生成sessionid
		return UUIDGenerator.getUUID();
	}
}