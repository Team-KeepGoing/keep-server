package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;

import java.util.List;

public interface BookService {
    public void bookRegistration(Book book);
    public List<Book> selectAllBook();
    public String deleteBookByNfcCode(String nfcCode);
    public String createNfcCode();

//    public void editBookByNfcCode(String nfcCode);
//    TODO
}


