package com.keepgoing.keepserver.global.file.model;

import java.util.List;

public record ExcelValidationResult<T>(T dto, int rowNum, List<String> errors) {}