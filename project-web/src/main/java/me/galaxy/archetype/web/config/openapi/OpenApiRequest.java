package me.galaxy.archetype.web.config.openapi;

import me.galaxy.archetype.common.base.DTO;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 3:58 下午
 **/
public class OpenApiRequest<T> implements DTO.Parameter {

    private String appId;

    private String signature;

    private String timestamp;

    private T data;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}