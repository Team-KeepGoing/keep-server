package com.keepgoing.keepserver.global.exception.excel;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class ExcelException extends BusinessException {
    public static final ExcelException EXCEL_NOT_AVAILABLE = new ExcelException(ExcelError.EXCEL_NOT_AVAILABLE);
    public static final ExcelException EXCEL_UNSUPPORTED_TYPE = new ExcelException(ExcelError.EXCEL_UNSUPPORTED_TYPE);
    public static final ExcelException EXCEL_DATE_FORMAT_ERROR = new ExcelException(ExcelError.EXCEL_DATE_FORMAT_ERROR);
    public static final ExcelException EXCEL_STATUS_VALUE_ERROR = new ExcelException(ExcelError.EXCEL_STATUS_VALUE_ERROR);
    public static final ExcelException EXCEL_CANNOT_PARSE = new ExcelException(ExcelError.EXCEL_CANNOT_PARSE);
    public static final ExcelException EXCEL_FAIL_TO_GENERATE = new ExcelException(ExcelError.EXCEL_FAIL_TO_GENERATE);
    public static final ExcelException EXCEL_VALIDATION_ERROR = new ExcelException(ExcelError.EXCEL_VALIDATION_ERROR);

    public ExcelException(ExcelError error) {
        super(error);
    }

    public static ExcelException excelNotAvailable() {
        return EXCEL_NOT_AVAILABLE;
    }

    public static ExcelException excelUnsupportedType() {
        return EXCEL_UNSUPPORTED_TYPE;
    }

    public static ExcelException excelDateFormatError() {
        return EXCEL_DATE_FORMAT_ERROR;
    }

    public static ExcelException excelStatusValueError() {
        return EXCEL_STATUS_VALUE_ERROR;
    }

    public static ExcelException excelCannotParse() {
        return EXCEL_CANNOT_PARSE;
    }

    public static ExcelException excelValidationError(){
        return EXCEL_VALIDATION_ERROR;
    }

    public static ExcelException excelFailToGenerate(){
        return EXCEL_FAIL_TO_GENERATE;
    }
}
