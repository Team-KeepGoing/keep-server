package com.keepgoing.keepserver.global.file.strategy;

import com.keepgoing.keepserver.global.file.processor.ExcelProcessor;

public interface ExcelStrategy<T> {
    boolean supports(Class<?> clazz);
    ExcelProcessor<T> getProcessor();
}

