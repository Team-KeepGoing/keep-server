package com.keepgoing.keepserver.domain.student.service;

import com.keepgoing.keepserver.domain.student.consts.StudentFindType;
import com.keepgoing.keepserver.domain.student.entity.Student;
import com.keepgoing.keepserver.domain.student.repository.StudentRepository;
import com.keepgoing.keepserver.domain.student.repository.dto.*;
import com.keepgoing.keepserver.global.common.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public BaseResponse uploadExcel(MultipartFile file) throws IOException {
        List<Student> studentList = new ArrayList<>();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (extension == null || !extension.equals("xlsx") && !extension.equals("xls")) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "엑셀 파일이 아닙니다.");
        }
        Workbook workbook;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            try {
                Row row = sheet.getRow(i);

                StudentDto excelDto = new StudentDto();

                excelDto.setGrade((int) row.getCell(0).getNumericCellValue());
                excelDto.setGroup((int) row.getCell(1).getNumericCellValue());
                excelDto.setGroupNum((int) row.getCell(2).getNumericCellValue());
                excelDto.setStudentName(row.getCell(3).getStringCellValue());
                excelDto.setPhoneNum(row.getCell(4).getStringCellValue());
                excelDto.setAddress(row.getCell(5).getStringCellValue());
                excelDto.setMail(row.getCell(6).getStringCellValue());

                Student student = excelDto.toEntity();
                studentList.add(student);
            } catch (Exception e) {
                return new BaseResponse(HttpStatus.BAD_REQUEST, "엑셀 파일이 아닙니다");
            }
        }
        studentRepository.saveAll(studentList);
        return new BaseResponse(HttpStatus.OK, "엑셀 업로딩 성공", studentList);
    }

    @Override
    public BaseResponse findAll() {
        ArrayList<StudentResponseDto> lst = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            lst.add(studentFormat(student, StudentFindType.WEB));
        }
        return new BaseResponse(HttpStatus.OK, "전체 학생 정보", lst);
    }

    public BaseResponse findByStudentName(StudentFindDto studentDto) {
        try {
            List<Student> stLst = studentRepository.findStudentsByStudentName(studentDto.getStudentName());
            if (stLst != null) {
                List<StudentResponseDto> stLstF = new ArrayList<>();
                for (Student student : stLst) {
                    stLstF.add(studentFormat(student, studentDto.getType()));
                }
                return new BaseResponse(HttpStatus.OK, "학생 정보 - 이름사용", stLstF);
            } else return new BaseResponse(HttpStatus.BAD_REQUEST, "학생 정보가 없습니다");

        } catch (Exception e) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "학생 정보가 없습니다");

        }
    }

    public BaseResponse findByStudentNum(StudentFindDto studentDto) {
        try {
            Student st = studentRepository.findStudentByGradeAndGroupAndGroupNum(studentDto.getGrade(), studentDto.getGroup(), studentDto.getGroupNum());
            if (st != null) {
                StudentResponseDto stResponse = studentFormat(st, studentDto.getType());
                return new BaseResponse(HttpStatus.OK, "학생 정보 - 번호사용", stResponse);
            } else return new BaseResponse(HttpStatus.BAD_REQUEST, "학생 정보가 없습니다");
        } catch (Exception e) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "잘못된 형식입니다.");
        }
    }


    @Transactional(rollbackOn = Exception.class)
    public BaseResponse editStudent(StudentRequestDto studentDto) {
        Student studentEntity = studentRepository.findStudentByGradeAndGroupAndGroupNum(studentDto.getGrade(), studentDto.getGroup(), studentDto.getGroupNum());

        if (studentDto.getStudentName() != null) studentEntity.setStudentName(studentDto.getStudentName());
        if (studentDto.getGrade() != 0) studentEntity.setGrade(studentDto.getGrade());
        if (studentDto.getGroup() != 0) studentEntity.setGroup(studentDto.getGroup());
        if (studentDto.getGroupNum() != 0) studentEntity.setGroupNum(studentDto.getGroupNum());
        if (studentDto.getPhoneNum() != null) studentEntity.setPhoneNum(studentDto.getPhoneNum());
        if (studentDto.getMail() != null) studentEntity.setMail(studentDto.getMail());
        if (studentDto.getAddress() != null) studentEntity.setAddress(studentDto.getAddress());

        studentRepository.save(studentEntity);
        return new BaseResponse(HttpStatus.OK, "학생 정보 수정 성공");
    }

    public StudentResponseDto studentFormat(Student student, StudentFindType num) {
        int grade = student.getGrade();
        int group = student.getGroup();
        int groupNum = student.getGroupNum();

        String formats = switch (num) {
            // 형식 1: 2학년 3반 4번 <iOS>
            case IOS -> String.format("%d학년 %d반 %d번", grade, group, groupNum);

            // 형식 2: 학년반번호 (2304) <web>
            case WEB -> String.format("%d%d%02d", grade, group, groupNum);
        };

        return StudentResponseDto.builder()
                .id(student.getId())
                .mail(student.getMail())
                .phoneNum(student.getPhoneNum())
                .studentName(student.getStudentName())
                .format(formats)
                .build();
    }
}
