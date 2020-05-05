package me.galaxy.archetype.web.config.whitelist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/4/30 6:52 下午
 **/
@EnableConfigurationProperties({WhiteListProperties.class})
@Component
public class PropertiesWhiteListFilter implements WhiteListFilter {

    @Autowired
    private WhiteListProperties whiteListProperties;

    @Override
    public boolean check(String url) {
        for (String whiteUrl : this.whiteListProperties.getWhiteList()) {
            if (wildCardMatch(whiteUrl, url) || whiteUrl.equals(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean wildCardMatch(String whiteUrl, String url) {

        if (!whiteUrl.contains("*")) {
            return false;
        }

        // ** 前缀匹配
        if (whiteUrl.endsWith("**")) {
            whiteUrl = whiteUrl.substring(0, whiteUrl.length() - 2);
            if (whiteUrl.endsWith("/")) {
                whiteUrl = whiteUrl.substring(0, whiteUrl.length() - 1);
            }
            return url.startsWith(whiteUrl);
        }

        // * 通配符匹配
        String[] whiteUrlParts = whiteUrl.split("/");
        String[] urlParts = url.split("/");
        if (whiteUrlParts.length != urlParts.length) {
            return false;
        }
        for (int i = 0; i < whiteUrlParts.length; i++) {
            if (!(whiteUrlParts[i].equals("*") || whiteUrlParts[i].equals(urlParts[i]))) {
                return false;
            }
        }
        return true;
    }

}