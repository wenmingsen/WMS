package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtOprInsideMessage;


/**
 * DrtOprInsideMessageMapper 站内消息表
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月06日 温明森
 */
public interface DrtOprInsideMessageMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtOprInsideMessage 站内消息表
	 */
	void insert(DrtOprInsideMessage drtOprInsideMessage);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtOprInsideMessage 站内消息表
	 */
	void update(DrtOprInsideMessage drtOprInsideMessage);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 站内消息表 主键ID
	 * @return 站内消息表 单条记录
	 */
	DrtOprInsideMessage selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtOprInsideMessage 站内消息表
	 * @return 站内消息表 记录集
	 */
	List<DrtOprInsideMessage> selectList(DrtOprInsideMessage drtOprInsideMessage);

}