package com.modules.task;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.commons.util.excel.ImportExcelUtil;
import com.modules.pojo.Catalog;
import com.modules.pojo.Drug;
import com.modules.pojo.FxDrugStore;
import com.modules.pojo.FxSaleData;
import com.modules.pojo.FxSaleFile;
import com.modules.service.CatalogService;
import com.modules.service.DrugService;
import com.modules.service.FxComnameService;
import com.modules.service.FxDrugStoreService;
import com.modules.service.FxOrderfxService;
import com.modules.service.FxSaleDataService;
import com.modules.service.FxSaleFileService;
import com.modules.service.FxSaleMonthService;

public class UploadFileTask {

	private static Logger logger = Logger.getLogger(UploadFileTask.class);

	@Autowired
	private FxSaleFileService fxSaleFileService;
	@Autowired
	private FxSaleDataService fxSaleDataService;
	@Autowired
	private FxSaleMonthService fxSaleMonthService;
	@Autowired
	private FxDrugStoreService fxDrugStoreService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private FxComnameService fxComnameService;
	@Autowired
	private FxOrderfxService fxOrderfxService;

	public void saleFileTask() {
		FxSaleFile file = new FxSaleFile();
		file.setStatus(0);
		try {
			List<FxSaleFile> files = fxSaleFileService.findList(file);
			for (FxSaleFile f : files) {
				List<FxSaleData> fxSaleDatas;
				fxSaleDatas = ImportExcelUtil.readExcel(FxSaleData.class, new File(f.getFilepath()));
				Catalog catalog = new Catalog();
				catalog.setMyself(true);
				catalog.setUserid(f.getUserid());
				catalog = catalogService.findObject(catalog);
				Drug drug;
				f.setStatus(1);
				fxSaleFileService.update(f);
				int processed = f.getProcessed();
				int error = f.getError();
				String temporderno = "";
				for (int i = 0; i < fxSaleDatas.size(); i++) {
					if (i > f.getProcessed() + error - 1) {
						FxSaleData fxSaleData = fxSaleDatas.get(i);
						drug = new Drug();
						drug.setCatalogid(catalog.getId());
						drug.setCode(fxSaleData.getCode());
						drug = drugService.findObject(drug);
						FxDrugStore fxDrugStore = new FxDrugStore();
						if (drug == null) {
							drug = new Drug();
							drug.setCode(fxSaleData.getCode());
							drug.setName(fxSaleData.getName());
							drug.setSpecifications(fxSaleData.getSpecifications());
							drug.setFactory(fxSaleData.getFactory());
							drug.setUnit(fxSaleData.getUnit());
							drug.setCatalogid(catalog.getId());
							String comnameid = fxComnameService.compareDrug(fxSaleData.getName());
							drug.setComnameid(comnameid);
							drugService.save(drug);
							fxDrugStore.setDrugid(drug.getId());
							fxDrugStore.setStoreid(f.getStoreid());
							fxDrugStore.setStatus(true);
							fxDrugStore.setZy(true);
							fxDrugStore.setXsrq(fxSaleData.getSaledate());
							fxDrugStore.setUserid(f.getUserid());
							if (fxSaleData != null)
								fxDrugStore.setBhrq(fxSaleData.getBhrq());
							fxDrugStoreService.save(fxDrugStore);
						}
						if (StringUtils.isBlank(fxDrugStore.getId())) {
							fxDrugStore.setDrugid(drug.getId());
							fxDrugStore.setStoreid(f.getStoreid());
							fxDrugStore = fxDrugStoreService.findObject(fxDrugStore);
							if (fxDrugStore != null) {
								fxDrugStore.setStatus(true);
								fxDrugStore.setZy(true);
								fxDrugStore.setXsrq(fxSaleData.getSaledate());
								if (fxSaleData != null)
									fxDrugStore.setBhrq(fxSaleData.getBhrq());
								fxDrugStoreService.update(fxDrugStore);
							} else {
								fxDrugStore = new FxDrugStore();
								fxDrugStore.setDrugid(drug.getId());
								fxDrugStore.setStoreid(f.getStoreid());
								fxDrugStore.setStatus(true);
								fxDrugStore.setZy(true);
								fxDrugStore.setXsrq(fxSaleData.getSaledate());
								if (fxSaleData != null)
									fxDrugStore.setBhrq(fxSaleData.getBhrq());
								fxDrugStoreService.save(fxDrugStore);
							}
						}
						fxSaleData.setDrugid(drug.getId());
						fxSaleData.setFileid(f.getId());
						fxSaleData.setStoreid(f.getStoreid());
						fxSaleData.setUserid(f.getUserid());
						FxSaleData tfxSaleData = new FxSaleData();
						tfxSaleData.setOrderno(fxSaleData.getOrderno());
						tfxSaleData.setCode(fxSaleData.getCode());
						tfxSaleData.setQty(fxSaleData.getQty());
						tfxSaleData.setStoreid(f.getStoreid());
						tfxSaleData = fxSaleDataService.findObject(tfxSaleData);
						if (tfxSaleData == null) {
							fxSaleDataService.save(fxSaleData);
							fxSaleMonthService.addSaleData(fxSaleData);

							if (temporderno != "" && !temporderno.equals(fxSaleData.getOrderno())) {
								fxOrderfxService.refresh(temporderno, f.getStoreid());
								temporderno = fxSaleData.getOrderno();
							}
						}
						fxSaleFileService.addProcessed(f, fxSaleData);
						processed++;
					}
				}
				fxOrderfxService.refresh(temporderno, f.getStoreid());

				FxSaleFile fxSaleFile = new FxSaleFile();
				fxSaleFile.setId(f.getId());
				fxSaleFile.setStatus(2);
				fxSaleFile.setError(f.getNum() - processed);
				fxSaleFileService.update(fxSaleFile);

			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
