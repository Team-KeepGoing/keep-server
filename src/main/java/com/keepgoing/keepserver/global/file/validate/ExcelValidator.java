package com.keepgoing.keepserver.global.file.validate;

import java.util.List;

public interface ExcelValidator<T> {
    List<ExcelValidationResult<T>> validate(List<T> dtos);
}
