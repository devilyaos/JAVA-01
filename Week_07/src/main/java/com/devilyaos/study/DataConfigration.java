package com.devilyaos.study;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 实验性质, 配置写死
 */
@Configuration
public class DataConfigration {

    @Bean
    public DataSource readSource() {
        return new HikariDataSource(getHikariConfig("jdbc:mysql://127.0.0.1:3306/test_r", "readuser", "testread"));
    }


    @Bean
    public DataSource writeSource() {
        return new HikariDataSource(getHikariConfig("jdbc:mysql://127.0.0.1:3306/test_w", "writeuser", "testwrite"));
    }

    /**
     * 获取公用配置
     * @param url 数据库地址
     * @param username 用户名
     * @param password 密码
     * @return 数据源配置信息
     */
    private HikariConfig getHikariConfig(String url, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        // 开启批量插入重写优化
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("characterEncoding", "utf8");
        config.addDataSourceProperty("useSSL", "false");
        return config;
    }
}
