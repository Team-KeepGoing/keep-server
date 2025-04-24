package com.keepgoing.keepserver.domain.teacher.mapper;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import com.keepgoing.keepserver.global.file.mapper.RowMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.TimeZone;

@Component
public class ItemDtoRowMapperImpl implements RowMapper<ItemExcelDto> {
    @Override
    public ItemExcelDto map(Row row) {
        try {
            return new ItemExcelDto(
                    row.getCell(0).getStringCellValue(),
                    getLocalDateOrNull(row.getCell(1)),
                    (long) row.getCell(2).getNumericCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(4).getStringCellValue(),
                    getItemStatusOrElse(row.getCell(5)),
                    getStringOrNull(row.getCell(6)),
                    getStringOrNull(row.getCell(7)),
                    getLocalDateOrNull(row.getCell(8)),
                    getLocalDateOrNull(row.getCell(9)),
                    getLongOrNull(row.getCell(10))
            );
        } catch (IllegalArgumentException e) {
            throw ExcelException.excelCannotParse();
        }
    }

    private ItemStatus getItemStatusOrElse(Cell cell) {
        try {
            String raw = cell.getStringCellValue().trim();
            return ItemStatus.valueOf(raw);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw ExcelException.excelStatusValueError();
        }
    }

    private String getStringOrNull(Cell cell) {
        return cell == null ? null : cell.getStringCellValue();
    }

    private LocalDate getLocalDateOrNull(Cell cell) {
        if (cell == null) return null;
        try {
            return LocalDate.ofInstant(cell.getDateCellValue().toInstant(), TimeZone.getDefault().toZoneId());
        } catch (Exception e) {
            throw ExcelException.excelDateFormatError();
        }
    }

    private Long getLongOrNull(Cell cell) {
        return cell == null ? null : (long) cell.getNumericCellValue();
    }
}
