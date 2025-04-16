package com.keepgoing.keepserver.domain.file.service.generate;

import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class ExcelGenerator {
    public static ResponseEntity<Resource> generateExcel(
            String filename,
            String sheetName,
            List<String> headers,
            Consumer<Sheet> dataPopular
    ) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                headerRow.createCell(i).setCellValue(headers.get(i));
            }

            dataPopular.accept(sheet);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());
            return ResponseEntity.ok()
                                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                                 .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                 .body(resource);
        } catch (IOException e) {
            throw ExcelException.excelNotCreate();
        }
    }
}
