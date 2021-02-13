package com.devilyaos;

import com.devilyaos.service.TestAnnotationService;
import com.devilyaos.service.TestConfigService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AutoRunner implements ApplicationRunner {

    @Resource
    private TestAnnotationService testAnnotationService;

    @Resource
    private TestConfigService testConfigService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testAnnotationService.print01();
        testConfigService.print01();
    }
}
