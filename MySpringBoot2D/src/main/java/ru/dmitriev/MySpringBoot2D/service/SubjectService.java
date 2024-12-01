package ru.dmitriev.MySpringBoot2D.service;

import ru.dmitriev.MySpringBoot2D.entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject saveSubject(Subject subject);
    Subject getSubject(int id);
    boolean updateSubject(Subject subject);
    boolean deleteSubject(int id);
}
