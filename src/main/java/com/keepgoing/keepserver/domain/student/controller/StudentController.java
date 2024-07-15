package com.keepgoing.keepserver.domain.student.controller;

import com.keepgoing.keepserver.domain.student.domain.repository.dto.StudentFindDto;
import com.keepgoing.keepserver.domain.student.domain.repository.dto.StudentRequestDto;
import com.keepgoing.keepserver.domain.student.service.StudentService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "이름을 통한 학생검색", description = "내용에 이름만 넣으면 학생정보가 나옵니다")
    @PostMapping("/find-name")
    public BaseResponse findStudentName(@RequestBody StudentFindDto studentFindDto) {
        return studentService.findByStudentName(studentFindDto);
    }

    @Operation(summary = "학반번호를 통한 학생검색", description = "내용에 학반번호(2304)만 넣으면 학생정보가 나옵니다")
    @PostMapping("/find-studentId")
    public BaseResponse findStudentNum(@RequestBody StudentFindDto studentFindDto) {
        return studentService.findByStudentNum(studentFindDto);
    }

    @Operation(summary = "전체 학생 불러오기", description = "모든 학생을 불러옵니다.")
    @GetMapping("/all")
    public BaseResponse findAll() {
        return studentService.findAll();
    }

    @Operation(summary = "학생 정보 수정하기", description = "id를 통해 학생 정보를 수정합니다. 파라미터는 전체 코드가 아닌, 수정할 내용만 넘기셔도 됩니다.")
    @PatchMapping("/edit/{id}")
    public BaseResponse editStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id) {
        return studentService.editStudent(studentRequestDto, id);
    }

    @Operation(summary = "학생 등록하기", description = "형식에 맞는 엑셀 파일 업로드 시 업로딩됩니다")
    @PostMapping("/upload")
    public BaseResponse createManyUserByExcel(@RequestPart(value = "excel") MultipartFile file) throws IOException {
        return studentService.createManyUserByExcel(file);
    }

}
