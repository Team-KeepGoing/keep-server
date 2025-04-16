package com.keepgoing.keepserver.global.file.processor;

import com.keepgoing.keepserver.global.file.reader.ExcelReader;
import com.keepgoing.keepserver.global.file.model.ExcelValidationResult;
import com.keepgoing.keepserver.global.file.validator.ExcelValidator;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
public class ExcelProcessor<T> {

    private final ExcelReader<T> reader;
    private final ExcelValidator<T> validator;

    public List<ExcelValidationResult<T>> validate(MultipartFile file) {
        List<T> dtos = reader.read(file);
        return validator.validate(dtos);
    }

    public List<T> parseValid(MultipartFile file) {
        List<ExcelValidationResult<T>> validated = validate(file);
        return validated.stream()
                        .filter(r -> r.errors().isEmpty())
                        .map(ExcelValidationResult::dto)
                        .toList();
    }
}