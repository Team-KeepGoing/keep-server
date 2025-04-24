package com.keepgoing.keepserver.global.file.generate;

import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class GenerateExcelTemplateImpl implements GenerateExcelTemplate {
    @Override
    public <T> Workbook generateTemplate(List<T> sampleData) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("item-list");

        if (!sampleData.isEmpty()) {
            Row headerRow = sheet.createRow(0);
            Field[] fields = sampleData.get(0).getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                headerRow.createCell(i).setCellValue(fields[i].getName());
            }

            for (int rowIndex = 0; rowIndex < sampleData.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                T data = sampleData.get(rowIndex);

                for (int cellIndex = 0; cellIndex < fields.length; cellIndex++) {
                    fields[cellIndex].setAccessible(true);
                    createCell(cellIndex, data, fields, row);
                }
            }
        }
        return workbook;
    }

    private <T> void createCell(int cellIndex, T data, Field[] fields, Row row) {
        try {
            Object value = fields[cellIndex].get(data);
            row.createCell(cellIndex).setCellValue(value != null ? value.toString() : "");
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw ExcelException.excelUnsupportedType();
        }
    }
}
