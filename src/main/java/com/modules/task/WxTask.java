package com.modules.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.modules.pojo.SysWx;
import com.modules.service.SysWxAccesstokenService;

public class WxTask {
	private static Logger logger = Logger.getLogger(WxTask.class);
	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;
//更新微信
	public void refresh() {
		try {
			sysWxAccesstokenService.refresh(new SysWx());
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
