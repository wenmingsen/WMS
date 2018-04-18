package com.csg.intshop.controller.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.common.enums.ErrorEnum;
import com.csg.intshop.entity.DrtAccountVO;
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.entity.DrtShopAddress;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtAccountService;
import com.csg.intshop.service.DrtMallEarningsExChangeService;
import com.csg.intshop.service.DrtMallItemService;
import com.csg.intshop.service.DrtMallOrderDetailService;
import com.csg.intshop.service.DrtMallOrderService;
import com.csg.intshop.service.DrtShopAddressService;
import com.csg.intshop.service.DrtShopConfigService;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.UUIDUtil;
import com.csg.personcenter.entity.mybatis.DrtAccount;
import com.github.pagehelper.PageHelper;

/**
 * 积分商城-订单管理
 *
 * @author  曾令鹏
 * @since   jdk1.8
 * @version 2018年1月30日 曾令鹏
 */
@RestController
@RequestMapping("/ins/mall/order/*")
public class OrderController {
	
	/** DrtMallOrderService 积分商城订单主表 */
	@Autowired
	private DrtMallOrderService drtMallOrderService;
	
	/** DrtMallOrderDetailService 积分商城订单明细表 */
	@Autowired
	private DrtMallOrderDetailService drtMallOrderDetailService;
	
	/** DrtMallItemService 积分商城商品池 */
	@Autowired
	private DrtMallItemService drtMallItemService;
	
	/** DrtShopConfigService 积分商城配置表 */
	@Autowired
	private DrtShopConfigService drtShopConfigService;
	
	/** DrtShopAddressService 积分商城地址表 */
	@Autowired
	private DrtShopAddressService drtShopAddressService;
	
	/** DrtMallEarningsExChangeService 积分兑换 */
	@Autowired
	private DrtMallEarningsExChangeService drtMallEarningsExChangeService;
	
	/** 电子商城接口 工具类 */
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;
	
	/** 积分接口*/
	@Autowired
	private EarningsCore earningsCore;
	
	/** 用户service */
	@Autowired	
	private	DrtAccountService drtAccountService;
	
	/**
	 * 积分商城兑换接口
	 * @param request
	 * 			orderIds： ["orderId1", "orderId2"]
	 * @author xuchen
	 * @return map
	 * 		   
	 * @throws Exception 
	 */
	@RequestMapping("exchangeOrder")
	public Map<String,Object> exchangeOrder(HttpServletRequest request, HttpSession session){
		try {
			//当前电融通用户
			DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
			//积分商城订单编号
			String orderIds = request.getParameter("orderIds");
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
				if(!Objects.equals(drtAccount.getAccountId(), drtMallOrder.getAccountId())){
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
				DrtAccountVO drtAccountVO = drtAccountService.selectByPrimaryKey(drtAccount.getAccountId());
				int earningsBalance = drtAccountVO.getTotalEarnings();
				if(earningsBalance < drtMallOrder.getOrderTotalEarnings()){
					return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "积分不足，余额为："+earningsBalance*SystemConstants.DIVISION100);	
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
	 * 确认订单
	 * 查询商品信息 orderJson: [{itemId:"商品ID", itemNum: "数量"},{itemId:"商品ID",
	 * itemNum: "数量"}]
	 * 
	 * @param request
	 * @return 结果
	 * @throws Exception 
	 */
	@RequestMapping(value = "confirmOrder", method = RequestMethod.POST)
	public Map<String, Object> orderInfoList(HttpServletRequest request) throws Exception {
		// 获取参数
		String orderJson = request.getParameter("orderJson");
		if(StringUtils.isBlank(orderJson)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品数据不能为空！");
		}
		JSONArray itemsJson = JSONArray.fromObject(orderJson);
		if(itemsJson == null || itemsJson.size() == 0){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品数据格式异常！");
		}
		
		// 过滤错误数据，返回最终的商品stuCode集合
		Map<String, Map<String, String>> itemMap = this.getItemMap(itemsJson);
		if(itemMap == null || itemMap.size() == 0){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品数据不存在！");
		}
		List<DrtMallOrderDetail> drtMallOrderDetailList = getDrtMallOrderDetailList(itemMap);
		if (CollectionUtils.isEmpty(drtMallOrderDetailList)) {
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品提交有误，请稍后再试！");
		}
		// 分组保存
		Map<String, List<DrtMallOrderDetail>> storeData = drtMallOrderDetailList
				.stream().collect(Collectors.groupingBy(DrtMallOrderDetail::getStoreCode));
		// 封装数据
		List<Map<String, Object>> resultDataList = new ArrayList<>();
		Map<String, Object> resultData = null;
		for(Map.Entry<String, List<DrtMallOrderDetail>> entry : storeData.entrySet()){
			String storeCode = entry.getKey();
			List<DrtMallOrderDetail> orderDetailList = entry.getValue();
			if(!CollectionUtils.isEmpty(orderDetailList)){
				resultData = new HashMap<>();
				resultData.put("storeCode", storeCode);
				resultData.put("storeName", orderDetailList.get(0).getStoreName());
				resultData.put("itemList", orderDetailList);
				resultDataList.add(resultData);
			}
		}
		
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "执行成功！", resultDataList);
	}
	
	/**
	 * 下单接口
	 * 
	 * @param request
	 *            orderJson={addressId: '123456',items: [{itemId: '1', itemNum:
	 *            1, orderMemberMemo: '留言1'},{itemId: '2', itemNum: 2,
	 *            orderMemberMemo: '留言2'}]}
	 * @author xuchen
	 * @return Map<String, Object> 结果集
	 * @throws Exception
	 */
	@RequestMapping("saveOrder")
	public Map<String, Object> saveOrder(HttpServletRequest request, HttpSession session) throws Exception {
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		
		/** 前端传入参数 */
		String orderJson = request.getParameter("orderJson");
		if(StringUtils.isBlank(orderJson)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单参数不能为空！");
		}
		// 订单Json转换格式JSONObject
		JSONObject orderJSONObject = JSONObject.fromObject(orderJson);
		// 收货地址ID
		String addressId = orderJSONObject.getString("addressId");
		// 商品
		String items = orderJSONObject.getString("items");
		if (StringUtils.isBlank(items)) {
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品信息不存在！");
		}
		if (StringUtils.isBlank(addressId)) {
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "地址不能为空！");
		}
		// String格式转换JSONArray
		JSONArray itemJsonArray = JSONArray.fromObject(items);
		if (itemJsonArray == null || itemJsonArray.size() == 0) {
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品信息获取失败！");
		}
		// 判断当前用户是否存有当期地址
		DrtShopAddress drtShopAddress = drtShopAddressService.selectByPrimaryKey(addressId);
		if(drtShopAddress == null || !Objects.equals(drtAccount.getAccountId(), drtShopAddress.getAccountId())){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "地址不存在或当前用户不存在该地址！");
		}
		// 过滤错误数据，返回最终的商品集合
		Map<String, Map<String, String>> itemMap = this.getItemMap(itemJsonArray);
		if (CollectionUtils.isEmpty(itemMap)) {
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "下单商品不存在或已下架！");
		}
		// 获取电子商城商品信息
		Map<String, Map<String, String>> skuInfoMap = getSkuInfoList(itemMap.keySet());
		if (CollectionUtils.isEmpty(skuInfoMap)) {
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品提交有误，请稍后再试！");
		}
		// 判断调用南网商品接口前后数量是否一致
		if (skuInfoMap.size() != itemMap.size()) {
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "数据提交异常，请重新下单！");
		} 
		
		// 获取积分商城配置项
		Map<String, String> drtShopConfigMap = drtShopConfigService.getDrtShopConfigMap();
		
		// 订单明细list
		List<DrtMallOrderDetail> drtMallOrderDetailList = new ArrayList<DrtMallOrderDetail>();
		
		
		for (Map.Entry<String, Map<String, String>> entry : skuInfoMap.entrySet()) {
			String skuCode = entry.getKey();
			// 获取单个南网电子商城商品信息
			Map<String, String> skuInfo = entry.getValue();
			if(skuInfo != null && itemMap.containsKey(skuCode)){
				// 获取单个待下单商品信息
				Map<String, String> item = itemMap.get(skuCode);
				if(item != null){
					// 单位：元
					double price = NumberUtils.toDouble(skuInfo.get("price"), 0);
					// 商品价格必须至少大于等于1分钱
					if(price >= 0.01){
						// 比例：元-->积分 
						int rate = NumberUtils.toInt(drtShopConfigMap.get("RATE"), 1);
						DrtMallOrderDetail drtMallOrderDetail = new DrtMallOrderDetail();
						drtMallOrderDetail.setId(UUIDUtil.generateUUID());
						drtMallOrderDetail.setItemId(item.get("itemId"));
						drtMallOrderDetail.setSkuId(skuInfo.get("skuId"));
						drtMallOrderDetail.setSkuCode(skuCode);
						drtMallOrderDetail.setSkuName(skuInfo.get("skuName"));
						drtMallOrderDetail.setImgUrl(skuInfo.get("imgUrl"));
						drtMallOrderDetail.setSkuPrice((long) (price * 100));
						drtMallOrderDetail.setBuyAmt(NumberUtils.toInt(item.get("itemNum"), 1));
						drtMallOrderDetail.setSkuEarnings((long) (price * rate * 100));
						drtMallOrderDetail.setSubtotalPrice(drtMallOrderDetail.getSkuPrice() * drtMallOrderDetail.getBuyAmt());
						drtMallOrderDetail.setSubtotalEarnings(drtMallOrderDetail.getSkuEarnings() * drtMallOrderDetail.getBuyAmt());
						drtMallOrderDetail.setPurchaseCatId(skuInfo.get("purchaseCatId"));
						drtMallOrderDetail.setPurchaseCatName(skuInfo.get("purchaseCatName"));
						drtMallOrderDetail.setBrandId(skuInfo.get("brandId"));
						drtMallOrderDetail.setBrandName(skuInfo.get("brandName"));
						drtMallOrderDetail.setCompanyId(skuInfo.get("companyId"));
						drtMallOrderDetail.setCompanyName(skuInfo.get("companyName"));
						drtMallOrderDetail.setStoreCode(skuInfo.get("storeCode"));
						drtMallOrderDetail.setStoreName(skuInfo.get("storeName"));
						drtMallOrderDetail.setCreateTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
						drtMallOrderDetail.setCreatorId(drtAccount.getAccountId());
						drtMallOrderDetail.setCreatorName(drtAccount.getNickname());
						drtMallOrderDetail.setLastModifierTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
						drtMallOrderDetail.setLastModifierId(drtAccount.getAccountId());
						drtMallOrderDetail.setLastModifierName(drtAccount.getNickname());
						// 是否删除：0-是 1-否
						drtMallOrderDetail.setIsDelete(1);
						drtMallOrderDetailList.add(drtMallOrderDetail);
					}
				}
			}
		}
		
		// 按店铺分组拆单
		Map<String, List<DrtMallOrderDetail>> resultData = drtMallOrderDetailList.stream().collect(
                Collectors.groupingBy(DrtMallOrderDetail::getStoreCode));
		if(resultData == null || resultData.size() == 0){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单获取失败！");
		}
		// 订单提交是否成功
		boolean flag = false;
		// 订单ID集
		List<String> orderIds = new ArrayList<>();
		for(Map.Entry<String, List<DrtMallOrderDetail>> entry : resultData.entrySet()){
			String storeCode = entry.getKey();
			String storeName = "";
			List<DrtMallOrderDetail> orderDetailList = entry.getValue();
			if(!CollectionUtils.isEmpty(orderDetailList)){
				if(orderDetailList != null && orderDetailList.size() > 0){
					// 订单ID
					String orderId = UUIDUtil.generateUUID();
					// 订单编号
					String orderCode = ElectronicMallApiUtil.getOrderCode();
					// 订单总价：单位/分
					long orderTotalAmount = 0;
					// 订单总积分
					long orderTotalEarnings = 0;
					// 留言
					String orderMemberMemo = "";
					for (DrtMallOrderDetail drtMallOrderDetail : orderDetailList) {
						if(StringUtils.isBlank(storeName)){
							storeName = drtMallOrderDetail.getStoreName();
						}
						if(StringUtils.isBlank(orderMemberMemo) && itemMap.get(drtMallOrderDetail.getSkuCode()) != null){
							orderMemberMemo = itemMap.get(drtMallOrderDetail.getSkuCode()).get("orderMemberMemo");
						}
						drtMallOrderDetail.setOrderId(orderId);
						drtMallOrderDetail.setOrderCode(orderCode);
						orderTotalAmount += drtMallOrderDetail.getSubtotalPrice();
						orderTotalEarnings += drtMallOrderDetail.getSubtotalEarnings();
					}
					DrtMallOrder drtMallOrder = new DrtMallOrder();
					drtMallOrder.setId(orderId);
					drtMallOrder.setOrderCode(orderCode);
					drtMallOrder.setAccountId(drtAccount.getAccountId());
					drtMallOrder.setNickname(drtAccount.getNickname());
					drtMallOrder.setSubmitTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
					drtMallOrder.setOrderTotalAmount(orderTotalAmount);
					drtMallOrder.setOrderTotalEarnings(orderTotalEarnings);
					// 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
					drtMallOrder.setOrderState(1);
					drtMallOrder.setStoreCode(storeCode);
					drtMallOrder.setStoreName(storeName);
					// 固定配置业务字段
					drtMallOrder.setBusinessActivityId(drtShopConfigMap.get("BUSINESS_ACTIVITY_ID"));
					drtMallOrder.setExpenseProjectId(drtShopConfigMap.get("EXPENSE_PROJECT_ID"));
					drtMallOrder.setBudgetSubjectId(drtShopConfigMap.get("BUDGET_SUBJECT_ID"));
					drtMallOrder.setDataArea(drtShopConfigMap.get("DATA_AREA"));
					drtMallOrder.setReimbursePersonId(drtShopConfigMap.get("REIMBURSE_PERSON_ID"));
					drtMallOrder.setReimbursePerson(drtShopConfigMap.get("REIMBURSE_PERSON"));
					drtMallOrder.setReimburseMobile(drtShopConfigMap.get("REIMBURSE_MOBILE"));
					drtMallOrder.setApplyPeopleId(drtShopConfigMap.get("APPLY_PEOPLE_ID"));
					drtMallOrder.setApplyPeople(drtShopConfigMap.get("APPLY_PEOPLE"));
					drtMallOrder.setApplyMobile(drtShopConfigMap.get("APPLY_MOBILE"));
					drtMallOrder.setInvoiceType(NumberUtils.toInt(drtShopConfigMap.get("2"), 2));
					drtMallOrder.setInvoiceTitle(drtShopConfigMap.get("INVOICE_TITLE"));
					drtMallOrder.setInvoiceContent(drtShopConfigMap.get("INVOICE_CONTENT"));
					drtMallOrder.setDeliveryType(NumberUtils.toInt(drtShopConfigMap.get("DELIVERY_TYPE"), 1));
					drtMallOrder.setCompanyName(drtShopConfigMap.get("COMPANY_NAME"));
					// 收货人信息
					drtMallOrder.setConsigneeId(drtAccount.getAccountId());
					drtMallOrder.setConsigneeName(drtShopAddress.getName());
					// 收货人手机号码（固话或手机有1项必填）
					drtMallOrder.setConsigneeTelephone("");
					drtMallOrder.setConsigneeMobile(drtShopAddress.getPhone());
					drtMallOrder.setConsigneeZip(drtShopAddress.getPostcode());
					drtMallOrder.setConsigneeProvinceId(drtShopAddress.getAddressLevel1Id());
					drtMallOrder.setConsigneeProvinceName(drtShopAddress.getAddressLevel1());
					drtMallOrder.setConsigneeCityId(drtShopAddress.getAddressLevel2Id());
					drtMallOrder.setConsigneeCityName(drtShopAddress.getAddressLevel2());
					drtMallOrder.setConsigneeCountyId(drtShopAddress.getAddressLevel3Id());
					drtMallOrder.setConsigneeCountyName(drtShopAddress.getAddressLevel3());
					drtMallOrder.setConsigneeTownId("");
					drtMallOrder.setConsigneeTownName("");
					drtMallOrder.setConsigneeAddressDetail(drtShopAddress.getAddressOther());
					drtMallOrder.setOrderMemberMemo(orderMemberMemo);
					drtMallOrder.setCreateTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
					drtMallOrder.setCreatorId(drtAccount.getAccountId());
					drtMallOrder.setCreatorName(drtAccount.getNickname());
					drtMallOrder.setLastModifierTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
					drtMallOrder.setLastModifierId(drtAccount.getAccountId());
					drtMallOrder.setLastModifierName(drtAccount.getNickname());
					// 是否删除：0-是 1-否
					drtMallOrder.setIsDelete(1);
					
					// 保存订单
					flag = drtMallOrderService.insert(drtMallOrder, orderDetailList);
					if(flag){
						orderIds.add(orderId);
					}
				}
			}
		}
		
		if(flag){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "保存成功！", orderIds);
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "保存失败！");
	}

	/**
	 * 订单确认收货
	 * 
	 * @param session
	 * @param request 
	 * 			orderId-订单ID（必填）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("synReceiptConfrim")
	public Map<String, Object> synReceiptConfrim(HttpSession session, HttpServletRequest request) throws Exception{
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		// 订单ID
		String orderId = request.getParameter("orderId");
		//参数校验
		if(StringUtils.isBlank(orderId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		// 查询订单
		DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
		if(drtMallOrder == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		if(!Objects.equals(drtAccount.getAccountId(), drtMallOrder.getAccountId())){
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
			/** 更新本地订单 */
			drtMallOrder.setOrderState(4);
			drtMallOrderService.update(drtMallOrder);
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "收货成功！");
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "收货失败！");
	}
	
	/**
	 * 取消订单
	 * 
	 * @param session
	 * @param request  orderId-订单ID（必填）
	 * @return
	 */
	@RequestMapping("cancleOrder")
	public Map<String, Object> cancleOrder(HttpSession session, HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		// 订单ID
		String orderId = request.getParameter("orderId");
		//参数校验
		if(StringUtils.isBlank(orderId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		// 查询订单
		DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
		if(drtMallOrder == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		if(!Objects.equals(drtAccount.getAccountId(), drtMallOrder.getAccountId())){
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
	 * 查询订单物流信息
	 * @param orderId 订单ID
	 * @return 订单物流信息
	 */
	@RequestMapping("getOrderWaybillDetail")
	public Map<String, Object> getOrderWaybillDetail(HttpServletRequest request){
		// 订单ID
		String orderId = request.getParameter("orderId");
		if(StringUtils.isBlank(orderId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		// 查询订单
		DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
		if(drtMallOrder == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		DrtMallOrderDetail drtMallOrderDetail = new DrtMallOrderDetail();
		drtMallOrderDetail.setOrderId(orderId);
		PageHelper.startPage(1, 1);
		List<DrtMallOrderDetail> drtMallOrderDetailList = drtMallOrderDetailService.selectList(drtMallOrderDetail);
		if(CollectionUtils.isEmpty(drtMallOrderDetailList)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单数据异常！");
		}
		
		// 调用南网商城接口
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		//订单编号
		paramMap.put("integralOrderCode", drtMallOrder.getOrderCode());						    		   
		JSONObject jsonObject = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.GET_ORDER_WAYBILL_DETAIL, paramMap);
		//调接口成功时
		if ("Y".equals(String.valueOf(jsonObject.get("success")))
				&& ErrorEnum.NORMAL.getResultCode().equals(
						String.valueOf(jsonObject.get("resultCode")))) {
			//配送进度信息List
			ArrayList<HashMap<String, Object>> lstLogisticsInfo = new ArrayList<HashMap<String, Object>>();
			JSONArray jsonArray = JSONArray.fromObject(String.valueOf(jsonObject.get("logisticsInfoList")));
			if(jsonArray != null && jsonArray.size() > 0){
				for ( int i=0, iSize=jsonArray.size(); i < iSize; i++){
					//配送进度信息map
					HashMap<String, Object> logisticsInfoMap = new HashMap<String, Object>();
					JSONObject jsonLogisticsInfo = jsonArray.getJSONObject(i);
					logisticsInfoMap.put("deliveryTime", String.valueOf(jsonLogisticsInfo.get("deliveryTime")));
					logisticsInfoMap.put("deliveryInfoProgress", String.valueOf(jsonLogisticsInfo.get("deliveryInfoProgress")));
					lstLogisticsInfo.add(logisticsInfoMap);
				}
			}
			Map<String, Object> result = new HashMap<String, Object>();
			//物流单号
			result.put("waybillId", String.valueOf(jsonObject.get("waybillId")));
			//物流商名称
			result.put("logisticsProviderName", String.valueOf(jsonObject.get("logisticsProviderName")));
			//imgURL
			result.put("imgUrl", drtMallOrderDetailList.get(0).getImgUrl());
			//物流配送信息集合
			result.put("logisticsInfoList", lstLogisticsInfo);
			//订单状态
			result.put("orderState", drtMallOrder.getOrderState());
			
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "物流查询成功！", result);
		}
		
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "物流查询失败！");
	}
	
	/**
	 * 获取用户订单列表
	 * 
	 * @param session
	 * @param request 
	 * 			orderState-订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单（可选）
	 * 			pageNumber-页码：默认第一页（必填）
	 * 			pageSize-每页显示数：默认10（可选）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderList")
	public Map<String, Object> orderList(HttpSession session, HttpServletRequest request) throws Exception{
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
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
		queryDrtMallOrder.setAccountId(drtAccount.getAccountId());
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
	 * @param request orderIds-订单ID json串（必填）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryOrderDetail")
	public Map<String, Object> queryOrderDetail(HttpSession session, HttpServletRequest request) throws Exception{
		
		// 多个订单集合
		List<Map<String, Object>> lstResultMap = new ArrayList<Map<String, Object>>();
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		// 订单ID json串
		String orderIds = request.getParameter("orderIds");
		//String orderIds = "35289528d7894ce286a5532d82097666,f2e30139056e4f7ca4f3f399129a524e";
		if(StringUtils.isBlank(orderIds)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID获取失败！");
		}
		String sbOrderIds = this.linkParameter(orderIds);
		// 查询订单
		List<DrtMallOrder> listDrtMallOrder = drtMallOrderService.selectByPrimaryKeys(sbOrderIds);
		if(CollectionUtils.isEmpty(listDrtMallOrder)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单不存在！");
		}
		for(DrtMallOrder drtMallOrder : listDrtMallOrder){
			if(!Objects.equals(drtAccount.getAccountId(), drtMallOrder.getAccountId())){
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
			orderResult.put("address", drtMallOrder.getConsigneeProvinceName() + drtMallOrder.getConsigneeCityName() + drtMallOrder.getConsigneeCountyName() + drtMallOrder.getConsigneeTownName() + drtMallOrder.getConsigneeAddressDetail());
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
			lstResultMap.add(orderResult);
		}
		
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "执行成功！", lstResultMap);
	}

	/**
	 * 获取商品信息列表，只返回上架商品
	 * 
	 * @param data 参数比如 1,2
	 * @author wenmingsen
	 * @return String 返回参数拼接String 例如'1','2'
	 * @throws Exception 出错抛出异常
	 * 
	 */
	private String linkParameter(String data) throws Exception {
		String [] strs = data.split(",");
		StringBuffer sb = new StringBuffer();
		for(int i = 0,isize = strs.length ;i < isize ; i++){
			sb.append("'"+strs[i]+"'");
			if(i < isize - 1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取商品信息列表，只返回上架商品
	 * 
	 * @param itemNumMap
	 *            Map<String, Map<String, String>> key-商品编码skuCode value-itemId、itemNum、skuCode
	 * @author xuchen
	 * @return List<DrtMallOrderDetail> 商品详情集合
	 * @throws Exception
	 *             出错抛出异常
	 * 
	 */
	private List<DrtMallOrderDetail> getDrtMallOrderDetailList(
			Map<String, Map<String, String>> itemNumMap) throws Exception {
		List<DrtMallOrderDetail> drtMallOrderDetailList = new ArrayList<>();
		Set<String> skuCodeSet = itemNumMap.keySet();
		String skuCodes = StringUtils.join(
				skuCodeSet.toArray(new String[skuCodeSet.size()]), ",");
		// 获取南网商品信息集
		LinkedHashMap<String, Object> skuCodeListMap = new LinkedHashMap<>();
		skuCodeListMap.put("skuCode", skuCodes);
		JSONObject jsonObject = electronicMallApiUtil.postJson(
				ElectronicMallApiUrlEnum.GET_SKU_INFO_LIST, skuCodeListMap);
		// 执行成功
		if ("Y".equals(jsonObject.get("success"))
				&& ErrorEnum.NORMAL.getResultCode().equals(
						jsonObject.get("resultCode"))) {
			// 商品SKU信息集合
			JSONArray skuInfoJsonArray = jsonObject.getJSONArray("skuList");
			if (!CollectionUtils.isEmpty(skuInfoJsonArray)) {
				// 获取积分商城配置项
				Map<String, String> drtShopConfigMap = drtShopConfigService
						.getDrtShopConfigMap();
				// 比例：元-->积分
				int rate = NumberUtils.toInt(drtShopConfigMap.get("RATE"), 1);

				for (int i = 0; i < skuInfoJsonArray.size(); i++) {
					// 获取单个商品SKU信息
					JSONObject skuInfoJson = skuInfoJsonArray.getJSONObject(i);
					String skuCode = String.valueOf(skuInfoJson.get("skuCode"));
					if(itemNumMap.get(skuCode) != null){
						String itemId = itemNumMap.get(skuCode).get("itemId");
						String skuId = String.valueOf(skuInfoJson.get("skuId"));
						String skuName = String.valueOf(skuInfoJson.get("skuName"));
						String purchaseCatId = String.valueOf(skuInfoJson
								.get("purchaseCatId"));
						String purchaseCatName = String.valueOf(skuInfoJson
								.get("purchaseCatName"));
						String brandId = String.valueOf(skuInfoJson.get("brandId"));
						String brandName = String.valueOf(skuInfoJson
								.get("brandName"));
						// 商品状态（-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3）
						String skuStatus = String.valueOf(skuInfoJson
								.get("skuStatus"));
						String price = String.valueOf(skuInfoJson.get("price"));
						String companyId = String.valueOf(skuInfoJson
								.get("companyId"));
						String companyName = String.valueOf(skuInfoJson
								.get("companyName"));
						String storeCode = String.valueOf(skuInfoJson
								.get("storeCode"));
						String storeName = String.valueOf(skuInfoJson
								.get("storeName"));
						String imgUrl = String.valueOf(skuInfoJson.get("imgUrl"));
						if ("3".equals(skuStatus)) {
							DrtMallOrderDetail drtMallOrderDetail = new DrtMallOrderDetail();
							drtMallOrderDetail.setItemId(itemId);
							drtMallOrderDetail.setSkuId(skuId);
							drtMallOrderDetail.setSkuCode(skuCode);
							drtMallOrderDetail.setSkuName(skuName);
							drtMallOrderDetail.setPurchaseCatId(purchaseCatId);
							drtMallOrderDetail.setPurchaseCatName(purchaseCatName);
							drtMallOrderDetail.setBrandId(brandId);
							drtMallOrderDetail.setBrandName(brandName);
							drtMallOrderDetail.setSkuPrice((long) (NumberUtils
									.toDouble(price, 0) * 100));
							drtMallOrderDetail.setBuyAmt(NumberUtils.toInt(
									itemNumMap.get(skuCode).get("itemNum") + "", 1));
							drtMallOrderDetail.setSkuEarnings((long) (NumberUtils
									.toDouble(price, 0) * rate));
							drtMallOrderDetail
							.setSubtotalPrice(drtMallOrderDetail
									.getSkuPrice()
									* drtMallOrderDetail.getBuyAmt());
							drtMallOrderDetail
							.setSubtotalEarnings(drtMallOrderDetail
									.getSkuEarnings()
									* drtMallOrderDetail.getBuyAmt());
							
							drtMallOrderDetail.setCompanyId(companyId);
							drtMallOrderDetail.setCompanyName(companyName);
							drtMallOrderDetail.setStoreCode(storeCode);
							drtMallOrderDetail.setStoreName(storeName);
							drtMallOrderDetail.setImgUrl(imgUrl);
							
							// 添加
							drtMallOrderDetailList.add(drtMallOrderDetail);
						} else {
							try {
								// 将非上架商品同步至系统商品
								DrtMallItem drtMallItem = new DrtMallItem();
								drtMallItem.setId(itemId);
								drtMallItem.setItemState(NumberUtils.toInt(skuStatus, 4));
								drtMallItemService.updateIfNotNull(drtMallItem);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return drtMallOrderDetailList;
	}
	
	/**
	 * 获取商品信息接口skuInfoList，只返回上架商品
	 * 
	 * @param Set
	 *            <String> skuCodeSet 商品ID集合
	 * @author xuchen
	 * @return Map<String, Map<String, String>> key-商品编码skuCode value-Map<String, String>（包含固定key：skuId、skuCode、skuName、storeCode、storeName、imgUrl、price）
	 */
	private Map<String, Map<String, String>> getSkuInfoList(Set<String> skuCodeSet) {
		Map<String, Map<String, String>> skuInfoMap = new HashMap<>();
		String skuCodes = StringUtils.join(skuCodeSet.toArray(new String[skuCodeSet.size()]),
				",");
		// 获取南网商品信息集
		LinkedHashMap<String, Object> skuCodeListMap = new LinkedHashMap<>();
		skuCodeListMap.put("skuCode", skuCodes);
		JSONObject jsonObject = electronicMallApiUtil.postJson(
				ElectronicMallApiUrlEnum.GET_SKU_INFO_LIST, skuCodeListMap);
		if ("Y".equals(jsonObject.get("success")) && ErrorEnum.NORMAL.getResultCode().equals(jsonObject.get("resultCode"))) {
			// 执行成功
			// 商品SKU信息集合
			JSONArray skuInfoJsonArray = jsonObject.getJSONArray("skuList");
			if(!CollectionUtils.isEmpty(skuInfoJsonArray)){
				for (int i = 0; i < skuInfoJsonArray.size(); i++) {
					// 获取单个商品SKU信息
					JSONObject skuInfoJson = skuInfoJsonArray.getJSONObject(i);
					String skuId = String.valueOf(skuInfoJson.get("skuId"));
					String skuCode = String.valueOf(skuInfoJson.get("skuCode"));
					String skuName = String.valueOf(skuInfoJson.get("skuName"));
					String purchaseCatId = String.valueOf(skuInfoJson
							.get("purchaseCatId"));
					String purchaseCatName = String.valueOf(skuInfoJson
							.get("purchaseCatName"));
					String brandId = String.valueOf(skuInfoJson.get("brandId"));
					String brandName = String.valueOf(skuInfoJson
							.get("brandName"));
					// 商品状态（-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3）
					String skuStatus = String.valueOf(skuInfoJson
							.get("skuStatus"));
					String price = String.valueOf(skuInfoJson.get("price"));
					String companyId = String.valueOf(skuInfoJson
							.get("companyId"));
					String companyName = String.valueOf(skuInfoJson
							.get("companyName"));
					String storeCode = String.valueOf(skuInfoJson
							.get("storeCode"));
					String storeName = String.valueOf(skuInfoJson
							.get("storeName"));
					String imgUrl = String.valueOf(skuInfoJson.get("imgUrl"));
					if("3".equals(skuStatus)){
						Map<String, String> skuInfo = new HashMap<>();
						skuInfo.put("skuId", skuId);
						skuInfo.put("skuCode", skuCode);
						skuInfo.put("skuName", skuName);
						skuInfo.put("purchaseCatId", purchaseCatId);
						skuInfo.put("purchaseCatName", purchaseCatName);
						skuInfo.put("brandId", brandId);
						skuInfo.put("brandName", brandName);
						skuInfo.put("price", price);
						skuInfo.put("companyId", companyId);
						skuInfo.put("companyName", companyName);
						skuInfo.put("storeCode", storeCode);
						skuInfo.put("storeName", storeName);
						skuInfo.put("imgUrl", imgUrl);
						skuInfoMap.put(skuCode, skuInfo);
					}
				}
			}
		}
		return skuInfoMap;
	}
	
	/**
	 * 过滤错误数据，返回最终的商品stuCode集合
	 * 
	 * @param itemJsonArray
	 *            [{itemId: '1', itemNum: 1, orderMemberMemo: '留言1'},{itemId:
	 *            '2', itemNum: 2, orderMemberMemo: '留言2'}]
	 * @return Map<String, Map<String, Object>> key-商品编码skuCode value-Map<String, Object> 包含三个key值{itemId、itemNum、skuCode、orderMemberMemo}
	 */
	private Map<String, Map<String, String>> getItemMap(JSONArray itemJsonArray) {
		Map<String, Map<String, String>> skuCodeItemMap = new HashMap<>();
		// 获取积分商城所有上架商品：上架商品才可正常交易
		DrtMallItem queryDrtMallItem = new DrtMallItem();
		// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
		queryDrtMallItem.setItemState(3);
		Map<String, DrtMallItem> drtMallItemMap = getDrtMallItemMap(queryDrtMallItem);
		if(itemJsonArray != null && itemJsonArray.size() > 0){
			for (int i = 0; i < itemJsonArray.size(); i++) {
				JSONObject item = itemJsonArray.getJSONObject(i);
				String itemId = String.valueOf(item.get("itemId"));
				int itemNum = NumberUtils.toInt(String.valueOf(item.get("itemNum")), 1);
				String orderMemberMemo = String.valueOf(item.get("orderMemberMemo"));
				// 1、数量大于0 2、商品ID不为空 3、该商品ID在数据库存在记录
				if (itemNum > 0 && StringUtils.isNotBlank(itemId)
						&& drtMallItemMap.containsKey(itemId)) {
					Map<String, String> itemMap = new HashMap<>();
					itemMap.put("itemId", itemId);
					itemMap.put("itemNum", itemNum + "");
					itemMap.put("skuCode", drtMallItemMap.get(itemId).getSkuCode());
					itemMap.put("orderMemberMemo", orderMemberMemo);
					skuCodeItemMap.put(drtMallItemMap.get(itemId).getSkuCode(), itemMap);
				}
			}
		}
		return skuCodeItemMap;
	}
	
	/**
	 * 通过查询条件获取商品
	 * 
	 * @param queryDrtMallItem
	 *            查询条件
	 * @author xuchen
	 * @return Map<String, DrtMallItem> 积分商城商品池Map<主键，实体>
	 * @throws Exception
	 */
	private Map<String, DrtMallItem> getDrtMallItemMap(
			DrtMallItem queryDrtMallItem) {
		List<DrtMallItem> drtMallItemList = drtMallItemService
				.selectList(queryDrtMallItem);
		Map<String, DrtMallItem> DrtMallItemMap = new HashMap<>();
		if (!CollectionUtils.isEmpty(drtMallItemList)) {
			for (DrtMallItem drtMallItem : drtMallItemList) {
				DrtMallItemMap.put(drtMallItem.getId(), drtMallItem);
			}
		}
		return DrtMallItemMap;
	}
}
