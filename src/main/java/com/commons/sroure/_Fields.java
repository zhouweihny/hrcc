package com.commons.sroure;

/**
 * @author Du.Jun
 *
 */
public class _Fields {
	private String column; // 列名
	private String columnType; // 列类型
	private String field; // 属性名
	private String fieldType; // 属性类型
	private String comment; // 列注释
	private Boolean isPrimarykey = false;// 是否主键
	private Integer size; // 长度
	private Integer precision;// 数字长度
	private Integer scale;// 数字 精度
	private Integer isNullable; // 是否为空

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * @return the columnType
	 */
	public String getColumnType() {
		return columnType;
	}

	/**
	 * @param columnType
	 *            the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the fieldType
	 */
	public String getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType
	 *            the fieldType to set
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the isPrimarykey
	 */
	public Boolean getIsPrimarykey() {
		return isPrimarykey;
	}

	/**
	 * @param isPrimarykey
	 *            the isPrimarykey to set
	 */
	public void setIsPrimarykey(Boolean isPrimarykey) {
		this.isPrimarykey = isPrimarykey;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * @return the isNullable
	 */
	public Integer getIsNullable() {
		return isNullable;
	}

	/**
	 * @param isNullable
	 *            the isNullable to set
	 */
	public void setIsNullable(Integer isNullable) {
		this.isNullable = isNullable;
	}

	/**
	 * @return the precision
	 */
	public Integer getPrecision() {
		return precision;
	}

	/**
	 * @param precision
	 *            the precision to set
	 */
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	/**
	 * @return the scale
	 */
	public Integer getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
	}

}