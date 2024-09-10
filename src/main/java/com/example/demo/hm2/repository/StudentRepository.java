package com.example.demo.hm2.repository;

import com.example.demo.hm2.entity.Student;
import com.example.demo.hm2.entity.dto.StudentTeacherDTO;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long id);
    boolean deleteById(Long id);
    List<Student> findAll();

    List<StudentTeacherDTO> findSIdGreaterThan(int sId);
}
