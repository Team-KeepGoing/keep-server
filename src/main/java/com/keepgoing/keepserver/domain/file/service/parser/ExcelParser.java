package com.keepgoing.keepserver.domain.file.service.parser;

import com.keepgoing.keepserver.domain.file.service.validate.ExcelValidationResult;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ExcelParser<T> {
    List<T> parse(MultipartFile file);
    List<ExcelValidationResult<T>> validate(MultipartFile file);
}
