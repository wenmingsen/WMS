package com.csg.statistics.entity;

/**
 * drt_monitor_behavior_record-->DrtMonitorBehaviorRecord 用户行为记录表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
public class DrtMonitorBehaviorRecord{
	
	/** 主键 */
	private String id;

	/** 用户ID */
	private String accountId;

	/** 用户行为类型：1.注册，2.绑定用户号3.绑定银行卡4.开通预购电费 */
	private Integer behaviorType;

	/** 用户行为时间：yyyyMMddHHmmss */
	private Long behaviorTime;

	/**
	 * 主键
	 * @param id 设置 id 属性值为参数值 id
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	/**
	 * 主键
     * @return 获取id属性值
     */
	public String getId() {
		return this.id;	
	}
	
	/**
	 * 用户ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 用户ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 用户行为类型：1.注册，2.绑定用户号3.绑定银行卡4.开通预购电费
	 * @param behaviorType 设置 behaviorType 属性值为参数值 behaviorType
	 */
	public void setBehaviorType(Integer behaviorType) {
		this.behaviorType = behaviorType;
	}
	/**
	 * 用户行为类型：1.注册，2.绑定用户号3.绑定银行卡4.开通预购电费
     * @return 获取behaviorType属性值
     */
	public Integer getBehaviorType() {
		return this.behaviorType;	
	}
	
	/**
	 * 用户行为时间：yyyyMMddHHmmss
	 * @param behaviorTime 设置 behaviorTime 属性值为参数值 behaviorTime
	 */
	public void setBehaviorTime(Long behaviorTime) {
		this.behaviorTime = behaviorTime;
	}
	/**
	 * 用户行为时间：yyyyMMddHHmmss
     * @return 获取behaviorTime属性值
     */
	public Long getBehaviorTime() {
		return this.behaviorTime;	
	}
	
	
}
	

	
