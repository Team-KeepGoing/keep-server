package com.keepgoing.keepserver.domain.student.domain.repository;

import com.keepgoing.keepserver.domain.student.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentById(Long id);
    Student findStudentByStudentId(String studentId);
    List<Student> findStudentsByStudentName(String name);
}
