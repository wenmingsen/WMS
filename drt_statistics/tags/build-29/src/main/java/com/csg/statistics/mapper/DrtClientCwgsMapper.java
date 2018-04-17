package com.csg.statistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.csg.statistics.entity.DrtClientCwgs;
import com.csg.statistics.mapper.provider.DrtClientCwgsSqlProvider;

/**
 * Description:客户信息（文件）表
 * Company: Syni
 * @author 李达才
 * @since 2017-12-1
 *
 */
public interface DrtClientCwgsMapper {
    
	//保存客户信息（文件）表
    @InsertProvider(type = DrtClientCwgsSqlProvider.class, method = "insertClientCwgs")
    int insertClientCwgs(DrtClientCwgs drtClientCwgs);
    
    //更新客户信息（文件）表
    @UpdateProvider(type = DrtClientCwgsSqlProvider.class, method = "updateClientCwgs")
    int updateClientCwgs(DrtClientCwgs drtClientCwgs);
    
    //删除客户信息（文件）表
    @Delete("DELETE FROM DRT_CLIENT_CWGS WHERE CLIENT_NUMBER=#{clientNumber}")
    public int deleteByClientNumber(String clientNumber);
    
    //批量保存客户信息（文件）表
    @InsertProvider(type = DrtClientCwgsSqlProvider.class, method = "insertClientCwgsAll")  
    int insertClientCwgsAll(@Param("list") List<DrtClientCwgs> list);  

    //删除保存客户信息（文件）表
    @DeleteProvider(type = DrtClientCwgsSqlProvider.class, method = "deleteClientCwgs")  
    int deleteClientCwgs(@Param("list") List<DrtClientCwgs> list);  
}
