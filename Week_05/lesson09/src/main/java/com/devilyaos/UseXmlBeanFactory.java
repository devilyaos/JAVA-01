package com.devilyaos;

import com.devilyaos.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Slf4j
public class UseXmlBeanFactory {
    public static void main(String[] args) {
        loadBean1();
        loadBean2();
    }

    /**
     * 通过ApplicationContext的方式加载bean
     */
    private static void loadBean2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-bean.xml");
        log.info("===============================");
        ((TestService) context.getBean("testService")).printResult2();
        log.info("===============================");
    }

    /**
     * 通过XmlBeanFactory的方式加载
     */
    private static void loadBean1() {
        Resource resource = new ClassPathResource("spring/spring-bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        log.info("===============================");
        ((TestService) factory.getBean("testService")).printResult1();
        log.info("===============================");
    }
}
