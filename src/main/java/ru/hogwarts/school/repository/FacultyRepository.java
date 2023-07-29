package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findFacultyByColorIgnoreCase(String Color);

    Faculty findFacultyByNameContainsIgnoreCase(String name);

    Faculty findFacultyByStudent(List<Student> student);
}
