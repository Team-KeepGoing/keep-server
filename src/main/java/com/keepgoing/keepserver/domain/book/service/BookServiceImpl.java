package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.book.repository.dto.BookRequestDTO;
import com.keepgoing.keepserver.domain.book.util.GenerateCertCharacter;
import com.keepgoing.keepserver.domain.device.repository.DeviceRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final DeviceRepository deviceRepository;

    @Override
    public BaseResponse bookRegister(Book book, MultipartFile multipartFile){
        String nfcCode = createNfcCode();
        book.setRegistrationDate(new Date());
        book.setNfcCode(nfcCode);
        book.setState("0");
        bookRepository.save(book);
        return new BaseResponse(HttpStatus.OK, "책 생성 성공");
    }


    @Override
    public BaseResponse selectAllBook() {
        return new BaseResponse(HttpStatus.OK, "책 불러오기 성공",(ArrayList<Book>) bookRepository.findAll());
    }



    @Override
    public BaseResponse deleteBook(String nfcCode) {
        if (nfcCode == null || nfcCode.isEmpty()) {
            return new BaseResponse(HttpStatus.FORBIDDEN,"Invalid NFC code");
        }
        Book book = bookRepository.findBookByNfcCode(nfcCode);
        if (book == null) {
            return new BaseResponse(HttpStatus.OK,"책을 찾을 수 없습니다");
        } else {
            bookRepository.delete(bookRepository.findBookByNfcCode(nfcCode));
            return new BaseResponse(HttpStatus.OK,"책 삭제 성공");
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

    @Override
    public BaseResponse editBook(String nfcCode, BookRequestDTO bookRequest)throws IOException{
        Book book = bookRepository.findBookByNfcCode(nfcCode);

        List<BookRequestDTO> list = new ArrayList<>();
        list.add(bookRequest);

        for (int i=0;i< list.size();i++) {
            if (list.get(i).getState() != null) book.setState(list.get(i).getState());
            if (list.get(i).getImageUrl() != null) book.setImage(list.get(i).getImageUrl());
            if (list.get(i).getName() != null) book.setName(list.get(i).getName());
        }
        bookRepository.save(book);
        return new BaseResponse(HttpStatus.OK,"책 정보 수정 성공");
    }


}




