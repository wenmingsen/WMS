package com.csg.intshop.entity;

/**
 * drt_mall_cart-->DrtMallCart 积分商城购物车表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月23日 曾令鹏
 */
public class DrtMallCart{
	
	/** 主键 */
	private String id;

	/** 电融通用户ID */
	private String accountId;

	/** 积分商城商品ID */
	private String itemId;

	/** 商品数量 */
	private Integer itemNum;

	/** 创建人ID */
	private String creatorId;

	/** 创建人姓名 */
	private String creatorName;

	/** 创建时间：yyyyMMddHHmmss */
	private Long createTime;

	/** 最后修改人ID */
	private String lastModifierId;

	/** 最后修改人 */
	private String lastModifierName;

	/** 最后修改时间：yyyyMMddHHmmss */
	private Long lastModifierTime;

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
	 * 电融通用户ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 电融通用户ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 积分商城商品ID
	 * @param itemId 设置 itemId 属性值为参数值 itemId
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}
	/**
	 * 积分商城商品ID
     * @return 获取itemId属性值
     */
	public String getItemId() {
		return this.itemId;	
	}
	
	/**
	 * 商品数量
	 * @param itemNum 设置 itemNum 属性值为参数值 itemNum
	 */
	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}
	/**
	 * 商品数量
     * @return 获取itemNum属性值
     */
	public Integer getItemNum() {
		return this.itemNum;	
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
	 * 创建人姓名
	 * @param creatorName 设置 creatorName 属性值为参数值 creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}
	/**
	 * 创建人姓名
     * @return 获取creatorName属性值
     */
	public String getCreatorName() {
		return this.creatorName;	
	}
	
	/**
	 * 创建时间：yyyyMMddHHmmss
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建时间：yyyyMMddHHmmss
     * @return 获取createTime属性值
     */
	public Long getCreateTime() {
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
	 * 最后修改时间：yyyyMMddHHmmss
	 * @param lastModifierTime 设置 lastModifierTime 属性值为参数值 lastModifierTime
	 */
	public void setLastModifierTime(Long lastModifierTime) {
		this.lastModifierTime = lastModifierTime;
	}
	/**
	 * 最后修改时间：yyyyMMddHHmmss
     * @return 获取lastModifierTime属性值
     */
	public Long getLastModifierTime() {
		return this.lastModifierTime;	
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
	

	
