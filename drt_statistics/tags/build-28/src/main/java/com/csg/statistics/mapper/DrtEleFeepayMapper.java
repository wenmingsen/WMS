package com.csg.statistics.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description：电费扣费帐单表dao
 * 
 * @version 1.0
 * @author 李达才
 * @since 2017-12-01
 *
 */
@Transactional
public interface DrtEleFeepayMapper {
	

	/**
	 * 查询时间范围内扣款交易数据
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	@Select("SELECT * FROM ( SELECT CONCAT( MAX(B.ORG_ID), "
			+ "DATE_FORMAT(SYSDATE(), '%Y%m%d'), UUID() ) AS YWSQBH, 'DRTKK' AS JYLX, SUM(B.SKJE) AS JE, DATE_FORMAT(MAX(B.CREAT_TIME), '%Y-%m-%d') AS RUNTIME, B.CLIENT_NUMBER, "
			+ "( SELECT DISTINCT C.ACCOUNT_NUMBER FROM DRT_ACCOUNT_CWGS C WHERE C.CLIENT_NUMBER = B.CLIENT_NUMBER AND C.ACCOUNT_NUMBER LIKE '00-32-%' "
			+ "LIMIT 1 ) AS FKFZHH, ( SELECT DISTINCT C.ACCOUNT_NUMBER FROM DRT_ACCOUNT_CWGS C WHERE C.CLIENT_NUMBER = B.CLIENT_NUMBER "
			+ "AND C.ACCOUNT_NUMBER LIKE '00-10-%' LIMIT 1 ) AS SKFZHH, ( SELECT DISTINCT C.ACCOUNT_NAME FROM DRT_ACCOUNT_CWGS C WHERE "
			+ "C.CLIENT_NUMBER = B.CLIENT_NUMBER AND C.ACCOUNT_NUMBER LIKE '00-10-%' LIMIT 1 ) AS SKFZHMC, '机制' AS SUBMIT_PERSON, DATE_FORMAT(SYSDATE(), '%Y-%m-%d %H:%i:%s') AS SUBMIT_TIME, "
			+ "( SELECT DISTINCT CONCAT( C.CLIENT_NAME, '划扣电费' ) FROM DRT_ACCOUNT_CWGS C WHERE C.CLIENT_NUMBER = B.CLIENT_NUMBER LIMIT 1 ) AS ZY, "
			+ "1 AS ZHLX FROM ( SELECT E.*, ( SELECT DISTINCT C.CLIENT_NUMBER FROM DRT_ACCOUNT_CWGS C, DRT_ACCOUNT_MARKET_AREA M WHERE "
			+ "C.CLIENT_NUMBER = CONCAT('00-', M.ACCOUNT_AREA) AND M.MARKET_AREA = E.ORG_ID LIMIT 1 ) AS CLIENT_NUMBER FROM DRT_ELE_FEEPAY E "
			+ "WHERE E.FEEPAY_RESULT = 0 AND E.CREAT_TIME >= #{startTime} AND E.CREAT_TIME < #{endTime} ) B GROUP BY B.CLIENT_NUMBER HAVING B.CLIENT_NUMBER IS NOT NULL ) F")
	List<Map<String,Object>> findListByCreatTime(@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
}

