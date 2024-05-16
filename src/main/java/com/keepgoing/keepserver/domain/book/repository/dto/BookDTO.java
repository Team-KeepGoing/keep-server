package com.keepgoing.keepserver.domain.book.repository.dto;

import com.keepgoing.keepserver.domain.book.util.GenerateCertCharacter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    GenerateCertCharacter generateCertCharacter = new GenerateCertCharacter();
    private long id;
    private String name;
    private String nfcCode;
    private String writer;
    private Date registrationDate;
    private String state = "0";


}
