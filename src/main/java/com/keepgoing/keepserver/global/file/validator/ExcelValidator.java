package com.keepgoing.keepserver.global.file.validator;

import com.keepgoing.keepserver.global.file.model.ExcelValidationResult;

import java.util.List;

public interface ExcelValidator<T> {
    List<ExcelValidationResult<T>> validate(List<T> dtos);
}
