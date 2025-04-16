package com.keepgoing.keepserver.global.file.validate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class ExcelValidatorImpl<T> implements ExcelValidator<T> {

    private final Validator validator;

    @Override
    public List<ExcelValidationResult<T>> validate(List<T> dtos) {
        List<ExcelValidationResult<T>> results = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            T dto = dtos.get(i);
            Set<ConstraintViolation<T>> violations = validator.validate(dto);
            List<String> errors = violations.stream()
                                            .map(ConstraintViolation::getMessage)
                                            .toList();
            results.add(new ExcelValidationResult<>(dto, i + 2, errors));
        }
        return results;
    }
}
