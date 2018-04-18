package com.csg.intshop.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @author xuchen
 */

public class EarningsUtils {

	/**
	 * 根据时间获得积分到期日期
	 * @param Date date
	 * @author xuchen
	 * @return Integer expiryDay 积分到期日期：yyyyMMdd
	 * 		         积分有效期最短1年，最长1年半。例如：2018年1月1日-6月30日获得的积分在2019年6月30日统一到期。每半年做一次积分到期清理。
	 */
	public static Integer getExpiryDay(Date date) {
		// 积分到期日期
		Integer expiryDay = null;
		if (date == null) {
			date = new Date();
		}
		// 时间
		int intDate = NumberUtils.toInt(DateTimeUtils.converDateToString(date,
				"yyyyMMdd"));
		// 得到年
		int year = intDate / 10000;
		// 得到月
		int month = intDate % 10000 / 100;
		// 积分有效期最短1年，最长1年半。例如：2018年1月1日-6月30日获得的积分在2019年6月30日统一到期。每半年做一次积分到期清理。
		if (month < 7) {
			month = 630;
		} else {
			month = 1231;
		}
		expiryDay = (year + 1) * 10000 + month;
		return expiryDay;
	}

	/**
	 * 根据到期日期返回该过期积分获取的时间段
	 * @param Integer expiryDay 积分到期日期
	 * @author xuchen
	 * @return Map<String,Integer> earningsCreateDurationMap
	 *         {key,value}:{startDate:yyyyMMdd},{endDate:yyyyMMdd}
	 */
	public static Map<String, Integer> getEarningsCreateDuration(
			Integer expiryDay) {
		Map<String, Integer> earningsCreateDurationMap = new HashMap<String, Integer>();
		// 积分有效期最短1年，最长1年半。例如：2018年1月1日-6月30日获得的积分在2019年6月30日统一到期。每半年做一次积分到期清理。
		int year = expiryDay / 10000;
		int month = (expiryDay - year * 10000) / 100;
		if (month < 7) {
			earningsCreateDurationMap
					.put("startDate", (year - 1) * 10000 + 101);
			earningsCreateDurationMap.put("endDate", (year - 1) * 10000 + 630);
		} else {
			earningsCreateDurationMap
					.put("startDate", (year - 1) * 10000 + 701);
			earningsCreateDurationMap.put("endDate", (year - 1) * 10000 + 1231);
		}
		return earningsCreateDurationMap;
	}

	/**
	 * 获取下次截止日期
	 * @param Integer date 日期：yyyyMMdd
	 * @author xuchen
	 * @return Integer 下次截止日期：yyyyMMdd
	 */
	public static Integer getNextExpiryDay(Integer date) {
		//得到年
		Integer year = date / 10000;
		//得到月
		Integer month = date % 10000 / 100;
		// 积分有效期最短1年，最长1年半。例如：2018年1月1日-6月30日获得的积分在2019年6月30日统一到期。每半年做一次积分到期清理。
		if (month < 7 && month > 0) {
			month = 630;
		} else if(month > 6 && month < 13){
			month = 1231;
		}else{
			return 0;
		}
		return year * 10000 + month;
	}

}
