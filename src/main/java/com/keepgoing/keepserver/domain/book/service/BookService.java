package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.dto.BookRequestDTO;
import com.keepgoing.keepserver.global.common.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface BookService {
    BaseResponse bookRegister(Book book);
    BaseResponse selectAllBook();
    BaseResponse deleteBook(String nfcCode);
    BaseResponse selectMyBook(Authentication auth);
    public String createNfcCode();
    @Transactional
    BaseResponse editBook(String nfcCode, BookRequestDTO bookRequest) throws IOException;
}


