package com.keepgoing.keepserver.domain.student.service;

import com.keepgoing.keepserver.domain.student.entity.Student;
import com.keepgoing.keepserver.domain.student.repository.StudentRepository;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentDto;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentFindDto;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentRequestDto;
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
public class StudentService {
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
                excelDto.setName(row.getCell(3).getStringCellValue());
                excelDto.setAddress(row.getCell(4).getStringCellValue());

                Student student = excelDto.toEntity();
                studentList.add(student);
            } catch (Exception e) {
                return new BaseResponse(HttpStatus.BAD_REQUEST, "니 엑셀 개판이다.");
            }
        }
        studentRepository.saveAll(studentList);
        return new BaseResponse(HttpStatus.OK, "엑셀 업로딩 성공", studentList);
    }

    public BaseResponse findByStudent(StudentFindDto studentDto, int num) {
        Student st = studentRepository.findStudentByNameAndGroupAndGroupNum(studentDto.getName(), studentDto.getGroup(), studentDto.getGrade());
        return new BaseResponse(HttpStatus.OK, "학생 정보", studentFormat(st,num));
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseResponse editStudent(StudentFindDto find, StudentRequestDto studentDto) {
        Student studentEntity = studentRepository.findStudentByNameAndGroupAndGroupNum(find.getName(), find.getGroup(), find.getGrade());

        if (studentDto.getName() != null) studentEntity.setName(studentDto.getName());
        if (studentDto.getGrade() != 0) studentEntity.setGrade(studentDto.getGrade());
        if (studentDto.getGroup() != 0) studentEntity.setGroup(studentDto.getGroup());
        if (studentDto.getGroupNum() != 0) studentEntity.setGroupNum(studentDto.getGroupNum());
        if (studentDto.getPhoneNum() != 0) studentEntity.setPhoneNum(studentDto.getPhoneNum());
        if (studentDto.getAddress() != null) studentEntity.setAddress(studentDto.getAddress());
        studentRepository.save(studentEntity);
        return new BaseResponse(HttpStatus.OK, "학생 정보 수정 성공");
    }

    public String studentFormat(Student student, int num) {
        int grade = student.getGrade();
        int group = student.getGroup();
        int groupNum = student.getGroupNum();

        // 형식 1: 2학년 3반 4번
        String format1 = String.format("%d학년 %d반 %d번", grade, group, groupNum);

        // 형식 2: 학년반번호 (2304)
        String format2 = String.format("%d%d%02d", grade, group, groupNum);
        if (num == 1) {
            return format1;
        } else if (num == 2) {
            return format2;
        } else return null;
    }

}
