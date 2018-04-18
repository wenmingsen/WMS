/**
 * 
 */
package com.csg.intshop.controller.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallOrderDetailService;
import com.csg.intshop.service.DrtMallOrderService;
import com.csg.intshop.service.DrtShopConfigService;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.ResultMapHelper;

/**
 * 积分商城兑换接口
 * 
 * @author xuchen
 * 
 */
@RestController
@RequestMapping("/ins/api/exchange")
public class DrtShopExChangeController {
	
	/** DrtMallOrderService 积分商城订单表 */
	@Autowired
	private DrtMallOrderService drtMallOrderService;
	
	/** DrtMallOrderDetailService 积分商城订单详情表 */
	@Autowired
	private DrtMallOrderDetailService drtMallOrderDetailService;
	
	/** DrtShopConfigService 积分商城配置表 */
	@Autowired
	private DrtShopConfigService drtShopConfigService;
	
	/** 积分接口*/
	@Autowired
	private EarningsCore earningsCore;
	
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;
	
	/**
	 * 积分商城兑换接口
	 * @param request
	 * @author xuchen
	 * @return map
	 * 		   code 状态码（0;-1）
	 * 		   msg 异常信息提示
	 * @throws Exception 
	 */
	@RequestMapping("/exchange")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Map<String,Object> exchange(HttpServletRequest request) throws Exception{
		try {
			//积分商城订单编号
			String[] orderIds = request.getParameterValues("orderIds");
			//返回Map
			Map<String,Object> resultMap = new HashMap<String, Object>();
			//参数校验
			if(orderIds == null||orderIds.length < 1){//积分商城订单ID为空
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ORDERID_NULL, "");
			}
			//查询积分商城订单ID
			String strIds = StringUtils.join(orderIds,",");
			List<DrtMallOrder> drtMallOrderList = drtMallOrderService.selectByPrimaryKeys(strIds);
			if(drtMallOrderList.size() == strIds.length()){
				//扣积分
				for(DrtMallOrder drtMallOrder:drtMallOrderList){
					resultMap = earningsExchange(drtMallOrder);
					if("-1".equals(resultMap.get("state"))){
						return resultMap;
					}else{
						//扣积分成功，封装参数，调用下单接口
						LinkedHashMap<String, String> paramMap = getParamMap(drtMallOrder);
						JSONObject jsonObject = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.CONFIRM_ORDER, paramMap);
						if("Y".equals(String.valueOf(jsonObject.get("success")))){
							//修改订单状态
							drtMallOrder.setOrderState(2);
							drtMallOrderService.updateIfNotNull(drtMallOrder);
							return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, SystemConstants.EXCHANGE_SUCCESS, "");
						}else{
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	// 手动回滚事务
							return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, String.valueOf(jsonObject.get("resultMessage")), "");
						}
					}
				}
			}else{
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ORDERID_NOT_EXIST, "");
			}
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	// 手动回滚事务
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.EXCHANGE_FAILED, "");
		}
	}
	
	/**
	 * 积分兑换
	 * @param totalEarnings 花费积分
	 * @param accountId 帐号
	 * @return 兑换结果
	 */
	public Map<String,Object> earningsExchange(DrtMallOrder drtMallOrder){ 
		//返回Map
		Map<String, Object> objMap= new HashMap<String, Object>();
		//电融通用户ID
		String accountId = drtMallOrder.getAccountId();
		//订单总积分
		Long orderTotalEarnings = drtMallOrder.getOrderTotalEarnings();
		//订单号
		String orderCode = drtMallOrder.getOrderCode();
		try{
			if(StringUtils.isEmpty(accountId) || drtMallOrder.getOrderTotalEarnings() < 0){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "无法进行兑换", "");				
			}
			if(drtMallOrder.getOrderState()!=SystemConstants.ORDERSTATE){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单已被兑换,无需扣缴积分", "");				
			}
			//查询用户积分余额
			int earningsBalance = earningsCore.queryEarnings(accountId);
			if(earningsBalance < orderTotalEarnings){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "积分不足，余额为："+earningsBalance*SystemConstants.DIVISION100, "");	
			}else{
				objMap = earningsCore.updateEarningsFromExchange(accountId, Integer.parseInt(String.valueOf(orderTotalEarnings)), "0",orderCode);
			}
			if(objMap.get("code").equals(SystemConstants.RESULT_CODE_SUCCESS)){
				drtMallOrder.setOrderState(2);
				drtMallOrderService.updateIfNotNull(drtMallOrder);
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "兑换成功", "");
			}
		}catch(Exception e){			
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换失败", "");
		}
		 return objMap;
	}
	
	/**
	 * 封装下单接口参数
	 * @param drtMallOrder
	 * @return paramMap 参数
	 * @throws Exception 
	 */
	public LinkedHashMap<String, String> getParamMap(DrtMallOrder drtMallOrder) throws Exception{
		LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
		/** 查询积分商城配置项*/
		Map<String,String> drtShopConfigMap = drtShopConfigService.getDrtShopConfigMap();
		//积分商城订单编号
		paramMap.put("integralOrderCode", drtMallOrder.getOrderCode());
		//下单时间, 格式：yyyy-mm-dd hh:mm:ss
		paramMap.put("submitTime", DateTimeUtils.converDateToString(new Date(), "yyyy-mm-dd hh:mm:ss"));
		//订单总金额。（积分商城转换成对应的订单总金额）
		paramMap.put("orderTotalAmount", String.valueOf(drtMallOrder.getOrderTotalAmount()));
		//店铺编号
		paramMap.put("storeCode", drtMallOrder.getStoreCode());
		//业务活动id。由南网商城创建积分商城所需业务活动信息，积分商城下单时需传输该业务活动id给南网商城。
		paramMap.put("businessActivityId", drtShopConfigMap.get(SystemConstants.BUSINESS_ACTIVITY_ID));
		//费用项目id。由南网商城创建积分商城所需费用项目信息，积分商城下单时需传输该费用项目id给南网商城。
		paramMap.put("expenseProjectId", drtShopConfigMap.get(SystemConstants.EXPENSE_PROJECT_ID));
		//预算科目id。由南网商城创建积分商城所需预算科目信息，积分商城下单时需传输该预算科目id给南网商城。
		paramMap.put("budgetSubjectId", drtShopConfigMap.get(SystemConstants.BUDGET_SUBJECT_ID));
		//数据区域
		paramMap.put("dataArea", drtShopConfigMap.get(SystemConstants.DATA_AREA));
		//报账人ID，传电融通运营单位在南网商城用户id，南网商城需要对应报账人进行申请开票操作。
		paramMap.put("reimbursePersonId", drtShopConfigMap.get(SystemConstants.REIMBURSE_PERSON_ID));
		//报账人姓名
		paramMap.put("reimbursePerson", drtShopConfigMap.get(SystemConstants.REIMBURSE_PERSON));
		//报账人手机
		paramMap.put("reimburseMobile", drtShopConfigMap.get(SystemConstants.REIMBURSE_MOBILE));
		//申购人id，传电融通运营单位在南网商城用户id，南网商城需要形成申购单信息。
		paramMap.put("applyPeopleId", drtShopConfigMap.get(SystemConstants.APPLY_PEOPLE_ID));
		//申购人姓名，传电融通运营单位在南网商城用户名称。
		paramMap.put("applyPeople", drtShopConfigMap.get(SystemConstants.APPLY_PEOPLE));
		//申购人手机
		paramMap.put("applyMobile", drtShopConfigMap.get(SystemConstants.APPLY_MOBILE));
		//发票类型(2=增值税普通，3=增值税专用)
		paramMap.put("invoiceType", drtShopConfigMap.get(SystemConstants.INVOICE_TYPE));
		//发票抬头
		paramMap.put("invoiceTitle", drtShopConfigMap.get(SystemConstants.INVOICE_TITLE));
		//配送方式（1=快递，2=EMS，3=平邮，4=商户自有物流）
		paramMap.put("deliveryType", drtShopConfigMap.get(SystemConstants.DELIVERY_TYPE));
		//单位名称
		paramMap.put("companyName", drtShopConfigMap.get(SystemConstants.COMPANY_NAME));
		//发票内容。如果是增值税发票，发票内容为空，必须有发票明细；普通发票发票内容可以为办公用品、食品等
		paramMap.put("invoiceContent", drtShopConfigMap.get(SystemConstants.INVOICE_CONTENT));
		//收货人ID，电融通运营单位在南网商城用户id，南网商城需要对应收货人进行确认收货操作。
		paramMap.put("consigneeId", drtMallOrder.getConsigneeId());
		//收货人姓名，默认为积分商城收货人名称
		paramMap.put("consigneeName", drtMallOrder.getConsigneeName());
		//收货人固定电话（固话或手机有1项必填）
		paramMap.put("consigneeTelephone", drtMallOrder.getConsigneeTelephone());
		//收货人手机号码（固话或手机有1项必填）
		paramMap.put("consigneeMobile", drtMallOrder.getConsigneeMobile());
		//收货地址邮编
		paramMap.put("consigneeZip", drtMallOrder.getConsigneeZip());
		//收货人省份ID
		paramMap.put("consigneeProvinceId", drtMallOrder.getConsigneeProvinceId());
		//收货人城市ID
		paramMap.put("consigneeCityId", drtMallOrder.getConsigneeCityId());
		//收货人区县ID
		paramMap.put("consigneeCountyId", drtMallOrder.getConsigneeCountyId());
		//收货人镇ID
		paramMap.put("consigneeTownId", drtMallOrder.getConsigneeTownId());
		//收货人详细地址(地址需要省市区镇4级使用逗号分隔，4级城镇没有的时候使用null代替，示例：广东,深圳市,光明新区,null,公明街道振明路153号)
		paramMap.put("consigneeAddressDetail", drtMallOrder.getConsigneeAddressDetail());
		//给卖家留言
		paramMap.put("orderMemberMemo", drtMallOrder.getOrderMemberMemo());
		//商品明细集合
		List<Map<String,Object>> skuItemList = getSkuItemList(drtMallOrder);
		paramMap.put("skuItemList", JSONArray.fromObject(skuItemList).toString());
		return paramMap;
	}
	
	/**
	 * 获取商品明细集合
	 * @return skuItemList 商品明细集合
	 * @throws Exception 
	 */
	public List<Map<String,Object>> getSkuItemList(DrtMallOrder drtMallOrder) throws Exception{
		List<Map<String,Object>> skuItemList = new ArrayList<Map<String,Object>>();
		DrtMallOrderDetail drtMallOrderDetailForQuery = new DrtMallOrderDetail();
		drtMallOrderDetailForQuery.setOrderId(drtMallOrder.getId());
		List<DrtMallOrderDetail> drtMallOrderDetailList = drtMallOrderDetailService.selectList(drtMallOrderDetailForQuery);
		if(drtMallOrderDetailList != null && drtMallOrderDetailList.size() > 0){
			for(DrtMallOrderDetail drtMallOrderDetail:drtMallOrderDetailList){
				Map<String,Object> drtMallOrderDetailMap = new HashMap<String, Object>();
				drtMallOrderDetailMap.put("skuCode", drtMallOrderDetail.getSkuCode());
				drtMallOrderDetailMap.put("buyAmt", drtMallOrderDetail.getBuyAmt());
				drtMallOrderDetailMap.put("skuPrice", drtMallOrderDetail.getSkuPrice()*SystemConstants.DIVISION100+"");
				drtMallOrderDetailMap.put("totelAmt", drtMallOrderDetail.getBuyAmt()*drtMallOrderDetail.getSkuPrice()*SystemConstants.DIVISION100+"");
				skuItemList.add(drtMallOrderDetailMap);
			}
		}
		return skuItemList;
	}
	
}
