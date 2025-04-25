package com.keepgoing.keepserver.global.file.generate;

import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ExcelGenerator {
    public static Resource generate(Workbook workbook) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);

            return new ByteArrayResource(out.toByteArray());
        } catch (IOException e) {
            throw ExcelException.excelFailToGenerate();
        }
    }
}