package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtShopConfig;

/**
 * DrtShopConfigMapper 积分商城配置表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2018年01月25日 徐辰
 */
public interface DrtShopConfigMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtShopConfig 积分商城配置表
	 */
	void insert(DrtShopConfig drtShopConfig);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopConfig 积分商城配置表
	 */
	void update(DrtShopConfig drtShopConfig);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城配置表 主键ID
	 * @return 积分商城配置表 单条记录
	 */
	DrtShopConfig selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopConfig 积分商城配置表
	 * @return 积分商城配置表 记录集
	 */
	List<DrtShopConfig> selectList(DrtShopConfig drtShopConfig);

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopConfig 积分商城配置表
	 */
	void updateIfNotNull(DrtShopConfig drtShopConfig);
}