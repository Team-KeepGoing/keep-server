package com.keepgoing.keepserver.domain.student.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFindDto {
    String name;
    int grade;
    int group;
    int groupNum;
}
