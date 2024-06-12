package com.keepgoing.keepserver.domain.student.repository.dto;


import com.keepgoing.keepserver.domain.student.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String studentName;
    private int grade;
    private int group;
    private int groupNum;
    private String phoneNum;
    private String address;
    private String mail;

    public Student toEntity() {
        return Student.builder()
                .studentName(this.studentName)
                .grade(this.grade)
                .group(this.group)
                .groupNum(this.groupNum)
                .phoneNum(this.phoneNum)
                .address(this.address)
                .mail(this.mail)
                .build();
    }


}