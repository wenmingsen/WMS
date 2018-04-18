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
import com.csg.intshop.service.DrtShopOrderDetailService;
import com.csg.intshop.service.DrtShopOrderService;


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
		String accountId=(String) request.getSession().getAttribute("accountId");
		Map<String,Object> result=new HashMap<String,Object>();
		DrtShopOrder drtShopOrder=new DrtShopOrder();
		drtShopOrder.setAccountId(accountId);
		drtShopOrder.setOrderState(state);
		try {
			List<DrtShopOrder> shopOrders= drtShopOrderService.selectList(drtShopOrder);
			List<DrtShopOrderDetail> details=new ArrayList<DrtShopOrderDetail>();
		    List<String> orderNos=new ArrayList<String>();
			for(DrtShopOrder shopOrder :shopOrders){
				orderNos.add(shopOrder.getOrderNo());
		    }
			if(orderNos.size()!=0){
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("state", state);
				params.put("orderNos", orderNos);
				details=drtShopOrderDetailService.selectListByUserID(params);
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
	public void updState(DrtShopOrder drtShopOrder){
		try {
		drtShopOrderService.updShopOrderState(drtShopOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询订单详细信息
	 * @param orderId 订单id
	 * @return 订单详情
	 */
	@RequestMapping("/queryGoods")
	@ResponseBody
	public Map<String,Object> queryGoods(String orderId){
		return drtShopOrderService.queryGoods(orderId);
	}
}
	

