package com.keepgoing.keepserver.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorProperty{
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생하였습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    EMAIL_BAD_REQUEST(HttpStatus.BAD_REQUEST, "사용중인 이메일 입니다."),
    FILE_ERROR(HttpStatus.BAD_REQUEST, "파일 업로드 중에 오류가 발생했습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다.");

    private final HttpStatus status;
    private final String message;
}