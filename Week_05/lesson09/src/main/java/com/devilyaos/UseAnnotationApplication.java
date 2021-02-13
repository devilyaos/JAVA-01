package com.devilyaos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.devilyaos")
public class UseAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UseAnnotationApplication.class, args);
    }
}
