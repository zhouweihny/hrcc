/**
 * @Title: UUIDGenerator.java
 * @Package com.util
 * @Description: TODO
 * @author Du.Jun
 * @date 2014年7月11日 下午3:08:08
 * @version V1.0
 */

package com.commons.util;

import java.util.UUID;

/**
 * @author Du.Jun
 * 
 */
public class UUIDGenerator {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.toUpperCase().replaceAll("-", "");
	}

}
