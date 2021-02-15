package com.devilyaos.study.demo;

import com.devilyaos.study.school.dto.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@Slf4j
@EnableAutoConfiguration
public class TestService {

    @Autowired
    private School school;

    public static void main(String[] args) {
        SpringApplication.run(TestService.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> log.info("[输出结果请注意!!!] ====> {}", school.toString());
    }

}
