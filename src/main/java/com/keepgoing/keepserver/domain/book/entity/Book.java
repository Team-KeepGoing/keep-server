package com.keepgoing.keepserver.domain.book.entity;


import com.keepgoing.keepserver.domain.book.consts.BookState;
import com.keepgoing.keepserver.domain.user.entity.user.User;
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
    private String bookName;

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

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @Builder
    public Book(String bookName, String nfcCode, String writer, Date registrationDate, BookState state, String imageUrl) {
        this.bookName = bookName;
        this.nfcCode = nfcCode;
        this.writer = writer;
        this.registrationDate = registrationDate;
        this.state = state;
        this.imageUrl = imageUrl;
    }
}
