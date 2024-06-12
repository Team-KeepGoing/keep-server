package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.dto.BookRequestDto;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    BaseResponse bookRegister(Book book, MultipartFile multipartFile);
    @Transactional(readOnly = true)
    BaseResponse selectAllBook();
    @Transactional(rollbackFor = Exception.class)
    BaseResponse deleteBook(String nfcCode, Authentication auth);
    BaseResponse selectMyBook(Authentication auth);
    String createNfcCode();
    @Transactional(rollbackFor = Exception.class)
    BaseResponse editBook(String nfcCode, BookRequestDto bookRequest) throws IOException;
}


