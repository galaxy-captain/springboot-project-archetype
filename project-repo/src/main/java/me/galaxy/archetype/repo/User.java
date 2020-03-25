package me.galaxy.archetype.repo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 11:10 下午
 **/
@Data
public class User {

    private String name;

    private String sex;

    private Date birthday;

    private String position;

}