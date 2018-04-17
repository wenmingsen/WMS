package com.csg.statistics.mapper.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.csg.statistics.entity.DrtClientCwgs;
import com.csg.statistics.util.StringUtils;

/**
 * Description:客户信息（文件）表
 * Company: Syni
 * @author 李达才
 * @since 2017-12-1
 *
 */
public class DrtClientCwgsSqlProvider {

    public String insertClientCwgs(final DrtClientCwgs drtClientCwgs) {
        return new SQL() {
            {
                INSERT_INTO("DRT_CLIENT_CWGS");
                if (!StringUtils.isEmpty(drtClientCwgs.getDrtClientId())) {
                    VALUES("DRT_CLIENT_ID", "#{drtClientId}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getOfficeNumber())) {
                    VALUES("OFFICE_NUMBER", "#{officeNumber}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getClientNumber())) {
                    VALUES("CLIENT_NUMBER", "#{clientNumber}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getClientName())) {
                    VALUES("CLIENT_NAME", "#{clientName}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getStandbyOne())) {
                    VALUES("STANDBY_ONE", "#{standbyOne}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getStandbyTwo())) {
                    VALUES("STANDBY_TWO", "#{standbyTwo}");
                }
                if (drtClientCwgs.getUpdateTime() != null) {
                    VALUES("UPDATE_TIME", "#{updateTime}");
                }
            }
        }.toString();
    }

    public String updateClientCwgs(final DrtClientCwgs drtClientCwgs) {
        return new SQL() {
            {
                UPDATE("DRT_CLIENT_CWGS");
                if (!StringUtils.isEmpty(drtClientCwgs.getOfficeNumber())) {
                    VALUES("OFFICE_NUMBER", "#{officeNumber}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getClientNumber())) {
                    VALUES("CLIENT_NUMBER", "#{clientNumber}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getClientName())) {
                    VALUES("CLIENT_NAME", "#{clientName}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getStandbyOne())) {
                    VALUES("STANDBY_ONE", "#{standbyOne}");
                }
                if (!StringUtils.isEmpty(drtClientCwgs.getStandbyTwo())) {
                    VALUES("STANDBY_TWO", "#{standbyTwo}");
                }
                if (drtClientCwgs.getUpdateTime() != null) {
                    VALUES("UPDATE_TIME", "#{uptdteTime}");
                }
                WHERE("DRT_CLIENT_ID = #{drtClientId}");
            }
        }.toString();
    }
    
    public String insertClientCwgsAll(Map map) {  
		List<DrtClientCwgs> dacs = (List<DrtClientCwgs>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO DRT_CLIENT_CWGS ");  
        sb.append("(DRT_CLIENT_ID, OFFICE_NUMBER, CLIENT_NUMBER, CLIENT_NAME, STANDBY_ONE, STANDBY_TWO, UPDATE_TIME) VALUES"); 
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].drtClientId}, #'{'list[{0}].officeNumber}, #'{'list[{0}].clientNumber}, "
        		+ "#'{'list[{0}].clientName}, #'{'list[{0}].standbyOne}, #'{'list[{0}].standbyTwo}, #'{'list[{0}].updateTime})");  
        for (int i = 0; i < dacs.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < dacs.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
    }  
    
    public String deleteClientCwgs(Map map) {  
        List<DrtClientCwgs> dacs = (List<DrtClientCwgs>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("DELETE FROM DRT_CLIENT_CWGS WHERE CLIENT_NUMBER IN ( ");  
        MessageFormat mf = new MessageFormat("#'{'list[{0}].clientNumber}");  
        for (int i = 0; i < dacs.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < dacs.size() - 1) {  
                sb.append(",");  
            }  
        }  
        sb.append(")");
        return sb.toString();  
    }  
}
