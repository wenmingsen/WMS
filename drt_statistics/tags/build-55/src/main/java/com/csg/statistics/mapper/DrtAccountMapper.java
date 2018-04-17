
package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtAccount;

/**
 * DrtAccountMapper 登录账户信息表（主要包括电融通账户、密码）
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2017年12月11日 曾令鹏
 */
public interface DrtAccountMapper {
    
    /**
     * 保存
     * 
     * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
     */
    void insert(DrtAccount drtAccount);
    
    /**
     * 更新
     * <p>
     * 通过主键更新记录
     * </p>
     * 
     * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
     */
    void update(DrtAccount drtAccount);
    
    /**
     * 通过主键获取单条记录
     * 
     * @param accountId 登录账户信息表（主要包括电融通账户、密码） 主键ID
     * @return 登录账户信息表（主要包括电融通账户、密码） 单条记录
     */
    DrtAccount selectByPrimaryKey(String accountId);
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
     * @return 登录账户信息表（主要包括电融通账户、密码） 记录集
     */
    List<DrtAccount> selectList(DrtAccount drtAccount);
    
    /**
     * 查询昨天按用户ID分组数据
     */
    public List<DrtAccount> queryDrtAccount();
}
