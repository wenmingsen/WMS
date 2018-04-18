package com.csg.intshop.entity;

/**
 * drt_shop_order_detail-->DrtShopOrderDetail 积分商城订单子表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public class DrtShopOrderDetail{
	
	/** 主键 */
	private String id;

	/** 父订单ID */
	private String orderId;

	/** 商品ID */
	private String itemId;

	/** 订单状态 */
	private Integer orderState;

	/** 订单积分 */
	private Integer orderEarnings;

	/** 创建人ID */
	private String creatorId;

	/** 创建人名称 */
	private String creatorName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 修改者ID */
	private String modifyId;

	/** 修改者名称 */
	private String modifyName;

	/** 修改时间 */
	private java.sql.Timestamp modifyTime;

	/** 商品数量 */
	private Integer itemNum;

	/** 商品积分 */
	private Integer itemEarnings;

	/** 备注 */
	private String remark;

	/** 手机号码 */
	private String phone;

	/** 收货人名称 */
	private String name;

	/** 邮政编码 */
	private String postcode;

	/**商品对象*/
	private DrtShopItem drtShopItem;
	
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
	 * 父订单ID
	 * @param orderId 设置 orderId 属性值为参数值 orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}
	/**
	 * 父订单ID
     * @return 获取orderId属性值
     */
	public String getOrderId() {
		return this.orderId;	
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
	 * 订单状态
	 * @param orderState 设置 orderState 属性值为参数值 orderState
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * 订单状态
     * @return 获取orderState属性值
     */
	public Integer getOrderState() {
		return this.orderState;	
	}
	
	/**
	 * 订单积分
	 * @param orderEarnings 设置 orderEarnings 属性值为参数值 orderEarnings
	 */
	public void setOrderEarnings(Integer orderEarnings) {
		this.orderEarnings = orderEarnings;
	}
	/**
	 * 订单积分
     * @return 获取orderEarnings属性值
     */
	public Integer getOrderEarnings() {
		return this.orderEarnings;	
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
	 * 创建人名称
	 * @param creatorName 设置 creatorName 属性值为参数值 creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}
	/**
	 * 创建人名称
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
	 * 修改者ID
	 * @param modifyId 设置 modifyId 属性值为参数值 modifyId
	 */
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId == null ? null : modifyId.trim();
	}
	/**
	 * 修改者ID
     * @return 获取modifyId属性值
     */
	public String getModifyId() {
		return this.modifyId;	
	}
	
	/**
	 * 修改者名称
	 * @param modifyName 设置 modifyName 属性值为参数值 modifyName
	 */
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName == null ? null : modifyName.trim();
	}
	/**
	 * 修改者名称
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
	
	/**
	 * 手机号码
	 * @param phone 设置 phone 属性值为参数值 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}
	/**
	 * 手机号码
     * @return 获取phone属性值
     */
	public String getPhone() {
		return this.phone;	
	}
	
	/**
	 * 收货人名称
	 * @param name 设置 name 属性值为参数值 name
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	/**
	 * 收货人名称
     * @return 获取name属性值
     */
	public String getName() {
		return this.name;	
	}
	
	/**
	 * 邮政编码
	 * @param postcode 设置 postcode 属性值为参数值 postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? null : postcode.trim();
	}
	/**
	 * 邮政编码
     * @return 获取postcode属性值
     */
	public String getPostcode() {
		return this.postcode;	
	}
	/**
	 * @return drtShopItem
	 */
	public DrtShopItem getDrtShopItem() {
		return drtShopItem;
	}
	/**
	 * @param drtShopItem 要设置的 drtShopItem
	 */
	public void setDrtShopItem(DrtShopItem drtShopItem) {
		this.drtShopItem = drtShopItem;
	}
}
	

	
