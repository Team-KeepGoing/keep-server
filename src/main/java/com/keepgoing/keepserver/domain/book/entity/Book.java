package com.keepgoing.keepserver.domain.book.entity;


import com.keepgoing.keepserver.domain.book.entity.enums.BookState;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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

    /*
        책 제목
    */
    @Column(nullable = false)
    private String bookName;

    /*
        NFC 코드
    */
    @Column(updatable = false)
    private String nfcCode;

    /*
        작가
    */
    @Column(nullable = false)
    private String writer;

    /*
        도서 등록일
    */
    @Column(updatable = false, nullable = false)
    private LocalDateTime registrationDate;

    /*
        도서 대여 여부
    */
    @Column(updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private BookState state;

    /*
        책 이미지 url
    */
    @Column
    private String imageUrl;

    /*
        기기 대여 시작일
    */
    @Column
    private LocalDateTime rentDate;

    /*
        책 대여자
    */
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @Builder
    public Book(String bookName, String nfcCode, String writer, LocalDateTime registrationDate, BookState state, String imageUrl, LocalDateTime rentDate, User borrower) {
        this.bookName = bookName;
        this.nfcCode = nfcCode;
        this.writer = writer;
        this.registrationDate = registrationDate;
        this.state = state;
        this.imageUrl = imageUrl;
        this.rentDate = rentDate;
        this.borrower = borrower;
    }
}

