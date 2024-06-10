package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.book.entity.dto.BookRequestDTO;
import com.keepgoing.keepserver.global.common.S3.S3Uploader;
import com.keepgoing.keepserver.global.util.GenerateCertCharacter;
import com.keepgoing.keepserver.domain.user.repository.user.UserRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;
    private final GenerateCertCharacter generateCertCharacter;

    @Override
    public BaseResponse bookRegister(Book book, MultipartFile multipartFile) {
        try {
            book.setImageUrl(s3Uploader.upload(multipartFile, "picture"));
        } catch (IOException e) {
            return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "사진 업로드중 오류.");
        }
        String nfcCode = createNfcCode();
        book.setRegistrationDate(new Date());
        book.setNfcCode(nfcCode);
        book.setState(BookState.AVAILABLE);


        bookRepository.save(book);
        return new BaseResponse(HttpStatus.OK, "책 생성 성공");
    }

    @Override
    public BaseResponse selectAllBook() {
        return new BaseResponse(HttpStatus.OK, "책 불러오기 성공", bookRepository.findAll());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseResponse deleteBook(String nfcCode, Authentication auth) {
        if (nfcCode == null || nfcCode.isEmpty()) {
            return new BaseResponse(HttpStatus.NOT_FOUND, "Invalid NFC code");
        }
        Book book = bookRepository.findBookByNfcCode(nfcCode);
        if (book == null) {
            return new BaseResponse(HttpStatus.NOT_FOUND, "책을 찾을 수 없습니다");
        } else {
            bookRepository.delete(bookRepository.findBookByNfcCode(nfcCode));
            return new BaseResponse(HttpStatus.OK, "책 삭제 성공");
        }
    }

    @Override
    public BaseResponse selectMyBook(Authentication auth) {
        //noinspection OptionalGetWithoutIsPresent
        String user = userRepository.findByEmail(auth.getName()).get().getEmail();
        List<Book> books = bookRepository.findByBookNameContaining(user);

        return new BaseResponse(HttpStatus.OK, "책 가져오기 성공", books.toString());
    }

    @Override
    public String createNfcCode() {
        String newNfcCode;
        do {
            newNfcCode = generateCertCharacter.executeGenerate();
        } while (bookRepository.findBookByNfcCode(newNfcCode) != null);
        return newNfcCode;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseResponse editBook(String nfcCode, BookRequestDTO bookRequest) {
        Book book = bookRepository.findBookByNfcCode(nfcCode);

        if (bookRequest.getState() != null) book.setState(bookRequest.getState());
        if (bookRequest.getImageUrl() != null) book.setImageUrl(bookRequest.getImageUrl());
        if (bookRequest.getName() != null) book.setBookName(bookRequest.getName());
        bookRepository.save(book);
        return new BaseResponse(HttpStatus.OK, "책 정보 수정 성공");
    }
}




