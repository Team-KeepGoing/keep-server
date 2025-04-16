package com.keepgoing.keepserver.domain.file.service.validate;

import java.util.List;

public record ExcelValidationResult<T>(T dto, int rowNum, List<String> errors) {}