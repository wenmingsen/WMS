package com.csg.statistics.entity;

/**
 * drt_monitor_fund_day-->DrtMonitorFundDay 日资金监控表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
public class DrtMonitorFundDay{
	
	/** 主键 */
	private String id;

	/** 当前日期：yyyyMMdd */
	private Integer presentDate;

	/** 预购金额（总） */
	private Long poMoney;

	/** 预购人数（总） */
	private Integer poUserCount;

	/** 预购金额（最大） */
	private Long poMoneyMax;

	/** 预购金额（最小） */
	private Long poMoneyMin;

	/** 代扣金额（总） */
	private Long deductMoney;

	/** 代扣人数（总） */
	private Integer deductUserCount;

	/** 代扣金额（最小） */
	private Long deductMoneyMin;

	/** 代扣金额（最大） */
	private Long deductMoneyMax;

	/** 电费余额（总） */
	private Long electricMoney;

	/** 电费人数（总） */
	private Integer electricUserCount;

	/** 电费余额（最大） */
	private Long electricMoneyMax;

	/** 电费余额（最小） */
	private Long electricMoneyMin;

	/** 创建人ID */
	private String creatorId;

	/** 创建人 */
	private String creatorName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 最后修改人ID */
	private String lastModifierId;

	/** 最后修改人 */
	private String lastModifierName;

	/** 最后修改时间 */
	private java.sql.Timestamp lastModifyTime;

	/** 是否删除：0-是 1-否 */
	private Integer isDelete;

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
	 * 当前日期：yyyyMMdd
	 * @param presentDate 设置 presentDate 属性值为参数值 presentDate
	 */
	public void setPresentDate(Integer presentDate) {
		this.presentDate = presentDate;
	}
	/**
	 * 当前日期：yyyyMMdd
     * @return 获取presentDate属性值
     */
	public Integer getPresentDate() {
		return this.presentDate;	
	}
	
	/**
	 * 预购金额（总）
	 * @param poMoney 设置 poMoney 属性值为参数值 poMoney
	 */
	public void setPoMoney(Long poMoney) {
		this.poMoney = poMoney;
	}
	/**
	 * 预购金额（总）
     * @return 获取poMoney属性值
     */
	public Long getPoMoney() {
		return this.poMoney;	
	}
	
	/**
	 * 预购人数（总）
	 * @param poUserCount 设置 poUserCount 属性值为参数值 poUserCount
	 */
	public void setPoUserCount(Integer poUserCount) {
		this.poUserCount = poUserCount;
	}
	/**
	 * 预购人数（总）
     * @return 获取poUserCount属性值
     */
	public Integer getPoUserCount() {
		return this.poUserCount;	
	}
	
	/**
	 * 预购金额（最大）
	 * @param poMoneyMax 设置 poMoneyMax 属性值为参数值 poMoneyMax
	 */
	public void setPoMoneyMax(Long poMoneyMax) {
		this.poMoneyMax = poMoneyMax;
	}
	/**
	 * 预购金额（最大）
     * @return 获取poMoneyMax属性值
     */
	public Long getPoMoneyMax() {
		return this.poMoneyMax;	
	}
	
	/**
	 * 预购金额（最小）
	 * @param poMoneyMin 设置 poMoneyMin 属性值为参数值 poMoneyMin
	 */
	public void setPoMoneyMin(Long poMoneyMin) {
		this.poMoneyMin = poMoneyMin;
	}
	/**
	 * 预购金额（最小）
     * @return 获取poMoneyMin属性值
     */
	public Long getPoMoneyMin() {
		return this.poMoneyMin;	
	}
	
	/**
	 * 代扣金额（总）
	 * @param deductMoney 设置 deductMoney 属性值为参数值 deductMoney
	 */
	public void setDeductMoney(Long deductMoney) {
		this.deductMoney = deductMoney;
	}
	/**
	 * 代扣金额（总）
     * @return 获取deductMoney属性值
     */
	public Long getDeductMoney() {
		return this.deductMoney;	
	}
	
	/**
	 * 代扣人数（总）
	 * @param deductUserCount 设置 deductUserCount 属性值为参数值 deductUserCount
	 */
	public void setDeductUserCount(Integer deductUserCount) {
		this.deductUserCount = deductUserCount;
	}
	/**
	 * 代扣人数（总）
     * @return 获取deductUserCount属性值
     */
	public Integer getDeductUserCount() {
		return this.deductUserCount;	
	}
	
	/**
	 * 代扣金额（最小）
	 * @param deductMoneyMin 设置 deductMoneyMin 属性值为参数值 deductMoneyMin
	 */
	public void setDeductMoneyMin(Long deductMoneyMin) {
		this.deductMoneyMin = deductMoneyMin;
	}
	/**
	 * 代扣金额（最小）
     * @return 获取deductMoneyMin属性值
     */
	public Long getDeductMoneyMin() {
		return this.deductMoneyMin;	
	}
	
	/**
	 * 代扣金额（最大）
	 * @param deductMoneyMax 设置 deductMoneyMax 属性值为参数值 deductMoneyMax
	 */
	public void setDeductMoneyMax(Long deductMoneyMax) {
		this.deductMoneyMax = deductMoneyMax;
	}
	/**
	 * 代扣金额（最大）
     * @return 获取deductMoneyMax属性值
     */
	public Long getDeductMoneyMax() {
		return this.deductMoneyMax;	
	}
	
	/**
	 * 电费余额（总）
	 * @param electricMoney 设置 electricMoney 属性值为参数值 electricMoney
	 */
	public void setElectricMoney(Long electricMoney) {
		this.electricMoney = electricMoney;
	}
	/**
	 * 电费余额（总）
     * @return 获取electricMoney属性值
     */
	public Long getElectricMoney() {
		return this.electricMoney;	
	}
	
	/**
	 * 电费人数（总）
	 * @param electricUserCount 设置 electricUserCount 属性值为参数值 electricUserCount
	 */
	public void setElectricUserCount(Integer electricUserCount) {
		this.electricUserCount = electricUserCount;
	}
	/**
	 * 电费人数（总）
     * @return 获取electricUserCount属性值
     */
	public Integer getElectricUserCount() {
		return this.electricUserCount;	
	}
	
	/**
	 * 电费余额（最大）
	 * @param electricMoneyMax 设置 electricMoneyMax 属性值为参数值 electricMoneyMax
	 */
	public void setElectricMoneyMax(Long electricMoneyMax) {
		this.electricMoneyMax = electricMoneyMax;
	}
	/**
	 * 电费余额（最大）
     * @return 获取electricMoneyMax属性值
     */
	public Long getElectricMoneyMax() {
		return this.electricMoneyMax;	
	}
	
	/**
	 * 电费余额（最小）
	 * @param electricMoneyMin 设置 electricMoneyMin 属性值为参数值 electricMoneyMin
	 */
	public void setElectricMoneyMin(Long electricMoneyMin) {
		this.electricMoneyMin = electricMoneyMin;
	}
	/**
	 * 电费余额（最小）
     * @return 获取electricMoneyMin属性值
     */
	public Long getElectricMoneyMin() {
		return this.electricMoneyMin;	
	}
	
	/**
	 * 创建人ID
	 * @param creatorId 设置 creatorId 属性值为参数值 creatorId
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}
	/**
	 * 创建人ID
     * @return 获取creatorId属性值
     */
	public String getCreatorId() {
		return this.creatorId;	
	}
	
	/**
	 * 创建人
	 * @param creatorName 设置 creatorName 属性值为参数值 creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}
	/**
	 * 创建人
     * @return 获取creatorName属性值
     */
	public String getCreatorName() {
		return this.creatorName;	
	}
	
	/**
	 * 创建时间
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建时间
     * @return 获取createTime属性值
     */
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;	
	}
	
	/**
	 * 最后修改人ID
	 * @param lastModifierId 设置 lastModifierId 属性值为参数值 lastModifierId
	 */
	public void setLastModifierId(String lastModifierId) {
		this.lastModifierId = lastModifierId == null ? null : lastModifierId.trim();
	}
	/**
	 * 最后修改人ID
     * @return 获取lastModifierId属性值
     */
	public String getLastModifierId() {
		return this.lastModifierId;	
	}
	
	/**
	 * 最后修改人
	 * @param lastModifierName 设置 lastModifierName 属性值为参数值 lastModifierName
	 */
	public void setLastModifierName(String lastModifierName) {
		this.lastModifierName = lastModifierName == null ? null : lastModifierName.trim();
	}
	/**
	 * 最后修改人
     * @return 获取lastModifierName属性值
     */
	public String getLastModifierName() {
		return this.lastModifierName;	
	}
	
	/**
	 * 最后修改时间
	 * @param lastModifyTime 设置 lastModifyTime 属性值为参数值 lastModifyTime
	 */
	public void setLastModifyTime(java.sql.Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	/**
	 * 最后修改时间
     * @return 获取lastModifyTime属性值
     */
	public java.sql.Timestamp getLastModifyTime() {
		return this.lastModifyTime;	
	}
	
	/**
	 * 是否删除：0-是 1-否
	 * @param isDelete 设置 isDelete 属性值为参数值 isDelete
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 是否删除：0-是 1-否
     * @return 获取isDelete属性值
     */
	public Integer getIsDelete() {
		return this.isDelete;	
	}
	
	
}
	

	
