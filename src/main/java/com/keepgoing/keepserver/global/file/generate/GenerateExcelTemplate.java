package com.keepgoing.keepserver.global.file.generate;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface GenerateExcelTemplate {
    <T> Workbook generateTemplate(List<T> excelExample);
}
