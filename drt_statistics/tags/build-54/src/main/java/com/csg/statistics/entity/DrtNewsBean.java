package com.csg.statistics.entity;

/**
 * 用户登录记录表-查询实体
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月19日 温明森
 */
public class DrtNewsBean extends DrtNews {

	/** 开始日期：yyyyMMdd */
	private Integer presentDateStart;
	
	/** 结束日期：yyyyMMdd */
	private Integer presentDateEnd;
	
	/**
	 * 开始日期：yyyyMMdd
	 * @return 获取presentDateEnd属性值
	 */
	public Integer getPresentDateStart() {
		return presentDateStart;
	}
	
	/**
	 * 开始日期：yyyyMMdd
	 * @param presentDate 设置 presentDateStart 属性值为参数值 presentDateStart
	 */
	public void setPresentDateStart(Integer presentDateStart) {
		this.presentDateStart = presentDateStart;
	}
	/**
	 * 结束日期：yyyyMMdd
	 * @return 获取presentDateEnd属性值
	 */
	public Integer getPresentDateEnd() {
		return presentDateEnd;
	}
	/**
	 * 结束日期：yyyyMMdd
	 * @param presentDateEnd 设置 presentDateEnd 属性值为参数值 presentDateEnd
	 */
	public void setPresentDateEnd(Integer presentDateEnd) {
		this.presentDateEnd = presentDateEnd;
	}
	
	
	
}
