package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtMallCart;

/**
 * DrtMallCartMapper 积分商城购物车表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月23日 曾令鹏
 */
public interface DrtMallCartMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMallCart 积分商城购物车表
	 */
	void insert(DrtMallCart drtMallCart);

	/**
	 * 批量保存
	 * 
	 * @param drtMallCart 积分商城购物车表
	 */
	void insertList(List<DrtMallCart> drtMallCartList);

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 积分商城购物车表 主键ID
	 */
	void delete(String id);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallCart 积分商城购物车表
	 */
	void update(DrtMallCart drtMallCart);

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallCart 积分商城购物车表
	 */
	void updateIfNotNull(DrtMallCart drtMallCart);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城购物车表 主键ID
	 * @return 积分商城购物车表 单条记录
	 */
	DrtMallCart selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallCart 积分商城购物车表
	 * @return 积分商城购物车表 记录集
	 */
	List<DrtMallCart> selectList(DrtMallCart drtMallCart);

}