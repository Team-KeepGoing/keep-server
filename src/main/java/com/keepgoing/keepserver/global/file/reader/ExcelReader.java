package com.keepgoing.keepserver.global.file.reader;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelReader<T> {
    List<T> read(MultipartFile file);
}