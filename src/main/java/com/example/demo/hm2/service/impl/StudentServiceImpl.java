package com.example.demo.hm2.service.impl;

import com.example.demo.hm2.entity.Student;
import com.example.demo.hm2.entity.Teacher;
import com.example.demo.hm2.entity.dto.StudentTeacherDTO;
import com.example.demo.hm2.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final EntityManager entityManager;

    @Autowired
    public StudentServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setAge(studentDetails.getAge());
            student.setGender(studentDetails.getGender());
            entityManager.merge(student);
        }
        return student;
    }

    @Override
    @Transactional
    public boolean deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    @Override
    public List<StudentTeacherDTO> findStudentsWithTeacherAndIdGreaterThan(int sId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);

        Root<Student> student = query.from(Student.class);
        Join<Student, Teacher> teacherJoin = student.join("teachers", JoinType.LEFT);

        query.multiselect(
                        student.get("id"),
                        student.get("firstName"),
                        student.get("lastName"),
                        teacherJoin.get("id"),
                        teacherJoin.get("firstName"),
                        teacherJoin.get("firstName"),
                        teacherJoin.get("subject")
                )
                .where(cb.greaterThan(student.get("id"), sId));

        List<Object[]> results = entityManager.createQuery(query).getResultList();
        List<StudentTeacherDTO> dtoList = new ArrayList<>();

        for (Object[] result : results) {
            Long sIdResult = (Long) result[0];
            String sFirstName = (String) result[1];
            String sLastName = (String) result[2];
            Long tId = (Long) result[3];
            String tFirstName = (String) result[4];
            String tLastName = (String) result[5];
            String subject = (String) result[6];

            StudentTeacherDTO dto = new StudentTeacherDTO(sIdResult, sFirstName, sLastName, tId, tFirstName, tLastName, subject);
            dtoList.add(dto);
        }

        return dtoList;
    }
}

