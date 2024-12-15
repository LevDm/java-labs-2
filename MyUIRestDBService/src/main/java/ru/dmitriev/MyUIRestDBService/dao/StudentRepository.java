package ru.dmitriev.MyUIRestDBService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmitriev.MyUIRestDBService.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}