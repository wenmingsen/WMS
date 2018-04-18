package com.csg.intshop.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.csg.intshop.javacommon.SystemConstants;
import com.csg.personcenter.entity.DrtAccountView;
import com.csg.personcenter.entity.mybatis.DrtAccount;

public class ResultMapHelper {
    public static final String resultCodeName = "state";//返回结果标志  0 -1 404 500 
    public static final String dataName = "data";//返回对象标志
    public static final String errorMsgName = "message";//返回消息标志


    //返回结果样式
    public static Map<String, Object> result(String status, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, status);
        result.put(errorMsgName, message);
        result.put(dataName, data);
        return result;
    }
    //返回结果样式
    public static Map<String, Object> result(String status, String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, status);
        result.put(errorMsgName, message);
        return result;
    }
   //失败返回结果样式
    public static Map<String, Object> failure(String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_FAILED);
        result.put(errorMsgName, message);
        return result;
    }
    //异常返回结果样式
    public static Map<String, Object> unusual(String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_UNUSUAL);
        result.put(errorMsgName, message);
        return result;
    }
    
    //成功返回结果样式
    public static Map<String, Object> success(Object obj) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_SUCCESS);
        result.put(dataName, obj);
        return result;
    }
    //成功返回结果样式
    public static Map<String, Object> success(Object obj, String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_SUCCESS);
        result.put(dataName, obj);
        result.put(errorMsgName, message);
        return result;
    }
    //成功返回结果样式
    public static Map<String, Object> success() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.RESULT_CODE_SUCCESS);
        result.put(errorMsgName, "操作成功");
        return result;
    }
    //设置返回结果样式
    public static void setData(Map<String, Object> map, Object obj) {
        map.put(dataName, obj);
    }
    //判断是否成功返回
    public static boolean isSuccess(Map<String, Object> result) {
        String status = result.get(resultCodeName).toString();
        if (StringUtils.equals(status, SystemConstants.RESULT_CODE_SUCCESS)) {
            return true;
        }
        return false;
    }

    

    
    public static JSONObject getReturnAccountViewData(DrtAccount drtAccount) {
        JSonResponse jsonResponse = new JSonResponse();
        DrtAccountView accountView = new DrtAccountView();
        if (drtAccount != null) {
            accountView.setAccountId(drtAccount.getAccountId());
            accountView.setNickname(drtAccount.getNickname());
//            accountView.setPhone(drtAccount.getPhone().substring(0, 4) + "*****" + drtAccount.getPhone().substring(9, drtAccount.getPhone().length()));
            accountView.setPhone(drtAccount.getPhone());
            accountView.setPhotoUrl(drtAccount.getImgURL());
            if (drtAccount.getGesturePwd() == null || "".equals(drtAccount.getGesturePwd())) {
                accountView.setEnableGesPwd(SystemConstants.GESTUREPASSWORD_DISABLE);
            } else {
                accountView.setEnableGesPwd(SystemConstants.GESTUREPASSWORD_ENABLE);
            }
        }
        return jsonResponse.toDataObj(drtAccount.getSessionId(), "user", accountView);
    }

    public static JSONObject getReturnAccountViewData2(String sessionId, DrtAccountView accountView) {
        JSonResponse jsonResponse = new JSonResponse();
        return jsonResponse.toDataObj(sessionId, "user", accountView);
    }
    
    /**
     * 设置 404返回结果
     * */
    public static Map<String, Object> error404() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.ERROR_STATUS_404);
        result.put(errorMsgName, "请求不存在");
        return result;
    }
    
    /**
     * 设置 500返回结果
     * */
    public static Map<String, Object> error500() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(resultCodeName, SystemConstants.ERROR_STATUS_500);
        result.put(errorMsgName, "请求错误");
        return result;
    }
}