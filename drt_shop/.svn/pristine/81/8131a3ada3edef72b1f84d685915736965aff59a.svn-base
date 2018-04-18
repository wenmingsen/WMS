package com.csg.intshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.mapper.DrtShopOrderMapper;

/**
 * DrtShopOrderService 积分商城订单表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtShopOrderService{
	
	/**积分商城订单表drtShopOrderMapper接口*/
	@Autowired
	private DrtShopOrderMapper drtShopOrderMapper;

	/**
	 * 保存
	 * 
	 * @param drtShopOrder 积分商城订单表
	 */
	public void insert(DrtShopOrder drtShopOrder) throws Exception{
		drtShopOrderMapper.insert(drtShopOrder);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopOrder 积分商城订单表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtShopOrder drtShopOrder) throws Exception{
		drtShopOrderMapper.update(drtShopOrder);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单表 主键ID
	 * @return 积分商城订单表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtShopOrder selectByPrimaryKey(String id) throws Exception{
		return drtShopOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopOrder 积分商城订单表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtShopOrder> selectList(DrtShopOrder drtShopOrder) throws Exception{
		return drtShopOrderMapper.selectList(drtShopOrder);
	}
	
	/**
	 * 修改订单状态
	 * 
	 * @param drtShopOrder 更新对象
	 */
	public void updShopOrderState(DrtShopOrder drtShopOrder){
		drtShopOrderMapper.updShopOrderState(drtShopOrder);
	}



}