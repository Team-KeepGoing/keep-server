package com.keepgoing.keepserver.domain.student.controller;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentFindDto;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentRequestDto;
import com.keepgoing.keepserver.domain.student.service.StudentService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/find")
    public BaseResponse findStudent(@RequestBody StudentFindDto studentFindDto) {
        return studentService.findByStudent(studentFindDto);
    }

    @PatchMapping("/edit")
    public BaseResponse editStudent(@RequestBody StudentFindDto studentFindDto,
                                                    @RequestBody StudentRequestDto studentRequestDto) {
        return studentService.editStudent(studentFindDto,studentRequestDto);
    }

    @PostMapping("/upload")
    public BaseResponse uploadExcel(@RequestPart(value = "excel") MultipartFile file) throws IOException {
        return studentService.uploadExcel(file);
    }

}
