package com.devilyaos.config;

import com.devilyaos.service.TestConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TestConfig {

    @Bean("testConfigService")
    public TestConfigService registerTestConfigService() {
        return new TestConfigService();
    }
}
