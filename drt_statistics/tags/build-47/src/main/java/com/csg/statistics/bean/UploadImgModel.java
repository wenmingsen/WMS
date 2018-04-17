package com.csg.statistics.bean;

/**
 * Description: 上传图片对象模型
 * Company: Syni
 * @author 杨彬俊
 * @version 1.0
 * @since 2017-11-01
 */
public class UploadImgModel {

    private String userId; //用户标识
    private String base64Str; //base64字符串
    private String suffix; //后缀，如：.jpg、.png

    public UploadImgModel() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBase64Str() {
        return base64Str;
    }

    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
