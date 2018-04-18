package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtShopAddress;


/**
 * DrtShopAddressMapper 积分商城地址表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public interface DrtShopAddressMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	int insert(DrtShopAddress drtShopAddress);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	int update(DrtShopAddress drtShopAddress);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城地址表 主键ID
	 * @return 积分商城地址表 单条记录
	 */
	DrtShopAddress selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopAddress 积分商城地址表
	 * @return 积分商城地址表 记录集
	 */
	List<DrtShopAddress> selectAddressList(DrtShopAddress drtShopAddress);

	/**
	 * 删除地址
	 * @param drtShopAddress 选择地址id
	 * @return 删除结果
	 */
	void deleteAddress(DrtShopAddress drtShopAddress);

}