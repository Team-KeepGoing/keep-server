package com.keepgoing.keepserver.global.config;

import com.keepgoing.keepserver.global.file.generate.GenerateExcelTemplate;
import com.keepgoing.keepserver.global.file.generate.GenerateExcelTemplateImpl;
import com.keepgoing.keepserver.global.file.mapper.RowMapper;
import com.keepgoing.keepserver.global.file.processor.ExcelProcessor;
import com.keepgoing.keepserver.global.file.reader.ExcelReader;
import com.keepgoing.keepserver.global.file.reader.ExcelReaderImpl;
import com.keepgoing.keepserver.global.file.validator.ExcelValidator;
import com.keepgoing.keepserver.domain.teacher.mapper.ItemDtoRowMapperImpl;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import com.keepgoing.keepserver.global.file.validator.ExcelValidatorImpl;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelConfig {

    @Bean
    public RowMapper<ItemExcelDto> itemExcelDtoRowMapper() {
        return new ItemDtoRowMapperImpl();
    }

    @Bean
    public ExcelValidator<ItemExcelDto> itemExcelDtoExcelValidator(Validator validator) {
        return new ExcelValidatorImpl<>(validator);
    }

    @Bean
    public GenerateExcelTemplate generateExcelTemplate() {
        return new GenerateExcelTemplateImpl();
    }

    @Bean
    public ExcelProcessor<ItemExcelDto> itemExcelProcessor(RowMapper<ItemExcelDto> rowMapper, ExcelValidator<ItemExcelDto> validator) {
        ExcelReader<ItemExcelDto> reader = new ExcelReaderImpl<>(rowMapper);
        return new ExcelProcessor<>(reader, validator);
    }
}
