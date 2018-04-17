package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtMonitorLoginRecord;
import com.csg.statistics.entity.DrtMonitorLoginRecordBean;
import com.csg.statistics.mapper.DrtMonitorLoginRecordMapper;

/**
 * DrtMonitorLoginRecordService 用户登录记录表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMonitorLoginRecordService{
	
	/**用户登录记录表drtMonitorLoginRecordMapper接口*/
	@Autowired
	private DrtMonitorLoginRecordMapper drtMonitorLoginRecordMapper;

	/**
	 * 保存
	 * 
	 * @param drtMonitorLoginRecord 用户登录记录表
	 */
	public void insert(DrtMonitorLoginRecord drtMonitorLoginRecord) throws Exception{
		drtMonitorLoginRecordMapper.insert(drtMonitorLoginRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorLoginRecord 用户登录记录表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMonitorLoginRecord drtMonitorLoginRecord) throws Exception{
		drtMonitorLoginRecordMapper.update(drtMonitorLoginRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户登录记录表 主键ID
	 * @return 用户登录记录表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMonitorLoginRecord selectByPrimaryKey(String id) throws Exception{
		return drtMonitorLoginRecordMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorLoginRecord 用户登录记录表
	 * @return 用户登录记录表 记录集
	 */
	public List<DrtMonitorLoginRecord> selectList(DrtMonitorLoginRecord drtMonitorLoginRecord){
		return drtMonitorLoginRecordMapper.selectList(drtMonitorLoginRecord);
	}
	
	/**
	 * 通过查询实体自定义非空字段获取记录集
	 * 
	 * @param DrtMonitorLoginRecordBean 用户登录记录表-查询实体
	 * @return 用户登录记录表 记录集
	 */
	public List<DrtMonitorLoginRecord> selectListByDrtMonitorLoginRecordBean(DrtMonitorLoginRecordBean drtMonitorLoginRecordBean){
		return drtMonitorLoginRecordMapper.selectListByDrtMonitorLoginRecordBean(drtMonitorLoginRecordBean);
	}
	
	/**
	 * 通过省份分组查询数据
	 *	
	 *	<pre>
	 *		结果集数据：provinceName、count
	 *	</pre>
	 *
	 * @param drtMonitorLoginRecordBean 查询实体
	 * @return 查询成功返回结果集
	 */
	public List<DrtMonitorLoginRecordBean> selectGroupCountByProvinceName(DrtMonitorLoginRecordBean drtMonitorLoginRecordBean){
		return drtMonitorLoginRecordMapper.selectGroupCountByProvinceName(drtMonitorLoginRecordBean);
	}
	
	/**
	 * 通过城市分组查询数据
	 *
	 * <pre>
	 * 	结果集数据：cityName、count
	 * </pre>
	 * @param drtMonitorLoginRecordBean 查询实体
	 * @return 查询成功返回结果集
	 */
	public List<DrtMonitorLoginRecordBean> selectGroupCountByCityName(DrtMonitorLoginRecordBean drtMonitorLoginRecordBean){
		return drtMonitorLoginRecordMapper.selectGroupCountByCityName(drtMonitorLoginRecordBean);
	}

}