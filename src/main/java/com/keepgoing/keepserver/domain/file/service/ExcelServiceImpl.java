package com.keepgoing.keepserver.domain.file.service;

import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Consumer;

@Service
public class ExcelServiceImpl implements ExcelService {

    private final Validator validator;
    private FormulaEvaluator evaluator;

    @Override
    public <T> List<T> parseExcelFile(MultipartFile file, Class<T> tClass) {
        Workbook workbook;
        try {
            workbook = getWorkbook(file);
            evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        } catch (IOException e) {
            throw ExcelException.EXCEL_NOT_AVAILABLE;
        }
        Sheet sheet = workbook.getSheetAt(0);
        List<T> dataList = new ArrayList<>();

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Constructor<?> constructor = tClass.getConstructors()[0];

            try {
                var param = constructor.getParameters();
                List<Object> args = new ArrayList<>(param.length);
                for (int j = 0; j < param.length; j++) {
                    args.add(guessType(param[j].getType(), row, j));
                }
                @SuppressWarnings("unchecked") T t = (T) constructor.newInstance(args.toArray());
                Set<ConstraintViolation<T>> violations = validator.validate(t);
                if (!violations.isEmpty()) {
                    throw ExcelException.EXCEL_VALIDATION_ERROR;
                }
                dataList.add(t);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw ExcelException.UNABLE_TO_INSTANTIATE;
            }
        }
        return dataList;
    }

    @Override
    public ResponseEntity<Resource> generateExcel(String fileName, String sheetName, String[] headers, Consumer<Sheet> dataWriter) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet(sheetName);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            dataWriter.accept(sheet);

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);

            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                                 .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                                 .body(resource);

        } catch (IOException e) {
            throw ExcelException.EXCEL_NOT_AVAILABLE;
        }
    }

    private Object guessType(Class<?> aClass, Row row, int i) {
        Cell cell = row.getCell(i, Row.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            if (aClass.isPrimitive()) {
                if (aClass == int.class)
                    return 0;
                if (aClass == long.class)
                    return 0L;
                if (aClass == double.class)
                    return 0.0;
                if (aClass == float.class)
                    return 0.0f;
                if (aClass == boolean.class)
                    return false;
            }
            return null;
        }

        if (aClass == String.class) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return String.valueOf(getNumericCell(cell));
            } else {
                return "";
            }
        } else if (aClass == Integer.class || aClass == int.class) {
            return getNumericCell(cell).intValue();
        } else if (aClass == Long.class || aClass == long.class) {
            if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                try {
                    CellValue cellValue = evaluator.evaluate(cell);
                    return (long) cellValue.getNumberValue();
                } catch (Exception e) {
                    return (long) cell.getNumericCellValue();
                }
            }
            return getNumericCell(cell).longValue();
        } else if (aClass == Double.class || aClass == double.class) {
            return getNumericCell(cell).doubleValue();
        } else if (aClass == Float.class || aClass == float.class) {
            return getNumericCell(cell).floatValue();
        } else if (aClass == LocalDate.class) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return LocalDate.ofInstant(cell.getDateCellValue().toInstant(), TimeZone.getDefault().toZoneId());
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                return LocalDate.parse(cell.getStringCellValue());
            } else {
                throw ExcelException.EXCEL_DATE_FORMAT_ERROR;
            }
        } else if (aClass.isEnum()) {
            @SuppressWarnings("unchecked")
            Enum<?> result = Enum.valueOf((Class<Enum>) aClass, cell.getStringCellValue());
            return result;
        }

        throw ExcelException.EXCEL_UNSUPPORTED_TYPE;
    }

    private Number getNumericCell(Cell cell) {
        double value;
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            String stringValue = cell.getStringCellValue().replace(",", "");
            if (stringValue.isEmpty()) {
                return 0.0;
            }
            value = Double.parseDouble(stringValue);
        } else {
            value = cell.getNumericCellValue();
        }
        return value;
    }

    private Workbook getWorkbook(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension == null || (!extension.equals("xlsx") && !extension.equals("xls"))) {
            throw ExcelException.EXCEL_NOT_AVAILABLE;
        }

        if (extension.equals("xlsx")) {
            return new XSSFWorkbook(file.getInputStream());
        } else {
            return new HSSFWorkbook(file.getInputStream());
        }
    }
    private ExcelServiceImpl(Validator validator) {
        this.validator = validator;
    }
}