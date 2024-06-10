package com.keepgoing.keepserver.domain.student.repository;

import com.keepgoing.keepserver.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByNameAndGroupAndGroupNum(String Name, int group, int groupNum);
}
