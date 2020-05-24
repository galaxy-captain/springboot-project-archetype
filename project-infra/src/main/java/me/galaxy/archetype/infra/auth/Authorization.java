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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    private Authentication<User> authentication;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void register(String acc, String password, String name, String sex, Date birthday, String position) {
        User user = userRepository.insert(name, sex, birthday, position);
        Account account = accountRepository.insert(acc, password, user.getId(), user.getName());
    }

    public String login(String acc, String pwd) {

        // 验证用户名和密码
        Account account = accountRepository.selectAccount(acc, pwd);
        if (account == null) {
            throw new WebException(WebErrors.LOGIN_ERROR);
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