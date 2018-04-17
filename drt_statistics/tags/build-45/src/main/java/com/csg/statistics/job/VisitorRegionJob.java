package com.csg.statistics.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.statistics.entity.DrtMonitorLoginRecordBean;
import com.csg.statistics.entity.DrtMonitorVisitorRecord;
import com.csg.statistics.service.DrtMonitorLoginRecordService;
import com.csg.statistics.service.DrtMonitorVisitorRecordService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;
import com.google.gson.Gson;

/**
 * 访客地域分布 计算任务
 *
 * @author 曾令鹏
 * @since jdk1.8
 * @version 2017年12月22日 曾令鹏
 */
@Component
public class VisitorRegionJob {
	
	/**日志*/
	private Logger log = LoggerFactory.getLogger(VisitorRegionJob.class);

	private final static String[] PROVINCE_NAMES = { "西藏", "青海", "宁夏", "海南", "甘肃",
			"贵州", "新疆", "云南", "重庆", "吉林", "山西", "天津", "江西", "广西", "陕西", "黑龙江",
			"内蒙古", "安徽", "北京", "福建", "上海", "湖北", "湖南", "四川", "辽宁", "河北", "河南",
			"浙江", "山东", "江苏", "广东" };
	
	private final static String PROVINCE_NAME_SELECTED = "广东";

	/** 用户登录记录表drtMonitorLoginRecordService */
	@Autowired
	private DrtMonitorLoginRecordService drtMonitorLoginRecordService;
	
	/** 访客记录drtMonitorVisitorRecordService */
	@Autowired
	private DrtMonitorVisitorRecordService drtMonitorVisitorRecordService;

	/**
	 *访客地域计算
	 *
	 */
	public void start() {
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String presentDateStart = DateTimeUtils.getIntervalTime(
					System.currentTimeMillis(), -7, simpleDateFormat);
			String presentDateEnd = DateTimeUtils.getIntervalTime(
					System.currentTimeMillis(), -1, simpleDateFormat);
			
			// 查询过去一周登录记录
			DrtMonitorLoginRecordBean queryDrtMonitorLoginRecordBean = new DrtMonitorLoginRecordBean();
			queryDrtMonitorLoginRecordBean.setPresentDateStart(NumberUtils
					.toInt(presentDateStart));
			queryDrtMonitorLoginRecordBean.setPresentDateEnd(NumberUtils
					.toInt(presentDateEnd));
			
			// 访客来源省份统计
			List<DrtMonitorLoginRecordBean> provinceListGroup = drtMonitorLoginRecordService
					.selectGroupCountByProvinceName(queryDrtMonitorLoginRecordBean);
			List<Map<String, Object>> provinceList = getInitProvinceList(provinceListGroup);
			
			// 访客来源城市统计
			queryDrtMonitorLoginRecordBean.setProvinceName(PROVINCE_NAME_SELECTED);
			List<DrtMonitorLoginRecordBean> cityListGroup = drtMonitorLoginRecordService
					.selectGroupCountByCityName(queryDrtMonitorLoginRecordBean);
			List<Map<String, Object>> cityList = new ArrayList<Map<String,Object>>();
			if(cityListGroup != null){
				Map<String, Object> cityMap = null;
				for (DrtMonitorLoginRecordBean drtMonitorLoginRecordBean : cityListGroup) {
					cityMap = new HashMap<>();
					cityMap.put("name", drtMonitorLoginRecordBean.getCityName());
					cityMap.put("value", drtMonitorLoginRecordBean.getCount());
					cityList.add(cityMap);
				}
			}
			// 设置值
			Gson gson = new Gson();
			DrtMonitorVisitorRecord drtMonitorVisitorRecord = new DrtMonitorVisitorRecord();
			drtMonitorVisitorRecord.setId(UUIDUtil.generateUUID());
			drtMonitorVisitorRecord.setPresentDate(NumberUtils.toInt(presentDateEnd));
			drtMonitorVisitorRecord.setNetHallCount(0L);
			drtMonitorVisitorRecord.setOtherCount(0L);
			drtMonitorVisitorRecord.setPalmHallCount(0L);
			drtMonitorVisitorRecord.setWeChatCount(0L);
			drtMonitorVisitorRecord.setCityListJson(gson.toJson(cityList));
			drtMonitorVisitorRecord.setProvinceListJson(gson.toJson(provinceList));
			drtMonitorVisitorRecordService.insert(drtMonitorVisitorRecord);
		}catch(Exception e){
			log.error("访客地域计算job - {}", e.getMessage());
		}
		
	}

	/**
	 * 获取省份初始化数据集
	 *
	 * @return 省份数据集
	 */
	private static List<Map<String, Object>> getInitProvinceList(List<DrtMonitorLoginRecordBean> provinceList){
		Set<String> ProvinceSet = new HashSet<String>();
		List<Map<String, Object>> initProvinceList = new ArrayList<Map<String, Object>>();
		Map<String, Object> provinceMap = null;
		for (DrtMonitorLoginRecordBean drtMonitorLoginRecordBean : provinceList) {
			ProvinceSet.add(drtMonitorLoginRecordBean.getProvinceName());
			provinceMap = new HashMap<String, Object>();
			provinceMap.put("name", drtMonitorLoginRecordBean.getProvinceName());
			provinceMap.put("value", drtMonitorLoginRecordBean.getCount());
			if(PROVINCE_NAME_SELECTED.equals(drtMonitorLoginRecordBean.getProvinceName())){
				provinceMap.put("selected", true);
			}
			initProvinceList.add(provinceMap);
		}
		for(String province : PROVINCE_NAMES){
			if(!ProvinceSet.contains(province)){
				provinceMap = new HashMap<String, Object>();
				provinceMap.put("name", province);
				provinceMap.put("value", 0);
				if(PROVINCE_NAME_SELECTED.equals(province)){
					provinceMap.put("selected", true);
				}
			}
			initProvinceList.add(provinceMap);
		}
		return initProvinceList;
	}
	
}
