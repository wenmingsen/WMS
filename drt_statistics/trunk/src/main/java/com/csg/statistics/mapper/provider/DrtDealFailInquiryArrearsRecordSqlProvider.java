package com.csg.statistics.mapper.provider;


import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csg.statistics.entity.DrtFailInquiryArrearsRecord;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.StringUtils;

/**
 * 处理失败查询欠费记录sql
 * @author xianlong 2018 03 27
 *
 */
public class DrtDealFailInquiryArrearsRecordSqlProvider {

	private Logger logger = LoggerFactory.getLogger(DrtDealFailInquiryArrearsRecordSqlProvider.class);
	
	public String updateRecord(final DrtFailInquiryArrearsRecord drtFailInquiryArrearsRecord){
		SQL sql = new SQL() {
            {
                UPDATE("drt_fail_inquiry_arrears_record");
                
                if(drtFailInquiryArrearsRecord.getStatus() != null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getStatus())) {
                    SET("status = #{status}");
                }
                if (drtFailInquiryArrearsRecord.getResult()!=null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getResult())) {
                    SET("result = #{result}");
                }
                if (drtFailInquiryArrearsRecord.getAccountId()!=null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getAccountId())) {
                    SET("account_id = #{accountId}");
                }
                if (drtFailInquiryArrearsRecord.getElecUserId()!=null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getElecUserId())) {
                    SET("elec_user_id = #{elecUserId}");
                }
                if (drtFailInquiryArrearsRecord.getCreateTime()!=null&&!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getCreateTime())) {
                    SET("create_time = #{createTime}");
                }
                if (drtFailInquiryArrearsRecord.getChangeTime()!=null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getChangeTime())) {
                    SET("change_time = #{changeTime}");
                }
                if (drtFailInquiryArrearsRecord.getSearchCount()!=null) {
                    SET("search_count = #{searchCount}");
                }
                if(drtFailInquiryArrearsRecord.getTid() != null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getTid())) {
                    SET("tid = #{tid}");
                }
                if(drtFailInquiryArrearsRecord.getRechargeNum() != null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getRechargeNum())) {
                    SET("recharge_num = #{rechargeNum}");
                }
                if(drtFailInquiryArrearsRecord.getPhone() != null &&
                		!StringUtils.isEmpty(drtFailInquiryArrearsRecord.getPhone())) {
                    SET("phone = #{phone}");
                }
                
                WHERE("inquiry_arrears_record_id = #{inquiryArrearsRecordId}");
            }
        };
        logger.info("updateFailInquiryArrearsRecord:"+sql.toString());
        return sql.toString();
	}
	
	public static void main(String[] args) {
		DrtFailInquiryArrearsRecord drtFailInquiryArrearsRecord =new DrtFailInquiryArrearsRecord();
		drtFailInquiryArrearsRecord.setInquiryArrearsRecordId("");
		drtFailInquiryArrearsRecord.setStatus("");
		drtFailInquiryArrearsRecord.setResult("");
		drtFailInquiryArrearsRecord.setAccountId("");
		drtFailInquiryArrearsRecord.setElecUserId("");
		drtFailInquiryArrearsRecord.setChangeTime(DateTimeUtils.getCurrentTime());
		drtFailInquiryArrearsRecord.setSearchCount(null);
		drtFailInquiryArrearsRecord.setTid("");
		drtFailInquiryArrearsRecord.setRechargeNum("");
		drtFailInquiryArrearsRecord.setPhone("");
		DrtDealFailInquiryArrearsRecordSqlProvider drtDealFailInquiryArrearsRecordSqlProvider =new DrtDealFailInquiryArrearsRecordSqlProvider();
		System.out.println(drtDealFailInquiryArrearsRecordSqlProvider.updateRecord(drtFailInquiryArrearsRecord));
	}
}
