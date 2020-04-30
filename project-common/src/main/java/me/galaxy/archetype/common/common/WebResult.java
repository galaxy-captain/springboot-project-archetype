package me.galaxy.archetype.common.common;

public class WebResult implements DTO {

    private String code = "000000";

    private String msg = "ok";

    private Object data = null;

    public WebResult() {

    }

    public WebResult(Object data) {
        this.data = data;
    }

    public WebResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WebResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static WebResult success(Object data) {
        return new WebResult(data);
    }

}