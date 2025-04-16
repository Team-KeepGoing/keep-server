package com.keepgoing.keepserver.global.file.validate;

import java.util.List;

public interface ExcelValidator<T> {
    List<String> validateExcel(T excelDto);
}
