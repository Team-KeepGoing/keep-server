package com.keepgoing.keepserver.domain.file.service.parser;

import com.keepgoing.keepserver.domain.file.service.validate.ExcelValidationResult;
import com.keepgoing.keepserver.domain.file.service.validate.ExcelValidator;
import com.keepgoing.keepserver.domain.file.service.mapper.RowMapper;
import com.keepgoing.keepserver.global.exception.excel.ExcelException;
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
public class ExcelParserImpl<T> implements ExcelParser<T> {

    private final RowMapper<T> rowMapper;
    private final ExcelValidator<T> validator;

    @Override
    public List<ExcelValidationResult<T>> validate(MultipartFile file) {
        List<ExcelValidationResult<T>> results = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    T dto = rowMapper.map(row);
                    List<String> errors = validator.validateExcel(dto);
                    results.add(new ExcelValidationResult<>(dto, i + 1, errors));
                }
            }
        } catch (IOException | InvalidFormatException e) {
            throw ExcelException.excelUnsupportedType();
        }
        return results;
    }

    @Override
    public List<T> parse(MultipartFile file) {
        List<T> result = new ArrayList<>();
        List<ExcelValidationResult<T>> validationResults = validate(file);
        for (ExcelValidationResult<T> r : validationResults) {
            if (r.errors().isEmpty()) result.add(r.dto());
        }
        return result;
    }
}

