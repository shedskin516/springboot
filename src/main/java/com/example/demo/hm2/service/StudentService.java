package com.example.demo.hm2.service;

import com.example.demo.hm2.entity.Student;
import com.example.demo.hm2.entity.dto.StudentTeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    public Student createStudent(Student student);

    public Student getStudentById(Long id);

    public Student updateStudent(Long id, Student studentDetails);

    public boolean deleteStudent(Long id);

    public List<Student> getAllStudents();

    public List<StudentTeacherDTO> findStudentsWithTeacherAndIdGreaterThan(int sId);
}
