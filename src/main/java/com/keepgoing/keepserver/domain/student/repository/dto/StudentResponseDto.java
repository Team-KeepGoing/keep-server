package com.keepgoing.keepserver.domain.student.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentResponseDto {
    String studentName;
    String format;
    String phoneNum;
    String mail;
}
