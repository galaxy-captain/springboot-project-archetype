package me.galaxy.archetype.infra.httpclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/3/23 2:24 下午
 **/
@ConfigurationProperties(prefix = "spring.http.client")
public class HttpProperties {

    private Integer connectionRequestTimeout = 10_000;

    private Integer connectTimeout = 10_000;

    private Integer socketTimeout = 20_000;

    private Integer poolTotalMax = 256;

    private Integer poolRouteMax = 32;

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getPoolTotalMax() {
        return poolTotalMax;
    }

    public void setPoolTotalMax(Integer poolTotalMax) {
        this.poolTotalMax = poolTotalMax;
    }

    public Integer getPoolRouteMax() {
        return poolRouteMax;
    }

    public void setPoolRouteMax(Integer poolRouteMax) {
        this.poolRouteMax = poolRouteMax;
    }

}