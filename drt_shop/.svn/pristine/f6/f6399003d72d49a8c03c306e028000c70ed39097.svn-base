package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtMallOrder;

/**
 * DrtMallOrderMapper 积分商城订单主表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月23日 曾令鹏
 */
public interface DrtMallOrderMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMallOrder 积分商城订单主表
	 */
	void insert(DrtMallOrder drtMallOrder);

	/**
	 * 批量保存
	 * 
	 * @param drtMallOrder 积分商城订单主表
	 */
	void insertList(List<DrtMallOrder> drtMallOrderList);

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 积分商城订单主表 主键ID
	 */
	void delete(String id);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallOrder 积分商城订单主表
	 */
	void update(DrtMallOrder drtMallOrder);

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallOrder 积分商城订单主表
	 */
	void updateIfNotNull(DrtMallOrder drtMallOrder);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单主表 主键ID
	 * @return 积分商城订单主表 单条记录
	 */
	DrtMallOrder selectByPrimaryKey(String id);

	/**
	 * 通过主键获取记录数
	 * 
	 * @param ids 积分商城订单主表 主键ID
	 * @return 积分商城订单主表 记录数
	 */
	List<DrtMallOrder> selectByPrimaryKeys(String ids);
	
	/**
	 * 通过主键获取记录数
	 * 
	 * @param ids 积分商城订单主表 主键orderCode
	 * @return 积分商城订单主表 记录数
	 */
	List<DrtMallOrder> selectByOrderCodes(String orderCodes);
	
	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallOrder 积分商城订单主表
	 * @return 积分商城订单主表 记录集
	 */
	List<DrtMallOrder> selectList(DrtMallOrder drtMallOrder);

	/**
	 * 积分商城接口查询用
	 * 
	 * @param drtMallOrder 积分商城订单主表
	 * @return 积分商城订单主表 记录集
	 */
	List<DrtMallOrder> selectListByinterface(DrtMallOrder drtMallOrder);
}