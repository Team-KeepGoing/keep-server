package com.keepgoing.keepserver.domain.student.entity;

import com.keepgoing.keepserver.domain.student.repository.dto.StudentRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String mail;

    public void updateInstance(StudentRequestDto studentDto) {
        if (studentDto.getStudentName() != null) setStudentName(studentDto.getStudentName());
        if (studentDto.getStudentId() != null) setStudentId(studentDto.getStudentId());
        if (studentDto.getPhoneNum() != null) setPhoneNum(studentDto.getPhoneNum());
        if (studentDto.getMail() != null) setMail(studentDto.getMail());
        if (studentDto.getAddress() != null) setAddress(studentDto.getAddress());
    }
}
