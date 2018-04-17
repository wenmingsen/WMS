package com.csg.statistics.controller;

import com.csg.statistics.entity.DrtFailInquiryArrearsRecord;
import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.job.DrtDealFailInquiryArrearsJob;
import com.csg.statistics.job.DrtElecDealPkwjJob;
import com.csg.statistics.mapper.DrtDealFailInquiryArrearsRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.service.elec.ElecService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * Description: Api微服务调用测试 
 * @author 王东
 * @author 1.0
 * @date 2018年1月18日 上午10:36:54 
 *
 */
@RestController
@RequestMapping("/statistics")
public class ApiController {
	
	@Autowired
	private DrtDealFailInquiryArrearsJob drtDealFailInquiryArrearsJob;
    @Autowired
    private DrtElecDealPkwjJob drtElecDealPkwjJob;
	@Autowired
	private ElecService elecService;
	@Autowired
	private DrtDealFailInquiryArrearsRecordMapper drtDealFailInquiryArrearsRecordMapper;
	/** 测试统计调用电费微服务是否成功   */
	@RequestMapping("testStatisticsElec")
	@HystrixCommand(fallbackMethod="testStatisticsElecCallBack")
	public String testStatisticElec(){
		return elecService.testStatisticsElec();
	}
	public String testStatisticsElecCallBack(){
		return "失败";
	}

    @RequestMapping("testPk")
    public String testPk(){
         drtElecDealPkwjJob.dealPkwj();
        return "sss";
    }
    
    /**
     * 测试添加一条查询欠费失败记录
     * @return
     */
    @RequestMapping("addRecord")
    public String addRecord(){
    	
    	DrtFailInquiryArrearsRecord drtFailInquiryArrearsRecord =new DrtFailInquiryArrearsRecord();
    	drtFailInquiryArrearsRecord.setInquiryArrearsRecordId(UUIDUtil.generateUUID());
    	drtFailInquiryArrearsRecord.setCreateTime(DateTimeUtils.getCurrentTime());
    	drtFailInquiryArrearsRecord.setStatus(SystemConstants.FAIL_RECORD_UNDEAL);
    	drtDealFailInquiryArrearsRecordMapper.insertRecord(drtFailInquiryArrearsRecord);
    	return "ok";
    }
    

    /**
     * 测试处理失败查询欠费记录
     * @return
     */
    @RequestMapping("testDealFailRecord")
    public String testDealFailRecord(){
    	drtDealFailInquiryArrearsJob.dealFailInquiryArrears();
    	return "ok";
    }
    
    
}
