package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.book.util.GenerateCertCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public void bookRegistration(Book book, MultipartFile multipartFile) {

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
    public String deleteBookByNfcCode(String nfcCode) {
        if(nfcCode == null || nfcCode.isEmpty()){
            return "Invalid NFC code";
        }
        Book book = bookRepository.findBookByNfcCode(nfcCode);
        if(book == null){
            return "This book cannot be found";
        }
        else {
            bookRepository.delete(bookRepository.findBookByNfcCode(nfcCode));
            return "Deletion successful";
        }
    }
    @Override
    public String createNfcCode() {
        GenerateCertCharacter generateCertCharacter = new GenerateCertCharacter();
        String newNfcCode;
        do {
            newNfcCode = generateCertCharacter.excuteGenerate();
        } while (bookRepository.findBookByNfcCode(newNfcCode) != null);

        return newNfcCode;
    }


}




