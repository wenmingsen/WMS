package com.csg.statistics.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csg.statistics.bean.PhoneModel;
import com.csg.statistics.entity.DrtAccount;
import com.csg.statistics.entity.DrtAccountRecord;
import com.csg.statistics.entity.DrtBankAccount;
import com.csg.statistics.entity.DrtElecUser;
import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.entity.DrtMonitorBehaviorRecord;
import com.csg.statistics.entity.DrtMonitorLoginRecord;
import com.csg.statistics.entity.DrtMonitorVisitDuration;
import com.csg.statistics.mapper.DrtFinPoAccountMapper;
import com.csg.statistics.service.DrtAccountRecordService;
import com.csg.statistics.service.DrtAccountService;
import com.csg.statistics.service.DrtBankAccountService;
import com.csg.statistics.service.DrtElecUserService;
import com.csg.statistics.service.DrtMonitorBehaviorRecordService;
import com.csg.statistics.service.DrtMonitorLoginRecordService;
import com.csg.statistics.service.DrtMonitorVisitDurationService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.GetUUID;
import com.csg.statistics.util.PhoneUtil;

/**
 * 用户监控任务
 *
 * @author 温明森
 * @since 1.8
 * @version 2017年12月08日 温明森
 */
@Component
public class UserMonitorComputingJob {
	/**日志*/
	private Logger log = LoggerFactory.getLogger(UserMonitorComputingJob.class);
	
	/** drtAccountRecordService 用户登陆记录表Service */
	@Autowired
	DrtAccountRecordService drtAccountRecordService;
	
	/** drtMonitorLoginRecordService 用户登陆统计表Service */
	@Autowired
	DrtMonitorLoginRecordService drtMonitorLoginRecordService;
	
	/** drtMonitorVisitDurationService 用户登陆集中时间统计表Service */
	@Autowired
	DrtMonitorVisitDurationService drtMonitorVisitDurationService;
	
	/** drtAccountService 电融通Service */
	@Autowired
	DrtAccountService drtAccountService;
	
	/** drtMonitorBehaviorRecordService 用户行为Service */
	@Autowired
	DrtMonitorBehaviorRecordService drtMonitorBehaviorRecordService;
	
	/** drtElecUserService 绑定用电户行为Service */
	@Autowired
	DrtElecUserService drtElecUserService;
	
	/**drtBankAccountService 银行账户信息表（绑卡信息）*/
	@Autowired
	DrtBankAccountService drtBankAccountService;
	
	/**drtFinPoAccountService 理财账户信息表（预购）*/
	@Autowired
	private DrtFinPoAccountMapper drtFinPoAccountService;
	
	/**
	 * 用户监控登陆次数统计 每天00:30运行
	 *
	 */
	@Scheduled(cron="0 30 0 * * ?")
	public void userMonitorLoginComputing() {
				
		try{
			// T-1天 
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String presentDateStart = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), -1, simpleDateFormat);
				
			// 设置查询 
			DrtAccountRecord queryDrtAccountRecord = new DrtAccountRecord();
			//记录类型,0:登录,1:登出,2:修改密码,3:修改手势密码,4:修改支付密码
			queryDrtAccountRecord.setRecordType("0");
			//查询前一天的数据
			List<DrtAccountRecord> drtDrtAccountRecordList = drtAccountRecordService.selectListByDrtAccountRecord(queryDrtAccountRecord);
			//插入用户登陆统计表List
			List<DrtMonitorLoginRecord> drtDrtMonitorLoginRecordList = new ArrayList<DrtMonitorLoginRecord>();
			//插入用户集中访问表List
			List<DrtMonitorVisitDuration> drtDrtMonitorVisitDurationList = new ArrayList<DrtMonitorVisitDuration>();
			//
			if(drtDrtAccountRecordList != null){
				// 按用户ID分组
				Map<String, List<DrtAccountRecord>> groupByAccountId = drtDrtAccountRecordList.stream().collect(Collectors.groupingBy(DrtAccountRecord::getAccountId));
				if(groupByAccountId != null){
					Set<Map.Entry<String, List<DrtAccountRecord>>> entrySet = groupByAccountId.entrySet();
					for(Map.Entry<String, List<DrtAccountRecord>> entry : entrySet){
						//用户登陆统计对象
						DrtMonitorLoginRecord objDrtMonitorLoginRecord = new DrtMonitorLoginRecord();
						//用户集中访问对象
						DrtMonitorVisitDuration objDrtMonitorVisitDuration = new DrtMonitorVisitDuration();
						
						if(entry.getValue() != null && entry.getValue().size() > 0){
							List<DrtAccountRecord> lstDrtAccountRecord = entry.getValue();
							//该用户一天登陆的次数
							int iLoginNum = 0;
							String strPhone = "";
							String strIpAddr = ""; 
							if(lstDrtAccountRecord != null && lstDrtAccountRecord.size() > 0){
								for(DrtAccountRecord objAccountRecord:lstDrtAccountRecord){
									//判断用在那个时间登陆
									this.getTime(objAccountRecord , objDrtMonitorVisitDuration);
									strPhone = objAccountRecord.getPhone();
									strIpAddr = objAccountRecord.getIpAddress();
									iLoginNum++;
								}
							}
							DrtAccountRecord objDrtAccountRecord = lstDrtAccountRecord.get(0);
							
							//封装用户登陆统计对象
							objDrtMonitorLoginRecord.setRequestCount(0);
							objDrtMonitorLoginRecord.setId(GetUUID.getUuuid());
							objDrtMonitorLoginRecord.setPresentDate(NumberUtils.toInt(presentDateStart));
							objDrtMonitorLoginRecord.setPhone(strPhone);
							//手机号码信息读取
							if(StringUtils.isNotBlank(strPhone)){								
								PhoneModel phoneModel = PhoneUtil.getPhoneModel(strPhone);
								objDrtMonitorLoginRecord.setProvinceName(phoneModel.getProvinceName());
								objDrtMonitorLoginRecord.setCityName(phoneModel.getCityName());
							}
							objDrtMonitorLoginRecord.setIpAddr(strIpAddr);
							objDrtMonitorLoginRecord.setAccountId(objDrtAccountRecord.getAccountId());
							objDrtMonitorLoginRecord.setLoginNumber(iLoginNum);
							drtDrtMonitorLoginRecordList.add(objDrtMonitorLoginRecord);
							
							//封装用户集中访问对象
							objDrtMonitorVisitDuration.setId(GetUUID.getUuuid());
							objDrtMonitorVisitDuration.setPresentDate(NumberUtils.toInt(presentDateStart));
							objDrtMonitorVisitDuration.setAccountId(objDrtAccountRecord.getAccountId());
							drtDrtMonitorVisitDurationList.add(objDrtMonitorVisitDuration);
						}
					}
				}
				drtMonitorLoginRecordService.insertMonitorLoginRecords(drtDrtMonitorLoginRecordList);
				drtMonitorVisitDurationService.insertDrtMonitorVisitDurations(drtDrtMonitorVisitDurationList);
			}
		}catch(Exception e){
			log.error("job-用户登陆统计失败:", e.getMessage());
		}
	}
	
	/**
	 * 用户行为统计 每天00:15运行
	 *
	 */
	@Scheduled(cron="0 15 0 * * ?")
	public void monitorBehaviorRecordComputing() {
		try{
			//时间格式
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			//插入用户注册行为记录
			this.insertRegisterBehavior(simpleDateFormat);
			//插入绑定用电户号行为记录
			this.insertBindingUserBehavior(simpleDateFormat);
			//插入绑定银行卡行为记录
			this.insertBindingCardBehavior(simpleDateFormat);
			//插入预购电费行为记录
			this.insertPurchaseMoney(simpleDateFormat);
			
		}catch(Exception e){
			log.error("job-用户行为统计失败:", e.getMessage());
		}
		
	}

	/**
	 * 插入预购电费行为记录
	 *@param simpleDateFormat 时间格式
	 */
	private void insertPurchaseMoney(SimpleDateFormat simpleDateFormat) {
		try{
			//用户行为集合
			List<DrtMonitorBehaviorRecord> lstDrtMonitorBehaviorRecord = new ArrayList<DrtMonitorBehaviorRecord>();
			//查询昨天按用户ID分组数据
			List<DrtFinPoAccount> lstDrtFinPoAccount = drtFinPoAccountService.queryDrtFinPoAccount();
			if(lstDrtFinPoAccount != null && lstDrtFinPoAccount.size() > 0){
				for(DrtFinPoAccount objDrtFinPoAccount : lstDrtFinPoAccount){
					DrtMonitorBehaviorRecord objDrtMonitorBehaviorRecord = new DrtMonitorBehaviorRecord();
					objDrtMonitorBehaviorRecord.setAccountId(objDrtFinPoAccount.getAccountId());
					String strCreateTime = DateTimeUtils.getIntervalTime(objDrtFinPoAccount.getCreateTime().getTime(), 0, simpleDateFormat);
					objDrtMonitorBehaviorRecord.setBehaviorTime(NumberUtils.toLong(strCreateTime));
					//用户行为类型：1.注册，2.绑定用户号3.绑定银行卡4.开通预购电费
					objDrtMonitorBehaviorRecord.setBehaviorType(4);
					objDrtMonitorBehaviorRecord.setId(GetUUID.getUuuid());
					lstDrtMonitorBehaviorRecord.add(objDrtMonitorBehaviorRecord);
				}
				//批量插入用户行为对象
				drtMonitorBehaviorRecordService.insertMonitorBehaviorRecords(lstDrtMonitorBehaviorRecord);
			}
			
		}catch(Exception e){
			log.error("job-插入预购电费行为对象失败:", e.getMessage());
		}
	}

	/**
	 * 插入绑定银行卡行为记录
	 *@param simpleDateFormat 时间格式
	 */
	private void insertBindingCardBehavior(SimpleDateFormat simpleDateFormat) {
		try{
			//用户行为集合
			List<DrtMonitorBehaviorRecord> lstDrtMonitorBehaviorRecord = new ArrayList<DrtMonitorBehaviorRecord>();
			//查询昨天按用户ID分组数据
			List<DrtBankAccount> lstDrtBankAccount = drtBankAccountService.queryDrtBankAccount();
			if(lstDrtBankAccount != null && lstDrtBankAccount.size() > 0){
				for(DrtBankAccount objDrtBankAccount : lstDrtBankAccount){
					DrtMonitorBehaviorRecord objDrtMonitorBehaviorRecord = new DrtMonitorBehaviorRecord();
					objDrtMonitorBehaviorRecord.setAccountId(objDrtBankAccount.getAccountId());
					String strCreateTime = DateTimeUtils.getIntervalTime(objDrtBankAccount.getCreateTime().getTime(), 0, simpleDateFormat);
					objDrtMonitorBehaviorRecord.setBehaviorTime(NumberUtils.toLong(strCreateTime));
					//用户行为类型：1.注册，2.绑定用户号3.绑定银行卡4.开通预购电费
					objDrtMonitorBehaviorRecord.setBehaviorType(3);
					objDrtMonitorBehaviorRecord.setId(GetUUID.getUuuid());
					lstDrtMonitorBehaviorRecord.add(objDrtMonitorBehaviorRecord);
				}
				//批量插入用户行为对象
				drtMonitorBehaviorRecordService.insertMonitorBehaviorRecords(lstDrtMonitorBehaviorRecord);
			}
			
		}catch(Exception e){
			log.error("job-插入绑定银行卡行为对象失败:", e.getMessage());
		}
	}

	/**
	 * 插入绑定用电户行为记录
	 *@param simpleDateFormat 时间格式
	 */
	private void insertBindingUserBehavior(SimpleDateFormat simpleDateFormat) {
		try{
			//用户行为集合
			List<DrtMonitorBehaviorRecord> lstDrtMonitorBehaviorRecord = new ArrayList<DrtMonitorBehaviorRecord>();
			//查询昨天按用户ID分组数据
			List<DrtElecUser> lstDrtElecUser = drtElecUserService.queryDrtElecUser();
			if(lstDrtElecUser != null && lstDrtElecUser.size() > 0){
				for(DrtElecUser objDrtElecUser : lstDrtElecUser){
					DrtMonitorBehaviorRecord objDrtMonitorBehaviorRecord = new DrtMonitorBehaviorRecord();
					objDrtMonitorBehaviorRecord.setAccountId(objDrtElecUser.getAccountId());
					String strCreateTime = DateTimeUtils.getIntervalTime(objDrtElecUser.getCreateTime().getTime(), 0, simpleDateFormat);
					objDrtMonitorBehaviorRecord.setBehaviorTime(NumberUtils.toLong(strCreateTime));
					//用户行为类型：1.注册，2.绑定用户号3.绑定银行卡4.开通预购电费
					objDrtMonitorBehaviorRecord.setBehaviorType(2);
					objDrtMonitorBehaviorRecord.setId(GetUUID.getUuuid());
					lstDrtMonitorBehaviorRecord.add(objDrtMonitorBehaviorRecord);
				}
				//批量插入用户行为对象
				drtMonitorBehaviorRecordService.insertMonitorBehaviorRecords(lstDrtMonitorBehaviorRecord);
			}
			
		}catch(Exception e){
			log.error("job-插入绑定用电户号行为记录失败:", e.getMessage());
		}
	}
	
	/**
	 * 插入用户注册行为记录
	 *@param simpleDateFormat 时间格式
	 */
	private void insertRegisterBehavior(SimpleDateFormat simpleDateFormat) {
		try{
			//用户行为集合
			List<DrtMonitorBehaviorRecord> lstDrtMonitorBehaviorRecord = new ArrayList<DrtMonitorBehaviorRecord>();
			//查询昨天按用户ID分组数据
			List<DrtAccount> lstDrtAccount = drtAccountService.queryDrtAccount();
			if(lstDrtAccount != null && lstDrtAccount.size() > 0){
				for(DrtAccount objDrtAccount: lstDrtAccount){
					DrtMonitorBehaviorRecord objDrtMonitorBehaviorRecord = new DrtMonitorBehaviorRecord();
					objDrtMonitorBehaviorRecord.setAccountId(objDrtAccount.getAccountId());
					String strCreateTime = DateTimeUtils.getIntervalTime(objDrtAccount.getCreateTime().getTime(), 0, simpleDateFormat);
					objDrtMonitorBehaviorRecord.setBehaviorTime(NumberUtils.toLong(strCreateTime));
					//用户行为类型：1.注册，2.绑定用户号3.绑定银行卡4.开通预购电费
					objDrtMonitorBehaviorRecord.setBehaviorType(1);
					objDrtMonitorBehaviorRecord.setId(GetUUID.getUuuid());
					lstDrtMonitorBehaviorRecord.add(objDrtMonitorBehaviorRecord);
				}
				//批量插入用户行为对象
				drtMonitorBehaviorRecordService.insertMonitorBehaviorRecords(lstDrtMonitorBehaviorRecord);
			}
		}catch (Exception e){
			log.error("job-批量插入用户注册行为对象失败:", e.getMessage());
		}
	}
	
	   /**
     * 
     * 
     * 设置时间段
     * 
     * @param objAccountRecord 用户登录记录对象
     * @param objMonitorVisitDuration 用户访问对象
     */
	public void getTime(DrtAccountRecord objAccountRecord,DrtMonitorVisitDuration objMonitorVisitDuration) {
		if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 0 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 1){
			objMonitorVisitDuration.setH0(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 1 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 2){
			objMonitorVisitDuration.setH1(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 2 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 3){
			objMonitorVisitDuration.setH2(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 3 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 4){
			objMonitorVisitDuration.setH3(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 4 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 5){
			objMonitorVisitDuration.setH4(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 5 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 6){
			objMonitorVisitDuration.setH5(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 6 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 7){
			objMonitorVisitDuration.setH6(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 7 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 8){
			objMonitorVisitDuration.setH7(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 8 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 9){
			objMonitorVisitDuration.setH8(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 9 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 10){
			objMonitorVisitDuration.setH9(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 10 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 11){
			objMonitorVisitDuration.setH10(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 11 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 12){
			objMonitorVisitDuration.setH11(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 12 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 13){
			objMonitorVisitDuration.setH12(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 13 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 14){
			objMonitorVisitDuration.setH13(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 14 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 15){
			objMonitorVisitDuration.setH14(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 15 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 16){
			objMonitorVisitDuration.setH15(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 16 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 17){
			objMonitorVisitDuration.setH16(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 17 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 18){
			objMonitorVisitDuration.setH17(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 18 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 19){
			objMonitorVisitDuration.setH18(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 19 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 20){
			objMonitorVisitDuration.setH19(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 20 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 21){
			objMonitorVisitDuration.setH20(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 21 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 22){
			objMonitorVisitDuration.setH21(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 22 && DateTimeUtils.getHour(objAccountRecord.getCreateTime()) < 23){
			objMonitorVisitDuration.setH22(1);
		}else if(DateTimeUtils.getHour(objAccountRecord.getCreateTime()) >= 23 ){
			objMonitorVisitDuration.setH23(1);
		}
	}
}

