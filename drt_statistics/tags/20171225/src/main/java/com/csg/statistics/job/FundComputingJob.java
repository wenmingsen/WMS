package com.csg.statistics.job;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.entity.DrtFinPoAccountRecord;
import com.csg.statistics.entity.DrtFinPoAccountRecordForQuery;
import com.csg.statistics.entity.DrtMonitorFundDay;
import com.csg.statistics.service.DrtFinPoAccountRecordService;
import com.csg.statistics.service.DrtFinPoAccountService;
import com.csg.statistics.service.DrtMonitorFundDayService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;

/**
 * 资金监控统计任务
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
@Component
public class FundComputingJob {

	/** 日资金监控表 */
	@Autowired
	private DrtMonitorFundDayService drtMonitorFundDayService;
	
	/** 预购信息明细表 */
	@Autowired
	private DrtFinPoAccountRecordService drtFinPoAccountRecordService;
	
	/** 预购账户表 */
	@Autowired
	private DrtFinPoAccountService drtFinPoAccountService;
	
	/**
	 * 资金计算
	 *
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 1 * * ?")
	public void fundComputing() throws Exception{
		Date yesterday = DateTimeUtils.getYesterdayStart();
		DrtFinPoAccountRecordForQuery drtFinPoAccountRecordForQuery = new DrtFinPoAccountRecordForQuery();
		drtFinPoAccountRecordForQuery.setDealTimeEnd(new Timestamp(DateTimeUtils.getStartTimeOfDay(new Date()).getTime()));
		drtFinPoAccountRecordForQuery.setDealTimeStart(new Timestamp(yesterday.getTime()));
		List<DrtFinPoAccountRecord> lstDrtFinPoAccountRecord = drtFinPoAccountRecordService.selectListYesterday(drtFinPoAccountRecordForQuery);
		long iPoMoney = 0;
		long iPoMoneyMax = 0;
		long iPoMoneyMin = 2147483647;
		int  iPoNum = lstDrtFinPoAccountRecord.size();
		long iDeMoney = 0;
		long iDeMoneyMax = 0;
		long iDeMoneyMin = 2147483647;
		int  iDeNum = 0;
		long iElMoney = 0;
		long iElMoneyMax = 0;
		long iElMoneyMin = 2147483647;
		int  iElNum = 0;
		//预购代扣
		for(DrtFinPoAccountRecord drtFinPoAccountRecordTemp:lstDrtFinPoAccountRecord){
			int poDealmoney = drtFinPoAccountRecordTemp.getPoDealMoney();
			if(poDealmoney < 0){
				poDealmoney = poDealmoney * (-1);
			}
			iPoMoney += poDealmoney;
			if(iPoMoneyMin > poDealmoney){
				iPoMoneyMin = poDealmoney;
			}
			if(iPoMoneyMax < poDealmoney){
				iPoMoneyMax = poDealmoney;
			}
			if(drtFinPoAccountRecordTemp.getIsInOut().equals("3")){
				iDeMoney += poDealmoney;
				if(iDeMoneyMin > poDealmoney){
					iDeMoneyMin = poDealmoney;
				}
				if(iDeMoneyMax < poDealmoney){
					iDeMoneyMax = poDealmoney;
				}
				iDeNum++;
			}
		}
		DrtMonitorFundDay drtMonitorFundDay = new DrtMonitorFundDay();
		drtMonitorFundDay.setId(UUIDUtil.generateUUID());
		drtMonitorFundDay.setPresentDate(Integer.valueOf(DateTimeUtils.converDateToString(yesterday, "yyyyMMdd")));
		drtMonitorFundDay.setPoMoney(iPoMoney);
		drtMonitorFundDay.setPoMoneyMax(iPoMoneyMax);
		drtMonitorFundDay.setPoMoneyMin(iPoMoneyMin);
		drtMonitorFundDay.setDeductMoney(iDeMoney);
		drtMonitorFundDay.setDeductMoneyMax(iDeMoneyMax);
		drtMonitorFundDay.setDeductMoneyMin(iDeMoneyMin);
		//余额
		List<DrtFinPoAccount> lstDrtFinPoAccount = drtFinPoAccountService.selectList(null);
		for(DrtFinPoAccount drtFinPoAccountTemp:lstDrtFinPoAccount){
			iElMoney += drtFinPoAccountTemp.getPoTotalMoney();
			if(iElMoneyMin > drtFinPoAccountTemp.getPoTotalMoney()){
				iElMoneyMin = drtFinPoAccountTemp.getPoTotalMoney();
			}
			if(iElMoneyMax < drtFinPoAccountTemp.getPoTotalMoney()){
				iElMoneyMax = drtFinPoAccountTemp.getPoTotalMoney();
			}
		}
		iElNum = lstDrtFinPoAccount.size();
		//赋值
		drtMonitorFundDay.setPoMoney(iPoMoney);
		drtMonitorFundDay.setPoMoneyMax(iPoMoneyMax);
		drtMonitorFundDay.setPoMoneyMin(iPoMoneyMin);
		drtMonitorFundDay.setPoUserCount(iPoNum);
		drtMonitorFundDay.setDeductMoney(iDeMoney);
		drtMonitorFundDay.setDeductMoneyMax(iDeMoneyMax);
		drtMonitorFundDay.setDeductMoneyMin(iDeMoneyMin);
		drtMonitorFundDay.setDeductUserCount(iDeNum);
		drtMonitorFundDay.setElectricMoney(iElMoney);
		drtMonitorFundDay.setElectricMoneyMax(iElMoneyMax);
		drtMonitorFundDay.setElectricMoneyMin(iElMoneyMin);
		drtMonitorFundDay.setElectricUserCount(iElNum);
		drtMonitorFundDay.setIsDelete(1);
		drtMonitorFundDayService.insert(drtMonitorFundDay);
	}
}
