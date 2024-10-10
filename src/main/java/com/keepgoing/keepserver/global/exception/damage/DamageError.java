package com.keepgoing.keepserver.global.exception.damage;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DamageError implements ErrorProperty {
    INVALID_DAMAGE(HttpStatus.NOT_FOUND, "파손 정보가 없습니다."),
    DUPLICATE(HttpStatus.NOT_FOUND, "이미 같은 문제로 신고된 기기입니다.");
    private final HttpStatus status;
    private final String message;
}
