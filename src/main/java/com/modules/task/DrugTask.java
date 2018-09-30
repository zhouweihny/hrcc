package com.modules.task;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.modules.pojo.Catalog;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;

public class DrugTask {
	private static Logger logger = Logger.getLogger(DrugTask.class);
	@Autowired
	private CatalogService catalogService;

	@Autowired
	private DrugService drugService;

	public void updateGgNPpflag() {
		try {
			List<Catalog> catalogs = catalogService.findList(new Catalog());

			for (Catalog catalog : catalogs) {
				drugService.updateGgNPpflag(catalog.getId());
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
