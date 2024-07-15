package com.keepgoing.keepserver.domain.student.service;

import com.keepgoing.keepserver.domain.student.domain.repository.dto.StudentFindDto;
import com.keepgoing.keepserver.domain.student.domain.repository.dto.StudentRequestDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    BaseResponse findByStudentName(StudentFindDto studentFindDto);
    BaseResponse findByStudentNum(StudentFindDto studentFindDto);
    BaseResponse editStudent(StudentRequestDto studentRequestDto,Long id);
    BaseResponse createManyUserByExcel(MultipartFile multipartFile) throws IOException;
    BaseResponse findAll();
    BaseResponse AddStudentImage(List<String> imgUrls);
}
