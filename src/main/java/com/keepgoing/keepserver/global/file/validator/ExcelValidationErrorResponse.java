package com.keepgoing.keepserver.global.file.validator;

import java.util.List;

public record ExcelValidationErrorResponse(int rowNum, List<String> errors) {}