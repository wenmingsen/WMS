/**
 * 
 */
package com.csg.statistics.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtStateBusinessUser;
import com.csg.statistics.entity.StatistiseBusinessUserBean;
import com.csg.statistics.mapper.DrtOprnBusinessStateMapper;
import com.csg.statistics.util.DateTimeUtils;


/**
 * 业务统计Service
 * 
 * @author 李城
 *
 */
@EnableTransactionManagement
@Transactional
@Service
public class DrtOprnBusinessStateService {

	/**业务统计dao接口*/
	@Autowired
	private DrtOprnBusinessStateMapper drtOprnBusinessStateMapper;
	
	/**
	 * 查询新增用户日统计
	 * 
	 * @return 新增用户日统计
	 * */
	public int queryNewUserDayCount(StatistiseBusinessUserBean bean){
		return  drtOprnBusinessStateMapper.queryNewUserDayCount(bean);
	}
	
	/**
	 * 查询实名认证日统计
	 * 
	 * @return 实名认证日统计
	 * */
	public int queryIdcardDayCount(StatistiseBusinessUserBean bean){
		return  drtOprnBusinessStateMapper.queryIdcardDayCount(bean);
	}
	
	/**
	 * 查询活跃度日统计
	 * 
	 * @return 活跃度日统计
	 * */
	public int queryRecordDayCount(StatistiseBusinessUserBean bean){
		return  drtOprnBusinessStateMapper.queryRecordDayCount(bean);
	}
	
	/**
	 * 查询绑定银行卡日统计
	 * 
	 * @return 绑定银行卡日统计
	 * */
	public int  queryBangkDayCount(StatistiseBusinessUserBean bean){
		return  drtOprnBusinessStateMapper.queryBangkDayCount(bean);
	}
	
	/**查询业务用户周，月统计*/
	public void  insBusinessUser(DrtStateBusinessUser drtStateBusinessUser){
		  drtOprnBusinessStateMapper.insBusinessUser(drtStateBusinessUser);
	}
	
	/**
	 * 获取业务用户日，周，月统计
	 * 
	 * @return 业务用户日，周，月统计
	 * */
	public void getDrtStateBusinessUser(){
		StatistiseBusinessUserBean bean=new StatistiseBusinessUserBean();
		DrtStateBusinessUser resultDTO=new DrtStateBusinessUser();
		String thisStartDay=DateTimeUtils.converDateToString(DateTimeUtils.getTodayStart(),"yyyy-MM-dd HH:mm:ss");
		String lastStartDay=DateTimeUtils.converDateToString(DateTimeUtils.getYesterdayStart(),"yyyy-MM-dd HH:mm:ss");
		Timestamp thisStartDayTime= DateTimeUtils.converStrToTimestamp(thisStartDay);
		Timestamp lastStartDayTime= DateTimeUtils.converStrToTimestamp(lastStartDay);
		bean.setStartTime(lastStartDayTime);
		bean.setEndTime(thisStartDayTime);
		int newUserdayCount=queryNewUserDayCount(bean);
		int idcardDayCount=queryIdcardDayCount(bean); 
		int recordDayCount=queryRecordDayCount(bean);
		int bangkDayCount=queryBangkDayCount(bean);
		resultDTO.setNewUserCount(newUserdayCount);
		resultDTO.setBangkCount(bangkDayCount);
		resultDTO.setIdcardCount(idcardDayCount);
		resultDTO.setRecordCount(recordDayCount);
		UUID uuid = UUID.randomUUID();
		String id = String.valueOf(uuid);
		id = id.replace("-","");
		resultDTO.setId(id);
  	    Timestamp newDate = new Timestamp(System.currentTimeMillis());
		resultDTO.setStatisticsTime(lastStartDayTime);
		resultDTO.setCreateTime(newDate);
		insBusinessUser(resultDTO);
		
	}
}
