
package com.csg.statistics.job;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.service.EarningsExpiryService;
import com.csg.statistics.service.finance.FinanceService;
import com.csg.statistics.service.shop.ShopService;
import com.csg.statistics.util.RedisUtil;
import com.csg.statistics.util.StringUtils;

/**
 * 任务注册
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2017年12月27日 曾令鹏
 */
@Component
public class JobRegister {
    
    /** 日志 */
    private Logger log = LoggerFactory.getLogger(JobRegister.class);
    
    /** 查询欠费失败记录标记 **/
    private static boolean fiarFlag =true;
    /** 批扣文件标记  */
    private static boolean pkflag = true;
    
    /** 业务用户统计标记*/
    private static boolean oneDayJobFlag = true;
    
    /** 用户监控统计标记*/
    private static boolean userMonitorComputingJobFlag = true;
    
    /** 积分收益标记*/
    private static boolean pointsEarningsComputingJobFlag = true;
    
    /** 留存率标记*/
    private static boolean userRetentionRecordComputingJobFlag = true;
    
    /** 访客来源标记*/
    private static boolean visitorRegionJobFlag = true;
    
    /** 资金监控标记*/
    private static boolean fundComputingJobFlag = true;
    
    /** 积分监控标记*/
    private static boolean pointsMonitorJobFlag = true;
    
    /** 站内消息标记*/
    private static boolean insideMessageComputingJobFlag = true;
    
    /** 站内消息（日）标记*/
    private static boolean insideMessageComputingJobByDayFlag = true;
    
    /** 站内消息（月）标记*/
    private static boolean insideMessageComputingJobByMonthFlag = true;
    
    /** 站内消息（删除）标记*/
    private static boolean insideMessageComputingJobDeleteFlag = true;
    
    /** 到期积分清算任务标记*/
    private static boolean earningsExpiryJobFlag = true;
    
    /** 资产代办标记*/
    private static boolean finflag = true;
    
    /** 电费汇总抵扣超时处理标记*/
    private static boolean hzdkTimeOutDisposeflag = true;
    
    /**获取南网商城商品池商品信息标志*/
    
    private static boolean mallItemComputingJobFlag = true;
    
    /** 访客地域分布 计算任务 */
    @Autowired
    private VisitorRegionJob visitorRegionJob;
    
    
    @Autowired
    private RedisUtil redisUtil;
   
    
    /** 用户留存率 计算任务 */
    @Autowired
    private UserRetentionRecordComputingJob userRetentionRecordComputingJob;
    
    /** 积分收益计算任务 */
    @Autowired
    private PointsEarningsComputingJob pointsEarningsComputingJob;
    
    /** 用户监控任务 */
    @Autowired
    private UserMonitorComputingJob userMonitorComputingJob;
    
    /** 每天积分类型种类及总数统计任务 */
    @Autowired
    private PointsMonitorJob pointsMonitorJob;
    
    /** 站内消息 */
    @Autowired
    InsideMessageComputingJob insideMessageComputingJob;
    
    /** 资金监控统计任务 */
    @Autowired
    private FundComputingJob fundComputingJob;
    
    /** 到期积分清算任务任务 */
    @Autowired
    private EarningsExpiryService earningsExpiryJob;
    
    @Autowired
    private FinanceJob financeJob;
    
    @Autowired
    private OneDayJob oneDayJob;
    
    @Autowired
    private DrtElecDealPkwjJob drtElecDealPkwjJob;
    
    @Autowired
    private DrtDealFailInquiryArrearsJob drtDealFailInquiryArrearsJob;
    
    /**积分商城获取南网商城商品池商品信息任务*/
    @Autowired
    private ShopService shopService;
    
    /**积分商城获取南网商城商品池商品信息任务*/
    @Autowired
    private FinanceService financeService;
    
    /**
     * 处理查询欠费失败记录：每5分钟执行一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void dealFailInquiryArrearsRecord(){
    	log.info("dealFailInquiryArrearsRecord处理查询欠费失败记录进入："+fiarFlag);
    	if(fiarFlag){
    		fiarFlag =false;
    		drtDealFailInquiryArrearsJob.dealFailInquiryArrears();
    		fiarFlag =true;
    	}
    	log.info("dealFailInquiryArrearsRecord处理查询欠费失败记录完成："+fiarFlag);
    }
    /**
     * 资产代办业务（01-交易）：每5分钟执行一次
     * 
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void startFinanceJob() {
    	log.info("startFinanceJob资产代办业务执行....:"+finflag);
    	if (finflag) {
			finflag = false;
			financeJob.finUnfinishedWorkJob();
			finflag = true;
		}
    	log.info("startFinanceJob资产代办业务完成....:"+finflag);
    }
    
    /**
     * 批扣文件处理：每5分钟执行一次
     * 
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void dealPkwj() {
    	log.info("dealPkwj批扣文件处理进入:"+pkflag);
    	if (pkflag) {
			pkflag = false;
    		drtElecDealPkwjJob.dealPkwj();
    		pkflag = true;
		}
    	log.info("dealPkwj批扣文件完成:"+pkflag);
    }
    
    /**
     * 业务用户统计（新增用户数量，实名认证用户数量，绑定银行卡用户数量，活跃度用户数量）
     * 
     * 00:15
     * 
     */
    @Scheduled(cron = "0 15 0 * * ?")
    public void startOneDayJob() {
    	log.info("startOneDayJob业务用户统计（新增用户数量，实名认证用户数量，绑定银行卡用户数量，活跃度用户数量）进入");
    	if (oneDayJobFlag) {
    		oneDayJobFlag = false;
			oneDayJob.statisticsBusinessUser();
			oneDayJobFlag = true;
		}
    }
    
    /**
     * 用户监控登陆次数统计 00:30
     *
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void startUserMonitorComputingJob() {
    	log.info("userMonitorComputingJob用户监控进入");
    	if (userMonitorComputingJobFlag) {
    		userMonitorComputingJobFlag = false;
    		userMonitorComputingJob.userMonitorLoginComputing();
    		userMonitorComputingJobFlag = true;
		}
        
        // 积分收益计算 01:00
    	log.info("pointsEarningsComputingJob积分收益计算01:00进入");
    	if (pointsEarningsComputingJobFlag) {
    		pointsEarningsComputingJobFlag = false;
    		pointsEarningsComputingJob.pointsEarningsComputing();
    		pointsEarningsComputingJobFlag = true;
		}
        
        // 留存率计算：01:30
    	log.info("userRetentionRecordComputingJob留存率计算：01:30进入");
    	if (userRetentionRecordComputingJobFlag) {
    		userRetentionRecordComputingJobFlag = false;
    		userRetentionRecordComputingJob.start();
    		userRetentionRecordComputingJobFlag = true;
		}
        
        // 访客地域计算：02:00
    	log.info("visitorRegionJob访客地域计算：02:00进入");
    	if (visitorRegionJobFlag) {
    		visitorRegionJobFlag = false;
    		visitorRegionJob.start();
    		visitorRegionJobFlag = true;
		}
        
    }
    
    /**
     * 资金计算：02:30
     * 
     */
    @Scheduled(cron = "0 30 2 * * ?")
    public void startFundComputingJob() {
        try {
        	log.info("fundComputingJob资金计算：02:30进入");
        	if (fundComputingJobFlag) {
        		fundComputingJobFlag = false;
        		fundComputingJob.fundComputing();
        		fundComputingJobFlag = true;
    		}
        } catch (Exception e) {
            log.error("资金监控统计任务 - {}" + e.getMessage());
        }
    }
    
    /**
     * 每天积分类型种类及总数统计任务：03:00
     * 
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void start() {
        try {
        	log.info("pointsMonitorJob每天积分类型种类及总数统计任务：03:00进入");
        	if (pointsMonitorJobFlag) {
        		pointsMonitorJobFlag = false;
        		pointsMonitorJob.pointsTypesTatistics();
        		pointsMonitorJobFlag = true;
    		}
        } catch (Exception e) {
            log.error("积分类型种类及总数统计 - {}" + e.getMessage());
        }
    }
    
    /**
     * 小时发送消息插入
     * 
     */
    @Scheduled(cron="0 0 0/1 * * ?")//每1个小时 
    public void insertComputingJob(){
        try {
        	log.info("insideMessageComputingJob小时发送消息插入进入");
        	if (insideMessageComputingJobFlag) {
        		insideMessageComputingJobFlag = false;
        		//定时发送消息插入
            	insideMessageComputingJob.insertTimingNewsComputingJob();
            	//循环发送消息插入
            	insideMessageComputingJob.insertHourLoopNewsComputingJob();
        		insideMessageComputingJobFlag = true;
    		}
        } catch (Exception e) {
            log.error("站内消息异常 - {}" + e.getMessage());
        }
    }
    
    /**
     * 天循环发送消息插入
     * 
     */
    @Scheduled(cron="0 15 03 * * ?")//每天上午03:15 触发
    public void insertDayLoopNewsComputingJob(){
        try {
        	//天循环发送消息插入
        	log.info("insideMessageComputingJob天循环发送消息插入进入");
        	if (insideMessageComputingJobByDayFlag) {
        		insideMessageComputingJobByDayFlag = false;
        		insideMessageComputingJob.insertDayLoopNewsComputingJob();
        		insideMessageComputingJobByDayFlag = true;
    		}
        } catch (Exception e) {
            log.error("站内消息异常 - {}" + e.getMessage());
        }
    }
    
    /**
     * 月循环发送消息插入
     * 
     */
    @Scheduled(cron="0 15 04 1 * ?")//  每月1日上午04:15触发
    public void insertMonthLoopNewsComputingJob(){
    	
	    try {
	        //月循环发送消息插入
	    	log.info("insideMessageComputingJob月循环发送消息插入进入");
        	if (insideMessageComputingJobByMonthFlag) {
        		insideMessageComputingJobByMonthFlag = false;
        		insideMessageComputingJob.insertMonthLoopNewsComputingJob();
        		insideMessageComputingJobByMonthFlag = true;
    		}
	    } catch (Exception e) {
	        log.error("站内消息异常 - {}" + e.getMessage());
	    }
    }
    
	/**
	 * 删除最近一周站前消息记录
	 *
	 */
    @Scheduled(cron="0 30 05 * * ?") //每天凌晨05:30分
	public void deleteWeekNewsComputingJob() {
		
		try{
	    	log.info("insideMessageComputingJob删除最近一周站前消息记录进入");
        	if (insideMessageComputingJobDeleteFlag) {
        		insideMessageComputingJobDeleteFlag = false;
        		insideMessageComputingJob.deleteWeekNewsComputingJob();
        		insideMessageComputingJobDeleteFlag = true;
    		}
		}catch(Exception e){
			log.error("站内消息异常 - {}" + e.getMessage());
		}
	}
    
	/**
	 * 获取南网商城商品池商品信息(调用drtShop微服务中MallItemComputingJob.class)
	 *
	 */
    @Scheduled(cron="0 45 05 * * ?") //每天凌晨05:45分
	public Map<String, Object> getMallItemComputingJob() {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
	    	log.info("mallItemComputingJob-{}:获取南网商城商品池商品信息");
        	if (mallItemComputingJobFlag) {
        		mallItemComputingJobFlag = false;
        		resultMap = shopService.getMallItem();
        		mallItemComputingJobFlag = true;
    		}
        	return resultMap;
		}catch(Exception e){
			log.error("获取南网商城商品池商品信息异常 -{}" + e.getMessage());
			return resultMap;
		}
	}
    
    /**
	 * 到期积分清算任务
	 *
	 */
    @Scheduled(cron="0 00 06 30 6 ?") //每年6月30日早上六点执行
	public void earningsExpiryJob630() {
		try{
	    	log.info("earningsExpiryJob到期积分清算任务进入");
        	if (earningsExpiryJobFlag) {
        		earningsExpiryJobFlag = false;
        		earningsExpiryJob.deductExpiryEarnings();
        		earningsExpiryJob.calculateNextTimeExpiryEarnings();
        		earningsExpiryJobFlag = true;
    		}
		}catch(Exception e){
			log.error("到期积分清算任务 - {}" + e.getMessage());
		}
	}
    
    /**
	 * 到期积分清算任务
	 *
	 */
    @Scheduled(cron="0 00 06 31 12 ?") //每年12月31日早上六点执行
	public void earningsExpiryJob1231() {
		try{
	    	log.info("earningsExpiryJob到期积分清算任务进入");
        	if (earningsExpiryJobFlag) {
        		earningsExpiryJobFlag = false;
        		earningsExpiryJob.deductExpiryEarnings();
        		earningsExpiryJob.calculateNextTimeExpiryEarnings();
        		earningsExpiryJobFlag = true;
    		}
		}catch(Exception e){
			log.error("到期积分清算任务 - {}" + e.getMessage());
		}
	}
    
    
    /**
     * 定时查询redis中汇总抵扣的返回结果
     */
    @Scheduled(cron="0/30 * * * * ?")//每30s执行一次
	public void getDKResultOnRedis() {
		try {
			log.info("进入统计定时获取汇总抵扣结果并处理hzdkResult");
			if (hzdkTimeOutDisposeflag) {
				// 定义redis中key格式
				String hzdkKey = SystemConstants.HZDK_KEY + "*:*";// 汇总抵扣redis的key
				log.info("redis获取汇总抵扣hzdkKey:" + hzdkKey);
				//redis批量查询key值
				Map<String,Object> result = redisUtil.getPattern(hzdkKey);
				log.info("result:"+result);
				//循环解析查询到的结果
				for(Map.Entry<String,Object> res : result.entrySet()){
					String getKey = res.getKey();
					String getValue = (String) res.getValue();
					String[] ids = getKey.split(":"); 
					if (!StringUtils.isEmpty(getValue)) {
						JSONObject jsobject = JSONObject.fromObject(getValue);
						if (jsobject.containsKey("state")) {
							String state = jsobject.getString("state");
							if (!SystemConstants.HZDK_STATE_DOING.equals(state)) {
								String accountId = ids[1];
								String elecUserId = ids[2];
								String hzdkResult = jsobject.getString("result");
								// 调用分析汇总抵扣结果的方法
								try {
									financeService.disposeHZDKResult(accountId, elecUserId, hzdkResult);
									redisUtil.remove(getKey);
								} catch (Exception e) {
									log.error("汇总抵扣结果处理失败",e);
								}
							}else {
								log.info(getKey+"该键值交易所存储结果仍处在交易中，稍后查询...");
							}
						}
					}else {
						log.info(getKey+"该键值未存储相应结果，稍后查询处理...");
					}
				}
			}
		} catch (Exception e) {
			log.error("定时处理汇总抵扣超时任务失败 - {}",e);
		}
    }
}
