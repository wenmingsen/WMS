package com.csg.intshop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csg.intshop.entity.DrtAccountVO;
import com.csg.intshop.entity.DrtFinAllEarningsRecord;
import com.csg.intshop.entity.DrtFinAllEarningsRecordHistory;
import com.csg.intshop.entity.DrtFinEarningsStatistics;
import com.csg.intshop.entity.DrtShopCart;
import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtShopCartMapper;
import com.csg.intshop.mapper.DrtShopOrderMapper;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.UUIDUtil;
import com.github.pagehelper.StringUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * DrtShopCartService 积分商城购物车表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Service
public class DrtShopCartService{
	
	private Logger log = LoggerFactory.getLogger(DrtShopCartService.class);

	/**/
	@Autowired
	private EarningsCore earningsCore;
	
	/**积分商城订单表drtShopOrderMapper接口*/
	@Autowired
	private DrtShopOrderMapper drtShopOrderMapper;
	
	/**积分商城购物车表drtShopCartMapper接口*/
	@Autowired
	private DrtShopCartMapper drtShopCartMapper;
	
	/** 订单service */
	@Autowired	
	private	DrtShopOrderService drtShopOrderService;
	
	/** 用户service */
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
	 * 统计商品种类数量
	 * @return 数量
	 */
	@Transactional
	public Map<String, Object> countGoods(String accountId){
		int count;
		try{
			count = drtShopCartMapper.countGoods(accountId);
		}catch(Exception e){
			count = 0;
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "统计成功", count);
	}

	/**
	 * 积分兑换
	 * @param totalMoney 花费积分
	 * @param accountId 帐号
	 * @return 兑换结果
	 */
	@HystrixCommand(fallbackMethod="ensureExchangeFallBack")
	@Transactional
	public Map<String,Object> ensureExchange(String totalMoney,String orderId,String orderNo,String accountId){
		Map<String, Object> objMap= new HashMap<String, Object>();
		try{
			log.info("ensureExchange:" + orderNo);
			if(StringUtils.isEmpty(accountId) || StringUtils.isEmpty(totalMoney)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "无法进行兑换", "");				
				return objMap;
			}
			DrtShopOrder drtShopOrder1 = drtShopOrderMapper.selectByPrimaryKey(orderId);
			//log.info("drtShopOrder1:" + drtShopOrder1);
			if(drtShopOrder1.getOrderState()!=SystemConstants.ORDERSTATE){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "该订单已被兑换，请重新下单", "");				
				return objMap;
			}
			DrtAccountVO drtAccount = drtAccountService.selectByPrimaryKey(accountId);
			int totalNum = drtAccount.getTotalEarnings();
			if(totalNum>=Integer.parseInt(totalMoney)*SystemConstants.MULTIPLICATION100){
				objMap = updateEarningsFromExchange(orderId);
				log.info("updateEarningsFromExchange :" + objMap);
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "积分不足，余额为："+totalNum*SystemConstants.DIVISION100, "");	
				return objMap;
			}
			if(objMap.get("state").equals(SystemConstants.RESULT_CODE_SUCCESS)){
				DrtShopOrder drtShopOrder = new DrtShopOrder();
				drtShopOrder.setOrderState(2);
				drtShopOrder.setId(orderId);
				drtShopOrderMapper.updShopOrderState(drtShopOrder);
				return objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "兑换成功", "");
			}
		}catch(Exception e){			
			log.error("DrtShopCartService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换失败", "");
			return objMap;
		}
		 return objMap ;
	}
	
	public Map<String,Object> ensureExchangeFallBack(String totalMoney,String orderId,String orderNo,String accountId){
		Map<String,Object>  objMap=new HashMap<String,Object>();
		objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换失败", "");
		return objMap;
	}
	
	
	/**
	 * 保存
	 * 
	 * @param drtShopCart 积分商城购物车表
	 */
	@Transactional
	public Map<String, Object> insert(String id,String itemNum,String accountId,String name) {
		Map<String, Object> objMap= null;
		DrtShopCart drtShopCart = new DrtShopCart();
		int count = 0;
		try{
			if(StringUtils.isEmpty(id)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "加入购物车对象不能为空", "");				
	            return objMap;
			}
			drtShopCart = drtShopCartMapper.queryItemId(id,accountId);
			if(drtShopCart !=null && drtShopCart.getItemNum()> SystemConstants.ZERO){
				drtShopCart.setItemId(id);
				drtShopCart.setAccountId(accountId);
				if(StringUtil.isNotEmpty(itemNum)){
					drtShopCart.setItemNum(Integer.parseInt(itemNum));
				}else{
					drtShopCart.setItemNum(drtShopCart.getItemNum()+1);
				}
				count = drtShopCartMapper.updateByItemId(drtShopCart);
			}else{
				drtShopCart = new DrtShopCart();
				drtShopCart.setItemId(id);
				drtShopCart.setId(UUIDUtil.generateUUID());
				drtShopCart.setItemNum(1);
				drtShopCart.setAccountId(accountId);
				drtShopCart.setCreatorId(accountId);
				drtShopCart.setCreatorName(name);
				count = drtShopCartMapper.insert(drtShopCart);
			}
			if(count == Integer.parseInt(SystemConstants.COMMON_FLAG_TRUE)){				
	            objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "添加(减少)成功", "");
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加(减少)失败", "");
			}
		}catch(Exception e){			
			log.error("DrtShopCartService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加(减少)失败", "");
			return objMap;
		}
		return objMap;
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopCart 积分商城购物车表
	 * @throws Exception 出错抛出异常
	 */
	@Transactional
	public Map<String,Object> update(DrtShopCart drtShopCart){
		Map<String, Object> objMap= null;
		try{
			if(drtShopCart == null){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "加入购物车对象不能为空", "");				
	            return objMap;
			}
			drtShopCartMapper.update(drtShopCart);
		}catch(Exception e){			
			log.error("DrtShopCartService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加(减少)失败", "");
			return objMap;
		}
		return objMap  = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "加入购物车成功", "");
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城购物车表 主键ID
	 * @return 积分商城购物车表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	@Transactional
	public DrtShopCart selectByPrimaryKey(String id) throws Exception{
		return drtShopCartMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopCart 积分商城购物车表
	 * @throws Exception 出错抛出异常
	 */
	@Transactional
	public List<DrtShopCart> selectList(DrtShopCart drtShopCart) throws Exception{
		return drtShopCartMapper.selectList(drtShopCart);
	}
	
	/**
	 * 查询当前用户购物车商品
	 * 
	 * @param 用户id
	 * @return  当前用户购物车商品
	 * */
	@Transactional
	public List<DrtShopItem> selectShopCars(String accountId){
		return drtShopCartMapper.selectShopCars(accountId);
	}
	
	/**
	 * 删除购物车商品
	 * 
	 * @param ids 商品id集合
	 * */
	@Transactional
	public void delDrtShop(Map<String,Object> params){
		drtShopCartMapper.delDrtShop(params);
	}

	/**
	 * 清空购物车商品
	 * @param accountId 用户id
	 */
	@Transactional
	public void clearShopCart(String accountId) {
		try{
			drtShopCartMapper.clearShopCart(accountId);
		}catch(Exception e){			
			log.error("DrtShopCartService error:" + e.getMessage());
		}
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
			DrtShopOrder drtShopOrder = drtShopOrderService.selectByPrimaryKey(orderId);
			//订单ID不存在
			if(drtShopOrder == null ||drtShopOrder.getOrderEarnings() == null){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单ID不存在", "");
			}
			//电融通ID
			String accountId = drtShopOrder.getAccountId();
			//订单积分
			Integer earnings = drtShopOrder.getOrderEarnings();
			//订单号
			String orderNo = drtShopOrder.getOrderNo();
			//订单状态
			Integer orderState = drtShopOrder.getOrderState();
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
			
			drtAccount.setTotalEarnings(drtAccount.getTotalEarnings() - earnings);
			
			//2018-03-01 维护下次截止日期到期积分字段
			drtAccount.setExpiryEarnings(drtAccount.getExpiryEarnings() - earnings);
			
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
					drtFinEarningsStatistics.setPayElecEarnings(drtFinEarningsStatistics.getPayElecEarnings()+earnings);
				}else if(2 == type){
					drtFinEarningsStatistics.setActivityEarnings(drtFinEarningsStatistics.getActivityEarnings()+earnings);
				}else if(3 == type){
					drtFinEarningsStatistics.setExchangeEarnings(drtFinEarningsStatistics.getExchangeEarnings()+earnings);
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