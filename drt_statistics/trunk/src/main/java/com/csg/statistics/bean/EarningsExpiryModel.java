package com.csg.statistics.bean;

import com.csg.statistics.entity.DrtFinAllEarningsRecordHistory;

/**
 * 积分到期查询模型
 *
 * @author xuchen
 * @since 1.8
 * @version 2018年3月1日 xuchen
 */
public class EarningsExpiryModel extends DrtFinAllEarningsRecordHistory{

	/** 开始日期 */
	private Integer startDate;

	/** 结束日期 */
	private Integer endDate;

	/**
	 * 开始日期 
	 * 
	 * @return 获取startDate属性值
	 */
	public Integer getStartDate() {
		return startDate;
	}

	/**
	 * 开始日期 
	 * 
	 * @param startDate
	 *            设置 startDate 属性值为参数值 startDate
	 */
	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}
	/**
	 * 结束日期 
	 * 
	 * @return 获取endDate属性值
	 */
	public Integer getEndDate() {
		return endDate;
	}

	/**
	 * 结束日期 
	 * 
	 * @param endDate
	 *            设置 endDate 属性值为参数值 endDate
	 */
	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}
}
