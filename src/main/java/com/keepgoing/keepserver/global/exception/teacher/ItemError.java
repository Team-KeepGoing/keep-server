package com.keepgoing.keepserver.global.exception.teacher;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ItemError implements ErrorProperty {
    ITEM_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "Item not found.");

    private final HttpStatus status;
    private final String message;
}
