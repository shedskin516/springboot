package com.example.demo.hm2.service.impl;

import com.example.demo.hm2.entity.Student;
import com.example.demo.hm2.entity.dto.StudentTeacherDTO;
import com.example.demo.hm2.repository.StudentRepositoryImpl;
import com.example.demo.hm2.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepositoryImpl stRepo;

    @Autowired
    public StudentServiceImpl(StudentRepositoryImpl stRepo) {
        this.stRepo = stRepo;
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {
        return stRepo.save(student);
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return stRepo.findById(id);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return stRepo.save(student);
    }

    @Override
    @Transactional
    public boolean deleteStudent(Long id) {
        return stRepo.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return stRepo.findAll();
    }

    @Override
    public List<StudentTeacherDTO> findStudentsWithTeacherAndIdGreaterThan(int sId) {
        return stRepo.findSIdGreaterThan(sId);
    }
}

