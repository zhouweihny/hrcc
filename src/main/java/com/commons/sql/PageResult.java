package com.commons.sql;

import java.io.Serializable;
import java.util.List;

/**
 * @author Du.Jun
 *
 */
public class PageResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7519967296852636970L;

	private Integer totalRows = 0; // 记录总数

	private Integer totalPages = 0; // 总页数

	private Integer pageSize = 10; // 每页显示数据条数，默认为10条记录

	private Integer currentPage = 1; // 当前页数

	private boolean hasPrevious = false; // 是否有上一页

	private boolean hasNext = false; // 是否有下一页

	private boolean isPage = true;// 是否翻页

	private List<T> data;

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		if (totalRows % pageSize == 0)
			totalPages = totalRows / pageSize;
		else
			totalPages = totalRows / pageSize + 1;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the hasPrevious
	 */
	public boolean isHasPrevious() {
		return hasPrevious;
	}

	/**
	 * @param hasPrevious
	 *            the hasPrevious to set
	 */
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	/**
	 * @return the hasNext
	 */
	public boolean isHasNext() {
		return hasNext;
	}

	/**
	 * @param hasNext
	 *            the hasNext to set
	 */
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * @return the isPage
	 */
	public boolean isPage() {
		return isPage;
	}

	/**
	 * @param isPage
	 *            the isPage to set
	 */
	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

}
