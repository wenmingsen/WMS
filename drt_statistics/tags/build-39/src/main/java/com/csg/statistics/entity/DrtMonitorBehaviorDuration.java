package com.csg.statistics.entity;

/**
 * drt_monitor_behavior_duration-->DrtMonitorBehaviorDuration 用户行为间隔记录表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月20日 温明森
 */
public class DrtMonitorBehaviorDuration{
	
	/** 主键 */
	private String id;

	/** 用户ID */
	private String accountId;

	/** 行为区间类型：1.注册-绑定用电户号2.绑定用电户号-绑定银行卡3.绑定银行卡-预购电费 */
	private Integer behaviorSection;

	/** 行为区间类型：1. 1-5天 2. 5-15天 3. 15-30天 4. 30天以上 */
	private Integer durationSection;

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
	 * 行为区间类型：1.注册-绑定用电户号2.绑定用电户号-绑定银行卡3.绑定银行卡-预购电费
	 * @param behaviorSection 设置 behaviorSection 属性值为参数值 behaviorSection
	 */
	public void setBehaviorSection(Integer behaviorSection) {
		this.behaviorSection = behaviorSection;
	}
	/**
	 * 行为区间类型：1.注册-绑定用电户号2.绑定用电户号-绑定银行卡3.绑定银行卡-预购电费
     * @return 获取behaviorSection属性值
     */
	public Integer getBehaviorSection() {
		return this.behaviorSection;	
	}
	
	/**
	 * 行为区间类型：1. 1-4天 2. 5-15天 3. 15-30天 4. 30天以上
	 * @param durationSection 设置 durationSection 属性值为参数值 durationSection
	 */
	public void setDurationSection(Integer durationSection) {
		this.durationSection = durationSection;
	}
	/**
	 * 行为区间类型：1. 1-4天 2. 5-15天 3. 15-30天 4. 30天以上
     * @return 获取durationSection属性值
     */
	public Integer getDurationSection() {
		return this.durationSection;	
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
	

	
