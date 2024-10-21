package com.keepgoing.keepserver.global.exception.user;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserError implements ErrorProperty {
    USER_NOT_TEACHER(HttpStatus.BAD_REQUEST, "선생님이 아닙니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 학생을 찾을 수 없습니다"),
    TEACHER_ACCOUNT_NOT_APPROVED(HttpStatus.UNAUTHORIZED, "교사 계정이 승인되지 않았습니다.");

    private final HttpStatus status;
    private final String message;
}
