package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.entity.BookDTO;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.book.util.GenerateCertCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

//    @Override
//    public void bookRegistration(Book book) {
//        book.builder()
//                .registrationDate(today)
//                .name(book.getName())
//                .nfcCode(createNfcCode())
//                .writer(book.getWriter())
//                .state("N")
//                .build();
//        bookRepository.save(book);
//    }

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
    public void deleteBook(String name) {
        bookRepository.deleteBookByName(name);
    }

    @Override
    public String createNfcCode() {
        GenerateCertCharacter generateCertCharacter = new GenerateCertCharacter();
        return generateCertCharacter.excuteGenerate();
    }


}
