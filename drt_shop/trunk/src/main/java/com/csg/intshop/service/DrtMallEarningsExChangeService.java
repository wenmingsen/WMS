package com.csg.intshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.common.enums.ErrorEnum;
import com.csg.intshop.entity.DrtAccountVO;
import com.csg.intshop.entity.DrtFinAllEarningsRecord;
import com.csg.intshop.entity.DrtFinAllEarningsRecordHistory;
import com.csg.intshop.entity.DrtFinEarningsStatistics;
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.UUIDUtil;

/**
 * DrtMallEarningsExChangeService 积分兑换
 *
 * @author xuchen
 * @since 1.8
 * @version 2018年02月01日xuchen
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMallEarningsExChangeService {
	
	/** 日志 */
	private Logger log = LoggerFactory.getLogger(DrtMallEarningsExChangeService.class);
	
	/** 积分接口*/
	@Autowired
	private EarningsCore earningsCore;

	
	/** DrtMallOrderService 积分商城订单表 */
	@Autowired
	private DrtMallOrderService drtMallOrderService;
	

	/** DrtShopConfigService 积分商城配置表 */
	@Autowired
	private DrtShopConfigService drtShopConfigService;

	/** DrtMallOrderDetailService 积分商城订单明细表 */
	@Autowired
	private DrtMallOrderDetailService drtMallOrderDetailService;
	
	/** DrtMallItemService 积分商城商品池 */
	@Autowired
	private DrtMallItemService drtMallItemService;

	/** 电子商城接口 工具类 */
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;
	
	/** 订单service */
	@Autowired	
	private	DrtAccountService drtAccountService;
	
	/** 分类收益统计表service */
	@Autowired	
	private	DrtFinEarningsStatisticsService drtFinEarningsStatisticsService;

	/** 积分收支明细历史service */
	@Autowired	
	private DrtFinAllEarningsRecordHistoryService drtFinAllEarningsRecordHistoryService;
	
	/** 积分收支明细service */
	@Autowired	
	private DrtFinAllEarningsRecordService drtFinAllEarningsRecordService;
		
	/**
	 * 积分兑换
	 * @param totalEarnings 花费积分
	 * @param accountId 帐号
	 * @return 兑换结果
	 * @throws Exception 
	 */
	public Boolean earningsExchange(DrtMallOrder drtMallOrder) throws Exception{
		//兑换积分
		Map<String, Object> objMap = updateEarningsFromExchange(drtMallOrder.getId());
		//兑换成功更新订单状态
		if(SystemConstants.RESULT_CODE_SUCCESS.equals(objMap.get("state"))){
			//扣积分成功，封装参数，调用下单接口
			LinkedHashMap<String, Object> paramMap = getParamMap(drtMallOrder);
			JSONObject jsonObject = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.CONFIRM_ORDER, paramMap);
			if("Y".equals(jsonObject.get("success")) && ErrorEnum.NORMAL.getResultCode().equals(jsonObject.get("resultCode"))){
				//修改订单状态
				drtMallOrder.setOrderState(2);
				drtMallOrderService.updateIfNotNull(drtMallOrder);
				return true;
			}
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	// 手动回滚事务
		return false;
	}
	
	/**
	 * 封装下单接口参数
	 * @param drtMallOrder
	 * @return paramMap 参数
	 * @throws Exception 
	 */
	private LinkedHashMap<String, Object> getParamMap(DrtMallOrder drtMallOrder) throws Exception{
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		/** 查询积分商城配置项*/
		Map<String,String> drtShopConfigMap = drtShopConfigService.getDrtShopConfigMap();
		//积分商城订单编号
		paramMap.put("integralOrderCode", drtMallOrder.getOrderCode());
		//下单时间, 格式：yyyy-mm-dd hh:mm:ss
		paramMap.put("submitTime", DateTimeUtils.converDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//订单总金额。（积分商城转换成对应的订单总金额）
		paramMap.put("orderTotalAmount", drtMallOrder.getOrderTotalAmount()*SystemConstants.DIVISION100+"");
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
		paramMap.put("skuItemList", skuItemList);
		return paramMap;
	}
	
	/**
	 * 获取商品明细集合
	 * @return skuItemList 商品明细集合
	 * @throws Exception 
	 */
	private List<Map<String,Object>> getSkuItemList(DrtMallOrder drtMallOrder) throws Exception{
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
				
				// 更新兑换量
				DrtMallItem drtMallItem = drtMallItemService.selectByPrimaryKey(drtMallOrderDetail.getItemId());
				if(drtMallItem != null){
					drtMallItem.setExchangeRate(NumberUtils.toInt(drtMallItem.getExchangeRate() + "", 0) + 1);
					drtMallItemService.updateIfNotNull(drtMallItem);
				}
			}
		}
		return skuItemList;
	}
	
	/**
	 * 商城兑换消耗积分更新方法
	 * @param accountId 账户ID
	 * @param earnings 积分
	 * @param plusminus 正负值 1-正 0-负
	 * @author xuchen
	 * @return resultMap code标识码 msg信息
	 * @throws Exception
	 */
	@Transactional
	public Map<String, Object> updateEarningsFromExchange(String orderId) throws Exception{
		try{
			//订单ID为空
			if(StringUtils.isBlank(orderId)){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID为空", "");
			}
			DrtMallOrder drtMallOrder = drtMallOrderService.selectByPrimaryKey(orderId);
			//订单ID不存在
			if(drtMallOrder == null ||drtMallOrder.getOrderTotalEarnings() == null){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID不存在", "");
			}
			//电融通ID
			String accountId = drtMallOrder.getAccountId();
			//订单积分
			Integer earnings = drtMallOrder.getOrderTotalEarnings().intValue();
			//订单号
			String orderNo = drtMallOrder.getOrderCode();
			//订单状态
			Integer orderState = drtMallOrder.getOrderState();
			//校验订单状态
			if(1 != orderState){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单状态异常", "");
			}
			//根据用户ID查询用户
			DrtAccountVO drtAccount=drtAccountService.selectByPrimaryKey(accountId);
			//账户不存在
			if(drtAccount == null){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ACCOUNT_NOT_EXIST, "");
			}
			//积分余额不足
			if(drtAccount.getTotalEarnings() == null || drtAccount.getTotalEarnings() < earnings){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_EARNINGS_NOT_ENOUGH, "");
			}
			if(earnings <= 0){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_MONEY_NULL, "");
			}
			//字段赋值
			DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory = new DrtFinAllEarningsRecordHistory();
			DrtFinAllEarningsRecord drtFinAllEarningsRecord = new DrtFinAllEarningsRecord();
			drtFinAllEarningsRecordHistory.setPoEarningsRecordId(UUIDUtil.generateUUID());//主键
			drtFinAllEarningsRecord.setPoEarningsRecordId(drtFinAllEarningsRecordHistory.getPoEarningsRecordId());//主键保持一致
			drtFinAllEarningsRecordHistory.setAccountId(accountId);
			drtFinAllEarningsRecordHistory.setEarnings(earnings);
			drtFinAllEarningsRecordHistory.setRecordTime(DateTimeUtils.getCurrentTime());
			drtFinAllEarningsRecordHistory.setPmType("0");
			drtFinAllEarningsRecordHistory.setFromType("9");
			drtFinAllEarningsRecordHistory.setFromName("积分兑换");
			drtFinAllEarningsRecordHistory.setAuditState(2);
			drtFinAllEarningsRecordHistory.setOrderNo(orderNo);
			
			drtFinAllEarningsRecord.setAccountId(accountId);
			drtFinAllEarningsRecord.setEarnings(earnings);
			drtFinAllEarningsRecord.setRecordTime(DateTimeUtils.getCurrentTime());
			drtFinAllEarningsRecord.setPmType("0");
			drtFinAllEarningsRecord.setFromType("9");
			drtFinAllEarningsRecord.setFromName("积分兑换");
			drtFinAllEarningsRecord.setAuditState(2);
			drtFinAllEarningsRecord.setOrderNo(orderNo);
			drtFinAllEarningsRecordHistory.setLastModifyTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
			drtFinAllEarningsRecordHistory.setCurrentEarnings(drtAccount.getTotalEarnings()-earnings);
			drtFinAllEarningsRecord.setLastModifyTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
			drtFinAllEarningsRecord.setCurrentEarnings(drtAccount.getTotalEarnings()-earnings);
			
			//2018-02-06 添加当前天字段供app积分走势图使用
			drtFinAllEarningsRecordHistory.setCurrDay(NumberUtils.toInt(DateTimeUtils.converDateToString(new Date(), "yyyyMMdd")));
			drtFinAllEarningsRecord.setCurrDay(NumberUtils.toInt(DateTimeUtils.converDateToString(new Date(), "yyyyMMdd")));
			
			//插入操作
			drtFinAllEarningsRecordHistoryService.insert(drtFinAllEarningsRecordHistory);
			drtFinAllEarningsRecordService.insert(drtFinAllEarningsRecord);
			
			drtAccount.setTotalEarnings(NumberUtils.toInt(drtAccount.getTotalEarnings() + "", 0) - earnings);
			
			//2018-03-01 维护下次截止日期到期积分字段
			drtAccount.setExpiryEarnings(NumberUtils.toInt(drtAccount.getExpiryEarnings() + "", 0) - earnings);
			
			drtAccountService.update(drtAccount);
			
			//2018-02-08 分类收益统计表插入数据供APP使用(积分商城兑换积分)
			updateEarningsStatistics(accountId,earnings,3,drtAccount.getTotalEarnings());
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, SystemConstants.SUCCESS_MSG_EARNINGS_UPDATE, "");
		}catch(Exception e){
			log.error("订单ID" + orderId + "积分兑换失败--", e);
		}
		// 手动回滚事务
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_EARNINGS_UPDATE, "");
	}
	
	/**
	 * 更新分类收益统计表
	 * @param accountId 账户ID
	 * @param earnings 积分
	 * @param type 1-交电费收益 2-活动奖励 3-商城兑换积分
	 * @author xuchen
	 * @return resultMap code标识码 msg信息
	 * @throws Exception
	 */
	public Map<String, Object> updateEarningsStatistics(String accountId,Integer earnings,Integer type,Integer totalEarnings) throws Exception{
		try{
			//accountID为空
			if(StringUtils.isBlank(accountId)){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "accountID为空", "");
			}
			//积分为空
			if(earnings == null||earnings < 0){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "更新分类收益统计表，积分值为空或小于零", "");
			}
			//类型有误
			if(type != 1 && type != 2 && type != 3){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "更新分类收益统计表，类型为空或小于零", "");
			}
			//根据accountID查询记录
			DrtFinEarningsStatistics drtFinEarningsStatisticsForQuery = new DrtFinEarningsStatistics();
			drtFinEarningsStatisticsForQuery.setAccountId(accountId);
			List<DrtFinEarningsStatistics> drtFinEarningsStatisticsList = drtFinEarningsStatisticsService.selectList(drtFinEarningsStatisticsForQuery);
			//存在更新，不存在新增
			if(drtFinEarningsStatisticsList.size() > 0){
				DrtFinEarningsStatistics drtFinEarningsStatistics = drtFinEarningsStatisticsList.get(0);
				if(1 == type){
					drtFinEarningsStatistics.setPayElecEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getPayElecEarnings() + "", 0) + earnings);
				}else if(2 == type){
					drtFinEarningsStatistics.setActivityEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getActivityEarnings() + "", 0) + earnings);
				}else if(3 == type){
					drtFinEarningsStatistics.setExchangeEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getExchangeEarnings() + "", 0) + earnings);
				}
				drtFinEarningsStatistics.setTotalEarnings(totalEarnings);
				drtFinEarningsStatistics.setUpdateTime(DateTimeUtils.getCurrentTime());
				drtFinEarningsStatisticsService.updateIfNotNull(drtFinEarningsStatistics);
			}else{
				DrtFinEarningsStatistics drtFinEarningsStatistics = new DrtFinEarningsStatistics();
				if(1 == type){
					drtFinEarningsStatistics.setPayElecEarnings(earnings);
				}else if(2 == type){
					drtFinEarningsStatistics.setActivityEarnings(earnings);
				}else if(3 == type){
					drtFinEarningsStatistics.setExchangeEarnings(earnings);
				}
				drtFinEarningsStatistics.setId(UUIDUtil.generateUUID());
				drtFinEarningsStatistics.setAccountId(accountId);
				drtFinEarningsStatistics.setTotalEarnings(totalEarnings);
				drtFinEarningsStatistics.setUpdateTime(DateTimeUtils.getCurrentTime());
				drtFinEarningsStatisticsService.insert(drtFinEarningsStatistics);
			}
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "更新分类收益统计表成功", "");
		}catch(Exception e){
			log.error("accountID" + accountId + "更新分类收益统计表失败--", e);
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "更新分类收益统计表失败", "");
	}
}