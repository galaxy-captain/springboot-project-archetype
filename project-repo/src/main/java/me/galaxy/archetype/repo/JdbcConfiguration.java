package me.galaxy.archetype.repo;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 8:15 下午
 **/
@Configuration
public class JdbcConfiguration {

    @Primary
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setEnable(true);
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

}