package com.keepgoing.keepserver.global.file.parser;

import com.keepgoing.keepserver.global.file.validate.ExcelValidationResult;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ExcelParser<T> {
    List<T> parse(MultipartFile file);
    List<ExcelValidationResult<T>> validate(MultipartFile file);
}
