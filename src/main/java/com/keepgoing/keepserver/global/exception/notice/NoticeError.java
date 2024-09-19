package com.keepgoing.keepserver.global.exception.notice;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum NoticeError implements ErrorProperty {
    USER_CANNOT_DELETE(HttpStatus.BAD_REQUEST, "본인 글이 아닙니다.");

    private final HttpStatus status;
    private final String message;
}
