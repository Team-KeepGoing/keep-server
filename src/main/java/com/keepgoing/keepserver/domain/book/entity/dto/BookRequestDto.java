package com.keepgoing.keepserver.domain.book.entity.dto;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import lombok.Getter;

@Getter
public class BookRequestDto {
    private String name;
    private String writer;
    private String nfcCode;
    private String imageUrl;
    private BookState state;
}
