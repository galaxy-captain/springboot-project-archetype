package me.galaxy.archetype.web.config.whitelist;

import me.galaxy.archetype.web.config.session.SessionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/30 6:52 下午
 **/
@EnableConfigurationProperties({WhiteListProperties.class})
@Component
public class PropertiesWhiteListFilter implements WhiteListFilter {

    @Autowired
    private WhiteListProperties whiteListProperties;

    @Override
    public boolean check(String url) {
        return this.whiteListProperties.getWhiteList().contains(url);
    }

}