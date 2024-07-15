package com.keepgoing.keepserver.domain.student.domain.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDto {
    private String studentName;
    private String studentId;
    private String phoneNum;
    private String address;
    private String mail;
}
