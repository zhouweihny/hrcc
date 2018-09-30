package com.commons.sql;

/**
 * @author Du.Jun
 *
 */
public class Page {

	private Integer pageSize = 10; // 每页显示数据条数，默认为10条记录

	private Integer currentPage = 1; // 当前页数

	private boolean isPage = true;// 是否需要翻页
	
	private boolean notCount=false;//不计算总量

	public Page(Boolean isPage) {
		this.isPage = isPage == null ? this.isPage : isPage;
	}

	public Page(Integer currentPage, Integer pageSize) {
		this.currentPage = currentPage == null ? this.currentPage : currentPage;
		this.pageSize = pageSize == null ? this.pageSize : pageSize;
	}

	public Page(Integer currentPage, Integer pageSize, Boolean notCount) {
		this.currentPage = currentPage == null ? this.currentPage : currentPage;
		this.pageSize = pageSize == null ? this.pageSize : pageSize;
		this.notCount = notCount == null ? this.notCount : notCount;
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

	public boolean isNotCount() {
		return notCount;
	}

	public void setNotCount(boolean notCount) {
		this.notCount = notCount;
	}

}
