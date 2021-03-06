package me.galaxy.archetype.repo;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 9:33 下午
 **/
@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountMapper accountMapper;

    public Account selectAccount(String account, String password) {
        List<Account> list = jdbcTemplate.query(
                "select account, password, salt, user_id, user_name from blog_account where account=? and password=?",
                new Object[]{
                        account,
                        password
                },
                (rs, rowNum) -> {
                    Account result = new Account();
                    result.setAccount(rs.getString(1));
                    result.setPassword(rs.getString(2));
                    result.setUserId(rs.getLong(4));
                    result.setUsername(rs.getString(5));
                    return result;
                }
        );
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    public Account insert(String account, String password, Long userId, String userName) {

        Account orm = new Account();
        orm.setAccount(account);
        orm.setPassword(password);
        orm.setSalt("100000");
        orm.setUserId(userId);
        orm.setUsername(userName);

        int result = accountMapper.insertSelective(orm);

        return orm;
    }

}