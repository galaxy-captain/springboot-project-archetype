package me.galaxy.archetype.infra.auth;

import lombok.extern.slf4j.Slf4j;
import me.galaxy.archetype.common.error.WebErrors;
import me.galaxy.archetype.infra.exceptions.WebException;
import me.galaxy.archetype.infra.sequence.Sequences;
import me.galaxy.archetype.infra.utils.JsonUtils;
import me.galaxy.archetype.repo.Account;
import me.galaxy.archetype.repo.AccountRepository;
import me.galaxy.archetype.repo.User;
import me.galaxy.archetype.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description 授权服务
 * @Author galaxy-captain
 * @Date 2020/3/25 8:00 下午
 **/
@Slf4j
@Service
public class Authorization {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Authentication authentication;

    @Autowired
    private UserRepository userRepository;

    public String login(String acc, String pwd) {

        // 验证用户名和密码
        Account account = accountRepository.selectAccount(acc, pwd);
        if (account == null) {
            throw new WebException(WebErrors.LOGIN_ERROR.getMsg(), WebErrors.LOGIN_ERROR.getCode());
        }

        // 查询用户信息
        User user = userRepository.queryUser(account.getUserId());

        // 存储Session信息
        String token = authentication.register(user);

        return token;
    }

    public void logout(String token) {

        authentication.remove(token);

    }

}