package com.csg.statistics.job;

import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.statistics.entity.DrtFailInquiryArrearsRecord;
import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.service.DrtDealFailInquiryArrearsRecordService;
import com.csg.statistics.service.DrtElecDealGetAllQFService;
import com.csg.statistics.service.DrtFinDealHZDKService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.StringUtils;


/**
 * 
 * @author xianlong 2018 03 26
 * 定时处理查询欠费失败的记录
 *
 */
@Component
public class DrtDealFailInquiryArrearsJob {

	private Logger log = LoggerFactory.getLogger(DrtDealFailInquiryArrearsJob.class);
	
	/**失败查询欠费记录相关service**/
	@Autowired
	private DrtDealFailInquiryArrearsRecordService drtDealFailInquiryArrearsRecordService;
	
	/**调电费查询总欠费service**/
	@Autowired
	private DrtElecDealGetAllQFService drtElecDealGetAllQFService;
	
	/**调资产汇总抵扣service**/
	@Autowired
	private DrtFinDealHZDKService drtFinDealHZDKService;
	
	public void dealFailInquiryArrears(){
		log.info("处理失败查询欠费记录.........");
		try {
			//1.查找数据库未处理的数据
			List<DrtFailInquiryArrearsRecord> recordLists =drtDealFailInquiryArrearsRecordService.findAllUndeal();
			log.info("失败查询欠费记录recordLists:"+recordLists);
			if(recordLists!=null&&recordLists.size()>0){//找到有未处理的记录
				//2.处理失败查询欠费的记录
				for(DrtFailInquiryArrearsRecord record: recordLists){
					log.info("失败查询欠费记录:"+record.toString());
					dealFailRecord(record);
				}
			}
			
			
		} catch (Exception e) {
			log.error("定时处理查询欠费失败的记录异常：",e);
		}
		
	}
	
	/**
	 * 处理单个失败查询欠费记录的操作
	 * @param record
	 * @throws Exception
	 */
	private void dealFailRecord(DrtFailInquiryArrearsRecord record) throws Exception{
		//2.1调电费查询总欠费并生成欠费账单接口
		String accountId =record.getAccountId();
		String elecUserId =record.getElecUserId();
		Integer searchCount =record.getSearchCount();
		log.info("调用电费查询总欠费接口参数--accountId:"+accountId+" elecUserId:"+elecUserId);
		if(accountId==null||StringUtils.isEmpty(accountId)||
				elecUserId==null||StringUtils.isEmpty(elecUserId)){
			return;
		}
		String result =drtElecDealGetAllQFService.dealGetAllQF(accountId, elecUserId);
		log.info("调用电费查询总欠费接口返回结果result:"+result);
		if(result!=null&&!StringUtils.isEmpty(result)){
			JSONObject jsonObject =JSONObject.fromObject(result);
			if(jsonObject.containsKey("state")&&jsonObject.containsKey("data")){
				String state =jsonObject.getString("state");
				String data =jsonObject.getString("data");
				if(SystemConstants.RESULT_CODE_SUCCESS.equals(state)){
					JSONObject jsonData =JSONObject.fromObject(data);
					if(jsonData.containsKey("qfMoney")&&jsonData.containsKey("tId")){
						//查询总欠费成功
						String qfMoney =jsonData.getString("qfMoney");
						String tId =jsonData.getString("tId");
						log.info("查询总欠费成功--qfMoney:"+qfMoney+" tid:"+tId);
						
						//2.2查询成功，有欠费的调资产的缴费接口
						String searchResult ="";
						if("".equals(tId)){
							//查无欠费
							searchResult ="查询欠费成功--查无欠费";
						}else{//查到有欠费
							String rechargeNum =record.getRechargeNum();
							String phone =record.getPhone();
							if(rechargeNum==null||StringUtils.isEmpty(rechargeNum)||
									phone==null||StringUtils.isEmpty(phone)){
								return;
							}
							//调资产汇总抵扣
							drtFinDealHZDKService.dealFinHZDK(accountId, 
									elecUserId, rechargeNum, tId, phone, qfMoney);
							searchResult ="查询欠费成功--已调资产汇总抵扣";
							
						}
						
						//2.3设置数据状态：状态为已处理，设置修改时间，设置处理结果
						record.setStatus(SystemConstants.FAIL_RECORD_DEAL);
						record.setChangeTime(DateTimeUtils.getCurrentTime());
						record.setResult(searchResult);
						record.setTid(tId);
					}
				}
			}
		}
		//2.2查询失败,查询次数加1,修改字段，判断次数是否为3，为3则改状态为已处理，
		if(searchCount==null||searchCount==0){
			record.setSearchCount(1);//第一次查询
		}else{
			Integer count =record.getSearchCount();
			count++;
			record.setSearchCount(count);
		}
		if(SystemConstants.FAIL_RECORD_UNDEAL.equals(record.getStatus())){
			//状态为未处理的表示是查询失败，修改查询结果
			record.setResult("查询失败");
		}
		if(SystemConstants.FAIL_RECORD_SEARCH_COUNT==record.getSearchCount()){
			//查询次数为3次，修改状态为已处理
			record.setStatus(SystemConstants.FAIL_RECORD_DEAL);
			record.setChangeTime(DateTimeUtils.getCurrentTime());
		}
		
		//3.保存数据修改到数据库
		drtDealFailInquiryArrearsRecordService.updateRecord(record);
		
	}
}
