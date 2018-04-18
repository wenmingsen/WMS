package com.csg.intshop.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.csg.intshop.javacommon.SystemConstants;

public class ResultMapHelper {
    public static final String resultCodeName = "state";//返回结果标志
    public static final String dataName = "data";//返回对象标志
    public static final String errorMsgName = "message";//返回消息标志


    public static Map<String, Object> result(String status, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, status);
        result.put(errorMsgName, message);
        result.put(dataName, data);
        return result;
    }

    public static Map<String, Object> result(String status, String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, status);
        result.put(errorMsgName, message);
        return result;
    }

    public static Map<String, Object> failure(String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_FAILED);
        result.put(errorMsgName, message);
        return result;
    }

    public static Map<String, Object> success(Object obj) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_SUCCESS);
        result.put(dataName, obj);
        return result;
    }

    public static Map<String, Object> success(Object obj, String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_SUCCESS);
        result.put(dataName, obj);
        result.put(errorMsgName, message);
        return result;
    }

    public static Map<String, Object> success() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_SUCCESS);
        result.put(errorMsgName, "操作成功");
        return result;
    }

    public static void setData(Map<String, Object> map, Object obj) {
        map.put(dataName, obj);
    }

    public static boolean isSuccess(Map<String, Object> result) {
        String status = result.get(resultCodeName).toString();
        if (StringUtils.equals(status, SystemConstants.RESULT_CODE_SUCCESS)) {
            return true;
        }
        return false;
    }

    

}