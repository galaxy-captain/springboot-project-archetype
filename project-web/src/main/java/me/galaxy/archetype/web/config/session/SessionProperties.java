package me.galaxy.archetype.web.config.session;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/24 3:29 下午
 **/
@ConfigurationProperties(prefix = "spring.http.session")
@Configuration
public class SessionProperties {

    private String token;

    private String language;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}