package ru.dmitriev.MySpringBoot2D.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitriev.MySpringBoot2D.dao.SubjectDAO;
import ru.dmitriev.MySpringBoot2D.entity.Subject;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    @Transactional
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    @Override
    @Transactional
    public Subject saveSubject(Subject subject) {
        return subjectDAO.saveSubject(subject);
    }

    @Override
    @Transactional
    public Subject getSubject(int id) {
        return subjectDAO.getSubject(id);
    }

    @Override
    @Transactional
    public boolean updateSubject(Subject subject) {
        if (subjectDAO.getSubject(subject.getId()) != null) {
            subjectDAO.saveSubject(subject);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteSubject(int id) {
        if (subjectDAO.getSubject(id) != null) {
            subjectDAO.deleteSubject(id);
            return true;
        }
        return false;
    }
}

