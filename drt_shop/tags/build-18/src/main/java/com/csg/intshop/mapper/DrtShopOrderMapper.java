package com.csg.intshop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.csg.intshop.entity.DrtShopOrder;

/**
 * DrtShopOrderMapper 积分商城订单表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public interface DrtShopOrderMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtShopOrder 积分商城订单表
	 */
	void insert(DrtShopOrder drtShopOrder);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopOrder 积分商城订单表
	 */
	void update(DrtShopOrder drtShopOrder);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单表 主键ID
	 * @return 积分商城订单表 单条记录
	 */
	DrtShopOrder selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopOrder 积分商城订单表
	 * @return 积分商城订单表 记录集
	 */
	List<DrtShopOrder> selectList(DrtShopOrder drtShopOrder);

	/**
	 * 修改订单状态
	 * 
	 * @param drtShopOrder 更新对象
	 */
	@Update("update drt_shop_order set order_state=#{orderState} where id=#{id} and account_id= #{accountId}")
	void updShopOrderState(DrtShopOrder drtShopOrder);

	/**
	 * 根据订单号查询
	 * @param orderNo 订单号
	 * @return 订单信息
	 */
	List<Map<String,Object>> queryGoods(String orderNo);
}