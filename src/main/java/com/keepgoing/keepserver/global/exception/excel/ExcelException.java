package com.keepgoing.keepserver.global.exception.excel;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class ExcelException extends BusinessException {
    public static final ExcelException EXCEL_NOT_AVAILABLE = new ExcelException(ExcelError.EXCEL_NOT_AVAILABLE);
    public static final ExcelException EXCEL_UNSUPPORTED_TYPE = new ExcelException(ExcelError.EXCEL_UNSUPPORTED_TYPE);
    public static final ExcelException UNABLE_TO_INSTANTIATE = new ExcelException(ExcelError.UNABLE_TO_INSTANTIATE);
    public static final ExcelException EXCEL_DATE_FORMAT_ERROR = new ExcelException(ExcelError.EXCEL_DATE_FORMAT_ERROR);
    public static final ExcelException EXCEL_VALIDATION_ERROR = new ExcelException(ExcelError.EXCEL_VALIDATION_ERROR);

    public ExcelException(ExcelError error) {
        super(error);
    }
}
