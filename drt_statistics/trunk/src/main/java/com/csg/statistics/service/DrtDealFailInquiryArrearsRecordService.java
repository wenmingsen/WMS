package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.entity.DrtFailInquiryArrearsRecord;
import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.mapper.DrtDealFailInquiryArrearsRecordMapper;


/**
 * 处理失败查询欠费记录service
 * @author xianlong 2018 03 28
 *
 */
@Service
public class DrtDealFailInquiryArrearsRecordService {

	@Autowired
	private DrtDealFailInquiryArrearsRecordMapper drtDealFailInquiryArrearsRecordMapper;
	
	/**
	 * 找出状态为未处理的数据
	 * 
	 */
	public List<DrtFailInquiryArrearsRecord> findAllUndeal(){
		return drtDealFailInquiryArrearsRecordMapper.selectByStatus(SystemConstants.FAIL_RECORD_UNDEAL);
		
	}
	
	/**
	 * 修改记录信息根据主键
	 * @param record
	 * @return
	 */
	public Integer updateRecord(DrtFailInquiryArrearsRecord record){
		return drtDealFailInquiryArrearsRecordMapper.updateRecord(record);
	}
}
