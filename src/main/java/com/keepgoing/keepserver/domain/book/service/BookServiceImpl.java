package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.domain.Book;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    Date today = new Date();

    @Override
    public void bookRegistration(Book book) {
        book.setRegistrationDate(today);
        if(book.getState()=="L"){ //L잃어버림, //N 정상
            book.setState("L");
        }else{
            book.setState("N");
        }
        bookRepository.save(book);

    }

    @Override
    public ArrayList<Book> selectAllContent() {
        return null;
    }

    @Override
    public Book selectByTitle(String title) {
        return null;
    }

    @Override
    public void editBook(Book book) {

    }

    @Override
    public void deleteBook(String book) {

    }
}
