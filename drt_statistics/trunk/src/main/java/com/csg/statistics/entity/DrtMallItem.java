package com.csg.statistics.entity;

/**
 * drt_mall_item-->DrtMallItem 积分商城商品池
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月24日 温明森
 */
public class DrtMallItem{
	
	/** 主键 */
	private String id;

	/** 排序编号 */
	private Integer sortNum;

	/** 商品SKU编号 */
	private String skuCode;

	/** 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3 */
	private Integer itemState;

	/** 兑换量 */
	private Integer exchangeRate;

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
	 * 排序编号
	 * @param sortNum 设置 sortNum 属性值为参数值 sortNum
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	/**
	 * 排序编号
     * @return 获取sortNum属性值
     */
	public Integer getSortNum() {
		return this.sortNum;	
	}
	
	/**
	 * 商品SKU编号
	 * @param skuCode 设置 skuCode 属性值为参数值 skuCode
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode == null ? null : skuCode.trim();
	}
	/**
	 * 商品SKU编号
     * @return 获取skuCode属性值
     */
	public String getSkuCode() {
		return this.skuCode;	
	}
	
	/**
	 * 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
	 * @param itemState 设置 itemState 属性值为参数值 itemState
	 */
	public void setItemState(Integer itemState) {
		this.itemState = itemState;
	}
	/**
	 * 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
     * @return 获取itemState属性值
     */
	public Integer getItemState() {
		return this.itemState;	
	}
	
	/**
	 * 兑换量
	 * @param exchangeRate 设置 exchangeRate 属性值为参数值 exchangeRate
	 */
	public void setExchangeRate(Integer exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	/**
	 * 兑换量
     * @return 获取exchangeRate属性值
     */
	public Integer getExchangeRate() {
		return this.exchangeRate;	
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
	

	
