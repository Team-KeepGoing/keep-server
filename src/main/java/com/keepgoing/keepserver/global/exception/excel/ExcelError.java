package com.keepgoing.keepserver.global.exception.excel;

import com.keepgoing.keepserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExcelError implements ErrorProperty {
    EXCEL_UNSUPPORTED_TYPE(HttpStatus.INTERNAL_SERVER_ERROR, "지원되지 않는 타입이 포함되어 있습니다."),
    EXCEL_DATE_FORMAT_ERROR(HttpStatus.BAD_REQUEST, "엑셀 파일 속 날짜 형식 또는 값이 잘못되었습니다. Ex) YYYY.MM.DD "),
    EXCEL_STATUS_VALUE_ERROR(HttpStatus.BAD_REQUEST, "엑셀 파일 속 상태 값이 잘못되었습니다. AVAILABLE / UNAVAILABLE / IN_USE 만 입력해주세요. "),
    EXCEL_CANNOT_PARSE(HttpStatus.BAD_REQUEST, "엑셀 파일 파싱에 실패하였습니다."),
    EXCEL_FAIL_TO_GENERATE(HttpStatus.INTERNAL_SERVER_ERROR, "엑셀 파일 생성에 실패하였습니다."),
    EXCEL_CANNOT_GET_STRATEGY(HttpStatus.BAD_REQUEST, "존재하지 않는 엑셀 strategy값입니다.");

    private final HttpStatus status;
    private final String message;
}
