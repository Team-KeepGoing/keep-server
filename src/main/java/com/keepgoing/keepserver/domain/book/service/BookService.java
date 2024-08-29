package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.payload.request.BookDto;
import com.keepgoing.keepserver.domain.book.payload.request.BookRequestDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;
import java.io.IOException;

public interface BookService {
    BaseResponse bookRegister(BookDto bookDto);
    BaseResponse selectAllBook();
    BaseResponse deleteBook(String nfcCode, Authentication auth);
    BaseResponse selectMyBook(Authentication auth);
    BaseResponse alertMyBook(Authentication auth, String date);
    String createNfcCode();
    BaseResponse editBook(String nfcCode, BookRequestDto bookRequest) throws IOException;
}


