package com.keepgoing.keepserver.domain.book.entity;


import com.keepgoing.keepserver.domain.book.consts.BookState;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(updatable = false, nullable = false)
    private String nfcCode;

    @Column(nullable = false)
    private String writer;

    @Column(updatable = false, nullable = false)
    private Date registrationDate;

    @Column(updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private BookState state; //대여 불가능시 '1' 대여 가능시 '0'

    private String imageUrl; //책 이미지 링크

    @Builder
    public Book(String name, String nfcCode, String writer, Date registrationDate, BookState state, String image) {
        this.name = name;
        this.nfcCode = nfcCode;
        this.writer = writer;
        this.registrationDate = registrationDate;
        this.state = state;
        this.imageUrl = image;
    }
}