package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.global.dto.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    public void bookRegistration(Book book, MultipartFile multipartFile);
    public List<Book> selectAllBook();
    public String deleteBookByNfcCode(String nfcCode);
    public String createNfcCode();

    BaseResponse findAll();

}


