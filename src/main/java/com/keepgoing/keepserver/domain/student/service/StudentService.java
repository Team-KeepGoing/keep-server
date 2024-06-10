package com.keepgoing.keepserver.domain.student.service;

import com.keepgoing.keepserver.domain.student.entity.Student;
import com.keepgoing.keepserver.domain.student.repository.StudentRepository;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentFindDto;
import com.keepgoing.keepserver.domain.student.repository.dto.StudentRequestDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public BaseResponse findByStudent(StudentFindDto studentDto) {
        Student st = studentRepository.findStudentByNameAndGroupAndGroupNum(studentDto.getName(), studentDto.getGroup(), studentDto.getGrade());
        return new BaseResponse(HttpStatus.OK, "학생 정보", st);
    }

    public BaseResponse editStudent(StudentFindDto studentDto, StudentRequestDto studentRequestDto) {
        Student st = studentRepository.findStudentByNameAndGroupAndGroupNum(studentDto.getName(), studentDto.getGroup(), studentDto.getGrade());
        List<StudentRequestDto> list = new ArrayList<>();
        list.add(studentRequestDto);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName() != null) st.setName(list.get(i).getName());
            if (list.get(i).getGrade() != 0) st.setGrade(list.get(i).getGrade());
            if (list.get(i).getGroup() != 0) st.setGroup(list.get(i).getGroup());
            if (list.get(i).getGroupNum() != 0) st.setGroupNum(list.get(i).getGroupNum());
            if (list.get(i).getPhoneNum() != 0) st.setPhoneNum(list.get(i).getPhoneNum());
            if (list.get(i).getAddress() != null) st.setAddress(list.get(i).getAddress());
        }
        studentRepository.save(st);
        return new BaseResponse(HttpStatus.OK, "학생 정보 수정 성공");
    }

    public String  studentFormat(Student student,int num) {
        int grade = student.getGrade();
        int group = student.getGroup();
        int groupNum = student.getGroupNum();

        // 형식 1: 2학년 3반 4번
        String format1 = String.format("%d학년 %d반 %d번", grade, group, groupNum);

        // 형식 2: 학년반번호 (2304)
        String format2 = String.format("%d%d%02d", grade, group, groupNum);
        if (num == 1){
            return format1;
        }
        else if (num == 2){
            return format2;
        }
        else return null;
    }

}
