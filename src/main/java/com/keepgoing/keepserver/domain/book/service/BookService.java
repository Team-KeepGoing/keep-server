package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.domain.Book;

import java.util.List;

public interface BookService {
    public void bookRegistration(Book book);
    public List<Book> selectAllBook();
    public Book selectByTitle(String title);
    public void editBook(Book book);
    public void deleteBook(String name);
}
