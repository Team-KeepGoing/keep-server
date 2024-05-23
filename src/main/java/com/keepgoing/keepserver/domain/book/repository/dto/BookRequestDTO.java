package com.keepgoing.keepserver.domain.book.repository.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class BookRequestDTO {
    private String name;
    private String nfcCode;
    private String imageUrl;
    private String state;

}
