package ru.dmitriev.MySpringBoot2D.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmitriev.MySpringBoot2D.entity.Subject;
import ru.dmitriev.MySpringBoot2D.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable int id) {
        Subject subject = subjectService.getSubject(id);
        if (subject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject savedSubject = subjectService.saveSubject(subject);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable int id, @RequestBody Subject subject) {
        subject.setId(id);
        boolean updated = subjectService.updateSubject(subject);
        if (updated) {
            return ResponseEntity.ok("Предмет успешно обновлен");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Предмет с ID " + subject.getId() + " не найден");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable int id) {
        boolean deleted = subjectService.deleteSubject(id);
        if (deleted) {
            return ResponseEntity.ok("Предмет успешно удален");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Предмет с ID " + id + " не найден");
        }
    }
}
