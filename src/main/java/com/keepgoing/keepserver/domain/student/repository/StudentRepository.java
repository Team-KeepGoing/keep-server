package com.keepgoing.keepserver.domain.student.repository;

import com.keepgoing.keepserver.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentsByGradeAndGroupAndGroupNum(int grade, int group, int groupNum);
    Student findStudentsByStudentName(String name);
    Student findStudentsByStudentNameAndGroupAndGroupNum(String name, int group,int groupNum);
}
