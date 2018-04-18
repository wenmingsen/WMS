package com.csg.intshop.entity;

/**
 * drt_shop_item-->DrtShopItem 积分商城商品表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public class DrtShopItem{
	
	/** 主键 */
	private String id;

	/** 商品名称 */
	private String itemName;

	/** 商品单价（分） */
	private Integer itemPrice;

	/** 商品积分 */
	private Integer itemEarnings;

	/** 商品描述 */
	private String itemText;

	/** 商品图片 */
	private String itemPicture;

	/** 创建人ID */
	private String creatorId;

	/** 创建人姓名 */
	private String creatorName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 商品状态 */
	private Integer itemState;

	/** 排序字段 */
	private String sort;

	/** 商品编码 */
	private String itemNo;

	/** 备注 */
	private String remark;
	
	/**数量*/
	private Integer itemNum;

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
	 * 商品名称
	 * @param itemName 设置 itemName 属性值为参数值 itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}
	/**
	 * 商品名称
     * @return 获取itemName属性值
     */
	public String getItemName() {
		return this.itemName;	
	}
	
	/**
	 * 商品单价（分）
	 * @param itemPrice 设置 itemPrice 属性值为参数值 itemPrice
	 */
	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * 商品单价（分）
     * @return 获取itemPrice属性值
     */
	public Integer getItemPrice() {
		return this.itemPrice;	
	}
	
	/**
	 * 商品积分
	 * @param itemEarnings 设置 itemEarnings 属性值为参数值 itemEarnings
	 */
	public void setItemEarnings(Integer itemEarnings) {
		this.itemEarnings = itemEarnings;
	}
	/**
	 * 商品积分
     * @return 获取itemEarnings属性值
     */
	public Integer getItemEarnings() {
		return this.itemEarnings;	
	}
	
	/**
	 * 商品描述
	 * @param itemText 设置 itemText 属性值为参数值 itemText
	 */
	public void setItemText(String itemText) {
		this.itemText = itemText == null ? null : itemText.trim();
	}
	/**
	 * 商品描述
     * @return 获取itemText属性值
     */
	public String getItemText() {
		return this.itemText;	
	}
	
	/**
	 * 商品图片
	 * @param itemPicture 设置 itemPicture 属性值为参数值 itemPicture
	 */
	public void setItemPicture(String itemPicture) {
		this.itemPicture = itemPicture == null ? null : itemPicture.trim();
	}
	/**
	 * 商品图片
     * @return 获取itemPicture属性值
     */
	public String getItemPicture() {
		return this.itemPicture;	
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
	 * 商品状态
	 * @param itemState 设置 itemState 属性值为参数值 itemState
	 */
	public void setItemState(Integer itemState) {
		this.itemState = itemState;
	}
	/**
	 * 商品状态
     * @return 获取itemState属性值
     */
	public Integer getItemState() {
		return this.itemState;	
	}
	
	/**
	 * 排序字段
	 * @param sort 设置 sort 属性值为参数值 sort
	 */
	public void setSort(String sort) {
		this.sort = sort == null ? null : sort.trim();
	}
	/**
	 * 排序字段
     * @return 获取sort属性值
     */
	public String getSort() {
		return this.sort;	
	}
	
	/**
	 * 商品编码
	 * @param itemNo 设置 itemNo 属性值为参数值 itemNo
	 */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo == null ? null : itemNo.trim();
	}
	/**
	 * 商品编码
     * @return 获取itemNo属性值
     */
	public String getItemNo() {
		return this.itemNo;	
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
	public Integer getItemNum() {
		return itemNum;
	}
	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}
	
	
}
	

	
