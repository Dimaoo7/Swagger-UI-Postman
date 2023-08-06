package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findAllByAgeBetween(int min, int max);

    List<Student> findAllByFaculty_id(Long id);

    @Query(value = "select id from student order by id desc limit 1;",nativeQuery = true)
    Long findLastID();


    List<Student> findStudentByAge(Integer age);

    List<Student> findStudentByAgeBetween(Integer min, Integer max);
}
