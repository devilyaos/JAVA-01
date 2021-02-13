package com.devilyaos.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestService {

    public void printResult1() {
        log.info("====> 1. XmlBeanFactory方式获取");
    }

    public void printResult2() {
        log.info("====> 2. ApplicationContext获取");
    }
}
