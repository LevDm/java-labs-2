package ru.dmitriev.MySpringBoot2D.dao;

import ru.dmitriev.MySpringBoot2D.entity.Subject;

import java.util.List;

public interface SubjectDAO {
    List<Subject> getAllSubjects();
    Subject saveSubject(Subject subject);
    Subject getSubject(int id);
    void deleteSubject(int id);
}
