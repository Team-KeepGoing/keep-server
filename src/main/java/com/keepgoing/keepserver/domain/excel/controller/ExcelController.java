package com.keepgoing.keepserver.domain.excel.controller;

import com.keepgoing.keepserver.domain.excel.service.ExcelService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;
    @PostMapping("/upload")
    public BaseResponse uploadExcel(@RequestPart("file") MultipartFile file) throws IOException {
        return excelService.uploadExcel(file);
    }
}
