package com.devilyaos.study.autoconfig;

import com.devilyaos.study.school.dto.Klass;
import com.devilyaos.study.school.dto.School;
import com.devilyaos.study.school.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Collections;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "study", name = "enabled", matchIfMissing = true)
public class StudySchoolBinder {

    @Bean
    public Student bindStudentConfig(ConfigurableEnvironment environment) {
        log.info("初始化学生信息");
        Student student = new Student();
        student.setName(getPropValue(environment, "study.student.name", ""));
        student.setAge(Integer.parseInt(getPropValue(environment, "study.student.age", "0")));
        return student;
    }

    @Bean
    public Klass bindKlassConfig(ConfigurableEnvironment environment) {
        log.info("初始化教室信息");
        Klass klass = new Klass();
        klass.setName(getPropValue(environment, "study.klass.name", ""));
        // 直接使用@Bean配置的方法会自动实现依赖注入
        klass.setStudents(Collections.singletonList(bindStudentConfig(environment)));
        return klass;
    }

    @Bean
    public School bindSchoolConfig(ConfigurableEnvironment environment) {
        log.info("初始化学校信息");
        School school = new School();
        school.setName(getPropValue(environment, "study.school.name", ""));
        // 通过调用@Bean注解的方法自动实现依赖注入
        school.setKlasses(Collections.singletonList(bindKlassConfig(environment)));
        return school;
    }

    private String getPropValue(ConfigurableEnvironment environment, String key, String defValue) {
        return environment.containsProperty(key) ? environment.getProperty(key) : defValue;
    }
}
