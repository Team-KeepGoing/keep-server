package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
//    public void bookRegistration2(Book book, MultipartFile multipartFile);
//    public List<Book> selectAllBook2();
//    public String deleteBookByNfcCode2(String nfcCode);
//    public String createNfcCode2();

    BaseResponse bookRegister(Book book, MultipartFile multipartFile);
    BaseResponse selectAllBook();
    BaseResponse deleteBook(String nfcCode);
    public String createNfcCode();

}


