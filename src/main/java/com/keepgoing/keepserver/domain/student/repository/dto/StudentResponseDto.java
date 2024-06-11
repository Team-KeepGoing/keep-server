package com.keepgoing.keepserver.domain.student.repository.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentResponseDto {
    long id;
    String studentName;
    String format;
    String phoneNum;
    String mail;
}
