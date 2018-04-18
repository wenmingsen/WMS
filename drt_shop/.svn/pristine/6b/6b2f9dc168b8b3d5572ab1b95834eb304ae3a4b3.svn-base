package com.csg.intshop.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.common.enums.ErrorEnum;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallEarningsExChangeService;
import com.csg.intshop.service.DrtMallOrderDetailService;
import com.csg.intshop.service.DrtMallOrderService;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.ResultMapHelper;
import com.github.pagehelper.PageHelper;

/**
 * 积分商城对app提供接口
 *
 * @author  曾令鹏
 * @since   jdk1.8
 * @version 2018年2月7日 曾令鹏
 */
@RestController
@RequestMapping("/ins/api/mall/order/*")
public class MallOrderController {
	
	/** DrtMallOrderService 积分商城订单主表 */
	@Autowired
	private DrtMallOrderService drtMallOrderService;
	
	/** DrtMallOrderDetailService 积分商城订单明细表 */
	@Autowired
	private DrtMallOrderDetailService drtMallOrderDetailService;
	
	/** DrtMallEarningsExChangeService 积分兑换 */
	@Autowired
	private DrtMallEarningsExChangeService drtMallEarningsExChangeService;
	
	/** 积分接口*/
	@Autowired
	private EarningsCore earningsCore;
	
	/** 电子商城接口 工具类 */
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;

	/**
	 * 获取用户订单列表
	 * 
	 * @param request 
	 * 			accountId-用户accountId
	 * 			orderState-订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单（可选）
	 * 			pageNumber-页码：默认第一页（必填）
	 * 			pageSize-每页显示数：默认10（可选）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderList")
	public Map<String, Object> orderList(HttpServletRequest request) {
		// 用户accountId
		String accountId = request.getParameter("accountId");
		// 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
		int orderState = NumberUtils.toInt(request.getParameter("orderState"), 9999);
		int pageNumber = NumberUtils.toInt(request.getParameter("pageNumber"), 1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 10);
		if(pageNumber < 1){
			pageNumber = 1;
		}
		// 订单主表-查询实体
		DrtMallOrder queryDrtMallOrder = new DrtMallOrder();
		// 订单子表-查询实体
		DrtMallOrderDetail queryDrtMallOrderDetail = new DrtMallOrderDetail();
		queryDrtMallOrder.setAccountId(accountId);
		if(orderState != 9999){
			queryDrtMallOrder.setOrderState(orderState);
		}
		PageHelper.startPage(pageNumber, pageSize, false, false);
		PageHelper.orderBy("submitTime desc");
		List<DrtMallOrder> orderList = drtMallOrderService.selectList(queryDrtMallOrder);
		List<Map<String, Object>> orderResultList = new ArrayList<>();
		Map<String, Object> orderResult = null;
		Map<String, Object> orderDetailResult = null;
		for (DrtMallOrder order : orderList) {
			orderResult = new HashMap<>();
			orderResult.put("orderId", order.getId());
			orderResult.put("orderCode", order.getOrderCode());
			orderResult.put("submitTime", DateTimeUtils.converDateToString(DateTimeUtils.converStringToDate(order.getSubmitTime() + "", "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm"));
			orderResult.put("orderState", order.getOrderState());
			orderResult.put("orderTotalEarnings", order.getOrderTotalEarnings().longValue() / 100);
			orderResult.put("storeCode", order.getStoreCode());
			orderResult.put("storeName", order.getStoreName());
			// 通过订单ID查询订单详情
			queryDrtMallOrderDetail.setOrderId(order.getId());
			PageHelper.orderBy("createTime desc");
			List<DrtMallOrderDetail> orderDetailList = drtMallOrderDetailService.selectList(queryDrtMallOrderDetail);
			List<Map<String, Object>> orderDetailResultList = new ArrayList<>();
			for (DrtMallOrderDetail orderDetail : orderDetailList) {
				orderDetailResult = new HashMap<>();
				orderDetailResult.put("itemId", orderDetail.getItemId());
				orderDetailResult.put("skuName", orderDetail.getSkuName());
				orderDetailResult.put("imgUrl", orderDetail.getImgUrl());
				orderDetailResult.put("skuEarnings", String.valueOf(orderDetail.getSkuEarnings().longValue() / 100));
				orderDetailResult.put("buyAmt", orderDetail.getBuyAmt());
				orderDetailResult.put("subtotalEarnings", String.valueOf(orderDetail.getSubtotalEarnings().longValue() / 100));
				orderDetailResult.put("brandId", orderDetail.getBrandId());
				orderDetailResult.put("brandName", orderDetail.getBrandName());
				orderDetailResult.put("companyId", orderDetail.getCompanyId());
				orderDetailResult.put("companyName", orderDetail.getCompanyName());
				orderDetailResultList.add(orderDetailResult);
			}
			orderResult.put("orderDetailList", orderDetailResultList);
			orderResultList.add(orderResult);
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "执行成功！", orderResultList);
	}
	
	/**
	 * 获取订单详情
	 * 
	 * @param session
	 * @param request 
	 * 			orderId-订单ID（必填）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryOrderDetail")
	public Map<String, Object> queryOrderDetail(HttpSession session, HttpServletRequest request){
		// 用户accountId
		String accountId = request.getParameter("accountId");
		// 订单ID
		String orderId = request.getParameter("orderId");
		//用户accountId非空校验
		if(StringUtils.isBlank(accountId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "用户accountId获取失败");
		}
		if(StringUtils.isBlank(orderId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		// 查询订单
		DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
		if(drtMallOrder == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		if(!Objects.equals(accountId, drtMallOrder.getAccountId())){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单不属于当前用户！");
		}
		// 封装订单数据
		Map<String, Object> orderResult = new HashMap<>();
		orderResult.put("orderId", drtMallOrder.getId());
		orderResult.put("orderCode", drtMallOrder.getOrderCode());
		orderResult.put("submitTime", DateTimeUtils.converDateToString(DateTimeUtils.converStringToDate(drtMallOrder.getSubmitTime() + "", "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss"));
		orderResult.put("createTime", DateTimeUtils.converDateToString(DateTimeUtils.converStringToDate(drtMallOrder.getCreateTime() + "", "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss"));
		orderResult.put("orderState", drtMallOrder.getOrderState());
		orderResult.put("deliveryType", drtMallOrder.getDeliveryType());
		orderResult.put("name", drtMallOrder.getConsigneeName());
		orderResult.put("phone", drtMallOrder.getConsigneeMobile());
		orderResult.put("address", drtMallOrder.getConsigneeProvinceName() + drtMallOrder.getConsigneeCityName() + drtMallOrder.getConsigneeCountyName() + drtMallOrder.getConsigneeTownName());
		orderResult.put("orderTotalEarnings", drtMallOrder.getOrderTotalEarnings().longValue() / 100);
		orderResult.put("storeCode", drtMallOrder.getStoreCode());
		orderResult.put("storeName", drtMallOrder.getStoreName());
		orderResult.put("orderMemberMemo", drtMallOrder.getOrderMemberMemo());
		// 通过订单ID查询订单详情
		DrtMallOrderDetail queryDrtMallOrderDetail = new DrtMallOrderDetail();
		queryDrtMallOrderDetail.setOrderId(drtMallOrder.getId());
		List<DrtMallOrderDetail> orderDetailList = drtMallOrderDetailService.selectList(queryDrtMallOrderDetail);
		// 封装订单详情数据
		List<Map<String, Object>> orderDetailResultList = new ArrayList<>();
		Map<String, Object> orderDetailResult = null;
		for (DrtMallOrderDetail orderDetail : orderDetailList) {
			orderDetailResult = new HashMap<>();
			orderDetailResult.put("itemId", orderDetail.getItemId());
			orderDetailResult.put("skuName", orderDetail.getSkuName());
			orderDetailResult.put("imgUrl", orderDetail.getImgUrl());
			orderDetailResult.put("skuEarnings", String.valueOf(orderDetail.getSkuEarnings().longValue() / 100));
			orderDetailResult.put("buyAmt", orderDetail.getBuyAmt());
			orderDetailResult.put("subtotalEarnings", String.valueOf(orderDetail.getSubtotalEarnings().longValue() / 100));
			orderDetailResult.put("brandId", orderDetail.getBrandId());
			orderDetailResult.put("brandName", orderDetail.getBrandName());
			orderDetailResult.put("companyId", orderDetail.getCompanyId());
			orderDetailResult.put("companyName", orderDetail.getCompanyName());
			orderDetailResultList.add(orderDetailResult);
		}
		orderResult.put("orderDetailList", orderDetailResultList);
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "执行成功！", orderResult);
	}
	
	/**
	 * 积分商城兑换接口
	 * @param request
	 * 			accountId-用户accountId（必填）
	 * 			orderIds-待兑换订单集（必填）格式：["orderId1", "orderId2"] 
	 * @author xuchen
	 * @return map
	 * 		   
	 * @throws Exception 
	 */
	@RequestMapping("exchangeOrder")
	public Map<String, Object> exchangeOrder(HttpServletRequest request){
		try {
			// 用户accountId
			String accountId = request.getParameter("accountId");
			//积分商城订单编号
			String orderIds = request.getParameter("orderIds");
			//用户accountId非空校验
			if(StringUtils.isBlank(accountId)){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "用户accountId获取失败");
			}
			//订单IDList非空校验
			if(StringUtils.isBlank(orderIds)){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换数据获取失败");
			}
			//订单IDList转JSON数组
			JSONArray orderIdArray = JSONArray.fromObject(orderIds);
			//订单IDJSON数组非空校验
			if(CollectionUtils.isEmpty(orderIdArray)){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换数据获取失败");
			}
			//封装查询参数
			String strOrderIds = "";
			for(int i=0; i<orderIdArray.size(); i++){
				if(StringUtils.isBlank(strOrderIds)){
					strOrderIds = "'" + orderIdArray.getString(i) + "'";
				}else{
					strOrderIds = strOrderIds + ", '" + orderIdArray.getString(i) + "'";
				}
			}
			//积分商城订单ID非空校验
			if(StringUtils.isEmpty(strOrderIds)){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ORDERID_NULL);
			}
			//根据积分商城订单ID查询订单LIST（strOrderIds格式：'orderId1','orderId2'）
			List<DrtMallOrder> drtMallOrderList = drtMallOrderService.selectByPrimaryKeys(strOrderIds);
			if(CollectionUtils.isEmpty(drtMallOrderList) || drtMallOrderList.size() != orderIdArray.size()){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ORDERID_NOT_EXIST, "");
			}
			//扣积分
			for(DrtMallOrder drtMallOrder:drtMallOrderList){
				if(!Objects.equals(accountId, drtMallOrder.getAccountId())){
					return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单不属于当前用户，无法进行兑换");				
				}
				if(drtMallOrder.getOrderTotalEarnings() < 0){
					return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单积分异常，无法进行兑换");				
				}
				//订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
				if(1 != drtMallOrder.getOrderState()){
					return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单状态不能进行兑换");				
				}
				//查询用户积分余额
				int earningsBalance = earningsCore.queryEarnings(accountId);
				if(earningsBalance*SystemConstants.MULTIPLICATION100 < drtMallOrder.getOrderTotalEarnings()){
					return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "积分不足，余额为："+earningsBalance);	
				}
				boolean flag = drtMallEarningsExChangeService.earningsExchange(drtMallOrder);
				if(!flag){
					return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换失败");	
				}
			}
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "兑换成功");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换失败");
	}
	
	/**
	 * 订单确认收货
	 * 
	 * @param session
	 * @param request 
	 * 			accountId-用户accountId
	 * 			orderId-订单ID（必填）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("synReceiptConfrim")
	public Map<String, Object> synReceiptConfrim(HttpSession session, HttpServletRequest request){
		// 用户accountId
		String accountId = request.getParameter("accountId");
		// 订单ID
		String orderId = request.getParameter("orderId");
		//参数校验
		if(StringUtils.isBlank(accountId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "用户accountId获取失败！");
		}
		if(StringUtils.isBlank(orderId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		// 查询订单
		DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
		if(drtMallOrder == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		if(!Objects.equals(accountId, drtMallOrder.getAccountId())){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单不属于当前用户！");
		}
		// 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
		if(drtMallOrder.getOrderState() != 3){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单状态不能完成收货！");
		}
		//确认收货接口
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("integralOrderCode", drtMallOrder.getOrderCode());
		JSONObject jsonObject = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.SYN_RECEIPT_CONFRIM, paramMap);
		if("Y".equals(jsonObject.get("success")) && ErrorEnum.NORMAL.getResultCode().equals(jsonObject.get("resultCode"))){
			try {
				/** 更新本地订单 */
				drtMallOrder.setOrderState(4);
				drtMallOrderService.update(drtMallOrder);
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "收货成功！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "收货失败！");
	}
	
	/**
	 * 取消订单
	 * 
	 * @param session
	 * @param request  accountId-用户accountId（必填）
	 * @param request  orderId-订单ID（必填）
	 * @return
	 */
	@RequestMapping("cancleOrder")
	public Map<String, Object> cancleOrder(HttpServletRequest request){
		// 用户accountId
		String accountId = request.getParameter("accountId");
		// 订单ID
		String orderId = request.getParameter("orderId");
		//参数校验
		if(StringUtils.isBlank(accountId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "用户accountId获取失败！");
		}
		if(StringUtils.isBlank(orderId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		// 查询订单
		DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
		if(drtMallOrder == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		if(!Objects.equals(accountId, drtMallOrder.getAccountId())){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单不属于当前用户！");
		}
		// 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
		if(drtMallOrder.getOrderState() != 1){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单状态不能取消！");
		}
		try{
			/** 更新本地订单 */
			drtMallOrder.setOrderState(5);
			drtMallOrderService.update(drtMallOrder);
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "取消成功！");
		}catch(Exception e){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "取消失败！");
		}
	}
	
	/**
	 * 删除订单
	 * 
	 * @param session
	 * @param request  accountId-用户accountId（必填）
	 * @param request  orderId-订单ID（必填）
	 * @return
	 */
	@RequestMapping("deleteOrder")
	public Map<String, Object> deleteOrder(HttpServletRequest request){
		// 用户accountId
		String accountId = request.getParameter("accountId");
		// 订单ID
		String orderId = request.getParameter("orderId");
		//参数校验
		if(StringUtils.isBlank(accountId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "用户accountId获取失败！");
		}
		if(StringUtils.isBlank(orderId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		// 查询订单
		DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
		if(drtMallOrder == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		if(!Objects.equals(accountId, drtMallOrder.getAccountId())){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单不属于当前用户！");
		}
		// 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
		if(drtMallOrder.getOrderState() != 4 && drtMallOrder.getOrderState() != 5){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单状态不能删除！");
		}
		try{
			/** 更新本地订单 */
			drtMallOrderService.delete(orderId);
			DrtMallOrderDetail queryDrtMallOrderDetail = new DrtMallOrderDetail();
			queryDrtMallOrderDetail.setOrderId(orderId);
			List<DrtMallOrderDetail> drtMallOrderDetailList = drtMallOrderDetailService.selectList(queryDrtMallOrderDetail);
			if(!CollectionUtils.isEmpty(drtMallOrderDetailList)){
				for (DrtMallOrderDetail drtMallOrderDetail : drtMallOrderDetailList) {
					drtMallOrderDetailService.delete(drtMallOrderDetail.getId());
				}
			}
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "删除成功！");
		}catch(Exception e){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "删除失败！");
		}
	}
	
}
