package com.keepgoing.keepserver.domain.student.service;

import com.keepgoing.keepserver.domain.student.repository.dto.StudentFindDto;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentRequestDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface StudentService{
    BaseResponse findByStudentsName(StudentFindDto studentFindDto);
    BaseResponse editStudent(StudentRequestDto studentRequestDto);
    BaseResponse uploadExcel(MultipartFile multipartFile) throws IOException;
    BaseResponse findAll();
}
