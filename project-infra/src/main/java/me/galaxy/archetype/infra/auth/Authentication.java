package me.galaxy.archetype.infra.auth;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description 鉴权服务
 * @Author galaxy-captain
 * @Date 2020/3/25 1:57 下午
 **/
public interface Authentication<T> {

    /**
     * 授权
     */
    String register(T obj);

    /**
     * 查看
     */
    T lookup(String token);

    /**
     * 鉴权
     */
    boolean check(String token);

    /**
     * 注销
     */
    void remove(String token);

}