package me.galaxy.archetype.repo;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountMapper extends Mapper<Account> {

}
