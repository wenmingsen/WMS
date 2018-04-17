package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtNews;
import com.csg.statistics.entity.DrtNewsBean;


/**
 * DrtNewsMapper 消息表
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月06日 温明森
 */
public interface DrtNewsMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtNews 消息表
	 */
	void insert(DrtNews drtNews);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtNews 消息表
	 */
	void update(DrtNews drtNews);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param tid 消息表 主键ID
	 * @return 消息表 单条记录
	 */
	DrtNews selectByPrimaryKey(String tid);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtNews 消息表
	 * @return 消息表 记录集
	 */
	List<DrtNews> selectList(DrtNews drtNews);
	
	/**
	 * 批量插入消息
	 * 
	 * @param lstDrtNews 消息表List
	 * @return Integer 影响条数
	 */
	Integer insertDrtNews(List<DrtNews> lstDrtNews);
	
	/**
	 * 删除最近一周的站内发送消息记录 
	 * @param drtNewsBean
	 * @return Integer 影响条数
	 */
	Integer deleteWeekNews();
}