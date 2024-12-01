package ru.dmitriev.MySpringBoot2D.dao;

import org.springframework.stereotype.Repository;
import ru.dmitriev.MySpringBoot2D.entity.Student;

import java.util.List;

@Repository
public interface StudentDAO {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}
