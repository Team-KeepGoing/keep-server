package com.keepgoing.keepserver.domain.student.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDto {
    private String studentName;
    private int grade;
    private int group;
    private int groupNum;
    private String phoneNum;
    private String address;
    private String mail;
}
