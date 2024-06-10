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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int grade;

    @Column(name = "student_group", nullable = false)
    private int group;

    @Column(nullable = false)
    private int groupNum;

    @Column(nullable = false)
    private int phoneNum;

    @Column(nullable = false)
    private String address;
}
