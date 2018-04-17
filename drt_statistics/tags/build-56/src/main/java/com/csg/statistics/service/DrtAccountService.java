
package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtAccount;
import com.csg.statistics.mapper.DrtAccountMapper;

/**
 * DrtAccountService 登录账户信息表（主要包括电融通账户、密码）
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2017年12月11日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtAccountService {
    
    /** 登录账户信息表（主要包括电融通账户、密码）drtAccountMapper接口 */
    @Autowired
    private DrtAccountMapper drtAccountMapper;
    
    /**
     * 保存
     * 
     * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
     */
    public void insert(DrtAccount drtAccount) throws Exception {
        drtAccountMapper.insert(drtAccount);
    }
    
    /**
     * 更新
     * <p>
     * 通过主键更新记录
     * </p>
     * 
     * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
     * @throws Exception 出错抛出异常
     */
    public void update(DrtAccount drtAccount) throws Exception {
        drtAccountMapper.update(drtAccount);
    }
    
    /**
     * 通过主键获取单条记录
     * 
     * @param accountId 登录账户信息表（主要包括电融通账户、密码） 主键ID
     * @return 登录账户信息表（主要包括电融通账户、密码） 单条记录
     * @throws Exception 出错抛出异常
     */
    public DrtAccount selectByPrimaryKey(String accountId) throws Exception {
        return drtAccountMapper.selectByPrimaryKey(accountId);
    }
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
     * @throws Exception 出错抛出异常
     */
    public List<DrtAccount> selectList(DrtAccount drtAccount) {
        return drtAccountMapper.selectList(drtAccount);
    }
    
    /**
     * 查询昨天按用户ID分组数据
     * 
     * @throws Exception 出错抛出异常
     */
    public List<DrtAccount> queryDrtAccount() throws Exception {
        return drtAccountMapper.queryDrtAccount();
    }
}
