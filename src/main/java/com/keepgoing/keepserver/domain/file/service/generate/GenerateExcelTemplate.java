package com.keepgoing.keepserver.domain.file.service.generate;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface GenerateExcelTemplate {
    <T> Workbook generateTemplate(List<T> excelExample);
}
