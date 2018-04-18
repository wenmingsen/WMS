package com.csg.intshop.javacommon;

import java.util.HashMap;
import java.util.Map;

import com.csg.intshop.javacommon.page.PageInfo;


public class PageInfoFactory {
	
	/** 获取指定行数和页码的PageInfo */
	public static PageInfo createFromFilters(int intPageSize,int intPageNumber, String defaultSortColumn, String defaultSortDirection){
		
		PageInfoImpl result = new PageInfoImpl();
		
		// 设置基本参数
		result.setPageSize(intPageSize);
		result.setSortingColumn(defaultSortColumn);
		result.setSortingDirection(defaultSortDirection);
		if (intPageNumber>0)
			result.setPageNumber(intPageNumber);
		
		// 设置过滤条件
		Map<String, Comparable> resultFilters = new HashMap<String, Comparable>();
		result.setFilters(resultFilters);
		
		return result;
	}
	
	/** 获取指定行数的PageInfo */
	public static PageInfo createFromFilters(int intPageSize, String defaultSortColumn, String defaultSortDirection){
		
		return createFromFilters(intPageSize, 0, defaultSortColumn, defaultSortDirection);
	}
	
	/** 获取全部数据的PageInfo */
	public static PageInfo createFromFiltersAllRows(String defaultSortColumn, String defaultSortDirection ){
		
		return createFromFilters(Integer.MAX_VALUE, defaultSortColumn, defaultSortDirection) ;
	}
	
}
