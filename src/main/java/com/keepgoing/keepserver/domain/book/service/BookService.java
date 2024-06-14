package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.dto.BookDto;
import com.keepgoing.keepserver.domain.book.entity.dto.BookRequestDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface BookService {
    BaseResponse bookRegister(BookDto bookDto);
    BaseResponse selectAllBook();
    BaseResponse deleteBook(String nfcCode, Authentication auth);
    BaseResponse selectMyBook(Authentication auth);
    String createNfcCode();
    BaseResponse editBook(String nfcCode, BookRequestDto bookRequest) throws IOException;
}


