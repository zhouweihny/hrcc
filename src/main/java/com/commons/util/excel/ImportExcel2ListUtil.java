package com.commons.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Description
 */
public class ImportExcel2ListUtil {

	/**
	 * 读取Office 2007 excel
	 */

	public static List<List<String>> importExcel2007(InputStream xlsx) throws Exception {
		try {
			// 取得Excel
			XSSFWorkbook xwb = new XSSFWorkbook(xlsx);
			XSSFSheet sheet = xwb.getSheetAt(0);
			List<List<String>> modelList = new ArrayList<List<String>>();
			XSSFRow row = null;
			XSSFCell cell = null;

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i) != null) {
					List<String> list = new ArrayList<String>();
					for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
						cell = sheet.getRow(i).getCell(j);
						try {
							if (cell == null || cell.toString().length() == 0) {
								list.add("");
							} else {
								list.add(parseString(cell));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					modelList.add(list);
				}
			}
			return modelList;

		} finally {
			xlsx.close();
		}
	}

	/**
	 * 导入Excel(2003)
	 * 
	 * @param clazz
	 * @param xls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public static List<List<String>> importExcel2003(InputStream xls) throws Exception {
		try {
			// 取得Excel
			HSSFWorkbook wb = new HSSFWorkbook(xls);
			HSSFSheet sheet = wb.getSheetAt(0);

			// 行循环
			List<List<String>> modelList = new ArrayList<List<String>>();
			HSSFCell cell;
			loop: for (int i = 0; i < sheet.getLastRowNum(); i++) {
				if (sheet.getRow(i) != null) {
					List<String> list = new ArrayList<String>();
					for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
						if (sheet.getRow(i).getCell(0) == null)
							break loop;
						cell = sheet.getRow(i).getCell(j);
						try {
							if (cell == null || cell.toString().length() == 0) {
								list.add("");
							} else {
								list.add(parseString(cell));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					modelList.add(list);
				}
			}
			return modelList;

		} finally {
			xls.close();
		}
	}

	private static String parseString(XSSFCell cell) {
		return String.valueOf(cell).trim();
	}

	private static String parseString(HSSFCell cell) {
		return String.valueOf(cell).trim();
	}

	public static void main(String args[]) throws Exception {
		InputStream xls = new FileInputStream(new File("d:/企业目录.xlsx"));
		List<List<String>> list = importExcel2007(xls);
		System.out.println(list.size());
	}
}