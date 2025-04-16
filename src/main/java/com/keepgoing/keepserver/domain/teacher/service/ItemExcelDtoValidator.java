package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.global.file.validate.ExcelValidator;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemExcelDtoValidator implements ExcelValidator<ItemExcelDto> {
    private final Validator validator;

    public ItemExcelDtoValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<String> validateExcel(ItemExcelDto dto) {
        Set<ConstraintViolation<ItemExcelDto>> violations = validator.validate(dto);
        return violations.stream()
                         .map(v -> v.getPropertyPath() + " - " + v.getMessage())
                         .collect(Collectors.toList());
    }
}