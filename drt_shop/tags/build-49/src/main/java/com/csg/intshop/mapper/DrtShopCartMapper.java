package com.csg.intshop.mapper;

import java.util.List;
import java.util.Map;

import com.csg.intshop.entity.DrtShopCart;
import com.csg.intshop.entity.DrtShopItem;

/**
 * DrtShopCartMapper 积分商城购物车表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public interface DrtShopCartMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtShopCart 积分商城购物车表
	 */
	int insert(DrtShopCart drtShopCart);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopCart 积分商城购物车表
	 */
	int update(DrtShopCart drtShopCart);
	
	/**
	 * 统计商品种类数量
	 * @return
	 */
	int countGoods(String accountId);
	
	/**
	 * 根据itemId修改
	 * @param drtShopCart
	 * @return
	 */
	int updateByItemId(DrtShopCart drtShopCart);
	
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城购物车表 主键ID
	 * @return 积分商城购物车表 单条记录
	 */
	DrtShopCart selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopCart 积分商城购物车表
	 * @return 积分商城购物车表 记录集
	 */
	List<DrtShopCart> selectList(DrtShopCart drtShopCart);
	
	/**
	 * 查询当前用户购物车商品
	 * 
	 * @param 用户id
	 * @return  当前用户购物车商品
	 * */
	List<DrtShopItem> selectShopCars(String accountId);


	/**
	 * 根据itemId查询
	 * @param itemId 商品id
	 * @return 返回该商品
	 */
	DrtShopCart queryItemId(String itemId);
	/**
	 * 删除购物车商品
	 * 
	 * @param ids 商品id集合
	 * */
	void delDrtShop(Map<String,Object> params);

	/**
	 * 清空购物车
	 * @param accountId 用户Id
	 */
	void clearShopCart(String accountId);
}