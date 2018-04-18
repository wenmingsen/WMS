package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtMallOrderDetail;

/**
 * DrtMallOrderDetailMapper 积分商城订单明细表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月23日 曾令鹏
 */
public interface DrtMallOrderDetailMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 */
	void insert(DrtMallOrderDetail drtMallOrderDetail);

	/**
	 * 批量保存
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 */
	void insertList(List<DrtMallOrderDetail> drtMallOrderDetailList);

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 积分商城订单明细表 主键ID
	 */
	void delete(String id);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 */
	void update(DrtMallOrderDetail drtMallOrderDetail);

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 */
	void updateIfNotNull(DrtMallOrderDetail drtMallOrderDetail);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单明细表 主键ID
	 * @return 积分商城订单明细表 单条记录
	 */
	DrtMallOrderDetail selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 * @return 积分商城订单明细表 记录集
	 */
	List<DrtMallOrderDetail> selectList(DrtMallOrderDetail drtMallOrderDetail);

}