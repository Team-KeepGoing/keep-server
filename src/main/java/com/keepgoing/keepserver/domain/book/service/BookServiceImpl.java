package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.BookDTO;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.book.util.GenerateCertCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    Date today = new Date();
    BookDTO bookDTO = new BookDTO();

    @Override
    public void bookRegistration(Book book) {
        book.setName(book.getName());
        book.setWriter(book.getName());
        book.setRegistrationDate(today);
        if(bookDTO.getState()=="L"){ //L잃어버림, //N 정상
            book.setState("L");
        }else{
            book.setState("N");
        }
        book.setNfcCode(bookDTO.getNfcCode());
        bookRepository.save(book);



    }

    @Override
    public ArrayList<Book> selectAllBook() {
        return (ArrayList<Book>) bookRepository.findAll();

    }


    @Override
    public void deleteBook(String name) {
        bookRepository.deleteBookByName(name);
    }


}
