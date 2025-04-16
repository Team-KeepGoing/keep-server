package com.keepgoing.keepserver.domain.file.service.validate;

import java.util.List;

public interface ExcelValidator<T> {
    List<String> validateExcel(T excelDto);
}
