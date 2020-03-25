package me.galaxy.archetype.repo;

import lombok.Data;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 9:38 下午
 **/
@Data
public class Account {

    private String account;

    private String password;

    private Long userId;

    private String username;

}