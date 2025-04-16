package com.keepgoing.keepserver.domain.file.service.generate;

import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ExcelGenerator {
    public static ResponseEntity<Resource> generate(String filename, Workbook workbook) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                                 .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                 .body(resource);
        } catch (IOException e) {
            throw ExcelException.excelFailToGenerate();
        }
    }
}