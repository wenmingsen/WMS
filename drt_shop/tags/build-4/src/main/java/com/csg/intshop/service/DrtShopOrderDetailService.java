package com.csg.intshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.entity.DrtShopOrderDetail;
import com.csg.intshop.mapper.DrtShopOrderDetailMapper;

/**
 * DrtShopOrderDetailService 积分商城订单子表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtShopOrderDetailService{
	
	/**积分商城订单子表drtShopOrderDetailMapper接口*/
	@Autowired
	private DrtShopOrderDetailMapper drtShopOrderDetailMapper;

	/**
	 * 保存
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 */
	public void insert(DrtShopOrderDetail drtShopOrderDetail) throws Exception{
		drtShopOrderDetailMapper.insert(drtShopOrderDetail);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtShopOrderDetail drtShopOrderDetail) throws Exception{
		drtShopOrderDetailMapper.update(drtShopOrderDetail);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单子表 主键ID
	 * @return 积分商城订单子表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtShopOrderDetail selectByPrimaryKey(String id) throws Exception{
		return drtShopOrderDetailMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtShopOrderDetail> selectList(DrtShopOrderDetail drtShopOrderDetail) throws Exception{
		return drtShopOrderDetailMapper.selectList(drtShopOrderDetail);
	}

	/**
	 * 获取用户订单
	 * 
	 * @param params 查询条件
	 * @return 用户订单记录集
	 */
	public List<DrtShopOrderDetail> selectListByUserID(Map<String,Object> params){
		return drtShopOrderDetailMapper.selectListByUserID(params);
	}
}