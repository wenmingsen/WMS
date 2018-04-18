package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtShopItem;

/**
 * DrtShopItemMapper 积分商城商品表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public interface DrtShopItemMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtShopItem 积分商城商品表
	 */
	void insert(DrtShopItem drtShopItem);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopItem 积分商城商品表
	 */
	void update(DrtShopItem drtShopItem);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城商品表 主键ID
	 * @return 积分商城商品表 单条记录
	 */
	DrtShopItem selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopItem 积分商城商品表
	 * @return 积分商城商品表 记录集
	 */
	List<DrtShopItem> selectList(DrtShopItem drtShopItem);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopItem 积分商城商品表
	 * @return 积分商城商品表 记录集
	 */
	List<DrtShopItem> seleItems(DrtShopItem drtShopItem);
	
	
	
	/**获取商品品种总数*/
	Integer selCount(DrtShopItem drtShopItem);
}