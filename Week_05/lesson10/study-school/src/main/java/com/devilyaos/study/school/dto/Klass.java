package com.devilyaos.study.school.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Klass {

    private String name;

    private List<Student> students;

}
