package com.csg.intshop.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csg.intshop.entity.DrtMallCart;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.mapper.DrtMallOrderMapper;

/**
 * DrtMallOrderService 积分商城订单主表
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2018年01月23日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMallOrderService {
	
	private Logger log = LoggerFactory.getLogger(DrtMallOrderService.class);

	/** 积分商城订单主表drtMallOrderMapper接口 */
	@Autowired
	private DrtMallOrderMapper drtMallOrderMapper;

	/** drtMallOrderDetailService 积分商城订单明细表 */
	@Autowired
	private DrtMallOrderDetailService drtMallOrderDetailService;
	
	/** DrtMallCartService 积分商城购物车表 */
	@Autowired
	private DrtMallCartService drtMallCartService;

	/**
	 * 保存
	 * 
	 * @param drtMallOrder
	 *            积分商城订单主表
	 */
	public void insert(DrtMallOrder drtMallOrder) throws Exception {
		drtMallOrderMapper.insert(drtMallOrder);
	}

	/**
	 * 批量保存
	 * 
	 * @param drtMallOrder
	 *            积分商城订单主表
	 */
	public void insertList(List<DrtMallOrder> drtMallOrderList)
			throws Exception {
		drtMallOrderMapper.insertList(drtMallOrderList);
	}

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id
	 *            积分商城订单主表 主键ID
	 * @throws Exception
	 *             出错抛出异常
	 */
	public void delete(String id) throws Exception {
		drtMallOrderMapper.delete(id);
	}

	/**
	 * 更新
	 * <p>
	 * 通过主键更新记录
	 * </p>
	 * 
	 * @param drtMallOrder
	 *            积分商城订单主表
	 * @throws Exception
	 *             出错抛出异常
	 */
	public void update(DrtMallOrder drtMallOrder) throws Exception {
		drtMallOrderMapper.update(drtMallOrder);
	}

	/**
	 * 更新非空字段
	 * <p>
	 * 通过主键更新记录
	 * </p>
	 * 
	 * @param drtMallOrder
	 *            积分商城订单主表
	 * @throws Exception
	 *             出错抛出异常
	 */
	public void updateIfNotNull(DrtMallOrder drtMallOrder) throws Exception {
		drtMallOrderMapper.updateIfNotNull(drtMallOrder);
	}

	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id
	 *            积分商城订单主表 主键ID
	 * @return 积分商城订单主表 单条记录
	 */
	public DrtMallOrder selectByPrimaryKey(String id) {
		return drtMallOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过主键获取记录数
	 * 
	 * @param ids
	 *            积分商城订单主表 主键ID
	 * @return 积分商城订单主表 记录数
	 */
	public List<DrtMallOrder> selectByPrimaryKeys(String ids) {
		return drtMallOrderMapper.selectByPrimaryKeys(ids);
	}
	
	/**
	 * 通过主键获取记录数
	 * 
	 * @param ids
	 *            积分商城订单主表 主键orderCode
	 * @return 积分商城订单主表 记录数
	 */
	public List<DrtMallOrder> selectByOrderCodes(String orderCodes) {
		return drtMallOrderMapper.selectByOrderCodes(orderCodes);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallOrder
	 *            积分商城订单主表
	 */
	public List<DrtMallOrder> selectList(DrtMallOrder drtMallOrder) {
		return drtMallOrderMapper.selectList(drtMallOrder);
	}

	/**
	 * 积分商城接口查询用
	 * 
	 * @param drtMallOrder
	 *            积分商城订单主表
	 * @return 积分商城订单主表 记录集
	 */
	public List<DrtMallOrder> selectListByinterface(DrtMallOrder drtMallOrder) {
		return drtMallOrderMapper.selectListByinterface(drtMallOrder);
	}

	/**
	 * 保存订单
	 * 
	 * @param drtMallOrder
	 *            积分商城订单主表
	 *            
	 * @param orderDetailList
	 *            积分商城订单明细表
	 * @return 积分商城订单主表 记录集
	 */
	public boolean insert(DrtMallOrder drtMallOrder,
			List<DrtMallOrderDetail> orderDetailList){
		try{
			// 保存订单主表
			this.insert(drtMallOrder);
			// 保存订单明细
			drtMallOrderDetailService.insertList(orderDetailList);
			
			/** 订单提交，删除相应的购物车商品 */
			// 获取用户购物车商品
			DrtMallCart queryDrtMallCart = new DrtMallCart();
			queryDrtMallCart.setAccountId(drtMallOrder.getAccountId());
			List<DrtMallCart> drtMallCartList = drtMallCartService.selectList(queryDrtMallCart);
			// 封装数据：key-商品ID value-购物车ID
			Map<String, String> itemCartId = new HashMap<>();
			for (DrtMallCart drtMallCart : drtMallCartList) {
				itemCartId.put(drtMallCart.getItemId(), drtMallCart.getId());
			}
			// 封装待删除的购物车商品相应的购物车ID
			Set<String> carIdSet = new HashSet<>();
			for (DrtMallOrderDetail drtMallOrderDetail : orderDetailList) {
				if(itemCartId.containsKey(drtMallOrderDetail.getItemId())){
					carIdSet.add(itemCartId.get(drtMallOrderDetail.getItemId()));
				}
			}
			// 删除
			drtMallCartService.delete(carIdSet);
			
			return true;
		}catch(Exception e){
			log.error("订单异常，回滚！！！");
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();         
		return false;
	}
}