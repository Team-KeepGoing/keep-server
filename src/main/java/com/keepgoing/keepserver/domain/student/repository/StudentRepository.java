package com.keepgoing.keepserver.domain.student.repository;

import com.keepgoing.keepserver.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByStudentId(String studentId);
    List<Student> findStudentsByStudentName(String name);
}
