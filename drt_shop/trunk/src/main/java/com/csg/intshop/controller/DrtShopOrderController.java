package com.csg.intshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.entity.DrtShopOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopOrderDetailService;
import com.csg.intshop.service.DrtShopOrderService;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.personcenter.entity.mybatis.DrtAccount;


/**
 * DrtShopOrder 积分商城订单表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@RestController
@RequestMapping("/ins/drtShopOrder")
public class DrtShopOrderController{

	/** drtShopOrderService 积分商城订单表 */
	@Autowired
	private DrtShopOrderService drtShopOrderService;

	/** drtShopOrderDetailService 积分商城订单子表 */
	@Autowired
	private DrtShopOrderDetailService drtShopOrderDetailService;
	
	/**
	 * 获取用户订单
	 * 
	 * @param params 查询条件
	 * @return 用户订单记录集
	 */
	@RequestMapping(value="/selShopOrder",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selShopOrder(Integer state,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		Map<String,Object> result=new HashMap<String,Object>();
		DrtShopOrder drtShopOrder=new DrtShopOrder();
		drtShopOrder.setAccountId(accountId);
		drtShopOrder.setOrderState(state);
		try {
			List<DrtShopOrder> shopOrders= drtShopOrderService.selectList(drtShopOrder);
			List<DrtShopOrderDetail> details=new ArrayList<DrtShopOrderDetail>();
		    List<String> orderIds=new ArrayList<String>();
			for(DrtShopOrder shopOrder :shopOrders){
				orderIds.add(shopOrder.getId());
		    }
			if(!orderIds.isEmpty()){
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("orderIds", orderIds);
				details=drtShopOrderDetailService.selectListByOrderID(params);
		    }
			result.put("shopOrders", shopOrders);
			result.put("details", details);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	/**修改订单状态
	 * 
	 * @param drtShopOrder 修改订单对象
	 * */
	@RequestMapping(value="/updShopOrder",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updState(DrtShopOrder drtShopOrder){
		Map<String,Object> result=new HashMap<String,Object>();
		int  orderState = drtShopOrder.getOrderState();
		try {
		drtShopOrderService.updShopOrderState(drtShopOrder);
		result = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, orderState==5?"订单取消成功":"确认收货成功", "");
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, orderState==5?"订单取消失败":"确认收货失败", "");
		}
		return result;
	}
	
	/**
	 * 查询订单详细信息
	 * @param orderId 订单id
	 * @return 订单详情
	 */
	@RequestMapping(value = "/queryGoods",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryGoods(String orderId){
		return drtShopOrderService.queryGoods(orderId);
	}
}
	

