package me.galaxy.archetype.repo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 11:10 下午
 **/
@Data
@Table(name = "blog_user")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "position")
    private String position;

}