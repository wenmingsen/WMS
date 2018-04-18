package com.csg.intshop.javacommon.page;

import java.util.ArrayList;

public class JQPage {
	
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	protected Object rows;

	protected int pageSize;

	protected int pageNumber;

	protected int total = 0;
	
	protected String msg;
	
	protected String errorMsg;

	public JQPage() {
		this(1,DEFAULT_PAGE_SIZE,0,new ArrayList(0));
	}
	
	public JQPage(Page p) {
		this(p.getThisPageNumber(), p.getPageSize(), p.getTotalNumberOfElements(), p.getThisPageElements());
	}
	 
	public JQPage(int pageNumber,int pageSize,int totalElementsCount,Object elements) {
		init(pageNumber,pageSize,totalElementsCount,elements);
	}
	
	protected void init(int pageNumber,int pageSize,int totalElementsCount) {
		init(pageNumber,pageSize,totalElementsCount,null);
	}
	
	protected void init(int pageNumber,int pageSize,int totalElementsCount,Object elements) {
		if(pageSize <= 0)
			throw new IllegalArgumentException("[pageSize] must great than zero");
		this.pageSize = pageSize;
		this.pageNumber = getPageNumber(pageNumber,pageSize,totalElementsCount);
		this.total = totalElementsCount;
		this.rows = elements;
	}
	
	
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setTotalNumberOfElements(int totalElementsCount) {
		this.total = totalElementsCount;
	}
	
	public boolean isFirstPage() {
		return getThisPageNumber() == 1;
	}

	public boolean isLastPage() {
		return getThisPageNumber() >= getLastPageNumber();
	}

	public boolean hasNextPage() {
		return getLastPageNumber() > getThisPageNumber();
	}

	public boolean hasPreviousPage() {
		return getThisPageNumber() > 1;
	}

	public int getLastPageNumber() {
		return getLastPageNumber(total, pageSize);
	}

	public Object getThisPageElements() {
		return rows;
	}

	public int getTotalNumberOfElements() {
		return total;
	}

	public int getThisPageFirstElementNumber() {
		return (getThisPageNumber() - 1) * getPageSize() + 1;
	}

	public int getThisPageLastElementNumber() {
		int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
		return getTotalNumberOfElements() < fullPage ? getTotalNumberOfElements() : fullPage;
	}

	public int getNextPageNumber() {
		return getThisPageNumber() + 1;
	}

	public int getPreviousPageNumber() {
		return getThisPageNumber() - 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getThisPageNumber() {
		return pageNumber;
	}
	
    public static int getPageNumber(int pageNumber, int pageSize,int totalElements) {
		if(pageNumber <= 0) {
			return 1;
		}
    	if (Integer.MAX_VALUE == pageNumber
				|| pageNumber > getLastPageNumber(totalElements,pageSize)) { //last page
			return getLastPageNumber(totalElements,pageSize);
		}
		return pageNumber;
    }
    
	public static int getLastPageNumber(int totalElements,int pageSize) {
		return totalElements % pageSize == 0 ? 
				totalElements / pageSize 
				: totalElements / pageSize + 1;
	}
	
	public Object getRows(){
		return rows;
	}
	
	public int getTotal(){
		return total;
	}
	
    
	
}
