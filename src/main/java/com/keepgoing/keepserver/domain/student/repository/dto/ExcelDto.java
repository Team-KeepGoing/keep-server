package com.keepgoing.keepserver.domain.student.repository.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelDto {
    private String name;
    private int grade;
    private int group;
    private int groupNum;
    private int phoneNum;
    private String address;
}
