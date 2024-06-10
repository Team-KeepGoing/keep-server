package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.dto.BookRequestDTO;
import com.keepgoing.keepserver.global.common.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    BaseResponse bookRegister(Book book, MultipartFile multipartFile);
    BaseResponse selectAllBook();
    BaseResponse deleteBook(String nfcCode, Authentication auth);
    BaseResponse selectMyBook(Authentication auth);
    String createNfcCode();
    @Transactional
    BaseResponse editBook(String nfcCode, BookRequestDTO bookRequest) throws IOException;
}


