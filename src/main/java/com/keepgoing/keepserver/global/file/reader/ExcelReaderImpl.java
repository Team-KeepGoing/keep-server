package com.keepgoing.keepserver.global.file.reader;

import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import com.keepgoing.keepserver.global.file.mapper.RowMapper;
import lombok.AllArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ExcelReaderImpl<T> implements ExcelReader<T> {

    private final RowMapper<T> rowMapper;

    @Override
    public List<T> read(MultipartFile file) {
        List<T> result = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    result.add(rowMapper.map(row));
                }
            }
        } catch (IOException | InvalidFormatException e) {
            throw ExcelException.excelUnsupportedType();
        }
        return result;
    }
}
