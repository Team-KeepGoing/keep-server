package com.keepgoing.keepserver.domain.book.payload.request;

import com.keepgoing.keepserver.domain.book.entity.enums.BookState;
import lombok.Getter;

@Getter
public class BookRequestDto {
    private String name;
    private String nfcCode;
    private String imageUrl;
    private BookState state;
}
