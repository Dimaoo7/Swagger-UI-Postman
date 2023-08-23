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
    Long getLastID();


    //- Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    @Query(value = "SELECT COUNT(*) FROM student;", nativeQuery = true)
    List<Integer> getStudents();


    // По дамашке сказано надо взять средний возраст из метода findAll
    //- Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
 //   @Query(value = "SELECT avg(age) FROM student;", nativeQuery = true)
  //  List<Double> getAverageAge();


    //- Возможность получать только пять последних студентов. Последние студенты считаются теми, у кого идентификатор
    //- меньше 5. Эндпоинт должен вернуть список.
    @Query(value = "SELECT * FROM student OFFSET 5", nativeQuery = true)
    List<FiveLastStudents> getLastStudents();




}



