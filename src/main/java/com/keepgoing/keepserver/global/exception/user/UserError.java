package com.keepgoing.keepserver.global.exception.user;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserError implements ErrorProperty {
    USER_NOT_TEACHER(HttpStatus.BAD_REQUEST, "선생님이 아닙니다.");
    private final HttpStatus status;
    private final String message;
}