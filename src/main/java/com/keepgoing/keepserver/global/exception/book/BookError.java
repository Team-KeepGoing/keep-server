package com.keepgoing.keepserver.global.exception.book;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookError implements ErrorProperty {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    BOOK_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 도서 찾을 수 없습니다."),
    BOOK_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "도서를 대여할 수 없습니다.");
    private final HttpStatus status;
    private final String message;
}