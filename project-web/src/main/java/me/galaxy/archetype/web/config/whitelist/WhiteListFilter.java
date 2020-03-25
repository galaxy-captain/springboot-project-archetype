package me.galaxy.archetype.web.config.whitelist;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 2:31 下午
 **/
public interface WhiteListFilter {

    boolean check(String url);

}