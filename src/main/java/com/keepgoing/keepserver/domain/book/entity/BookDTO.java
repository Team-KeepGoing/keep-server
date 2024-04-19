package com.keepgoing.keepserver.domain.book.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class BookDTO {
    private long id;
    private String name;
    private String nfcCode;
    private String writer;
    private Date registrationDate;
    private String state;



}
