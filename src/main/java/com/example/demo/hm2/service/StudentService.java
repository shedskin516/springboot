package com.example.demo.hm2.service;

import com.example.demo.hm2.entity.Student;
import com.example.demo.hm2.entity.dto.StudentTeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    public Student createStudent(Student student);

    public Optional<Student> getStudentById(Long id);

    public Student updateStudent(Student studentDetails);

    public boolean deleteStudent(Long id);

    public List<Student> getAllStudents();

    public List<StudentTeacherDTO> findStudentsWithTeacherAndIdGreaterThan(int sId);
}
