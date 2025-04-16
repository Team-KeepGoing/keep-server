package com.keepgoing.keepserver.domain.teacher.payload;

import java.util.List;

public record ExcelValidationErrorResponse(int rowNum, List<String> errors) {}