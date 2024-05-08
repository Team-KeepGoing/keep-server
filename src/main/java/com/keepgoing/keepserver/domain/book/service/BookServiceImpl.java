package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
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

    @Override
    public void bookRegistration(Book book) {
        String nfcCode = createNfcCode();
        book.setRegistrationDate(new Date());
        book.setNfcCode(nfcCode);
        book.setState("N");
        bookRepository.save(book);
    }

    @Override
    public ArrayList<Book> selectAllBook() {
        return (ArrayList<Book>) bookRepository.findAll();

    }


    @Override
    public void deleteBookByNfcCode(String nfcCode) {
        bookRepository.deleteByNfcCode(nfcCode);
    }


    @Override
    public String createNfcCode() {
        GenerateCertCharacter generateCertCharacter = new GenerateCertCharacter();
        return generateCertCharacter.excuteGenerate();
    }
//
//    @Override
//    public void editBookByNfcCode(String nfcCode) {
//
//    }

}




