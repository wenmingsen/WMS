package com.csg.statistics.entity;

public class DrtOprPointsRuleBean {

	/** 预购收益计算金额 */
	private Long poEarningsComputingMoney;
	
	/** 积分规则类型（1.余额积分 2.交费积分 3.推广积分） */
	private String pointsRuleType; 
	
	/** 状态（0.停用 1.启用） */
	private String status;
	
	/** 是否删除,0是，1否 */
	private String isDelete;
	
	/**
	 * 预购收益计算金额
	 * @param poEarningsComputingMoney 设置 poEarningsComputingMoney 属性值为参数值 poEarningsComputingMoney
	 */
	public void setPoEarningsComputingMoney(Long poEarningsComputingMoney) {
		this.poEarningsComputingMoney = poEarningsComputingMoney;
	}
	/**
	 * 预购收益计算金额
     * @return 获取poEarningsComputingMoney属性值
     */
	public Long getPoEarningsComputingMoney() {
		return this.poEarningsComputingMoney;	
	}
	
	/**
	 * 积分规则类型（1.余额积分 2.交费积分 3.推广积分）
	 * @param pointsRuleType 设置 pointsRuleType 属性值为参数值 pointsRuleType
	 */
	public void setPointsRuleType(String pointsRuleType) {
		this.pointsRuleType = pointsRuleType == null ? null : pointsRuleType.trim();
	}
	/**
	 * 积分规则类型（1.余额积分 2.交费积分 3.推广积分）
     * @return 获取pointsRuleType属性值
     */
	public String getPointsRuleType() {
		return this.pointsRuleType;	
	}
	
	/**
	 * 状态（0.停用 1.启用）
	 * @param status 设置 status 属性值为参数值 status
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	/**
	 * 状态（0.停用 1.启用）
     * @return 获取status属性值
     */
	public String getStatus() {
		return this.status;	
	}
	
	/**
	 * 是否删除,0是，1否
	 * @param isDelete 设置 isDelete 属性值为参数值 isDelete
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}
	/**
	 * 是否删除,0是，1否
     * @return 获取isDelete属性值
     */
	public String getIsDelete() {
		return this.isDelete;	
	}
	
	
}
