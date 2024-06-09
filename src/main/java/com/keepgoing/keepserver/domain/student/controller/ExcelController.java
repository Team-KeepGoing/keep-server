package com.keepgoing.keepserver.domain.student.controller;

import com.keepgoing.keepserver.domain.student.service.ExcelService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;
    @PostMapping("/upload")
    public BaseResponse uploadExcel(@RequestPart(value = "excel") MultipartFile file) throws IOException {
        return excelService.uploadExcel(file);
    }
}
