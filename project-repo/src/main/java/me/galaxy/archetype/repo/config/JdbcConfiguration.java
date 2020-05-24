package me.galaxy.archetype.repo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.core.strategy.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.shardingsphere.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Role;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 8:15 下午
 **/
@EnableTransactionManagement
@Configuration
public class JdbcConfiguration {

    @Bean
    public MasterSlaveLoadBalanceAlgorithm masterSlaveLoadBalanceAlgorithm() {
        return new RoundRobinMasterSlaveLoadBalanceAlgorithm();
    }

    @Bean
    public MasterSlaveRuleConfiguration masterSlaveRuleConfiguration() {
        return new MasterSlaveRuleConfiguration("shardingDataSource",
                "masterDS",
                Arrays.asList("slave1DS", "slave2DS"));
    }

    @Bean("configMap")
    public Map<String, Object> configMap() {
        Map<String, Object> config = new HashMap<>();
        return config;
    }

    @Bean("props")
    public Properties props() {
        Properties props = new Properties();
        props.put("sql.show", true);
        return props;
    }

    @Primary
    @Bean
    public DataSource dataSource(MasterSlaveRuleConfiguration masterSlaveRuleConfiguration, Properties props) throws SQLException {

        Map<String, DataSource> slaveMap = new HashMap<>();
        slaveMap.put("masterDS", masterDS());
        slaveMap.put("slave1DS", slave1DS());
        slaveMap.put("slave2DS", slave2DS());

        return MasterSlaveDataSourceFactory.createDataSource(slaveMap, masterSlaveRuleConfiguration, props);
    }

    public DruidDataSource masterDS() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.100.100:3306/blog");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    public DruidDataSource slave1DS() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.100.101:3306/blog");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    public DruidDataSource slave2DS() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.100.102:3306/blog");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

}