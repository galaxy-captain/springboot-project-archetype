package me.galaxy.archetype.web.config.whitelist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/30 7:04 下午
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.http")
public class WhiteListProperties {

    private Set<String> whiteList;

    public Set<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(Set<String> whiteList) {
        this.whiteList = whiteList;
    }

}