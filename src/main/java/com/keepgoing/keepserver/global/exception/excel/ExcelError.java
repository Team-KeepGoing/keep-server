package com.keepgoing.keepserver.global.exception.excel;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExcelError implements ErrorProperty {
    EXCEL_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "엑셀 파일이 올바르지 않습니다."),
    EXCEL_UNSUPPORTED_TYPE(HttpStatus.INTERNAL_SERVER_ERROR, "지원되지 않는 타입이 포함되어 있습니다."),
    UNABLE_TO_INSTANTIATE(HttpStatus.INTERNAL_SERVER_ERROR, "Dto를 생성할 수 없습니다."),
    EXCEL_DATE_FORMAT_ERROR(HttpStatus.BAD_REQUEST, "엑셀 파일 속 날짜 형식이 잘못되었습니다. Ex) YYYY.MM.DD "),
    EXCEL_VALUE_NULL(HttpStatus.BAD_REQUEST, "엑셀 파일에 null 값이 포함되어있습니다.");

    private final HttpStatus status;
    private final String message;
}
