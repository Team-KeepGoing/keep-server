package com.keepgoing.keepserver.global.file.service;

import com.keepgoing.keepserver.global.file.model.ExcelValidationResult;
import com.keepgoing.keepserver.global.file.processor.ExcelProcessor;
import com.keepgoing.keepserver.global.file.strategy.ExcelStrategySelector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelImportService {

    private final ExcelStrategySelector strategySelector;

    public <T> List<T> parseValid(MultipartFile file, Class<T> clazz) {
        ExcelProcessor<T> processor = strategySelector.getProcessorFor(clazz);
        return processor.parseValid(file);
    }

    public <T> List<ExcelValidationResult<T>> validate(MultipartFile file, Class<T> clazz) {
        ExcelProcessor<T> processor = strategySelector.getProcessorFor(clazz);
        return processor.validate(file);
    }
}