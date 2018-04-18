package com.csg.intshop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopOrderDetailService;
import com.csg.personcenter.entity.mybatis.DrtAccount;
import com.github.pagehelper.StringUtil;


/**
 * DrtShopOrderDetail 积分商城订单子表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Controller
@RequestMapping("/ins/drtShopOrderDetail")
public class DrtShopOrderDetailController{

	/** drtShopOrderDetailService 积分商城订单子表 */
	@Autowired
	private DrtShopOrderDetailService drtShopOrderDetailService;
	
	/**
	 * 创建主订单和子订单
	 * @param id 商品id
	 * @param orderNum 订单号
	 * @param request 获取session
	 * @return 创建结果
	 */
	@RequestMapping(value = "/insertOrder",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> insertOrder(String id,String itemNum,String orderNum,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		Integer itemNum1 = null;
		if(StringUtil.isNotEmpty(itemNum)){
			itemNum1 = Integer.parseInt(itemNum);
		}
		Map<String,Object> objMap = drtShopOrderDetailService.insertOrder(id,itemNum1, orderNum, accountId);
		return objMap;
				
	}
	/**
	 * 获取商品信息
	 * @param id 单件商品的id
	 * @param request 获取session
	 * @return 商品信息结果
	 */
	@RequestMapping("/queryGoodsInfo")
	@ResponseBody
	public Map<String,Object> queryGoodsInfo(String id,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		Map<String,Object> objMap = drtShopOrderDetailService.queryGoodsInfo(id,accountId);
		return objMap;
	}
	
	/**
	 * 改变订单中商品的数量以及积分值
	 * @param itemId 商品id
	 * @param itemNum 商品数量
	 * @param earnings 积分值
	 * @return
	 */
	@RequestMapping("/changeNumEarnings")
	@ResponseBody
	public Map<String,Object> changeNumEarnings(String itemId,String orderId,String itemNum,String earnings){
		Map<String,Object> objMap = drtShopOrderDetailService.changeNumEarnings(itemId,orderId,itemNum,earnings);
		return objMap;
	}
	
	/**
	 * 修改订单中地址
	 * @return
	 */
	@RequestMapping(value = "/updateOrderAddress",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateOrderAddress(DrtShopOrder drtShopOrder,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		drtShopOrder.setAccountId(drtAccount.getAccountId());
		Map<String,Object> objMap = drtShopOrderDetailService.updateOrderAddress(drtShopOrder);
		return objMap;
		
	}
	
}
	

