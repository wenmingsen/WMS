package com.csg.statistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.csg.statistics.entity.DrtAccountCwgs;
import com.csg.statistics.mapper.provider.DrtAccountCwgsSqlProvider;

/**
 * Description:账户信息（文件）表
 * Company: Syni
 * @author 李达才
 * @since 2017-12-1
 *
 */
public interface DrtAccountCwgsMapper {
    
	//保存账户信息（文件）表
    @InsertProvider(type = DrtAccountCwgsSqlProvider.class, method = "insertAccountCwgs")
    int insertAccountCwgs(DrtAccountCwgs drtAccountCwgs);
    
    //更新账户信息（文件）表
    @UpdateProvider(type = DrtAccountCwgsSqlProvider.class, method = "updateAccountCwgs")
    int updateAccountCwgs(DrtAccountCwgs drtAccountCwgs);
    
    //删除账户信息（文件）表
    @Delete("DELETE FROM DRT_ACCOUNT_CWGS WHERE ACCOUNT_NUMBER=#{accountNumber}")
    public int deleteByAccountNumber(String accountNumber);
    
    //批量保存账户信息（文件）表
    @InsertProvider(type = DrtAccountCwgsSqlProvider.class, method = "insertAccountCwgsAll")  
    int insertAccountCwgsAll(@Param("list") List<DrtAccountCwgs> list);  

    //删除保存账户信息（文件）表
    @DeleteProvider(type = DrtAccountCwgsSqlProvider.class, method = "deleteAccountCwgs")  
    int deleteAccountCwgs(@Param("list") List<DrtAccountCwgs> list);  
}
