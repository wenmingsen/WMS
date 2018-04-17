package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtMonitorVisitorRecord;
import com.csg.statistics.mapper.DrtMonitorVisitorRecordMapper;
import com.github.pagehelper.PageHelper;

/**
 * DrtMonitorVisitorRecordService 访客记录
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月22日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMonitorVisitorRecordService{
	
	/**访客记录drtMonitorVisitorRecordMapper接口*/
	@Autowired
	private DrtMonitorVisitorRecordMapper drtMonitorVisitorRecordMapper;

	/**
	 * 保存
	 * 
	 * @param drtMonitorVisitorRecord 访客记录
	 */
	public void insert(DrtMonitorVisitorRecord drtMonitorVisitorRecord) throws Exception{
		// 判断当天的记录是否已经存在：存在-update 不存在-更新
		DrtMonitorVisitorRecord queryDrtMonitorVisitorRecord = new DrtMonitorVisitorRecord();
		queryDrtMonitorVisitorRecord.setPresentDate(drtMonitorVisitorRecord.getPresentDate());
		PageHelper.startPage(1, 1, false);
		List<DrtMonitorVisitorRecord> drtMonitorVisitorRecordList = this.selectList(queryDrtMonitorVisitorRecord);
		if(drtMonitorVisitorRecordList != null && drtMonitorVisitorRecordList.size() > 0){
			DrtMonitorVisitorRecord oldDrtMonitorVisitorRecord = drtMonitorVisitorRecordList.get(0);
			if(drtMonitorVisitorRecord.getCityListJson() != null){
				oldDrtMonitorVisitorRecord.setCityListJson(drtMonitorVisitorRecord.getCityListJson());
			}
			if(drtMonitorVisitorRecord.getProvinceListJson() != null){
				oldDrtMonitorVisitorRecord.setProvinceListJson(drtMonitorVisitorRecord.getProvinceListJson());
			}
			if(drtMonitorVisitorRecord.getNetHallCount() != null){
				oldDrtMonitorVisitorRecord.setNetHallCount(oldDrtMonitorVisitorRecord.getNetHallCount().longValue()+drtMonitorVisitorRecord.getNetHallCount().longValue());
			}
			if(drtMonitorVisitorRecord.getPalmHallCount() != null){
				oldDrtMonitorVisitorRecord.setPalmHallCount(oldDrtMonitorVisitorRecord.getPalmHallCount().longValue()+drtMonitorVisitorRecord.getPalmHallCount().longValue());
			}
			if(drtMonitorVisitorRecord.getWeChatCount() != null){
				oldDrtMonitorVisitorRecord.setWeChatCount(oldDrtMonitorVisitorRecord.getWeChatCount().longValue()+drtMonitorVisitorRecord.getWeChatCount().longValue());
			}
			if(drtMonitorVisitorRecord.getOtherCount() != null){
				oldDrtMonitorVisitorRecord.setOtherCount(oldDrtMonitorVisitorRecord.getOtherCount().longValue()+drtMonitorVisitorRecord.getOtherCount().longValue());
			}
			this.update(oldDrtMonitorVisitorRecord);
		}else{
			drtMonitorVisitorRecordMapper.insert(drtMonitorVisitorRecord);
		}
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorVisitorRecord 访客记录
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMonitorVisitorRecord drtMonitorVisitorRecord) throws Exception{
		drtMonitorVisitorRecordMapper.update(drtMonitorVisitorRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 访客记录 主键ID
	 * @return 访客记录 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMonitorVisitorRecord selectByPrimaryKey(String id) throws Exception{
		return drtMonitorVisitorRecordMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorVisitorRecord 访客记录
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtMonitorVisitorRecord> selectList(DrtMonitorVisitorRecord drtMonitorVisitorRecord) throws Exception{
		return drtMonitorVisitorRecordMapper.selectList(drtMonitorVisitorRecord);
	}

}