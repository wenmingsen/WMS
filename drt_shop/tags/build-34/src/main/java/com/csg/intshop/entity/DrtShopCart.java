package com.csg.intshop.entity;

/**
 * drt_shop_cart-->DrtShopCart 积分商城购物车表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public class DrtShopCart{
	
	/** 主键 */
	private String id;

	/** 用户ID */
	private String accountId;

	/** 商品ID */
	private String itemId;

	/** 商品数量 */
	private Integer itemNum;

	/** 创建人ID */
	private String creatorId;

	/** 创建人姓名 */
	private String creatorName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 修改人ID */
	private String modifyId;

	/** 修改人姓名 */
	private String modifyName;

	/** 修改时间 */
	private java.sql.Timestamp modifyTime;

	/** 备注 */
	private String remark;

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
	 * 商品ID
	 * @param itemId 设置 itemId 属性值为参数值 itemId
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}
	/**
	 * 商品ID
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
	 * 修改人ID
	 * @param modifyId 设置 modifyId 属性值为参数值 modifyId
	 */
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId == null ? null : modifyId.trim();
	}
	/**
	 * 修改人ID
     * @return 获取modifyId属性值
     */
	public String getModifyId() {
		return this.modifyId;	
	}
	
	/**
	 * 修改人姓名
	 * @param modifyName 设置 modifyName 属性值为参数值 modifyName
	 */
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName == null ? null : modifyName.trim();
	}
	/**
	 * 修改人姓名
     * @return 获取modifyName属性值
     */
	public String getModifyName() {
		return this.modifyName;	
	}
	
	/**
	 * 修改时间
	 * @param modifyTime 设置 modifyTime 属性值为参数值 modifyTime
	 */
	public void setModifyTime(java.sql.Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 修改时间
     * @return 获取modifyTime属性值
     */
	public java.sql.Timestamp getModifyTime() {
		return this.modifyTime;	
	}
	
	/**
	 * 备注
	 * @param remark 设置 remark 属性值为参数值 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
	/**
	 * 备注
     * @return 获取remark属性值
     */
	public String getRemark() {
		return this.remark;	
	}
	
	
}
	

	
