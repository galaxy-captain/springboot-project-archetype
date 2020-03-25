package me.galaxy.archetype.repo;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 11:10 下午
 **/
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User queryUser(Long id) {
        List<User> list = jdbcTemplate.query("select name,sex,birthday,position from blog_user where id=?",
                new Object[]{
                        id
                },
                (rs, rowNum) -> {
                    User user = new User();
                    user.setName(rs.getString(1));
                    user.setSex(rs.getString(2));
                    user.setBirthday(rs.getDate(3));
                    user.setPosition(rs.getString(4));
                    return user;
                });
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

}