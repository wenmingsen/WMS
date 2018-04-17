package com.csg.statistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.csg.statistics.entity.DrtFailInquiryArrearsRecord;
import com.csg.statistics.mapper.provider.DrtDealFailInquiryArrearsRecordSqlProvider;

/**
 * 处理失败查询欠费记录mapper
 * @author xianlong 2018 03 27
 *
 */
public interface DrtDealFailInquiryArrearsRecordMapper {

	/**
	 * 插入一条新记录
	 * @param drtFailInquiryArrearsRecord
	 * @return
	 */
	@Insert("INSERT INTO drt_fail_inquiry_arrears_record(inquiry_arrears_record_id, status , result, account_id, elec_user_id, create_time, change_time, search_count, tid, recharge_num,phone) " +
            "VALUES(#{inquiryArrearsRecordId}, #{status}, #{result}, #{accountId}, #{elecUserId}, #{createTime}, #{changeTime}, #{searchCount}, #{tid}, #{rechargeNum}, #{phone})")
	public Long insertRecord(DrtFailInquiryArrearsRecord drtFailInquiryArrearsRecord);
	
	/**
	 * 根据主键id更新记录
	 * @param drtFailInquiryArrearsRecord
	 * @return
	 */
	@UpdateProvider(type =DrtDealFailInquiryArrearsRecordSqlProvider.class , method ="updateRecord")
	public Integer updateRecord(DrtFailInquiryArrearsRecord drtFailInquiryArrearsRecord);
	
	/**
	 * 根据状态查询记录
	 */
	@Select("SELECT inquiry_arrears_record_id,status,result,account_id,elec_user_id,create_time,change_time,search_count,tid,recharge_num,phone FROM drt_fail_inquiry_arrears_record WHERE status =#{status}" )
	public List<DrtFailInquiryArrearsRecord> selectByStatus(@Param("status")String status);
}
