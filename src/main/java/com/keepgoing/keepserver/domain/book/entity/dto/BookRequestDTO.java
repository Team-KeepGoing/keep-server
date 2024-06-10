package com.keepgoing.keepserver.domain.book.entity.dto;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import lombok.Getter;

@Getter
public class BookRequestDTO {
    private String name;
    private String nfcCode;
    private String imageUrl;
    private BookState state;
}
