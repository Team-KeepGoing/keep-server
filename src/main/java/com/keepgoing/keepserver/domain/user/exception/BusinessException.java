package com.keepgoing.keepserver.domain.user.exception;

import com.keepgoing.keepserver.domain.user.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    private final ErrorProperty error;
}