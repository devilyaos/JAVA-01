package com.devilyaos.study.school.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class School {

    private String name;

    private List<Klass> klasses;

}
