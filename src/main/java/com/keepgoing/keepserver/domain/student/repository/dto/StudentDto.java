package com.keepgoing.keepserver.domain.student.repository.dto;


import com.keepgoing.keepserver.domain.student.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String name;
    private int grade;
    private int group;
    private int groupNum;
    private int phoneNum;
    private String address;

    public Student toEntity() {
        return Student.builder()
                .name(this.name)
                .grade(this.grade)
                .group(this.group)
                .groupNum(this.groupNum)
                .phoneNum(this.phoneNum)
                .address(this.address)
                .build();
    }


}