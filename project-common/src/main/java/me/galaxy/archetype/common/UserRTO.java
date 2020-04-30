package me.galaxy.archetype.common;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 10:36 下午
 **/
@Data
public class UserRTO {

    private String name;

    private String sex;

    private long age;

    private Date birthday;

    private String position;

}