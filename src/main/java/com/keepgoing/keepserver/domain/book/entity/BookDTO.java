package com.keepgoing.keepserver.domain.book.entity;

import com.keepgoing.keepserver.domain.book.util.GenerateCertCharacter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class BookDTO {
    GenerateCertCharacter generateCertCharacter = new GenerateCertCharacter();
    private long id;
    private String name;
    private String nfcCode = generateCertCharacter.excuteGenerate();
    private String writer;
    private Date registrationDate;
    private String state = "N";



}
