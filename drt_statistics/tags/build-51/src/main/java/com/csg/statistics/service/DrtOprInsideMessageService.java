package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtOprInsideMessage;
import com.csg.statistics.mapper.DrtOprInsideMessageMapper;

/**
 * DrtOprInsideMessageService 站内消息表
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月06日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtOprInsideMessageService{
	
	/**站内消息表drtOprInsideMessageMapper接口*/
	@Autowired
	private DrtOprInsideMessageMapper drtOprInsideMessageMapper;

	/**
	 * 保存
	 * 
	 * @param drtOprInsideMessage 站内消息表
	 */
	public void insert(DrtOprInsideMessage drtOprInsideMessage) throws Exception{
		drtOprInsideMessageMapper.insert(drtOprInsideMessage);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtOprInsideMessage 站内消息表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtOprInsideMessage drtOprInsideMessage) throws Exception{
		drtOprInsideMessageMapper.update(drtOprInsideMessage);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 站内消息表 主键ID
	 * @return 站内消息表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtOprInsideMessage selectByPrimaryKey(String id) throws Exception{
		return drtOprInsideMessageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtOprInsideMessage 站内消息表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtOprInsideMessage> selectList(DrtOprInsideMessage drtOprInsideMessage) throws Exception{
		return drtOprInsideMessageMapper.selectList(drtOprInsideMessage);
	}

	/**
	 * 批量更新消息
	 * 
	 * @param lstInsideMessage 消息表List
	 * @return Integer 影响条数
	 */
	public Integer updateInsideMessages(List<DrtOprInsideMessage> lstInsideMessage){
		return drtOprInsideMessageMapper.updateInsideMessages(lstInsideMessage);
	}
}