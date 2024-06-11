package com.keepgoing.keepserver.domain.student.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFindDto {
    String studentName;
    int grade;
    int group;
    int groupNum;
    int num;
}
