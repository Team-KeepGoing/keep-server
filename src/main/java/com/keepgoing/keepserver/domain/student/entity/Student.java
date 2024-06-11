package com.keepgoing.keepserver.domain.student.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(nullable = false)
    private int grade;

    @Column(name = "student_group", nullable = false)
    private int group;

    @Column(nullable = false)
    private int groupNum;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String mail;
}
