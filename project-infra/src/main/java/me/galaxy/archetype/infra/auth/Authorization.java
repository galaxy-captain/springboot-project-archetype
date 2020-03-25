package me.galaxy.archetype.infra.auth;

import lombok.extern.slf4j.Slf4j;
import me.galaxy.archetype.common.error.WebErrors;
import me.galaxy.archetype.infra.exceptions.ServerException;
import me.galaxy.archetype.infra.exceptions.WebException;
import me.galaxy.archetype.repo.Account;
import me.galaxy.archetype.repo.AccountRepository;
import me.galaxy.archetype.repo.User;
import me.galaxy.archetype.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 8:00 下午
 **/
@Slf4j
@Component
public class Authorization {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public User login(String acc, String pwd) {

        Account account = accountRepository.queryAccount(acc, pwd);
        if (account == null) {
            throw new WebException(WebErrors.LOGIN_ERROR.getMsg(), WebErrors.LOGIN_ERROR.getCode());
        }

        User user = userRepository.queryUser(account.getUserId());

        log.info("");

        return user;
    }

}