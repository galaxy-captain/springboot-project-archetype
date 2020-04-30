package me.galaxy.archetype.infra.auth;

import me.galaxy.archetype.infra.sequence.Sequences;
import me.galaxy.archetype.infra.utils.JsonUtils;
import me.galaxy.archetype.infra.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/30 6:30 下午
 **/
@Component
public class RedisAuthentication implements Authentication {

    private final String TOKEN = "TOKEN";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String register(Object obj) {
        String token = Sequences.next().toUpperCase();
        String key = TOKEN + ":" + token;
        String value = JsonUtils.toJsonString(obj);
        redisTemplate.opsForValue().set(key, value);
        return token;
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