package com.keepgoing.keepserver.domain.book.entity.dto;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookDto {
    private long id;
    private String bookName;
    private String writer;
    private String imageUrl;
    private String nfcCode;
    private LocalDateTime registrationDate;
    private LocalDateTime rentDate;
    private BookState state;
}
