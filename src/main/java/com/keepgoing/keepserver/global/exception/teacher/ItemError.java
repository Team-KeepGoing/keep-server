package com.keepgoing.keepserver.global.exception.teacher;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ItemError implements ErrorProperty {
    ITEM_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다."),
    ITEM_SERIAL_NUM_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 식별번호 값 입니다.");

    private final HttpStatus status;
    private final String message;
}
