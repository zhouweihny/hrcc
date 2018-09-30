package com.commons.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class OrderNoUtil {

	public static String billNo;

	public static String purchaseNo;

	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	public static void intiBillNo() {
		Date date = new Date();
		String h = format.format(date);
		billNo = h + "00000";
	}

	public static void intiBillNo(String billNo) {
		if (StringUtils.isBlank(billNo))
			intiBillNo();
		else
			OrderNoUtil.billNo = billNo;
	}

	public static void intiPurchaseNo() {
		Date date = new Date();
		String h = format.format(date);
		purchaseNo = h + "00000";
	}

	public static void intiPurchaseNo(String purchaseNo) {
		if (StringUtils.isBlank(purchaseNo))
			intiPurchaseNo();
		else
			OrderNoUtil.purchaseNo = purchaseNo;
	}

	public static String getBillNo() {
		Date date = new Date();
		String h = format.format(date) + "00000";
		BigDecimal bd = new BigDecimal(billNo);
		BigDecimal tbd = new BigDecimal(h);
		if (tbd.compareTo(bd) == 1) {
			bd = tbd;
		}
		billNo = bd.add(new BigDecimal(1)).toString();
		return billNo;
	}

	public static String getPurchaseNo() {
		Date date = new Date();
		String h = format.format(date) + "00000";

		BigDecimal tbd = new BigDecimal(h);

		BigDecimal bd = new BigDecimal(purchaseNo);
		if (tbd.compareTo(bd) == 1) {
			bd = tbd;
		}

		purchaseNo = bd.add(new BigDecimal(1)).toString();
		return purchaseNo;
	}

	public static void main(String args[]) throws Exception {
		intiBillNo();
		System.out.println(billNo);
		System.out.println(getBillNo());
		System.out.println(getBillNo());
	}

}
