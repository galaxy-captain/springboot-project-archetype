package me.galaxy.archetype.web.config.whitelist;

import org.springframework.stereotype.Component;

/**
 * @Description 本地白名单
 * @Author galaxy-captain
 * @Date 2020/3/25 2:34 下午
 **/
public class LocalWhiteListFilter implements WhiteListFilter {

    @Override
    public boolean check(String url) {
        return false;
    }

}