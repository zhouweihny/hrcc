package com.commons.util.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.modules.pojo.FxSaleData;

/**
 * @Description
 */
public class ImportExcelUtil {
	final static String notnullerror = "请填入第%s行的%s,%s不能为空";
	final static String errormsg = "第%s行的%s数据导入错误";

	public static <E> Integer countRows(Class<E> clazz, File file) throws Exception {
		if (file.getName().toLowerCase().endsWith("xlsx")) {
			return ImportExcelUtil.countRows2007(clazz, new FileInputStream(file));
		} else if (file.getName().toLowerCase().endsWith("xls")) {
			return ImportExcelUtil.countRows2003(clazz, new FileInputStream(file));
		}
		return 0;
	}

	public static <E> Integer countRows2007(Class<E> clazz, InputStream xlsx) throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook(xlsx);
		try {
			XSSFSheet sheet = xwb.getSheetAt(0);
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() != -1) {
						if (!sheet.getRow(0).getCell(modelProp.colIndex()).toString().trim().equals(modelProp.name()))
							throw new Exception("模板格式错误,请检查上传模板 !");
					}
				}
			}
			return sheet.getPhysicalNumberOfRows();
		} finally {
			xwb.close();
			xlsx.close();
		}
	}

	public static <E> Integer countRows2003(Class<E> clazz, InputStream xls) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(xls);
		try {
			// 取得Excel
			HSSFSheet sheet = wb.getSheetAt(0);
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() != -1) {
						if (!sheet.getRow(0).getCell(modelProp.colIndex()).toString().trim().equals(modelProp.name()))
							throw new Exception("模板格式错误,请检查上传模板 !");
					}
				}
			}

			return sheet.getPhysicalNumberOfRows();
		} finally {
			wb.close();
			xls.close();
		}
	}

	public static <E> List<E> readExcel(Class<E> clazz, File file) throws Exception {
		if (file.getName().toLowerCase().endsWith("xlsx")) {
			return ImportExcelUtil.readExcel2007(clazz, file);
		} else if (file.getName().toLowerCase().endsWith("xls")) {
			return ImportExcelUtil.readExcel2003(clazz, file);
		}
		return null;
	}

	/**
	 * 读取Office 2007 excel
	 */

	public static <E> List<E> readExcel2007(Class<E> clazz, File file) throws Exception {

		InputStream xlsx = new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(file.getPath().replaceAll(".xlsx", "_error.xlsx"));
		try {
			// 取得Excel
			XSSFWorkbook xwb = new XSSFWorkbook(xlsx);
			XSSFSheet sheet = xwb.getSheetAt(0);
			Field[] fields = clazz.getDeclaredFields();
			List<Field> fieldList = new ArrayList<Field>(fields.length);

			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() != -1) {
						fieldList.add(field);
					}
				}
			}
			List<E> modelList = new ArrayList<E>();
			XSSFRow row = null;
			XSSFCell cell = null;
			XSSFCellStyle style = xwb.createCellStyle();
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
			style.setFillBackgroundColor(IndexedColors.ORANGE.getIndex());
			List<XSSFRow> erows = new ArrayList<XSSFRow>();

			loop: for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				E model = (E) clazz.newInstance();
				int nullCount = 0;
				for (Field field : fieldList) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (sheet.getRow(i).getCell(0) == null)
						break loop;
					cell = sheet.getRow(i).getCell(modelProp.colIndex());
					try {
						if (cell == null || StringUtils.isBlank(cell.toString())) {
							nullCount++;
							if (!modelProp.nullable()) {
								row.createCell(fieldList.size()).setCellValue(String.format(notnullerror, new String[] { "" + (1 + i), modelProp.name(), modelProp.name() }));
								erows.add(row);
								continue loop;
							}
						} else if (field.getType().equals(Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Date(cell.getDateCellValue().getTime()));

							}
						} else if (field.getType().equals(Timestamp.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(cell.getDateCellValue().getTime()));
							}

						} else if (field.getType().equals(java.sql.Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(cell.getDateCellValue().getTime()));
							}
						} else if (field.getType().equals(java.lang.Integer.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), (int) cell.getNumericCellValue());
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), Integer.parseInt(parseString(cell)));
							}
						} else if (field.getType().equals(java.math.BigDecimal.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(parseString(cell)));
							}
						} else {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), parseString(cell));
							}
						}
					} catch (Exception e) {
						row.createCell(fieldList.size()).setCellValue(String.format(errormsg, new String[] { "" + (1 + i), modelProp.name() }));
						erows.add(row);
						continue loop;
					}
				}
				sheet.shiftRows(i, 1, -1);
				if (nullCount == fieldList.size()) {
					break;
				}
				modelList.add(model);
			}
			for (XSSFRow r : erows) {
				r.setRowStyle(style);
				for (int i = 0; i < fieldList.size() + 1; i++) {
					XSSFCell c = r.getCell(i);
					c.setCellStyle(style);
				}
			}
			xwb.write(os);
			return modelList;
		} finally {
			os.close();
			xlsx.close();
		}
	}

	/**
	 * 读取Office 20033 excel
	 */

	public static <E> List<E> readExcel2003(Class<E> clazz, File file) throws Exception {
		InputStream xls = new FileInputStream(file);
		FileOutputStream os = new FileOutputStream(file.getPath().replaceAll(".xls", "_error.xls"));
		try {
			// 取得Excel
			HSSFWorkbook wb = new HSSFWorkbook(xls);
			HSSFSheet sheet = wb.getSheetAt(0);
			Field[] fields = clazz.getDeclaredFields();
			List<Field> fieldList = new ArrayList<Field>(fields.length);
			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() != -1) {
						fieldList.add(field);
					}
				}
			}
			// 行循环
			List<E> modelList = new ArrayList<E>();
			List<HSSFRow> erows = new ArrayList<HSSFRow>();
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
			style.setFillBackgroundColor(IndexedColors.ORANGE.getIndex());
			HSSFCell cell;
			loop: for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				// 数据模型
				E model = (E) clazz.newInstance();
				HSSFRow row = sheet.getRow(i);
				for (Field field : fieldList) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					cell = sheet.getRow(i).getCell(modelProp.colIndex());
					try {
						if (cell == null || StringUtils.isBlank(cell.toString())) {
							if (!modelProp.nullable()) {
								row.createCell(fieldList.size()).setCellValue(String.format(notnullerror, new String[] { "" + (1 + i), modelProp.name(), modelProp.name() }));
								erows.add(row);
								continue loop;
							}
						} else if (field.getType().equals(Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Date(cell.getDateCellValue().getTime()));

							}
						} else if (field.getType().equals(Timestamp.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(cell.getDateCellValue().getTime()));
							}

						} else if (field.getType().equals(java.sql.Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(cell.getDateCellValue().getTime()));
							}
						} else if (field.getType().equals(java.lang.Integer.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), (int) cell.getNumericCellValue());
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), Integer.parseInt(parseString(cell)));
							}
						} else if (field.getType().equals(java.math.BigDecimal.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(parseString(cell)));
							}
						} else {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), parseString(cell));
							}
						}
					} catch (Exception e) {
						row.createCell(fieldList.size()).setCellValue(String.format(errormsg, new String[] { "" + (1 + i), modelProp.name() }));
						erows.add(row);
						continue loop;
					}
				}
				modelList.add(model);
			}
			for (HSSFRow r : erows) {
				r.setRowStyle(style);
				for (int i = 0; i < fieldList.size() + 1; i++) {
					HSSFCell c = r.getCell(i);
					if (c != null)
						c.setCellStyle(style);
				}
			}

			wb.write(os);
			return modelList;
		} finally {
			os.close();
			xls.close();
		}
	}

	/**
	 * 读取Office 2007 excel
	 */

	public static <E> List<E> importExcel2007(Class<E> clazz, InputStream xlsx) throws Exception {
		try {
			// 取得Excel
			XSSFWorkbook xwb = new XSSFWorkbook(xlsx);
			XSSFSheet sheet = xwb.getSheetAt(0);
			Field[] fields = clazz.getDeclaredFields();
			List<Field> fieldList = new ArrayList<Field>(fields.length);

			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() != -1) {
						fieldList.add(field);
					}
				}
			}
			List<E> modelList = new ArrayList<E>();
			XSSFRow row = null;
			XSSFCell cell = null;

			loop: for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}

				E model = (E) clazz.newInstance();
				int nullCount = 0;
				Exception nullError = null;
				for (Field field : fieldList) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (sheet.getRow(i).getCell(0) == null)
						break loop;
					cell = sheet.getRow(i).getCell(modelProp.colIndex());
					try {
						if (cell == null || StringUtils.isBlank(cell.toString())) {
							nullCount++;
							if (!modelProp.nullable()) {
								nullError = new Exception(String.format(notnullerror, new String[] { "" + (1 + i), modelProp.name(), modelProp.name() }));
							}
						} else if (field.getType().equals(Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Date(cell.getDateCellValue().getTime()));

							}
						} else if (field.getType().equals(Timestamp.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(cell.getDateCellValue().getTime()));
							}

						} else if (field.getType().equals(java.sql.Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(cell.getDateCellValue().getTime()));
							}
						} else if (field.getType().equals(java.lang.Integer.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), (int) cell.getNumericCellValue());
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), Integer.parseInt(parseString(cell)));
							}
						} else if (field.getType().equals(java.math.BigDecimal.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(parseString(cell)));
							}
						} else {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), parseString(cell));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(String.format(errormsg, new String[] { "" + (1 + i), modelProp.name() }) + "," + e.getMessage());
					}
				}
				if (nullCount == fieldList.size()) {
					break;
				}
				if (nullError != null) {
					throw nullError;
				}
				modelList.add(model);

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
	public static <E> List<E> importExcel2003(Class<E> clazz, InputStream xls) throws Exception {
		try {
			// 取得Excel
			HSSFWorkbook wb = new HSSFWorkbook(xls);
			HSSFSheet sheet = wb.getSheetAt(0);
			Field[] fields = clazz.getDeclaredFields();
			List<Field> fieldList = new ArrayList<Field>(fields.length);
			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() != -1) {
						fieldList.add(field);
					}
				}
			}
			// 行循环
			List<E> modelList = new ArrayList<E>();
			HSSFCell cell;
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				// 数据模型
				E model = (E) clazz.newInstance();
				int nullCount = 0;
				Exception nullError = null;
				for (Field field : fieldList) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					cell = sheet.getRow(i).getCell(modelProp.colIndex());
					try {
						if (cell == null || StringUtils.isBlank(cell.toString())) {
							nullCount++;
							if (!modelProp.nullable()) {
								nullError = new Exception(String.format(notnullerror, new String[] { "" + (1 + i), modelProp.name(), modelProp.name() }));

							}
						} else if (field.getType().equals(Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Date(cell.getDateCellValue().getTime()));

							}
						} else if (field.getType().equals(Timestamp.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new Timestamp(cell.getDateCellValue().getTime()));
							}

						} else if (field.getType().equals(java.sql.Date.class)) {
							if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(parseDate(parseString(cell))));
							} else {
								BeanUtils.setProperty(model, field.getName(), new java.sql.Date(cell.getDateCellValue().getTime()));
							}
						} else if (field.getType().equals(java.lang.Integer.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), (int) cell.getNumericCellValue());
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), Integer.parseInt(parseString(cell)));
							}
						} else if (field.getType().equals(java.math.BigDecimal.class)) {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(parseString(cell)));
							}
						} else {
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), new BigDecimal(cell.getNumericCellValue()));
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								BeanUtils.setProperty(model, field.getName(), parseString(cell));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception(String.format(errormsg, new String[] { "" + (1 + i), modelProp.name() }) + "," + e.getMessage());
					}
				}
				if (nullCount == fieldList.size()) {
					break;
				}
				if (nullError != null) {
					throw nullError;
				}
				modelList.add(model);
			}
			return modelList;

		} finally {
			xls.close();
		}
	}

	private final static int colsizeN = 630;
	private final static int colsizeM = 1000;

	/**
	 * 下载Excel模版
	 * 
	 * @param clazz
	 * @param map
	 * @param rowSize
	 * @return
	 */
	public static InputStream excelModelbyClass(Class<?> clazz, Map<Integer, String[]> map, Integer rowSize) {
		try {
			if (rowSize == null) {
				rowSize = 1000;
			}
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			Field[] fields = clazz.getDeclaredFields();
			HSSFRow headRow = sheet.createRow(0);
			int colSzie = 0;
			/**
			 * 设置表头样式
			 */
			HSSFCellStyle headStyle = wb.createCellStyle();
			headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFFont headFont = wb.createFont();
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headFont.setFontHeight((short) 240);
			headStyle.setFont(headFont);
			List<Integer> cells = new ArrayList<Integer>();

			for (Field field : fields) {
				if (field.isAnnotationPresent(ModelProp.class)) {
					ModelProp modelProp = field.getAnnotation(ModelProp.class);
					if (modelProp.colIndex() == -1)
						continue;
					cells.add(modelProp.colIndex());
					HSSFCell cell = headRow.createCell(modelProp.colIndex());
					cell.setCellValue(new HSSFRichTextString(modelProp.name()));
					cell.setCellStyle(headStyle);
					colSzie++;
					sheet.autoSizeColumn((short) modelProp.colIndex());
					sheet.setColumnWidth(modelProp.colIndex(), modelProp.name().length() * colsizeN + colsizeM);

					// 设置列为下拉框格式
					if (map != null && map.get(new Integer(modelProp.colIndex())) != null) {
						DVConstraint constraint = DVConstraint.createExplicitListConstraint(map.get(modelProp.colIndex()));
						CellRangeAddressList regions = new CellRangeAddressList(2, rowSize, modelProp.colIndex(), modelProp.colIndex());
						HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
						sheet.addValidationData(dataValidation);
					}
				}
			}
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFDataFormat format = wb.createDataFormat();
			cellStyle.setDataFormat(format.getFormat("@"));
			for (int i = 1; i < rowSize; i++) {
				HSSFRow row = sheet.createRow(i);
				for (Integer integer : cells) {
					HSSFCell cell = row.createCell(integer);
					cell.setCellStyle(cellStyle);
				}
			}
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSzie - 1));
			if (map != null) {
				for (Integer colIndex : map.keySet()) {
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(map.get(colIndex));
					CellRangeAddressList regions = new CellRangeAddressList(2, 1000, colIndex, colIndex);
					HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
					sheet.addValidationData(dataValidation);
				}
			}

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] b = os.toByteArray();

			ByteArrayInputStream in = new ByteArrayInputStream(b);
			return in;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String parseString(XSSFCell cell) {
		return String.valueOf(cell).trim();
	}

	private static String parseString(HSSFCell cell) {
		return String.valueOf(cell).trim();
	}

	public static void removeRow(HSSFSheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum)
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);// 将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
		if (rowIndex == lastRowNum) {
			HSSFRow removingRow = sheet.getRow(rowIndex);
			if (removingRow != null)
				sheet.removeRow(removingRow);
		}
	}

	private static long parseDate(String dateString) throws ParseException {
		if (dateString.indexOf("/") == 4) {
			return new SimpleDateFormat("yyyy/MM/dd").parse(dateString).getTime();
		} else if (dateString.indexOf("-") == 4) {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime();
		} else if (dateString.indexOf("年") == 4) {
			return new SimpleDateFormat("yyyy年MM月dd").parse(dateString).getTime();
		} else if (dateString.length() == 8) {
			return new SimpleDateFormat("yyyyMMdd").parse(dateString).getTime();
		} else {
			return new Date().getTime();
		}
	}

	public static void main(String args[]) throws Exception {
		ImportExcelUtil.readExcel(FxSaleData.class, new File("D:/salepath/20180621/CBEC5443E39A4A17BFD7FDF65FE393AB.xls"));
	}
}