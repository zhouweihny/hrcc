package com.modules.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.modules.service.AccesstokenService;

public class AccesstokenTask {
	private static Logger logger = Logger.getLogger(AccesstokenTask.class);

	@Autowired
	private AccesstokenService accesstokenService;

	public void clear() {

		try {
			accesstokenService.clear();
		} catch (Exception e) {
			logger.error(e);
		}

	}

}
