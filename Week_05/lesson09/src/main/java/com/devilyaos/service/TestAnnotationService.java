package com.devilyaos.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestAnnotationService {

    public void print01() {
        log.info("========================================");
        log.info("3. Spring自动装配获取");
        log.info("========================================");
    }
}
