package com.keepgoing.keepserver.global.file.strategy;

import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import com.keepgoing.keepserver.global.file.mapper.RowMapper;
import com.keepgoing.keepserver.global.file.processor.ExcelProcessor;
import com.keepgoing.keepserver.global.file.reader.ExcelReader;
import com.keepgoing.keepserver.global.file.reader.ExcelReaderImpl;
import com.keepgoing.keepserver.global.file.validator.ExcelValidator;
import org.springframework.stereotype.Component;

@Component
public class ItemExcelStrategy implements ExcelStrategy<ItemExcelDto> {

    private final ExcelProcessor<ItemExcelDto> excelProcessor;

    public ItemExcelStrategy(RowMapper<ItemExcelDto> rowMapper, ExcelValidator<ItemExcelDto> validator) {
        ExcelReader<ItemExcelDto> reader = new ExcelReaderImpl<>(rowMapper);
        this.excelProcessor = new ExcelProcessor<>(reader, validator);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ItemExcelDto.class);
    }

    @Override
    public ExcelProcessor<ItemExcelDto> getProcessor() {
        return excelProcessor;
    }
}
