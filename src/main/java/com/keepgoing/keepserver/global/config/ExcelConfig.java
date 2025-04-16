package com.keepgoing.keepserver.global.config;

import com.keepgoing.keepserver.global.file.generate.GenerateExcelTemplate;
import com.keepgoing.keepserver.global.file.generate.GenerateExcelTemplateImpl;
import com.keepgoing.keepserver.global.file.mapper.RowMapper;
import com.keepgoing.keepserver.global.file.parser.ExcelParser;
import com.keepgoing.keepserver.global.file.parser.ExcelParserImpl;
import com.keepgoing.keepserver.global.file.validate.ExcelValidator;
import com.keepgoing.keepserver.domain.teacher.service.ItemDtoRowMapperImpl;
import com.keepgoing.keepserver.domain.teacher.service.ItemExcelDtoValidator;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
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
        return new ItemExcelDtoValidator(validator);
    }

    @Bean
    public GenerateExcelTemplate generateExcelTemplate() {
        return new GenerateExcelTemplateImpl();
    }

    @Bean
    public ExcelParser<ItemExcelDto> itemExcelParser(RowMapper<ItemExcelDto> rowMapper, ExcelValidator<ItemExcelDto> validator) {
        return new ExcelParserImpl<>(rowMapper, validator);
    }
}
