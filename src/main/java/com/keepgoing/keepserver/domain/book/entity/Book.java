package com.keepgoing.keepserver.domain.book.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book")
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nfcCode;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private Date registrationDate;

    @Column(nullable = false)
    private String state; //대여 불가능시 '1' 대여 가능시 '0'

    @Builder
    public Book(String name, String nfcCode, String writer, Date registrationDate, String state) {
        this.name = name;
        this.nfcCode = nfcCode;
        this.writer = writer;
        this.registrationDate = registrationDate;
        this.state = state;
    }
}
