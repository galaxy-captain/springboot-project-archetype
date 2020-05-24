package me.galaxy.archetype.repo;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 9:38 下午
 **/
@Data
@Table(name = "blog_account")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String username;

}