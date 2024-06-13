package com.keepgoing.keepserver.domain.book.entity.dto;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class BookDto {
    private long id;
    private String bookName;
    private String imageUrl;
    private Date registrationDate;
    private LocalDateTime rentDate;
    private BookState state;
}
