package com.example.demo.hm2.repository;

import com.example.demo.hm2.entity.Student;
import com.example.demo.hm2.entity.StudentTeacher;
import com.example.demo.hm2.entity.Teacher;
import com.example.demo.hm2.entity.dto.StudentTeacherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
            return student;
        } else {
            return entityManager.merge(student);
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return Optional.ofNullable(student);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    @Override
    public List<StudentTeacherDTO> findSIdGreaterThan(int sId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Student> student = cq.from(Student.class);
        Join<Student, StudentTeacher> studentTeacher = student.join("studentTeachers", JoinType.LEFT);
        Join<StudentTeacher, Teacher> teacher = studentTeacher.join("teacher", JoinType.LEFT);

        cq.multiselect(student, teacher)
                .where(cb.and(
                        cb.gt(student.get("id"), sId),
                        cb.isNotNull(teacher.get("id")) // Ensure Teacher is not null
                ));

        TypedQuery<Object[]> query = entityManager.createQuery(cq);
        List<Object[]> result = query.getResultList();

        // Convert result to List<StudentTeacherDTO>
        return result.stream()
                .map(record -> {
                    Student studentObj = (Student) record[0];
                    Teacher teacherObj = (Teacher) record[1];

                    return new StudentTeacherDTO(
                            studentObj.getId(),
                            studentObj.getFirstName(),
                            studentObj.getLastName(),
                            teacherObj.getId(),
                            teacherObj.getFirstName(),
                            teacherObj.getLastName(),
                            teacherObj.getSubject()
                    );
                })
                .collect(Collectors.toList());
    }
}
