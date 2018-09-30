package com.modules.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.modules.pojo.Bill;
import com.modules.pojo.SysWx;
import com.modules.pojo.User;
import com.modules.service.BillService;
import com.modules.service.SysWxAccesstokenService;
import com.modules.service.UserService;

public class BillTask {
	private static Logger logger = Logger.getLogger(BillTask.class);
	@Autowired
	private BillService billService;

	@Autowired
	private UserService userService;

	public void autosend() {
		try {
			User user = new User();
			user.setAutosendbill(true);
			List<User> companys = userService.findList(user);

			for (User company : companys) {

				Bill bill = new Bill();
				bill.setStatus(-1); 
				bill.setPurchaserid(company.getId());
				List<Bill> bills = billService.findList(bill);

				for (Bill b : bills) {
					b.setStatus(0);
					billService.update(bill);
					billService.sendNotice(b.getId());
				}

			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
