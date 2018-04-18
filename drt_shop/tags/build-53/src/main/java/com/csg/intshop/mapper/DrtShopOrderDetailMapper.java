package com.csg.intshop.mapper;

import java.util.List;
import java.util.Map;

import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.entity.DrtShopOrderDetail;

/**
 * DrtShopOrderDetailMapper 积分商城订单子表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public interface DrtShopOrderDetailMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 */
	void insert(DrtShopOrderDetail drtShopOrderDetail);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 */
	void update(DrtShopOrderDetail drtShopOrderDetail);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单子表 主键ID
	 * @return 积分商城订单子表 单条记录
	 */
	DrtShopOrderDetail selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 * @return 积分商城订单子表 记录集
	 */
	List<DrtShopOrderDetail> selectList(DrtShopOrderDetail drtShopOrderDetail);

	/**
	 * 获取用户订单
	 * 
	 * @param params 查询条件
	 * @return 用户订单记录集
	 */
	List<DrtShopOrderDetail> selectListByOrderID(Map<String,Object> params);

	/**
	 * 批量向子订单插入数据
	 * @param objLists
	 */
	void insertList(List<DrtShopOrderDetail> objLists);

	/**
	 * 创建主订单
	 * @param drtShopOrder
	 */
	void insertOrder(DrtShopOrder drtShopOrder);
}