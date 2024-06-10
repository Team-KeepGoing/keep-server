package com.keepgoing.keepserver.domain.book.entity.dto;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import lombok.Getter;

@Getter
public class BookDTO {
    private long id;
    private String bookName;
    private String imageUrl;
    private BookState state;
}