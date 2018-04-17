package com.csg.statistics.entity;

/**
 * drt_fin_earnings_statistics-->DrtFinEarningsStatistics 分类收益统计表
 *
 * @author  xuchen
 * @since   1.8
 * @version 2018年02月08日 xuchen
 */
public class DrtFinEarningsStatistics{
	
	/** 主键ID */
	private String id;

	/** 电融通账户id */
	private String accountId;

	/** 动态收益 */
	private Integer dynamicEarnings;

	/** 交电费收益 */
	private Integer payElecEarnings;

	/** 活动奖励 */
	private Integer activityEarnings;

	/** 商城兑换积分 */
	private Integer exchangeEarnings;

	/** 异常处理 */
	private Integer exceptionEarnings;

	/** 总积分统计 */
	private Integer totalEarnings;

	/** 更新时间 */
	private java.sql.Timestamp updateTime;

	/**
	 * 主键ID
	 * @param id 设置 id 属性值为参数值 id
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	/**
	 * 主键ID
     * @return 获取id属性值
     */
	public String getId() {
		return this.id;	
	}
	
	/**
	 * 电融通账户id
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 电融通账户id
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 动态收益
	 * @param dynamicEarnings 设置 dynamicEarnings 属性值为参数值 dynamicEarnings
	 */
	public void setDynamicEarnings(Integer dynamicEarnings) {
		this.dynamicEarnings = dynamicEarnings;
	}
	/**
	 * 动态收益
     * @return 获取dynamicEarnings属性值
     */
	public Integer getDynamicEarnings() {
		return this.dynamicEarnings;	
	}
	
	/**
	 * 交电费收益
	 * @param payElecEarnings 设置 payElecEarnings 属性值为参数值 payElecEarnings
	 */
	public void setPayElecEarnings(Integer payElecEarnings) {
		this.payElecEarnings = payElecEarnings;
	}
	/**
	 * 交电费收益
     * @return 获取payElecEarnings属性值
     */
	public Integer getPayElecEarnings() {
		return this.payElecEarnings;	
	}
	
	/**
	 * 活动奖励
	 * @param activityEarnings 设置 activityEarnings 属性值为参数值 activityEarnings
	 */
	public void setActivityEarnings(Integer activityEarnings) {
		this.activityEarnings = activityEarnings;
	}
	/**
	 * 活动奖励
     * @return 获取activityEarnings属性值
     */
	public Integer getActivityEarnings() {
		return this.activityEarnings;	
	}
	
	/**
	 * 商城兑换积分
	 * @param exchangeEarnings 设置 exchangeEarnings 属性值为参数值 exchangeEarnings
	 */
	public void setExchangeEarnings(Integer exchangeEarnings) {
		this.exchangeEarnings = exchangeEarnings;
	}
	/**
	 * 商城兑换积分
     * @return 获取exchangeEarnings属性值
     */
	public Integer getExchangeEarnings() {
		return this.exchangeEarnings;	
	}
	
	/**
	 * 异常处理
	 * @param exceptionEarnings 设置 exceptionEarnings 属性值为参数值 exceptionEarnings
	 */
	public void setExceptionEarnings(Integer exceptionEarnings) {
		this.exceptionEarnings = exceptionEarnings;
	}
	/**
	 * 异常处理
     * @return 获取exceptionEarnings属性值
     */
	public Integer getExceptionEarnings() {
		return this.exceptionEarnings;	
	}
	
	/**
	 * 总积分统计
	 * @param totalEarnings 设置 totalEarnings 属性值为参数值 totalEarnings
	 */
	public void setTotalEarnings(Integer totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	/**
	 * 总积分统计
     * @return 获取totalEarnings属性值
     */
	public Integer getTotalEarnings() {
		return this.totalEarnings;	
	}
	
	/**
	 * 更新时间
	 * @param updateTime 设置 updateTime 属性值为参数值 updateTime
	 */
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 更新时间
     * @return 获取updateTime属性值
     */
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;	
	}
	
	
}
	

	
