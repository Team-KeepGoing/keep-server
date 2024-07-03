package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.payload.request.BookDto;
import com.keepgoing.keepserver.domain.book.payload.request.BookRequestDto;
import com.keepgoing.keepserver.domain.book.mapper.BookMapper;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.domain.user.repository.user.UserRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.book.BookException;
import com.keepgoing.keepserver.global.util.GenerateCertCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final GenerateCertCharacter generateCertCharacter;
    private final BookMapper bookMapper;

    @Override
    public BaseResponse bookRegister(BookDto bookDto) {
        String nfcCode = createNfcCode();
        bookRepository.save(bookMapper.dtoToEntity(bookDto, nfcCode));
        return new BaseResponse(HttpStatus.OK, "책 생성 성공");
    }

    @Override
    public BaseResponse selectAllBook() {
        return new BaseResponse(HttpStatus.OK, "책 불러오기 성공", bookRepository.findAll());
    }

    @Override
    public BaseResponse deleteBook(String nfcCode, Authentication auth) {
        if (nfcCode == null || nfcCode.isEmpty()) {
            return new BaseResponse(HttpStatus.NOT_FOUND, "Invalid NFC code");
        }
        Book book = bookRepository.findBookByNfcCode(nfcCode);
        if (book == null) {
            return new BaseResponse(HttpStatus.NOT_FOUND, "책을 찾을 수 없습니다");
        }

        bookRepository.delete(bookRepository.findBookByNfcCode(nfcCode));
        return new BaseResponse(HttpStatus.OK, "책 삭제 성공");
    }

    @Override
    public BaseResponse selectMyBook(Authentication auth) {
        if (auth == null) // requires auth
            throw BookException.userNotFound();

        Optional<User> userOptional = userRepository.findByEmail(auth.getName());
        if (userOptional.isPresent()) {
            List<Book> books = bookRepository.findByBorrower(userOptional.get());
            return new BaseResponse(HttpStatus.OK, "책 가져오기 성공", books);
        } else {
            return new BaseResponse(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
        }

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
    public BaseResponse editBook(String nfcCode, BookRequestDto bookRequest) {
        Book book = bookRepository.findBookByNfcCode(nfcCode);

        if (bookRequest.state() != null) book.setState(bookRequest.state());
        if (bookRequest.imageUrl() != null) book.setImageUrl(bookRequest.imageUrl());
        if (bookRequest.name() != null) book.setBookName(bookRequest.name());
        bookRepository.save(book);
        return new BaseResponse(HttpStatus.OK, "책 정보 수정 성공");
    }

    public List<Book> findBooksBorrowedByUser(User user) {
        return bookRepository.findByBorrower(user);
    }
}