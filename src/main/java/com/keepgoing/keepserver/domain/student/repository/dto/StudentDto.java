package com.keepgoing.keepserver.domain.student.repository.dto;


import com.keepgoing.keepserver.domain.student.entity.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDto {
    private String studentName;
    private int grade;
    private int group;
    private int groupNum;
    private String phoneNum;
    private String address;
    private String mail;

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