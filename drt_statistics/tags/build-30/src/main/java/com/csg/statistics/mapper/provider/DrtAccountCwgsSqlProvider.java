package com.csg.statistics.mapper.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.csg.statistics.entity.DrtAccountCwgs;
import com.csg.statistics.util.StringUtils;

/**
 * Description:账户信息（文件）表
 * Company: Syni
 * @author 李达才
 * @since 2017-12-1
 *
 */
public class DrtAccountCwgsSqlProvider {

    public String insertAccountCwgs(final DrtAccountCwgs drtAccountCwgs) {
        return new SQL() {
            {
                INSERT_INTO("DRT_ACCOUNT_CWGS");
                if (!StringUtils.isEmpty(drtAccountCwgs.getDrtAccountId())) {
                    VALUES("DRT_ACCOUNT_ID", "#{drtAccountId}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getAccountNumber())) {
                    VALUES("ACCOUNT_NUMBER", "#{accountNumber}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getAccountName())) {
                    VALUES("ACCOUNT_NAME", "#{accountName}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getClientNumber())) {
                    VALUES("CLIENT_NUMBER", "#{clientNumber}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getClientName())) {
                    VALUES("CLIENT_NAME", "#{clientName}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getStandbyOne())) {
                    VALUES("STANDBY_ONE", "#{standbyOne}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getStandbyTwo())) {
                    VALUES("STANDBY_TWO", "#{standbyTwo}");
                }
                if (drtAccountCwgs.getUpdateTime() != null) {
                    VALUES("UPDATE_TIME", "#{updateTime}");
                }
            }
        }.toString();
    }

    public String updateAccountCwgs(final DrtAccountCwgs drtAccountCwgs) {
        return new SQL() {
            {
                UPDATE("DRT_ACCOUNT_CWGS");
                if (!StringUtils.isEmpty(drtAccountCwgs.getAccountNumber())) {
                    VALUES("ACCOUNT_NUMBER", "#{accountNumber}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getAccountName())) {
                    VALUES("ACCOUNT_NAME", "#{accountName}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getClientNumber())) {
                    VALUES("CLIENT_NUMBER", "#{clientNumber}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getClientName())) {
                    VALUES("CLIENT_NAME", "#{clientName}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getStandbyOne())) {
                    VALUES("STANDBY_ONE", "#{standbyOne}");
                }
                if (!StringUtils.isEmpty(drtAccountCwgs.getStandbyTwo())) {
                    VALUES("STANDBY_TWO", "#{standbyTwo}");
                }
                if (drtAccountCwgs.getUpdateTime() != null) {
                    VALUES("UPDATE_TIME", "#{updateTime}");
                }
                WHERE("DRT_ACCOUNT_ID = #{drtAccountId}");
            }
        }.toString();
    }
    
    public String insertAccountCwgsAll(Map map) {  
		List<DrtAccountCwgs> dacs = (List<DrtAccountCwgs>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO DRT_ACCOUNT_CWGS ");  
        sb.append("(DRT_ACCOUNT_ID, ACCOUNT_NUMBER, ACCOUNT_NAME, CLIENT_NUMBER, CLIENT_NAME, STANDBY_ONE, STANDBY_TWO, UPDATE_TIME) VALUES"); 
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].drtAccountId}, #'{'list[{0}].accountNumber}, #'{'list[{0}].accountName}, "
        		+ "#'{'list[{0}].clientNumber}, #'{'list[{0}].clientName}, #'{'list[{0}].standbyOne}, #'{'list[{0}].standbyTwo}, "
        		+ "#'{'list[{0}].updateTime})");  
        for (int i = 0; i < dacs.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < dacs.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
    }  
    
    public String deleteAccountCwgs(Map map) {  
        List<DrtAccountCwgs> dacs = (List<DrtAccountCwgs>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("DELETE FROM DRT_ACCOUNT_CWGS WHERE ACCOUNT_NUMBER IN ( ");  
        MessageFormat mf = new MessageFormat("#'{'list[{0}].accountNumber}");  
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
