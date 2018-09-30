package com.modules.thread;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.modules.service.FxOrderfxService;



public class FxOrdernoThread extends Thread {


	
	private FxOrderfxService fxOrderfxService;

	 String Orderno;
	 String storeid;
	 Date rq;
	 BigDecimal xse;
	 String userid;
	
	public FxOrdernoThread(String Orderno,String storeid,Date rq,BigDecimal xse,String userid) {
		this.Orderno = Orderno;
		this.storeid = storeid;
		this.rq = rq;
		this.xse = xse;
		this.userid=userid;

	}


	public void run() {
		// TODO Auto-generated method stub
		try {
			fxOrderfxService.fxOrderno(Orderno, storeid, rq,xse,userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
