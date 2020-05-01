package me.galaxy.archetype.infra.auth;

import me.galaxy.archetype.infra.sequence.Sequences;
import me.galaxy.archetype.infra.utils.JsonUtils;
import me.galaxy.archetype.repo.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 本地鉴权
 * @Author galaxy-captain
 * @Date 2020/3/25 2:07 下午
 **/
@Component
public class LocalAuthentication implements Authentication<User> {

    private final String TOKEN = "TOKEN";

    private final Map<String, User> session = new HashMap<>();

    @Override
    public String register(User obj) {
        String token = Sequences.next().toUpperCase();
        String key = TOKEN + ":" + token;
        this.session.put(key, obj);
        return token;
    }

    @Override
    public User lookup(String token) {
        String key = TOKEN + ":" + token;
        return this.session.get(key);
    }

    @Override
    public boolean check(String token) {
        String key = TOKEN + ":" + token;
        return this.session.containsKey(key);
    }

    @Override
    public void remove(String token) {
        String key = TOKEN + ":" + token;
        this.session.remove(key);
    }

}