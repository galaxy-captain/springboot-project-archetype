package me.galaxy.archetype.infra.auth;

import me.galaxy.archetype.infra.sequence.Sequences;
import me.galaxy.archetype.infra.utils.JsonUtils;
import me.galaxy.archetype.infra.utils.StringUtils;
import me.galaxy.archetype.repo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/4/30 6:30 下午
 **/
@Component
public class RedisAuthentication implements Authentication<User> {

    private final String TOKEN = "TOKEN";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String register(User obj) {
        String token = Sequences.next().toUpperCase();
        String key = TOKEN + ":" + token;
        String value = JsonUtils.toJsonString(obj);
        redisTemplate.opsForValue().set(key, value);
        return token;
    }

    @Override
    public User lookup(String token) {
        String key = TOKEN + ":" + token;
        String session = redisTemplate.opsForValue().get(key);
        if (session == null) {
            return null;
        }
        User user = JsonUtils.parseJson(session, User.class);
        return user;
    }

    @Override
    public boolean check(String token) {

        if (StringUtils.isEmpty(token)) {
            return false;
        }

        String key = TOKEN + ":" + token;

        String session = redisTemplate.opsForValue().get(key);

        return session != null;
    }

    @Override
    public void remove(String token) {

        if (StringUtils.isEmpty(token)) {
            return;
        }

        String key = TOKEN + ":" + token;

        boolean isSuccess = redisTemplate.delete(key);

    }

}