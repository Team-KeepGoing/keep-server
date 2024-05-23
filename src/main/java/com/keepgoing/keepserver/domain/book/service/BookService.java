package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.dto.BookRequestDTO;
import com.keepgoing.keepserver.global.common.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    BaseResponse bookRegister(Book book, MultipartFile multipartFile);
    BaseResponse selectAllBook();
    BaseResponse deleteBook(String nfcCode);
    public String createNfcCode();
    @Transactional
    BaseResponse editBook(String nfcCode, BookRequestDTO bookRequest) throws IOException;
}


