
package me.galaxy.archetype.common.error;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/21 10:39 上午
 **/
public enum WebErrors {

    UNKNOWN_ERROR("100000", "系统未知异常，请联系管理员"),
    SERVER_ERROR("100001", "系统业务异常，请联系管理员"),
    AUTH_ERROR("100002", "请登录系统"),
    LOGIN_ERROR("100003", "用户名或密码错误");

    private String code;

    private String msg;

    WebErrors(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}