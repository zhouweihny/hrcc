/**
 * @Title: InstantiationTracingBeanPostProcessor.java
 * @Package com.init
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年7月21日 上午11:19:51
 * @version V1.0
 */

package com.modules.init;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.commons.sql.Page;
import com.commons.sql.PageResult;
import com.commons.util.OrderNoUtil;
import com.modules.pojo.Autocompare;
import com.modules.pojo.Bill;
import com.modules.pojo.Purchase;
import com.modules.pojo.SysWx;
import com.modules.service.AutocompareService;
import com.modules.service.BillService;
import com.modules.service.FactoryKeywordService;
import com.modules.service.KeywordsService;
import com.modules.service.PurchaseService;
import com.modules.service.SysWxAccesstokenService;

/**
 * @author Du.Jun
 *
 */
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger logger = Logger.getLogger(InstantiationTracingBeanPostProcessor.class);

	@Autowired
	private KeywordsService keywordsService;
	@Autowired
	private AutocompareService autocompareService;
	@Autowired
	private FactoryKeywordService factoryKeywordService;
	@Autowired
	private SysWxAccesstokenService sysWxAccesstokenService;
	@Autowired
	private BillService billService;
	@Autowired
	private PurchaseService purchaseService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org
	 * .springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		if (event.getApplicationContext().getParent() == null) {// rootapplicationcontext
			// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法.
			try {
				Map<String, Object> billMap = new HashMap<String, Object>();
				billMap.put("stype", "desc");
				billMap.put("sfield", "createtime");
				PageResult<Bill> billdata = billService.findList(billMap, new Page(1, 1, false));
				if (billdata.getData().size() == 0) {
					OrderNoUtil.intiBillNo();
				} else {
					OrderNoUtil.intiBillNo(billdata.getData().get(0).getNo());
				}

				Map<String, Object> purchaseMap = new HashMap<String, Object>();
				purchaseMap.put("stype", "desc");
				purchaseMap.put("sfield", "createtime");
				PageResult<Purchase> purchasedata = purchaseService.findList(purchaseMap, new Page(1, 1, false));
				if (purchasedata.getData().size() == 0) {
					OrderNoUtil.intiPurchaseNo();
				} else {
					OrderNoUtil.intiPurchaseNo(purchasedata.getData().get(0).getNo());
				}

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("finished", false);
				PageResult<Autocompare> autocompares = autocompareService.findList(params, new Page(false));
				for (final Autocompare autocompare : autocompares.getData()) {
					new Thread() {
						public void run() {
							try {
								autocompareService.autoCompare(autocompare);
							} catch (Exception e) {
								logger.error(e);
							}
						}
					}.start();
				}
				keywordsService.refreshIK();
				factoryKeywordService.refreshIK();
				sysWxAccesstokenService.refresh(new SysWx());
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
}
