/**
 * 
 */
package com.csg.intshop.bean;

import java.util.List;

/**
 * @author Administrator
 * @param <T>
 *
 */
public class ShopPage<T> {
	
	/**页码*/
	private int pageNumber;
	
	/**每页显示数量*/
	private int pageSize;

	/**总记录数*/
	private int totalPageNum;
	
	/**总页数*/
	private int totalPageSize;
	
	/**mysql查询开始位置*/
	private int startNum;
	
	/**分页集合*/
	private List<T> list;
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	/**
	 * @return pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber 要设置的 pageNumber
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize 要设置的 pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return startNum
	 */
	public int getStartNum() {
		return startNum;
	}

	/**
	 * @param startNum 要设置的 startNum
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	/**
	 * @return totalPageNum
	 */
	public int getTotalPageNum() {
		return totalPageNum;
	}

	/**
	 * @param totalPageNum 要设置的 totalPageNum
	 */
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	/**
	 * @return totalPageSize
	 */
	public int getTotalPageSize() {
		return totalPageSize;
	}

	/**
	 * @param totalPageSize 要设置的 totalPageSize
	 */
	public void setTotalPageSize(int totalPageSize) {
		if(totalPageSize>0){
			this.totalPageSize=totalPageSize;
			totalPageNum=this.totalPageSize%this.pageSize==0?(this.totalPageSize/pageSize):this.totalPageSize/pageSize+1 ;
		}
		this.totalPageSize = totalPageSize;
	}
	
}
