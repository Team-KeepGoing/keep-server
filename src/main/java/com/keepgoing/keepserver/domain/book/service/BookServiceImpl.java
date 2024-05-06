package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.book.util.GenerateCertCharacter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        bookRepository.deleteAllByNfcCode(nfcCode);
    }

    @Override
    public String createNfcCode() {
        GenerateCertCharacter generateCertCharacter = new GenerateCertCharacter();
        return generateCertCharacter.excuteGenerate();
    }

    @Override
    public void editBookByNfcCode(String nfcCode) {

    }
}

    class DirtyChecking {

        BookRepository bookRepository;


        @Transactional
        public void updateBook(String nfcCode, String name) {

            // DB에서 id값을 기준으로 데이터를 찾는다 (영속화)
            List<Book> book = bookRepository.findByNfcCode(nfcCode);


        }
    }



//}



