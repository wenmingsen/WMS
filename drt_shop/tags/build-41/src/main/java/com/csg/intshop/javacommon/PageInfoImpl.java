package com.csg.intshop.javacommon;

import java.util.HashMap;
import java.util.Map;

import com.csg.intshop.javacommon.page.PageInfo;


public class PageInfoImpl implements PageInfo {

	private Map filters;

	private int pageNumber;

	private int pageSize;

	private String sortingColumn;

	private String sortingDirection;
	
	public PageInfoImpl() {
	}
	
	public PageInfoImpl(int pageNumber, int pageSize, Map filters) {
		this(pageNumber,pageSize,filters,null,null);
	}
	
	public PageInfoImpl(int pageNumber, int pageSize, Map filters, String sortingColumn, String sortingDirection) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.filters = filters;
		this.sortingColumn = sortingColumn;
		this.sortingDirection = sortingDirection;
	}

	public Map getFilters() {
		if(filters == null) {
			filters = new HashMap(0);
		}
		return filters;
	}

	public void setFilters(Map filters) {
		this.filters = filters;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortingColumn() {
		return sortingColumn;
	}

	public void setSortingColumn(String sortingColumn) {
		this.sortingColumn = sortingColumn;
	}

	public String getSortingDirection() {
		return sortingDirection;
	}

	public void setSortingDirection(String sortingDirection) {
		this.sortingDirection = sortingDirection;
	}

//	public static PageInfo generateFrom(HttpServletRequest request)  {
//		Map parameters = RequestUtils.getParameters(request);
//		return generateFrom(parameters);
//	}
//
//	public static PageInfo generateFrom(HttpServletRequest request,String defaultSortingColumn,String defaultSortingDirection)  {
//		Map parameters = RequestUtils.getParameters(request);
//		return generateFrom(parameters,defaultSortingColumn,defaultSortingDirection);
//	}
//	
//	public static PageInfo generateFrom(Map parameters) {
//		return generateFrom(parameters, null, null);
//	}
//	
//	public static PageInfo generateFrom(Map parameters,String defaultSortingColumn,String defaultSortingDirection) {
//		PageInfoImpl result = new PageInfoImpl();
//		try {
//			BeanUtils.populate(result,parameters);
//			
//			String sortingColumn = StringUtils.emptyValue(result.getSortingColumn(),defaultSortingColumn); 
//			result.setSortingColumn(sortingColumn);
//			String sortingDirection = StringUtils.emptyValue(result.getSortingDirection(),defaultSortingDirection);
//			result.setSortingDirection(sortingDirection);
//			
//			result.setFilters(parameters);
//		}catch(Exception ex) {
//			ReflectionUtils.handleReflectionException(ex);
//		}
//		return result;
//	}
	
}
