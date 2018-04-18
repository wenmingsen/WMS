package com.csg.intshop.javacommon.page;

import java.util.Map;
/**
 * 分页信息的接口
 */
public interface PageInfo {
	/**
	 * 得到排序的方向
	 * @return ASC 与 DESC
	 */
	public String getSortingDirection();
	/**
	 * 得到排序的列字段
	 * @return
	 */
	public String getSortingColumn();
	/**
	 * 得到过滤参数
	 * @return
	 */
	public Map getFilters();
	/**
	 * 得到页号码
	 * @return
	 */
	public int getPageNumber();
	/**
	 * 得到分页大小
	 * @return
	 */
	public int getPageSize();
	
}
