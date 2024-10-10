package com.keepgoing.keepserver.domain.student.domain.repository.dto;

import com.keepgoing.keepserver.domain.student.domain.entity.Student;

public record StudentDto(
        String studentName,
        int grade,
        int group,
        int groupNum,
        String phoneNum,
        String address,
        String mail
) {
    public String format() {
        final String FORMAT_PATTERN = "%d%d%02d";
        return String.format(FORMAT_PATTERN, this.grade, this.group, this.groupNum);
    }

    public Student toEntity() {
        return Student.builder()
                      .studentName(this.studentName)
                      .studentId(format())
                      .phoneNum(this.phoneNum)
                      .address(this.address)
                      .mail(this.mail)
                      .build();
    }

}