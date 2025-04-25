package com.keepgoing.keepserver.global.file.strategy;

import com.keepgoing.keepserver.global.exception.excel.ExcelException;
import com.keepgoing.keepserver.global.file.processor.ExcelProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExcelStrategySelector {

    private final List<ExcelStrategy<?>> strategies;

    public ExcelStrategySelector(List<ExcelStrategy<?>> strategies) {
        this.strategies = strategies;
    }

    @SuppressWarnings("unchecked")
    public <T> ExcelProcessor<T> getProcessorFor(Class<T> dtoClass) {
        return strategies.stream()
                         .filter(s -> s.supports(dtoClass))
                         .findFirst()
                         .map(s -> (ExcelProcessor<T>) s.getProcessor())
                         .orElseThrow(ExcelException::excelCannotGetStrategy);
    }
}