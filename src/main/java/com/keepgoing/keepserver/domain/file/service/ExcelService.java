package com.keepgoing.keepserver.domain.file.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Consumer;

public interface ExcelService {
    <T> List<T> parseExcelFile(MultipartFile file, Class<T> clazz);
    ResponseEntity<Resource> generateExcel(String fileName, String sheetName, String[] headers, Consumer<Sheet> dataWriter);
}

