package me.galaxy.archetype.infra.openapi;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 6:35 下午
 **/
public class OpenApiApplication {

    private String appId;

    private String appSecret;

    private String description;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}