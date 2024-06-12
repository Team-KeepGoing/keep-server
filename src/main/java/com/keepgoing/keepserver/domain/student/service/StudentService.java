package com.keepgoing.keepserver.domain.student.service;

import com.keepgoing.keepserver.domain.student.repository.dto.StudentFindDto;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentRequestDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentService {
    @Transactional(readOnly = true)
    BaseResponse findByStudentName(StudentFindDto studentFindDto);
    @Transactional(readOnly = true)
    BaseResponse findByStudentNum(StudentFindDto studentFindDto);
    @Transactional(rollbackFor = Exception.class)
    BaseResponse editStudent(StudentRequestDto studentRequestDto);
    BaseResponse createManyUserByExcel(MultipartFile multipartFile) throws IOException;
    @Transactional(readOnly = true)
    BaseResponse findAll();
}
