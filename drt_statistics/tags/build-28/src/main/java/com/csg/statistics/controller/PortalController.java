package com.csg.statistics.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.entity.DrtMonitorVisitorRecord;
import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.service.DrtMonitorVisitorRecordService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;

/**
 * 门户操作Controller
 * 
 * @author 曾令鹏
 * @since 1.8
 * @version 2017年12月26日 曾令鹏
 */
@RestController
@RequestMapping("/statistics/portal/*")
public class PortalController {
	
	/** 日志 */
	private Logger log = LoggerFactory.getLogger(PortalController.class);

	@Autowired
	private DrtMonitorVisitorRecordService drtMonitorVisitorRecordService;

	/**
	 * 插入访客记录
	 *
	 * @param sourceType 访客来源：0-其它 1-掌厅 2-网厅 3-微信
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("insertVisitorRecord")
	public Map<String, Object> insertVisitorRecord(@RequestParam(value="t", defaultValue="0") Integer sourceType) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String presentDate = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), 0, simpleDateFormat);
			DrtMonitorVisitorRecord drtMonitorVisitorRecord = new DrtMonitorVisitorRecord();
			drtMonitorVisitorRecord.setId(UUIDUtil.generateUUID());
			drtMonitorVisitorRecord.setPresentDate(NumberUtils.toInt(presentDate));
			drtMonitorVisitorRecord.setPalmHallCount(0L);
			drtMonitorVisitorRecord.setNetHallCount(0L);
			drtMonitorVisitorRecord.setWeChatCount(0L);
			drtMonitorVisitorRecord.setOtherCount(0L);
			if(sourceType.intValue() == 1){
				drtMonitorVisitorRecord.setPalmHallCount(1L);
			}else if(sourceType.intValue() == 2){
				drtMonitorVisitorRecord.setNetHallCount(1L);
			}else if(sourceType.intValue() == 3){
				drtMonitorVisitorRecord.setWeChatCount(1L);
			}else{
				drtMonitorVisitorRecord.setOtherCount(1L);
			}
			drtMonitorVisitorRecordService.insert(drtMonitorVisitorRecord);
		}catch(Exception e){
			log.error("插入访客记录：" + e);
			resultMap.put("state", SystemConstants.RESULT_CODE_FAILED);
			resultMap.put("message", "执行错误！");
		}
		resultMap.put("state", SystemConstants.RESULT_CODE_SUCCESS);
		resultMap.put("message", "执行成功！");
		return resultMap;
	}

}
