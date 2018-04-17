package com.csg.statistics.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Description: JSON响应工具类
 * Company: Syni
 * @version 1.0
 * @author 杨彬俊
 * @since 2017-11-01
 */
public class JSonResponse {

    public final String resultCodeName="state";//返回结果标志
    public final String resultInfoName="message";//返回消息标志
    public final String dataName="data";//返回对象标志

    public JSonResponse() {

    }

    public String toJSONString(String state, String message, Object data) {
        return toJSONString(state, message, dataName, data);
    }

    public String toJSONString(String state, String message, String name, Object data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(resultCodeName, state);
        jsonObj.put(resultInfoName, message);
        try {
            if (data instanceof String) {
                jsonObj.put(dataName, data == null ? "" : data);
            } else if(data instanceof List) {
                jsonObj.put(dataName, data == null ? "[]" : JSONArray.fromObject(data).toString());
            } else if(data instanceof Object) {
                jsonObj.put(dataName, data == null ? "" : JSONObject.fromObject(data).toString());
            }
        } catch (Exception e) {
            jsonObj.put(name, "JSON format error:" + e.getMessage());
        }
        return jsonObj.toString();
    }

    public JSONObject toJSONObj(String state, String message, String name, Object data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(resultCodeName, state);
        jsonObj.put(resultInfoName, message);
        try {
            if (data instanceof String) {
                jsonObj.put(dataName, data == null ? "" : data);
            } else if(data instanceof List) {
                jsonObj.put(dataName, data == null ? "[]" : JSONArray.fromObject(data).toString());
            } else if(data instanceof Object) {
                jsonObj.put(dataName, data == null ? "" : JSONObject.fromObject(data).toString());
            }
        } catch (Exception e) {
            jsonObj.put(name, "JSON format error:" + e.getMessage());
        }
        return jsonObj;
    }

    public String toDataString(String sessionId, String dataName, Object data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sessionId", sessionId);
        jsonObj.put("timestamp", System.currentTimeMillis());
        try {
            if (data instanceof String) {
                jsonObj.put(dataName, data == null ? "" : data);
            } else if(data instanceof List) {
                jsonObj.put(dataName, data == null ? "[]" : JSONArray.fromObject(data).toString());
            } else if(data instanceof Object) {
                jsonObj.put(dataName, data == null ? "" : JSONObject.fromObject(data).toString());
            }
        } catch (Exception e) {
            jsonObj.put(dataName, "JSON format error:" + e.getMessage());
        }
        return jsonObj.toString();
    }

    public JSONObject toDataObj(String sessionId, String dataName, Object data) {
        JSONObject jsonObj = new JSONObject();
       // jsonObj.put("sessionId", sessionId);
        //jsonObj.put("timestamp", System.currentTimeMillis());
        try {
            if (data instanceof String) {
                jsonObj.put(dataName, data == null ? "" : data);
            } else if(data instanceof List) {
                jsonObj.put(dataName, data == null ? "[]" : JSONArray.fromObject(data));
            } else if(data instanceof Object) {
                jsonObj.put(dataName, data == null ? "" : JSONObject.fromObject(data));
            }
        } catch (Exception e) {
            jsonObj.put(dataName, "JSON format error:" + e.getMessage());
        }
        return jsonObj;
    }
}
