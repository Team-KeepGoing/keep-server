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
    private String studentName;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String mail;
}
