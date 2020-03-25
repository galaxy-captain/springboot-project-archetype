package me.galaxy.archetype.infra.auth;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 2:07 下午
 **/
@Component
public class LocalAuthentication implements Authentication {

    @Override
    public boolean check(String token) {
        return false;
    }

}