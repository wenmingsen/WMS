package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtAccountRecord;
import com.csg.statistics.mapper.DrtAccountRecordMapper;


/**
 * DrtAccountRecordService 登录账户操作记录表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月22日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtAccountRecordService{
	
	/**登录账户操作记录表drtAccountRecordMapper接口*/
	@Autowired
	private DrtAccountRecordMapper drtAccountRecordMapper;

	/**
	 * 保存
	 * 
	 * @param drtAccountRecord 登录账户操作记录表
	 */
	public void insert(DrtAccountRecord drtAccountRecord) throws Exception{
		drtAccountRecordMapper.insert(drtAccountRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtAccountRecord 登录账户操作记录表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtAccountRecord drtAccountRecord) throws Exception{
		drtAccountRecordMapper.update(drtAccountRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param accountRecordId 登录账户操作记录表 主键ID
	 * @return 登录账户操作记录表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtAccountRecord selectByPrimaryKey(String accountRecordId) throws Exception{
		return drtAccountRecordMapper.selectByPrimaryKey(accountRecordId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtAccountRecord 登录账户操作记录表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtAccountRecord> selectList(DrtAccountRecord drtAccountRecord) throws Exception{
		return drtAccountRecordMapper.selectList(drtAccountRecord);
	}
	
	/**
	 * 通过查询实体自定义非空字段获取记录集
	 * 
	 * @param drtMonitorLoginRecordBean 用户登录记录表-查询实体
	 * @return 用户登录记录表 记录集
	 */
	public List<DrtAccountRecord> selectListByDrtAccountRecord(DrtAccountRecord drtMonitorLoginRecord){
		return drtAccountRecordMapper.selectListByDrtAccountRecord(drtMonitorLoginRecord);
	}

}