package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMallItem;


/**
 * DrtMallItemMapper 积分商城商品池
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月24日 温明森
 */
public interface DrtMallItemMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMallItem 积分商城商品池
	 */
	void insert(DrtMallItem drtMallItem);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallItem 积分商城商品池
	 */
	void update(DrtMallItem drtMallItem);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城商品池 主键ID
	 * @return 积分商城商品池 单条记录
	 */
	DrtMallItem selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallItem 积分商城商品池
	 * @return 积分商城商品池 记录集
	 */
	List<DrtMallItem> selectList(DrtMallItem drtMallItem);
	
	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param strSkuCode 商品编号
	 * @return 积分商城商品池 记录集
	 */
	List<DrtMallItem> selectListBySkuCodes(String strSkuCode);
	
	/**
	 * 批量保存
	 * 
	 * @param drtMallItem 积分商城商品池
	 */
	void insertList(List<DrtMallItem> drtMallItemList);

}