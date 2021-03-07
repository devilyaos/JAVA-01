package com.devilyaos.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableAutoConfiguration
public class DatasourceChangeApplication01 {

    @Autowired
    private ChangeDatasourceUtil changeDatasourceUtil;

    public static void main(String[] args) {
        SpringApplication.run(DatasourceChangeApplication01.class).close();
    }

    /**
     * 不用框架的思路除了用注解或拦截器无侵入hook之外, 也可以显式的使用一个新的客户端组合读写数据源, 并使用api进行显式的分离
     * 使用显式api调整时, 修改时容易观察到剩余调用方
     */
    @Bean
    public void runtest() {
        Long count = changeDatasourceUtil.query("select count(1) from test_table", Long.class);
        System.out.println("总量 " + count);

        int executeNum = changeDatasourceUtil.execute("update test_table set name = '1' where age > 2");
        System.out.println("执行了 " + executeNum + " 条");
    }
}
