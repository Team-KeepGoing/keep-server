package com.keepgoing.keepserver.domain.book.service;

import com.keepgoing.keepserver.domain.book.domain.entity.Book;
import com.keepgoing.keepserver.domain.book.domain.repository.BookRepository;
import com.keepgoing.keepserver.domain.book.mapper.BookMapper;
import com.keepgoing.keepserver.domain.book.payload.request.BookDto;
import com.keepgoing.keepserver.domain.book.payload.request.BookRequestDto;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import com.keepgoing.keepserver.domain.user.domain.repository.user.UserRepository;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.book.BookException;
import com.keepgoing.keepserver.global.util.DateRange;
import com.keepgoing.keepserver.global.util.GenerateCertCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final GenerateCertCharacter generateCertCharacter;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BaseResponse bookRegister(BookDto bookDto) {
        String nfcCode = createNfcCode();
        bookRepository.save(bookMapper.dtoToEntity(bookDto, nfcCode));
        return new BaseResponse(HttpStatus.OK, "책 생성 성공");
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse selectAllBook() {
        return new BaseResponse(HttpStatus.OK, "책 불러오기 성공", bookRepository.findAll().stream().map(bookMapper::entityToDto).toList());
    }

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public BaseResponse selectMyBook(Authentication auth) {
        User user = getUserByAuthentication(auth);
        List<Book> books = bookRepository.findByBorrower(user);
        return new BaseResponse(HttpStatus.OK, "책 가져오기 성공", books.stream().map(bookMapper::entityToDto).toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse alertMyBook(Authentication auth, String dateString) {
        User user = getUserByAuthentication(auth);
        DateRange dateRange = DateRange.fromDateString(dateString, "yyyyMMdd");

        List<Book> books = bookRepository.findByBorrowerAndRentDateBetween(user, dateRange.getSt(), dateRange.getEnd());
        return new BaseResponse(HttpStatus.OK, "책 가져오기 성공", books.stream().map(bookMapper::entityToDto).toList());
    }

    @Override
    @Transactional(readOnly = true)
    public String createNfcCode() {
        String nfcCode;
        do {
            nfcCode = generateCertCharacter.executeGenerate();
        } while (bookRepository.findBookByNfcCode(nfcCode) != null);
        return nfcCode;
    }

    @Override
    @Transactional
    public BaseResponse editBook(String nfcCode, BookRequestDto bookRequest) {
        Book book = bookRepository.findBookByNfcCode(nfcCode);

        if (bookRequest.state() != null) book.setState(bookRequest.state());
        if (bookRequest.imageUrl() != null) book.setImageUrl(bookRequest.imageUrl());
        if (bookRequest.name() != null) book.setBookName(bookRequest.name());
        bookRepository.save(book);
        return new BaseResponse(HttpStatus.OK, "책 정보 수정 성공");
    }

    private User getUserByAuthentication(Authentication auth) {
        if (auth == null) {
            throw BookException.userNotFound();
        }

        return userRepository.findByEmail(auth.getName())
                             .orElseThrow(BookException::userNotFound);
    }
}