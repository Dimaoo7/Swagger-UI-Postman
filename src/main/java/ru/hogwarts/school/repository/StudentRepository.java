package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long> {

    List<Student> findStudentByAge(int age);

    Student findStudentByNameContains(String name);

    List<Student> findByAgeBetween( int min, int max);

    List<Student> findStudentByFaculty(Faculty faculty);
}