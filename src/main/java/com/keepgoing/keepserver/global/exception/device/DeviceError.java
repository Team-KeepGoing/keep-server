package com.keepgoing.keepserver.global.exception.device;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DeviceError implements ErrorProperty {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    DEVICE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 기기를 찾을 수 없습니다."),
    DEVICE_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "기기를 대여할 수 없습니다."),
    INVALID_BORROWER(HttpStatus.BAD_REQUEST, "반납 요청자가 기기의 대여자가 아닙니다.");
    private final HttpStatus status;
    private final String message;
}
