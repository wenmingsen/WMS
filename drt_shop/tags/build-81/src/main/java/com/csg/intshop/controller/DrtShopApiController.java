/**
 * 
 */
package com.csg.intshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.entity.DrtShopOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopCartService;
import com.csg.intshop.service.DrtShopOrderDetailService;
import com.csg.intshop.service.DrtShopOrderService;
import com.csg.intshop.util.ResultMapHelper;

/**
 * 微服务调用接口
 * 
 * @author 李城
 * 
 */
@RestController
@RequestMapping("/ins/api")
public class DrtShopApiController {
	private Logger log = LoggerFactory.getLogger(DrtShopApiController.class);
	/** drtShopOrderService 积分商城订单表 */
	@Autowired
	private DrtShopOrderService drtShopOrderService;
	
	/** drtShopCartService 积分商城购物车表 */
	@Autowired
	private DrtShopCartService drtShopCartService;
	
	/** drtShopOrderDetailService 积分商城订单子表 */
	@Autowired
	private DrtShopOrderDetailService drtShopOrderDetailService;
	
	/**
	 * 资产调用商城服务测试
	 * 
	 * @return 测试结果
	 */
	@RequestMapping("/testFinanceShop")
	public String testFinanceShop(){
		return "资产调用商城服务成功";
	}
	
	/**
	 * 获取用户订单
	 * 
	 * @param params 查询条件
	 * @return 用户订单记录集
	 */
	@RequestMapping(value="/selShopOrder",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selShopOrder(Integer state,String accountId){
		Map<String,Object> map=new HashMap<String,Object>();
		DrtShopOrder drtShopOrder=new DrtShopOrder();
		drtShopOrder.setAccountId(accountId);
		drtShopOrder.setOrderState(state);
		try {
			Map<String,Object> result=new HashMap<String,Object>();
			List<DrtShopOrder> shopOrders= drtShopOrderService.selectList(drtShopOrder);
			List<DrtShopOrderDetail> details=new ArrayList<DrtShopOrderDetail>();
		    List<String> orderNos=new ArrayList<String>();
		    if(shopOrders==null||shopOrders.isEmpty()){
		    	map.put(SystemConstants.RESULT_CODE_SUCCESS, "查询无该用电户号订单");
		    }else{
				for(DrtShopOrder shopOrder :shopOrders){
					orderNos.add(shopOrder.getId());
			    } 
				if(!orderNos.isEmpty()){
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("state", state);
					params.put("orderNos", orderNos);
					details=drtShopOrderDetailService.selectListByOrderID(params);
					for(DrtShopOrder shopOrder:shopOrders){
						List<DrtShopOrderDetail> shopOrderdetails=new ArrayList<DrtShopOrderDetail>();
						for(DrtShopOrderDetail shopOrderDetail:details){
							if(shopOrder.getId().equals(shopOrderDetail.getOrderId())){
								shopOrderdetails.add(shopOrderDetail);
							}
						}
						shopOrder.setOrderSons(shopOrderdetails);
					}
			    }
				result.put("shopOrders", shopOrders);
				//result.put("details", details);
				result.put("accountId", accountId);
				map= ResultMapHelper.success(result);
		    }
		} catch (Exception e) {
			map = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "查询订单状态失败", "");
			e.printStackTrace();
		}
		return map ;
	}
	
	/**修改订单状态(确认收货，取消订单)
	 * 
	 * @param  id 订单id
	 * @param orderState 订单状态
	 * @return 修改结果
	 * */
	@RequestMapping(value="/updShopOrder",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updState(String accountId,String id,String orderState){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			DrtShopOrder drtShopOrder=new DrtShopOrder();
			drtShopOrder.setAccountId(accountId);
			drtShopOrder.setId(id);
			if(StringUtils.isNotEmpty(orderState)){
				drtShopOrder.setOrderState(Integer.valueOf(orderState));
			}else{
				drtShopOrder.setOrderState(SystemConstants.ZERO);
			}
			int result =drtShopOrderService.updShopOrderState(drtShopOrder);
			if(result==SystemConstants.ORDERSTATE){
				map = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "修改订单状态成功", "");
			}else{
				map = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单不存在", "");
			}
		} catch (Exception e) {
			map = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "修改订单状态失败", "");
			e.printStackTrace();
		}
		return map;
	}
	
	
	/****
	 * 积分兑换
	 * @param totalMoney 花费积分
	 * @param orderId 订单id
	 * @param orderNo 订单号
	 * @return 兑换结果
	 */
	@RequestMapping(value = "/ensureExchange",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> ensureExchange(String totalMoney,String id,String orderNo,String accountId){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			log.info("当前登陆人："+accountId);
			Map<String,Object> result= drtShopCartService.ensureExchange(totalMoney, id,orderNo,accountId);
			map=ResultMapHelper.success(result);
		} catch (Exception e) {
			map = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换订单状态失败", "");
			e.printStackTrace();
		}
		return map;
	}
}
